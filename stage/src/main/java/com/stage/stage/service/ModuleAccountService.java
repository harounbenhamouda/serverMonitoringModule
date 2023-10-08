package com.stage.stage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.CoreAccountModule;
import com.stage.stage.entity.Core_Accounts;
import com.stage.stage.entity.Modules;
import com.stage.stage.repository.CoreAccountsRepository;
import com.stage.stage.repository.ModuleAccountRepository;
import com.stage.stage.repository.ModuleRepository;
import com.stage.stage.validators.Validators;
@Service
public class ModuleAccountService {
private ModuleAccountRepository moduleaccountrepository;
private CoreAccountsRepository car;
private ModuleRepository modulerepo;
@Autowired
public ModuleAccountService(ModuleAccountRepository moduleaccountrepository,CoreAccountsRepository car,ModuleRepository modulerepo) {
	super();
	this.moduleaccountrepository = moduleaccountrepository;
	this.car = car;
	this.modulerepo=modulerepo;
}
public CoreAccountModule createModule(CoreAccountModule coreaccountmodule) {
	
	Long accountid= coreaccountmodule.getAccount().getId();
	Long modulid=coreaccountmodule.getModules().getId();

	
	if(coreaccountmodule.getAccount()==null) {
		throw new CustomException("you should provide at least an account id ");
	}
	else if(accountid==null) {
		throw new CustomException("verify your account iD");
	}
	else if(modulid==null) {
		throw new CustomException("verify your modi=ul iD");
	}
	else if((coreaccountmodule.getAccount().getId()<=0) ) {
		throw new CustomException("please verify account id");
	}
	else if((coreaccountmodule.getModules()==null)) {
		throw new CustomException("you should provide at least module id");
	}
	else if((coreaccountmodule.getModules().getId()<=0)) {
		throw new CustomException("please verify account id");
	}
	
	try {car.findById(accountid);
	modulerepo.findById(modulid);
	return moduleaccountrepository.save(coreaccountmodule);
	}catch(Exception e){
			
			if(e.getMessage() == "could not execute statement") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			
			 throw new CustomException("verify your fields"); }
	
	
	
	
	
}
public void deleteAccount(Long id) {
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}
	else {
		try{

			 moduleaccountrepository.deleteById(id);
			
		}catch(Exception e){
		
		if(e.getMessage() == "No class") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("resource doesn t not exist"); 
	}
	
}
	
}
public List<CoreAccountModule> getModulebyAccountId(Long id){
	List<CoreAccountModule> module;
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}

		module=moduleaccountrepository.findByaccountId(id);
		if(module==null) {
			throw new CustomException("no resource found");
		}
	
	
	return module;
}
public CoreAccountModule getMouduleById(Long id)
{
	CoreAccountModule moduleaccount;
	 moduleaccount=moduleaccountrepository.findById(id).get();
	
	return moduleaccount;}
public CoreAccountModule updateModule(CoreAccountModule accountmodule,Long id) {
	CoreAccountModule accountmodule1;
	
	if(id <=0 ) {
		throw new CustomException("invalid id");
	}	
	else {
		try{

			accountmodule1 =moduleaccountrepository.findById(id).get();
			if(Validators.MenichString(accountmodule.getStatus())==false) {
				throw new CustomException("not a valid parametre");
			}
			 
			
			
			if(accountmodule.getStart_date()==null) {
				accountmodule.setStart_date(accountmodule1.getStart_date());
			}
			return moduleaccountrepository.saveAndFlush(accountmodule);
		}catch(Exception e){
		
		if(e.getMessage() == "No value present") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		
		 throw new CustomException("verify your fields"); 
	}
	
	}
	
	}





public List<Map<String,Object>> getAccountUserModules(Long user_id,Long account_id){
	return moduleaccountrepository.showActivatedModules(user_id,account_id);
}
	
}





