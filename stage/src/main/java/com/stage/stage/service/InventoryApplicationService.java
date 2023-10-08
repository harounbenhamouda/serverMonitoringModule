package com.stage.stage.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.Categories;
import com.stage.stage.entity.InventoryApplication;
import com.stage.stage.entity.Modules;
import com.stage.stage.repository.CoreAccountsRepository;
import com.stage.stage.repository.InventoryApplicationRepository;

@Service
public class InventoryApplicationService {
private InventoryApplicationRepository  applicationrepository;
private CoreAccountsRepository car;
@Autowired
public InventoryApplicationService(InventoryApplicationRepository applicationrepository, CoreAccountsRepository car) {
	super();
	this.applicationrepository = applicationrepository;
	this.car = car;}


public InventoryApplication createApplication(String application,MultipartFile file) throws IOException {
	
InventoryApplication p =new ObjectMapper().readValue(application,InventoryApplication.class);


	
	String nomImage=saveImage(file);
    p.setLogo(nomImage);
	

	return applicationrepository.save(p);
		}

public InventoryApplication createApplication1(String application) throws IOException{
	InventoryApplication p =new ObjectMapper().readValue(application,InventoryApplication.class);
	Long accountid= p.getAccount().getId();


	if(p.getAccount()==null) {
		throw new CustomException("you should provide at least an account id ");
	} 
	else if(accountid==null) {
		throw new CustomException("your account id is null ");
	}
	else if(accountid<=0)  {
		throw new CustomException("please verify account id");
	}
	
	
	try {car.findById(accountid);
	return applicationrepository.save(p);
	}catch(Exception e){
			
			if(e.getMessage() == "could not find") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			
			 throw new CustomException("verify your application id fields"); }	}
   


	
		


public List<InventoryApplication> getApplicationByAccountId(Long id){
	List<InventoryApplication>application;
	
	 if (id <= 0) {
throw new CustomException("invalid id");
}

else {
	try{

		application =  applicationrepository.findByAccountId(id);
		return 	application;
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

			 applicationrepository.deleteById(id);
			
		}catch(Exception e){
		
		if(e.getMessage() == "No class") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("resource doesn t not exist"); 
	}
	
}}
public <Optional> InventoryApplication findapplicationByid(Long id) {
	// TODO Auto-generated method stub
	InventoryApplication application;
	
			 if (id <= 0) {
		 throw new CustomException("invalid id");
	}
	
	else {
			try{

				application =  applicationrepository.findById(id).get();
				return application;
			}catch(Exception e){
			
			if(e.getMessage() == "No value present") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			 throw new CustomException("no resource found"); }
		}}


public InventoryApplication updateInventoryApplication(InventoryApplication application,Long id) {
	InventoryApplication application1;
	
	if(id <=0 ) {
		throw new CustomException("invalid id");
	}
	
	
	
	else {
		try{

			application1 =  applicationrepository.findById(id).get();
			
			
			
			if(application.getStart_date()==null) {
				application1.setStart_date(application1.getStart_date());
			}
			if(application.getLogo()==null) {
				application1.setLogo(application1.getLogo());
			}
			application1.setClasse(application.getClasse());
			application1.setStatus(application.getStatus());
			application1.setName(application.getName());
			application1.setEnvironment(application.getEnvironment());
			return applicationrepository.saveAndFlush(application1);
		}catch(Exception e){
		
		if(e.getMessage() == "No value present") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		
		 throw new CustomException("ma famech"); 
	}
	
}

	
	

}
public void deleteSource(Long id) {
	if(id <=0 || id==null) {
		throw new CustomException("invalid id");
	}
	else {
		try{

			applicationrepository.deleteById(id);
			
		}catch(Exception e){
		
		if(e.getMessage() == "No class") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("resource doesn t not exist"); 
	}}
	
}



public InventoryApplication updateApplication2(String application, Long idApplication, 
MultipartFile file) throws Exception {
	
	
String fileName=saveImage(file);
InventoryApplication app=new ObjectMapper().readValue(application,InventoryApplication.class);
if(idApplication <=0 ) {
	throw new CustomException("invalid id");
}
else {
	try{

		InventoryApplication	application1 =  applicationrepository.findById(idApplication).get();
		
		
		
		if(app.getStart_date()==null) {
			app.setStart_date(application1.getStart_date());
		}
		

  app.setId(idApplication);
   app.setLogo(fileName);
return applicationrepository.save(app);}
catch(Exception e){
	
	if(e.getMessage() == "No value present") {
		throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
	}
	
	 throw new CustomException("ma famech"); 
}
}

}






private String saveImage(MultipartFile file) throws
IOException {
String filename=file.getOriginalFilename();
String tab[]=filename.split("\\.");
String 
filenameModif=tab[0]+"_"+System.currentTimeMillis()+"."+tab[1];
File f=new
File(System.getProperty("user.home")+"/serverMonitoring/src/assets/logo2/"+filenameModif);
FileOutputStream fos=new FileOutputStream(f);
fos.write(file.getBytes());
//FileUtils.writeByteArrayToFile(f, file.getBytes());
return filenameModif;}




}
