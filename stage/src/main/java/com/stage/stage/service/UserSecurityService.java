package com.stage.stage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.UserSecurity;
import com.stage.stage.entity.UsersModule;
import com.stage.stage.repository.UserSecurityRepository;
import com.stage.stage.repository.UsersRepository;

@Service
public class UserSecurityService {
	private UserSecurityRepository usersecurityrepo;
	private UsersRepository userrepository;
	@Autowired
	public UserSecurityService(UserSecurityRepository usersecurityrepo, UsersRepository userrepository) {
		super();
		this.usersecurityrepo = usersecurityrepo;
		this.userrepository = userrepository;
	}
	
	public UserSecurity getUserSecurityByUserId(Long id){
		UserSecurity usersecurity;
		if(id <=0 || id==null) {
			throw new CustomException("invalid id");
		}

		usersecurity=usersecurityrepo.findByUsersId(id);
			if(usersecurity==null) {
				throw new CustomException("no resource found");
			}
		
		
		return usersecurity;
	}
	public UserSecurity createUserSecurity(UserSecurity usersecurity) {
		
		Long userid= usersecurity.getUsers().getId();
		
		

		if(usersecurity.getUsers()==null) {
			throw new CustomException("you should provide at least a user id ");
		} 
		
		else if (userid==null) {
			throw new CustomException("your user id is null");
		}
		
		else if((usersecurity.getUsers().getId()<=0) ) {
			throw new CustomException("please verify account id");
		} 
		
		
		
		try {userrepository.findById(userid);
				
		return usersecurityrepo.save(usersecurity);
		}catch(Exception e){
				
				if(e.getMessage() == "Unable to find user") {
					throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
				}
				
				 throw new CustomException("verify your user id field "); }
		
	}
	
	public UserSecurity updateUserSecurity( UserSecurity usersecurity,Long id) {
		UserSecurity usersecurity1;
		
		if(id <=0 ) {
			throw new CustomException("invalid id");
		}
		
		
		
		else {
			try{

				usersecurity1 =usersecurityrepo.findById(id).get();
							
				
				

				
				if(usersecurity.getStart_date()==null) {
					usersecurity.setStart_date(usersecurity1.getStart_date());
				}
				if(usersecurity.getEnd_date()==null) {
					usersecurity.setStart_date(usersecurity1.getEnd_date());
					
				}
				
				return usersecurityrepo.saveAndFlush(usersecurity);
			}catch(Exception e){
			
			if(e.getMessage() == "No value present") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			
			 throw new CustomException("verify your fields"); 
		}
		
		}
		
		}

	
	
	public void deleteUserModule(Long id) {
		if(id <=0 || id==null) {
			throw new CustomException("invalid id");
		}
		else {
			try{

				usersecurityrepo.deleteById(id);
				
			}catch(Exception e){
			
			if(e.getMessage() == "No class") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			 throw new CustomException("resource doesn t not exist"); 
		}
		
	}	
	}
	
	
	
	
	public UserSecurity  findUserSecurityByid(Long id) {
		// TODO Auto-generated method stub
   UserSecurity usersecurity;
		
				 if (id <= 0) {
			 throw new CustomException("invalid id");
		}
		
		else {
				try{

					usersecurity =  usersecurityrepo.findByUsersId(id);
					return usersecurity;
				}catch(Exception e){
				
				if(e.getMessage() == "No value present") {
					throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
				}
				 throw new CustomException("ma famech"); 
			}
			
		}
			
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
