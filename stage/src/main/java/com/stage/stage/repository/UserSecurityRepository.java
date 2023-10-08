package com.stage.stage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stage.stage.entity.CoreUserInstance;
import com.stage.stage.entity.UserSecurity;
@Repository
public interface UserSecurityRepository extends JpaRepository<UserSecurity,Long> {
	@Query(value="select * from user_security where user_security.user_id=?1 and status=1",nativeQuery=true)
	public UserSecurity findByUsersId(Long id);
}
