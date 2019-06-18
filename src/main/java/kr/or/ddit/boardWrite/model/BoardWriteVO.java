package kr.or.ddit.boardWrite.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardWriteVO {
	
	private int writeId;
	private int boardId;
	private String userId;
	private String subject;
	private String content;
	private Date writeDate;
	private String use_yn;
	private int parent_seq;
	private int group_seq;
	private int lv;
	private int rn;
	
	public BoardWriteVO() {
		
	}

	public BoardWriteVO(int writeId, int boardId, String userId, String subject, String content, Date writeDate,
			String use_yn, int parent_seq, int group_seq) {
		super();
		this.writeId = writeId;
		this.boardId = boardId;
		this.userId = userId;
		this.subject = subject;
		this.content = content;
		this.writeDate = writeDate;
		this.use_yn = use_yn;
		this.parent_seq = parent_seq;
		this.group_seq = group_seq;
	}
	
	
	public BoardWriteVO(int writeId, int boardId, String userId, String subject, String content) {
		super();
		this.writeId = writeId;
		this.boardId = boardId;
		this.userId = userId;
		this.subject = subject;
		this.content = content;
	}
	

	public BoardWriteVO(int writeId, int boardId, String userId, String use_yn) {
		super();
		this.writeId = writeId;
		this.boardId = boardId;
		this.userId = userId;
		this.use_yn = use_yn;
	}
	

	public BoardWriteVO(int boardId, String userId, String subject, String content, int parent_seq, int group_seq) {
		super();
		this.boardId = boardId;
		this.userId = userId;
		this.subject = subject;
		this.content = content;
		this.parent_seq = parent_seq;
		this.group_seq = group_seq;
	}
	

	public String getWriteDateStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(writeDate == null) {
			return "";
		}
		return sdf.format(writeDate);
	}
	
	public int getWriteId() {
		return writeId;
	}

	public void setWriteId(int writeId) {
		this.writeId = writeId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	public int getParent_seq() {
		return parent_seq;
	}

	public void setParent_seq(int parent_seq) {
		this.parent_seq = parent_seq;
	}

	public int getGroup_seq() {
		return group_seq;
	}

	public void setGroup_seq(int group_seq) {
		this.group_seq = group_seq;
	}


	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}
	
	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

	@Override
	public String toString() {
		return "BoardWriteVO [writeId=" + writeId + ", boardId=" + boardId + ", userId=" + userId + ", subject="
				+ subject + ", content=" + content + ", writeDate=" + writeDate + ", use_yn=" + use_yn + ", parent_seq="
				+ parent_seq + ", group_seq=" + group_seq + "]";
	}
	
	
	
}
