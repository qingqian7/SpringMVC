package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping("/toindex")
	public String hello() {
		System.out.println("debug3..............");
		return "index";
	}
}
