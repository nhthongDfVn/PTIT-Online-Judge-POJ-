package ptitcode.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptitcode.entity.Exercise;

@Transactional
@Controller
public class SubmitSolutionController {

	@Autowired
	SessionFactory factory;
	
	@Autowired
	ServletContext context;
	
	
	@RequestMapping(value = "/submit/{id}")
	public String update(ModelMap model, @PathVariable("id") int id) {
		Session session = factory.getCurrentSession();
		Exercise exercise = (Exercise) session.get(Exercise.class,id);
		//String command = "dir";
		model.addAttribute("exercise", exercise);
		//testPing();
		return "submit-code";	
	}
	
	
	
	@RequestMapping("comile")
	public void show(){
		testPing();
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
	
	
	
}
