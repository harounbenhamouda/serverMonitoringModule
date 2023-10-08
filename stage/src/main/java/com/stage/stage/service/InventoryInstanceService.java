package com.stage.stage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.Categories;
import com.stage.stage.entity.InventoryInstance;
import com.stage.stage.repository.InventoryInstanceRepository;

@Service
public class InventoryInstanceService {
private InventoryInstanceRepository instanceRepo;
@Autowired
public InventoryInstanceService(InventoryInstanceRepository instanceRepo) {
	super();
	this.instanceRepo = instanceRepo;
}
public List<InventoryInstance> getAllinstances(Long id) {
	// TODO Auto-generated method stub
	List<InventoryInstance> instance;
	
	 if (id <= 0) {
throw new CustomException("invalid id");
}

else {
	try{

		instance =  instanceRepo.findByAccountId(id);
		return instance;
	}catch(Exception e){
	
	if(e.getMessage() == "No value present") {
		throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
	}
	 throw new CustomException("ma famech"); 
}

}
	
}
public <Optional> InventoryInstance findinstanceByid(Long id) {
	// TODO Auto-generated method stub
	InventoryInstance instance;
	
			 if (id <= 0) {
		 throw new CustomException("invalid id");
	}
	
	else {
			try{

				instance =  instanceRepo.findById(id).get();
				return instance;
			}catch(Exception e){
			
			if(e.getMessage() == "No value present") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			 throw new CustomException("ma famech"); 
		}
		
	}
		
}

public void deleteInstance(Long id) {
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}
	else {
		try{

			instanceRepo.deleteById(id);
			
		}catch(Exception e){
		
		if(e.getMessage() == "No class") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("resource doesn t not exist"); 
	}
	
}		
}

public InventoryInstance createInstance(InventoryInstance instance) {
	
	
	return instanceRepo.save(instance);
	
	
	
	
	
	
	
}








}
