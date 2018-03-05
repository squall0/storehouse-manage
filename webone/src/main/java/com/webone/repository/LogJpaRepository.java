package com.webone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webone.domain.Log;

public interface LogJpaRepository extends JpaRepository<Log, Integer>{

}
