package org.sist.sb06_sbb6.question;

import java.util.List;

import org.sist.sb06_sbb6.answer.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
																						// 					pk 타입
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	// CRUD 메서드 이미 내장
	Question findBySubject(String subject);
	
	// 1. query method
	// 메소드 이름으로 쿼리를 자동으로 생성해주는 기능
	// find컬럼명Like
	// find컬럼명Containing
	List<Question> findBySubjectContaining(String subject);
	
	/*
	// 2. @Query
	@Query("select q from question q where q.subject like %:subject%")
	List<Question> findBySubjectLike(@Param("subject") String subject);
	*/
	
	// 2-2 쿼리 메서드
	List<Question> findBySubjectLike(String subject);
	
	// where subject=? and content=?
	Question findBySubjectAndContent(String subject, String content);
	
	// @Query("select a from answer a where question.id=:qid")
	// List<Answer> findByQuestion(@Param("qid") Integer question_id);
	
	// 페이징 처리 ( ***암기 )
	Page<Question> findAll(Pageable pageable);
	
} // interface
