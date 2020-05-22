package ptitcode.controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ptitcode.entity.*;


@Transactional
@Controller
public class ExerciseController {
	
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ServletContext context;
	
	
	@RequestMapping("/exercise")
	public String showExercise1(ModelMap model){	
		Session session= factory.getCurrentSession();
		String hql="FROM Exercise";
		Query query= session.createQuery(hql);
		List<Exercise> list=query.list();
		model.addAttribute("exercise",list);	
		return "exercise";
	}
	
	
	@RequestMapping("/exercise/view/{id}")
	public String views(ModelMap model, @PathVariable("id") int id) {
		Session session = factory.getCurrentSession();
		Exercise exercise = (Exercise) session.get(Exercise.class,id);
		model.addAttribute("exercise", exercise);
		return "exercise/details";
		
	}
	
	@RequestMapping(value="/exercise/add-solution/{id}", method=RequestMethod.GET)
	public String addSolution(ModelMap model,@PathVariable("id") int id){
		
		
		if (ExerciseIsExist(id)==false){
			return "404";
		}
		Session session = factory.getCurrentSession();
		Exercise exercise = (Exercise) session.get(Exercise.class,id);
		model.addAttribute("exercise", exercise);
		File f = new File(context.getRealPath("/solution/" + String.valueOf(id)+".cpp"));
		if(f.exists() && !f.isDirectory()) { 
			model.addAttribute("message","success");
			model.addAttribute("body", readSolution(id));
		}
		else
		{
			model.addAttribute("message","fail");
		}
		return "exercise/add-solution";
	}
	
	@RequestMapping(value="/exercise/add-solution/{id}", method=RequestMethod.POST)
	public String addSolution(ModelMap model,@PathVariable("id") int id, @RequestParam("file") MultipartFile file){
		Session session = factory.getCurrentSession();
		Exercise exercise = (Exercise) session.get(Exercise.class,id);
		model.addAttribute("exercise", exercise);
		if (file.isEmpty()){
			model.addAttribute("uploadErr", "Vui lòng chọn file");
		}
		else
		{
			if (saveSolutionFile(file, id)==true){
				if (CompileFile(id)==false){
					model.addAttribute("message","fail");
				}
				else
				{
					model.addAttribute("message","success");	
				}		
			}
			else{
				model.addAttribute("message","fail");
			}
		}
		model.addAttribute("body", readSolution(id));
		
		return "exercise/add-solution";
	}
	
	
	
	
	
	@RequestMapping(value="/exercise/add-testcase/{id}", method=RequestMethod.GET)
	public String addTestcase(ModelMap model,@PathVariable("id") int id){
		
		if (ExerciseIsExist(id)==false){
			return "404";
		}
		List <Testcase> list= new ArrayList<>();
		Session session = factory.getCurrentSession();
		Exercise exercise = (Exercise) session.get(Exercise.class,id);
		model.addAttribute("exercise", exercise);
		createTetcaseDicretory(id);
		
		// read testcase
		File directory=new File(context.getRealPath("/testcase/"+String.valueOf(id)+"/"));
	    int fileCount=directory.list().length;
	    fileCount=fileCount;
	    for (int i=1;i<=fileCount;i++){
	    	Testcase test= new Testcase();
	    	test.setInput(readTestcaseInput(id,i));
	    	list.add(test);
	    }
	    
	    model.addAttribute("items",list);
		return "exercise/add_testcase";	
	}
	
	
	
	// add new exercise POST // insert
		@RequestMapping(value="/exercise/add-testcase/{id}", method=RequestMethod.POST)
		public String addTestCase(ModelMap model,@PathVariable("id") int id,@RequestParam("file") MultipartFile testcase){
			
			List <Testcase> list= new ArrayList<>();
			Session session = factory.getCurrentSession();
			Exercise exercise = (Exercise) session.get(Exercise.class,id);
			model.addAttribute("exercise", exercise);
			if (testcase.isEmpty()){
				model.addAttribute("uploadErr", "Vui lòng chọn file");
			}
			else
			{
				if (saveTestcaseFile(testcase, id)==true){
					model.addAttribute("message","success");	
				}
				else{
					model.addAttribute("message","fail");
				}
			}
			
			// read testcase
			File directory=new File(context.getRealPath("/testcase/"+String.valueOf(id)+"/"));
		    int fileCount=directory.list().length;
		    fileCount=fileCount;
		    for (int i=1;i<=fileCount;i++){
		    	Testcase test= new Testcase();
		    	test.setInput(readTestcaseInput(id,i));
		    	list.add(test);
		    }   
		    model.addAttribute("items",list);
			return "exercise/add_testcase";
		}
		
	
	

		// add new exercise GET
		@RequestMapping(value="/exercise/add-exercise", method=RequestMethod.GET)
		public String addExercise(ModelMap model){
			model.addAttribute("exercise", new Exercise());
			return "admin/new_exercise";
		}
		
		
	// add new exercise POST // insert
	@RequestMapping(value="/exercise/add-exercise", method=RequestMethod.POST)
	public String insertExercise(ModelMap model, @ModelAttribute("exercise") Exercise exercise){
		Session session = factory.openSession();
		Transaction t= session.beginTransaction();
		try{
			session.save(exercise);
			t.commit();
			model.addAttribute("message","success");
		}
		catch(Exception e){
			t.rollback();
			model.addAttribute("message","fail");
		}
		finally {
			//session.close();
		}
		return "admin/new_exercise";
	}
	
	
	// edit exercise // edit   // GET request
	@RequestMapping(value = "/exercise/update/{id}",method = RequestMethod.GET)
	public String update(ModelMap model, @PathVariable("id") int id) {
		Session session = factory.getCurrentSession();
		Exercise exercise = (Exercise) session.get(Exercise.class,id);
		model.addAttribute("exercise", exercise);
		return "exercise/edit";	
	}
	// edit exercise // edit   // POST request
	
