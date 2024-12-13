package org.sist.sb05_oracle_mybatis_thymeleaf.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptVO {
	
	private int deptno;
	private String dname;
	private String loc;
	
} // class
