package org.sist.sb06_sbb4.question;

import java.util.List;

import org.sist.sb06_sbb4.answer.AnswerForm;
import org.sist.sb06_sbb4.page.Criteria;
import org.sist.sb06_sbb4.page.PageDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
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
	/* [1]
	@GetMapping("/list")
	public void list(Model model) {
		// List<Question> questionList = this.questionRepository.findAll(); // select All
		List<Question> questionList = this.questionService.getList();
		model.addAttribute("questionList", questionList); // 담기
	} // list
	*/
	
	// [2]
	@GetMapping("/list")
	public void list(Model model
			, @RequestParam(value = "page", defaultValue = "0") int page) {
		// 페이징 처리가 된 객체 : paging
		Page<Question> paging = this.questionService.getList(page);
		model.addAttribute("paging", paging); // 담기
		
		Criteria criteria = new Criteria(page+1, 10 );
	    int total = (int)paging.getTotalElements();
	    model.addAttribute("pageMaker",  new PageDTO(criteria, total));
	} // list
	
	// 질문 상세 보기
	// question/detail/2
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id
			, Model model
			, AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "/question/detail";
	} //
	
	// 질문 등록하기
	@GetMapping("/create")
	public void questionCreate( QuestionForm questionForm ) { // QuestionForm questionForm 넣어줘야 오류 안뜬다.
		
	} //
	
	/* [1]
	// 질문 등록하기
	@PostMapping("/create")
	public String questionCreate(@RequestParam("subject") String subject
			, @RequestParam("content") String content) {
		// 1. 질문을 등록 작업
		this.questionService.create(subject, content);
		// 2. 질문 목록으로 리다이렉트
		return "redirect:/question/list";
	} //
	*/
		
		// [2]
		// 질문 등록하기 + 유효성 검사
		@PostMapping("/create")
		public String questionCreate(
				@Valid QuestionForm questionForm // @Valid : 유효성 검사까지 한
				, BindingResult bindingResult // 뒤에 따라와야 된다.
				) {
			// 1. 질문을 등록 작업
			if ( bindingResult.hasErrors() ) {
				return "/question/create";
			} // if
			
			String subject = questionForm.getSubject();
			String content = questionForm.getContent();
			this.questionService.create(subject, content);
			// 2. 질문 목록으로 리다이렉트
			return "redirect:/question/list";
		} //
	
} // class
