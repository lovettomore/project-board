package kr.or.ddit.boardWrite.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.boardWrite.model.BoardWriteVO;
import kr.or.ddit.boardWrite.service.BoardWriteService;
import kr.or.ddit.boardWrite.service.IBoardWriteService;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;

/**
 * Servlet implementation class ListBoardController
 */
@WebServlet("/listBoard")
public class ListBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ListBoardController.class);
	
    private IBoardWriteService boardWriteService;   
    private IBoardService boardService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		boardWriteService = new BoardWriteService();
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		logger.debug("boardId : {}", boardId);
		
		int page = 0;
		int pageSize = 0;
		
		if(request.getParameter("page") == null && request.getParameter("pageSize") == null) {	
			page = 1;
			pageSize = 10;
		}else {
			page = Integer.parseInt(request.getParameter("page"));
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		
		BoardWriteVO boardWriteVO = new BoardWriteVO();
		BoardVO boardVO = new BoardVO();
		PageVO pageVO = new PageVO(page, pageSize, boardId);
		
		Map<String, Object> resultMap = boardWriteService.boardWriteList(pageVO);
		List<BoardWriteVO> boardWriteList = (List<BoardWriteVO>) resultMap.get("boardWriteList");
		
		boardWriteVO.setBoardId(boardId);
		
		List<BoardVO> dbboardId = boardService.boardList();
		
		int paginationSize = (int) resultMap.get("paginationSize");
			
		String boardName = "";
		for(BoardVO boardID : dbboardId) {
			if(boardId == boardID.getBoardId()) {
				boardName = boardID.getName();
			}
		}
		
		request.setAttribute("boardName", boardName);
		request.setAttribute("boardWriteList", boardWriteList);
		request.setAttribute("paginationSize", paginationSize); //request에 userList를 셋팅
		request.setAttribute("pageVO", pageVO);
		request.getRequestDispatcher("/board/listBoard.jsp").forward(request, response);
	}


}
