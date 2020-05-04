package ptitcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SubmitSolutionController {
	@RequestMapping("submit")
	public String subbmitCode(){
		// hien thi trang gui bai giai
		return "submit-code";
	}
}
