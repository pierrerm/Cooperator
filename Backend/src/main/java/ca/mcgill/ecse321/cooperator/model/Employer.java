package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Employer extends User{
   private String position;

public void setPosition(String value) {
    this.position = value;
}
public String getPosition() {
    return this.position;
}
private String company;

public void setCompany(String value) {
    this.company = value;
}
public String getCompany() {
    return this.company;
}
private int phone;

public void setPhone(int value) {
    this.phone = value;
}
public int getPhone() {
    return this.phone;
}
private Set<Coop> coop;

@OneToMany(mappedBy="employer" )
public Set<Coop> getCoop() {
   return this.coop;
}

public void setCoop(Set<Coop> coops) {
   this.coop = coops;
}

private String location;

public void setLocation(String value) {
    this.location = value;
}
public String getLocation() {
    return this.location;
}
}
