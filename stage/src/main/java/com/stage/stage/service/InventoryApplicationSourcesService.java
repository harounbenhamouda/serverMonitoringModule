package com.stage.stage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.InventoryApplication;
import com.stage.stage.entity.InventoryApplicationSources;
import com.stage.stage.repository.CoreAccountsRepository;
import com.stage.stage.repository.InventoryApplicationRepository;
import com.stage.stage.repository.InventoryApplicationSourcesRepository;
@Service
public class InventoryApplicationSourcesService {
	
	
	private InventoryApplicationSourcesRepository sourcerepository;
	private InventoryApplicationRepository applicationrepository;
	@Autowired
	public InventoryApplicationSourcesService(InventoryApplicationSourcesRepository sourcerepository,
			InventoryApplicationRepository applicationrepository) {
		super();
		this.sourcerepository = sourcerepository;
		this.applicationrepository = applicationrepository;
	}


	
	public void deleteSource(Long id) {
		if(id <=0 || id==null) {
			throw new CustomException("invalid id");
		}
		else {
			try{

				 sourcerepository.deleteById(id);
				
			}catch(Exception e){
			
			if(e.getMessage() == "No class") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			 throw new CustomException("resource doesn t not exist"); 
		}}
		
	}


	

	public InventoryApplicationSources createSource(InventoryApplicationSources source) {
		
	Long applicationid= source.getApplication().getId();


		if(source.getApplication()==null) {
			throw new CustomException("you should provide at least an account id ");
		} 
		else if(applicationid==null) {
			throw new CustomException("your account id is null ");
		}
		else if(applicationid<=0)  {
			throw new CustomException("please verify account id");
		}
		
		
		try {applicationrepository.findById(applicationid);
		return sourcerepository.save(source);
		}catch(Exception e){
				
				if(e.getMessage() == "could not find") {
					throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
				}
				
				 throw new CustomException("verify your application id fields"); }	}


	


	public List<InventoryApplicationSources> getSourceByApplicationid(Long id){
		List<InventoryApplicationSources>source;
		
		 if (id <= 0) {
	throw new CustomException("invalid id");
	}

	else {
		try{

			source = sourcerepository.findByApplicationId(id);
			return 	source;
		}catch(Exception e){
		
		if(e.getMessage() == "No value present") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("verfiy your account id"); 
	}}}
	public void deleteApplication(Long id) {
		if(id <=0 || id==null) {
			throw new CustomException("invalid id");
		}
		else {
			try{

				 sourcerepository.deleteById(id);
				
			}catch(Exception e){
			
			if(e.getMessage() == "No class") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			 throw new CustomException("resource doesn t not exist"); 
		}
		
	}}
	public <Optional> InventoryApplicationSources findSourceByid(Long id) {
		// TODO Auto-generated method stub
		InventoryApplicationSources  sources;
		
				 if (id <= 0) {
			 throw new CustomException("invalid id");
		}
		
		else {
				try{

					sources =  sourcerepository.findById(id).get();
					return sources;
				}catch(Exception e){
				
				if(e.getMessage() == "No value present") {
					throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
				}
				 throw new CustomException("no resource found"); }
			}}


	public InventoryApplicationSources updateSources(InventoryApplicationSources source,Long id) {
		InventoryApplicationSources source1;
		
		if(id <=0 ) {
			throw new CustomException("invalid id");
		}
		
		
		
		else {
			try{

				source1 =  sourcerepository.findById(id).get();
				
				
				
				if(source.getStart_date()==null) {
					source1.setStart_date(source1.getStart_date());
				}
				if(source.getEnd_date()==null) {
					source1.setEnd_date(source1.getEnd_date());
				}
				else {
					source1.setEnd_date(source.getEnd_date());
				}
				if(source.getClasse()==0) {
					source1.setClasse(source1.getClasse());
				}
				else {
				source1.setClasse(source.getClasse());}
			source1.setSource_build(source.getSource_build());
				source1.setUrl(source.getUrl());
				source1.setSource_account(source.getSource_account());
				source1.setStatus(source.getStatus());
				return sourcerepository.saveAndFlush(source1);
			}catch(Exception e){
			
			if(e.getMessage() == "No value present") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			
			 throw new CustomException("ma famech"); 
		}
		
	}
}}

	

	






