package com.stage.stage.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stage.stage.Dto.ServerInstanceDto;
import com.stage.stage.entity.Categories;
import com.stage.stage.entity.InventoryInstance;
import com.stage.stage.entity.InventoryServers;

public interface InventoryServersRepository  extends JpaRepository<InventoryServers,Long>{
	public InventoryServers findByInstancesId(Long id);
	@Query(value="SELECT c.id  as accountid, a.name ,a.status, b.*   FROM inventory_servers b join inventory_instance a  on a.id=b.instance_id\r\n"
			+ "join core_accounts c on c.id=a.account_id where c.id=?1",nativeQuery=true)
	
public	 List<Map<String,Object>> findserversbyAccountid(Long id);
	@Query(value="SELECT a.id as ins_id,a.creation_type,a.favorite,a.name ,a.start_date, a.status,a.account_id ,b.* FROM `inventory_servers`b join inventory_instance a on a.id=b.instance_id where a.id=?1",nativeQuery=true)
	public List<Map<String,Object>> findserverByInstanceIdd(Long id);
}
