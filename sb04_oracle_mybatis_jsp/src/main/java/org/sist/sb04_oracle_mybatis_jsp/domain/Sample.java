package org.sist.sb04_oracle_mybatis_jsp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_sample") // 안주면 자동으로 클래스 이름하고 똑같은 테이블이 만들어진다.
public class Sample {
	
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY) // oracle은 시퀀스를 써서 그냥은 못쓴다.
	@GeneratedValue(generator = "seq_tblsample", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_tblsample", sequenceName = "seq_tblsample" // 시퀀스 생성
	, initialValue = 1, allocationSize = 1) // 1부터 1씩 증가한다.
	private Long sno;
	
	// 컬럼명을 바꾸고 싶으면 "컬럼 어노테이션" 쓰면 된다.
	private String col1;
	private String col2;
	
} // class

/* 자동으로 테이블 생성된다.
	Hibernate: 
	create table tbl_sample 
	(sno number(19,0) not null, col1 varchar2(255 char)
	, col2 varchar2(255 char)
	, primary key (sno))
	Hibernate: 
	create sequence seq_tblsample
	 start with 1
	  increment by 1
*/