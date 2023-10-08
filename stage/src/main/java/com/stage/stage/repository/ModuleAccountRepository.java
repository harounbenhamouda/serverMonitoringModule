package com.stage.stage.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stage.stage.entity.CoreAccountModule;
@Repository
public interface ModuleAccountRepository extends JpaRepository<CoreAccountModule,Long>{
public List<CoreAccountModule> findByaccountId(Long id);
@Query(value="SELECT a.`module_idd`,a.id as kk, b.name, c.status,c.user_id, c.id, (SELECT COUNT(1) from users_module um where um.user_id=c.user_id AND  um.module_id=c.module_id AND um.status = 3) As hasmodule\r\n"
		+ "\r\n"
		+ "FROM core_account_module a\r\n"
		+ "\r\n"
		+ "LEFT JOIN core_modules b ON b.id = a.module_idd\r\n"
		+ "\r\n"
		+ "LEFT JOIN users_module c ON c.module_id = a.id  and c.user_id=?1\r\n"
		+ " \r\n"
		+ "\r\n"
		+ "WHERE a.`account_id` =?2 GROUP by a.module_idd DESC",
nativeQuery=true)
public List<Map<String,Object>> showActivatedModules(Long user_id,Long account_id);
}
