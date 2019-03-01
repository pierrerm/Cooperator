package ca.mcgill.ecse321.cooperator.dto;

import java.sql.Date;

import ca.mcgill.ecse321.cooperator.model.*;

import java.util.ArrayList;

public class CoopDto {

	private ArrayList<Integer> PDFIds;
	private ArrayList<Integer> formIds;
	private StudentDto studentId;
	private EmployerDto employerId;
	private Date startDate;
	private Date endDate;
	private Semester semester;
	private ArrayList<Integer> reminderIds;
	private int coopId;
	private Boolean employerConfirmation;
	private Boolean needWorkPermit;
	private String jobDescription;
	private String location;
	private int jobId;

	public CoopDto() {

	}

	public CoopDto(int coopId, boolean employerConfirmation, Date endDate, String jobDescription, int jobId,
			String location, boolean needWorkPermit, Semester semester, Date startDate, StudentDto studentId,
			EmployerDto employerId, ArrayList<Integer> PDFIds, ArrayList<Integer> formIds,
			ArrayList<Integer> reminderIds) {
		this.coopId = coopId;
		this.employerConfirmation = employerConfirmation;
		this.endDate = endDate;
		this.jobDescription = jobDescription;
		this.jobId = jobId;
		this.location = location;
		this.needWorkPermit = needWorkPermit;
		this.semester = semester;
		this.startDate = startDate;
		this.studentId = studentId;
		this.employerId = employerId;
		this.PDFIds = PDFIds;
		this.formIds = formIds;
		this.reminderIds = reminderIds;
	}

	public ArrayList<Integer> getPDF() {
		return this.PDFIds;
	}

	public ArrayList<Integer> getForm() {
		return this.formIds;
	}

	public StudentDto getStudent() {
		return this.studentId;
	}

	public EmployerDto getEmployer() {
		return this.employerId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public Semester getSemester() {
		return this.semester;
	}

	public int getJobId() {
		return this.jobId;
	}

	public String getLocation() {
		return this.location;
	}

	public String getJobDescription() {
		return this.jobDescription;
	}

	public Boolean getNeedWorkPermit() {
		return this.needWorkPermit;
	}

	public Boolean getEmployerConfirmation() {
		return this.employerConfirmation;
	}

	public int getCoopId() {
		return this.coopId;
	}

	public ArrayList<Integer> getReminder() {
		return this.reminderIds;
	}

}
