package com.stage.stage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.Categories;
import com.stage.stage.entity.CategoriesElements;
import com.stage.stage.entity.CoreAccountModule;
import com.stage.stage.entity.Core_Accounts;
import com.stage.stage.entity.Users;
import com.stage.stage.repository.CoreAccountsRepository;
import com.stage.stage.repository.UsersRepository;
import com.stage.stage.validators.Validators;

@Service
public class UserService {
private CoreAccountsRepository car;
private UsersRepository userRepository;
@Autowired
public UserService(CoreAccountsRepository car, UsersRepository userRepository) {
	super();
	this.car = car;
	this.userRepository = userRepository;
}
public Users createUser(Users user) {
	
	Long accountid= user.getAccount().getId();
	

	if(user.getAccount()==null) {
		throw new CustomException("you should provide at least an account id ");
	} 
	else if((user.getAccount().getId()<=0) ) {
		throw new CustomException("please verify account id");
	}
	
	try {car.findById(accountid);
	return userRepository.save(user);
	}catch(Exception e){
			
			if(e.getMessage() == "could not execute statement") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			
			 throw new CustomException("verify your fields"); }
		
	
	
}

public List<Users> getUsers() {
	// TODO Auto-generated method stub
	return userRepository.findAll();
}
public List<Users> getUsersByAccountId(Long id){
	List<Users>users;
	
	 if (id <= 0) {
throw new CustomException("invalid id");
}

else {
	try{

		users =  userRepository.findByAccountId(id);
		return users;
	}catch(Exception e){
	
	if(e.getMessage() == "No value present") {
		throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
	}
	 throw new CustomException("verfiy your Category id"); 
}

}
}





public void deleteUser(Long id) {
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}
	else {
		try{

			 userRepository.deleteById(id);
			
		}catch(Exception e){
		
		if(e.getMessage() == "No class") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("resource doesn t not exist"); 
	}
	
}
	
}
public Users updateUser(Users user,Long id) {
	Users user1;
	
	if(id <=0 ) {
		throw new CustomException("invalid id");
	}
	
	else {
		try{

			user1 =userRepository.findById(id).get();
			
			user1.setFirstname(user.getFirstname());
			user1.setLastname(user.getLastname());
			user1.setRole(user.getRole());
			user1.setStatus(user.getStatus());
			if(user.getIp_address()!=null) {
				user1.setIp_address(user.getIp_address());
			}
			if(user.getLanguage()!=null) {
				user1.setLanguage(user.getLanguage());
			}
			
			if(user.getLast_auth()!=null) {
				user1.setLast_auth(user.getLast_auth());
			}
			if(user.getBrowser()!=null) {
				user1.setBrowser(user.getBrowser());
			}
			
			if(user.getPhoto()!=null) {
				user1.setPhoto(user.getPhoto());}
			if(user.getPhoto()==null) {
				user1.setPhoto(user.getPhoto());}
			
			if(user.getTimezone()!=null) {
				user1.setTimezone(user.getTimezone());
			}
			
			return userRepository.saveAndFlush(user1);
		}catch(Exception e){
		
		if(e.getMessage() == "No value present") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		
		 throw new CustomException("verify your fields"); 
	}}}
public Users getUserById(Long id)
{
	Users user;
	 if (id <= 0) {
		 throw new CustomException("invalid id");
	}
	
	else {
			try{

				 user=userRepository.findById(id).get();
				return user;
			}catch(Exception e){
			
			if(e.getMessage() == "No value present") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			 throw new CustomException("ma famech"); 
		}
		
	}	
	
	
	}

public List<Users> getAllUser() {
	// TODO Auto-generated method stub
	return userRepository.findAll();
}


}
