package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.user.model.UserVO;

public class BoardDaoTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDaoTest.class);
	
	private IBoardDao boardDao;
	
	@Before
	public void setup() {
		boardDao = new BoardDao();
		logger.debug("@setup");
	}
	
	@Test
	public void boardListTest() {
		/***Given***/

		/***When***/
		List<BoardVO> boardList = boardDao.boardList();
		
		/***Then***/
		//assertEquals("1", boardList.get(0).getBoardId());
		logger.debug("boardList : {}", boardList);
	}
	
	@Test
	public void useBoardListTest() {
		/***Given***/
		String use_yn = "Y";
		
		/***When***/
		List<BoardVO> useBoardList = boardDao.useBoardList(use_yn);
		
		/***Then***/
		assertEquals("공지사항", useBoardList.get(0).getName());
		logger.debug("boardVO  : {}", useBoardList);
	}
	
	@Test
	public void insertBoardTest() throws ParseException {
		/***Given***/
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		BoardVO boardVO = new BoardVO('2', "cony", "자유 게시판", "Y", sdf.parse("2019-05-01"));
//		
//		/***When***/
//		int insertCnt = boardDao.insertBoard(boardVO);
//		
//		/***Then***/
//		assertEquals(1, insertCnt);
	}
	
	@Test
	public void updateBoardTest() throws ParseException {
		/***Given***/
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		BoardVO boardVO = new BoardVO(1, "brown", "공지사항", "Y", sdf.parse("2019-05-01"));
//		
//		/***When***/
//		int updateCnt = boardDao.updateBoard(boardVO);
//		
//		/***Then***/
//		assertEquals(1, updateCnt);
	}

}
