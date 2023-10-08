package com.stage.stage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.CoreUserInstance;
import com.stage.stage.entity.UserPermessions;
import com.stage.stage.entity.Users;
import com.stage.stage.repository.CoreUserInstanceRepository;
import com.stage.stage.repository.UserPermessionRepository;
import com.stage.stage.repository.UsersRepository;

@Service
public class CoreUserInstanceService {
private CoreUserInstanceRepository instancerepository;
	private UsersRepository userRepository;
	@Autowired
	public CoreUserInstanceService(CoreUserInstanceRepository instancerepository, UsersRepository userRepository) {
		super();
		this.instancerepository = instancerepository;
		this.userRepository = userRepository;
	}

	
	public List<CoreUserInstance> getInstanceByUserId(Long id){
		List<CoreUserInstance> userinstance;
		if(id <=0 || id==null) {
			throw new CustomException("invalid id");
		}

		userinstance=instancerepository.findByUsersId(id);
			if(userinstance==null) {
				throw new CustomException("no resource found");
			}
		
		
		return userinstance;
	}
	public CoreUserInstance getInstanceById(Long id){
		CoreUserInstance	userinstance;
		if(id <=0 || id==null) {
			throw new CustomException("invalid id");
		}

		userinstance=instancerepository.findById(id).get();
			if(userinstance==null) {
				throw new CustomException("no resource found");
			}
		
		
		return userinstance;
	}
public  CoreUserInstance createUserInstance(CoreUserInstance userinstance) {
		
		Long userid= userinstance.getUsers().getId();
		
		if(( userinstance.getInstances()==null )|  userinstance.getInstances().getId()<=0 ){
			throw new CustomException("element id is not valid");
		}
		else if (userid==null) {
			throw new CustomException("user id is null");
		}
		
		
		else if((userinstance.getUsers().getId()<=0) ) {
			throw new CustomException("please verify account id");
		} 
		 
		
		try {userRepository.findById(userid);
		return instancerepository.save(userinstance);
		}catch(Exception e){
				
				if(e.getMessage() == "Unable to find ") {
					throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
				}
				
				 throw new CustomException("verify your user id fields"); }
		
	}
	


public CoreUserInstance updateInstance( CoreUserInstance userinstance,Long id) {
	CoreUserInstance  userinstance1;
	
	
	if(id <=0 ) {
		throw new CustomException("invalid id");
	}
	else if(( userinstance.getInstances()==null )|  userinstance.getInstances().getId()<=0 ){
		throw new CustomException("element id is not valid");
	}
	
	
	else {
		try{

			userinstance1 =instancerepository.findById(id).get();
						
			
			if(userinstance.getStatus()==0) {
				userinstance.setStatus(userinstance1.getStatus());
			}

			if(userinstance.getStart_date()==null) {
				userinstance.setStart_date(userinstance1.getStart_date());
			}
			if(userinstance.getEnd_date()==null) {
				userinstance.setEnd_date(userinstance1.getEnd_date());
			}
			
			
			return instancerepository.saveAndFlush(userinstance);
		}catch(Exception e){
		
		if(e.getMessage() == "No value present") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		
		 throw new CustomException("verify your fields"); 
	}
	
	}
	
	}

public void deleteUserInstance(Long id) {
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}
	else {
		try{

			 instancerepository.deleteById(id);
			
		}catch(Exception e){
		
		if(e.getMessage() == "No class") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("resource doesn t not exist"); 
	}
	
}
	
}	
	
	
	
	
}
