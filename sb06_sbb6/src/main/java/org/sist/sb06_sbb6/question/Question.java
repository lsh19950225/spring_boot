package org.sist.sb06_sbb6.question;

import java.time.LocalDateTime;
import java.util.List;

import org.sist.sb06_sbb6.answer.Answer;
import org.sist.sb06_sbb6.user.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // 엔티티에서는 setter 안써도 된다. / 있어도 문제는 없다.
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 시퀀스 개념 / Oracle 이 아니라 이거 사용한다.
	private Integer id;
	
	@Column(length = 200) // 크기
	private String subject;
	
	@Column(columnDefinition = "TEXT") // 글자수 제한이 없는 긴 문자열
	private String content;
	
	private LocalDateTime createDate; // create_date 로 바뀐다.
	
	// cascade = CascadeType.REMOVE : 부모 테이블이 제거되면 자식들도 제거한다.
	// mappedBy = "question" : 매핑
	// fetch = FetchType.EAGER : 즉시 가져온다.
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<Answer> answerList; // 답변이 n개니까 List로 받는다.
	
	@ManyToOne
	private SiteUser author;
	
	// 수정한 날짜
	private LocalDateTime modifyDate;
	
} // class
