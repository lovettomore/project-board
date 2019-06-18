package kr.or.ddit.user.model;

public class UserVO {
	
	private String userId;
	private String name;
	private String alias;
	private String pass;
	private String path;
	private String filename;
	

	public UserVO() {
		
	}

	public UserVO(String userId, String name, String alias, String pass, String path, String filename) {
		super();
		this.userId = userId;
		this.name = name;
		this.alias = alias;
		this.pass = pass;
		this.path = path;
		this.filename = filename;
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", name=" + name + ", alias=" + alias + ", pass=" + pass + ", path=" + path
				+ ", filename=" + filename + "]";
	}

}
