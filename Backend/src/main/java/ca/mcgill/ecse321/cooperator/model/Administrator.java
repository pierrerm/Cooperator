package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Administrator extends User{
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
   private Set<Student> student;
   
   @ManyToMany
   public Set<Student> getStudent() {
      return this.student;
   }
   
   public void setStudent(Set<Student> students) {
      this.student = students;
   }
   
   private Set<Reminder> reminders;
   public Set<Reminder> getReminder() {
	      return this.reminders;
	   }
	   
	   public void setReminder(Set<Reminder> reminders) {
	      this.reminders = reminders;
	   }
   
   }
