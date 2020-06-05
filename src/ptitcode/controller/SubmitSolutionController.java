package ptitcode.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import ptitcode.controller.ExerciseController;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ptitcode.entity.Exercise;
import ptitcode.entity.Post;
import ptitcode.entity.Submit;
import ptitcode.entity.Testcase;
import ptitcode.entity.User;
import ptitcode.entity.UserInfo;

@Transactional
@Controller
public class SubmitSolutionController {

	@Autowired
	SessionFactory factory;
	
	@Autowired
	ServletContext context;
	
	
	
	
	@RequestMapping(value = "/submit/{id}",method = RequestMethod.GET)
	public String submit(ModelMap model, @PathVariable("id") int id) {
		if (ExerciseIsExist(id)==false){
			return "404";
		} 
		Session session = factory.getCurrentSession();
		Exercise exercise = (Exercise) session.get(Exercise.class,id);
		//String command = "dir";
		model.addAttribute("exercise", exercise);
		//testPing();
		return "submit-code";	
	}
	
	@RequestMapping(value = "/submit/{id}",method = RequestMethod.POST)
	public String submiter(ModelMap model, @PathVariable("id") int id,HttpServletRequest request,HttpSession session1,@RequestParam("myfile")MultipartFile file) throws IOException {
		if (ExerciseIsExist(id)==false){
			return "404";
		} 
		int answer=0; 
		String time="";
		Submit submit= new Submit();
		String username= (String) session1.getValue("username");	
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		
		// set SubmitID
		File directory=new File(context.getRealPath("/submited/"));
	    int submitID=directory.list().length+1;
		submit.setUsername(username);
		submit.setExerciseID(id);
		submit.setSubmitID(submitID);
		submit.setTimesubmit(timeStamp);
		
		// write to sql database
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(submit);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
		
		// write to file : send text or send file
		if (file.isEmpty()){
			createSubmitDicretory(submitID);
			writeToInput(request.getParameter("code"),submitID);
		}
		else{
			try {
				createSubmitDicretory(submitID);
				String photoPath = context.getRealPath("submited/"+String.valueOf(submitID)+"/submit.cpp");
				file.transferTo(new File(photoPath));
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}	
		}
		// compiler code
		if (CompileFile(submitID)==false){
			// error when compile code
			answer=-1;
		}
		else{
			// complie code successful
			
			File directory1=new File(context.getRealPath("/testcase/"+String.valueOf(id)+"/"));
		    int fileCount=directory1.list().length;
		    fileCount=fileCount/2;
		    for (int i=1;i<=fileCount;i++){
		    	writeToInputFile(String.valueOf(id),String.valueOf(i),String.valueOf(submitID));
				time=RunCode();
				String s1= readTestcaseOutput(id,i);
				String s2= readSubmitOutput(submitID,i);
				if (s1.equals(s2)){			
				}
				else{
					answer=i; break;
				}
		    }  
			submit.setTimerun(Integer.parseInt(time));
		}
		 submit.setAnswer(answer);
		// update to database: submit
		Session session2 = factory.openSession();
		Transaction t2 = session2.beginTransaction();
		try {
			session2.update(submit);
			t2.commit();
		} catch (Exception e) {
			t2.rollback();
		} finally {
			session2.close();
		}
		
		
		// update to database: exrciseID
		Session session3 = factory.openSession();
		Transaction t3 = session3.beginTransaction();
		Exercise exercise = (Exercise) session3.get(Exercise.class,id);
		if (isSolve(exercise.getExerciseID(), username)==false){
			exercise.setSolves(exercise.getSolves()+1);
			try {
				session3.update(exercise);
				t3.commit();
			} catch (Exception e) {
				t3.rollback();
			} finally {
				session3.close();
			}
		}
		return "thankforsubmit";	
	}
	
	@RequestMapping(value = "/submit/view/{id}",method = RequestMethod.GET)
	public String viewSubmit(ModelMap model, @PathVariable("id") int id) {
		
		// get data from database
		Session session = factory.getCurrentSession();
		Submit submit = (Submit) session.get(Submit.class,id);
		model.addAttribute("submit", submit);

		
		// load data from file
		List <Testcase> list= new ArrayList<>();
		File directory=new File(context.getRealPath("/submited/"+String.valueOf(id)+"/"));
		int fileCount=directory.list().length-2;
	    for (int i=1;i<=fileCount;i++){
	    	Testcase test= new Testcase();
	    	test.setInput(readTestcaseInput(submit.getExerciseID(),i));
	    	test.setOutput(readTestcaseOutput(submit.getExerciseID(),i));
	    	test.setOutput1(readSubmitOutput(id,i));
	    	test.setAnswer(submit.getAnswer());
	    	list.add(test);
	    }
		model.addAttribute("inout",list);
		
		
		
		return "exercise/view-submit";
	}
	
	
	
