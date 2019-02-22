package ca.mcgill.ecse321.cooperator.dto;

import java.util.Set;

import ca.mcgill.ecse321.cooperator.model.Coop;

public class EmployerDto {
	
	private long phone;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int userId;
	
	private String position;
	private String company;
	private Set<Coop> coop;
	private String location;
	
	public EmployerDto() {
		
	}
	
	public EmployerDto(int userId, long phone, String firstName, String lastName, String email, String password, String position, String company, String location) {
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.userId = userId;
		this.position = position;
		this.company = company;
		this.location = location;
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
	
	public String getPassword() {
		return password;
	}
	
	public int getUserId() {
		return userId;
	}

	public String getPosition() {
		return this.position;
	}

	public String getCompany() {
		return this.company;
	}

	public Set<Coop> getCoop() {
		return this.coop;
	}

	public String getLocation() {
		return this.location;
	}
}
