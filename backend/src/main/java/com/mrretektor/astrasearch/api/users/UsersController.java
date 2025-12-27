package com.mrretektor.astrasearch.api.users;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrretektor.astrasearch.dao.UsersDao;
import com.mrretektor.astrasearch.domain.Users;

@RestController
@RequestMapping("/api")
public class UsersController {
	
	private final UsersDao usersDao;
	
	public UsersController (final UsersDao usersDao) {
		this.usersDao = usersDao;
	}
	
	@GetMapping("/users/{username}")
	public Optional<Users> getUserByUsername(@PathVariable String username) {
		return usersDao.findOne(username);
	}
}
