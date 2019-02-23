package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Student extends User {
	private long id;

	public void setId(long value) {
		this.id = value;
	}

	public long getId() {
		return this.id;
	}

	private String academicYear;

	public void setAcademicYear(String value) {
		this.academicYear = value;
	}

	public String getAcademicYear() {
		return this.academicYear;
	}

	private String major;

	public void setMajor(String value) {
		this.major = value;
	}

	public String getMajor() {
		return this.major;
	}

	private String minor;

	public void setMinor(String value) {
		this.minor = value;
	}

	public String getMinor() {
		return this.minor;
	}

	private Administrator administrator;

	@ManyToOne
	public Administrator getAdministrator() {
		return this.administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	private Faculty faculty;

	public void setFaculty(Faculty value) {
		this.faculty = value;
	}

	public Faculty getFaculty() {
		return this.faculty;
	}

	private Set<Coop> coop;

	@OneToMany(mappedBy = "student")
	public Set<Coop> getCoop() {
		return this.coop;
	}

	public void setCoop(Set<Coop> coops) {
		this.coop = coops;
	}

}
