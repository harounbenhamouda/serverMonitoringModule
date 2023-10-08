package com.stage.stage.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.InventoryApplicationSources;
import com.stage.stage.entity.InventoryApplicationVersion;
import com.stage.stage.repository.InventoryApplicationRepository;
import com.stage.stage.repository.InventoryAppllicationVersionRepository;

@Service
public class InventoryApplicationVersionService {
private InventoryAppllicationVersionRepository versionrepository;
private InventoryApplicationRepository applicationrepository;
@Autowired
public InventoryApplicationVersionService(InventoryAppllicationVersionRepository versionrepository,
		InventoryApplicationRepository applicationrepository) {
	super();
	this.versionrepository = versionrepository;
	this.applicationrepository = applicationrepository;
}


public void deleteVersion(Long id) {
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}
	else {
		try{

			 versionrepository.deleteById(id);
			
		}catch(Exception e){
		
		if(e.getMessage() == "No class") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("resource doesn t not exist"); 
	}}
	
}




public InventoryApplicationVersion createVersion( InventoryApplicationVersion version) {
	
Long applicationid= version.getApplication().getId();


	if(version.getApplication()==null) {
		throw new CustomException("you should provide at least an account id ");
	} 
	else if(applicationid==null) {
		throw new CustomException("your account id is null ");
	}
	else if(applicationid<=0)  {
		throw new CustomException("please verify account id");
	}
	versionrepository.desctivatOldversion(version.getApplication().getId());
	
	try {applicationrepository.findById(applicationid);
	return versionrepository.save(version);
	}catch(Exception e){
			
			if(e.getMessage() == "could not find") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			
			 throw new CustomException("verify your application id fields"); }	}





public List<InventoryApplicationVersion> getVesrionByApplicationid(Long id){
	List<InventoryApplicationVersion>version;
	
	 if (id <= 0) {
throw new CustomException("invalid id");
}

else {
	try{

		version=versionrepository.findByApplicationId(id);
		return 	version;
	}catch(Exception e){
	
	if(e.getMessage() == "No value present") {
		throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
	}
	 throw new CustomException("verfiy your account id"); 
}}}
public void deleteApplicationVersion(Long id) {
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}
	else {
		try{

			 versionrepository.deleteById(id);
			
		}catch(Exception e){
		
		if(e.getMessage() == "No class") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("resource doesn t not exist"); 
	}
	
}}
public <Optional> InventoryApplicationVersion findVersionById(Long id) {
	// TODO Auto-generated method stub
	InventoryApplicationVersion version;
	
			 if (id <= 0) {
		 throw new CustomException("invalid id");
	}
	
	else {
			try{

				version=  versionrepository.findById(id).get();
				return version;
			}catch(Exception e){
			
			if(e.getMessage() == "No value present") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			 throw new CustomException("no resource found"); }
		}}


public InventoryApplicationVersion updateVersion(InventoryApplicationVersion version,Long id) {
	InventoryApplicationVersion version1;
	
	if(id <=0 ) {
		throw new CustomException("invalid id");
	}
	
	
	
	else {
		try{

			version1 =  versionrepository.findById(id).get();
			
			
			
			if(version.getStart_date()==null) {
				version1.setStart_date(version1.getStart_date());
			}
			
			if(version.getEnd_date()==null) {
				version1.setEnd_date(version1.getEnd_date());
			}
			else {
				version1.setEnd_date(version.getEnd_date());
			}
		version1.setVersion(version.getVersion());
			
			version1.setStatus(version.getStatus());
			return versionrepository.saveAndFlush(version1);
		}catch(Exception e){
		
		if(e.getMessage() == "No value present") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		
		 throw new CustomException("ma famech"); 
	}
	
}
}
}
