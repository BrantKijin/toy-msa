package sample.userservice.service;

import sample.userservice.dto.UserDto;
import sample.userservice.jpa.UserEntity;

public interface UserService {
	UserDto createUser(UserDto userDto);
	UserDto getUserByUserId(String userId);
	Iterable<UserEntity> getUserByAll();
}
