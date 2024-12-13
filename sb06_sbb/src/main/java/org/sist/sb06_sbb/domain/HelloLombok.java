package org.sist.sb06_sbb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
/*
@Getter
@Setter

public class HelloLombok {
	
	private String Hello;
	private int lombok;
	
	public static void main(String[] args) {
		// System.out.println("hello world");
		HelloLombok helloLombok = new HelloLombok();
		helloLombok.setHello("헬로");
		helloLombok.setLombok(5);
		
		System.out.println(helloLombok.getHello());
		System.out.println(helloLombok.getLombok());
	} // main

} // class
*/
@Getter
// @NoArgsConstructor
// @AllArgsConstructor
@RequiredArgsConstructor
public class HelloLombok {
	
	private final String Hello;
	private final int lombok;
	
	public static void main(String[] args) {
		// System.out.println("hello world");
		HelloLombok helloLombok = new HelloLombok("헬로", 5);
		
		System.out.println(helloLombok.getHello());
		System.out.println(helloLombok.getLombok());
	} // main

} // class