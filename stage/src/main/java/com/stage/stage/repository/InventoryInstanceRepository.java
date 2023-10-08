package com.stage.stage.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stage.stage.entity.CategoriesElements;
import com.stage.stage.entity.InventoryInstance;

public interface InventoryInstanceRepository extends JpaRepository<InventoryInstance,Long>{
public List<InventoryInstance>findByAccountId(Long id);

}
