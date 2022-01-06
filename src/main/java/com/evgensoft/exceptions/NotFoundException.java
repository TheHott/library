package com.evgensoft.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 3387584614288254478L;
	
	private String error;
}
