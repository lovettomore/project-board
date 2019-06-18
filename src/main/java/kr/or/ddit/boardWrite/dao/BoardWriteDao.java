package kr.or.ddit.boardWrite.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.boardWrite.model.BoardWriteVO;
import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.paging.model.PageVO;

public class BoardWriteDao implements IBoardWriteDao{
	
	
	/**
	 * 
	* Method 		: boardWriteList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param boardWriteVO
	* @return
	* Method 설명 	: 게시판 전체 조회
	 */
	@Override
	public List<BoardWriteVO> boardWriteList(PageVO pageVO) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardWriteVO> boardWriteList = sqlSession.selectList("boardWrite.boardWriteList", pageVO);
		sqlSession.close();
		return boardWriteList;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		BoardWriteVO getBoardWrite = sqlSession.selectOne("boardWrite.getBoardWrite", boardWriteVO);
		sqlSession.close();
		return getBoardWrite;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		sqlSession.insert("boardWrite.insertBoardWrite", boardWriteVO);
		int insertBoardWrite = boardWriteVO.getWriteId();
		sqlSession.commit();
		sqlSession.close();
		return insertBoardWrite;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int updateBoardWrite = sqlSession.update("boardWrite.updateBoardWrite", boardWriteVO);
		sqlSession.commit();
		sqlSession.close();
		return updateBoardWrite;
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int deleteBoardWrite = sqlSession.update("boardWrite.deleteBoardWrite", boardWriteVO);
		sqlSession.commit();
		sqlSession.close();
		return deleteBoardWrite;
	}

	@Override
	public int boardWriteCnt(int boardId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int bwCnt = (Integer)sqlSession.selectOne("boardWrite.boardWriteCnt", boardId);
		sqlSession.close();
		return bwCnt;
	}

}
