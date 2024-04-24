package ru.itmentor.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootSecurityDemoApplication.class, args);

		UserService userService = context.getBean(UserService.class);

		Role role1 = new Role("ROLE_ADMIN");
		Role role2 = new Role("ROLE_USER");
		Role role3 = new Role("ROLE_USER");
		Role role4 = new Role("ROLE_ADMIN");
		List<Role> list = new ArrayList<>();
		list.add(role1);
		list.add(role2);
		// https://stackoverflow.com/questions/2302802/how-to-fix-the-hibernate-object-references-an-unsaved-transient-instance-save#:~:text=%D0%A0%D0%B5%D1%88%D0%B5%D0%BD%D0%B8%D0%B5%D0%BC%20%D0%B1%D1%8B%D0%BB%D0%BE%20%D1%81%D0%BE%D1%85%D1%80%D0%B0%D0%BD%D0%B5%D0%BD%D0%B8%D0%B5%20%D0%B4%D0%BE%D1%87%D0%B5%D1%80%D0%BD%D0%B5%D0%B3%D0%BE%20%D0%BE%D0%B1%D1%8A%D0%B5%D0%BA%D1%82%D0%B0%20%D0%B8%20%D0%B4%D0%BE%D0%B1%D0%B0%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5%20%D1%81%D0%BE%D1%85%D1%80%D0%B0%D0%BD%D0%B5%D0%BD%D0%BD%D0%BE%D0%B3%D0%BE%20%D0%B4%D0%BE%D1%87%D0%B5%D1%80%D0%BD%D0%B5%D0%B3%D0%BE%20%D0%BE%D0%B1%D1%8A%D0%B5%D0%BA%D1%82%D0%B0%20%D0%BA%20%D1%80%D0%BE%D0%B4%D0%B8%D1%82%D0%B5%D0%BB%D1%8C%D1%81%D0%BA%D0%BE%D0%BC%D1%83%20%D0%BE%D0%B1%D1%8A%D0%B5%D0%BA%D1%82%D1%83%2C%20%D0%BD%D0%B0%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80%3A
		List<Role> listuser = new ArrayList<>();
		listuser.add(role3);
		List<Role> listadmin = new ArrayList<>();
		listadmin.add(role4);

		User two = new User("User1", "Lastname1", (byte) 22, "two", "two");
		two.setRoles(list);
		User user = new User("User", "User", (byte) 22, "user", "user");
		user.setRoles(listuser);
		User admin = new User("admin", "admin", (byte) 22, "admin", "admin");
		admin.setRoles(listadmin);

		userService.saveUser(two);
		userService.saveUser(user);
		userService.saveUser(admin);

	}

}
