package kr.or.ddit.boardComment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.boardComment.model.BoardCommentVO;
import kr.or.ddit.boardWrite.model.BoardWriteVO;
import kr.or.ddit.mybatis.MyBatisUtil;

public class BoardCommentDao implements IBoardCommentDao{

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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardCommentVO> commentList = sqlSession.selectList("boardComment.commentList", writeId);
		sqlSession.close();
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int insertComment = sqlSession.insert("boardComment.insertComment", boardCommentVO);
		sqlSession.commit();
		sqlSession.close();
		return insertComment;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int updateComment = sqlSession.update("boardComment.updateComment", boardCommentVO);
		sqlSession.commit();
		sqlSession.close();
		return updateComment;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int deleteComment = sqlSession.update("boardComment.deleteComment", boardCommentVO);
		sqlSession.commit();
		sqlSession.close();
		return deleteComment;
	}

}
