package com.paypal.restapp.resources;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Todos")
@XmlRootElement
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String status;
	private Date dueDate;
	private String comment;
	private String assignee;
	
	public Todo() {
		
	}
	
	public Todo(Long id, String title, String status, Date dueDate, String comment, String assignee) {
		this.id = id;
		this.title = title;
		this.status = status;
		this.dueDate = dueDate;
		this.comment = comment;
		this.assignee = assignee;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title + ", status=" + status + ", dueDate=" + dueDate + ", comment="
				+ comment + ", assignee=" + assignee + "]";
	}
	
}
