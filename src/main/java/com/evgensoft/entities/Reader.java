package com.evgensoft.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Reader {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String fullName;
	
	@Column(nullable = false)
	private Long debt; // штрафы за задолженность
	
	private String profilePic; // ссылка на фотографию на сервере - ограничить до маленьких типа 250х250

}