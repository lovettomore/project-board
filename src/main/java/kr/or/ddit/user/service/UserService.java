package kr.or.ddit.user.service;

import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDao;
import kr.or.ddit.user.model.UserVO;

public class UserService implements IUserService{

	IUserDao userDao = new UserDao();
	
	/**
	 * 
	* Method 		: getUser
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param userId
	* @return
	* Method 설명 	: 사용자 정보 조회
	 */
	@Override
	public UserVO getUser(String userId) {
		UserVO userVO = null;
		userVO = userDao.getUser(userId);
		return userVO;
	}

}
