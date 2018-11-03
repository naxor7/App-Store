package io.zilker.appstore.beans;

import java.io.Serializable;

public class GenericUser implements Serializable {

	private static final long serialVersionUID = 1L;
	private int userID;
	private String fullName, userName, password, userPrivilege, email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserPrivilege() {
		return userPrivilege;
	}
	public void setUserPrivilege(String userPrivilege) {
		this.userPrivilege = userPrivilege;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
