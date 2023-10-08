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
import com.stage.stage.entity.Categories;
import com.stage.stage.entity.InventoryApplicationVersion;
import com.stage.stage.entity.InventoryServerVersion;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.InventoryServerVersionService;
import com.stage.stage.validators.Validators;
@CrossOrigin("*")
@Validated
@RestController
@RequestMapping("/api")
public class InventoryServerVersionController {
	private InventoryServerVersionService serverversionservice;
@Autowired
	public InventoryServerVersionController(InventoryServerVersionService serverversionservice) {
		super();
		this.serverversionservice = serverversionservice;
	}
@GetMapping("/serverVersions/{id}")
public CoreAcountResponse getServerVersionByserverId( @Valid @PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	int total=0;
	 List<InventoryServerVersion> serverversion=serverversionservice.getServerVesrionByserverid(id1);
	 for( InventoryServerVersion temp :serverversion) {
		 total=total+1;
	 }
	return new CoreAcountResponse("sucess",serverversion,total);
	}

@PostMapping("/createServerVersions")
public CoreAcountResponse createServerVersion(@Valid @RequestBody InventoryServerVersion serverversion) {
	
	InventoryServerVersion serverversion1 = serverversionservice.createserevrVersion(serverversion);
	
	return new CoreAcountResponse("success", serverversion1,1);}

@PutMapping("/updateServerVersion/{id}")	
public CoreAcountResponse updateServerVersion(@Valid @RequestBody InventoryServerVersion version, @PathVariable  String id) {
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;
	InventoryServerVersion version1=serverversionservice.updateServerVersion(version,id1);
	return new CoreAcountResponse("sucess",version1,1);
}

@DeleteMapping("/deleteServerVersion/{id}")

public DeleteResponse deleteServerVersionById(@Valid @PathVariable(name="id") String id) {
	boolean val = Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid id type error");
	}
	Long id1=Long.parseLong(id)  ;
	serverversionservice.deleteServerVersion(id1);
	return new  DeleteResponse("success","Media deleted successfully");
}

@GetMapping("/serverVersion/{id}")
public CoreAcountResponse getServerVersionById(@Valid @PathVariable(name="id") String id){
	
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;
	InventoryServerVersion serverversion = serverversionservice.findserverversionById(id1);
	return new CoreAcountResponse("sucess", serverversion,1);
}







}
