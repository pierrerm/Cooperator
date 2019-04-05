package ca.mcgill.ecse321.cooperator.dto;

public class AdministratorDto {

	private String firstName;
	private String lastName;
	private String email;
	private int userId;

	public AdministratorDto() {

	}

	public AdministratorDto(String firstName, String lastName, String email, int userId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userId = userId;
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

	public int getUserId() {
		return userId;
	}

}
