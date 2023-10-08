package com.stage.stage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.Categories;
import com.stage.stage.entity.CategoriesElements;
import com.stage.stage.repository.CategoriesElementsRepository;
import com.stage.stage.repository.CategoriesRepository;

@Service
public class CategoriesElementsService {
private CategoriesElementsRepository categelrepo;
private CategoriesRepository   categrepo;
public CategoriesElementsService(CategoriesElementsRepository categelrepo, CategoriesRepository categrepo) {
	super();
	this.categelrepo = categelrepo;
	this.categrepo = categrepo;
}
public CategoriesElements createCategoryEement(CategoriesElements  categoryelement) {
	
Long Categoryid= categoryelement.getCategories().getId();
	

	if(categoryelement.getCategories()==null) {
		throw new CustomException("you should provide at least an category id ");
	} 
	else if(Categoryid==null) {
		throw new CustomException("verify your category id ");
	}
	else if((Categoryid<=0) ) {
		throw new CustomException("please verify account id");
	}
	
	try {categrepo.findById(Categoryid);
	return categelrepo.save(categoryelement);
	}catch(Exception e){
			
			if(e.getMessage() == "could not find ") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			
			 throw new CustomException("verify your fields"); }
			
}
public List<CategoriesElements> getCategoriesByCategoryId(Long id){
	List<CategoriesElements>categoryelment;
	
	 if (id <= 0) {
throw new CustomException("invalid id");
}

else {
	try{

		categoryelment =  categelrepo.findByCategoriesId(id);
		return categoryelment;
	}catch(Exception e){
	
	if(e.getMessage() == "No value present") {
		throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
	}
	 throw new CustomException("verfiy your Category id"); 
}

}
}

public void deleteCategoryElement(Long id) {
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}
	else {
		try{

			 categelrepo.deleteById(id);
			
		}catch(Exception e){
		
		if(e.getMessage() == "No class") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("resource doesn t not exist"); 
	}
	
}}

public <Optional> CategoriesElements findCatgoryElementByid(Long id) {
	// TODO Auto-generated method stub
	CategoriesElements categoryelm;
	
			 if (id <= 0) {
		 throw new CustomException("invalid id");
	}
	
	else {
			try{

				categoryelm=  categelrepo.findById(id).get();
				return categoryelm;
			}catch(Exception e){
			
			if(e.getMessage() == "No value present") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			 throw new CustomException("ma famech"); 
		}
		
	}
		
}

public CategoriesElements updateCategoryElem(CategoriesElements categoryelm,Long id) {
	CategoriesElements categoryelm1;
	
	if(id <=0 ) {
		throw new CustomException("invalid id");
	}
	else {
		try{

			categoryelm1 =  categelrepo.findById(id).get();
			if(categoryelm.getCategories()==null) {
				categoryelm.setCategories(null);
		
			}
			
			
			if(categoryelm.getElement()==0) {
				categoryelm.setElement(categoryelm1.getElement());
			}
			
			if(categoryelm.getElement_id()==null) {
				categoryelm.setElement_id(categoryelm1.getElement_id());
			}
			
			
			
			return categelrepo.saveAndFlush(categoryelm);
		}catch(Exception e){
		
		if(e.getMessage() == "No value present") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		
		 throw new CustomException("ma famech"); 
	}
	
}

	
	

}









}
