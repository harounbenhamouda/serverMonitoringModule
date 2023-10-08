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
import com.stage.stage.entity.InventoryApplication;
import com.stage.stage.entity.InventoryApplicationSources;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.InventoryApplicationSourcesService;
import com.stage.stage.validators.Validators;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@Validated
public class InventoryApplicationSourcesController {
	private InventoryApplicationSourcesService sourceservice;
	@Autowired
	public InventoryApplicationSourcesController(InventoryApplicationSourcesService sourceservice) {
		super();
		this.sourceservice = sourceservice;
	}
@PostMapping("/createSource")
public CoreAcountResponse createsource(@Valid @RequestBody InventoryApplicationSources source) {
	InventoryApplicationSources source1=sourceservice.createSource(source);
	return new CoreAcountResponse("success",source1,1);
}
	
@GetMapping("/sources/{id}")
public CoreAcountResponse getSourcebyAccountId( @Valid @PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	int total=0;
	 List<InventoryApplicationSources> sources=sourceservice.getSourceByApplicationid(id1);
	 for(InventoryApplicationSources temp :sources) {
		 total=total+1;
	 }
	return new CoreAcountResponse("sucess",sources,total);
	
}

@GetMapping("/source/{id}")
public CoreAcountResponse getSource( @Valid @PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	
	 InventoryApplicationSources source=sourceservice.findSourceByid(id1);
	 
	return new CoreAcountResponse("sucess",source,1);
	}

	
@PutMapping("/updateSource/{id}")	
public CoreAcountResponse updateSource(@Valid @RequestBody InventoryApplicationSources source, @PathVariable  String id) {
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;
	InventoryApplicationSources source1=sourceservice.updateSources(source, id1);
	return new CoreAcountResponse("sucess",source1,1);
}
@DeleteMapping("/deleteApplicationSource/{id}")

public DeleteResponse deleteSourceById(@Valid @PathVariable(name="id") String id) {
	boolean val = Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid id type error");
	}
	Long id1=Long.parseLong(id)  ;
	
sourceservice.deleteApplication(id1);
return new  DeleteResponse("success","Media deleted successfully");
}	
	
}
