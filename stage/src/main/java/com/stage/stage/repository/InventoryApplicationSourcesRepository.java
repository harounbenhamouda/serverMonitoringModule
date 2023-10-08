package com.stage.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.stage.entity.InventoryApplicationSources;

public interface InventoryApplicationSourcesRepository extends JpaRepository<InventoryApplicationSources,Long>{
public List<InventoryApplicationSources> findByApplicationId(Long id);
}
