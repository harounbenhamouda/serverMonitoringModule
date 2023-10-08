package com.stage.stage.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.InventoryApplicationInstances;
import com.stage.stage.entity.InventoryApplicationSources;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.InventoryApplicationInstanceService;
import com.stage.stage.validators.Validators;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@Validated
public  class InventoryApplicationInstancesController {
	private InventoryApplicationInstanceService applicationinstanceservice;
@Autowired
	public InventoryApplicationInstancesController(InventoryApplicationInstanceService applicationinstanceservice) {
		super();
		this.applicationinstanceservice = applicationinstanceservice;
	}
	
@PostMapping("/createApplicationInstance")
public CoreAcountResponse createInstance(@Valid @RequestBody InventoryApplicationInstances instance) {
	InventoryApplicationInstances instance1=applicationinstanceservice.createApplicationINstance(instance);
	return new CoreAcountResponse("success",instance1,1);
}
	
@GetMapping("/applicationInstances/{id}")
public CoreAcountResponse getInstancebyApplicationtId( @Valid @PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	int total=0;
	List<Map<String,Object>> instance1=applicationinstanceservice.getApplicationInstanceByApplicationId(id1);
	 
	return new CoreAcountResponse("sucess",instance1,total);
	
}
@GetMapping("/serverInstances/{id}")
public CoreAcountResponse getInstancebyserverInstanceId( @Valid @PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	int total=0;
	List<Map<String,Object>> instance1=applicationinstanceservice.getApplicationInstanceByinstanceId(id1);
	 
	return new CoreAcountResponse("sucess",instance1,total);
	
}

@GetMapping("/applicationInstance/{id}")
public CoreAcountResponse getSource( @Valid @PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	
	InventoryApplicationInstances instance=applicationinstanceservice.getApplicationInstanceById(id1);
	 
	return new CoreAcountResponse("sucess",instance,1);
	}

	
@PutMapping("/updateApplicationInstance/{id}")	
public CoreAcountResponse updateSource(@Valid @RequestBody InventoryApplicationInstances appinstance, @PathVariable  String id) {
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;
	InventoryApplicationInstances instance =applicationinstanceservice.updateAppInstance(appinstance, id1);
	return new CoreAcountResponse("sucess",instance,1);
}
@DeleteMapping("/deleteApplicationInstance/{id}")

public DeleteResponse deleteInstanceById(@Valid @PathVariable(name="id") String id) {
	boolean val = Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid id type error");
	}
	Long id1=Long.parseLong(id)  ;
	
	applicationinstanceservice.deleteApplicationInstance(id1);
return new  DeleteResponse("success","Media deleted successfully");
}		
	
	
}
