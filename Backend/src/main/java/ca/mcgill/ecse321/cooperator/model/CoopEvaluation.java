package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;

@Entity
public class CoopEvaluation extends Form {
	private String workExperience;

	public void setWorkExperience(String value) {
		this.workExperience = value;
	}

	public String getWorkExperience() {
		return this.workExperience;
	}

	private int employerEvaluation;

	public void setEmployerEvaluation(int value) {
		this.employerEvaluation = value;
	}

	public int getEmployerEvaluation() {
		return this.employerEvaluation;
	}

	private String softwareTechnologies;

	public void setSoftwareTechnologies(String value) {
		this.softwareTechnologies = value;
	}

	public String getSoftwareTechnologies() {
		return this.softwareTechnologies;
	}

	private String usefulCourses;

	public void setUsefulCourses(String value) {
		this.usefulCourses = value;
	}

	public String getUsefulCourses() {
		return this.usefulCourses;
	}
}
