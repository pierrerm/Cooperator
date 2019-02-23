package ca.mcgill.ecse321.cooperator.dto;

import java.sql.Date;

import ca.mcgill.ecse321.cooperator.model.Coop;
import ca.mcgill.ecse321.cooperator.model.DocumentType;

public class PDFDto {

	private int docId;
	private String filePath;
	private Coop coop;
	private DocumentType documentType;
	private Date submissionDate;

	public PDFDto() {

	}

	public PDFDto(int docId, String filePath, Coop coop, DocumentType documentType, Date submissionDate) {
		this.docId = docId;
		this.filePath = filePath;
		this.coop = coop;
		this.documentType = documentType;
		this.submissionDate = submissionDate;
	}

	public int getDocId() {
		return this.docId;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public Coop getCoop() {
		return this.coop;
	}

	public DocumentType getDocumentType() {
		return this.documentType;
	}

	public Date getSubmissionDate() {
		return this.submissionDate;
	}
}
