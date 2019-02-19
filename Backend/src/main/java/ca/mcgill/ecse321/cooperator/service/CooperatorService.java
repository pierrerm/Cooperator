package ca.mcgill.ecse321.cooperator.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.cooperator.dao.AdministratorRepository;
import ca.mcgill.ecse321.cooperator.dao.CoopRepository;
import ca.mcgill.ecse321.cooperator.dao.PDFRepository;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.dao.FormRepository;
import ca.mcgill.ecse321.cooperator.dao.ReminderRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.model.AcceptanceForm;
import ca.mcgill.ecse321.cooperator.model.Administrator;
import ca.mcgill.ecse321.cooperator.model.Coop;
import ca.mcgill.ecse321.cooperator.model.CoopEvaluation;
import ca.mcgill.ecse321.cooperator.model.DocumentType;
import ca.mcgill.ecse321.cooperator.model.PDF;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Faculty;
import ca.mcgill.ecse321.cooperator.model.Form;
import ca.mcgill.ecse321.cooperator.model.Reminder;
import ca.mcgill.ecse321.cooperator.model.Semester;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.StudentEvaluation;
import ca.mcgill.ecse321.cooperator.model.TasksWorkloadReport;

@Service
public class CooperatorService {

	@Autowired
	CoopRepository coopRepository;
	@Autowired
	PDFRepository PDFRepository;
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
	public Coop createCoop(int coopId, int jobId, boolean employerConfirmation, Date endDate, String jobDescription,
			String location, boolean needWorkPermit, Semester semester, Date startDate, Employer employer, Student student) {
		Coop coop = new Coop();
		coop.setCoopId(coopId);
		coop.setJobId(jobId);
		coop.setEmployerConfirmation(employerConfirmation);
		coop.setEndDate(endDate);
		coop.setJobDescription(jobDescription);
		coop.setLocation(location);
		coop.setNeedWorkPermit(needWorkPermit);
		coop.setSemester(semester);
		coop.setStartDate(startDate);
		coop.setEmployer(employer);
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
	public AcceptanceForm createAcceptanceForm(int formId, Date submissionDate, Coop coop) {
		AcceptanceForm acceptanceForm = new AcceptanceForm();
		acceptanceForm.setFormId(formId);
		acceptanceForm.setSubmissionDate(submissionDate);
		acceptanceForm.setCoop(coop);
		formRepository.save(acceptanceForm);
		return acceptanceForm;
	}

	// Form -- CoopEvaluation
	@Transactional
	public CoopEvaluation createCoopEvaluation(int formId, Date submissionDate, String workExperience, int employerEvaluation, String softwareTechnologies, String usefulCourses, Coop coop) {
		CoopEvaluation coopEvaluation = new CoopEvaluation();
		coopEvaluation.setFormId(formId);
		coopEvaluation.setSubmissionDate(submissionDate);
		coopEvaluation.setWorkExperience(workExperience);
		coopEvaluation.setEmployerEvaluation(employerEvaluation);
		coopEvaluation.setSoftwareTechnologies(softwareTechnologies);
		coopEvaluation.setUsefulCourses(usefulCourses);
		coopEvaluation.setCoop(coop);
		formRepository.save(coopEvaluation);
		return coopEvaluation;
	}

	// Form -- StudentEvaluation
	@Transactional
	public StudentEvaluation createStudentEvaluation(int formId, Date submissionDate, String studentWorkExperience, int studentPerformance, Coop coop) {
		StudentEvaluation studentEvaluation = new StudentEvaluation();
		studentEvaluation.setFormId(formId);
		studentEvaluation.setSubmissionDate(submissionDate);
		studentEvaluation.setStudentWorkExperience(studentWorkExperience);
		studentEvaluation.setStudentPerformance(studentPerformance);
		studentEvaluation.setCoop(coop);
		formRepository.save(studentEvaluation);
		return studentEvaluation;
	}

	// Form -- TasksWorkloadReport
	@Transactional
	public TasksWorkloadReport createTasksWorkloadReport(int formId, Date submissionDate, String tasks, int hoursPerWeek, int wage, String training, Coop coop) {
		TasksWorkloadReport tasksWorkloadReport = new TasksWorkloadReport();
		tasksWorkloadReport.setFormId(formId);
		tasksWorkloadReport.setSubmissionDate(submissionDate);
		tasksWorkloadReport.setTasks(tasks);
		tasksWorkloadReport.setHoursPerWeek(hoursPerWeek);
		tasksWorkloadReport.setWage(wage);
		tasksWorkloadReport.setTraining(training);
		tasksWorkloadReport.setCoop(coop);
		formRepository.save(tasksWorkloadReport);
		return tasksWorkloadReport;
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
			int urgency, Coop coop) {
		Reminder reminder = new Reminder();
		reminder.setReminderId(reminderId);
		reminder.setSubject(subject);
		reminder.setDate(date);
		reminder.setDeadLine(deadline);
		reminder.setDescription(description);
		reminder.setUrgency(urgency);
		reminder.setCoop(coop);
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

	// PDF
	@Transactional
	public PDF createPDF(int docId, String filePath, DocumentType docType, Date submissionDate, Coop coop) {
		PDF pdf = new PDF();
		pdf.setDocId(docId);
		pdf.setFilePath(filePath);
		pdf.setDocumentType(docType);
		pdf.setSubmissionDate(submissionDate);
		pdf.setCoop(coop);
		PDFRepository.save(pdf);
		return pdf;
	}

	@Transactional
	public PDF getPDF(int docId) {
		PDF pdf = PDFRepository.findPDFByDocId(docId);
		return pdf;
	}

	@Transactional
	public List<PDF> getAllPDFs() {
		return toList(PDFRepository.findAll());
	}

	// Student
	@Transactional
	public Student createStudent(int userId, long phone, String email, String firstName, String lastName, String password,
			Faculty faculty, int id, String major, String minor, String academicYear, Administrator admin) {
		Student student = new Student();
		student.setUserId(userId);
		student.setEmail(email);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setPassword(password);
		student.setPhone(phone);
		student.setFaculty(faculty);
		student.setId(id);
		student.setMajor(major);
		student.setMinor(minor);
		student.setAcademicYear(academicYear);
		//student.setAdministrator(admin);
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
			String company, String location, long phone, String position) {
		Employer employer = new Employer();
		employer.setUserId(userId);
		employer.setEmail(email);
		employer.setFirstName(firstName);
		employer.setLastName(lastName);
		employer.setPassword(password);
		employer.setPhone(phone);
		employer.setCompany(company);
		employer.setLocation(location);
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
	public Administrator createAdministrator(int userId, long phone, String email, String firstName, String lastName,
			String password, Faculty faculty, int id) {
		Administrator administrator = new Administrator();
		administrator.setUserId(userId);
		administrator.setEmail(email);
		administrator.setFirstName(firstName);
		administrator.setLastName(lastName);
		administrator.setPassword(password);
		administrator.setPhone(phone);
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
