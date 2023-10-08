package com.stage.stage.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.stage.stage.entity.Urls;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.CategoryService;
import com.stage.stage.service.UrlService;
import com.stage.stage.validators.Validators;

@Validated
@RestController
@RequestMapping("/api")
public class UrlController {
	private UrlService urlservice;

	@Autowired
	public UrlController(UrlService urlservice) {
		super();
		this.urlservice = urlservice;
	}

	@GetMapping("/urls")
	public CoreAcountResponse getUrls(){
		

		int total=0;
		 List<Urls> urls=urlservice.getUrls();
		 for(Urls temp :urls) {
			 total=total+1;
		 }
		return new CoreAcountResponse("sucess",urls,total);
		
	}

	@PostMapping("/createUrl")
	public CoreAcountResponse createUrl(@Valid @RequestBody Urls url) {
		
		Urls url1 = urlservice.createUrls(url);
		
		return new CoreAcountResponse("success", url1,1);
	}

	@PutMapping("/updateUrl/{id}")
	public CoreAcountResponse editUrl(@Valid @RequestBody Urls url ,@Valid @PathVariable(name="id")String id){
		boolean val =Validators.MenichInt(id);
		if(val == false) {
			throw new CustomException("invalid type error");
		}
		Long id1=Long.parseLong(id)  ;
		
		
		url.setId(id1);
		Urls url1= urlservice.updateUrls(url, id1);
	  return new CoreAcountResponse("sucess", url1,1);
	}

	@DeleteMapping("/url/{id}")

	public DeleteResponse deleteUrlById(@Valid @PathVariable(name="id") String id) {
		boolean val = Validators.MenichInt(id);
		if(val == false) {
			throw new CustomException("invalid id type error");
		}
		Long id1=Long.parseLong(id)  ;
		
		urlservice.deleteUrl(id1);
		return new  DeleteResponse("success","Media deleted successfully");
	}

	@GetMapping("/url/{id}")
	public CoreAcountResponse getUrlById(@Valid @PathVariable(name="id") String id){
		
		boolean val =Validators.MenichInt(id);
		if(val == false) {
			throw new CustomException("invalid type error");
		}
		Long id1=Long.parseLong(id)   ;
Urls url = urlservice.getUrlById(id1);
		return new CoreAcountResponse("sucess", url,1);
	}


}
