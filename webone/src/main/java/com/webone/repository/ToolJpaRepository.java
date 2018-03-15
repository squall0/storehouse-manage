package com.webone.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.webone.domain.Tool;

public interface ToolJpaRepository extends JpaRepository<Tool, Integer> {
	List<Tool> findByuserId(int id);

	List<Tool> findByStoreroomId(int roomid, Sort sort);

	List<Tool> findByStoreroomIdAndUserId(int storeroomId, int userId, Sort sort);

	List<Tool> findByNameLikeAndSize1LikeAndStoreroomIdIn(String name, String size1, List<Integer> storeroomId,
			Sort sort);

}
