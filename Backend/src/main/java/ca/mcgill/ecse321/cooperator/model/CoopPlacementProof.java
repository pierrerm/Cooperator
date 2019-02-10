package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class CoopPlacementProof extends DownloadableDoc{
   private Coop coop;
   
   @OneToOne(mappedBy="coopPlacementProof" , optional=false)
   public Coop getCoop() {
      return this.coop;
   }
   
   public void setCoop(Coop coop) {
      this.coop = coop;
   }
   
   }
