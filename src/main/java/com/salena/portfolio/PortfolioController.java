package com.salena.portfolio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PortfolioController {
	
	@GetMapping("/")
	public String viewHome() {
		return "home";
	}
	
	@GetMapping("/about")
	public String viewAbout(){
		return "about";
	}
	
	@GetMapping("/resume")
	public String viewResume(){
		return "resume";
	}
	
	@GetMapping("/portfolio")
	public String viewPortfolio(){
		return "portfolio";
	}

	@GetMapping("/contact")
	public String viewContact() {
		return "contact";
	}
	 @GetMapping("/index.html")
	    public String index() {
	        return "index.html";
	    }
}
