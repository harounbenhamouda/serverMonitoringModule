package com.stage.stage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.Logs;
import com.stage.stage.entity.Urls;
import com.stage.stage.entity.UserSecurity;
import com.stage.stage.entity.Users;
import com.stage.stage.repository.UrlRepository;

@Service
public class UrlService {
	private UrlRepository urlrepository;
@Autowired
	public UrlService(UrlRepository urlrepository) {
		super();
		this.urlrepository = urlrepository;
	}
	
public  Urls createUrls(Urls url) {
	
	
	 if(  url.getElementid()<=0 ){
		throw new CustomException(" elemt id is not valid");
	}
	 else if ( url.getClasse()<=0 ){
			throw new CustomException(" classe is not valid");
		}
	 
	 else return urlrepository.save(url);
	
	
}
public Urls getUrlById(Long id){
	Urls url;
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}
	else {
		try{

			url=urlrepository.findById(id).get();
			return url;
		}catch(Exception e){
		
		if(e.getMessage() == "No value present") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("ma famech"); 
	}
}	
}
public List<Urls> getUrls() {
	// TODO Auto-generated method stub
	return urlrepository.findAll();
}
public void deleteUrl(Long id) {
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}
	else {
		try{

			 urlrepository.deleteById(id);
			
		}catch(Exception e){
		
		if(e.getMessage() == "No class") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("resource doesn t not exist"); 
	}
	
}
	
}
public Urls updateUrls( Urls url,Long id) {
	Urls url1;
	
	if(id <=0 ) {
		throw new CustomException("invalid id");
	}
	
	
	
	else {
		try{

			url1 =urlrepository.findById(id).get();
						
			
			

			
			if(url.getClasse()==0) {
				url.setClasse(url1.getClasse());
			}
			else if  (url.getElementid()==null) {
				url.setElementid(url1.getElementid());
			}
			else if  (url.getUrl()==null) {
				url.setUrl(url1.getUrl());
			}
			return urlrepository.saveAndFlush(url);
		}catch(Exception e){
		
		if(e.getMessage() == "No value present") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		
		 throw new CustomException("verify your fields"); 
	}
	
	}
	
	}














}
