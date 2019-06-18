package kr.or.ddit.boardWrite.controller;

import java.io.File;
import java.io.IOException;
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
 * Servlet implementation class ReplyBoardController
 */
@WebServlet("/reply")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 15)
public class ReplyBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ReplyBoardController.class);
	
	private IBoardWriteService boardWriteService;
	private IBoardFileService boardFileService;
	
       
	@Override
	public void init(ServletConfig config) throws ServletException {
		boardWriteService = new BoardWriteService();
		boardFileService = new BoardFileService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int writeId = Integer.parseInt(request.getParameter("writeId"));
		
		BoardWriteVO bwVO = new BoardWriteVO();
		bwVO.setWriteId(writeId);
		
		BoardWriteVO boardWriteVO = boardWriteService.getBoardWrite(bwVO);
		
		request.setAttribute("boardWriteVO", boardWriteVO);
		request.getRequestDispatcher("/board/replyBoard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int writeId = Integer.parseInt(request.getParameter("writeId"));
		
		BoardWriteVO bwVO = new BoardWriteVO();
		bwVO.setWriteId(writeId);
		
		BoardWriteVO boardWriteItems = boardWriteService.getBoardWrite(bwVO);
		
		int boardId = boardWriteItems.getBoardId();
		int parent_seq = boardWriteItems.getWriteId();
		int group_seq = boardWriteItems.getGroup_seq();
		
		String userId = (String) request.getSession().getAttribute("userId");
		String subject = request.getParameter("subject");
		String content = request.getParameter("smarteditor");
		
		BoardWriteVO boardWriteVO = new BoardWriteVO(boardId, userId, subject, content, parent_seq, group_seq);
		int insertCnt = boardWriteService.insertBoardWrite(boardWriteVO);
		
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
	               
	               boardFileVO.setWriteId(insertCnt);
	               boardFileVO.setFile_path(file_path);
	               boardFileVO.setOriginal_file_name(file_name);
	               
	               insertFileCnt = boardFileService.insertFile(boardFileVO);
	               request.setAttribute("boardFileVO", boardFileVO);
	            }
	         }
	      }
	    
		if(insertCnt != 0 || insertFileCnt != 0) {
			response.sendRedirect(request.getContextPath()+"/viewBoard?boardId="+boardId+"&writeId="+insertCnt);
		}else {
			request.getRequestDispatcher("/board/writeBoard.jsp").forward(request, response);
		}
	}

}
