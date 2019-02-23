package ca.mcgill.ecse321.cooperator.dto;

import java.sql.Date;

import ca.mcgill.ecse321.cooperator.model.Coop;

public class ReminderDto {

	private int reminderId;
	private String subject;
	private Date date;
	private Date deadLine;
	private String description;
	private int urgency;
	private Coop coop;

	public ReminderDto() {

	}

	public ReminderDto(int reminderId, String subject, Date date, Date deadLine, String description, int urgency,
			Coop coop) {
		this.reminderId = reminderId;
		this.subject = subject;
		this.date = date;
		this.deadLine = deadLine;
		this.description = description;
		this.urgency = urgency;
		this.coop = coop;
	}

	public int getReminderId() {
		return this.reminderId;
	}

	public String getSubject() {
		return this.subject;
	}

	public Date getDate() {
		return this.date;
	}

	public Date getDeadLine() {
		return this.deadLine;
	}

	public String getDescription() {
		return this.description;
	}

	public int getUrgency() {
		return this.urgency;
	}

	public Coop getCoop() {
		return this.coop;
	}

}
