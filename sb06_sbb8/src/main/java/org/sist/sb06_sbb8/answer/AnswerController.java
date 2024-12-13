package org.sist.sb06_sbb8.answer;

import java.security.Principal;

import org.sist.sb06_sbb8.question.Question;
import org.sist.sb06_sbb8.question.QuestionService;
import org.sist.sb06_sbb8.user.SiteUser;
import org.sist.sb06_sbb8.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
	
	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;
	
	/* [1]
	@PostMapping("/create/{id}")
	public String createAnswer(
			@PathVariable("id") Integer id
			, @Valid AnswerForm answerForm
			) {
		Question question = this.questionService.getQuestion(id);
		this.answerService.create(question, content);
		return String.format("redirect:/question/detail/%s", id); // Integer 래퍼 클래스라 %s 쓴다.
	} //
	*/
	
	// [2] 답변 쓰기(저장)
	// 인증을 받지 않으면 강제로 로그인 페이지로 이동시킨다.
	@PreAuthorize("isAuthenticated()") // 먼저 (글쓸)권한이 있는지 물어본다.
	@PostMapping("/create/{id}")
	public String createAnswer(
			@PathVariable("id") Integer id
			, @Valid AnswerForm answerForm
			, BindingResult bindingResult
			, Model model
			, Principal principal // 인증 정보
			) {
		// 1. 
		Question question = this.questionService.getQuestion(id);
		SiteUser siteUser = this.userService.getUser(principal.getName());
		if (bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "/question/detail";
		} // if
		// 2. 
		Answer answer = this.answerService.create(question, answerForm.getContent(), siteUser);
																				// #answer_%d : 앵커 기능
		return String.format("redirect:/question/detail/%s#answer_%s", id, answer.getId()); // Integer 래퍼 클래스라 %s 쓴다.
	} //
	
		// 답변 수정
	    @PreAuthorize("isAuthenticated()")
	    @GetMapping("/modify/{id}")
	    public String answerModify(AnswerForm answerForm, @PathVariable("id") Integer id, Principal principal) {
	        Answer answer = this.answerService.getAnswer(id);
	        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
	        }
	        answerForm.setContent(answer.getContent());
	        return "/answer/answer_form";
	    } //
	   
	    // 답변 수정
	    @PreAuthorize("isAuthenticated()")
	    @PostMapping("/modify/{id}")
	    public String answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult,
	            @PathVariable("id") Integer id, Principal principal) {
	        if (bindingResult.hasErrors()) {
	            return "/answer/answer_form";
	        }
	        Answer answer = this.answerService.getAnswer(id);
	        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
	        }
	        this.answerService.modify(answer, answerForm.getContent());
	        // return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
	        return String.format("redirect:/question/detail/%s#answer_%s", 
	                answer.getQuestion().getId(), answer.getId());
	    } //
	   
	    // 답변 삭제
	    @PreAuthorize("isAuthenticated()")
	    @GetMapping("/delete/{id}")
	    public String answerDelete(Principal principal, @PathVariable("id") Integer id) {
	        Answer answer = this.answerService.getAnswer(id);
	        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
	        }
	        this.answerService.delete(answer);
	        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
	    } //
	    
	    // 답변 추천
	    @PreAuthorize("isAuthenticated()")
	    @GetMapping("/vote/{id}")
	    public String answerVote(Principal principal, @PathVariable("id") Integer id) {
	        Answer answer = this.answerService.getAnswer(id);
	        SiteUser siteUser = this.userService.getUser(principal.getName());
	        this.answerService.vote(answer, siteUser);
	        // return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
	        return String.format("redirect:/question/detail/%s#answer_%s", 
	                answer.getQuestion().getId(), answer.getId());
	    } //
	    
} // class
