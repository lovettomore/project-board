package kr.or.ddit.boardFile.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.boardFile.model.BoardFileVO;
import kr.or.ddit.boardFile.service.BoardFileService;
import kr.or.ddit.boardFile.service.IBoardFileService;

/**
 * Servlet implementation class DownloadFileController
 */
@WebServlet("/downloadFile")
public class DownloadFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IBoardFileService boardFileService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		boardFileService = new BoardFileService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int fileId = Integer.parseInt(request.getParameter("fileId"));
		
		BoardFileVO boardFileVO = boardFileService.getBoardFile(fileId);

		// 파일 업로드된 경로
		String savePath = boardFileVO.getFile_path();

		// 실제 내보낼 파일명
		String orgfilename = boardFileVO.getOriginal_file_name();

		InputStream in = null;
		OutputStream os = null;
		File file = null;
		boolean skip = false;
		String client = "";

		try{
			// 파일을 읽어 스트림에 담기
			try{
				file = new File(savePath);
				in = new FileInputStream(file);
			}catch(FileNotFoundException fe){
				skip = true;
			}

			client = request.getHeader("User-Agent");

			// 파일 다운로드 헤더 지정
			response.reset() ;
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Description", "JSP Generated Data");


			if(!skip){
				// IE
				if(client.indexOf("MSIE") != -1){
					response.setHeader ("Content-Disposition", "attachment; filename="+new String(orgfilename.getBytes("KSC5601"),"ISO8859_1"));

				}else{
					// 한글 파일명 처리
					orgfilename = new String(orgfilename.getBytes("utf-8"),"iso-8859-1");

					response.setHeader("Content-Disposition", "attachment; filename=\"" + orgfilename + "\"");
					response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
				} 

				response.setHeader ("Content-Length", ""+file.length() );

				os = response.getOutputStream();
				byte b[] = new byte[(int)file.length()];
				int leng = 0;

				while( (leng = in.read(b)) > 0 ){
					os.write(b,0,leng);
				}
			}

			in.close();
			os.close();

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	int fileId = Integer.parseInt(request.getParameter("fileId"));
//	
//	BoardFileVO vo = new BoardFileVO();
//	vo.setFileId(fileId);
//	
//	BoardFileVO bfVO = boardFileService.getBoardFile(fileId);
//	
//	String fileName = bfVO.getOriginal_file_name(); //사용자가 요청한 파일 이름
//	String directory = this.getServletContext().getRealPath("/Users/chewoop/Documents/upload/");
//	File file = new File(directory + "/" + fileName);
//	
//	String mimeType = getServletContext().getMimeType(file.toString());
//	if(mimeType == null) {
//		response.setContentType("application/octet-stream");
//	}
//	
//	String downloadName = null;
//	if(request.getHeader("user-agent").indexOf("MSIE") == -1) {
//		downloadName = new String(fileName.getBytes("UTF-8"), "8859_1");
//	}else {
//		downloadName = new String(fileName.getBytes("EUC-KR"), "8859_1");
//	}
//	
//	response.setHeader("Content-Disposition", "attachment;filename=\"" + downloadName + "\";");
//	
//	FileInputStream fileInputStream = new FileInputStream(file);
//	ServletOutputStream servletOutputStream = response.getOutputStream();
//	
//	byte b[] = new byte[1024];
//	int data = 0;
//	
//	while((data = (fileInputStream.read(b,0,b.length))) != -1) {
//		servletOutputStream.write(b, 0, data);
//	}
//	
//	servletOutputStream.flush();
//	servletOutputStream.close();
//	fileInputStream.close();
//	
//}

}
