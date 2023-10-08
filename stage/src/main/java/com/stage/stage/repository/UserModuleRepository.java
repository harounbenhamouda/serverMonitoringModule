package com.stage.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stage.stage.entity.CoreAccountModule;
import com.stage.stage.entity.UsersModule;
@Repository
public interface UserModuleRepository extends JpaRepository<UsersModule,Long>{
	public List<UsersModule> findByUsersId(Long id);
	
}
