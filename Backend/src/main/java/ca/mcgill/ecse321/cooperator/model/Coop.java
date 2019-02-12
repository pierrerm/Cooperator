package ca.mcgill.ecse321.cooperator.model;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Coop{
private DownloadableDoc downloadableDoc;
   
   @OneToOne
   public DownloadableDoc getDownloadableDoc() {
      return this.downloadableDoc;
   }
   
   public void setDownloadableDoc(DownloadableDoc downloadableDoc) {
      this.downloadableDoc = downloadableDoc;
   }
   
   private Form form;
   
   @OneToOne
   public Form getForm() {
      return this.form;
   }
   
   public void setForm(Form form) {
      this.form = form;
   }
   
   private Student student;
   
   @ManyToOne(optional=false)
   public Student getStudent() {
      return this.student;
   }
   
   public void setStudent(Student student) {
      this.student = student;
   }
   
   private Employer employer;
   
   @ManyToOne(optional=false)
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
   private int year;

public void setYear(int value) {
    this.year = value;
}
public int getYear() {
    return this.year;
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
private int jobId;

public void setJobId(int value) {
    this.jobId = value;
}
@Id
public int getJobId() {
    return this.jobId;
}

private Set<Reminder> reminder;

@OneToMany(mappedBy="coop" )
public Set<Reminder> getReminder() {
   return this.reminder;
}

public void setReminder(Set<Reminder> reminders) {
   this.reminder = reminders;
}

private Semester semester;

public void setSemester(Semester value) {
    this.semester = value;
}
public Semester getSemester() {
    return this.semester;
}
}
