package org.sist.sb06_sbb3.answer;

import org.sist.sb06_sbb3.question.Question;
import org.sist.sb06_sbb3.question.QuestionService;
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
	
	@PostMapping("/create/{id}")
	public String createAnswer(
			@PathVariable("id") Integer id
			, @Valid AnswerForm answerForm
			, BindingResult bindingResult
			, Model model
			) {
		// 1. 
		Question question = this.questionService.getQuestion(id);
		if (bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "/question/detail";
		} // if
		// 2. 
		this.answerService.create(question, answerForm.getContent());
		return String.format("redirect:/question/detail/%s", id); // Integer 래퍼 클래스라 %s 쓴다.
	} //

} // class
