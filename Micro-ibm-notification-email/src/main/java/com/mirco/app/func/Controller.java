package com.mirco.app.func;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@Autowired
	private EmailService emailService;
	
    @PostMapping(value = "/simple-email")
	public String simple(@RequestBody EmailForm form) throws Exception {
    		if (form != null && !this.check(form.getSendTo())) {
			return String.format("%s, E-mail address is not currect.", form.getSendTo());
		}
    	
    		this.emailService.sendSimpleMail(form.getSendTo(), form.getSubject(), form.getContent());
		return "Done";
	}
    
    @PostMapping(value = "/html-email")
   	public String html(@RequestBody EmailForm form) throws Exception {
    		if (form != null && !this.check(form.getSendTo())) {
			return String.format("%s, E-mail address is not currect.", form.getSendTo());
		}
    	
    		this.emailService.sendHtmlMail(form.getSendTo(), form.getSubject(), form.getContent());
   		return "Done";
   	}
    
    private boolean check(String email) {
		String regex = "^(.*)@(.*)ibm.com$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher((CharSequence) email);
	    return matcher.matches();
	}
}
