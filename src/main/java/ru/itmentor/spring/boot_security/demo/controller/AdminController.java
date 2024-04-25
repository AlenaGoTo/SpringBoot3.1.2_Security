package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	private UserService userService;
	private User user;

	//страничка со всеми юзерами из БД
	@GetMapping()
	public String printUsers(ModelMap model){
		model.addAttribute("users", userService.getAllUsers()); // передача данных в html
		return "userCRUD";
	}

	//страница для правки юзера
	@GetMapping(value = "/{id}")
	public String getUser(@PathVariable("id") long id, ModelMap model){
		model.addAttribute("user", userService.getUserById(id)); // передача данных в html
		return "edit";
	}

	// удаляем юзера на страничке show
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable("id") long id){
		userService.removeUserById(id);
		return "redirect:/admin";
	}

	// Действие по кнопке правки юзера на странице edit
	@PatchMapping("/{id}")
	public String editUser(@RequestParam("firstName") String name,
						   @RequestParam("lastName") String lastname,
						   @RequestParam("age") byte age,
						   @PathVariable("id") long id )
	{
		userService.updateUser(id, name, lastname, age);
		return "redirect:/admin";
	}

	//страничка с добавлением юзера
	@GetMapping(value = "/new")
	public String newUser(@ModelAttribute("user") User user) {
		return "new";
	}

	// Действие по кнопке добавления юзера на страничке new
	@PostMapping()
	public String addUser(@RequestParam("firstName") String name,
						 @RequestParam("lastName") String lastname,
						 @RequestParam("age") byte age,
						  @RequestParam("lastName") String username,
						  @RequestParam("lastName") String password)
	{
		user = new User(name, lastname, age, username, password);
		userService.saveUser(user);
		return "redirect:/admin";
	}
	
}