package kr.or.ddit.boardComment.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardCommentVO {

	private int commId;
	private int writeId;
	private String userId;
	private String content;
	private Date writeDate;
	private String use_yn;
	
	public BoardCommentVO() {

	}

	public BoardCommentVO(int commId, int writeId, String userId, String content, Date writeDate, String use_yn) {
		super();
		this.commId = commId;
		this.writeId = writeId;
		this.userId = userId;
		this.content = content;
		this.writeDate = writeDate;
		this.use_yn = use_yn;
	}
	
	
	public BoardCommentVO(int writeId, String userId, String content) {
		super();
		this.writeId = writeId;
		this.userId = userId;
		this.content = content;
	}
	

	public BoardCommentVO(int commId, int writeId, String content) {
		super();
		this.commId = commId;
		this.writeId = writeId;
		this.content = content;
	}
	

	public BoardCommentVO(int commId, int writeId) {
		super();
		this.commId = commId;
		this.writeId = writeId;
	}

	public String getWriteDateStr() {
		SimpleDateFormat sdf = new SimpleDateFormat();
		if(writeDate == null) {
			return "";
		}
		return sdf.format(writeDate);
	}

	public int getCommId() {
		return commId;
	}

	public void setCommId(int commId) {
		this.commId = commId;
	}

	public int getWriteId() {
		return writeId;
	}

	public void setWriteId(int writeId) {
		this.writeId = writeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public String getUse_yn() {
		return use_yn;
	}

	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}

	@Override
	public String toString() {
		return "BoardCommentVO [commId=" + commId + ", writeId=" + writeId + ", userId=" + userId + ", content="
				+ content + ", writeDate=" + writeDate + ", use_yn=" + use_yn + "]";
	}
	
	
}
