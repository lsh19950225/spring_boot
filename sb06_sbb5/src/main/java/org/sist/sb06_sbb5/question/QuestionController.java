package org.sist.sb06_sbb5.question;

import java.security.Principal;
import java.util.List;

import org.sist.sb06_sbb5.answer.AnswerForm;
import org.sist.sb06_sbb5.page.Criteria;
import org.sist.sb06_sbb5.page.PageDTO;
import org.sist.sb06_sbb5.user.SiteUser;
import org.sist.sb06_sbb5.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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
	private final UserService userService;
	
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
	// 인증을 받지 않으면 강제로 로그인 페이지로 이동시킨다.
	@PreAuthorize("isAuthenticated()") // 먼저 (글쓸)권한이 있는지 물어본다.
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
		// 인증을 받지 않으면 강제로 로그인 페이지로 이동시킨다.
		@PreAuthorize("isAuthenticated()") // 먼저 (글쓸)권한이 있는지 물어본다.
		@PostMapping("/create")
		public String questionCreate(
				@Valid QuestionForm questionForm // @Valid : 유효성 검사까지 한
				, BindingResult bindingResult // 뒤에 따라와야 된다.
				, Principal principal
				) {
			// 1. 질문을 등록 작업
			if ( bindingResult.hasErrors() ) {
				return "/question/create";
			} // if
			SiteUser siteUser = this.userService.getUser(principal.getName());
			
			String subject = questionForm.getSubject();
			String content = questionForm.getContent();
			this.questionService.create(subject, content, siteUser);
			// 2. 질문 목록으로 리다이렉트
			return "redirect:/question/list";
		} //
	
} // class
