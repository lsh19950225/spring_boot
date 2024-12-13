package org.sist.sb06_sbb5.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sist.sb06_sbb5.exception.DataNotFoundException;
import org.sist.sb06_sbb5.user.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository; // 주입
	
	// 질문 목록 + 페이징 처리 x
	public List<Question> getList() {
		return this.questionRepository.findAll();
	} //
	
	// id에 해당하는 질문
	public Question getQuestion(Integer id) {
		Optional<Question> oQuestion = this.questionRepository.findById(id);
		if (oQuestion.isPresent()) {
			return oQuestion.get(); // get() : Optional<Question> -> Question 으로 바꾼다.
		} else {
			// 강제로 사용자 정의 예외를 발생시키겠다.
			// exception 패키지 + DataNotFoundException 예외 클래스 추가
			throw new DataNotFoundException("question not found");
		} // if else
	} //
	
	// 질문을 등록
	public void create(String subject, String content
			, SiteUser user) {
		Question question = new Question();
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
		
		question.setAuthor(user); // 작성자 추가
		
		this.questionRepository.save(question);
	} //
	
	// 페이징 처리 + 목록 / 1번 페이지를 주고 싶으면 0을 줘야된다.
	public Page<Question> getList(int page) {
								// pageNumber : 현재 페이지, pageSize : 한 페이지에 몇개
		// Pageable pageable = PageRequest.of(page, 10);
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("id"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.questionRepository.findAll(pageable);
	} //
	
} // class
