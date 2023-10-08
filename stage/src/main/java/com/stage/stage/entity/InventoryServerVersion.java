package com.stage.stage.entity;

import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name="inventory_servers_versions")
@JsonIdentityInfo(scope =InventoryServerVersion.class ,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class InventoryServerVersion {

	@Id
	@NonNull
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	
	  private Long id;
	 @Max(4)
	private int status;
	@CreationTimestamp
	private Date start_date;
	private Date end_date;

	@Size( max = 64, message = "{validation.name.size.too_long}")

	private String version;
	private int element;
    private Long element_Id;
    private String element_name;
    @ManyToOne
	@NotNull
	@JsonIdentityReference(alwaysAsId = true)
	@JoinColumn(name ="server_id",nullable = false )
	private InventoryServers server;
}
