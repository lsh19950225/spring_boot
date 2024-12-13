package org.sist.sb06_sbb6.user;

import lombok.Getter;

@Getter
public enum UserRole {
	
	ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

	UserRole(String value) {
		this.value = value;
	} //
	
	private String value;
	
} // enum

// 사용자 권한 열거형 : ADMIN, USER : 상수의 값으로
// "ROLE_ADMIN", "ROLE_USER" : value 값으로 설정