package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String taskName;
	//@Size(min = 10, message = "Enter at least 10 Characters...")
	private String description;
	private Date targetDate;
	
	

	@ManyToOne
	private User user;
	
	public Todo() {
		super();
	}

	public Todo(User user,String taskName, String description, Date targetDate) {
		super();
		this.user = user;
		this.taskName = taskName;
		this.description = description;
		this.targetDate = targetDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	/*
	 * @Override public String toString() { return "Todo [id=" + id + ", taskName="
	 * + taskName + ", description=" + description + ", targetDate=" + targetDate +
	 * ", user=" + user + "]"; }
	 */
	
	
}
