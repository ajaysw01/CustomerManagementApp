package com.aj.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min = 3, max=15)
	@NotBlank(message ="Name is mandatory")
	private String name;
	
	@Email
	@NotBlank(message ="Email is mandatory")
	private String email;
	
	@NotBlank(message ="PHone is mandatory")
	private String phone;
	
	@NotBlank(message ="Password is mandatory")
	private String pass;
	
	@NotBlank(message ="Address is mandatory")
	private String address;
	

}
