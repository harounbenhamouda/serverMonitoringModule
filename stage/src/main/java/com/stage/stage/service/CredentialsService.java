package com.stage.stage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.CategoriesElements;
import com.stage.stage.entity.Credentials;
import com.stage.stage.entity.Users;
import com.stage.stage.repository.CoreAccountsRepository;
import com.stage.stage.repository.CredentialsRepository;
import com.stage.stage.validators.Validators;

@Service
public class CredentialsService {
	private CredentialsRepository credentialrepo;
	private CoreAccountsRepository coreaccountrepo;
	@Autowired
	public CredentialsService(CredentialsRepository credentialrepo, CoreAccountsRepository coreaccountrepo) {
		super();
		this.credentialrepo = credentialrepo;
		this.coreaccountrepo = coreaccountrepo;
	}
	
	public Credentials createCredentils(Credentials credential) {
		
		Long accountid=credential.getAccount().getId();
		

		if((credential.getAccount()==null )) {
			throw new CustomException("you should provide at least an account id ");
		} 
		else if (accountid==null) {
			throw new CustomException("your account id is null");
		}
		else if(credential.getAccount().getId()<=0)  {
			throw new CustomException("please verify account id");
		}
		
		try {coreaccountrepo.findById(accountid);
		return credentialrepo.save(credential);
		}catch(Exception e){
				
				if(e.getMessage() == "could not execute statement") {
					throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
				}
				
				 throw new CustomException("verify your account fields"); }		
		}
	
	public Credentials updateCredentials(Credentials credential,Long id) {
		Credentials credential1;
		
		if(id <=0 ) {
			throw new CustomException("invalid id");
		}
		
		else {
			try{

				credential1 =credentialrepo.findById(id).get();
				
				
				if(Validators.MenichString(credential.getStatus())==false) {
					throw new CustomException("not a valid parametre");
				}
				
				if(credential.getPort()==null) {
					credential.setPort(credential1.getPort());}
				if(credential.getUrl()==null) {
					credential.setUrl(credential1.getUrl());}
				
				if(credential.getStart_date()==null) {
					credential.setStart_date(credential1.getStart_date());
				}
				
				if(credential.getElement()==0) {
					credential.setElement(credential1.getElement());
				}
				
				if(credential.getClasse()==0) {
					credential.setClasse(credential1.getClasse());
				}
				if(credential.getElement_id()==null) {
					credential.setElement_id(credential.getElement_id());
				}
				if(credential.getStatus()==0) {
					credential.setStatus(credential1.getStatus());
				}
			
				
				return credentialrepo.saveAndFlush(credential);
			}catch(Exception e){
			
			if(e.getMessage() == "No value present") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			
			 throw new CustomException("verify your fields"); 
		}

}
	}
	
	public void deleteCredentials(Long id) {
		if(id <=0 || id==null) {
			throw new CustomException("invalid id");
		}
		else {
			try{

				credentialrepo.deleteById(id);
				
			}catch(Exception e){
			
			if(e.getMessage() == "No class") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			 throw new CustomException("resource doesn t not exist"); 
		}
		
	}}


	public List<Credentials> getCredentialsByaccountId(Long id){
		List<Credentials>credentials;
		
		 if (id <= 0) {
	throw new CustomException("invalid id");
	}

	else {
		try{

			credentials =  credentialrepo.findByAccountId(id);
			return credentials;
		}catch(Exception e){
		
		if(e.getMessage() == "No value present") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("verfiy your Category id"); 
	}

	}
	}




	public <Optional> Credentials getCredentialsByid(Long id) {
		// TODO Auto-generated method stub
		Credentials credentials;
		
				 if (id <= 0) {
			 throw new CustomException("invalid id");
		}
		
		else {
				try{

					credentials=  credentialrepo.findById(id).get();
					return credentials;
				}catch(Exception e){
				
				if(e.getMessage() == "No value present") {
					throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
				}
				 throw new CustomException("ma famech"); 
			}
			
		}
			
	}












}	
