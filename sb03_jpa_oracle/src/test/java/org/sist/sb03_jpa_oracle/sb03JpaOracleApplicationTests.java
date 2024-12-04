package org.sist.sb03_jpa_oracle;

import org.junit.jupiter.api.Test;
import org.sist.sb03_jpa_oracle.persistence.TimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class sb03JpaOracleApplicationTests {
	
	@Autowired
	TimeMapper timeMapper;
	
	@Test
	void timeMapperTest() {
		System.out.println("@@@@@@@@@@@" + this.timeMapper.getTime());
	} //

} // class
