package com.stage.stage.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.Categories;
import com.stage.stage.entity.Core_Accounts;
import com.stage.stage.repository.CategoriesRepository;
import com.stage.stage.repository.CoreAccountsRepository;
import com.stage.stage.validators.Validators;

@Service
public class CategoryService {
private CategoriesRepository categrepo;
private CoreAccountsRepository car;
@Autowired
public CategoryService(CategoriesRepository categrepo, CoreAccountsRepository car) {
	super();
	this.categrepo = categrepo;
	this.car = car;
}



public Categories createCategory(Categories category) {
	
Long accountid= category.getAccount().getId();


	if(category.getAccount()==null) {
		throw new CustomException("you should provide at least an account id ");
	} 
	else if(accountid==null) {
		throw new CustomException("your account id is null ");
	}
	else if((category.getAccount().getId()<=0) ) {
		throw new CustomException("please verify account id");
	}
	
	
	try {car.findById(accountid);
	return categrepo.save(category);
	}catch(Exception e){
			
			if(e.getMessage() == "could not find") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			
			 throw new CustomException("verify your account id fields"); }
		
	
	

}
public List<Categories> getAllCategories() {
	// TODO Auto-generated method stub
	return categrepo.findAll();
}
public List<Categories> getCategoriesByAccountId(Long id){
	List<Categories >category;
	
	 if (id <= 0) {
throw new CustomException("invalid id");
}

else {
	try{

		category =  categrepo.findByaccountId(id);
		return category;
	}catch(Exception e){
	
	if(e.getMessage() == "No value present") {
		throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
	}
	 throw new CustomException("verfiy your account id"); 
}

}
}



public void deleteCategory(Long id) {
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}
	else {
		try{

			 categrepo.deleteById(id);
			
		}catch(Exception e){
		
		if(e.getMessage() == "No class") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("resource doesn t not exist"); 
	}
	
}		
}
@Transactional

public Categories updateCategory(Categories category,Long id) {
	Categories category1;
	
	if(id <=0 ) {
		throw new CustomException("invalid id");
	}
	
	
	
	else {
		try{

			category1 =  categrepo.findById(id).get();
			if(category.getName()==null) {
				category.setName(category1.getName());
		
			}
			
			
			if(category.getStatus()==0) {
				category.setStatus(category1.getStatus());
			}
			
			if(category.getClasse()==0) {
				category.setClasse(category1.getClasse());
			}
			
			
			
			return categrepo.saveAndFlush(category);
		}catch(Exception e){
		
		if(e.getMessage() == "No value present") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		
		 throw new CustomException("resource does not exist"); 
	}
	
}
}


public <Optional> Categories findCatgoryByid(Long id) {
	// TODO Auto-generated method stub
	Categories category;
	
			 if (id <= 0) {
		 throw new CustomException("invalid id");
	}
	
	else {
			try{

				category =  categrepo.findById(id).get();
				return category;
			}catch(Exception e){
			
			if(e.getMessage() == "No value present") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			 throw new CustomException("ma famech"); 
		}
		
	}
		
}













}
