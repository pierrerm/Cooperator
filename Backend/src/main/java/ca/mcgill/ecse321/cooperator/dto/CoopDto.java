package ca.mcgill.ecse321.cooperator.dto;

import java.sql.Date;

import ca.mcgill.ecse321.cooperator.model.*;

import java.util.Set;

public class CoopDto {

	private Set<PDF> downloadableDoc;
	private Set<Form> form;
	private Student student;
	private Employer employer;
	private Date startDate;
	private Date endDate;
	private Semester semester;
	private Set<Reminder> reminder;
	private int coopId;
	private Boolean employerConfirmation;
	private Boolean needWorkPermit;
	private String jobDescription;
	private String location;
	private int jobId;
	
	public CoopDto() {
		
	}
	
	public CoopDto (int coopId, boolean employerConfirmation, Date endDate, String jobDescription, int jobId, String location, boolean needWorkPermit,
			Semester semester, Date startDate, Student student, Employer employer) {
		this.coopId = coopId;
		this.employerConfirmation = employerConfirmation;
		this.endDate = endDate;
		this.jobDescription = jobDescription;
		this.jobId = jobId;
		this.location = location;
		this.needWorkPermit = needWorkPermit;
		this.semester = semester;
		this.startDate = startDate;
		this.student = student;
		this.employer = employer;
	}

	public Set<PDF> getDownloadableDoc() {
		return this.downloadableDoc;
	}

	public Set<Form> getForm() {
		return this.form;
	}

	public Student getStudent() {
		return this.student;
	}

	public Employer getEmployer() {
		return this.employer;
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

	public Set<Reminder> getReminder() {
		return this.reminder;
	}

}
