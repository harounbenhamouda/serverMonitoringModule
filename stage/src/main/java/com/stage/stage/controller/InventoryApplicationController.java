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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.CoreAccountModule;
import com.stage.stage.entity.InventoryApplication;
import com.stage.stage.entity.InventoryApplicationSources;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.InventoryApplicationService;
import com.stage.stage.validators.Validators;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@Validated
public class InventoryApplicationController {
private InventoryApplicationService applicationservice;
@Autowired
public InventoryApplicationController(InventoryApplicationService applicationservice) {
	super();
	this.applicationservice = applicationservice;
}

@PostMapping("/createApplication")
public CoreAcountResponse createApplication(@Valid @RequestParam 
String app,@RequestParam("file") MultipartFile
		file)throws Exception {
	
	InventoryApplication application1 = applicationservice.createApplication(app,file);
	
	return new CoreAcountResponse("success", application1,1);
}
@PostMapping("/createApplication1")
public CoreAcountResponse createApplication1(@Valid  @RequestParam 
		String app) throws Exception {
	
	InventoryApplication application1 = applicationservice.createApplication1(app);
	
	return new CoreAcountResponse("success", application1,1);
}

@GetMapping("/applications/{id}")
public CoreAcountResponse getApplicationyAccountId( @Valid @PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	int total=0;
	 List<InventoryApplication> application=applicationservice.getApplicationByAccountId(id1);
	 for(InventoryApplication temp :application) {
		 total=total+1;
	 }
	return new CoreAcountResponse("sucess",application,total);
	
}

@GetMapping("/application/{id}")
public CoreAcountResponse getApplication( @Valid @PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	
	 InventoryApplication application=applicationservice.findapplicationByid(id1);
	 
	return new CoreAcountResponse("sucess",application,1);
	}


@DeleteMapping("/deleteApplication/{id}")

public DeleteResponse deleteSourceById(@Valid @PathVariable(name="id") String id) {
	boolean val = Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid id type error");
	}
	Long id1=Long.parseLong(id)  ;
	
	applicationservice.deleteApplication(id1);
return new  DeleteResponse("success","Media deleted successfully");
}

@PutMapping("/updateApplication/{id}")	
public CoreAcountResponse updateSource(@Valid @RequestBody InventoryApplication app, @PathVariable  String id) {
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;
	InventoryApplication app1=applicationservice.updateInventoryApplication(app, id1);
	return new CoreAcountResponse("sucess",app1,1);
}
@PutMapping("/updateApplication2/{id}")
public CoreAcountResponse modifier(@RequestParam String app, 
@RequestParam("file") MultipartFile file,
@PathVariable("id") Long id) throws Exception {
	InventoryApplication application1 =applicationservice.updateApplication2(app, id, file);
	return new CoreAcountResponse("sucess",application1,1);
}



}
