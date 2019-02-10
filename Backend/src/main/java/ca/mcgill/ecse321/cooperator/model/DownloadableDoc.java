package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public abstract class DownloadableDoc{
   private Date downloadDate;

public void setDownloadDate(Date value) {
    this.downloadDate = value;
}
public Date getDownloadDate() {
    return this.downloadDate;
}
}
