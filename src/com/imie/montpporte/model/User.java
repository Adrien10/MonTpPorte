package com.imie.montpporte.model;

import java.io.Serializable;


public class User implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1829571294030623559L;
	/**
	 * USER
	 */

	protected int id;
	protected String login;
	protected String password;
	
	public User() {
	}
	public User(int id, String login, String pswd) {
		this.id 		= id;
		this.login 		= login;
		this.password 	= pswd;
	}
	public User(String login, String pswd) {
		this.login 		= login;
		this.password 	= pswd;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.login;
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
