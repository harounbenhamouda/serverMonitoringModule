package com.stage.stage.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Data @AllArgsConstructor @NoArgsConstructor
@JsonIdentityInfo(scope = Logs.class,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Logs {
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@CreationTimestamp
	private Date action_date;
	@Size( max = 100, message = "{validation.name.size.too_long}")
	
	@NotBlank(message = "provide provide your action")
	private String action;
	@Min(1) @Max(4)
	private int element;
	@NonNull
	private Long element_id;
	private int source;
	
	@ManyToOne
	@NotNull
	@JsonIdentityReference(alwaysAsId = true)
	@JoinColumn(name ="user_id")
	
private Users users;
	
	
	
	

}
