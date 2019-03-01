package com.modulopgave1.model;

public class Migration {

	private int id;
	private Year year;
	private Municipality fromMunicipality;
	private Municipality toMunicipality;
	private Gender gender;
	private AgeSpan ageSpan;
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

	public Municipality getFromMunicipality() {
		return this.fromMunicipality;
	}

	public void setFromMunicipality(Municipality fromMunicipality) {
		this.fromMunicipality = fromMunicipality;
	}

	public Municipality getToMunicipality() {
		return this.toMunicipality;
	}

	public void setToMunicipality(Municipality toMunicipality) {
		this.toMunicipality = toMunicipality;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public AgeSpan getAgeSpan() {
		return this.ageSpan;
	}

	public void setAgeSpan(AgeSpan ageSpan) {
		this.ageSpan = ageSpan;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Migration() {
		setFromMunicipality(new Municipality(0, ""));
		setToMunicipality(new Municipality(0, ""));
		setGender(new Gender(0, ""));
		setYear(new Year(0, 0));
		setAgeSpan(new AgeSpan(0, 0, 0));
	}

	public Migration(int id, Year year, Municipality fromMunicipality, Municipality toMunicipality, Gender gender, AgeSpan ageSpan, int count) {
		setId(id);
		setYear(year);
		setFromMunicipality(fromMunicipality);
		setToMunicipality(toMunicipality);
		setGender(gender);
		setAgeSpan(ageSpan);
		setCount(count);
	}

	public String toString() {
		String result = "";

		result += "Id: " + getId();
		result += "\tYear: " + getYear().getYear();
		result += "\tFrom: " + getFromMunicipality().getTitle();
		result += "\tTo: " + getToMunicipality().getTitle();
		result += "\tGender: " + getGender().getTitle();
		result += "\tAge Span: [" + getAgeSpan().getAgeFrom() + " ; " + getAgeSpan().getAgeTo() + "]";
		result += "\tCount: " + getCount();

		return result;
	}
}