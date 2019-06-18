package kr.or.ddit.boardComment.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.boardComment.model.BoardCommentVO;

public class BoardCommentDaoTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardCommentDaoTest.class);
	private IBoardCommentDao boardCommentDao;

	@Before
	public void setup(){
		boardCommentDao = new BoardCommentDao();
		logger.debug("@setup");
	}
	
	@Test
	public void commentListTest() {
		
		/***Given***/
		int writeId = 31;
		
		/***When***/
		List<BoardCommentVO> commentList = boardCommentDao.commentList(writeId);

		/***Then***/
		assertEquals("brown", commentList.get(0).getUserId());
	}
	
	@Test
	public void insertComment(){
		/***Given***/
		BoardCommentVO boardCommentVO = new BoardCommentVO(31, "brown", "브라운이 쓴 댓글");
		
		/***When***/
		int insertCnt = boardCommentDao.insertComment(boardCommentVO);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void updateComment(){
	
		/***Given***/
		BoardCommentVO boardCommentVO = new BoardCommentVO(5, 31, "달님이가 쓴 두번째 댓글");
		
		/***When***/
		int updateCnt = boardCommentDao.updateComment(boardCommentVO);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void deleteComment(){
	
		/***Given***/
		BoardCommentVO boardCommentVO = new BoardCommentVO(11, 31);
		
		/***When***/
		int deleteCnt = boardCommentDao.deleteComment(boardCommentVO);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}

}
