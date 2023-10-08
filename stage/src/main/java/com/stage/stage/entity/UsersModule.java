package com.stage.stage.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="users_module")
@Data @AllArgsConstructor @NoArgsConstructor
@JsonIdentityInfo(scope = UsersModule.class,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UsersModule {
	@Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	@NonNull
	  private Long id;
	@NumberFormat
	 @Min(1) @Max(4)
	private int status;
	@CreationTimestamp
	private Date start_date;
	private Date end_date;
	
	@ManyToOne
	@NotNull
	@JsonIdentityReference(alwaysAsId = true)
	@JoinColumn(name ="module_id")
	
private CoreAccountModule modules;
	
	
	
	@ManyToOne
	@NotNull
	@JsonIdentityReference(alwaysAsId = true)
	@JoinColumn(name ="user_id")
	
private Users users;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
