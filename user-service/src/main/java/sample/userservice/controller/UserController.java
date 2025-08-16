package sample.userservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import sample.userservice.dto.UserDto;
import sample.userservice.jpa.UserEntity;
import sample.userservice.service.UserService;
import sample.userservice.vo.Greeting;
import sample.userservice.vo.RequestUser;
import sample.userservice.vo.ResponseUser;

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

	@GetMapping("/welcome")
	public String welcome(HttpServletRequest request) {
		log.info("users.welcome ip: {}, {}, {}, {}", request.getRemoteAddr()
			, request.getRemoteHost(), request.getRequestURI(), request.getRequestURL());

		//        return env.getProperty("greeting.message");
		return greeting.getMessage();
	}

	@PostMapping("/users")
	public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto userDto = mapper.map(user, UserDto.class);
		userService.createUser(userDto);

		ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
	}


	@GetMapping("/users")
	public ResponseEntity getUsers() {
		Iterable<UserEntity> userList = userService.getUserByAll();

		List<ResponseUser> result = new ArrayList<>();
		userList.forEach(v -> {
			result.add(new ModelMapper().map(v, ResponseUser.class));
		});

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity getUser(@PathVariable("userId") String userId) {
		UserDto userDto = userService.getUserByUserId(userId);
		ResponseUser returnValue = new ModelMapper().map(userDto, ResponseUser.class);

		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}

}
