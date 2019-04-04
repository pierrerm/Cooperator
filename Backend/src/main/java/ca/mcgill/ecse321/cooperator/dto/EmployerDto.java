package ca.mcgill.ecse321.cooperator.dto;

import java.util.ArrayList;

public class EmployerDto {

	private long phone;
	private String firstName;
	private String lastName;
	private String email;

	private String position;
	private String company;
	private ArrayList<Integer> coop;
	private String location;

	public EmployerDto() {

	}

	public EmployerDto(long phone, String firstName, String lastName, String email, String position, String company,
			String location, ArrayList<Integer> coop) {
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.position = position;
		this.company = company;
		this.location = location;
		this.coop = coop;
	}

	public long getPhone() {
		return phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPosition() {
		return this.position;
	}

	public String getCompany() {
		return this.company;
	}

	public ArrayList<Integer> getCoop() {
		return this.coop;
	}

	public String getLocation() {
		return this.location;
	}
}
