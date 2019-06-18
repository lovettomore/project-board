package kr.or.ddit.board.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/regBoard")
public class RegBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RegBoardController.class);
	
	private IUserService userService;
	private IBoardService boardService;
       
	@Override
	public void init(ServletConfig config) throws ServletException {
		userService = new UserService();
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//boardList 객체를 regBoard.jsp에서 참 할 수 있도록 request객체에 속성으로 넣어준다.
		request.setAttribute("boardList", boardService.boardList());
		request.setAttribute("useBoardList", boardService.useBoardList("Y"));
		
		String boardId = request.getParameter("boardId");
		logger.debug("boardId : {}", boardId);
		logger.debug("useBoardList : {}", boardService.useBoardList("Y"));
		logger.debug("boardList : {}", boardService.boardList());
		
		
		//regBoard.jsp로 forward
		request.getRequestDispatcher("/board/regBoard.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		//세션으로 받아온 userId를 변수에 저장
		String userId = (String)request.getSession().getAttribute("userId");
		String name = request.getParameter("subject");
		String use_yn = request.getParameter("useBoard");
		String boardId1 = request.getParameter("boardId");
		
		logger.debug("userId : {}", userId);
		logger.debug("name : {}", name);
		logger.debug("use_yn : {}", use_yn);
		logger.debug("boardId1 : {}", boardId1);
		

		if(boardId1 != null) {
			int boardId = Integer.parseInt(boardId1);
			logger.debug("boardId : {}", boardId);
			BoardVO boardVo = new BoardVO(boardId, name, use_yn);
			boardService.updateBoard(boardVo);
			request.setAttribute("boardVo", boardVo);
			response.sendRedirect(request.getContextPath()+"/regBoard");
		}else {
			BoardVO boardVo = new BoardVO(userId, name, use_yn);
			boardService.insertBoard(boardVo);
			request.setAttribute("boardVo", boardVo);
			response.sendRedirect(request.getContextPath()+"/regBoard");
		}
		
	}

}
