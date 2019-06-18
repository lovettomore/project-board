package kr.or.ddit.boardFile.model;


public class BoardFileVO {
	
	private int fileId;
	private int writeId;
	private String file_path;
	private String original_file_name;
	
	public BoardFileVO() {

	}
	
	public BoardFileVO(int fileId, int writeId, String file_path, String original_file_name) {
		super();
		this.fileId = fileId;
		this.writeId = writeId;
		this.file_path = file_path;
		this.original_file_name = original_file_name;
	}
	

	public BoardFileVO(int writeId, String file_path, String original_file_name) {
		super();
		this.writeId = writeId;
		this.file_path = file_path;
		this.original_file_name = original_file_name;
	}
	

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public int getWriteId() {
		return writeId;
	}

	public void setWriteId(int writeId) {
		this.writeId = writeId;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getOriginal_file_name() {
		return original_file_name;
	}

	public void setOriginal_file_name(String original_file_name) {
		this.original_file_name = original_file_name;
	}
	

	@Override
	public String toString() {
		return "BoardFileVO [fileId=" + fileId + ", writeId=" + writeId + ", file_path=" + file_path
				+ ", original_file_name=" + original_file_name + "]";
	}
	
}
