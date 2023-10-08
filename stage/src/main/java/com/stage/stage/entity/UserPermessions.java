package com.stage.stage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name="users_permissions")
@Data @AllArgsConstructor @NoArgsConstructor
public class UserPermessions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private long id;
	@Size( max = 100, message = "{validation.name.size.too_long}")
	@Size(min = 2, message = "{validation.name.size.too_short}")
	@NotBlank(message = "provide provide accou info")
	private String code;
	@NumberFormat
	 @Min(1) @Max(4)
	private int status;
	
	@Min(1) @Max(2)
	private int dependency;
	@ManyToOne
	@NotNull
	@JsonIdentityReference(alwaysAsId = true)
	@JoinColumn(name ="user_id",nullable = false )
	private Users users;
}
