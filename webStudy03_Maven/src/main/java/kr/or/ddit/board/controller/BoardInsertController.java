package kr.or.ddit.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;

@CommandHandler
public class BoardInsertController {
	@URIMapping("board/boardInsert.do")
	public String boardForm(HttpServletRequest req, HttpServletRequest resp) {
		return "board/boardForm";
	}
}
