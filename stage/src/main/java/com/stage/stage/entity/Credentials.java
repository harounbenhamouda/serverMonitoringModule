package com.stage.stage.entity;

import java.util.Date;
import java.util.List;

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

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@JsonIdentityInfo(scope = Credentials.class,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Data @NoArgsConstructor @AllArgsConstructor

@DynamicUpdate(true)
@Table(name="core_credentials")
public class Credentials {
	@Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	@NonNull
	  private Long id;
	@Size(min = 2, max = 100, message = "{validation.name.size.too_long}")
	@Size(min = 2, message = "{validation.name.size.too_short}")
	@NotBlank(message = "provide provide your name")
private String name;
	@Size( max = 100, message = "{validation.name.size.too_long}")
	@NotBlank(message = "provide provide your url")	
private String url;
	@Size( max = 14, message = "{validation.name.size.too_long}")
	
	private String port;
	@Size( max = 100, message = "{validation.name.size.too_long}")
	@NotBlank(message = "provide provide your login")
private String login;
	
	@Size( max = 100, message = "{validation.name.size.too_long}")
	@NotBlank(message = "provide provide your password")
private String password;
	@NumberFormat
	 @Min(1) @Max(4)	
private int status;
	@CreatedDate
private Date start_date;
private Date end_date;
@NumberFormat
@Min(1) @Max(4)
private int element;
@NotNull
private Long element_id;
@NumberFormat
@Min(1) @Max(4)
private int classe;
@ManyToOne

@NotNull
@JsonIdentityReference(alwaysAsId = true)
@JoinColumn(name ="account_id",nullable = false )

private Core_Accounts account;
	
	
	
	
}
