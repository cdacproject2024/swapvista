package com.swapvista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.swapvista.entity.User;
import com.swapvista.service.UserService;
import com.swapvista.utility.Constants.UserRole;
import com.swapvista.utility.Constants.UserStatus;



@SpringBootApplication
public class SwapvistaApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SwapvistaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User admin = this.userService.getUserByEmailIdAndRoleAndStatus("demo.admin@demo.com",
				UserRole.ROLE_ADMIN.value(), UserStatus.ACTIVE.value());

		if (admin == null) {

			User user = new User();
			user.setEmailId("demo.admin@demo.com");
			user.setPassword(passwordEncoder.encode("123456"));
			user.setRole(UserRole.ROLE_ADMIN.value());
			user.setStatus(UserStatus.ACTIVE.value());

			this.userService.addUser(user);

		}

	}

}
