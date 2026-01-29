package com.mrretektor.astrasearch.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrretektor.astrasearch.dao.UserDao;
import com.mrretektor.astrasearch.domain.User;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private final UserDao userDao;
	
	public UserController (final UserDao usersDao) {
		this.userDao = usersDao;
	}
	
	@GetMapping("/users/{username}")
	public Optional<User> getUserByUsername(@PathVariable String username) {
		return userDao.findOne(username);
	}
}
