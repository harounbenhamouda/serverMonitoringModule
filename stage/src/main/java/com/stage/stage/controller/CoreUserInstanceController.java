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
import com.stage.stage.entity.CoreUserInstance;
import com.stage.stage.entity.UserPermessions;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.CoreUserInstanceService;
import com.stage.stage.validators.Validators;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@Validated
public class CoreUserInstanceController {
private CoreUserInstanceService instanceservice;
@Autowired
public CoreUserInstanceController(CoreUserInstanceService instanceservice) {
	super();
	this.instanceservice = instanceservice;
}

@GetMapping("/insantces/{id}")
public CoreAcountResponse getInstanceByUserId( @Valid @PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	int total=0;
	 List<CoreUserInstance> userinstance=instanceservice.getInstanceByUserId(id1);
	 for(CoreUserInstance temp :userinstance) {
		 total=total+1;
	 }
	return new CoreAcountResponse("sucess",userinstance,total);
	
}
@GetMapping("/instance/{id}")
public CoreAcountResponse getInstanceByrId( @Valid @PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	
	 CoreUserInstance userinstance=instanceservice.getInstanceById(id1);
	 
	return new CoreAcountResponse("sucess",userinstance,1);
	
}











@PostMapping("/createUserInstance")
public CoreAcountResponse createInstance(@Valid @RequestBody CoreUserInstance userinstance) {
	
	CoreUserInstance userpermession1 = instanceservice.createUserInstance(userinstance);
	
	return new CoreAcountResponse("success", userpermession1,1);
}
@PutMapping("/updateUserInstance/{id}")
public CoreAcountResponse editPermession(@Valid @RequestBody CoreUserInstance userinstance ,@Valid @PathVariable(name="id")String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)  ;
	
	
	userinstance.setId(id1);
	CoreUserInstance userinstance1= instanceservice.updateInstance(userinstance, id1);
  return new CoreAcountResponse("sucess", userinstance1,1);
}
@DeleteMapping("/deleteInstance/{id}")
public DeleteResponse deleteAccountbyId(@PathVariable(name="id") String id) {
	boolean val = Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)  ;
	instanceservice.deleteUserInstance(id1);
	return new  DeleteResponse("success","Media deleted successfully");
}


}
