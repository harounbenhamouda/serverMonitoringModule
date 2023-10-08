package com.stage.stage.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.Categories;
import com.stage.stage.entity.InventoryInstance;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.InventoryInstanceService;
import com.stage.stage.validators.Validators;
@CrossOrigin("*")
@Validated
@RestController
@RequestMapping("/api")
public class InventoryIntsanceController {
	private InventoryInstanceService instanceservice;
@Autowired 
	public InventoryIntsanceController(InventoryInstanceService instanceservice) {
		super();
		this.instanceservice = instanceservice;
	}


@GetMapping("/allInstances/{id}")
public CoreAcountResponse getAllInstances(@Valid @PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;
	int total=0;
	 List<InventoryInstance> instance=instanceservice.getAllinstances(id1);
	 for(InventoryInstance temp :instance) {
		 total=total+1;
	 }
	return new CoreAcountResponse("sucess", instance,total);
}


@GetMapping("/inventoryInstance/{id}")
public CoreAcountResponse getInstanceById(@Valid @PathVariable(name="id") String id){
	
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;
InventoryInstance instance = instanceservice.findinstanceByid(id1);
	return new CoreAcountResponse("sucess",instance,1);
}
@PostMapping("/createInventoryInstance")
public CoreAcountResponse createInstance(@Valid @RequestBody InventoryInstance insatnce) {
	
	InventoryInstance instance1 = instanceservice.createInstance(insatnce);
	
	return new CoreAcountResponse("success", instance1,1);
}
@DeleteMapping("/deleteInventoryInstance/{id}")

public DeleteResponse deleteCategoryById(@Valid @PathVariable(name="id") String id) {
	boolean val = Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid id type error");
	}
	Long id1=Long.parseLong(id)  ;
	
	instanceservice.deleteInstance(id1);
	return new  DeleteResponse("success","Media deleted successfully");
}
}
