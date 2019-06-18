package kr.or.ddit.user.service;

import kr.or.ddit.user.model.UserVO;

public interface IUserService {
	
	/**
	 * 
	* Method 		: getUser
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param userId
	* @return
	* Method 설명 	: 사용자 정보 조회
	 */
	UserVO getUser(String userId);
}
