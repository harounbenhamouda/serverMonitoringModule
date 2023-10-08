package com.stage.stage.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;





@Entity
@Table(name="core_modules")
@Data @AllArgsConstructor @NoArgsConstructor
@DynamicInsert
@DynamicUpdate(true)
@JsonIdentityInfo(scope = Modules.class,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Modules {
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
	@Size( max = 100, message = "{validation.name.size.too_long}")
	@Size(min = 2, message = "{validation.name.size.too_short}")
	@NotBlank(message = "provide module name")
	private String name;
	@OneToMany(mappedBy="modules",cascade = CascadeType.ALL)
	private List<CoreAccountModule> acountmodules=new ArrayList<>();
}
