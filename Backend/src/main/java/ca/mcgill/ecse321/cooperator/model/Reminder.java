package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Reminder{
private Date date;

public void setDate(Date value) {
this.date = value;
}
public Date getDate() {
return this.date;
}
private String subject;

public void setSubject(String value) {
    this.subject = value;
}
public String getSubject() {
    return this.subject;
}
private int urgency;

public void setUrgency(int value) {
    this.urgency = value;
}
public int getUrgency() {
    return this.urgency;
}
private String message;

public void setMessage(String value) {
    this.message = value;
}
public String getMessage() {
    return this.message;
}

private Student student;

public void setStudent(Student student) {
	this.student = student;
}
public Student getStudent() {
	return this.student;
}

private Administrator administrator;

public void setAdministrator(Administrator administrator) {
	this.administrator = administrator;
}
public Administrator getAdministrator() {
	return this.administrator;
}

}
