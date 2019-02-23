package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import javax.persistence.ManyToOne;

@Entity
public class Form {
	private int formId;

	public void setFormId(int value) {
		this.formId = value;
	}

	@Id
	public int getFormId() {
		return this.formId;
	}

	private Date submissionDate;

	public void setSubmissionDate(Date value) {
		this.submissionDate = value;
	}

	public Date getSubmissionDate() {
		return this.submissionDate;
	}

	private Coop coop;

	@ManyToOne(optional = false)
	public Coop getCoop() {
		return this.coop;
	}

	public void setCoop(Coop coop) {
		this.coop = coop;
	}

}
