package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.user.model.UserVO;

public class UserServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
	private IUserService userService;
	
	@Before
	public void setup() {
		userService = new UserService();
		logger.debug("@setup");
	}

	@Test
	public void UserServiceTest() {
		/***Given***/
		String userId = "brown";
		
		/***When***/
		UserVO userVO = userService.getUser(userId);

		/***Then***/
		assertEquals("브라운 이녀석", userVO.getName());
		logger.debug("userVO : {}", userVO);
	}

}
