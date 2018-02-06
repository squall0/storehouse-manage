package com.webone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webone.domain.Storeroom;

public interface StoJpaRepository extends JpaRepository<Storeroom, Integer> {
	List<Storeroom> findByname(String name);

	// List<Storeroom> findBynameAndteamId(String name,int teamId);
	List<Storeroom> findByteamId(int teamId);
}
