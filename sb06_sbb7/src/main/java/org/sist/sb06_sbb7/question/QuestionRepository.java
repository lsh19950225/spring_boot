package org.sist.sb06_sbb7.question;

import java.util.List;

import org.sist.sb06_sbb7.answer.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
	
	// 페이징 처리 + 검색 Specification<Question> spec
	Page<Question> findAll( Specification<Question> spec, Pageable pageable );
	
	// 페이징 처리 + 검색 @Query / 쿼리메서드 아니다.
	@Query("select "
            + "distinct q "
            + "from Question q " 
            + "left outer join SiteUser u1 on q.author=u1 "
            + "left outer join Answer a on a.question=q "
            + "left outer join SiteUser u2 on a.author=u2 "
            + "where "
            + "   q.subject like %:kw% "
            + "   or q.content like %:kw% "
            + "   or u1.username like %:kw% "
            + "   or a.content like %:kw% "
            + "   or u2.username like %:kw% ")
	// @Param("kw") String kw 이 쿼리랑 매칭된다.
	Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable );
	
} // interface
