package com.webone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webone.domain.User;

public interface UserJpaRepository extends JpaRepository<User, Integer> {
	List<User> findByusername(String name);
	List<User> findBytoken(String token);
	List<User> findBypassword(String password);
}
