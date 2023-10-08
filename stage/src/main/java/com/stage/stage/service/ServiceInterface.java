package com.stage.stage.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.stage.stage.entity.Core_Accounts;

public interface ServiceInterface {
	public Core_Accounts createAccount(Core_Accounts coreaccount);
public List<Core_Accounts> getAllAccounts();
public <Optional> Core_Accounts findAccountByid(Long id) ;
public void deleteAccount(Long id);
public Core_Accounts updateAccount(Core_Accounts coreaccount,Long id);
public List<Map<String,Object>> getActivatedUseInstances(Long user_id,Long account_id);
}
