package kr.or.ddit.boardFile.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.boardFile.model.BoardFileVO;
import kr.or.ddit.mybatis.MyBatisUtil;

public class BoardFileDao implements IBoardFileDao{

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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardFileVO> boardFileList = sqlSession.selectList("boardFile.boardFileList", writeId);
		sqlSession.close();
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		BoardFileVO getBoardFile = sqlSession.selectOne("boardFile.getBoardFile", fileId);
		sqlSession.close();
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int insertFile = sqlSession.insert("boardFile.insertFile", boardFileVO);
		sqlSession.commit();
		sqlSession.close();
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int deleteFile = sqlSession.delete("boardFile.deleteFile", fileId);
		sqlSession.commit();
		sqlSession.close();
		return deleteFile;
	}

	

}
