package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.EntityService;
import ru.itmentor.spring.boot_security.demo.service.impl.RoleService;
import ru.itmentor.spring.boot_security.demo.service.impl.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	private User user;

	//страничка со всеми юзерами из БД
	@GetMapping()
	public String printUsers(ModelMap model){
		model.addAttribute("users", userService.getAll());
		return "userCRUD";
	}

	//страница для правки юзера
	@GetMapping(value = "/{id}")
	public String getUser(@PathVariable("id") long id, ModelMap model){
		model.addAttribute("user", userService.getById(id));
		return "edit";
	}

	// удаляем юзера на страничке show
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable("id") long id){
		userService.deleteById(id);
		return "redirect:/admin";
	}

	// Действие по кнопке правки юзера на странице edit
	@PatchMapping("/{id}")
	public String editUser(@RequestParam("firstName") String name,
						   @RequestParam("lastName") String lastname,
						   @RequestParam("age") byte age,
						   @PathVariable("id") long id )
	{
		User user = userService.getById(id).orElseThrow(() -> new RuntimeException("User not found"));
		user.setFirstName(name);
		user.setLastName(lastname);
		user.setAge(age);
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