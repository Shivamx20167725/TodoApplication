package com.example.demo.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.User;
import com.example.demo.helper.Message;

@Controller
public class HomeController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@RequestMapping("/")
	public String home1(Model model) {
		model.addAttribute("title", "This is dyanamic value");
		return "home";
	}

	
	  @RequestMapping("/home") 
	  public String home(Model model) {
		  model.addAttribute("title","This is dyanamic value"); return "home"; 
	  }
	 
	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "This is about");
		return "about";
	}

	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";

	}

	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			 Model model, HttpSession session) {
		try {

			
			if (bindingResult.hasErrors()) {
				System.out.println("bindingResult.hasErrors()");
				model.addAttribute("user", user);
				return "signup";
			}

			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			User result = this.userRepository.save(user);

			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("Registration completed", "alert-success"));

			return "signup";

		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something went wrong!!" + ex.getMessage(), "alert-danger"));

			return "signup";

		}
	}

	// handler for custom login
	@RequestMapping("/signin")
	public String signin(Model model) {
		model.addAttribute("title", "This is dyanamic value");
		return "login";
	}
}
