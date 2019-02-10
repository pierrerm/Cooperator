package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Coop{
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

private AcceptanceForm acceptanceForm;

@OneToOne(mappedBy="coop" )
public AcceptanceForm getAcceptanceForm() {
   return this.acceptanceForm;
}

public void setAcceptanceForm(AcceptanceForm acceptanceForm) {
   this.acceptanceForm = acceptanceForm;
}

private EmployerContract employerContract;

@OneToOne(mappedBy="coop" )
public EmployerContract getEmployerContract() {
   return this.employerContract;
}

public void setEmployerContract(EmployerContract employerContract) {
   this.employerContract = employerContract;
}

private TechnicalReport technicalReport;

@OneToOne(mappedBy="coop" )
public TechnicalReport getTechnicalReport() {
   return this.technicalReport;
}

public void setTechnicalReport(TechnicalReport technicalReport) {
   this.technicalReport = technicalReport;
}

private CoopEvaluation coopEvaluation;

@OneToOne(mappedBy="coop" )
public CoopEvaluation getCoopEvaluation() {
   return this.coopEvaluation;
}

public void setCoopEvaluation(CoopEvaluation coopEvaluation) {
   this.coopEvaluation = coopEvaluation;
}

private StudentEvaluation studentEvaluation;

@OneToOne(mappedBy="coop" )
public StudentEvaluation getStudentEvaluation() {
   return this.studentEvaluation;
}

public void setStudentEvaluation(StudentEvaluation studentEvaluation) {
   this.studentEvaluation = studentEvaluation;
}

private TasksWorkloadReport tasksWorkloadReport;

@OneToOne(mappedBy="coop" )
public TasksWorkloadReport getTasksWorkloadReport() {
   return this.tasksWorkloadReport;
}

public void setTasksWorkloadReport(TasksWorkloadReport tasksWorkloadReport) {
   this.tasksWorkloadReport = tasksWorkloadReport;
}

private CoopPlacementProof coopPlacementProof;

@OneToOne
public CoopPlacementProof getCoopPlacementProof() {
   return this.coopPlacementProof;
}

public void setCoopPlacementProof(CoopPlacementProof coopPlacementProof) {
   this.coopPlacementProof = coopPlacementProof;
}

private TaxFormInstruction taxFormInstruction;

@OneToOne
public TaxFormInstruction getTaxFormInstruction() {
   return this.taxFormInstruction;
}

public void setTaxFormInstruction(TaxFormInstruction taxFormInstruction) {
   this.taxFormInstruction = taxFormInstruction;
}

private TaxForm taxForm;

@OneToOne
public TaxForm getTaxForm() {
   return this.taxForm;
}

public void setTaxForm(TaxForm taxForm) {
   this.taxForm = taxForm;
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
