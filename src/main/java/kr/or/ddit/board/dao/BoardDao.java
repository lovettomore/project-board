package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.user.dao.IUserDao;

public class BoardDao implements IBoardDao {
	
	
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardVO> boardList = sqlSession.selectList("board.boardList");
		sqlSession.close();
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		BoardVO getboard = sqlSession.selectOne("board.getBoard", boardId);
		sqlSession.close();
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardVO> useBoardList = sqlSession.selectList("board.useBoardList", use_yn);
		sqlSession.close();
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int insertCnt = sqlSession.insert("board.insertBoard", boardVO);
		sqlSession.commit();
		sqlSession.close();
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int updateCnt = sqlSession.update("board.updateBoard", boardVO);
		sqlSession.commit();
		sqlSession.close();
		return updateCnt;
	}

	
}
