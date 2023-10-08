package com.stage.stage.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.stage.stage.entity.CategoriesElements;
import com.stage.stage.entity.Credentials;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.CredentialsService;
import com.stage.stage.validators.Validators;

@Validated
@RestController
@RequestMapping("/api")
public class CredentialsController {
	private CredentialsService credentialservice;

	public CredentialsController(CredentialsService credentialservice) {
		super();
		this.credentialservice = credentialservice;
	}
	
	
	@GetMapping("/credentials/{id}")
	public CoreAcountResponse getCredentialbyAccountId( @Valid @PathVariable(name="id") String id){
		boolean val =Validators.MenichInt(id);
		if(val == false) {
			throw new CustomException("invalid type error");
		}
		Long id1=Long.parseLong(id)   ;

		int total=0;
		 List<Credentials> credentials=credentialservice.getCredentialsByaccountId(id1);
		 for(Credentials temp :credentials) {
			 total=total+1;
		 }
		return new CoreAcountResponse("sucess",credentials,total);
		
	}

	@PostMapping("/createCredential")
	public CoreAcountResponse createCategory(@Valid @RequestBody Credentials credential) {
		
		Credentials credential1 = credentialservice.createCredentils(credential);
		
		return new CoreAcountResponse("success", credential1,1);
	}

	@PutMapping("/updatecredential/{id}")
	public CoreAcountResponse editUserSecurity(@Valid @RequestBody Credentials credential ,@Valid @PathVariable(name="id")String id){
		boolean val =Validators.MenichInt(id);
		if(val == false) {
			throw new CustomException("invalid type error");
		}
		Long id1=Long.parseLong(id);
		
		
		credential.setId(id1);
		Credentials credential1= credentialservice.updateCredentials(credential, id1);
	  return new CoreAcountResponse("sucess", credential1,1);
	}

	@DeleteMapping("/deleteCredentials/{id}")

	public DeleteResponse deleteCredentialById(@Valid @PathVariable(name="id") String id) {
		boolean val = Validators.MenichInt(id);
		if(val == false) {
			throw new CustomException("invalid id type error");
		}
		Long id1=Long.parseLong(id)  ;
		
		credentialservice.deleteCredentials(id1);
		return new  DeleteResponse("success","Media deleted successfully");
	}

	@GetMapping("/credential/{id}")
	public CoreAcountResponse getUsersById(@Valid @PathVariable(name="id") String id){
		
		boolean val =Validators.MenichInt(id);
		if(val == false) {
			throw new CustomException("invalid type error");
		}
		Long id1=Long.parseLong(id)   ;
	Credentials credential = credentialservice.getCredentialsByid(id1);
		return new CoreAcountResponse("sucess", credential,1);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
