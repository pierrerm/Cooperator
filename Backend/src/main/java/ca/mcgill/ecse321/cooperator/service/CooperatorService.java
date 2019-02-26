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
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.dao.FormRepository;
import ca.mcgill.ecse321.cooperator.dao.PDFRepository;
import ca.mcgill.ecse321.cooperator.dao.ReminderRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.model.AcceptanceForm;
import ca.mcgill.ecse321.cooperator.model.Administrator;
import ca.mcgill.ecse321.cooperator.model.Coop;
import ca.mcgill.ecse321.cooperator.model.CoopEvaluation;
import ca.mcgill.ecse321.cooperator.model.DocumentType;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Faculty;
import ca.mcgill.ecse321.cooperator.model.Form;
import ca.mcgill.ecse321.cooperator.model.PDF;
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
	public Coop createCoop(int coopId, boolean employerConfirmation, Date endDate, String jobDescription, int jobId,
			String location, boolean needWorkPermit, Semester semester, Date startDate, Student student,
			Employer employer) {
		String error = "";
		if (employer == null) {
			error = error + "Employer cannot be null! ";
		}
		if (student == null) {
			error = error + "Student cannot be null! ";
		}
		if (startDate != null && endDate != null) {
			if (startDate.after(endDate)) {
				error = error + "Start date cannot be after end date! ";
			}
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

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

		if (coop == null) {
			throw new IllegalArgumentException("Coop cannot be null! ");
		}
		AcceptanceForm acceptanceForm = new AcceptanceForm();
		acceptanceForm.setFormId(formId);
		acceptanceForm.setSubmissionDate(submissionDate);
		acceptanceForm.setCoop(coop);
		formRepository.save(acceptanceForm);
		return acceptanceForm;
	}

	// Form -- CoopEvaluation
	@Transactional
	public CoopEvaluation createCoopEvaluation(int formId, Date submissionDate, String workExperience,
			int employerEvaluation, String softwareTechnologies, String usefulCourses, Coop coop) {
		if (coop == null) {
			throw new IllegalArgumentException("Coop cannot be null! ");
		}
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
	public StudentEvaluation createStudentEvaluation(int formId, Date submissionDate, String studentWorkExperience,
			int studentPerformance, Coop coop) {
		if (coop == null) {
			throw new IllegalArgumentException("Coop cannot be null! ");
		}
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
	public TasksWorkloadReport createTasksWorkloadReport(int formId, Date submissionDate, String tasks,
			int hoursPerWeek, int wage, String training, Coop coop) {
		if (coop == null) {
			throw new IllegalArgumentException("Coop cannot be null! ");
		}
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
		if (formId == null || formId.trim().length() == 0) {
			throw new IllegalArgumentException("Form ID cannot be empty! ");
		}
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
		String error = "";
		if (coop == null) {
			error = error + "Coop cannot be null! ";
		}

		if (date != null && deadline != null) {
			if (date.after(deadline)) {
				error = error + "Issue date cannot be after deadline! ";
			}
		}
		if (urgency < 0 || urgency > 3) {
			error = error + "Urgency can only be between 0 and 3. ";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

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
	
	@Transactional
	public Reminder sendReminder(List<Student> problematicStudents) {
		int reminderId, urgency; 
		String subject, description; 
		Date date, deadline; 
		Coop coop_problem;
		
		for (Student student: problematicStudents) {
			Set<Coop> coops = student.getCoop();
			if(coops.isEmpty()) break;
			for (Coop coop: coops) {
				Date startDate = coop.getStartDate();
			}
		}
		return null;	
	}

	// PDF
	@Transactional
	public PDF createPDF(int docId, String filePath, DocumentType docType, Date submissionDate, Coop coop) {
		if (coop == null) {
			throw new IllegalArgumentException("Coop cannot be null! ");
		}
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
	public Student createStudent(int userId, long phone, String email, String firstName, String lastName,
			String password, Faculty faculty, int id, String major, String minor, String academicYear,
			Administrator admin) {

		String error = "";
		if (firstName == null || firstName.trim().length() == 0) {
			error = error + "First name cannot be null! ";
		}
		if (lastName == null || lastName.trim().length() == 0) {
			error = error + "Last name cannot be null! ";
		}
		if (password == null || password.trim().length() == 0) {
			error = error + "Password cannot be null! ";
		}
		if (email == null || email.trim().length() == 0) {
			error = error + "Email cannot be null! ";
		}
		if (faculty == null) {
			error = error + "Faculty cannot be null! ";
		}
		if (major == null || major.trim().length() == 0) {
			error = error + "Major cannot be null! ";
		}
		if (academicYear == null || academicYear.trim().length() == 0) {
			error = error + "Academic year cannot be null! ";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

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
	public Employer createEmployer(int userId, long phone, String email, String firstName, String lastName,
			String password, String position, String company, String location) {

		String error = "";
		if (firstName == null || firstName.trim().length() == 0) {
			error = error + "First name cannot be null! ";
		}
		if (lastName == null || lastName.trim().length() == 0) {
			error = error + "Last name cannot be null! ";
		}
		if (password == null || password.trim().length() == 0) {
			error = error + "Password cannot be null! ";
		}
		if (email == null || email.trim().length() == 0) {
			error = error + "Email cannot be null! ";
		}
		if (position == null || position.trim().length() == 0) {
			error = error + "Position cannot be null! ";
		}
		if (company == null || company.trim().length() == 0) {
			error = error + "Company cannot be null! ";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

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

		String error = "";
		if (firstName == null || firstName.trim().length() == 0) {
			error = error + "First name cannot be null! ";
		}
		if (lastName == null || lastName.trim().length() == 0) {
			error = error + "Last name cannot be null! ";
		}
		if (password == null || password.trim().length() == 0) {
			error = error + "Password cannot be null! ";
		}
		if (email == null || email.trim().length() == 0) {
			error = error + "Email cannot be null! ";
		}
		if (faculty == null) {
			error = error + "Faculty cannot be null! ";
		}

		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
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

	@Transactional
	public List<Student> getAllStudentsWithFormError() {
		return studentRepository.findStudentsWithError();
	}
	
	@Transactional
	public double[] getSemesterStatistics(Semester semester, int year) {
		// num students doing coop term this semester, avg num of coops completed per active students, avg coop completion (files submitted) for ongoing coops this semester
		
		List<Student> students = getAllStudents();
		
		double numActiveStudents = 0;
		double numCompletedCoops = 0;
		double numSubmittedForms = 0;
		List<Coop> ongoingCoops = null;
		
		for(Student student : students) {
			
			boolean isActive = false;
			double tempNumCompletedCoops = 0;
			double tempNumSubmittedForms = 0;
			Coop ongoingCoop = null;
			
			Set<Coop> coops = student.getCoop();
			if(coops.isEmpty()) break;
			
			for(Coop coop : coops) {
				if(coopRepository.isInSemester(coop, semester, year)) {
					isActive = true;
					ongoingCoop = coop;
					tempNumSubmittedForms = coop.getForm().size();
				}
				else if(coop.getForm().size() >= 4) {
					tempNumCompletedCoops++;
				}
			}
			
			if(isActive) {
				numActiveStudents++;
				numCompletedCoops += tempNumCompletedCoops;
				numSubmittedForms += tempNumSubmittedForms;
				ongoingCoops.add(ongoingCoop);
			}
		}
		double[] stats = {numActiveStudents, (numCompletedCoops/numActiveStudents), (numSubmittedForms/numActiveStudents)};
		return stats;
	}

}
