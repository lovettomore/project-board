package kr.or.ddit.boardFile.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.boardFile.dao.BoardFileDao;
import kr.or.ddit.boardFile.dao.IBoardFileDao;
import kr.or.ddit.boardFile.model.BoardFileVO;

public class BoardFileService implements IBoardFileService {

	IBoardFileDao boardFileDao = new BoardFileDao();

	/**
	 * 
	* Method 		: boardFileList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param writeId
	* @return
	* Method 설명 	: 파일 조회
	 */
	@Override
	public List<BoardFileVO> boardFileList(int writeId) {
		List<BoardFileVO> boardFileList = new ArrayList<BoardFileVO>();
		boardFileList = boardFileDao.boardFileList(writeId);
		return boardFileList;
	}

	/**
	 * 
	* Method 		: getBoardFile
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param fileId
	* @return
	* Method 설명 	: 각 파일 조회
	 */
	@Override
	public BoardFileVO getBoardFile(int fileId) {
		BoardFileVO getBoardFile = null;
		getBoardFile = boardFileDao.getBoardFile(fileId);
		return getBoardFile;
	}
	
	/**
	 * 
	* Method 		: insertFile
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param boardFileVO
	* @return
	* Method 설명 	: 파일 등록
	 */
	@Override
	public int insertFile(BoardFileVO boardFileVO) {
		int insertFile = 0;
		insertFile = boardFileDao.insertFile(boardFileVO);
		return insertFile;
	}

	
	
	/**
	 * 
	* Method 		: deleteFile
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param boardFileVO
	* @return
	* Method 설명 	: 파일 삭제
	 */
	
	@Override
	public int deleteFile(int fileId) {
		int deleteFile = 0;
		deleteFile = boardFileDao.deleteFile(fileId);
		return deleteFile;
	}


	

	
}
