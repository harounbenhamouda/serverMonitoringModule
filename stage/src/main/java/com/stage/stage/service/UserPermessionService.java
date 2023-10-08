package com.stage.stage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.CoreAccountModule;
import com.stage.stage.entity.UserPermessions;
import com.stage.stage.repository.UserPermessionRepository;
import com.stage.stage.repository.UsersRepository;
import com.stage.stage.validators.Validators;

@Service

public class UserPermessionService {
	private UserPermessionRepository userpermessionrepository;
	private UsersRepository userRepository;
	public UserPermessionService(UserPermessionRepository userpermessionrepository, UsersRepository userRepository) {
		super();
		this.userpermessionrepository = userpermessionrepository;
		this.userRepository = userRepository;
	}
	public List<UserPermessions> getPermessionByUserId(Long id){
		List<UserPermessions> userpermession;
		if(id <=0 || id==null) {
			throw new CustomException("invalid id");
		}

			userpermession=userpermessionrepository.findByUsersId(id);
			if(userpermession==null) {
				throw new CustomException("no resource found");
			}
		
		
		return userpermession;
	}
	public UserPermessions createUserPermession(UserPermessions userpermession) {
		
		Long userid= userpermession.getUsers().getId();
		
		

		if(userpermession.getUsers()==null) {
			throw new CustomException("you should provide at least an account id ");
		} 
		else if(userid==null) {
		throw new CustomException("your user id is null");}
		else if((userpermession.getUsers().getId()<=0) ) {
			throw new CustomException("please verify account id");
		} 
		
		try {userRepository.findById(userid);
		return userpermessionrepository.save(userpermession);
		}catch(Exception e){
				
				if(e.getMessage() == "Unable to find ") {
					throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
				}
				
				 throw new CustomException("verify your user id fields"); }
		
	}
	
	public UserPermessions updatePermession( UserPermessions userpermession,Long id) {
		UserPermessions userpermession1;
		
		if(id <=0 ) {
			throw new CustomException("invalid id");
		}
		
		
		
		else {
			try{

				userpermession1 =userpermessionrepository.findById(id).get();
							
				
				if(userpermession.getCode()==null) {
					userpermession.setCode(userpermession1.getCode());
				}

				if(userpermession.getStatus()==0) {
					userpermession.setStatus(userpermession1.getStatus());
				}
				if(userpermession.getDependency()==0) {
					userpermession.setDependency(userpermession1.getDependency());
				}
				return userpermessionrepository.saveAndFlush(userpermession);
			}catch(Exception e){
			
			if(e.getMessage() == "No value present") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			
			 throw new CustomException("verify your fields"); 
		}
		
		}
		
		}
		
	public void deleteUserPermession(Long id) {
		if(id <=0 || id==null) {
			throw new CustomException("invalid id");
		}
		else {
			try{

				 userpermessionrepository.deleteById(id);
				
			}catch(Exception e){
			
			if(e.getMessage() == "No class") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			 throw new CustomException("resource doesn t not exist"); 
		}
		
	}
	
}}
