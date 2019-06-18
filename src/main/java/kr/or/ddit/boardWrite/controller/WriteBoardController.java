package kr.or.ddit.boardWrite.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Enumeration;
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

import org.apache.ibatis.javassist.Loader.Simple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.ddit.boardFile.model.BoardFileVO;
import kr.or.ddit.boardFile.service.BoardFileService;
import kr.or.ddit.boardFile.service.IBoardFileService;
import kr.or.ddit.boardWrite.model.BoardWriteVO;
import kr.or.ddit.boardWrite.service.BoardWriteService;
import kr.or.ddit.boardWrite.service.IBoardWriteService;
import kr.or.ddit.util.PartUtil;

/**
 * Servlet implementation class WriteBoardController
 */
@WebServlet("/writeBoard")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 15)
public class WriteBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(WriteBoardController.class);
    
    private IBoardWriteService boardWriteService;
    private IBoardFileService boardFileService;
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		boardWriteService = new BoardWriteService();
		boardFileService = new BoardFileService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/board/writeBoard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int boardId = Integer.parseInt(request.getParameter("boardId").trim());
		String userId = (String) request.getSession().getAttribute("userId");
		String subject = request.getParameter("subject");
		String content = request.getParameter("smarteditor");
		int parent_seq = 0;
		int group_seq = 0;
		
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
		
		
//		Part attFile = null;
//		

//		
//		//profile이 0보다 크면 사용자가 파일을 업로드 한거겠죵
//		if(attFile.getSize() > 0) {
//			//실제 파일명
//			String contentDisposition = attFile.getHeader("content-disposition");
//			String filename = PartUtil.getFileName(contentDisposition);
//			String ext = PartUtil.getExt(filename);
//			
//			
//			//만약 폴더가 정상적으로 생성이 되었으면
//			String uploadPath = PartUtil.getUploadPath();
//			File uploadFolder = new File(uploadPath);
//			if(uploadFolder.exists()) {
//				//파일 디스크에 쓰기
//				String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
//				
//				boardFileVO.setWriteId(insertCnt);
//				boardFileVO.setFile_path(filePath);
//				boardFileVO.setOriginal_file_name(filename);
//				
//				attFile.write(filePath);
//				attFile.delete();
//			}
//		}
//		
//		int insertFileCnt = boardFileService.insertFile(boardFileVO);
//				
//		if(insertCnt != 0 && insertFileCnt != 0) {
//			response.sendRedirect(request.getContextPath()+"/viewBoard?boardId="+boardId+"&writeId="+insertCnt);
//		}else {
//			request.getRequestDispatcher("/board/writeBoard.jsp").forward(request, response);
//		}
		
	}

}
