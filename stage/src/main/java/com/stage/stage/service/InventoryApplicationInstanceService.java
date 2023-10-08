package com.stage.stage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.CoreAccountModule;
import com.stage.stage.entity.InventoryApplicationInstances;
import com.stage.stage.repository.InventoryApplicationInstancesRepository;
import com.stage.stage.repository.InventoryApplicationRepository;
import com.stage.stage.repository.InventoryInstanceRepository;
import com.stage.stage.validators.Validators;

@Service
public class InventoryApplicationInstanceService {
private InventoryApplicationInstancesRepository appinstancerepo;
private  InventoryApplicationRepository apprepo;
private InventoryInstanceRepository inrepo;
@Autowired
public InventoryApplicationInstanceService(InventoryApplicationInstancesRepository appinstancerepo,
		InventoryApplicationRepository apprepo, InventoryInstanceRepository inrepo) {
	super();
	this.appinstancerepo = appinstancerepo;
	this.apprepo = apprepo;
	this.inrepo = inrepo;}


public InventoryApplicationInstances createApplicationINstance(InventoryApplicationInstances appinstance) {
	
	Long applicationid= appinstance.getApplication().getId();
	Long instanceid=appinstance.getInstances().getId();

	
	if(appinstance.getApplication()==null) {
		throw new CustomException("you should provide at least an application id ");
	}
	else if(applicationid==null) {
		throw new CustomException("verify your application iD");
	}
	else if(instanceid==null) {
		throw new CustomException("verify your modi=ul iD");
	}
	else if((appinstance.getApplication().getId()<=0) ) {
		throw new CustomException("please verify account id");
	}
	else if((appinstance.getInstances()==null)) {
		throw new CustomException("you should provide at least module id");
	}
	else if((appinstance.getInstances().getId()<=0)) {
		throw new CustomException("please verify account id");
	}
	
	try {apprepo.findById(applicationid);
	inrepo.findById(instanceid);
	return appinstancerepo.save(appinstance);
	}catch(Exception e){
			
			if(e.getMessage() == "could not execute statement") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			
			 throw new CustomException("verify your fields"); }}


public void deleteApplicationInstance(Long id) {
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}
	else {
		try{

			appinstancerepo.deleteById(id);
			
		}catch(Exception e){
		
		if(e.getMessage() == "No class") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("resource doesn t not exist"); 
	}
	
}
	
}
public List<Map<String,Object>> getApplicationInstanceByApplicationId(Long id){
	List<Map<String,Object>> applicationinstances;
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}

	applicationinstances=appinstancerepo.getInstanceByapplicationId(id);
		if(applicationinstances==null) {
			throw new CustomException("no resource found");
		}
	
	
	return applicationinstances;
}


public List<Map<String,Object>> getApplicationInstanceByinstanceId(Long id){
	List<Map<String,Object>> applicationinstances;
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}

	applicationinstances=appinstancerepo.getApplicationByintsanceId(id);
		if(applicationinstances==null) {
			throw new CustomException("no resource found");
		}
	
	
	return applicationinstances;
}






public InventoryApplicationInstances getApplicationInstanceById(Long id)
{
	InventoryApplicationInstances appinstance;
	appinstance=appinstancerepo.findById(id).get();
	
	return appinstance;}
public InventoryApplicationInstances updateAppInstance(InventoryApplicationInstances appinstance,Long id) {
	InventoryApplicationInstances  appinstance1;
	
	if(id <=0 ) {
		throw new CustomException("invalid id");
	}	
	else {
		try{

			appinstance1  =appinstancerepo.findById(id).get();
			
			 
			
			
			if(appinstance.getStart_date()==null) {
				appinstance1.setStart_date(appinstance1.getStart_date());}
			
			if(appinstance.getEnd_date()==null) {
				appinstance1.setEnd_date(appinstance1.getEnd_date());
			}
			else {
				appinstance1.setEnd_date(appinstance.getEnd_date());
			}
			appinstance1.setInstances(appinstance.getInstances());
			
		appinstance1.setStatus(appinstance.getStatus());
			
			return appinstancerepo.saveAndFlush(appinstance1);
		}catch(Exception e){
		
		if(e.getMessage() == "No value present") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		
		 throw new CustomException("verify your fields"); }}
	}








}
