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

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@JsonIdentityInfo(scope =Users.class ,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name="core_users")
@Data @AllArgsConstructor @NoArgsConstructor
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private long id;
	//private Long account_id;
	@Size( max = 100, message = "{validation.name.size.too_long}")
	
	@NotBlank(message = "provide provide your firstname")
	@Size( max = 100, message = "{validation.name.size.too_long}")
	
	private String firstname;
	@NotBlank(message = "provide provide your lastname")
	private String lastname;
	
	
	 @Min(1) @Max(4)
	private int role;
	private String language;
	private String timezone;
	private String browser;
	private String ip_address;	
	private Date last_auth;
	private String photo;
	@NumberFormat
	 @Min(1) @Max(4)
	private int status;
	@ManyToOne
	
	@JsonIdentityReference(alwaysAsId = true)
	@JoinColumn(name ="account_id")
	
private Core_Accounts account;
	
	@OneToMany(mappedBy="users",cascade = CascadeType.ALL)
	private List<UserPermessions> userpermession;
	@OneToMany(mappedBy="users",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CoreUserInstance> userinstance;
	@OneToMany(mappedBy="users",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<UsersModule> usermodule;
	@OneToMany(mappedBy="users",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<UserSecurity> usersecurity;
	
	@OneToMany(mappedBy="users",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Logs> logs;
}
