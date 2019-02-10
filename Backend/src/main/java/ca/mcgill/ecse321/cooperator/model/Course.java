package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course{
   private String id;

public void setId(String value) {
    this.id = value;
}
@Id
public String getId() {
    return this.id;
}
private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
}
}
