package kr.or.ddit.boardComment.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.boardComment.dao.BoardCommentDao;
import kr.or.ddit.boardComment.dao.BoardCommentDaoTest;
import kr.or.ddit.boardComment.dao.IBoardCommentDao;
import kr.or.ddit.boardComment.model.BoardCommentVO;

public class BoardCommentServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(BoardCommentDaoTest.class);
	private IBoardCommentService boardCommentService;

	@Before
	public void setup(){
		boardCommentService = new BoardCommentService();
		logger.debug("@setup");
	}
	
	@Test
	public void commentListTest() {
		
		/***Given***/
		int writeId = 31;
		
		/***When***/
		List<BoardCommentVO> commentList = boardCommentService.commentList(writeId);

		/***Then***/
		assertEquals("brown", commentList.get(0).getUserId());
	}
	
	@Test
	public void insertComment(){
		/***Given***/
		BoardCommentVO boardCommentVO = new BoardCommentVO(31, "brown", "브라운이 쓴 댓글");
		
		/***When***/
		int insertCnt = boardCommentService.insertComment(boardCommentVO);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void updateComment(){
	
		/***Given***/
		BoardCommentVO boardCommentVO = new BoardCommentVO(5, 31, "달님이가 쓴 두번째 댓글");
		
		/***When***/
		int updateCnt = boardCommentService.updateComment(boardCommentVO);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void deleteComment(){
	
		/***Given***/
		BoardCommentVO boardCommentVO = new BoardCommentVO(11, 31);
		
		/***When***/
		int deleteCnt = boardCommentService.deleteComment(boardCommentVO);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}

}
