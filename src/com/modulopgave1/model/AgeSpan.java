package com.modulopgave1.model;

public class AgeSpan {

	private int id;
	private int ageFrom;
	private int ageTo;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAgeFrom() {
		return this.ageFrom;
	}

	public void setAgeFrom(int ageFrom) {
		this.ageFrom = ageFrom;
	}

	public int getAgeTo() {
		return this.ageTo;
	}

	public void setAgeTo(int ageTo) {
		this.ageTo = ageTo;
	}


	public AgeSpan() {}

	public AgeSpan(int id, int ageFrom, int ageTo) {

		// sort age values ASC
		if(ageFrom > ageTo){
			int placeHolder = ageFrom;
			ageFrom = ageTo;
			ageTo = placeHolder;
		}

		setId(id);
		setAgeFrom(ageFrom);
		setAgeTo(ageTo);
	}
}