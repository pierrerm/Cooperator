package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Form{
   private Boolean submitted;

public void setSubmitted(Boolean value) {
    this.submitted = value;
}
public Boolean getSubmitted() {
    return this.submitted;
}
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
}
