package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;

@Entity
public class TasksWorkloadReport extends Form {
	private String tasks;

	public void setTasks(String value) {
		this.tasks = value;
	}

	public String getTasks() {
		return this.tasks;
	}

	private int hoursPerWeek;

	public void setHoursPerWeek(int value) {
		this.hoursPerWeek = value;
	}

	public int getHoursPerWeek() {
		return this.hoursPerWeek;
	}

	private int wage;

	public void setWage(int value) {
		this.wage = value;
	}

	public int getWage() {
		return this.wage;
	}

	private String training;

	public void setTraining(String value) {
		this.training = value;
	}

	public String getTraining() {
		return this.training;
	}
}
