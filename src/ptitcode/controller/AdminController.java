package ptitcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	@RequestMapping("/add-exercise")
	public String addExercise(){
		return "admin/new_exercise";
	}
}