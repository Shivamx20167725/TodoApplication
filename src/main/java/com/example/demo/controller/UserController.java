package com.example.demo.controller;


import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.TodoRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entities.Todo;
import com.example.demo.entities.User;




@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TodoRepository todoRepository;
	
	// method for adding common data to response
		@ModelAttribute
		public void addCommonData(Model model, Principal principal) {
			String userName = principal.getName();
			System.out.println("USERNAME " + userName);

			// get the user using usernamne(Email)

			User user = userRepository.getUserByUserName(userName);
			System.out.println("USER " + user);
			model.addAttribute("user", user);

		}
	
		// dashboard home
		//@RequestMapping("/index")
		public String dashboard(Model model, Principal principal) {
			model.addAttribute("title", "User Dashboard");
			return "normal/user_dashboard";
		}
		
		// open add form handler
		/*
		 * @GetMapping("/add-todo") public String openAddTodoForm(Model model) {
		 * model.addAttribute("title", "Add Todo"); model.addAttribute("todos", new
		 * Todo());
		 * 
		 * return "normal/user_dashboard"; }
		 */
		
		@GetMapping("/index")
		public String addTodoForm(@ModelAttribute Todo todo,Model m, Principal principal) {
			//model.addAttribute("title", "Add Todo"); 
			//model.addAttribute("todos", new Todo());
			
			String name = principal.getName();
			
			User user = this.userRepository.getUserByUserName(name);
			
			todo.setUser(user);
			
			user.getTodo().add(todo);
			
			this.userRepository.save(user);
			
			System.out.println("Todo data: " + todo.toString());
			
			System.out.println("Todo data: added ");
			
			 
			String name1 = principal.getName();
			
			User user1 = this.userRepository.getUserByUserName(name1);
			this.todoRepository.save(todo);
			List<Todo> todos = this.todoRepository.findTodosByUser(user1.getId());
			
			System.out.println("sizeeeee "+ todos.size());
			
			m.addAttribute("todos",todos);
			
			return "normal/user_dashboard";
		}
		
		@GetMapping("/delete/{id}")
		public String deleteTodo(@PathVariable("id") Integer id, Model model, HttpSession session,
				Principal principal) {
			System.out.println("TID " + id);

			Todo todo = this.todoRepository.findById(id).get();
			

			//User user = this.userRepository.getUserByUserName(principal.getName());

			todo.setUser(null);

			this.todoRepository.delete(todo);

			System.out.println("DELETED");

			return "normal/user_dashboard";
		}
	
	
}
