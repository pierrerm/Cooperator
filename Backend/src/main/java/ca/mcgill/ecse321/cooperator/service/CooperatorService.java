package ca.mcgill.ecse321.cooperator.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.cooperator.dao.CoopRepository;
import ca.mcgill.ecse321.cooperator.dao.DownloadableDocRepository;
import ca.mcgill.ecse321.cooperator.dao.FormRepository;
import ca.mcgill.ecse321.cooperator.dao.ReminderRepository;
import ca.mcgill.ecse321.cooperator.model.Coop;
import ca.mcgill.ecse321.cooperator.model.DownloadableDoc;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Form;
import ca.mcgill.ecse321.cooperator.model.Reminder;
import ca.mcgill.ecse321.cooperator.model.Semester;
import ca.mcgill.ecse321.cooperator.model.Student;


@Service
public class CooperatorService {
	
	@Autowired(required=false)
	CoopRepository coopRepository;
	@Autowired(required=false)
	DownloadableDocRepository downloadableDocRepository;
	@Autowired(required=false)
	FormRepository formRepository;
	@Autowired(required=false)
	ReminderRepository reminderRepository;
	
	@Transactional
	public Coop createCoop(int year, String location, String jobDescription, Boolean needWorkPermit, Boolean employerConfirmation, int jobId, Semester semester, Student student, Employer employer) {
		Coop coop = new Coop();
		coop.setYear(year);
		coop.setLocation(location);
		coop.setJobDescription(jobDescription);
		coop.setNeedWorkPermit(needWorkPermit);
		coop.setEmployerConfirmation(employerConfirmation);
		coop.setJobId(jobId);
		coop.setSemester(semester);
		coop.setStudent(student);
		coop.setEmployer(employer);
		coopRepository.save(coop);
		return coop;
	}
	
	@Transactional
	public Coop getCoop(int jobId) {
		Coop coop = coopRepository.findCoopByJobId(jobId);
		return coop;
	}
	
//	@Transactional
//	public List<Coop> getAllCoops() {
//		//return toList(coopRepository.findAll());
//	}
	
	@Transactional
	public Form createForm(Boolean submitted, String filePath, String formId) {
		Form form = new Form();
		form.setSubmitted(submitted);
		form.setFilePath(filePath);
		form.setFormId(formId);
		formRepository.save(form);
		return form;
	}
	
	@Transactional
	public Form getForm(String formId) {
		Form form = formRepository.findFormByFormId(formId);
		return form;
	}
	
	@Transactional
	public List<Form> getAllForms() {
		return toList(formRepository.findAll());
	}
	
	@Transactional
	public Reminder createReminder(int reminderId, String subject, Date date, Date deadline, String description, int urgency) {
		Reminder reminder = new Reminder();
		reminder.setReminderId(reminderId);
		reminder.setSubject(subject);
		reminder.setDate(date);
		reminder.setDeadLine(deadline);
		reminder.setDescription(description);
		reminder.setUrgency(urgency);
		reminderRepository.save(reminder);
		return reminder;
	}
	
	@Transactional
	public Reminder getReminder(int reminderId) {
		Reminder reminder = reminderRepository.findReminderByReminderId(reminderId);
		return reminder;
	}
	
	@Transactional
	public List<Reminder> getAllReminders() {
		return toList(reminderRepository.findAll());
	}
	
	@Transactional
	public DownloadableDoc createDownloadableDoc(int docId, String filePath) {
		DownloadableDoc downloadableDoc = new DownloadableDoc();
		downloadableDoc.setDocId(docId);
		downloadableDoc.setFilePath(filePath);
		downloadableDocRepository.save(downloadableDoc);
		return downloadableDoc;
	}
	
	@Transactional
	public DownloadableDoc getDownloadableDoc(int docId) {
		DownloadableDoc downloadableDoc = downloadableDocRepository.findDownloadableFormByDocId(docId);
		return downloadableDoc;
	}
	
	@Transactional
	public List<DownloadableDoc> getAllDownloadableDocs() {
		return toList(downloadableDocRepository.findAll());
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
