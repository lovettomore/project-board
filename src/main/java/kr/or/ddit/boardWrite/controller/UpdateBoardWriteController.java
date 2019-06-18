package kr.or.ddit.boardWrite.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.boardFile.model.BoardFileVO;
import kr.or.ddit.boardFile.service.BoardFileService;
import kr.or.ddit.boardFile.service.IBoardFileService;
import kr.or.ddit.boardWrite.model.BoardWriteVO;
import kr.or.ddit.boardWrite.service.BoardWriteService;
import kr.or.ddit.boardWrite.service.IBoardWriteService;
import kr.or.ddit.util.PartUtil;

/**
 * Servlet implementation class UpdateBoardWriteController
 */
@WebServlet("/updateWriteBoard")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 15)
public class UpdateBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UpdateBoardWriteController.class);
	private IBoardWriteService boardWriteService;
	private IBoardFileService boardFileService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		boardWriteService = new BoardWriteService();
		boardFileService = new BoardFileService();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("UpdateBoardWriteController doGet()");
		
		//parmeter 변수에 담기
		int writeId = Integer.parseInt(request.getParameter("writeId"));
		
		//vo에 파라미터 셋팅
		BoardWriteVO bwVO = new BoardWriteVO();
		bwVO.setWriteId(writeId);
		
		BoardWriteVO vo = boardWriteService.getBoardWrite(bwVO);
		
		int boardId = vo.getBoardId();
		String userId = vo.getUserId();
		
		/* file */
		List<BoardFileVO> boardFileList = boardFileService.boardFileList(writeId);
		
		request.setAttribute("boardFileList", boardFileList);
		request.setAttribute("boardId", boardId);
		request.setAttribute("userId", userId);
		request.setAttribute("boardWriteVO", vo);
		request.getRequestDispatcher("/board/updateBoard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("UpdateBoardWriteController doPost()");
		request.setCharacterEncoding("utf-8");
		
		int writeId = Integer.parseInt(request.getParameter("writeId"));
		
		String subject = request.getParameter("subject");
		String content = request.getParameter("smarteditor");
		
		
		BoardWriteVO bwVO = new BoardWriteVO();
		bwVO.setWriteId(writeId);
		
		BoardWriteVO vo = boardWriteService.getBoardWrite(bwVO);
		int boardId = vo.getBoardId();
		String userId = vo.getUserId();
		
		BoardWriteVO boardWriteVO = new BoardWriteVO(writeId, boardId, userId, subject, content);
		int updateCnt = boardWriteService.updateBoardWrite(boardWriteVO);
		
		int insertFileCnt = 0;
		BoardFileVO boardFileVO = new BoardFileVO();
		
		//파일 업로드 처리를 할거에요. **********
	    for (Part part : request.getParts()) {
	         if (part.getSize() > 0) {
	            String contentDisposition = part.getHeader("content-disposition");
	            String file_name = PartUtil.getFileName(contentDisposition);
	            if(file_name !=""){
	            	
	               String ext = PartUtil.getExt(file_name);
	               
	               String uploadPath = PartUtil.getUploadPath();
	               File uploadFolder = new File(uploadPath);
	               
	               String file_path = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
	               if (uploadFolder.exists()) {
	                  part.write(file_path);
	                  part.delete();
	               }
	               
	               boardFileVO.setWriteId(writeId);
	               boardFileVO.setFile_path(file_path);
	               boardFileVO.setOriginal_file_name(file_name);
	               
	               insertFileCnt = boardFileService.insertFile(boardFileVO);
	               request.setAttribute("boardFileVO", boardFileVO);
	            }
	         }
	      }
	    
		if(updateCnt != 0 || insertFileCnt != 0) {
			response.sendRedirect(request.getContextPath()+"/viewBoard?boardId="+boardId+"&writeId="+writeId);
		}else {
			request.getRequestDispatcher("/board/writeBoard.jsp").forward(request, response);
		}

	}

}
