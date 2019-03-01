package com.modulopgave1.model;

public class MovementType {

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

	public MovementType() {}

	public MovementType(int id, String title) {
		setId(id);
		setTitle(title);
	}

}