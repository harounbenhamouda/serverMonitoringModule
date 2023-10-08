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
import com.stage.stage.entity.Core_Accounts;
import com.stage.stage.entity.UserPermessions;
import com.stage.stage.entity.Users;
import com.stage.stage.entity.UsersModule;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.UserModuleService;
import com.stage.stage.validators.Validators;
@CrossOrigin("*")
@Validated
@RestController
@RequestMapping("/api")
public class UsersModuleController {
	private UserModuleService userModuleservice;
	@Autowired
	public UsersModuleController(UserModuleService userModuleservice) {
		super();
		this.userModuleservice = userModuleservice;
	}

	@GetMapping("/userModules/{id}")
	public CoreAcountResponse getModuleByUserId( @Valid @PathVariable(name="id") String id){
		boolean val =Validators.MenichInt(id);
		if(val == false) {
			throw new CustomException("invalid type error");
		}
		Long id1=Long.parseLong(id)   ;

		int total=0;
		 List<UsersModule> usermodule=userModuleservice.getModuleByUserId(id1);
		 for(UsersModule temp :usermodule) {
			 total=total+1;
		 }
		return new CoreAcountResponse("sucess",usermodule,total);
		
	}
	
	@GetMapping("/userModule/{id}")
	public CoreAcountResponse getUsersById(@Valid @PathVariable(name="id") String id){
		
		boolean val =Validators.MenichInt(id);
		if(val == false) {
			throw new CustomException("invalid type error");
		}
		Long id1=Long.parseLong(id)   ;
	UsersModule usermodule = userModuleservice.findUserModuleByid(id1);
		return new CoreAcountResponse("sucess",usermodule,1);
	}
	
	
	
	
	
	
	
	
	
	@PostMapping("/createUserModule")
	public CoreAcountResponse createUserModule(@Valid @RequestBody UsersModule usermodule) {
		
	    UsersModule user1 = userModuleservice.createUserModule(usermodule);
		
		return new CoreAcountResponse("success", user1,1);
	}
	
	@DeleteMapping("/deleteUserModule/{id}")

	public DeleteResponse deleteuserModulebyId(@Valid @PathVariable(name="id") String id) {
		boolean val = Validators.MenichInt(id);
		if(val == false) {
			throw new CustomException("invalid id type error");
		}
		Long id1=Long.parseLong(id)  ;
		
		userModuleservice.deleteUserModule(id1);
		return new  DeleteResponse("success","Media deleted successfully");
	}
	
	@PutMapping("/updateUserModule/{id}")
	public CoreAcountResponse editPermession(@Valid @RequestBody UsersModule usermodule ,@Valid @PathVariable(name="id")String id){
		boolean val =Validators.MenichInt(id);
		if(val == false) {
			throw new CustomException("invalid type error");
		}
		Long id1=Long.parseLong(id)  ;
		
		
		usermodule.setId(id1);
		UsersModule usermodule1= userModuleservice.updateUserModule(usermodule, id1);
	  return new CoreAcountResponse("sucess", usermodule1,1);
	}
	
	
	
	
	

}
