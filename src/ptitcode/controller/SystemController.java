package ptitcode.controller;

import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.stereotype.Controller;



@Controller
public class SystemController {
	
	@RequestMapping("404")
	public String show404(){
		return "404";
	}
}
