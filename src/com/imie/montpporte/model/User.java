package com.imie.montpporte.model;


public class User{

	/**
	 * USER
	 */

	protected int id;
	protected String login;
	protected String password;
	
	public User() {
	}
	
	public User(String login, String pswd) {
		this.login = login;
		this.password = pswd;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
