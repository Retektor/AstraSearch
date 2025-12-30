package com.mrretektor.astrasearch.dao;

import java.util.Optional;

import com.mrretektor.astrasearch.domain.User;

public interface UserDao {
	public void create(User user);
	public Optional<User> findOne(String username);
}
