package org.sist.sb06_sbb8.answer;

import java.time.LocalDateTime;
import java.util.Set;

import org.sist.sb06_sbb8.question.Question;
import org.sist.sb06_sbb8.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
// @Builder // setter 말고 이걸 사용해도 된다.
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	// 연관관계를 맺어줘야된다. / 아니면 오류뜬다.
	@ManyToOne // 1대n 질문 1개에 답변이 n개
	private Question question; // 다르다. / 주의 / FK / question_id 로 나온다.
	
	@ManyToOne
	private SiteUser author;
	
	// 수정한 날짜
	private LocalDateTime modifyDate;
	
		// 답변 - 회원 다대다 연관관계 : 테이블이 생성된다. - 복합키로 잡힌다.
		// Set : 좋아요를 한번 밖에 못누른다.
		@ManyToMany
		private Set<SiteUser> voter;
	
} // class
