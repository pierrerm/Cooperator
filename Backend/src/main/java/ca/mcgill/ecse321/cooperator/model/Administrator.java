package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Administrator extends User{
   private int id;

public void setId(int value) {
    this.id = value;
}
public int getId() {
    return this.id;
}
private Set<Student> student;

@OneToMany(mappedBy="administrator" )
public Set<Student> getStudent() {
   return this.student;
}

public void setStudent(Set<Student> students) {
   this.student = students;
}

private Faculty faculty;

public void setFaculty(Faculty value) {
    this.faculty = value;
}
public Faculty getFaculty() {
    return this.faculty;
}
}
