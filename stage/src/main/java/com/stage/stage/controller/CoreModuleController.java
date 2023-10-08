package com.stage.stage.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stage.stage.Exception.CustomException;

import com.stage.stage.entity.Modules;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.ModuleService;
import com.stage.stage.validators.Validators;
@RestController
@RequestMapping("/api")
@Validated
public class CoreModuleController {
private ModuleService moduleservice;
@Autowired
public CoreModuleController(ModuleService moduleservice) {
	super();
	this.moduleservice = moduleservice;
}


@PostMapping("/createModule")
public CoreAcountResponse createModule1(@Valid @RequestBody Modules module) {
	
	 Modules module1 =moduleservice.createModule(module);
	
	return new CoreAcountResponse("sucess", module1,1);
}
@GetMapping("/modules")
public CoreAcountResponse getAllaccounts(){
	int total=0;
	 List<Modules> module=moduleservice.getAllModules();
	 for(Modules temp :module) {
		 total=total+1;
	 }
	return new CoreAcountResponse("sucess",module,total);
	
}

@GetMapping("/module/{id}")
public CoreAcountResponse getUsersById(@Valid @PathVariable(name="id") String id){
	
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;
Modules module = moduleservice.findModuleByid(id1);
	return new CoreAcountResponse("sucess",module,1);
}
@DeleteMapping("/deleteModule/{id}")
public DeleteResponse deleteAccountbyId(@Valid @PathVariable(name="id") String id) {
	boolean val = Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)  ;
	 moduleservice.deleteModule(id1);
	return new  DeleteResponse("success","Media deleted successfully");
}
@PutMapping("/updateModule/{id}")
public CoreAcountResponse editStudent(@Valid @RequestBody Modules module,@PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)  ;
	
	
	
   
   Modules module1= moduleservice.updateModule(module, id1);
  
  return new CoreAcountResponse("sucess", module1,1);
}














}
