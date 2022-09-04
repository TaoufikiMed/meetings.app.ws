package com.ocpmsmeetings.app.ws.services.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;	
import org.springframework.stereotype.Service;

import com.ocpmsmeetings.app.ws.entities.UserEntity;
import com.ocpmsmeetings.app.ws.repositories.UserRepository;
import com.ocpmsmeetings.app.ws.services.UserService;
import com.ocpmsmeetings.app.ws.shared.Utils;
import com.ocpmsmeetings.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Utils util;

	@Override
	public UserDto createUser(UserDto user) {
		UserEntity checkUser = userRepository.findByEmail(user.getEmail());

		if (checkUser != null)
			throw new RuntimeException("User Alrady Exists !");

		ModelMapper modelMapper = new ModelMapper();

		UserEntity userEntity = modelMapper.map(user, UserEntity.class);

		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		userEntity.setUserId(util.generateStringId(32));

		UserEntity newUser = userRepository.save(userEntity);

		UserDto userDto = modelMapper.map(newUser, UserDto.class);

		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if (userEntity == null) throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email); 
		
		ModelMapper modelMapper = new ModelMapper();

		UserDto userDto =  modelMapper.map(userEntity, UserDto.class);
		
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) throw new UsernameNotFoundException(userId); 
		
		ModelMapper modelMapper = new ModelMapper();

		UserDto userDto =  modelMapper.map(userEntity, UserDto.class);
		
		return userDto;
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		if(page > 0) page = page - 1;
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<UserEntity> userPage=userRepository.findAllUsers(pageableRequest);
		List<UserEntity> users = userPage.getContent();
		Type listType = new TypeToken<List<UserDto>>() {}.getType();
		List<UserDto> usersDto =new ModelMapper().map(users, listType);
		return usersDto;

	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null) throw new UsernameNotFoundException(userId);
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		UserEntity user = userRepository.save(userEntity);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDtoUpdated =  modelMapper.map(user, UserDto.class);
		return userDtoUpdated;
	}

	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null) throw new UsernameNotFoundException(userId);
		userRepository.delete(userEntity);
	}


}
