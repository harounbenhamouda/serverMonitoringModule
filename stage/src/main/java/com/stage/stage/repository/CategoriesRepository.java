package com.stage.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.stage.entity.Categories;
import com.stage.stage.entity.CoreAccountModule;

public interface CategoriesRepository  extends JpaRepository<Categories,Long>{
	public List<Categories> findByaccountId(Long id);
}
