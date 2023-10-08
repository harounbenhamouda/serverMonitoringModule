package com.stage.stage.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@JsonIdentityInfo(scope =InventoryApplicationInstances.class,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Data @NoArgsConstructor @AllArgsConstructor

@DynamicUpdate(true)
public class InventoryApplicationInstances {
	@Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	@NonNull
	  private Long id;
	@CreationTimestamp	
	private Date start_date;
		private Date end_date;
		 @Min(1) @Max(4)
			private int status;	
		
		@ManyToOne
		@NotNull
		@JsonIdentityReference(alwaysAsId = true)
		@JoinColumn(name ="application_id",nullable = false )
		private InventoryApplication application;
		
		@ManyToOne
		@NotNull
		@JsonIdentityReference(alwaysAsId = true)
		@JoinColumn(name ="serverInstance_id",nullable = false )
		private InventoryInstance instances;	
		
		
		

}
