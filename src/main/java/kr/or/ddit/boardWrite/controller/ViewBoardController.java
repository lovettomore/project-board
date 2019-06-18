package kr.or.ddit.boardWrite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.boardComment.model.BoardCommentVO;
import kr.or.ddit.boardComment.service.BoardCommentService;
import kr.or.ddit.boardComment.service.IBoardCommentService;
import kr.or.ddit.boardFile.model.BoardFileVO;
import kr.or.ddit.boardFile.service.BoardFileService;
import kr.or.ddit.boardFile.service.IBoardFileService;
import kr.or.ddit.boardWrite.model.BoardWriteVO;
import kr.or.ddit.boardWrite.service.BoardWriteService;
import kr.or.ddit.boardWrite.service.IBoardWriteService;

/**
 * Servlet implementation class ViewVoardController
 */
@WebServlet("/viewBoard")
public class ViewBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ViewBoardController.class);
    
    private IBoardWriteService boardWriteService;
    private IBoardCommentService boardCommentService;
    private IBoardFileService boardFileService;
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		boardWriteService = new BoardWriteService();
		boardCommentService = new BoardCommentService();
		boardFileService = new BoardFileService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.debug("ViewBoardController doGet() ");
		
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		int writeId = Integer.parseInt(request.getParameter("writeId"));
		
		/* boardWrite */
		BoardWriteVO bwVO = new BoardWriteVO();
		bwVO.setBoardId(boardId);
		bwVO.setWriteId(writeId);
		
		BoardWriteVO boardWriteVO = boardWriteService.getBoardWrite(bwVO);
		request.setAttribute("boardWriteVO", boardWriteVO);
		
		/* commnet */
		List<BoardCommentVO> boardCommentList = boardCommentService.commentList(writeId);
		request.setAttribute("boardCommentList", boardCommentList);
		
		/* file */
		List<BoardFileVO> boardFileList = boardFileService.boardFileList(writeId);
		request.setAttribute("boardFileList", boardFileList);
	
		request.getRequestDispatcher("/board/viewBoard.jsp").forward(request, response);
		
	}

}
