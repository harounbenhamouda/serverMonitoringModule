package com.stage.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.stage.entity.CategoriesElements;

public interface CategoriesElementsRepository  extends JpaRepository<CategoriesElements,Long>{
public List<CategoriesElements>findByCategoriesId(Long id);
}
