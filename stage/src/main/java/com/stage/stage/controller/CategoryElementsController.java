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
import com.stage.stage.entity.CategoriesElements;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.CategoriesElementsService;
import com.stage.stage.validators.Validators;

@Validated
@RestController
@RequestMapping("/api")
public class CategoryElementsController {
private CategoriesElementsService  categoryelemservice;
@Autowired
public CategoryElementsController(CategoriesElementsService categoryelmservice) {
	super();
	this.categoryelemservice = categoryelmservice;
}

@GetMapping("/categoryElements/{id}")
public CoreAcountResponse getCategoryElementId( @Valid @PathVariable(name="id") String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;

	int total=0;
	 List<CategoriesElements> categoryelem=categoryelemservice.getCategoriesByCategoryId(id1);
	 for(CategoriesElements temp :categoryelem) {
		 total=total+1;
	 }
	return new CoreAcountResponse("sucess",categoryelem,total);
	
}

@PostMapping("/createCategoryElement")
public CoreAcountResponse createCategory(@Valid @RequestBody CategoriesElements categoryelem) {
	
	CategoriesElements categoryelem1 = categoryelemservice.createCategoryEement(categoryelem);
	
	return new CoreAcountResponse("success", categoryelem1,1);
}

@PutMapping("/updateCategoryElements/{id}")
public CoreAcountResponse editUserSecurity(@Valid @RequestBody CategoriesElements categoryelem ,@Valid @PathVariable(name="id")String id){
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id);
	
	
	categoryelem.setId(id1);
	CategoriesElements categoryelem1= categoryelemservice.updateCategoryElem(categoryelem, id1);
  return new CoreAcountResponse("sucess", categoryelem1,1);
}

@DeleteMapping("/deleteCategoryElem/{id}")

public DeleteResponse deleteCategoryElemById(@Valid @PathVariable(name="id") String id) {
	boolean val = Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid id type error");
	}
	Long id1=Long.parseLong(id)  ;
	
	categoryelemservice.deleteCategoryElement(id1);
	return new  DeleteResponse("success","Media deleted successfully");
}

@GetMapping("/categoryElement/{id}")
public CoreAcountResponse getUsersById(@Valid @PathVariable(name="id") String id){
	
	boolean val =Validators.MenichInt(id);
	if(val == false) {
		throw new CustomException("invalid type error");
	}
	Long id1=Long.parseLong(id)   ;
CategoriesElements categoryelem = categoryelemservice.findCatgoryElementByid(id1);
	return new CoreAcountResponse("sucess", categoryelem,1);
}



}
