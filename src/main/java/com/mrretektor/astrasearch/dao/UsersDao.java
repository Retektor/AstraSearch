package com.mrretektor.astrasearch.dao;

import java.util.Optional;

import com.mrretektor.astrasearch.domain.Users;

public interface UsersDao {
	public void create(Users user);
	public Optional<Users> findOne(String username);
}
