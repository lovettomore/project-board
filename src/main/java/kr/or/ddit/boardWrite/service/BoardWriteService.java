package kr.or.ddit.boardWrite.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.boardWrite.dao.BoardWriteDao;
import kr.or.ddit.boardWrite.dao.IBoardWriteDao;
import kr.or.ddit.boardWrite.model.BoardWriteVO;
import kr.or.ddit.paging.model.PageVO;

public class BoardWriteService implements IBoardWriteService{

	IBoardWriteDao boardWriteDao = new BoardWriteDao();
	
	/**
	 * 
	* Method 		: boardWriteList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param boardWriteVO
	* @return
	* Method 설명 	: 게시판 전체 조회
	 */
//	@Override
//	public List<BoardWriteVO> boardWriteList(BoardWriteVO boardWriteVO) {
//		List<BoardWriteVO> boardWriteList = new ArrayList<BoardWriteVO>();
//		boardWriteList = boardWriteDao.boardWriteList(boardWriteVO);
//		return boardWriteList;
//	}
	
	@Override
	public Map<String, Object> boardWriteList(PageVO pageVO) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("boardWriteList", boardWriteDao.boardWriteList(pageVO));
		
		//usersCnt > paginationSize 으로 변경
		int boardId = pageVO.getBoardId();
		int bwCnt = boardWriteDao.boardWriteCnt(boardId);
		
		//pageSize > pageVO.getPageSize();
		int paginationSize = (int)Math.ceil((double)bwCnt/pageVO.getPageSize());
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}

	/**
	 * 
	* Method 		: getBoardWrite
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param boardWriteVO
	* @return
	* Method 설명 	: 게시글 조회
	 */
	@Override
	public BoardWriteVO getBoardWrite(BoardWriteVO boardWriteVO) {
		BoardWriteVO vo = null;
		vo = boardWriteDao.getBoardWrite(boardWriteVO);
		return vo;
	}

	/**
	 * 
	* Method 		: insertBoardWrite
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: 게시글 등록
	 */
	@Override
	public int insertBoardWrite(BoardWriteVO boardWriteVO) {
		int insertCnt = 0;
		boardWriteDao.insertBoardWrite(boardWriteVO);
		insertCnt = boardWriteVO.getWriteId();
		return insertCnt;
	}

	/**
	 * 
	* Method 		: updateBoardWrite
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: 게시글 수정
	 */
	@Override
	public int updateBoardWrite(BoardWriteVO boardWriteVO) {
		int updateCnt = 0;
		updateCnt = boardWriteDao.updateBoardWrite(boardWriteVO);
		return updateCnt;
	}

	/**
	 * 
	* Method 		: deleteBoardWrite
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: 게시글 삭제(플래스 업데이트)
	 */
	@Override
	public int deleteBoardWrite(BoardWriteVO boardWriteVO) {
		int deleteCnt = 0;
		deleteCnt = boardWriteDao.deleteBoardWrite(boardWriteVO);
		return deleteCnt;
	}


}