	@RequestMapping(value = "/exercise/update/{id}", method = RequestMethod.POST)
	public String update(ModelMap model, @ModelAttribute("exercise") Exercise exercise/*, @PathVariable("id") int id*/) {	
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(exercise);
			t.commit();
			model.addAttribute("message","success");
		} catch (Exception e) {
			t.rollback();
			System.out.print(e.getMessage());
			model.addAttribute("message","fail");	
		} finally {
			//Exercise exercise1 = (Exercise) session.get(Exercise.class,id);
			//model.addAttribute("exercise", exercise1);
			session.close();			
		}
		return "exercise/edit";
	}
	
	public boolean saveSolutionFile(MultipartFile photo, int exerciseID){
		boolean result=true;
		try {
			System.out.println(context.getRealPath(""));
			String photoPath = context.getRealPath("/solution/" + String.valueOf(exerciseID)+".cpp");
			photo.transferTo(new File(photoPath));
		} catch (Exception e) {
			System.out.print(e.getMessage());
			result=false;
		}	
		return result;
	}
	
	public String readSolution(int id){
		StringBuffer sb = new StringBuffer(); // constructs a string buffer
		// with no characters
		
		try {
			File file = new File(context.getRealPath("/solution/"+id+".cpp")); // creates a new file instance
			FileReader fr = new FileReader(file); // reads the file
			BufferedReader br = new BufferedReader(fr); // creates a buffering
														// character input
														// stream
			
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line); // appends line to string buffer
				sb.append("\n"); // line feed
			}
			fr.close(); // closes the stream and release the resources
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	 public boolean ExerciseIsExist(int exerciseID){
	    	Session session =factory.getCurrentSession();
			Exercise user=null;
			boolean re=false;
			user=(Exercise)session.get(Exercise.class,exerciseID);
			if (user!=null)
					re=true; 
				else 
					re=false;
			return re;		
	    }
	 
	 public void createTetcaseDicretory(int exerciseID){
			File file = new File(context.getRealPath("/testcase/"+String.valueOf(exerciseID)+"/"));
			boolean fileExists = file.exists();
			boolean dirCreated = file.mkdir();
		}
	 
	 public boolean saveTestcaseFile(MultipartFile testcase, int exerciseID){
			boolean result=true;
			File directory=new File(context.getRealPath("/testcase/"+String.valueOf(exerciseID)+"/"));
		    int fileCount=directory.list().length;
		    fileCount=fileCount/2;
		    System.out.println(fileCount);
			try {
				String photoPath = context.getRealPath("/testcase/"+String.valueOf(exerciseID)+"/"+String.valueOf(fileCount+1)+".inp");
				testcase.transferTo(new File(photoPath));
			} catch (Exception e) {
				System.out.print(e.getMessage());
				result=false;
			}	
			return result;
		}
	 
	 public String readTestcaseInput(int id, int num){
			StringBuffer sb = new StringBuffer(); // constructs a string buffer
			// with no characters
			try {
				File file = new File(context.getRealPath("/testcase/"+String.valueOf(id)+"/"+String.valueOf(num)+".inp")); 
				FileReader fr = new FileReader(file); // reads the file
				BufferedReader br = new BufferedReader(fr); 
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line); // appends line to string buffer
					sb.append("\n"); // line feed
				}
				fr.close(); // closes the stream and release the resources
			} catch (IOException e) {
				e.printStackTrace();
			}
			return sb.toString();
		}
	 
	 public String readTestcaseOutput(int id, int num){
			StringBuffer sb = new StringBuffer(); // constructs a string buffer
			// with no characters
			try {
				File file = new File(context.getRealPath("/testcase/"+String.valueOf(id)+"/"+String.valueOf(num)+".out")); 
				FileReader fr = new  FileReader(file); // reads the file
				BufferedReader br = new BufferedReader(fr); 
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line); // appends line to string buffer
					sb.append("\n"); // line feed
				}
				fr.close(); // closes the stream and release the resources
			} catch (IOException e) {
				e.printStackTrace();
			}
			return sb.toString();
		}
	 
	 public boolean CompileFile(int exerciseID){
		 	boolean result=true;
		    String s="cd ../../Workspace/Eclipse/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/PTITCoding/solution&g++ "+ String.valueOf(exerciseID)+".cpp -o "+String.valueOf(exerciseID)+".exe";   
			String commandArray[] = {"cmd", "/c", s}; 
			try {
				Process process = Runtime.getRuntime().exec(commandArray); 
			 
			    BufferedReader reader = new BufferedReader(
			            new InputStreamReader(process.getInputStream()));
			    BufferedReader errinput = new BufferedReader(new InputStreamReader(
			    		process.getErrorStream()));
			    String line;
			    while ((line = reader.readLine()) != null) {
			        System.out.println(line);
			    }	    
			    if (errinput.readLine() != null) result=false;
			    reader.close();
			} catch (IOException e) {
			    e.printStackTrace();
			}
			return result;
		}
	 
}
