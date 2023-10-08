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
import com.stage.stage.entity.UserSecurity;
import com.stage.stage.entity.UsersModule;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.UserSecurityService;
import com.stage.stage.validators.Validators;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@Validated
public class UserSecurityController {
private UserSecurityService usersecurityservice;
@Autowired
public UserSecurityController(UserSecurityService usersecurityservice) {
	super();
	this.usersecurityservice = usersecurityservice;
}


@PostMapping("/createUserSecurity")
public CoreAcountResponse createUserSecurity(@Valid @RequestBody UserSecurity usersecurity) {
	
	UserSecurity usersecurity1 = usersecurityservice.createUserSecurity(usersecurity);
	
	return new CoreAcountResponse("success", usersecurity1,1);
}

@PutMapping("/updateUserSecurity/{id}")
public CoreAcountResponse editUserSecurity(@Valid @RequestBody UserSecurity usersecurity ,@Valid @PathVariable(name="id")String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)  ;
	
	
	usersecurity.setId(id1);
	UserSecurity usersecurity1= usersecurityservice.updateUserSecurity(usersecurity, id1);
  return new CoreAcountResponse("sucess", usersecurity,1);
}

@DeleteMapping("/deleteUserSecurity/{id}")

public DeleteResponse deleteuserSecurityById(@Valid @PathVariable(name="id") String id) {
	boolean val = Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid id type error");
	}
	Long id1=Long.parseLong(id)  ;
	
	usersecurityservice.deleteUserModule(id1);
	return new  DeleteResponse("success","Media deleted successfully");
}

@GetMapping("/userSecurity/{id}")
public CoreAcountResponse getUsersById(@Valid @PathVariable(name="id") String id){
	
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;
UserSecurity  usersecurity = usersecurityservice.findUserSecurityByid(id1);
	return new CoreAcountResponse("sucess",usersecurity,1);
}



}
