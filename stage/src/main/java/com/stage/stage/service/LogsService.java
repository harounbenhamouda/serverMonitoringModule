package com.stage.stage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.stage.Exception.CustomException;
import com.stage.stage.entity.CoreUserInstance;
import com.stage.stage.entity.Logs;
import com.stage.stage.entity.UserSecurity;
import com.stage.stage.repository.LogsRepository;
import com.stage.stage.repository.UsersRepository;

@Service
public class LogsService {
	private UsersRepository userRepository;
	private LogsRepository logsrepository;
	@Autowired
	public LogsService(UsersRepository userRepository, LogsRepository logsrepository) {
		super();
		this.userRepository = userRepository;
		this.logsrepository = logsrepository;
	}
	
	public List<Logs> getLogsByUserId(Long id){
		List<Logs> logs;
		if(id <=0 || id==null) {
			throw new CustomException("invalid id");
		}

		logs=logsrepository.findByUsersId(id);
			if(logs==null) {
				throw new CustomException("no resource found");
			}
		
		
		return logs;
	}
	
	public Logs getLogsById(Long id){
		Logs logs;
		if(id <=0 || id==null) {
			throw new CustomException("invalid id");
		}
		else {
			try{

				 logs=logsrepository.findById(id).get();
				return logs;
			}catch(Exception e){
			
			if(e.getMessage() == "No value present") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			 throw new CustomException("ma famech"); 
		}

		
	}
	
	}
	
public  Logs createLOgs(Logs log) {
		
		Long userid= log.getUsers().getId();
		
		if(( log.getUsers()==null )|  userid<=0 ){
			throw new CustomException("provide at least a user id");
		}
		
		else if (userid==null) {
			throw new CustomException("your user id is null");
		}
		
		else if(  log.getElement_id()<=0 ){
			throw new CustomException(" id is not valid");
		}
		
		 
		
		try {userRepository.findById(userid);
		return logsrepository.save(log);
		}catch(Exception e){
				
				if(e.getMessage() == "Unable to find ") {
					throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
				}
				
				 throw new CustomException("verify your user id fields"); }
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
