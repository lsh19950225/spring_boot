package org.sist.sb06_sbb8.question;

import java.security.Principal;
import java.util.List;

import org.sist.sb06_sbb8.answer.AnswerForm;
import org.sist.sb06_sbb8.page.Criteria;
import org.sist.sb06_sbb8.page.PageDTO;
import org.sist.sb06_sbb8.user.SiteUser;
import org.sist.sb06_sbb8.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

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
	/*
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
	*/
	
	/*
	// [3] 검색 기능 포함
	@GetMapping("/list")
	public void list(Model model
			, @RequestParam(value = "page", defaultValue = "0") int page
			, @RequestParam(value = "kw", defaultValue = "") String kw) {
		// 페이징 처리가 된 객체 : paging
		Page<Question> paging = this.questionService.getList(page, kw);
		model.addAttribute("paging", paging); // 담기
		model.addAttribute("kw", kw); // 담기
		
		Criteria criteria = new Criteria(page+1, 10 );
	    int total = (int)paging.getTotalElements();
	    model.addAttribute("pageMaker",  new PageDTO(criteria, total));
	} // list
	*/
	
	   // [4] 검색기능 포함 + Querydsl
	   @GetMapping("/list")
	   public void list(Model model
	         , @RequestParam( value = "page", defaultValue = "0") int page 
	         , @RequestParam( value = "type", defaultValue = "s") String type 
	         , @RequestParam( value = "kw", defaultValue = "") String keyword 
	         ) {      
	      // 페이징 처리가 된 객체  : paging
	      Page<Question> paging = this.questionService.getList(page, type, keyword);
	      model.addAttribute("paging", paging);
	      model.addAttribute("kw", keyword);

	      Criteria criteria = new Criteria(page+1, 10 ); 
	      int total = (int)paging.getTotalElements();
	      model.addAttribute("pageMaker",  new PageDTO(criteria, total));
	   }
	
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
		
		// 수정버튼 클릭 시
		@PreAuthorize("isAuthenticated()")
	    @GetMapping("/modify/{id}")
	    public String questionModify(QuestionForm questionForm
	    		, @PathVariable("id") Integer id, Principal principal) {
	        Question question = this.questionService.getQuestion(id);
	        if(!question.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
	        }
	        // question(엔티티) -> DTO 변환 : 엔티티는 DTO 역할로 안쓴다.
	        questionForm.setSubject(question.getSubject());
	        questionForm.setContent(question.getContent());
	        return "/question/create";
	    } //
		
		// 수정 처리
		@PreAuthorize("isAuthenticated()")
	    @PostMapping("/modify/{id}")
	    public String questionModify(@Valid QuestionForm questionForm
	    		, BindingResult bindingResult
	    		, Principal principal
	    		, @PathVariable("id") Integer id) {
	        if (bindingResult.hasErrors()) {
	            return "/question/create";
	        }
	        Question question = this.questionService.getQuestion(id);
	        if (!question.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
	        } // if
	        this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
	        return String.format("redirect:/question/detail/%s", id);
	    } //
		
		// 삭제
		@PreAuthorize("isAuthenticated()")
	    @GetMapping("/delete/{id}")
	    public String questionDelete(Principal principal
	    		, @PathVariable("id") Integer id) {
	        Question question = this.questionService.getQuestion(id);
	        if (!question.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
	        } // if
	        this.questionService.delete(question);
	        return "redirect:/";
	    }
		
		// 추천
		@PreAuthorize("isAuthenticated()")
	    @GetMapping("/vote/{id}")
	    public String questionVote(Principal principal, @PathVariable("id") Integer id) {
	        Question question = this.questionService.getQuestion(id);
	        SiteUser siteUser = this.userService.getUser(principal.getName());
	        this.questionService.vote(question, siteUser);
	        return String.format("redirect:/question/detail/%s", id);
	    }
		
} // class
