package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;

public interface IBoardDao {
	
	/**
	 * 
	* Method 		: boardList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: 전체 게시판 조회
	 */
	List<BoardVO> boardList();
	
	/**
	 * 
	* Method 		: getboard
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param boardId
	* @return
	* Method 설명 	: 게시판 아이디로 게시판 조회
	 */
	BoardVO getBoard(int boardId);
	
	/**
	 * 
	* Method 		: useBoardList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param use_yn
	* @return
	* Method 설명 	: 사용중인 게시판 조회
	 */
	List<BoardVO> useBoardList(String use_yn);
	
	/**
	 * 
	* Method 		: insertBoard
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: 게시판 생성
	 */
	int insertBoard(BoardVO boardVO);
	
	/**
	 * 
	* Method 		: updateBoard
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: 게시판 수정
	 */
	int updateBoard(BoardVO boardVO);
	

}
