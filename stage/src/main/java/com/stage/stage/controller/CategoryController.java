package com.stage.stage.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.stage.stage.entity.UserSecurity;
import com.stage.stage.reponses.CoreAcountResponse;
import com.stage.stage.reponses.DeleteResponse;
import com.stage.stage.service.CategoryService;
import com.stage.stage.validators.Validators;
@Validated
@RestController
@RequestMapping("/api")
public class CategoryController {
	private CategoryService categoryservice;

	public CategoryController(CategoryService categoryservice) {
		super();
		this.categoryservice = categoryservice;
	}
	
	@GetMapping("/categories/{id}")
	public CoreAcountResponse getCategoryAccountId( @Valid @PathVariable(name="id") String id){
		boolean val =Validators.MenichInt(id);
		if(val == false) {
			throw new CustomException("invalid type error");
		}
		Long id1=Long.parseLong(id)   ;

		int total=0;
		 List<Categories> category=categoryservice.getCategoriesByAccountId(id1);
		 for(Categories temp :category) {
			 total=total+1;
		 }
		return new CoreAcountResponse("sucess",category,total);
		
	}

	@PostMapping("/createCategory")
	public CoreAcountResponse createCategory(@Valid @RequestBody Categories category) {
		
		Categories category1 = categoryservice.createCategory(category);
		
		return new CoreAcountResponse("success", category1,1);
	}

	@PutMapping("/updateCategory/{id}")
	public CoreAcountResponse editCategory(@Valid @RequestBody Categories category ,@Valid @PathVariable(name="id")String id){
		boolean val =Validators.MenichInt(id);
		if(val == false) {
			throw new CustomException("invalid type error");
		}
		Long id1=Long.parseLong(id)  ;
		
		
		category.setId(id1);
		Categories category1= categoryservice.updateCategory(category, id1);
	  return new CoreAcountResponse("sucess", category1,1);
	}

	@DeleteMapping("/deleteCategory/{id}")

	public DeleteResponse deleteCategoryById(@Valid @PathVariable(name="id") String id) {
		boolean val = Validators.MenichInt(id);
		if(val == false) {
			throw new CustomException("invalid id type error");
		}
		Long id1=Long.parseLong(id)  ;
		
		categoryservice.deleteCategory(id1);
		return new  DeleteResponse("success","Media deleted successfully");
	}

	@GetMapping("/category/{id}")
	public CoreAcountResponse getUsersById(@Valid @PathVariable(name="id") String id){
		
		boolean val =Validators.MenichInt(id);
		if(val == false) {
			throw new CustomException("invalid type error");
		}
		Long id1=Long.parseLong(id)   ;
	Categories category = categoryservice.findCatgoryByid(id1);
		return new CoreAcountResponse("sucess", category,1);
	}

@GetMapping("/allCategories")
public CoreAcountResponse getAllCategories(){
	
	int total=0;
	 List<Categories> category=categoryservice.getAllCategories();
	 for(Categories temp :category) {
		 total=total+1;
	 }
	return new CoreAcountResponse("sucess", category,total);
}

}
