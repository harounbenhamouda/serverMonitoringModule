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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.InventoryApplicationSources;
import com.stage.stage.entity.InventoryApplicationVersion;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.InventoryApplicationVersionService;
import com.stage.stage.validators.Validators;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@Validated
public class InventoryApplicationVersionController {
private InventoryApplicationVersionService vesrionservice;
@Autowired
public InventoryApplicationVersionController(InventoryApplicationVersionService vesrionservice) {
	super();
	this.vesrionservice = vesrionservice;
}
@PostMapping("/createVersion")
public CoreAcountResponse createversion(@Valid @RequestBody InventoryApplicationVersion version) {
	InventoryApplicationVersion version1=vesrionservice.createVersion(version);
	return new CoreAcountResponse("success",version1,1);
}
	
@GetMapping("/versions/{id}")
public CoreAcountResponse getVersionbyAccountId( @Valid @PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	int total=0;
	 List<InventoryApplicationVersion> versions=vesrionservice.getVesrionByApplicationid(id1);
	 for(InventoryApplicationVersion temp :versions) {
		 total=total+1;
	 }
	return new CoreAcountResponse("sucess",versions,total);
	
}

@GetMapping("/version/{id}")
public CoreAcountResponse getVersion( @Valid @PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	
	 InventoryApplicationVersion version=vesrionservice.findVersionById(id1);
	 
	return new CoreAcountResponse("sucess",version,1);
	}

	
@PutMapping("/updateVersion/{id}")	
public CoreAcountResponse updateVersion(@Valid @RequestBody InventoryApplicationVersion version, @PathVariable  String id) {
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;
	InventoryApplicationVersion version1=vesrionservice.updateVersion(version,id1);
	return new CoreAcountResponse("sucess",version1,1);
}
@DeleteMapping("/deleteVersion/{id}")

public DeleteResponse deleteSourceById(@Valid @PathVariable(name="id") String id) {
	boolean val = Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid id type error");
	}
	Long id1=Long.parseLong(id)  ;
	
	vesrionservice.deleteApplicationVersion(id1);
return new  DeleteResponse("success","Media deleted successfully");
}	










}
