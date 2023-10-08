package com.stage.stage.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name="core_Notes")
public class Notes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private Long id;
	@NumberFormat
	 @Min(1) @Max(4)
	private int visibility;
	
	@Min(1) @Max(4)
	private int element;
	
	private Long elementid;
	@Size( max = 700, message = "{validation.name.size.too_long}")
	
	@NotBlank(message = "provide url name")
	private String content;

}
