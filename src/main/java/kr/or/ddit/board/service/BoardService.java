package kr.or.ddit.board.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.model.BoardVO;

public class BoardService implements IBoardService {
	
	IBoardDao boardDao = new BoardDao();
	
	/**
	 * 
	* Method 		: boardList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: 전체 게시판 조회
	 */
	@Override
	public List<BoardVO> boardList() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		boardList = boardDao.boardList();
		return boardList;
	}
	
	/**
	 * 
	* Method 		: getboard
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param boardId
	* @return
	* Method 설명 	: 게시판 아이디로 게시판 조회
	 */
	@Override
	public BoardVO getBoard(int boardId) {
		BoardVO getboard = null;
		getboard = boardDao.getBoard(boardId);
		return getboard;
	}
	
	/**
	 * 
	* Method 		: useBoardList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param use_yn
	* @return
	* Method 설명 	: 사용중인 게시판 조회
	 */
	@Override
	public List<BoardVO> useBoardList(String use_yn) {
		List<BoardVO> useBoardList = new ArrayList<BoardVO>();
		useBoardList = boardDao.useBoardList(use_yn);
		return useBoardList;
	}

	/**
	 * 
	* Method 		: insertBoard
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: 게시판 생성
	 */
	@Override
	public int insertBoard(BoardVO boardVO) {
		int insertCnt = 0;
		insertCnt = boardDao.insertBoard(boardVO);
		return insertCnt;
	}

	/**
	 * 
	* Method 		: updateBoard
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: 게시판 수정
	 */
	@Override
	public int updateBoard(BoardVO boardVO) {
		int updateCnt = 0;
		updateCnt = boardDao.updateBoard(boardVO);
		return updateCnt;
	}
}
