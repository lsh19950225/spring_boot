package org.sist.sb06_sbb2.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
	
	// private final QuestionRepository questionRepository; // 주입
	private final QuestionService questionService; // 서비스 주입
	
	/*
	@GetMapping("/question/list")
	@ResponseBody
	public String list() {
		
		return "question list";
	}
	*/
	
	// 질문 목록
	@GetMapping("/list")
	public void list(Model model) {
		// List<Question> questionList = this.questionRepository.findAll(); // select All
		List<Question> questionList = this.questionService.getList();
		model.addAttribute("questionList", questionList); // 담기
	} // list
	
	// 질문 상세 보기
	// question/detail/2
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id
			, Model model) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "/question/detail";
	} // list
	
} // class
