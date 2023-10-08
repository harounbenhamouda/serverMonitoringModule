package com.stage.stage.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.CoreAccountModule;
import com.stage.stage.entity.CoreUserInstance;
import com.stage.stage.entity.Logs;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.service.LogsService;
import com.stage.stage.validators.Validators;

@RequestMapping("/api")
@RestController
@Validated
public class LogsController {
private LogsService  logservice;
@Autowired
public LogsController(LogsService logservice) {
	super();
	this.logservice = logservice;
}
@GetMapping("/logs/{id}")
public CoreAcountResponse getLogsByUserId( @Valid @PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	int total=0;
	 List<Logs> log=logservice.getLogsByUserId(id1);
	 for(Logs temp :log) {
		 total=total+1;
	 }
	return new CoreAcountResponse("sucess",log,total);	}
@GetMapping("/log/{id}")
public CoreAcountResponse getLogsById( @Valid @PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	
	 Logs log=logservice.getLogsById(id1);
	
	return new CoreAcountResponse("sucess",log,1);	}

@PostMapping("/createLogs")
public CoreAcountResponse createModule(@Valid @RequestBody Logs log) {
	
	Logs log1 = logservice.createLOgs(log);
	
	return new CoreAcountResponse("success", log1,1);
}

}
