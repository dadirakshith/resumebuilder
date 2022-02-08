package com.test.yantra.resume.builder.dto;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyDetails{
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int techDetailsId;
	
	@Column(length = 50)
	private String type;
	
	
	private String items;
	
}
