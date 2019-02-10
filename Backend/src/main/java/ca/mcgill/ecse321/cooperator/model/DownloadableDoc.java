package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DownloadableDoc{
   private int docId;

public void setDocId(int value) {
    this.docId = value;
}
@Id
public int getDocId() {
    return this.docId;
}
private String filePath;

public void setFilePath(String value) {
    this.filePath = value;
}
public String getFilePath() {
    return this.filePath;
}
}
