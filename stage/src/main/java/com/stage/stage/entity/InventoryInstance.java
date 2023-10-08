package com.stage.stage.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@JsonIdentityInfo(scope =InventoryInstance.class,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="inventory_instance")

public class InventoryInstance {
	
		@Id
		@NonNull
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	
	  private Long id;
	
	private String name;
	
	

	private int favorite;	
	
	private int creation_type;	
	
	private Long status;	
@CreationTimestamp
	private Date start_date;
	
	private Date end_date;

	@OneToOne(mappedBy="instances")
	private InventoryServers inventoryservers;
	
	@OneToMany(mappedBy="instances")
	private List<CoreUserInstance> userinstance;
	
	@OneToMany(mappedBy="instances")
	private List<InventoryApplicationInstances> userinstances;
@ManyToOne
	
	@JsonIdentityReference(alwaysAsId = true)
	@JoinColumn(name ="account_id")
	
private Core_Accounts account;
	public InventoryInstance(Long id, String name, Long status,Core_Accounts account,Date start_date) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.account=account;
		this.start_date=start_date;
	}

}
