package com.webone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webone.domain.Tool;

public interface ToolJpaRepository extends JpaRepository<Tool, Integer> {
	List<Tool> findByuserId(int id);

	List<Tool> findBystoreroomId(int id);

	List<Tool> findByStoreroomIdAndUserId(int storeroomId, int userId);
}
