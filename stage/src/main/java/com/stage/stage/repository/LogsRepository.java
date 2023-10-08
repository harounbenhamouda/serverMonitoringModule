package com.stage.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.stage.entity.Logs;
import com.stage.stage.entity.UserSecurity;

public interface LogsRepository  extends JpaRepository<Logs,Long>{
	public List<Logs> findByUsersId(Long id);
}
