package com.modulopgave1.model;

public class Gender {

	private int id;
	private String title;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public Gender() {}

	public Gender(int id, String title) {
		setId(id);
		setTitle(title);
	}
}