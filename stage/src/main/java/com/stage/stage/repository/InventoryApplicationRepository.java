package com.stage.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.stage.entity.InventoryApplication;

public interface InventoryApplicationRepository extends JpaRepository<InventoryApplication,Long>{
public List<InventoryApplication>  findByAccountId(Long id);
}
