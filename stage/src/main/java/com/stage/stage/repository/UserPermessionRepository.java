package com.stage.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.stage.entity.CoreAccountModule;
import com.stage.stage.entity.UserPermessions;
import com.stage.stage.entity.Users;

public interface UserPermessionRepository extends JpaRepository<UserPermessions,Long>{
	public List<UserPermessions> findByUsersId(Long id);
}
