package io.zilker.appstore.beans;

import java.io.Serializable;

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	private int categoryID;
	private String categoryName;

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
