package kr.or.ddit.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	IUserService userService;
	IBoardService boardService;
       
	@Override
	public void init(ServletConfig config) throws ServletException {
		userService = new UserService();
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("LoginController doGet()");
		
		//만약에 request에 빈값이 있으면 null을 반환한다.
		if(request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				logger.debug("cookie : {}, {}", cookie.getName(), cookie.getValue());
			}
		};
		
		//현재 userId 세션 여부 확인
		logger.debug("info {}", request.getSession().getAttribute("USER_INFO"));
		
		//현재 세션에 userId가 남아 있으면 index.jsp로 이동, 그렇지 않으면 login.jsp 유지
		if(request.getSession().getAttribute("USER_INFO") != null)
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		else
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("LoginController doPost()");
		
		//parmeter 확인
		logger.debug("parameter userId : {}", request.getParameter("userId"));
		logger.debug("parameter password : {}", request.getParameter("pass"));
		
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		String encryptPassword = KISA_SHA256.encrypt(pass);
		
		//UserVO 서비스 객체에서 getUser받아옴
		UserVO userVO = userService.getUser(userId);
		
		List<BoardVO> useBoardList = new ArrayList<BoardVO>();
		
		String use_yn = "Y";
		useBoardList = boardService.useBoardList(use_yn);
		
		//아이디와 비밀번호가 db데이터와 일치하면 index.jsp로 이동 그렇지 않으면 login페이지 유지
		//userVO != null > userVO에 데이터가 있음
		if(userVO != null && encryptPassword.equals(userVO.getPass())) {
			
			//30일 쿠키 생성
			int cookieMaxAge = 60 * 60 * 24 * 30;
			
			//userId에 대한 쿠키 생성
			Cookie userIdCookie = new Cookie("userId", userId);
			userIdCookie.setMaxAge(cookieMaxAge);

			response.addCookie(userIdCookie);
			
			logger.debug("여기가 시작이에요");
			request.getSession().setAttribute("useBoardList", useBoardList);
			logger.debug("여기까지는 오나용?");
			request.getSession().setAttribute("userId", userVO.getUserId());
			request.getSession().setAttribute("USER_INFO", userVO);
			
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else {
			//로그인시 입력한 아이디 유지
			request.setAttribute("userId", userId);
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
		}
	}

}
