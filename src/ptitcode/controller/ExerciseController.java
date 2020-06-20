package ptitcode.controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
import org.springframework.validation.BindingResult;
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
	
	
	public int getAllPage(){
		int numPost=20;
		int numPage=0;
		Session session = factory.getCurrentSession();
		String hql = "select count(*) from Submit";
		Query query = session.createQuery(hql);
		Long count =(long)query.uniqueResult();
		if (count%numPost==0) numPage=(int) (count/numPost);
		else numPage=(int) (count/numPost+1);
		return numPage;
	}
	
	public List<Submit> getPage(int i){
		Session session = factory.getCurrentSession();
		String hql = "FROM Submit ORDER BY submitID DESC";
		Query query = session.createQuery(hql);
		query.setFirstResult(i);
		query.setMaxResults(20);
		List<Submit> list = query.list();
		return list;
	}
	
	
	
	@RequestMapping("/exercise")
	public String showExercise1(ModelMap model){	
		// exercise
		Session session= factory.getCurrentSession();
		String hql="FROM Exercise";
		Query query= session.createQuery(hql);
		List<Exercise> list=query.list();
		model.addAttribute("exercise",list);		
		
		
		//submit	
		int numPage=getAllPage();
		List<Submit> list1 = getPage(0);
		model.addAttribute("submit", list1);
		model.addAttribute("allpage",numPage);
		model.addAttribute("current",1);
		return "exercise";
	}
	
	@RequestMapping(value="/exercise",params="next")
	public String showExercise2(ModelMap model, HttpServletRequest request){	
		// exercise
		Session session= factory.getCurrentSession();
		String hql="FROM Exercise";
		Query query= session.createQuery(hql);
		List<Exercise> list=query.list();
		model.addAttribute("exercise",list);		
		
		
		//submit	
		int numPost=20;
		int numPage=getAllPage();
		int page=Integer.valueOf(request.getParameter("next"));
		
		List<Submit> list1 = getPage(numPost*(page-1));
		model.addAttribute("submit", list1);
		model.addAttribute("allpage",numPage);
		model.addAttribute("current",page);
		return "exercise";
	}
	
	
	@RequestMapping("/exercise/view/{id}")
	public String views(ModelMap model, @PathVariable("id") int id, HttpSession session1) {
		if (ExerciseIsExist(id)==false){
			return "404";
		}
		if (session1!=null){
			String username= (String) session1.getAttribute("username");
			if (isSolve(id, username)==true){
				model.addAttribute("usersolve","Bạn đã giải bài này");
			}
		}
		
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
	    fileCount=fileCount/2;
	    for (int i=1;i<=fileCount;i++){
	    	Testcase test= new Testcase();
	    	test.setInput(readTestcaseInput(id,i));
	    	test.setOutput(readTestcaseOutput(id,i));
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
		    fileCount=fileCount/2;
		    for (int i=1;i<=fileCount;i++){
		    	Testcase test= new Testcase();
		    	test.setInput(readTestcaseInput(id,i));
		    	test.setOutput(readTestcaseOutput(id,i));
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
	public String insertExercise(ModelMap model, @ModelAttribute("exercise") Exercise exercise, BindingResult error){
		
		if (exercise.getName().trim().length()==0){
			error.rejectValue("name","exercise","Tên không được để trống");
		}
		else if (exercise.getType().trim().length()==0){
			error.rejectValue("type","exercise","Dạng bài không được trống");
		}
		else if (exercise.getName().trim().length()==0){
			error.rejectValue("name","exercise","Tên không được để trống");
		}
		else if (exercise.getDetail().trim().length()==0){
			error.rejectValue("detail","exercise","Mô tả không được để trống");
		}
		else if (exercise.getInput().trim().length()==0){
			error.rejectValue("input","exercise","Input không được để trống");
		}
		else if (exercise.getOutput().trim().length()==0){
			error.rejectValue("output","exercise","Output không được để trống");
		}
		
		if (!error.hasErrors()){
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
	public String update(ModelMap model, @ModelAttribute("exercise") Exercise exercise, @PathVariable("id") int id, BindingResult error) {	
		
		if (exercise.getName().trim().length()==0){
			error.rejectValue("name","exercise","Tên không được để trống");
		}
		else if (exercise.getType().trim().length()==0){
			error.rejectValue("type","exercise","Dạng bài không được trống");
		}
		else if (exercise.getName().trim().length()==0){
			error.rejectValue("name","exercise","Tên không được để trống");
		}
		else if (exercise.getDetail().trim().length()==0){
			error.rejectValue("detail","exercise","Mô tả không được để trống");
		}
		else if (exercise.getInput().trim().length()==0){
			error.rejectValue("input","exercise","Input không được để trống");
		}
		else if (exercise.getOutput().trim().length()==0){
			error.rejectValue("output","exercise","Output không được để trống");
		}
		
		if (!error.hasErrors()) {

			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			Session session1 = factory.openSession();
			Transaction t1 = session1.beginTransaction();
			Exercise exercise1 = (Exercise) session1.get(Exercise.class, id);
			exercise.setSolves(exercise1.getSolves());
			System.out.println(exercise.getSolves());
			try {
				session.update(exercise);
				t.commit();
				model.addAttribute("message", "success");
			} catch (Exception e) {
				t.rollback();
				System.out.print(e.getMessage());
				model.addAttribute("message", "fail");
			} finally {
				// Exercise exercise1 = (Exercise)
				// session.get(Exercise.class,id);
				// model.addAttribute("exercise", exercise1);
				session.close();
				session1.close();
			}
		}
		return "exercise/edit";
	}
	
	@RequestMapping(value = "/exercise/delete/{id}", method = RequestMethod.GET)
	public String delete(ModelMap model, @PathVariable("id") int id) {	

			Session session1 = factory.openSession();
			Transaction t1 = session1.beginTransaction();
			Exercise exercise1 = (Exercise) session1.get(Exercise.class, id);
			try {
				session1.delete(exercise1);
				t1.commit();
			} catch (Exception e) {
				t1.rollback();
			} finally {
				session1.close();
			}
		return "200";
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
				writeToInputFile(String.valueOf(exerciseID),String.valueOf(fileCount+1));
				if (RunCode()==false) 
					{
					DeleteTestCaseFile(exerciseID,fileCount+1);
					result=false;
					}
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
	 
	 
	 public void writeToInputFile(String exesrc,String inout) 
			 throws IOException {
		    FileWriter fileWriter = new FileWriter(context.getRealPath("/input.txt"));
		    PrintWriter printWriter = new PrintWriter(fileWriter);
		    printWriter.println(context.getRealPath("")+"solution\\"+exesrc+".exe");
		    printWriter.println(context.getRealPath("")+"testcase\\"+exesrc+"\\"+inout+".inp");
		    printWriter.print(context.getRealPath("")+"testcase\\"+exesrc+"\\"+inout+".out");
		    printWriter.close();
	}	 
	 
	 public boolean RunCode(){
		    boolean result=true;
			String s="cd ../../Workspace/Eclipse/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/PTITCoding&chamcode.exe < input.txt";
			
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
			    while ((line = errinput.readLine()) != null) {
			        System.out.println(line);
			    }
			    if (errinput.readLine() != null) result=false;
			    reader.close();
			 
			} catch (IOException e) {
			    e.printStackTrace();
			}
			return result;
		} 
	 public void DeleteTestCaseFile(int exerciseID, int num){
		 String exesrc= String.valueOf(exerciseID);
		 String inout=String.valueOf(num);
		 try {
	            File file = new File(context.getRealPath("")+"testcase\\"+exesrc+"\\"+inout+".inp");
	            if (file.delete()) {
	                System.out.println(file.getName() + " is deleted!");
	            } else {
	                System.out.println("Delete operation is failed.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 try {
	            File file = new File(context.getRealPath("")+"testcase\\"+exesrc+"\\"+inout+".out");
	            if (file.delete()) {
	                System.out.println(file.getName() + " is deleted!");
	            } else {
	                System.out.println("Delete operation is failed.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	 }
	 
	 public boolean isSolve(int exerciseID, String username){
		    int  number=0;
			boolean result=true;
		    Session session = factory.getCurrentSession();
			String hql = "select  COUNT(*) from Submit where username=:username and exerciseID=:exerciseID and answer=0";
			Query query = session.createQuery(hql);
			query.setParameter("exerciseID",exerciseID);
			query.setParameter("username",username);
			Long count =(long)query.uniqueResult();
			number=(int) (count/1);
			if (number<=0) result=false;
			return result;
		}
 
}
