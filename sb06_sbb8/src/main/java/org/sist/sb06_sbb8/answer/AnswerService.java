package org.sist.sb06_sbb8.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.sist.sb06_sbb8.exception.DataNotFoundException;
import org.sist.sb06_sbb8.question.Question;
import org.sist.sb06_sbb8.user.SiteUser;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {
	
	private final AnswerRepository answerRepository;
	
	// 답변 쓰기
	public Answer create(Question question, String content
			, SiteUser author) {
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		
		answer.setAuthor(author); // 작성자 추가
		
		this.answerRepository.save(answer);
		return answer;
	} //
	
	// 답변 조회
    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    } //

    // 답변 수정
    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    } //
    
    // 답변 삭제
    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    } //
    
    // 답변 추천
 // 추천							질문				추천인(로그인 회원)
 	public void vote(Answer answer, SiteUser siteUser) {
 		answer.getVoter().add(siteUser);
 		this.answerRepository.save(answer);
 	} //
	
} // class
