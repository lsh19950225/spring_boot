package org.sist.sb03_jpa_oracle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.sist.sb03_jpa_oracle.persistence") // ***
public class sb03JpaOracleApplication {

	public static void main(String[] args) {
		SpringApplication.run(sb03JpaOracleApplication.class, args);
	}

} // class
