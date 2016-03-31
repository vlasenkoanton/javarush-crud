package com.losemind.www.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.bridge.builtin.IntegerBridge;

@Entity
@Indexed
@Table(name = "user")
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Field
	@Size(min = 2, max = 25, message = "Name must be at least 2 characters long")
	@Pattern(regexp = "[a-zA-Zа-яА-Я_]+", message = "Name must consist only of letter, numbers and underline symbol")
	private String name;
	
	@Field
	@FieldBridge(impl = IntegerBridge.class)
	@Max(value = 60, message = "Age must be equal or lower than 60")
	@Min(value = 5, message = "Age must be equal or bigger than 5")
	@Digits(fraction = 0, integer = 2, message = "Age must consists of 1 or 2 numbers")
	private int age;
	private boolean isAdmin;
	private Date createdDate;

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", isAdmin=" + isAdmin + ", createdDate="
				+ createdDate + "]";
	}

}
