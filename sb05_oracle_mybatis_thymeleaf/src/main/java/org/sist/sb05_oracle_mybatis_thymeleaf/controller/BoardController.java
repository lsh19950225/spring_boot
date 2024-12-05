package org.sist.sb05_oracle_mybatis_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/board")
@Controller
@Log
public class BoardController {
	
	@GetMapping("/list")
	public void list() {
		System.out.println("listController test");
	} //
	
	
} // class
