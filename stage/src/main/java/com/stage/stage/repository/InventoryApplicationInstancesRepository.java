package com.stage.stage.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stage.stage.entity.InventoryApplicationInstances;

public interface InventoryApplicationInstancesRepository extends JpaRepository<InventoryApplicationInstances,Long>{
public List<InventoryApplicationInstances> findByApplicationId(Long id);
@Query(value="SELECT s.name,  i.* FROM `inventory_application_instances` i  join inventory_instance s on s.id=i.server_instance_id where i.application_id=?1",nativeQuery=true)
public List<Map<String,Object>>getInstanceByapplicationId(Long id);
@Query(value="SELECT app.name,  i.* FROM `inventory_application_instances` i  join inventory_application app on app.id=i.application_id where i.server_instance_id=?1",nativeQuery=true)
public List<Map<String,Object>>getApplicationByintsanceId(Long id);
}
