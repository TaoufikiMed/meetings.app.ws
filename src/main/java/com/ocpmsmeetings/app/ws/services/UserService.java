package com.ocpmsmeetings.app.ws.services;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ocpmsmeetings.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);
	List<UserDto> getUsers(int page, int limit);
	UserDto createUser(UserDto user);
	UserDto updateUser(String userId,UserDto userDto);
	void deleteUser(String userId);

}
