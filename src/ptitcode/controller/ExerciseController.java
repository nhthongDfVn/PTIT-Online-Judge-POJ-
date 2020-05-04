package ptitcode.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExerciseController {
	@RequestMapping("/exercise")
	public String showExercise1(){	
		// goi den file exercise
		return "exercise";
	}
	
	@RequestMapping("/exercise/Details")
	public String showDetails(){	
		// goi den file chi tiet bai tap
		return "exercise/details";
	}
}
