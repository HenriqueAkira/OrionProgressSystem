package br.com.fiap.tdsu.bean;

public class Account {

	protected int id;
	protected String email;
	protected String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean logIn() {
		return true;
	}
	
	public void logOut() {
	}
}
