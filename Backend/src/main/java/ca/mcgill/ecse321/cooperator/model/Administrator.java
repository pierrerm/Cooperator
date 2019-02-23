package ca.mcgill.ecse321.cooperator.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Administrator extends User {
	private Faculty faculty;

	public void setFaculty(Faculty value) {
		this.faculty = value;
	}

	public Faculty getFaculty() {
		return this.faculty;
	}

	private long id;

	public void setId(long value) {
		this.id = value;
	}

	public long getId() {
		return this.id;
	}

	private Set<Student> student;

	@OneToMany(mappedBy = "administrator")
	public Set<Student> getStudent() {
		return this.student;
	}

	public void setStudent(Set<Student> students) {
		this.student = students;
	}

}
