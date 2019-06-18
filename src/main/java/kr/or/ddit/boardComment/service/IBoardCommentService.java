package kr.or.ddit.boardComment.service;

import java.util.List;

import kr.or.ddit.boardComment.model.BoardCommentVO;

public interface IBoardCommentService {

	/**
	 * 
	* Method 		: commentList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param writeId
	* @return
	* Method 설명 	: 전체 댓글 조회
	 */
	List<BoardCommentVO> commentList(int writeId);
	
	/**
	 * 
	* Method 		: insertComment
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param boardCommentVO
	* @return
	* Method 설명 	: 댓글 등록
	 */
	int insertComment(BoardCommentVO boardCommentVO);
	
	/**
	 * 
	* Method 		: updateComment
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param boardCommentVO
	* @return
	* Method 설명 	: 댓글 수정
	 */
	int updateComment(BoardCommentVO boardCommentVO);
	
	/**
	 * 
	* Method 		: deleteComment
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param boardCommentVO
	* @return
	* Method 설명 	: 댓글 삭제(플래그 업데이트)
	 */
	int deleteComment(BoardCommentVO boardCommentVO);
	
	
}
