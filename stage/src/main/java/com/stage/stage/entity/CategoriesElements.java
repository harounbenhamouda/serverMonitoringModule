package com.stage.stage.entity;

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

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@JsonIdentityInfo(scope = CategoriesElements.class,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Data @NoArgsConstructor @AllArgsConstructor

@DynamicUpdate(true)
@Table(name="core_categories_elements")
public class CategoriesElements {
	@Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	@NonNull
	  private Long id;
	
	@NumberFormat
	 @Min(1) @Max(4)
	private int element;	
	@NumberFormat
	 @Min(1) 
	private Long element_id;
	@ManyToOne
	@NotNull
	@JsonIdentityReference(alwaysAsId = true)
	@JoinColumn(name ="categore_id",nullable = false )
	private Categories categories;
}
