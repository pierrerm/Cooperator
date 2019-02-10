package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import java.sql.Date;
import javax.persistence.Id;

@Entity
public abstract class Form{
   private Date submissionDate;

public void setSubmissionDate(Date value) {
    this.submissionDate = value;
}
public Date getSubmissionDate() {
    return this.submissionDate;
}
private int formID;

public void setFormID(int value) {
    this.formID = value;
}
@Id
public int getFormID() {
    return this.formID;
}
}
