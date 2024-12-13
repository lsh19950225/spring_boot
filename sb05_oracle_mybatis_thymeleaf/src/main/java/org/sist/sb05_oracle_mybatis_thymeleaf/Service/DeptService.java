package org.sist.sb05_oracle_mybatis_thymeleaf.Service;

import java.util.List;

import org.sist.sb05_oracle_mybatis_thymeleaf.domain.DeptVO;

public interface DeptService {
	
	List<DeptVO> getDeptList() throws Exception;
	
} // interface
