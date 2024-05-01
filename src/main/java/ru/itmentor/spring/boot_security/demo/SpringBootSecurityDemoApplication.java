package ru.itmentor.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootSecurityDemoApplication.class, args);

		UserService userService = context.getBean(UserService.class);

		Role role1 = new Role("ROLE_ADMIN");
		Role role2 = new Role("ROLE_USER");

		User two = new User("User1", "Lastname1", (byte) 22, "two", "two");
		User user = new User("User", "User", (byte) 22, "user", "user");
		User admin = new User("admin", "admin", (byte) 22, "admin", "admin");

		two.addRole(role1);
		two.addRole(role2);
		user.addRole(role2);
		admin.addRole(role1);

		System.out.println("OK");
		userService.saveUser(two);
		System.out.println("OK");
		userService.saveUser(user);
		userService.saveUser(admin);
		userService.getUserByParam("two");
		System.out.println("OK");
		userService.getUserByParam("two123");
		System.out.println("OK");

	}

}
