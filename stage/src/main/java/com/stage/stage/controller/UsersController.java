package com.stage.stage.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.CoreAccountModule;
import com.stage.stage.entity.Core_Accounts;
import com.stage.stage.entity.Users;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.UserService;
import com.stage.stage.validators.Validators;
@CrossOrigin("*")
@RequestMapping("/api")
@RestController
@Validated
public class UsersController {
	private UserService userservice;
@Autowired
	public UsersController(UserService userservice) {
		super();
		this.userservice = userservice;
	}

@PostMapping("/createUser")
public CoreAcountResponse createModule(@Valid @RequestBody Users user) {
	
    Users user1 = userservice.createUser(user);
	
	return new CoreAcountResponse("success", user1,1);
}
@GetMapping("/users/{id}")
public CoreAcountResponse getAllusers(@Valid @PathVariable(name="id") String id){
	boolean val = Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid id type error");
	}
	Long id1=Long.parseLong(id)  ;
	int total=0;
	 List<Users> allusers=userservice.getUsersByAccountId(id1);
	 for(Users temp :allusers) {
		 total=total+1;
	 }
	return new CoreAcountResponse("sucess",allusers,total);
	
}
@DeleteMapping("/deleteUser/{id}")

public DeleteResponse deleteUserById(@Valid @PathVariable(name="id") String id) {
	boolean val = Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid id type error");
	}
	Long id1=Long.parseLong(id)  ;
	
	userservice.deleteUser(id1);
	return new  DeleteResponse("success","Media deleted successfully");
}

@PutMapping("/updateUser/{id}")
public CoreAcountResponse editUser(@Valid @RequestBody Users user,@Valid @PathVariable(name="id")String id){
	boolean val =Validators.MenichInt(id);
	
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)  ;
	
    user.setId(id1);
    Users user1 = userservice.updateUser(user, id1);
  return new CoreAcountResponse("sucess", user1,1);
}

@GetMapping("/user/{id}")
public CoreAcountResponse getUserById(  @Valid @PathVariable(name="id") String id) {

	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	Users user = userservice.getUserById(id1);
	return  new CoreAcountResponse("sucess",user,1);
}
@GetMapping("/users")
public CoreAcountResponse getAllUsers() {
	List<Users> listuser=userservice.getUsers();
	return new CoreAcountResponse("success",listuser,1);
}
}
