package ca.mcgill.ecse321.cooperator.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Coop {
	private Set<PDF> pdf;

	@OneToMany(mappedBy = "coop")
	public Set<PDF> getPDF() {
		return this.pdf;
	}

	public void setPDF(Set<PDF> pdf) {
		this.pdf = pdf;
	}

	private Set<Form> form;

	@OneToMany(mappedBy = "coop")
	public Set<Form> getForm() {
		return this.form;
	}

	public void setForm(Set<Form> forms) {
		this.form = forms;
	}

	private Student student;

	@ManyToOne(optional = false)
	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	private Employer employer;

	@ManyToOne(optional = false)
	public Employer getEmployer() {
		return this.employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	private Date startDate;

	public void setStartDate(Date value) {
		this.startDate = value;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	private Date endDate;

	public void setEndDate(Date value) {
		this.endDate = value;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	private Semester semester;

	public void setSemester(Semester value) {
		this.semester = value;
	}

	public Semester getSemester() {
		return this.semester;
	}

	private int jobId;

	public void setJobId(int value) {
		this.jobId = value;
	}

	public int getJobId() {
		return this.jobId;
	}

	private String location;

	public void setLocation(String value) {
		this.location = value;
	}

	public String getLocation() {
		return this.location;
	}

	private String jobDescription;

	public void setJobDescription(String value) {
		this.jobDescription = value;
	}

	public String getJobDescription() {
		return this.jobDescription;
	}

	private Boolean needWorkPermit;

	public void setNeedWorkPermit(Boolean value) {
		this.needWorkPermit = value;
	}

	public Boolean getNeedWorkPermit() {
		return this.needWorkPermit;
	}

	private Boolean employerConfirmation;

	public void setEmployerConfirmation(Boolean value) {
		this.employerConfirmation = value;
	}

	public Boolean getEmployerConfirmation() {
		return this.employerConfirmation;
	}

	private int coopId;

	public void setCoopId(int value) {
		this.coopId = value;
	}

	@Id
	public int getCoopId() {
		return this.coopId;
	}

	private Set<Reminder> reminder;

	@OneToMany(mappedBy = "coop")
	public Set<Reminder> getReminder() {
		return this.reminder;
	}

	public void setReminder(Set<Reminder> reminders) {
		this.reminder = reminders;
	}
}
