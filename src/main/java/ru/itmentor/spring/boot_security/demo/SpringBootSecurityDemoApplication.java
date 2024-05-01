package ru.itmentor.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.impl.RoleService;
import ru.itmentor.spring.boot_security.demo.service.impl.UserService;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootSecurityDemoApplication.class, args);

		UserService userService = context.getBean(UserService.class);
		RoleService roleService = context.getBean(RoleService.class);

		//Role role1 = new Role("ROLE_ADMIN");
		//Role role2 = new Role("ROLE_USER");

		User two = new User("User1", "Lastname1", (byte) 22, "two", "two");
		//User user = new User("User", "User", (byte) 22, "user", "user");
		//User admin = new User("admin", "admin", (byte) 22, "admin", "admin");

		two.addRole(roleService.getByParam("ROLE_ADMIN").orElseThrow());
		two.addRole(roleService.getByParam("ROLE_USER").orElseThrow());
		//user.addRole(role2);
		//admin.addRole(role1);

		userService.save(two);
		//userService.save(user);
		//userService.save(admin);

	}

}
