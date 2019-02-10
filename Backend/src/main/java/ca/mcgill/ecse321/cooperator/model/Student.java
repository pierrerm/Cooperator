package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
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
private int year;

public void setYear(int value) {
    this.year = value;
}
public int getYear() {
    return this.year;
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
private Administrator administrator;

@ManyToOne
public Administrator getAdministrator() {
   return this.administrator;
}

public void setAdministrator(Administrator administrator) {
   this.administrator = administrator;
}

private Set<Coop> coop;

@OneToMany(mappedBy="student" )
public Set<Coop> getCoop() {
   return this.coop;
}

public void setCoop(Set<Coop> coops) {
   this.coop = coops;
}

private Faculty faculty;

public void setFaculty(Faculty value) {
    this.faculty = value;
}
public Faculty getFaculty() {
    return this.faculty;
}
}
