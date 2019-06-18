package kr.or.ddit.boardFile.dao;

import java.util.List;

import kr.or.ddit.boardFile.model.BoardFileVO;

public interface IBoardFileDao {

	/**
	 * 
	* Method 		: boardFileList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param writeId
	* @return
	* Method 설명 	: 파일 조회
	 */
	List<BoardFileVO> boardFileList(int writeId);
	
	/**
	 * 
	* Method 		: getBoardFile
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param fileId
	* @return
	* Method 설명 	: 각 파일 조회
	 */
	BoardFileVO getBoardFile(int fileId);
	
	/**
	 * 
	* Method 		: insertFile
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param boardFileVO
	* @return
	* Method 설명 	: 파일 등록
	 */
	int insertFile(BoardFileVO boardFileVO);
	
	/**
	 * 
	* Method 		: deleteFile
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param boardFileVO
	* @return
	* Method 설명 	: 파일 삭제
	 */
	int deleteFile(int fileId);
	
}
