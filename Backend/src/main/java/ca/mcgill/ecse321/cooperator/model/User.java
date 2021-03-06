package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User {
	private long phone;

	public void setPhone(long value) {
		this.phone = value;
	}

	public long getPhone() {
		return this.phone;
	}

	private String firstName;

	public void setFirstName(String value) {
		this.firstName = value;
	}

	public String getFirstName() {
		return this.firstName;
	}

	private String lastName;

	public void setLastName(String value) {
		this.lastName = value;
	}

	public String getLastName() {
		return this.lastName;
	}

	private String email;

	public void setEmail(String value) {
		this.email = value;
	}

	public String getEmail() {
		return this.email;
	}

	private String password;

	public void setPassword(String value) {
		this.password = value;
	}

	public String getPassword() {
		return this.password;
	}

	private int userId;

	public void setUserId(int value) {
		this.userId = value;
	}

	@Id
	public int getUserId() {
		return this.userId;
	}
}
