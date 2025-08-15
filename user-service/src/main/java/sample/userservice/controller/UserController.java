package sample.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import sample.userservice.service.UserService;
import sample.userservice.vo.Greeting;

@RestController
@RequestMapping("/user-service")
@Slf4j
public class UserController {
	private Environment env;

	private Greeting greeting;

	private UserService userService;

	@Autowired
	public UserController(Environment env, Greeting greeting, UserService userService) {
		this.env = env;
		this.greeting = greeting;
		this.userService = userService;
	}

	@GetMapping("/health-check")
	public String status() {
		return String.format("It's Working in User Service"
			+ ", port(local.server.port)=" + env.getProperty("local.server.port")
			+ ", port(server.port)=" + env.getProperty("server.port"));
	}
}
