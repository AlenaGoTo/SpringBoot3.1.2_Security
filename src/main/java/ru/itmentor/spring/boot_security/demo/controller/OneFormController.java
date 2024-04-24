package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping(value = "/users_s")
public class OneFormController {
	@Autowired
	private UserService userService;
	private User user;

	//страничка со всеми юзерами из БД
	@GetMapping()
	public String printUsers(ModelMap model, @ModelAttribute("user") User user){
		model.addAttribute("users", userService.getAllUsers()); // передача данных в html
		return "admin1";
	}

	// удаляем юзера на страничке
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable("id") long id){
		userService.removeUserById(id);
		return "redirect:/users_s";
	}

	// Действие по кнопке добавления юзера
	@PostMapping()
	public String addUser(@RequestParam("firstName") String name,
						  @RequestParam("lastName") String lastname,
						  @RequestParam("age") byte age,
						  @RequestParam("lastName") String username,
						  @RequestParam("lastName") String password)
	{
		user = new User(name, lastname, age, username, password);
		userService.saveUser(user);
		return "redirect:/users_s";
	}

	// Действие по кнопке правки юзера на странице edit
	@PatchMapping()
	public String messageCenterHome(@RequestParam("firstName") String name,
									@RequestParam("lastName") String lastname,
									@RequestParam("age") byte age,
									@RequestParam(value = "id") long id)
	{
		userService.updateUser(id, name, lastname, age);
		return "redirect:/users_s";
	}

}