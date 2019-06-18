package kr.or.ddit.boardComment.controller;

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
import kr.or.ddit.boardWrite.model.BoardWriteVO;
import kr.or.ddit.boardWrite.service.BoardWriteService;
import kr.or.ddit.boardWrite.service.IBoardWriteService;

/**
 * Servlet implementation class BoardCommentController
 */
@WebServlet("/comment")
public class WriteBoardCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(WriteBoardCommentController.class);  
    
    private IBoardCommentService boardCommentService;
    private IBoardWriteService boardWriteService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		boardCommentService = new BoardCommentService();
		boardWriteService = new BoardWriteService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int writeId = Integer.parseInt(request.getParameter("writeId"));
		String content = request.getParameter("comment");
		
		//vo에 파라미터 셋팅
		BoardWriteVO bwVO = new BoardWriteVO();
		bwVO.setWriteId(writeId);
		
		BoardWriteVO vo = boardWriteService.getBoardWrite(bwVO);
		
		int boardId = vo.getBoardId();
		String userId = vo.getUserId();

		BoardCommentVO boardCommentVO = new BoardCommentVO(writeId, userId, content);
		
		int inserCnt = boardCommentService.insertComment(boardCommentVO);
		
		if(inserCnt == 1) {
			List<BoardCommentVO> BoardCommentList = boardCommentService.commentList(writeId);
			request.setAttribute("BoardCommentList", BoardCommentList);
			response.sendRedirect(request.getContextPath()+"/viewBoard?boardId="+boardId+"&writeId="+writeId);
		}
		
	}

}
