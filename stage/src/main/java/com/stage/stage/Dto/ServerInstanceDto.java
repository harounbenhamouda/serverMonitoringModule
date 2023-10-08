package com.stage.stage.Dto;

import java.util.Date;

import com.stage.stage.entity.Core_Accounts;
import com.stage.stage.entity.InventoryInstance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class ServerInstanceDto {

	Long Id;
	String name;
	Long status;
	
	Date start_date;
	
	Long classe;
	Long operating_system;
	Long location;
	String external_id;
	String ip_adress;
	Core_Accounts account;
	InventoryInstance instances;
	String os_version;
	   int cpu;
	  int disk_space;
	  int memory;
	  int creation_type;
	  int favorite;
	  int environment;
}

