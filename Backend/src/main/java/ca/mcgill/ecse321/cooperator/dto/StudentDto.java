package ca.mcgill.ecse321.cooperator.dto;

import java.util.Set;

import ca.mcgill.ecse321.cooperator.model.Administrator;
import ca.mcgill.ecse321.cooperator.model.Coop;
import ca.mcgill.ecse321.cooperator.model.Faculty;

public class StudentDto{

	private long phone;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int userId;
	
	private long id;
	private String academicYear;
	private String major;
	private String minor;
	private Administrator administrator;
	private Faculty faculty;
	private Set<Coop> coop;
	
	public StudentDto() {
		
	}
	
	public StudentDto(long phone, String firstName, String lastName, String email, String password, int userId, long id, String academicYear, String major, String minor, Administrator administrator, Faculty faculty, Set<Coop> coop) {
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.userId = userId;
		this.id = id;
		this.academicYear = academicYear;
		this.major = major;
		this.minor = minor;
		this.administrator = administrator;
		this.faculty = faculty;
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
	
	public String getPassword() {
		return password;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public long getId() {
		return id;
	}
	
	public String getAcademicYear() {
		return academicYear;
	}
	
	public String getMajor() {
		return major;
	}
	
	public String getMinor() {
		return minor;
	}
	
	public Administrator getAdministrator() {
		return administrator;
	}
	
	public Faculty getFaculty() {
		return faculty;
	}
	
	public Set<Coop> getCoop(){
		return coop;
	}
}
