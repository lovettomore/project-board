package kr.or.ddit.boardWrite.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.boardWrite.model.BoardWriteVO;
import kr.or.ddit.boardWrite.service.BoardWriteService;
import kr.or.ddit.boardWrite.service.IBoardWriteService;

/**
 * Servlet implementation class DeleteBoardController
 */
@WebServlet("/deleteBoard")
public class DeleteBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DeleteBoardController.class);
	private IBoardWriteService boardWriteService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		boardWriteService = new BoardWriteService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		logger.debug("DeleteBoardController doPost()");

		int writeId = Integer.parseInt(request.getParameter("writeId"));
		
		//vo에 파라미터 셋팅
		BoardWriteVO bwVO = new BoardWriteVO();
		bwVO.setWriteId(writeId);
		
		BoardWriteVO vo = boardWriteService.getBoardWrite(bwVO);
		
		int boardId = vo.getBoardId();
		String userId = vo.getUserId();
		String use_yn = "N";
		
		
		BoardWriteVO boardWriteVO = new BoardWriteVO(writeId, boardId, userId, use_yn);
		int deleteCnt = boardWriteService.deleteBoardWrite(boardWriteVO);
		
		response.sendRedirect(request.getContextPath()+"/listBoard?boardId="+boardId);
	}

}
