package com.stage.stage.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.stage.Dto.ServerInstanceDto;
import com.stage.stage.Exception.CustomException;

import com.stage.stage.entity.InventoryInstance;
import com.stage.stage.entity.InventoryServers;
import com.stage.stage.repository.InventoryInstanceRepository;
import com.stage.stage.repository.InventoryServersRepository;

@Service
public class InventoryServersService {
	@Autowired
	private InventoryInstanceRepository inventoryinstancerepo;
	private InventoryServersRepository serverRepository;
	@Autowired
	public InventoryServersService(InventoryServersRepository serverRepository) 
	{
		super();


		this.serverRepository = serverRepository;
	}
	
	public InventoryServers createServer(ServerInstanceDto serverDto) {
	
	InventoryInstance instance1=inventoryinstancerepo.save(new InventoryInstance(null,serverDto.getName(),serverDto.getStatus(),serverDto.getAccount(),serverDto.getStart_date()));
Long idInstance=instance1.getId();
	return serverRepository.save(new InventoryServers(serverDto.getClasse(),serverDto.getExternal_id(),serverDto.getIp_adress(),serverDto.getOperating_system(),serverDto.getOs_version(),serverDto.getLocation(),serverDto.getCpu(),serverDto.getDisk_space(),serverDto.getMemory(),serverDto.getEnvironment(),instance1));
					

		}	
	
	public InventoryServers getServersByInstanceId(Long id){
		InventoryServers servers;
		
		 if (id <= 0) {
	throw new CustomException("invalid id");
	}

	else {
		try{

			servers =  serverRepository.findByInstancesId(id);
			return servers;
		}catch(Exception e){
		
		if(e.getMessage() == "No value present") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		 throw new CustomException("verfiy your account id"); 
	}

	}
	}
	
	public void deleteServer(Long id) {
		if(id <=0 || id==null) {
			throw new CustomException("invalid id");
		}
		else {
			try{

				 serverRepository.deleteById(id);
				
			}catch(Exception e){
			
			if(e.getMessage() == "No class") {
				throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
			}
			 throw new CustomException("resource doesn t not exist"); 
		}
		
	}		
	}
	
	public List<Map<String,Object>> findserverByInstanceIdd(Long id) {
		// TODO Auto-generated method stub
		
				 if (id <= 0) {
			 throw new CustomException("invalid id");
		}
		
		else {
				

					return   serverRepository.findserverByInstanceIdd(id);
					
				
			
		}
			
	}
	public InventoryServers updateSerever(ServerInstanceDto serverDto,Long id) {
	InventoryServers server;
	InventoryInstance instance;
	InventoryInstance instance1;
	if(id <=0 ) {
		throw new CustomException("invalid id");
	}
	else {
		try{
	 instance=inventoryinstancerepo.findById(id).get();
	 
	 instance.setName(serverDto.getName());
	 instance.setStatus(serverDto.getStatus());
	 instance.setCreation_type(serverDto.getCreation_type());
	 instance.setFavorite(serverDto.getFavorite());
	 if(serverDto.getStart_date()==null) {
		 instance.setStart_date(instance.getStart_date());}
	 if(serverDto.getCreation_type()==0) {
		 instance.setCreation_type(instance.getCreation_type());
	 }
	 instance1 =inventoryinstancerepo.saveAndFlush(instance);
		}catch(Exception e){
		
		if(e.getMessage() == "No value present") {
			throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
		}
		
		 throw new CustomException("verify your fields"); 
	}}
	
	try {
	
		server=serverRepository.findByInstancesId(id);
		 server.setClasse(serverDto.getClasse());
		 server.setDisk_space(serverDto.getDisk_space());
		 server.setLocation(serverDto.getLocation());
		 server.setCpu(serverDto.getCpu());
		 server.setMemory(serverDto.getMemory());
		 server.setExternal_id(serverDto.getExternal_id());
		 server.setIp_adress(serverDto.getIp_adress());
		 server.setOs_version(serverDto.getOs_version());
		 server.setOperating_system(serverDto.getOperating_system());
		 server.setEnvironment(serverDto.getEnvironment());
		 
		 return serverRepository.save(server);
	}
		 catch(Exception e){
				
				if(e.getMessage() == "No value present") {
					throw new com.stage.stage.Exception.ResourceNotFoundException("resource doesn t exist");
				}
				
				 throw new CustomException("verify your fields"); 
			}}
	
	
public 	List<Map<String,Object>> findserversbyAccountId(Long id){
	if (id <= 0) {
		 throw new CustomException("invalid id");
	}
	else {
		return serverRepository.findserversbyAccountid(id);}
	
}
	
	
	
	
	
	
	
	
	
	

}
