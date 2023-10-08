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
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@JsonIdentityInfo(scope =InventoryApplication.class,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Data @NoArgsConstructor @AllArgsConstructor

@DynamicUpdate(true)
@Table(name="inventory_application")
public class InventoryApplication {
	@Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	@NonNull
	  private Long id;
	@Size( max = 100, message = "{validation.name.size.too_long}")
	@Size(min = 2, message = "{validation.name.size.too_short}")
	@NotBlank(message = "provide categorie name")
	private String name;
	@NumberFormat
	 @Min(1) @Max(4)
	private int status;	
	private int classe;
@CreationTimestamp	
private Date start_date;
	private Date end_date;
	private String logo;
	private int environment;
	@ManyToOne
	@NotNull
	@JsonIdentityReference(alwaysAsId = true)
	@JoinColumn(name ="account_id",nullable = false )
	private Core_Accounts account;
	
	@JsonIgnore
	@OneToMany(mappedBy="application",cascade = CascadeType.ALL)
	private List<InventoryApplicationSources> sources;
	
	@JsonIgnore
	@OneToMany(mappedBy="application",cascade = CascadeType.ALL)
	private List<InventoryApplicationVersion> versions;
	
	@JsonIgnore
	@OneToMany(mappedBy="application",cascade = CascadeType.ALL)
	private List<InventoryApplicationInstances> instances;
}
