package kr.or.ddit.user.dao;

import kr.or.ddit.user.model.UserVO;

public interface IUserDao {
	
	/**
	 * 
	* Method 		: getUser
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param userId
	* @return
	* Method 설명 	: 사용자 정보 조회
	 */
	public UserVO getUser(String userId);
}
