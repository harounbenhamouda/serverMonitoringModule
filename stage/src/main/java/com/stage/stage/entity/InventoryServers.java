package com.stage.stage.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name="inventory_servers")
@JsonIdentityInfo(scope =InventoryServers.class ,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class InventoryServers {
	
	
	
	
	

	public InventoryServers(Long classe, String external_id, String ip_adress, Long operating_system, String os_version,
			Long location, int cpu, int disk_space, int memory, int environment, InventoryInstance instances) {
		super();
		this.classe = classe;
		this.external_id = external_id;
		this.ip_adress = ip_adress;
		this.operating_system = operating_system;
		this.os_version = os_version;
		this.location = location;
		this.cpu = cpu;
		this.disk_space = disk_space;
		this.memory = memory;
		this.environment = environment;
		this.instances = instances;
	}

	@Id
	@NonNull
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	
	  private Long id;
	
	private Long classe;	
	  
	private String external_id;
	  
	private String ip_adress;
	  
		private Long operating_system;	
	 
	  private String os_version;
	  private Long  location;
	  private int cpu;
	  private int disk_space;
	  private int memory;
	  private int environment;
	 
	  @OneToOne( cascade = {CascadeType.ALL, CascadeType.MERGE} )
	  @JsonIdentityReference(alwaysAsId = true)
	  @JoinColumn(name = "instance_id")
		
	private InventoryInstance instances;  
	  
	  
	  @OneToMany(mappedBy="server",cascade = CascadeType.ALL)
		private List<InventoryServerVersion> serverversion;
	  
	  
}
