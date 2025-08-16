package sample.userservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import sample.userservice.dto.UserDto;
import sample.userservice.jpa.UserEntity;

public interface UserService extends UserDetailsService {
	UserDto createUser(UserDto userDto);
	UserDto getUserByUserId(String userId);
	Iterable<UserEntity> getUserByAll();

	UserDto getUserDetailsByEmail(String userName);
}
