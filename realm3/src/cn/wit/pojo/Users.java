package cn.wit.pojo;

import java.util.List;


public class Users {
	private int id;
	private String username;
	private String password;
	private String password_salt;
	private List<String>element;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword_salt() {
		return password_salt;
	}
	public void setPassword_salt(String password_salt) {
		this.password_salt = password_salt;
	}
	public List<String> getElement() {
		return element;
	}
	public void setElement(List<String> element) {
		this.element = element;
	}
	public Users(int id, String username, String password,
			String password_salt, List<String> element) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.password_salt = password_salt;
		this.element = element;
	}
	public Users() {
		super();
	}
	
}
