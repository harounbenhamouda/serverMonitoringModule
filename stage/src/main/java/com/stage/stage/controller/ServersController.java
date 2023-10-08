package com.stage.stage.controller;

import java.util.Date;
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

import com.stage.stage.Dto.ServerInstanceDto;
import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.InventoryInstance;
import com.stage.stage.entity.InventoryServers;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.InventoryServersService;
import com.stage.stage.validators.Validators;
@CrossOrigin("*")
@Validated
@RestController
@RequestMapping("/api")
public class ServersController {
private  InventoryServersService serverservice;
@Autowired
public ServersController(InventoryServersService serverservice) {
	super();
	this.serverservice = serverservice;
}
@GetMapping("/servers/{id}")
public CoreAcountResponse getserverbyInstanceId( @Valid @PathVariable(name="id") String id){
	
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	
	 InventoryServers server=serverservice.getServersByInstanceId(id1);
	 
	return new CoreAcountResponse("sucess", server,1);}




@GetMapping("/server/{id}")
public CoreAcountResponse getServerById(@Valid @PathVariable(name="id") String id){
	
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;
	List<Map<String,Object>>server = serverservice.findserverByInstanceIdd(id1);
	return new CoreAcountResponse("sucess",server,1);
}


@PostMapping("/createServer")
public CoreAcountResponse createInstance(@Valid @RequestBody ServerInstanceDto serverinstance) {
	
	InventoryServers server1 = serverservice.createServer(serverinstance);
	
	return new CoreAcountResponse("success", server1,1);
}

@DeleteMapping("/deleteServer/{id}")

public DeleteResponse deleteServerById(@Valid @PathVariable(name="id") String id) {
	boolean val = Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid id type error");
	}
	Long id1=Long.parseLong(id)  ;
	
	serverservice.deleteServer(id1);
	return new  DeleteResponse("success","Media deleted successfully");
}
@PutMapping("/updateServer/{id}")
public CoreAcountResponse updateserver(@Valid  @PathVariable String id, @RequestBody ServerInstanceDto serverinstance) {
	
	boolean val = Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid id type error");
	}
	Long id1=Long.parseLong(id)  ;
	InventoryServers server1 = serverservice.updateSerever(serverinstance,id1);
	return new CoreAcountResponse("success", server1,1);
	
}
@GetMapping("/allServers/{id}")
	public CoreAcountResponse getAllserversbyAccountId(@Valid @PathVariable String id) {
		boolean val = Validators.MenichInt(id);
		if(val == false) {
			throw new CustomException("invalid id type error");
		}
		Long id1=Long.parseLong(id);
		List<Map<String,Object>> servers=serverservice.findserversbyAccountId(id1);
		return new CoreAcountResponse("success",servers,1);
	}
}

















