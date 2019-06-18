package kr.or.ddit.board.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;

/**
 * Servlet implementation class UpdateBoardController
 */
@WebServlet("/updateBoard")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 15)
public class UpdateBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UpdateBoardController.class);
	
	private IBoardService boardService;
       
	@Override
	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		//세션으로 받아온 userId를 변수에 저장
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String userId = (String)request.getSession().getAttribute("userId");
		String name = request.getParameter("subject");
		String use_yn = request.getParameter("useBoard");
		
		//logger.debug("boardId : {}",boardId);
	
		
		//boardList 객체를 regBoard.jsp에서 참 할 수 있도록 request객체에 속성으로 넣어준다.
		request.setAttribute("boardList", boardService.boardList());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		BoardVO boardVO = null;
		
		
//		try {
//			boardVO = new BoardVO(boardId, userId, name, use_yn, sdf.parse("2019-05-05"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		int updateCnt = boardService.updateBoard(boardVO);
//		
//		if(updateCnt == 1) {
//			//request.getRequestDispatcher("/board/regBoard.jsp").forward(request, response);
//			response.sendRedirect(request.getContextPath() + "/regBoard?updateCnt=" + updateCnt);
//		}
//		
//		List<BoardVO> useBoardList = null;
//		String use_yn2 = "Y";
//		useBoardList = boardService.useBoardList(use_yn2);
//		ServletContext sc = request.getServletContext();
//		sc.setAttribute("useBoardList", useBoardList);
		
	}

}
