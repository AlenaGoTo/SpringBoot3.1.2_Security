package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class OneFormController {
	@Autowired
	private UserService userService;
	private User user;

	//страничка со всеми юзерами из БД
	@GetMapping()
	public String printUsers(ModelMap model, @ModelAttribute("user") User user){
		model.addAttribute("users", userService.getAllUsers()); // передача данных в html
		return "index";
	}

	// удаляем юзера на страничке
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable("id") long id){
		userService.removeUserById(id);
		return "redirect:/users";
	}

	// Действие по кнопке добавления юзера
	@PostMapping()
	public String addUser(@RequestParam("firstName") String name,
						  @RequestParam("lastName") String lastname,
						  @RequestParam("age") byte age)
	{
		user = new User(name, lastname, age);
		userService.saveUser(user);
		return "redirect:/users";
	}

	// Действие по кнопке правки юзера на странице edit
	@PatchMapping()
	public String messageCenterHome(@RequestParam("firstName") String name,
									@RequestParam("lastName") String lastname,
									@RequestParam("age") byte age,
									@RequestParam(value = "id") long id)
	{
		userService.updateUser(id, name, lastname, age);
		return "redirect:/users";
	}

}