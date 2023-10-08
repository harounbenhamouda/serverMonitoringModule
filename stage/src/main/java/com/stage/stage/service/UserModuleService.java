package com.stage.stage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.CoreUserInstance;
import com.stage.stage.entity.Core_Accounts;
import com.stage.stage.entity.UserPermessions;
import com.stage.stage.entity.UsersModule;
import com.stage.stage.repository.ModuleAccountRepository;
import com.stage.stage.repository.UserModuleRepository;
import com.stage.stage.repository.UsersRepository;

@Service
public class UserModuleService {
	private UserModuleRepository usermodulerepository;
	private UsersRepository userrepository;
	private   ModuleAccountRepository modulerepository;
@Autowired
public UserModuleService(UserModuleRepository usermodulerepository, UsersRepository userrepository,
		ModuleAccountRepository modulerepository) {
	super();
	this.usermodulerepository = usermodulerepository;
	this.userrepository = userrepository;
	this.modulerepository = modulerepository;
}

public List<UsersModule> getModuleByUserId(Long id){
	List<UsersModule> usermodule;
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}

	usermodule=usermodulerepository.findByUsersId(id);
		if(usermodule==null) {
			throw new CustomException("no resource found");
		}
	
	
	return usermodule;
}

public UsersModule createUserModule(UsersModule usermodule) {
	
	Long userid= usermodule.getUsers().getId();
	Long moduleid= usermodule.getModules().getId();
	

	if(usermodule.getUsers()==null) {
		throw new CustomException("you should provide at least a user id ");
	} 
	else if (userid==null) {
		throw new CustomException("your user id is null");
	}
	else if(moduleid==null) {
		
		throw new CustomException("your module id is null");
		
	}
	else if((usermodule.getUsers().getId()<=0) ) {
		throw new CustomException("please verify account id");
	} 
	else if((usermodule.getModules()==null) ) {
		throw new CustomException("you should provide at least a module id");
	} 
	
	else if((usermodule.getModules().getId()<=0)|  (usermodule.getModules().getId()==null)) {
		throw new CustomException("please verify account id");
	} 
	
	try {userrepository.findById(userid);
			modulerepository.findById(moduleid);
	return usermodulerepository.save(usermodule);
	}catch(Exception e){
			
			if(e.getMessage() == "Unable to find ") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			
			 throw new CustomException("verify your user id field or your module id field"); }
	
}
public void deleteUserModule(Long id) {
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}
	else {
		try{

			 usermodulerepository.deleteById(id);
			
		}catch(Exception e){
		
		if(e.getMessage() == "No class") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("resource doesn t not exist"); 
	}
	
}

}
public UsersModule updateUserModule( UsersModule usermodule,Long id) {
	UsersModule usermodule1;
	
	if(id <=0 ) {
		throw new CustomException("invalid id");
	}
	
	
	
	else {
		try{

			usermodule1 =usermodulerepository.findById(id).get();
						
			
			if(usermodule.getModules()==null) {
				usermodule.setModules(usermodule1.getModules());
			}

			if(usermodule.getStatus()==0) {
				usermodule.setStatus(usermodule1.getStatus());
			}
			if(usermodule.getUsers()==null) {
				usermodule.setUsers(usermodule1.getUsers());
			}
			if(usermodule.getStart_date()==null) {
				usermodule.setStart_date(usermodule1.getStart_date());
			}
			if(usermodule.getEnd_date()==null) {
				usermodule.setEnd_date(usermodule1.getStart_date());
			}
			
			return usermodulerepository.saveAndFlush(usermodule);
		}catch(Exception e){
		
		if(e.getMessage() == "No value present") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		
		 throw new CustomException("verify your fields"); 
	}
	
	}
	
	}


public <Optional> UsersModule findUserModuleByid(Long id) {
	// TODO Auto-generated method stub
	UsersModule module;
	
			 if (id <= 0) {
		 throw new CustomException("invalid id");
	}
	
	else {
			try{

				module =  usermodulerepository.findById(id).get();
				return module;
			}catch(Exception e){
			
			if(e.getMessage() == "No value present") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			 throw new CustomException("ma famech"); 
		}
		
	}
		
}















}
