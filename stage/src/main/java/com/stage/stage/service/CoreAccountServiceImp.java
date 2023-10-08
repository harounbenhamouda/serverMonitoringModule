package com.stage.stage.service;

import java.time.temporal.ValueRange;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stage.stage.Exception.CustomException;
import com.stage.stage.Exception.ExceptionResponse;
import com.stage.stage.entity.Core_Accounts;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.repository.CoreAccountsRepository;
import com.stage.stage.validators.Validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;
@Service
public class CoreAccountServiceImp  implements ServiceInterface{
private CoreAccountsRepository car;
@Autowired
	public CoreAccountServiceImp(CoreAccountsRepository car) {
	super();
	this.car = car;
}
	
	@Override
	public Core_Accounts createAccount(Core_Accounts coreaccount) {
		
		return car.save(coreaccount);
	
	}
	@Override
	public List<Core_Accounts> getAllAccounts() {
		// TODO Auto-generated method stub
		return car.findAll();
	}

	
	@Override
	public void deleteAccount(Long id) {
		if(id <=0 || id==null) {
			throw new CustomException("invalid id");
		}
		else {
			try{

				 car.deleteById(id);
				
			}catch(Exception e){
			
			if(e.getMessage() == "No class") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			 throw new CustomException("resource doesn t not exist"); 
		}
		
	}
		
		
	}
	@Transactional
	@Override
	public Core_Accounts updateAccount(Core_Accounts coreaccount,Long id) {
		Core_Accounts coreaccount1;
		
		if(id <=0 ) {
			throw new CustomException("invalid id");
		}
		
		
		
		else {
			try{

				coreaccount1 =  car.findById(id).get();
				if(coreaccount.getName()==null) {
					coreaccount.setName(coreaccount1.getName());
			
				}
				
				if(Validators.MenichString(coreaccount.getStatus())==false) {
					throw new CustomException("not a valid parametre");
				}
				if(coreaccount.getStart_date()==null) {
					coreaccount.setStart_date(coreaccount1.getStart_date());
				}
				return car.saveAndFlush(coreaccount);
			}catch(Exception e){
			
			if(e.getMessage() == "No value present") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			
			 throw new CustomException("ma famech"); 
		}
		
	}
	
		
		
	
	}
	
	@Override
	public <Optional> Core_Accounts findAccountByid(Long id) {
		// TODO Auto-generated method stub
		Core_Accounts account;
		
				 if (id <= 0) {
			 throw new CustomException("invalid id");
		}
		
		else {
				try{

					account =  car.findById(id).get();
					return account;
				}catch(Exception e){
				
				if(e.getMessage() == "No value present") {
					throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
				}
				 throw new CustomException("ma famech"); 
			}
			
		}
			
	}
	
	
	public List<Map<String,Object>> getActivatedUseInstances(Long user_id,Long account_id){
		
		 if ((user_id <= 0)|(account_id<=0)) {
			 throw new CustomException("invalid id");
		}
		 else {
		return car.showActivatedInstances(user_id,account_id);}
	}
	
	
	
	
	
	
	
	
	
	
	

}
