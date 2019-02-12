package ca.mcgill.ecse321.cooperator.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.cooperator.dao.AdministratorRepository;
import ca.mcgill.ecse321.cooperator.dao.CoopRepository;
import ca.mcgill.ecse321.cooperator.dao.DownloadableDocRepository;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.dao.FormRepository;
import ca.mcgill.ecse321.cooperator.dao.ReminderRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.model.AcceptanceForm;
import ca.mcgill.ecse321.cooperator.model.Administrator;
import ca.mcgill.ecse321.cooperator.model.Coop;
import ca.mcgill.ecse321.cooperator.model.CoopEvaluation;
import ca.mcgill.ecse321.cooperator.model.DocumentType;
import ca.mcgill.ecse321.cooperator.model.DownloadableDoc;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.EmployerContract;
import ca.mcgill.ecse321.cooperator.model.Faculty;
import ca.mcgill.ecse321.cooperator.model.Form;
import ca.mcgill.ecse321.cooperator.model.Reminder;
import ca.mcgill.ecse321.cooperator.model.Semester;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.StudentEvaluation;
import ca.mcgill.ecse321.cooperator.model.TasksWorkloadReport;
import ca.mcgill.ecse321.cooperator.model.TechnicalReport;

@Service
public class CooperatorService {

	@Autowired
	CoopRepository coopRepository;
	@Autowired
	DownloadableDocRepository downloadableDocRepository;
	@Autowired
	FormRepository formRepository;
	@Autowired
	ReminderRepository reminderRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	EmployerRepository employerRepository;
	@Autowired
	AdministratorRepository administratorRepository;

	// Coop
	@Transactional
	public Coop createCoop(int jobId, Boolean employerConfirmation, Date endDate, String jobDescription,
			String location, Boolean needWorkPermit, Semester semester, Date startDate, String year,
			DownloadableDoc doc, Employer employer, Form form, Student student) {
		Coop coop = new Coop();
		coop.setJobId(jobId);
		coop.setEmployerConfirmation(employerConfirmation);
		coop.setEndDate(endDate);
		coop.setJobDescription(jobDescription);
		coop.setLocation(location);
		coop.setNeedWorkPermit(needWorkPermit);
		coop.setSemester(semester);
		coop.setStartDate(startDate);
		coop.setYear(year);
		coop.setDownloadableDoc(doc);
		coop.setEmployer(employer);
		coop.setForm(form);
		coop.setStudent(student);
		coopRepository.save(coop);
		return coop;
	}

	@Transactional
	public Coop getCoop(int jobId) {
		Coop coop = coopRepository.findCoopByJobId(jobId);
		return coop;
	}

	@Transactional
	public List<Coop> getAllCoops() {
		return toList(coopRepository.findAll());
	}

	// Form -- AcceptanceForm
	@Transactional
	public AcceptanceForm createAcceptanceForm(String filePath, String formId, Date submissionDate) {
		AcceptanceForm form = new AcceptanceForm();
		form.setFilePath(filePath);
		form.setFormId(formId);
		form.setSubmissionDate(submissionDate);
		formRepository.save(form);
		return form;
	}

	// Form -- EmployerContract
	@Transactional
	public EmployerContract createEmployerContract(String filePath, String formId, Date submissionDate) {
		EmployerContract form = new EmployerContract();
		form.setFilePath(filePath);
		form.setFormId(formId);
		form.setSubmissionDate(submissionDate);
		formRepository.save(form);
		return form;
	}

	// Form -- TechnicalReport
	@Transactional
	public TechnicalReport createTechnicalReport(String filePath, String formId, Date submissionDate) {
		TechnicalReport form = new TechnicalReport();
		form.setFilePath(filePath);
		form.setFormId(formId);
		form.setSubmissionDate(submissionDate);
		formRepository.save(form);
		return form;
	}

	// Form -- CooEvaluation
	@Transactional
	public CoopEvaluation createCoopEvaluation(String filePath, String formId, Date submissionDate) {
		CoopEvaluation form = new CoopEvaluation();
		form.setFilePath(filePath);
		form.setFormId(formId);
		form.setSubmissionDate(submissionDate);
		formRepository.save(form);
		return form;
	}

