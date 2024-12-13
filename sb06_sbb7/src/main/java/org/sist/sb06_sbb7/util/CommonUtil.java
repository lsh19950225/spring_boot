package org.sist.sb06_sbb7.util;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {
	
	// 일반 문자열 -> 서식 문자열 변환 메서드
	public String markdown(String markdown) {
		Parser parser = Parser.builder().build();
		Node document = parser.parse(markdown);
		
		HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();
		return htmlRenderer.render(document); // String 를 돌린다.
	} //
	
} // class
