package ca.mcgill.ecse321.cooperator.dto;

import java.sql.Date;

public class FormDto {

	private int formId;
	private Date submissionDate;
	private int coopId;
	private int employerEvaluation;
	private String softwareTechnologies;
	private String usefulCourses;
	private String studentWorkExperience;
	private int studentPerformance;
	private String tasks;
	private int hoursPerWeek;
	private int wage;
	private String training;
	private String formType = "";

	public FormDto() {

	}

	// Acceptance Form
	public FormDto(int formId, Date submissionDate, int coopId) {
		this.formId = formId;
		this.submissionDate = submissionDate;
		this.coopId = coopId;
		this.formType = "AcceptanceForm";
	}

	// Coop Evaluation
	public FormDto(int formId, Date submissionDate, int coopId, int employerEvaluation, String softwareTechnologies,
			String usefulCourses, String studentWorkExperience) {
		this.formId = formId;
		this.submissionDate = submissionDate;
		this.coopId = coopId;
		this.employerEvaluation = employerEvaluation;
		this.softwareTechnologies = softwareTechnologies;
		this.usefulCourses = usefulCourses;
		this.studentWorkExperience = studentWorkExperience;
		this.formType = "Coop Evaluation";
	}

	// Student Evaluation
	public FormDto(int formId, Date submissionDate, int coopId, String studentWorkExperience, int studentPerformance) {
		this.formId = formId;
		this.submissionDate = submissionDate;
		this.coopId = coopId;
		this.studentPerformance = studentPerformance;
		this.studentWorkExperience = studentWorkExperience;
		this.formType = "Student Evaluation";
	}

	// Tasks Workload Report
	public FormDto(int formId, Date submissionDate, int coopId, String tasks, int hoursPerWeek, int wage,
			String training) {
		this.formId = formId;
		this.submissionDate = submissionDate;
		this.coopId = coopId;
		this.tasks = tasks;
		this.hoursPerWeek = hoursPerWeek;
		this.wage = wage;
		this.training = training;
		this.formType = "Tasks Workload Report";
	}

	public FormDto(int formId, Date submissionDate, int coopId, String workExperience, int employerEvaluation,
			String softwareTechnologies, String usefulCourses, int studentPerformance, String tasks, int hoursPerWeek,
			int wage, String training) {
		this.formId = formId;
		this.submissionDate = submissionDate;
		this.coopId = coopId;
		this.employerEvaluation = employerEvaluation;
		this.softwareTechnologies = softwareTechnologies;
		this.usefulCourses = usefulCourses;
		this.studentPerformance = studentPerformance;
		this.tasks = tasks;
		this.hoursPerWeek = hoursPerWeek;
		this.wage = wage;
		this.training = training;
		this.formType = "Mixed Form";
	}

	public int getFormId() {
		return this.formId;
	}

	public Date getSubmissionDate() {
		return this.submissionDate;
	}

	public int getCoop() {
		return this.coopId;
	}

	public String getStudentWorkExperience() {
		return this.studentWorkExperience;
	}

	public int getEmployerEvaluation() {
		return this.employerEvaluation;
	}

	public String getSoftwareTechnologies() {
		return this.softwareTechnologies;
	}

	public String getUsefulCourses() {
		return this.usefulCourses;
	}

	public int getStudentPerformance() {
		return this.studentPerformance;
	}

	public String getTasks() {
		return this.tasks;
	}

	public int getHoursPerWeek() {
		return this.hoursPerWeek;
	}

	public int getWage() {
		return this.wage;
	}

	public String getTraining() {
		return this.training;
	}
	
	public String getFormType() {
		return this.formType;
	}

}
