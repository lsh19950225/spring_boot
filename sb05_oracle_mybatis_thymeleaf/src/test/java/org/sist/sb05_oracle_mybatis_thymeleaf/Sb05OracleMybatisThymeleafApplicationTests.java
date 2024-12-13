package org.sist.sb05_oracle_mybatis_thymeleaf;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.sist.sb05_oracle_mybatis_thymeleaf.persistence.TimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.java.Log;

@SpringBootTest
@Log
class Sb05OracleMybatisThymeleafApplicationTests {
	
	@Autowired
	TimeMapper timeMapper;
	
	@Test
	void timeMapperTest() {
		System.out.println("@@@@@@@@@@@" + this.timeMapper.getTime());
	} //
	
	@Autowired
	DataSource dataSource;
	
	@Test
	void testConnection() {
		try (Connection con = this.dataSource.getConnection()) {
			log.info("@@@@@@@@@@@@@" + con);
			// 2024-12-05T10:32:47.396+09:00  INFO 12440 --- [sb03_jpa_oracle] [           main] o.s.s.sb03JpaOracleApplicationTests      : @@@@@@@@@@@@@HikariProxyConnection@1694568739 wrapping oracle.jdbc.driver.T4CConnection@44864536
		} catch (Exception e) {
			e.printStackTrace();
		} // try catch
	} //

} // class
