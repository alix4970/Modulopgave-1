package com.modulopgave1.model;

public class Year {

	private int id;
	private int year;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}


	public Year() {}

	public Year(int id, int year) {
		setId(id);
		setYear(year);
	}
}