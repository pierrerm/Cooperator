package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;

@Entity
public class Technology{
   private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
}
private String description;

public void setDescription(String value) {
    this.description = value;
}
public String getDescription() {
    return this.description;
}
}
