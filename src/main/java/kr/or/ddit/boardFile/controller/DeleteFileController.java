package kr.or.ddit.boardFile.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.boardComment.service.BoardCommentService;
import kr.or.ddit.boardFile.service.BoardFileService;
import kr.or.ddit.boardFile.service.IBoardFileService;
import kr.or.ddit.boardWrite.model.BoardWriteVO;
import kr.or.ddit.boardWrite.service.BoardWriteService;
import kr.or.ddit.boardWrite.service.IBoardWriteService;

/**
 * Servlet implementation class DeleteFileController
 */
@WebServlet("/deleteFile")
public class DeleteFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DeleteFileController.class);
	private IBoardFileService boardFileService;
	private IBoardWriteService boardWriteService;
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		boardFileService = new BoardFileService();
		boardWriteService = new BoardWriteService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("DeleteFileController doGet");
		
		int fileId = Integer.parseInt(request.getParameter("fileId"));
		int writeId = Integer.parseInt(request.getParameter("writeId"));
		String userId = request.getParameter("userId");
		
		BoardWriteVO bWVO = new BoardWriteVO();
		bWVO.setWriteId(writeId);
		
		BoardWriteVO boardWriteVO = boardWriteService.getBoardWrite(bWVO);
		int boardId = boardWriteVO.getBoardId();
		logger.debug("boardId : {}", boardId);
		
		int deleteCnt = boardFileService.deleteFile(fileId);
		if(deleteCnt != 0) {
			response.sendRedirect(request.getContextPath()+"/updateWriteBoard?boardId="+boardId+"&writeId="+writeId);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
