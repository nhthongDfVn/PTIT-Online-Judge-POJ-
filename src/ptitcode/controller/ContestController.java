package ptitcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContestController {
	@RequestMapping("/add-contest")
	public String addExercise(){
		return "contest/new_contest";
	}
}
