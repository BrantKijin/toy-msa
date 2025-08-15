package sample.userservice.service;

public interface UserService {
	UserDto createUser(UserDto userDto);
	UserDto getUserByUserId(String userId);
	Iterable<UserEntity> getUserByAll();
}
