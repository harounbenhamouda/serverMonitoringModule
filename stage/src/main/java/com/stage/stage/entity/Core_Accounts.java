package com.stage.stage.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@JsonIdentityInfo(scope =Core_Accounts.class,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@DynamicInsert
@DynamicUpdate(true)
public class Core_Accounts{
	
	@Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	@NonNull
	  private Long id;
	@Size( max = 100, message = "{validation.name.size.too_long}")
	@Size(min = 2, message = "{validation.name.size.too_short}")
	@NotBlank(message = "provide provide account name")
	private String name;
	@NumberFormat
	 @Min(1) @Max(4)
	private int status;
	@CreationTimestamp
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date start_date;
	
	private Date end_date;

	@OneToMany(mappedBy="account",cascade = CascadeType.ALL)
	
	
			private List<CoreAccountModule> acountmodules=new ArrayList<>();
@JsonIgnore
	@OneToMany(mappedBy="account",cascade = CascadeType.ALL)
	
	
			private List<Users> user;



@JsonIgnore
@OneToMany(mappedBy="account",cascade = CascadeType.ALL)


		private List<Categories> categories;

@JsonIgnore
@OneToMany(mappedBy="account",cascade = CascadeType.ALL)


		private List<Credentials> credential;

@OneToMany(mappedBy="account",cascade = CascadeType.ALL)

private List<InventoryInstance> insatnces;

@OneToMany(mappedBy="account",cascade = CascadeType.ALL)

private List<InventoryApplication> inventories;


	public String toString() {
		return "";
	}
	
}
