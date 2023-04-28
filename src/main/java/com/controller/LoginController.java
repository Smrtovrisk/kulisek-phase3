package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.Login;
import com.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String open(Model mm, Login ll) {
		mm.addAttribute("login", ll);
		return "index";
	}
	
	@RequestMapping(value = "/openSignUp",method = RequestMethod.GET)
	public String openSignUpPage(Model mm, Login ll) {
		mm.addAttribute("login", ll);
		return "signUp";
	}
	
	@RequestMapping(value = "/signIn",method = RequestMethod.POST)
	public String signIn(Model mm, Login ll,HttpSession hs) {
		String result = loginService.signIn(ll);
		
		if(result.equals("Customer login successfully")) {
			hs.setAttribute("emailid", ll.getEmailid());		// stored session object of that person 
			return "customerHome";
		}else if(result.equals("Admin login successfully")) {
			return "adminHome";
		}else {
			return "index";
		}
		
	}
	
    @RequestMapping(value = "/viewUsers", method = RequestMethod.GET)
    public String viewUsers(Model mm) {
        List<Login> users = loginService.findAllUsers();
        mm.addAttribute("users", users);
        return "viewUsers";
    }
	
    @RequestMapping(value = "/searchUsers", method = RequestMethod.GET)
    public String searchUsers(Model mm, @RequestParam("searchTerm") String searchTerm) {
        List<Login> users = loginService.searchUsers(searchTerm);
        mm.addAttribute("users", users);
        return "viewUsers";
    }
	
	@RequestMapping(value = "/signUp",method = RequestMethod.POST)
	public String signUp(Model mm, Login ll) {
		String result = loginService.signUp(ll);
		mm.addAttribute("login", ll);
		System.out.println(result);

		return "index";

	}
}