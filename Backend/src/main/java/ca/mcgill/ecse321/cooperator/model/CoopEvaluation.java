package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class CoopEvaluation extends Form{
   private Coop coop;
   
   @OneToOne(optional=false)
   public Coop getCoop() {
      return this.coop;
   }
   
   public void setCoop(Coop coop) {
      this.coop = coop;
   }
   
   }
