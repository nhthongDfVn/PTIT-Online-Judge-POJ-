package ptitcode.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
	
	
	@RequestMapping(value = "/submit/{id}")
	public String update(ModelMap model, @PathVariable("id") int id) {
		Session session = factory.getCurrentSession();
		Exercise exercise = (Exercise) session.get(Exercise.class,id);
		String command = "dir";
		model.addAttribute("exercise", exercise);
		testPing();
		return "submit-code";	
	}
	
	public void testPing(){
		String commandArray[] = {"cmd", "/c", "py", "test_api.py"}; 
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
