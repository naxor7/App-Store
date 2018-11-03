package io.zilker.appstore.beans;

import java.io.Serializable;

public class Applications implements Serializable {

	private static final long serialVersionUID = 1L;
	private int appID, userID, cost, reportCount;
	private float review;
	private Category category;
	private String appName, description, status, createdAt, updatedAt;
	private Comments[] comments;
	
	public Applications() {
		category = new Category();
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public float getReview() {
		return review;
	}

	public void setReview(float review) {
		this.review = review;
	}

	public Comments[] getComments() {
		return comments;
	}

	public void setComments(Comments[] comments) {
		this.comments = comments;
	}

	public void setAppID(int appID) {
		this.appID = appID;
	}

	public int getAppID() {
		return this.appID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getUserID() {
		return this.userID;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getCost() {
		return this.cost;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public int getReportCount() {
		return this.reportCount;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppName() {
		return this.appName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedAt() {
		return this.createdAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt() {
		return this.updatedAt;
	}

}
