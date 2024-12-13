package org.sist.sb05_oracle_mybatis_thymeleaf.controller;

import java.util.List;

import org.sist.sb05_oracle_mybatis_thymeleaf.Service.DeptService;
import org.sist.sb05_oracle_mybatis_thymeleaf.domain.DeptVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.annotation.Resource;
import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/dept")
public class DeptController {
	
	@Resource
	private DeptService deptService;
	
	@GetMapping("/list")
	public ModelAndView deptList() throws Exception {
		
		List<DeptVO> list = this.deptService.getDeptList();
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("list", list);
		mav.setViewName("/dept/list");
		
		return mav;
		
	} // deptList
	
} // class
