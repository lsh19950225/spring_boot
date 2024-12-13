package org.sist.sb06_sbb8.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionSearch {
	
	// "scw".split("")
	Page<Question> searchAll (String [] types, String keyword, Pageable pageable);
	
} // interface
