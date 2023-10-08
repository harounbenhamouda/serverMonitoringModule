package com.stage.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.stage.stage.entity.InventoryServerVersion;
@Transactional
public interface InventoryServerVersonRepository  extends JpaRepository<InventoryServerVersion,Long>
{
public List<InventoryServerVersion>findByServerId(Long id);
@Modifying
@Query(value="UPDATE `inventory_servers_versions` SET status='1'  ,end_date=CURRENT_TIMESTAMP WHERE server_id=?1 and element=?2 and status=2",nativeQuery=true)
public void desctivatOldversion(Long id,int element);

}
