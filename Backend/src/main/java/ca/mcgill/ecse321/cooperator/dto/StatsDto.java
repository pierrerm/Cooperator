package ca.mcgill.ecse321.cooperator.dto;

public class StatsDto {

	private String term;
	private String activeCoops;
	private String completionIndex;
	private String averageFormSubmission;
	private String problematicStudents;

	public StatsDto() {

	}

	public StatsDto(String term, String activeCoops, String completionIndex, String averageFormSubmission,
			String problematicStudents) {
		this.term = term;
		this.activeCoops = activeCoops;
		this.completionIndex = completionIndex;
		this.averageFormSubmission = averageFormSubmission;
		this.problematicStudents = problematicStudents;
	}

	public String getTerm() {
		return term;
	}

	public String getActiveCoops() {
		return activeCoops;
	}

	public String getCompletionIndex() {
		return completionIndex;
	}

	public String getAverageFormSubmission() {
		return averageFormSubmission;
	}

	public String getProblematicStudents() {
		return problematicStudents;
	}

}
