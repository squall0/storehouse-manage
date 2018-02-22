package com.webone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webone.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {

}
