package kr.or.ddit.boardFile.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.boardFile.dao.BoardFileDaoTest;
import kr.or.ddit.boardFile.model.BoardFileVO;

public class BoardFileServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(BoardFileDaoTest.class);
	private IBoardFileService boardFileService;

	@Before
	public void setup() {
		boardFileService = new BoardFileService();
		logger.debug("@setup");
	}
	
	@Test
	public void boardFileListTest() {
		/***Given***/
		int writeId = 65;
		
 		/***When***/
		List<BoardFileVO> boardFileList = boardFileService.boardFileList(writeId);

		/***Then***/
		assertEquals("190213_GNB_travelfriends_W.png", boardFileList.get(0).getOriginal_file_name());
	}
	
	@Test
	public void insertFileTest() {
		/***Given***/
		
		
 		/***When***/
		BoardFileVO boardFileVO = new BoardFileVO(30, 65, "/Users/chewoop/Documents/upload/2019/06/3e0f6b80-a1ee-4198-b8ed-6ed09f552dfd.png", "190425_GNBbanner_honey_W.png");
		int inserCnt = boardFileService.insertFile(boardFileVO);
		
		/***Then***/
		assertEquals(1, inserCnt);
	}
	
	@Test
	public void deleteFileTest() {
		/***Given***/
		int fileId = 29;
		
 		/***When***/
		int deleteCnt = boardFileService.deleteFile(fileId);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}

}
