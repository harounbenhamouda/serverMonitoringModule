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

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity

@Data @AllArgsConstructor @NoArgsConstructor
@JsonIdentityInfo(scope = UserSecurity.class,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserSecurity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private long id;
	//private Long account_id;
	
	
	private String login;
	
	private String password;
	@NumberFormat
	 @Min(1) @Max(4)
	private int status;
	@CreationTimestamp
	private Date start_date;
	
	private Date end_date;
	
	@ManyToOne
	@NotNull
	@JsonIdentityReference(alwaysAsId = true)
	@JoinColumn(name ="user_id")
	
private Users users;
	
	
	
	
	
	
	
	
	
}
