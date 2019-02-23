package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.Reminder;

public interface ReminderRepository extends CrudRepository<Reminder, String> {

	Reminder findReminderByReminderId(int id);
}
