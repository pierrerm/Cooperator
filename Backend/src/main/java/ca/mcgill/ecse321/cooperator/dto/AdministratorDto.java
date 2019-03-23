package ca.mcgill.ecse321.cooperator.dto;

import java.util.ArrayList;
import java.util.Set;

import ca.mcgill.ecse321.cooperator.model.Faculty;
import ca.mcgill.ecse321.cooperator.model.Student;

public class AdministratorDto {

	private long phone;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int userId;

	private Faculty faculty;
	private long id;
	private ArrayList<StudentDto> student;

	public AdministratorDto() {

	}

	public AdministratorDto(long phone, String firstName, String lastName, String email, String password, int userId,
			Faculty faculty, long id, ArrayList<StudentDto> student) {
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.userId = userId;
		this.faculty = faculty;
		this.id = id;
		this.student = student;
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

	public Faculty getFaculty() {
		return this.faculty;
	}

	public long getId() {
		return this.id;
	}

	public ArrayList<StudentDto> getStudent() {
		return this.student;
	}

}
