package org.sist.sb06_sbb8;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.sist.sb06_sbb8.answer.Answer;
import org.sist.sb06_sbb8.answer.AnswerRepository;
import org.sist.sb06_sbb8.question.Question;
import org.sist.sb06_sbb8.question.QuestionRepository;
import org.sist.sb06_sbb8.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class Sb06Sbb8ApplicationTests {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	/*
	@Test
	void testJpa() {
		// 질문 등록 테스트 insert
		Question q1 = new Question();
		
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);
		
		Question q2 = new Question();
		
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2); // save()
	} //
	*/
	
	/*
	@Test
	void testJpa() {
		// 모든 질문 조회 select
		List<Question> list = this.questionRepository.findAll();
		System.out.println("@@@@@@@@@@ : " + list.size()); // 레코드 갯수
		list.stream().forEach(q->System.out.println(q.getSubject()));
		
		Hibernate: 
		    select
		        q1_0.id,
		        q1_0.content,
		        q1_0.create_date,
		        q1_0.subject 
		    from
		        question q1_0
		@@@@@@@@@@2
		sbb가 무엇인가요?
		스프링부트 모델 질문입니다.
		
	} //
	*/
	
	/*
	@Test
	void testJpa() {
		// 질문 ID에 해당하는 질문을 조회
		Optional<Question> optional = this.questionRepository.findById(2);
		if (optional.isPresent()) { // 값이 존재한다면
			// 쿼리에 where 조건이 들어가진다.
			Question q = optional.get();
			System.out.println( q.getSubject() + "/" + q.getContent() );
		}
	} //
	*/
	
	/*
	@Test
	void testJpa() {
		// Repository
		// CrudRepository
		// PagingAndSortRepository
		// JpaRepository
		// 제목으로 검색 / 쿼리 메서드를 써본다.
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		System.out.println("@@@@@@@@@@ : " + q.getContent());
	} //
	*/
	
	/*
	// WHERE subject LIKE '%무엇%' 이런식으로 해보기
	@Test
	void testJpa() {
		
		// 제목으로 검색
		// 1. 쿼리 메서드 사용
		// List<Question> list = this.questionRepository.findBySubjectContaining("sbb");
		// System.out.println("@@@@@@@@ : " + list.size());
		
		// 2. @Query
		List<Question> list = this.questionRepository.findBySubjectLike("sbb");
		System.out.println("@@@@@@@@ : " + list.size());
		
		// 2-2. sbb%, %sbb, %sbb%
		List<Question> list = this.questionRepository.findBySubjectLike("%sbb%");
		System.out.println("@@@@@@@@ : " + list.size());
		
	} //
	*/
	
	/*
	@Test
	void testJpa() {
		
		Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		System.out.println("@@@@@@@@@@" + q.getId());
		
	} //
	*/
	
	// 질문 수정 (제목만 수정)
	// update question
	// set subject = '수정할 값'
	// where id = 1;
	/*
	@Test
	void testJpa() {
		Optional<Question> optional = this.questionRepository.findById(1);
		if (optional.isPresent()) {
			Question q1 = optional.get();
			q1.setSubject("수정된 제목");
			this.questionRepository.save(q1);
		} // if
	} //
	*/
	
	// 질문 삭제
	/*
	@Test
	void testJpa() {
		System.out.println("총 질문 수 : " + this.questionRepository.count());
		
		// void this.questionRepository.deleteById(1);
		Optional<Question> oq = this.questionRepository.findById(1);
		Question q1 = oq.get();
		this.questionRepository.delete(q1);
		
		System.out.println("총 질문 수 : " + this.questionRepository.count());
	} //
	*/
	
	////////////////////////////////////////////////////////////////////////////
	// 1. 답변 저장
	@Autowired
	private AnswerRepository answerRepository;
	
	/*
	@Test
	void testJpa() {
		
		Optional<Question> optional = this.questionRepository.findById(2);
		if (optional.isPresent()) {
			Question q = optional.get();
			Answer a = new Answer();
			a.setContent("2번째 답글");
			a.setCreateDate(LocalDateTime.now());
			a.setQuestion(q);
			this.answerRepository.save(a);
		} // if
	} //
	*/
	
	
	// 데이터를 가져오는 방식
	// 1. 즉시 방식(Eager)
	// 2. 지연 방식(Lazy)
	/*
	// 질문 2에 대한 모든 답변글 조회
	@Transactional // 스프링프레임워크로 넣기 / 단위테스트여서 그렇다 아니면 가능하다.
	@Test
	void testJpa() {
		Optional<Question> optional =  this.questionRepository.findById(2);
		if (optional.isPresent()) {
			Question q = optional.get();
			// q 는 나온다.
			List<Answer> answerList = q.getAnswerList();
			answerList.stream().forEach(a -> System.out.println("@@@@@@@@@@" + a.getContent()));
		} // if
	} //
	*/
	
	@Autowired
	private QuestionService questionService;
	
	/*
	// 많은 질문 추가
	@Test
	void testJpa() {
		for (int i = 1; i <= 285; i++) {
			String subject = "질문 " + i;
			String content = "질문 내용 " + i;
			this.questionService.create(subject, content);
		} // for
	} //
	*/
	
	@Test
	   public void testSearchAll() {
	      String [] types = { "s", "c" };
	      String keyword = "마크";
	      
	      PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id").descending());
	      
	      Page<Question> result = this.questionRepository.searchAll(types, keyword, pageRequest);
	       
	      result
	          .getContent()    // List<Question>
	          .forEach(question -> System.out.printf("[%s]%s\n"
	                , question.getSubject() 
	                , question.getContent()) 
	                );
	   }

	
} // class
