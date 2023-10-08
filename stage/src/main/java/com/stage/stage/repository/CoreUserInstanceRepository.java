package com.stage.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stage.stage.entity.CoreUserInstance;
import com.stage.stage.entity.UserPermessions;
import com.stage.stage.entity.Users;

public interface CoreUserInstanceRepository extends JpaRepository<CoreUserInstance,Long> {
	public List<CoreUserInstance> findByUsersId(Long id);

 
}