package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
public class PDF {
	private int docId;

	public void setDocId(int value) {
		this.docId = value;
	}

	@Id
	public int getDocId() {
		return this.docId;
	}

	private String filePath;

	public void setFilePath(String value) {
		this.filePath = value;
	}

	public String getFilePath() {
		return this.filePath;
	}

	private Coop coop;

	@ManyToOne(optional = false)
	public Coop getCoop() {
		return this.coop;
	}

	public void setCoop(Coop coop) {
		this.coop = coop;
	}

	private DocumentType documentType;

	public void setDocumentType(DocumentType value) {
		this.documentType = value;
	}

	public DocumentType getDocumentType() {
		return this.documentType;
	}

	private Date submissionDate;

	public void setSubmissionDate(Date value) {
		this.submissionDate = value;
	}

	public Date getSubmissionDate() {
		return this.submissionDate;
	}
}
