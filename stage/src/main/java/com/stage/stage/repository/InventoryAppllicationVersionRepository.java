package com.stage.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.stage.stage.entity.InventoryApplicationVersion;
@Transactional
public interface InventoryAppllicationVersionRepository extends JpaRepository<InventoryApplicationVersion,Long>{
public List<InventoryApplicationVersion>findByApplicationId(Long id);
@Modifying
@Query(value="UPDATE `inventory_applications_version` SET status='1' ,end_date=CURRENT_TIMESTAMP WHERE application_id=?1 and status=2 ",nativeQuery=true)
public void desctivatOldversion(Long id);
}
