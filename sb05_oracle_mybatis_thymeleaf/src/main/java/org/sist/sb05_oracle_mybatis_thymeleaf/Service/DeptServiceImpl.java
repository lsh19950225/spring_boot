package org.sist.sb05_oracle_mybatis_thymeleaf.Service;

import java.util.List;

import org.sist.sb05_oracle_mybatis_thymeleaf.domain.DeptVO;
import org.sist.sb05_oracle_mybatis_thymeleaf.persistence.DeptMapper;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class DeptServiceImpl implements DeptService {

	@Resource
	private DeptMapper deptMapper;
	
	@Override
	public List<DeptVO> getDeptList() throws Exception {
		return this.deptMapper.getDeptList();
	}

} // class
