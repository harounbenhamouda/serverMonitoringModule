package com.stage.stage.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.Core_Accounts;
import com.stage.stage.entity.Modules;
import com.stage.stage.repository.ModuleRepository;
import com.stage.stage.validators.Validators;

@Service
public class ModuleService {
	private ModuleRepository modulerepo;
	@Autowired

	public ModuleService(ModuleRepository modulerepo) {
		super();
		this.modulerepo = modulerepo;
	}
    
	
	
	public Modules createModule(Modules module) {
		
		
		return modulerepo.save(module);
	}
	

	public List<Modules> getAllModules() {
		// TODO Auto-generated method stub
		return modulerepo.findAll();
	}

	
	public void deleteModule(Long id) {
		if(id <=0 || id==null) {
			throw new CustomException("invalid id");
		}
		else {
			try{

				 modulerepo.deleteById(id);
				
			}catch(Exception e){
			
			if(e.getMessage() == "No class") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			 throw new CustomException("resource doesn t not exist"); 
		}
		
	}
		
		
	}
	@Transactional
	
	public Modules updateModule(Modules module,Long id) {
		Modules module1;
		
		if(id <=0 ) {
			throw new CustomException("invalid id");
		}
		
		
		
		else {
			try{

				module1 =  modulerepo.findById(id).get();
				
				
				
				if(module.getStart_date()==null) {
					module.setStart_date(module1.getStart_date());
				}
				return modulerepo.saveAndFlush(module);
			}catch(Exception e){
			
			if(e.getMessage() == "No value present") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			
			 throw new CustomException("ma famech"); 
		}
		
	}
	
		
		
	
	}
	
	
	public <Optional> Modules findModuleByid(Long id) {
		// TODO Auto-generated method stub
	Modules module;
		
				 if (id <= 0) {
			 throw new CustomException("invalid id");
		}
		
		else {
				try{

				module =  modulerepo.findById(id).get();
					return module;
				}catch(Exception e){
				
				if(e.getMessage() == "No value present") {
					throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
				}
				 throw new CustomException("no resource found"); 
			}
			
		}
			
	}

	
	
	
	
	
	
	
	
	
	
}
