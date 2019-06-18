package kr.or.ddit.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.user.model.UserVO;

public class UserDao implements IUserDao{

	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
	
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
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		UserVO userVO = sqlSession.selectOne("user.getUser", userId);
		sqlSession.close();
		return userVO;
	}

}