	@RequestMapping("comile")
	public void show(){
		String s1= readTestcaseOutput(20,1);
		String s2= readSubmitOutput(5, 1);
		if (s1.equals(s2)){
			System.out.println("Correct");
		}
		else{
			System.out.println("Wrong Answer");
		}
	}
	

	@RequestMapping(value = "/readfile")
	public String update(ModelMap model) {
		String username="nht";
		try {
			File file = new File(context.getRealPath("/solution/"+username+"/1.txt")); // creates a new file instance
			FileReader fr = new FileReader(file); // reads the file
			BufferedReader br = new BufferedReader(fr); // creates a buffering
														// character input
														// stream
			StringBuffer sb = new StringBuffer(); // constructs a string buffer
													// with no characters
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line); // appends line to string buffer
				sb.append("\n"); // line feed
			}
			fr.close(); // closes the stream and release the resources
			System.out.println("Contents of File: ");
			System.out.println(sb.toString()); // returns a string that
												// textually represents the
												// object
			model.addAttribute("mess",sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "readfile";
		
	}
	
	public void createSubmitDicretory(int submitID){
		File file = new File(context.getRealPath("/submited/"+String.valueOf(submitID)+"/"));
		boolean fileExists = file.exists();
		boolean dirCreated = file.mkdir();
	}
	
	
	
	
	
	
	@RequestMapping(value = "/submit")
	public void test(HttpSession session){
		System.out.println(context.getRealPath(""));
		String username="nht";
		System.out.println(session.getAttribute("username"));
		File file = new File(context.getRealPath("/solution/"+username+"/"));
		boolean fileExists = file.exists();
		boolean dirCreated = file.mkdir();
		File directory=new File(context.getRealPath("/solution/"+username+"/"));
	    int fileCount=directory.list().length;
	    System.out.println("File Count:"+fileCount);
		System.out.println("Okay");	
	}
	
	public void writeToInput(String s, int submitID) 
			  throws IOException {
			    FileWriter fileWriter = new FileWriter(context.getRealPath("submited/"+String.valueOf(submitID)+"/submit.cpp"));
			    PrintWriter printWriter = new PrintWriter(fileWriter);
			    printWriter.println(s);
			    printWriter.close();
	}
	
	
	public void testPing(){
		int i=20;
		String s="cd ../../Workspace/Eclipse/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/PTITCoding/solution&g++ "+ String.valueOf(i)+".cpp -o "+String.valueOf(i)+".exe";
		
		String commandArray[] = {"cmd", "/c", s}; 
		try {
			Process process = Runtime.getRuntime().exec(commandArray); 
		 
		    BufferedReader reader = new BufferedReader(
		            new InputStreamReader(process.getInputStream()));
		    String line;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		 
		    reader.close();
		 
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void testChamCode(){
		int i=20;
		String s="cd ../../Workspace/Eclipse/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/PTITCoding&chamcode.exe < input.txt";
		
		String commandArray[] = {"cmd", "/c", s}; 
		try {
			Process process = Runtime.getRuntime().exec(commandArray); 
		 
		    BufferedReader reader = new BufferedReader(
		            new InputStreamReader(process.getInputStream()));
		    String line;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		 
		    reader.close();
		 
		} catch (IOException e) {
		    e.printStackTrace();
		}
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
	 
	 public boolean CompileFile(int submitID){
		 	boolean result=true;
		    String s="cd ../../Workspace/Eclipse/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/PTITCoding/submited/"+String.valueOf(submitID)+" &g++ submit.cpp -o submit.exe";   
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
	 
	 public void writeToInputFile(String exesrc,String inout, String submitID) 
			 throws IOException {
		    FileWriter fileWriter = new FileWriter(context.getRealPath("/input.txt"));
		    PrintWriter printWriter = new PrintWriter(fileWriter);
		    printWriter.println(context.getRealPath("")+"submited\\"+submitID+"\\submit.exe");
		    printWriter.println(context.getRealPath("")+"testcase\\"+exesrc+"\\"+inout+".inp");
		    printWriter.print(context.getRealPath("")+"submited\\"+submitID+"\\"+inout+".out");
		    printWriter.close();
	}	 
	 
	 public String RunCode(){
		    boolean result=true;
		    String timerun="";
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
			        timerun=line;
			    }
			    while ((line = errinput.readLine()) != null) {
			        System.out.println(line);
			    }
			    if (errinput.readLine() != null) result=false;
			    reader.close();
			 
			} catch (IOException e) {
			    e.printStackTrace();
			}
			return timerun;
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
	 
	
	 
	 public String readSubmitOutput(int id, int num){
			StringBuffer sb = new StringBuffer(); // constructs a string buffer
			// with no characters
			try {
				File file = new File(context.getRealPath("/submited/"+String.valueOf(id)+"/"+String.valueOf(num)+".out")); 
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
