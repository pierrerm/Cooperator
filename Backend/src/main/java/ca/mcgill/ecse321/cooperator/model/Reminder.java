package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import javax.persistence.ManyToOne;

@Entity
public class Reminder {
	private int reminderId;

	public void setReminderId(int value) {
		this.reminderId = value;
	}

	@Id
	public int getReminderId() {
		return this.reminderId;
	}

	private String subject;

	public void setSubject(String value) {
		this.subject = value;
	}

	public String getSubject() {
		return this.subject;
	}

	private Date date;

	public void setDate(Date value) {
		this.date = value;
	}

	public Date getDate() {
		return this.date;
	}

	private Date deadLine;

	public void setDeadLine(Date value) {
		this.deadLine = value;
	}

	public Date getDeadLine() {
		return this.deadLine;
	}

	private String description;

	public void setDescription(String value) {
		this.description = value;
	}

	public String getDescription() {
		return this.description;
	}

	private int urgency;

	public void setUrgency(int value) {
		this.urgency = value;
	}

	public int getUrgency() {
		return this.urgency;
	}

	private Coop coop;

	@ManyToOne(optional = false)
	public Coop getCoop() {
		return this.coop;
	}

	public void setCoop(Coop coop) {
		this.coop = coop;
	}

}
