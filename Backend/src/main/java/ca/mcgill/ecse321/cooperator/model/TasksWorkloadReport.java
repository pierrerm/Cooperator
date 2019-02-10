package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
public class TasksWorkloadReport extends Form{
   private int hours;

public void setHours(int value) {
    this.hours = value;
}
public int getHours() {
    return this.hours;
}
private String tasks;

public void setTasks(String value) {
    this.tasks = value;
}
public String getTasks() {
    return this.tasks;
}
private Coop coop;

@OneToOne(optional=false)
public Coop getCoop() {
   return this.coop;
}

public void setCoop(Coop coop) {
   this.coop = coop;
}

private int taskID;

public void setTaskID(int value) {
    this.taskID = value;
}
@Id
public int getTaskID() {
    return this.taskID;
}
}