	// Form -- StudentEvaluation
	@Transactional
	public StudentEvaluation createStudentEvaluation(String filePath, String formId, Date submissionDate) {
		StudentEvaluation form = new StudentEvaluation();
		form.setFilePath(filePath);
		form.setFormId(formId);
		form.setSubmissionDate(submissionDate);
		formRepository.save(form);
		return form;
	}

	// Form -- TasksWorkloadReport
	@Transactional
	public TasksWorkloadReport createTasksWorkloadReport(String filePath, String formId, Date submissionDate) {
		TasksWorkloadReport form = new TasksWorkloadReport();
		form.setFilePath(filePath);
		form.setFormId(formId);
		form.setSubmissionDate(submissionDate);
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

	// Reminder
	@Transactional
	public Reminder createReminder(int reminderId, String subject, Date date, Date deadline, String description,
			int urgency) {
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

	// DownloadableDoc
	@Transactional
	public DownloadableDoc createDownloadableDoc(int docId, String filePath, DocumentType docType) {
		DownloadableDoc downloadableDoc = new DownloadableDoc();
		downloadableDoc.setDocId(docId);
		downloadableDoc.setFilePath(filePath);
		downloadableDoc.setDocumentType(docType);
		downloadableDocRepository.save(downloadableDoc);
		return downloadableDoc;
	}

	@Transactional
	public DownloadableDoc getDownloadableDoc(int docId) {
		DownloadableDoc downloadableDoc = downloadableDocRepository.findDownloadableDocByDocId(docId);
		return downloadableDoc;
	}

	@Transactional
	public List<DownloadableDoc> getAllDownloadableDocs() {
		return toList(downloadableDocRepository.findAll());
	}

	// Student
	@Transactional
	public Student createStudent(int userId, String email, String firstName, String lastName, String password,
			Faculty faculty, int id, String major, String minor, int year, Administrator admin) {
		Student student = new Student();
		student.setUserId(userId);
		student.setEmail(email);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setPassword(password);
		student.setFaculty(faculty);
		student.setId(id);
		student.setMajor(major);
		student.setMinor(minor);
		student.setYear(year);
		student.setAdministrator(admin);
		studentRepository.save(student);
		return student;
	}

	@Transactional
	public Student getStudent(int userId) {
		Student student = studentRepository.findStudentByUserId(userId);
		return student;
	}

	@Transactional
	public List<Student> getAllStudents() {
		return toList(studentRepository.findAll());
	}

	// Employer
	@Transactional
	public Employer createEmployer(int userId, String email, String firstName, String lastName, String password,
			String company, String location, int phone, String position) {
		Employer employer = new Employer();
		employer.setUserId(userId);
		employer.setEmail(email);
		employer.setFirstName(firstName);
		employer.setLastName(lastName);
		employer.setPassword(password);
		employer.setCompany(company);
		employer.setLocation(location);
		employer.setPhone(phone);
		employer.setPosition(position);
		employerRepository.save(employer);
		return employer;
	}

	@Transactional
	public Employer getEmployer(int userId) {
		Employer employer = employerRepository.findEmployerByUserId(userId);
		return employer;
	}

	@Transactional
	public List<Employer> getAllEmployers() {
		return toList(employerRepository.findAll());
	}

	// Administrator
	@Transactional
	public Administrator createAdministrator(int userId, String email, String firstName, String lastName,
			String password, Faculty faculty, int id) {
		Administrator administrator = new Administrator();
		administrator.setUserId(userId);
		administrator.setEmail(email);
		administrator.setLastName(lastName);
		administrator.setPassword(password);
		administrator.setFaculty(faculty);
		administrator.setId(id);
		administratorRepository.save(administrator);
		return administrator;
	}

	@Transactional
	public Administrator getAdministrator(int userId) {
		Administrator administrator = administratorRepository.findAdministratorByUserId(userId);
		return administrator;
	}

	@Transactional
	public List<Administrator> getAllAdministrators() {
		return toList(administratorRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
