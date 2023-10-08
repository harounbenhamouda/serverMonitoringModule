package com.stage.stage.controller;

import java.time.temporal.ValueRange;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.aspectj.weaver.ast.Instanceof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.NumberUtils;
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
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.Core_Accounts;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.CoreAccountServiceImp;
import com.stage.stage.service.ModuleService;
import com.stage.stage.service.ServiceInterface;
import com.stage.stage.validators.Validators;
@Validated
@CrossOrigin("*")
@RestController
@RequestMapping("/api")

public class CoreAccountController {
private ServiceInterface coreaccountserviceimp;
private ModuleService moduleservice;
@Autowired
public CoreAccountController(@Valid CoreAccountServiceImp coreaccountserviceimp, ModuleService moduleservice) {
	super();
	this.coreaccountserviceimp = coreaccountserviceimp;
	this.moduleservice=moduleservice;
}

@PostMapping("/createAccount")
public CoreAcountResponse createAccount1( @Valid @RequestBody Core_Accounts coreaccount) {
	
	 Core_Accounts coreaccount1 =coreaccountserviceimp.createAccount(coreaccount);
	
	return new CoreAcountResponse("sucess", coreaccount1,1);
}
@GetMapping("/accounts")
public CoreAcountResponse getAllaccounts(){
	int total=0;
	 List<Core_Accounts> allacounts=coreaccountserviceimp.getAllAccounts();
	 for(Core_Accounts temp :allacounts) {
		 total=total+1;
	 }
	return new CoreAcountResponse("sucess",allacounts,total);
	
}

@GetMapping("/account/{id}")
public CoreAcountResponse getUsersById(@Valid @PathVariable(name="id") String id){
	
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;
Core_Accounts account = coreaccountserviceimp.findAccountByid(id1);
	return new CoreAcountResponse("sucess",account,1);
}
@DeleteMapping("/deleteaccount/{id}")
public DeleteResponse deleteAccountbyId(@PathVariable(name="id") String id) {
	boolean val = Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)  ;
	coreaccountserviceimp.deleteAccount(id1);
	return new  DeleteResponse("success","Media deleted successfully");
}
@PutMapping("/updateAccount/{id}")
public CoreAcountResponse editStudent(@Valid @RequestBody Core_Accounts coreaccount,@PathVariable(name="id") String id){
	
	
	boolean val =Validators.MenichInt(id);
	
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)  ;
	if( coreaccount.getName()=="") {
		throw new CustomException("invalid name field");
	}
	
	coreaccount.setId(id1);
   
   Core_Accounts account= coreaccountserviceimp.updateAccount(coreaccount,(long) id1);
  
  return new CoreAcountResponse("sucess", account,1);
}
@GetMapping("/activeinstance/user/{user_id}/account/{account_id}")
public CoreAcountResponse  getActiveInstance(@PathVariable String user_id,@PathVariable String  account_id){
	boolean val = Validators.MenichInt(user_id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long user_id1=Long.parseLong(user_id);
	boolean val2 = Validators.MenichInt(account_id);
	if(val2 == false) {
		throw new CustomException("invalid type error");
	}
	Long account_id1=Long.parseLong(account_id);
	List<Map<String,Object>> instance= coreaccountserviceimp.getActivatedUseInstances(user_id1, account_id1);
	 return new CoreAcountResponse("succes",instance,1);
}




}
