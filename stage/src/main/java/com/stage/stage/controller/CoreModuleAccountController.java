package com.stage.stage.controller;

import java.time.temporal.ValueRange;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.CoreAccountModule;
import com.stage.stage.entity.Core_Accounts;
import com.stage.stage.entity.Modules;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.ModuleAccountService;
import com.stage.stage.validators.Validators;
@CrossOrigin("*")
@RequestMapping("/api")
@RestController
@Validated
public class CoreModuleAccountController {
private ModuleAccountService moduleAccountservice;
@Autowired
public CoreModuleAccountController(ModuleAccountService moduleAccountservice) {
	super();
	this.moduleAccountservice = moduleAccountservice;
}
@PostMapping("/createCoreModule")
public CoreAcountResponse createModule(@Valid @RequestBody CoreAccountModule accountmodule) {
	
	CoreAccountModule moduleaccount1 = moduleAccountservice.createModule(accountmodule);
	
	return new CoreAcountResponse("success", moduleaccount1,1);
}
@DeleteMapping("/deleteCoreModule/{id}")

public DeleteResponse deleteAccountbyId(@Valid @PathVariable(name="id") String id) {
	boolean val = Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid id type error");
	}
	Long id1=Long.parseLong(id)  ;
	
	moduleAccountservice.deleteAccount(id1);
	return new  DeleteResponse("success","Media deleted successfully");
}
@GetMapping("/coreModules/{id}")
public CoreAcountResponse getModulebyAccountId( @Valid @PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	int total=0;
	 List<CoreAccountModule> allmodules=moduleAccountservice.getModulebyAccountId(id1);
	 for(CoreAccountModule temp :allmodules) {
		 total=total+1;
	 }
	return new CoreAcountResponse("sucess",allmodules,total);
	
}
@GetMapping("/coreModule/{id}")
public CoreAcountResponse getModuleById(  @Valid @PathVariable(name="id") String id) {

	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	CoreAccountModule moduleaccount1 = moduleAccountservice.getMouduleById(id1);
	return  new CoreAcountResponse("sucess",moduleaccount1,1);
}
@PutMapping("/updateCoreModule/{id}")
public CoreAcountResponse editModule(@Valid @RequestBody CoreAccountModule moduleaccount,@Valid @PathVariable(name="id")String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)  ;
	
	
    moduleaccount.setId(id1);
    CoreAccountModule module= moduleAccountservice.updateModule(moduleaccount, id1);
  return new CoreAcountResponse("sucess", module,1);
}
@GetMapping("/activemodule/user/{user_id}/account/{account_id}")
public List<Map<String,Object>>  getActiveModule(@PathVariable Long user_id,@PathVariable Long account_id){
	
	return moduleAccountservice.getAccountUserModules(user_id,account_id);
}

}