package com.evgensoft.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

	READER("Читатель"),
	ADMIN("");
	
	private String name;
	
	Role(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return this.toString();
	}
	
	public String getName() {
		return name;
	}
}
