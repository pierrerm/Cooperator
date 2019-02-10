package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class TechnicalReport extends Form{
   private String subject;

public void setSubject(String value) {
    this.subject = value;
}
public String getSubject() {
    return this.subject;
}
   private Coop coop;
   
   @OneToOne(optional=false)
   public Coop getCoop() {
      return this.coop;
   }
   
   public void setCoop(Coop coop) {
      this.coop = coop;
   }
   
   }
