package ca.mcgill.ecse321.cooperator.dto;

public class StatsDto {

	private String term;
	private int activeCoops;
	private String completionIndex;
	private String averageFormSubmission;
	private int problematicStudents;

	public StatsDto() {

	}

	public StatsDto(String term, int activeCoops, String completionIndex, String averageFormSubmission,
			int problematicStudents) {
		this.term = term;
		this.activeCoops = activeCoops;
		this.completionIndex = completionIndex;
		this.averageFormSubmission = averageFormSubmission;
		this.problematicStudents = problematicStudents;
	}

	public String getTerm() {
		return term;
	}

	public int getActiveCoops() {
		return activeCoops;
	}

	public String getCompletionIndex() {
		return completionIndex;
	}

	public String getAverageFormSubmission() {
		return averageFormSubmission;
	}

	public int getProblematicStudents() {
		return problematicStudents;
	}

}
