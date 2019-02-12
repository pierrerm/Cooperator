package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import javax.persistence.OneToOne;

@Entity
public class Form{
   private String filePath;

public void setFilePath(String value) {
    this.filePath = value;
}
public String getFilePath() {
    return this.filePath;
}
private String formId;

public void setFormId(String value) {
    this.formId = value;
}
@Id
public String getFormId() {
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
   
   @OneToOne(mappedBy="form" , optional=false)
   public Coop getCoop() {
      return this.coop;
   }
   
   public void setCoop(Coop coop) {
      this.coop = coop;
   }
   
   }
