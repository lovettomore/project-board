package kr.or.ddit.paging.model;

public class PageVO {
	
	private int page;		//페이지 번호
	private int pageSize;	//페이지당 건수
	private int boardId; 	//게시판 번호
	
	
	public PageVO() {
		
	}
	
	public PageVO(int page, int pageSize) {
		this.page = page;
		this.pageSize = pageSize;
	}
	
	public PageVO(int page, int pageSize, int boardId) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.boardId = boardId;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	@Override
	public String toString() {
		return "PageVO [page=" + page + ", pageSize=" + pageSize + ", boardId=" + boardId + "]";
	}
	
}
