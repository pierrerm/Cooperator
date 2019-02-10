package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class CoopEvaluation extends Form{
   private Set<Course> course;
   
   @OneToMany
   public Set<Course> getCourse() {
      return this.course;
   }
   
   public void setCourse(Set<Course> courses) {
      this.course = courses;
   }
   
   private Set<Technology> technology;
   
   @OneToMany
   public Set<Technology> getTechnology() {
      return this.technology;
   }
   
   public void setTechnology(Set<Technology> technologys) {
      this.technology = technologys;
   }
   
   }
