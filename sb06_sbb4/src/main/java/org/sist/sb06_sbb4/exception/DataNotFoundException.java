package org.sist.sb06_sbb4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 질문ID 가 없어도 404를 띄우겠다.
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class DataNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public DataNotFoundException(String message) {
		super(message);
	} //
	
} // class

// 질문ID 가 존재하지 않을 때 발생하는 사용자 예외 클래스 : DataNotFoundException
// 위의 예외가 발생하면 스프링 부트에서는 HTTP 상태 코드 + 이유를
// 포함해서 응답객체를 생성할 수 있다.