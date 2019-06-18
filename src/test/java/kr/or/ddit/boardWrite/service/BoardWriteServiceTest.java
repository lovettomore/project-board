package kr.or.ddit.boardWrite.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.boardWrite.dao.BoardWriteDao;
import kr.or.ddit.boardWrite.dao.BoardWriteDaoTest;
import kr.or.ddit.boardWrite.dao.IBoardWriteDao;
import kr.or.ddit.boardWrite.model.BoardWriteVO;
import kr.or.ddit.paging.model.PageVO;

public class BoardWriteServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(BoardWriteDaoTest.class);
	private IBoardWriteService boardWriteService;

	@Before
	public void setup() {
		boardWriteService = new BoardWriteService();
		logger.debug("@setup");
	}

	@Test
	public void boardWriteListTest() {
		/***Given***/
		int boardId = 21;
		String use_yn = "Y";
		
		PageVO pageVO = new PageVO(1, 10, 21);
		
		/***When***/
		List<BoardWriteVO> boardWriteList = (List<BoardWriteVO>) boardWriteService.boardWriteList(pageVO);

		/***Then***/
		assertEquals("brown", boardWriteList.get(0).getUserId());
		logger.debug("boardWriteList : {}", boardWriteList);
	}
	
	@Test
	public void getBoardWriteTest() {
		/***Given***/
		int boardId = 21;
		int writeId = 2;
		String use_yn = "Y";
		
		BoardWriteVO boardWriteVO = new BoardWriteVO();
		boardWriteVO.setBoardId(boardId);
		boardWriteVO.setWriteId(writeId);
		boardWriteVO.setUse_yn(use_yn);
		
		/***When***/
		
		BoardWriteVO getBoardWrite = boardWriteService.getBoardWrite(boardWriteVO);

		/***Then***/
		assertEquals("brown", getBoardWrite.getUserId());
		logger.debug("getBoardWrite : {}", getBoardWrite);
	}
	
	@Test
	public void insertBoardWriteTest() throws ParseException {
		/***Given***/
		int insertCnt = 0;
		int parent_seq = 0;
		SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
		//BoardWriteVO boardWriteVO = new BoardWriteVO(21, "brown", "ㅎㅎㅎㅎㅎㅎㅎ", "ㅎㅎㅎㅎㅎㅎ", "Y");
		
		/***When***/
		//insertCnt = boardWriteDao.insertBoardWrite(boardWriteVO);

		/***Then***/
		//assertEquals(1, insertCnt);
	}
	
	@Test
	public void updateBoardWriteTest() throws ParseException{
		/***Given***/
		int updateCnt = 0;
		int parent_seq = 0;
		SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
		BoardWriteVO boardWriteVO = new BoardWriteVO(18, 21, "cony", "달님이가 쓴글", "게시글 내용 입니다.");
		
		/***When***/
		updateCnt = boardWriteService.updateBoardWrite(boardWriteVO);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void deleteBoardWriteTest() throws ParseException{
		/***Given***/
		BoardWriteVO boardWriteVO = new BoardWriteVO(18, 21, "cony", "달님이가 쓴글", "게시글 내용 입니다.");
		
		/***When***/
		int deleteCnt = boardWriteService.deleteBoardWrite(boardWriteVO);

		/***Then***/
		assertEquals(1, deleteCnt);
	}

}
