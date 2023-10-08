package com.stage.stage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.InventoryApplicationVersion;
import com.stage.stage.entity.InventoryServerVersion;
import com.stage.stage.repository.InventoryServerVersonRepository;
import com.stage.stage.repository.InventoryServersRepository;

@Service
public class InventoryServerVersionService {
private InventoryServerVersonRepository serverversionrepository;
private InventoryServersRepository  serverrepository;
public InventoryServerVersionService(InventoryServerVersonRepository serverversionrepository,
		InventoryServersRepository serverrepository) {
	super();
	this.serverversionrepository = serverversionrepository;
	this.serverrepository = serverrepository;
}

public void deleteServerVersion(Long id) {
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}
	else {
		try{

			 serverversionrepository.deleteById(id);
			
		}catch(Exception e){
		
		if(e.getMessage() == "No class") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("resource doesn t not exist"); 
	}}
	
}




public InventoryServerVersion createserevrVersion( InventoryServerVersion version) {
	
Long serverid= version.getServer().getId();


	if(version.getServer()==null) {
		throw new CustomException("you should provide at least an server id ");
	} 
	else if(serverid==null) {
		throw new CustomException("your serverd id is null ");
	}
	else if(serverid<=0)  {
		throw new CustomException("please verify serverd id");
	}
	
	serverversionrepository.desctivatOldversion(serverid,version.getElement());
	try {serverrepository.findById(serverid);
	return serverversionrepository.save(version);
	}catch(Exception e){
			
			if(e.getMessage() == "could not find") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			
			 throw new CustomException("verify your server id fields"); }	}





public List<InventoryServerVersion> getServerVesrionByserverid(Long id){
	List<InventoryServerVersion>serverversion;
	
	 if (id <= 0) {
throw new CustomException("invalid id");
}

else {
	try{

		serverversion=serverversionrepository.findByServerId(id);
		return 	serverversion;
	}catch(Exception e){
	
	if(e.getMessage() == "No value present") {
		throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
	}
	 throw new CustomException("verfiy your account id"); 
}}}

public <Optional> InventoryServerVersion findserverversionById(Long id) {
	// TODO Auto-generated method stub
	InventoryServerVersion serverversion;
	
			 if (id <= 0) {
		 throw new CustomException("invalid id");
	}
	
	else {
			try{

				serverversion=  serverversionrepository.findById(id).get();
				return serverversion;
			}catch(Exception e){
			
			if(e.getMessage() == "No value present") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			 throw new CustomException("no resource found"); }
		}}


public InventoryServerVersion updateServerVersion(InventoryServerVersion version,Long id) {
	InventoryServerVersion version1;
	
	if(id <=0 ) {
		throw new CustomException("invalid id");
	}
	
	
	
	else {
		try{

			version1 =  serverversionrepository.findById(id).get();
			
			
			
			if(version.getStart_date()==null) {
				version1.setStart_date(version1.getStart_date());
			} 
			if(version.getEnd_date()==null) {
				version1.setEnd_date(version1.getEnd_date());
			}
			else {
				version1.setEnd_date(version.getEnd_date());
			}
			if(version.getStatus()==0) {
				version1.setStatus(version1.getStatus());
			}
			else {
				version1.setStatus(version.getStatus());
			}
			if(version.getElement_name()==null) {
				version1.setElement_name(version1.getElement_name());
			}
			else  {
				version1.setElement_name(version.getElement_name());
				
			}
			
			return serverversionrepository.saveAndFlush(version1);
		}catch(Exception e){
		
		if(e.getMessage() == "No value present") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		
		 throw new CustomException("ma famech"); 
	}
	
}
}








}
