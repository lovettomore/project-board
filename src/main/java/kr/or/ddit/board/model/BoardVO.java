package kr.or.ddit.board.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardVO implements Serializable{
	private int boardId;
	private String userId;
	private String name;
	private String use_yn;
	private Date reg_dt;
	
	public BoardVO() {
		
	}
	
	
	public BoardVO(String userId, String name, String use_yn) {
		super();
		this.userId = userId;
		this.name = name;
		this.use_yn = use_yn;
	}
	
	public BoardVO(int boardId, String name, String use_yn) {
		super();
		this.boardId = boardId;
		this.name = name;
		this.use_yn = use_yn;
	}
	

	public String getRegDtStr() {
		SimpleDateFormat sdf = new SimpleDateFormat();
		if(reg_dt == null) {
			return "";
		}
		return sdf.format(reg_dt);
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUse_yn() {
		return use_yn;
	}


	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}


	public Date getReg_dt() {
		return reg_dt;
	}


	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}


	@Override
	public String toString() {
		return "BoardVO [boardId=" + boardId + ", userId=" + userId + ", name=" + name + ", use_yn=" + use_yn
				+ ", reg_dt=" + reg_dt + "]";
	}
	
	

}
