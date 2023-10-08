package com.stage.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.stage.entity.Credentials;

public interface CredentialsRepository extends JpaRepository<Credentials,Long> {
	public List<Credentials> findByAccountId(Long id);

}
