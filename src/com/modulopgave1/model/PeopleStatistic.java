package com.modulopgave1.model;

public class PeopleStatistic {

	private int id;
	private Year year;
	private Municipality municipality;
	private MovementType movementType;
	private Gender gender;
	private int count;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Year getYear() {
		return this.year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public Municipality getMunicipality() {
		return this.municipality;
	}

	public void setMunicipality(Municipality municipality) {
		this.municipality = municipality;
	}

	public MovementType getMovementType() {
		return this.movementType;
	}

	public void setMovementType(MovementType movementType) {
		this.movementType = movementType;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public PeopleStatistic()
	{
		setMunicipality(new Municipality(0, ""));
		setMovementType(new MovementType(0, ""));
		setGender(new Gender(0, ""));
		setYear(new Year(0, 0));
	}

	public PeopleStatistic(int id, Year year, MovementType movementType, Municipality municipality, Gender gender, int count)
	{
		setId(id);
		setYear(year);
		setMunicipality(municipality);
		setMovementType(movementType);
		setGender(gender);
		setCount(count);
	}

	public String toString()
	{
		String result = "";

		result += "Id: " + getId();
		result += "\tYear: " + getYear().getYear();
		result += "\tMovement: " + getMovementType().getTitle();
		result += "\tMunicipality: " + getMunicipality().getTitle();
		result += "\tGender: " + getGender().getTitle();
		result += "\tCount: " + getCount();

		return result;
	}
}