package com.learn.html5;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HtmlController {

	@RequestMapping("/greeting")
	public String greeting(
			@RequestParam(value="firstname",required=true) String firstname,
			@RequestParam(value="lastname",required=true) String lastname,
			@RequestParam(value="id") String securityid,
			@RequestParam(value="position") String position){
		String returns = "Hi, "+ firstname + " " + lastname+ ".";
		if(position!=null){
			returns = returns + " Position : " + position+ ".";
		}
		if(securityid!=null){
			returns = returns + " This is your id: "+ securityid;
		}
		return returns;
	}
	
}
