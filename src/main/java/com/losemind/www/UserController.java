package com.losemind.www;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.losemind.www.model.User;
import com.losemind.www.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/users")
public class UserController {

	/*
	 * I know that I should define separate Controllers for each function but 
	 * I didn't do this because this task even without this drank a lot of my blood :)
	*/
	
	private UserService userService;
	
	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService service) {
		this.userService = service;		
	}

	//Controller for list of users	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String users(@RequestParam(value = "search", required = false) String criteria,
			@RequestParam(value = "page", required = false) Integer pageNumber, Model model) {
		if (pageNumber == null) {
			pageNumber = 1;
		}

		List<User> listUsers = this.userService.listUsers(pageNumber, UserService.ENTITIES_PER_PAGE, criteria);

		int start = 3;
		if (pageNumber > start) {
			start = pageNumber;
		}
		List<Integer> listOfPages = new ArrayList<Integer>();
		for (int i = start - 2; i < start + 3 && i <= this.userService.getNumberOfPages(); i++) {
			listOfPages.add(i);
		}

		model.addAttribute("criteria", criteria);

		model.addAttribute("pageNum", pageNumber);
		model.addAttribute("lastPageNum", this.userService.getNumberOfPages());
		model.addAttribute("listOfPages", listOfPages);

		model.addAttribute("user", new User());
		model.addAttribute("listUsers", listUsers);

		return "users";
	}
	
	//Controller for add new user
	@RequestMapping(value = "/addUser")
	public String goToUserAddForm(@ModelAttribute User user, Model model) {
		model.addAttribute("user", user);
		return "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@Valid User user, BindingResult bindingResult, Model model) {		
		
		if (bindingResult.hasErrors()) {			
			return "add";
		}
		
		this.userService.addUser(user);
		return "redirect:/users?page=" + this.userService.getNumberOfPages();
	}
	
	//for remove
	@RequestMapping(value = "/removeUser/{id}")
	public String removeUser(@PathVariable("id") int id) {
		this.userService.removeUser(this.userService.getById(id));
		return "redirect:/users";
	}
	
	//for edit
	@RequestMapping(value = "/editUser/{id}")
	public String goToUserEditForm(@PathVariable("id") int id, Model model) {
		User user = this.userService.getById(id);
		model.addAttribute("user", user);
		return "edit";
	}
	
	@RequestMapping(value = "/editUser/edit", method = RequestMethod.POST)
	public String editUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "edit";
		}		
		
		this.userService.updateUser(user);
		return "redirect:/users";
	}

	//for search. Works only for fields "name" and "age" and only for strict matches
	@RequestMapping(value = "/searchUser", method = RequestMethod.POST)
	public String search(@RequestParam(value = "criteria", required = false) String criteria, Model model) {
		System.out.println("Searching by criteria: " + criteria);
		model.addAttribute("search", criteria);
		return "redirect:/users";
	}

}
