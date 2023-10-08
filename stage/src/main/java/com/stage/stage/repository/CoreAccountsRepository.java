package com.stage.stage.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stage.stage.entity.Core_Accounts;

public interface CoreAccountsRepository  extends JpaRepository<Core_Accounts,Long>{
@Query(value="SELECT ini.id as userinstanceid, ini.status as userinstatus ,ui.name,ui.id,ini.status ,(SELECT COUNT(1) from core_users_instances uin \r\n"
		+ "      where uin.user_id=ini.user_id AND  uin.instance_id=ini.instance_id AND uin.status = 3) As hasmodule\r\n"
		+ "from core_accounts ca left join inventory_instance ui ON ui.account_id=ca.id\r\n"
		+ "		 left join core_users_instances ini ON ini.instance_id=ui.id and ini.user_id=?1\r\n"
		+ "		where ca.id=?2\r\n"
		+ "	Group By ui.id",nativeQuery=true)
public List<Map<String,Object>> showActivatedInstances(Long user_id,Long account_id);
}
