package com.stage.stage.entity;

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
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name="inventory_Applications_sources")
@JsonIdentityInfo(scope =InventoryApplicationSources.class ,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class InventoryApplicationSources {
	@Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	@NonNull
	  private Long id;
	@Size( max = 255, message = "{validation.name.size.too_long}")
	
	private String url;
	 
		@Size( max = 64, message = "{validation.name.size.too_long}")
		@NotBlank(message = "provide categorie name")
		private String source_account;
		@Size( max = 64, message = "{validation.name.size.too_long}")
		@NotBlank(message = "provide source bield")
		private String source_build;
		@Min(1) @Max(4)
		private int status;
		private int classe;
		@CreationTimestamp
		private Date start_date;
		private Date end_date;
		@Min(1) @Max(4)
		private int source_type;
		
		@ManyToOne
		@NotNull
		@JsonIdentityReference(alwaysAsId = true)
		@JoinColumn(name ="application_id",nullable = false )
		private InventoryApplication application;
		
	
}
