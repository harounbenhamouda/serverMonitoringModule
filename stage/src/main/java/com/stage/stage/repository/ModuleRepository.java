package com.stage.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stage.stage.entity.Modules;

public interface ModuleRepository extends JpaRepository<Modules,Long>{

}
