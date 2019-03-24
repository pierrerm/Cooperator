package ca.mcgill.ecse321.cooperator.dto;

import java.util.ArrayList;
import java.util.Set;

import ca.mcgill.ecse321.cooperator.model.Faculty;
import ca.mcgill.ecse321.cooperator.model.Student;

public class StatsDto {

	private String term;
	private double activeCoops;
	private double completionIndex;
	private double averageFormSubmission;
	private double problematicStudents;


	public StatsDto() {

	}

	public StatsDto(String term, double activeCoops, double completionIndex, double averageFormSubmission, double problematicStudents) {
		this.term = term;
		this.activeCoops = activeCoops;
		this.completionIndex = completionIndex;
		this.averageFormSubmission = averageFormSubmission;
		this.problematicStudents = problematicStudents;
	}

	public String getTerm() {
		return term;
	}

	public double getActiveCoops() {
		return activeCoops;
	}

	public double getCompletionIndex() {
		return completionIndex;
	}

	public double getAverageFormSubmission() {
		return averageFormSubmission;
	}

	public double getProblematicStudents() {
		return problematicStudents;
	}

}
