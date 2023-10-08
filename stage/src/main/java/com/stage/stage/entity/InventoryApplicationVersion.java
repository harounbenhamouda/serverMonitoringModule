package com.stage.stage.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="inventory_Applications_version")
@JsonIdentityInfo(scope =InventoryApplicationSources.class ,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class InventoryApplicationVersion {

	@Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	@NonNull
	  private Long id;
	@Min(1) @Max(4)
	private int status;
	@Size( max = 64, message = "{validation.name.size.too_long}")
	@NotBlank(message = "provide version")
	private String version;
	
	@CreationTimestamp
	private Date start_date;
	private Date end_date;
	@ManyToOne
	@NotNull
	@JsonIdentityReference(alwaysAsId = true)
	@JoinColumn(name ="application_id",nullable = false )
	private InventoryApplication application;
}
