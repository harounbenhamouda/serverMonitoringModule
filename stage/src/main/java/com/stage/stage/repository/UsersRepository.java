package com.stage.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.stage.entity.CoreAccountModule;
import com.stage.stage.entity.Users;

public interface UsersRepository extends JpaRepository<Users,Long>{
public List<Users> findByAccountId(Long id);
}
