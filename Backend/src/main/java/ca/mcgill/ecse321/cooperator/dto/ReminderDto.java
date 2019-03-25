package ca.mcgill.ecse321.cooperator.dto;

import java.sql.Date;

public class ReminderDto {

	private int reminderId;
	private String subject;
	private Date date;
	private Date deadLine;
	private String description;
	private int urgency;
	private CoopDto coop;

	public ReminderDto() {

	}

	public ReminderDto(int reminderId, String subject, Date date, Date deadLine, String description, int urgency,
			CoopDto coop) {
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

	public CoopDto getCoop() {
		return this.coop;
	}

}
