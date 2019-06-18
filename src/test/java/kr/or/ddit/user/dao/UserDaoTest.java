package kr.or.ddit.user.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.user.model.UserVO;

public class UserDaoTest {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	private IUserDao userDao;
	
	@Before
	public void setup() {
		userDao = new UserDao();
		logger.debug("@setup");
	}

	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "brown";
		
		/***When***/
		UserVO userVO = userDao.getUser(userId);

		/***Then***/
		assertEquals("브라운 이녀석", userVO.getName());
		logger.debug("userVO : {}", userVO);
	}

}
