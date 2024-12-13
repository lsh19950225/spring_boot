package org.sist.sb06_sbb5.answer;

import java.security.Principal;

import org.sist.sb06_sbb5.question.Question;
import org.sist.sb06_sbb5.question.QuestionService;
import org.sist.sb06_sbb5.user.SiteUser;
import org.sist.sb06_sbb5.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	// [2]
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
		this.answerService.create(question, answerForm.getContent(), siteUser);
		return String.format("redirect:/question/detail/%s", id); // Integer 래퍼 클래스라 %s 쓴다.
	} //

} // class
