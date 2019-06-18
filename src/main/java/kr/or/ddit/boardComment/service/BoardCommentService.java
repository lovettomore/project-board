package kr.or.ddit.boardComment.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.boardComment.dao.BoardCommentDao;
import kr.or.ddit.boardComment.dao.IBoardCommentDao;
import kr.or.ddit.boardComment.model.BoardCommentVO;

public class BoardCommentService implements IBoardCommentService{
	
	IBoardCommentDao boardCommentDao = new BoardCommentDao();
	
	/**
	 * 
	* Method 		: commentList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param writeId
	* @return
	* Method 설명 	: 전체 댓글 조회
	 */
	@Override
	public List<BoardCommentVO> commentList(int writeId) {
		List<BoardCommentVO> commentList = new ArrayList<BoardCommentVO>();
		commentList = boardCommentDao.commentList(writeId);
		return commentList;
	}

	/**
	 * 
	* Method 		: insertComment
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param boardCommentVO
	* @return
	* Method 설명 	: 댓글 등록
	 */
	@Override
	public int insertComment(BoardCommentVO boardCommentVO) {
		int insertCnt = 0;
		insertCnt = boardCommentDao.insertComment(boardCommentVO);
		return insertCnt;
	}

	/**
	 * 
	* Method 		: updateComment
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param boardCommentVO
	* @return
	* Method 설명 	: 댓글 수정
	 */
	@Override
	public int updateComment(BoardCommentVO boardCommentVO) {
		int updateCnt = 0;
		updateCnt = boardCommentDao.updateComment(boardCommentVO);
		return updateCnt;
	}

	/**
	 * 
	* Method 		: deleteComment
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param boardCommentVO
	* @return
	* Method 설명 	: 댓글 삭제(플래그 업데이트)
	 */
	@Override
	public int deleteComment(BoardCommentVO boardCommentVO) {
		int deleteCnt = 0;
		deleteCnt = boardCommentDao.deleteComment(boardCommentVO);
		return deleteCnt;
	}

}
