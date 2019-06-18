package kr.or.ddit.boardWrite.dao;

import java.util.List;

import kr.or.ddit.boardWrite.model.BoardWriteVO;
import kr.or.ddit.paging.model.PageVO;

public interface IBoardWriteDao {
	
	/**
	 * 
	* Method 		: boardWriteList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param boardWriteVO
	* @return
	* Method 설명 	: 게시판 전체 조회
	 */
	List<BoardWriteVO> boardWriteList(PageVO pageVO);
	
	/**
	 * 
	* Method 		: getBoardWrite
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param boardWriteVO
	* @return
	* Method 설명 	: 게시글 조회
	 */
	BoardWriteVO getBoardWrite(BoardWriteVO boardWriteVO); 
	
	/**
	 * 
	* Method 		: insertBoardWrite
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: 게시글 등록
	 */
	int insertBoardWrite(BoardWriteVO boardWriteVO);
	
	/**
	 * 
	* Method 		: updateBoardWrite
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: 게시글 수정
	 */
	int updateBoardWrite(BoardWriteVO boardWriteVO);
	
	/**
	 * 
	* Method 		: deleteBoardWrite
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: 게시글 삭제(플래스 업데이트)
	 */
	int deleteBoardWrite(BoardWriteVO boardWriteVO);
	
	/**
	 * 
	* Method 		: usersCnt
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: 사용자 게시글 건수 조회
	 */
	int boardWriteCnt(int boardId);
	
}
