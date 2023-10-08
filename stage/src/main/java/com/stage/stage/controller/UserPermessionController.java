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
import com.stage.stage.entity.UserPermessions;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.UserPermessionService;
import com.stage.stage.validators.Validators;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@Validated
public class UserPermessionController {
private UserPermessionService permessionservice;
@Autowired
public UserPermessionController(UserPermessionService permessionservice) {
	super();
	this.permessionservice = permessionservice;
}
@GetMapping("/permessions/{id}")
public CoreAcountResponse getPermessionsByAccountId( @Valid @PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	int total=0;
	 List<UserPermessions> userpermession=permessionservice.getPermessionByUserId(id1);
	 for(UserPermessions temp :userpermession) {
		 total=total+1;
	 }
	return new CoreAcountResponse("sucess",userpermession,total);
	
}
@PostMapping("/createPermession")
public CoreAcountResponse createModule(@Valid @RequestBody UserPermessions userpermession) {
	
	UserPermessions userpermession1 = permessionservice.createUserPermession(userpermession);
	
	return new CoreAcountResponse("success", userpermession1,1);
}
@PutMapping("/updatePermession/{id}")
public CoreAcountResponse editPermession(@Valid @RequestBody UserPermessions userpermession ,@Valid @PathVariable(name="id")String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)  ;
	
	
	userpermession.setId(id1);
	UserPermessions userpermession1= permessionservice.updatePermession(userpermession, id1);
  return new CoreAcountResponse("sucess", userpermession1,1);
}

@DeleteMapping("/deletePermession/{id}")

public DeleteResponse deletePermessionbyId(@Valid @PathVariable(name="id") String id) {
	boolean val = Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid id type error");
	}
	Long id1=Long.parseLong(id)  ;
	
	permessionservice.deleteUserPermession(id1);
	return new  DeleteResponse("success","Media deleted successfully");
}

}
