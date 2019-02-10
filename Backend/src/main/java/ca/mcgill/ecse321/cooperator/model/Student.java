package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Student extends User{
   private int id;

public void setId(int value) {
    this.id = value;
}
public int getId() {
    return this.id;
}
private Faculty faculty;

public void setFaculty(Faculty value) {
    this.faculty = value;
}
public Faculty getFaculty() {
    return this.faculty;
}
private String major;

public void setMajor(String value) {
    this.major = value;
}
public String getMajor() {
    return this.major;
}
private String minor;

public void setMinor(String value) {
    this.minor = value;
}
public String getMinor() {
    return this.minor;
}
   private Set<Administrator> administrator;
   
   @ManyToMany(mappedBy="student" )
   public Set<Administrator> getAdministrator() {
      return this.administrator;
   }
   
   public void setAdministrator(Set<Administrator> administrators) {
      this.administrator = administrators;
   }
   
   private Set<Coop> coop;
   
   @OneToMany(mappedBy="student" )
   public Set<Coop> getCoop() {
      return this.coop;
   }
   
   public void setCoop(Set<Coop> coops) {
      this.coop = coops;
   }
   
   private Set<Reminder> reminders;
   public Set<Reminder> getReminder() {
	      return this.reminders;
	   }
	   
	   public void setReminder(Set<Reminder> reminders) {
	      this.reminders = reminders;
	   }
   }
