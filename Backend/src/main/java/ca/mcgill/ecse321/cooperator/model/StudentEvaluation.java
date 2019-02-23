package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;

@Entity
public class StudentEvaluation extends Form {
	private String studentWorkExperience;

	public void setStudentWorkExperience(String value) {
		this.studentWorkExperience = value;
	}

	public String getStudentWorkExperience() {
		return this.studentWorkExperience;
	}

	private int studentPerformance;

	public void setStudentPerformance(int value) {
		this.studentPerformance = value;
	}

	public int getStudentPerformance() {
		return this.studentPerformance;
	}
}
