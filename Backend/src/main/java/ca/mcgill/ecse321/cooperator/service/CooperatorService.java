package ca.mcgill.ecse321.cooperator.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
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

		if (student.getCoop() == null) {
			Set<Coop> coops = new HashSet<Coop>();
			coops.add(coop);
			student.setCoop(coops);
		} else {
			Set<Coop> coops = student.getCoop();
			coops.add(coop);
			student.setCoop(coops);
		}

		if (employer.getCoop() == null) {
			Set<Coop> coops = new HashSet<Coop>();
			coops.add(coop);
			employer.setCoop(coops);
		} else {
			Set<Coop> coops = employer.getCoop();
			coops.add(coop);
			employer.setCoop(coops);
		}

		coopRepository.save(coop);
		studentRepository.save(student);
		employerRepository.save(employer);
		return coop;
	}

	@Transactional
	public Coop getCoop(int jobId) {
		Coop coop = coopRepository.findCoopByCoopId(jobId);
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

		if (coop.getForm() == null) {
			Set<Form> forms = new HashSet<Form>();
			forms.add(acceptanceForm);
			coop.setForm(forms);
		} else {
			Set<Form> forms = coop.getForm();
			forms.add(acceptanceForm);
			coop.setForm(forms);
		}

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

		if (coop.getForm() == null) {
			Set<Form> forms = new HashSet<Form>();
			forms.add(coopEvaluation);
			coop.setForm(forms);
		} else {
			Set<Form> forms = coop.getForm();
			forms.add(coopEvaluation);
			coop.setForm(forms);
		}

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

		if (coop.getForm() == null) {
			Set<Form> forms = new HashSet<Form>();
			forms.add(studentEvaluation);
			coop.setForm(forms);
		} else {
			Set<Form> forms = coop.getForm();
			forms.add(studentEvaluation);
			coop.setForm(forms);
		}

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

		if (coop.getForm() == null) {
			Set<Form> forms = new HashSet<Form>();
			forms.add(tasksWorkloadReport);
			coop.setForm(forms);
		} else {
			Set<Form> forms = coop.getForm();
			forms.add(tasksWorkloadReport);
			coop.setForm(forms);
		}

		formRepository.save(tasksWorkloadReport);
		return tasksWorkloadReport;
	}

	@Transactional
	public Form getForm(int formId) {
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

		if (coop.getReminder() == null) {
			Set<Reminder> reminders = new HashSet<>();
			reminders.add(reminder);
			coop.setReminder(reminders);
		} else {
			Set<Reminder> reminders = coop.getReminder();
			reminders.add(reminder);
			coop.setReminder(reminders);
		}

		reminderRepository.save(reminder);
		coopRepository.save(coop);
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

	/**
	 * 
	 * @author JulienLesaffre
	 */
	@Transactional
	public List<Reminder> sendReminders() {
		int reminderId, urgency;
		String subject, description;
		Date date, deadline;
		List<Student> problematicStudents = this.getAllStudents();
		List<Reminder> remindersSent = new ArrayList<Reminder>();
		if (problematicStudents.isEmpty()) return remindersSent;
		for (Student student : problematicStudents) {
			Set<Coop> coops = student.getCoop();

			if (coops.isEmpty())
				break;
			for (Coop coop : coops) {
				Date startDate = coop.getStartDate();
				date = new Date(System.currentTimeMillis()); // return today's date
				deadline = addDays(startDate, -14);
				Date threeDaysLeft = addDays(deadline, -3);
				Set<Form> forms = coop.getForm();
				boolean isTasksWorkloadReportSubmited = false;
				for (Form form : forms) {
					if (form instanceof TasksWorkloadReport) {
						isTasksWorkloadReportSubmited = true;
						break;
					}
				}
				if (!isTasksWorkloadReportSubmited && date.after(threeDaysLeft)) {
					reminderId = 911;
					urgency = 3;
					subject = "Tasks Workload Report Submission";
					description = "You only have 3 days left to submit your Task Workload Report!";
					remindersSent.add(createReminder(reminderId, subject, date, deadline, 
							description, urgency, coop));
				}
			}
		}
		return remindersSent;
	}

	/**
	 * add or subtract days to date in java
	 * 
	 * @param date
	 * @param days
	 * @return date + days
	 * @author JulienLesaffre
	 */
	public Date addDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return new Date(c.getTimeInMillis());
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

		if (coop.getPDF() == null) {
			Set<PDF> pdfs = new HashSet<>();
			pdfs.add(pdf);
			coop.setPDF(pdfs);
		} else {
			Set<PDF> pdfs = coop.getPDF();
			pdfs.add(pdf);
			coop.setPDF(pdfs);
		}

		PDFRepository.save(pdf);
		coopRepository.save(coop);
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

		if (admin != null) {
			if (admin.getStudent() == null) {
				Set<Student> students = new HashSet<>();
				students.add(student);
				admin.setStudent(students);
			} else {
				Set<Student> students = admin.getStudent();
				students.add(student);
				admin.setStudent(students);
			}
		}

		studentRepository.save(student);
		if (admin != null) {
			administratorRepository.save(admin);
		}
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
	public List<Student> getAllStudentsWithFormError(String term) {

		term = term.toLowerCase();

		List<Student> students = new ArrayList<>();
		for (Student s : getAllStudents()) {
			for (Coop c : s.getCoop()) {
				if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
					if (c.getForm() == null) {
						students.add(s);
					} else if (countForms(c) < 4) {
						System.out.println(countForms(c));
						students.add(s);
					}
				}
			}
		}

		return students;
	}

	public int countForms(Coop c) {
		Set<Form> forms = c.getForm();
		int count = 0;
		boolean aFound = false, cFound = false, sFound = false, tFound = false;

		for (Form f : forms) {
			if (f.getClass().getName().equalsIgnoreCase("ca.mcgill.ecse321.cooperator.model.AcceptanceForm")
					&& !aFound) {
				count++;
				aFound = true;
			} else if (f.getClass().getName().equalsIgnoreCase("ca.mcgill.ecse321.cooperator.model.CoopEvaluation")
					&& !cFound) {
				count++;
				cFound = true;
			} else if (f.getClass().getName().equalsIgnoreCase("ca.mcgill.ecse321.cooperator.model.StudentEvaluation")
					&& !sFound) {
				count++;
				sFound = true;
			} else if (f.getClass().getName().equalsIgnoreCase("ca.mcgill.ecse321.cooperator.model.TasksWorkloadReport")
					&& !tFound) {
				count++;
				tFound = true;
			}
		}

		if (count > 4) {
			throw new IllegalArgumentException();
		}

		return count;
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public String getTerm(Semester semester, Date startDate, Date endDate) {
		String year = null;
		if (startDate.getYear() == endDate.getYear()) {
			year = Integer.toString(startDate.getYear() + 1900);
		} else if (startDate.getYear() == endDate.getYear() - 1) {
			if (semester == Semester.Fall) {
				year = Integer.toString(startDate.getYear() + 1900);
			} else if (semester == Semester.Winter) {
				year = Integer.toString(endDate.getYear() + 1900);
			} else {
				year = Integer.toString(startDate.getYear() + 1900);
			}
		}

		switch (semester) {
		case Winter:
			return "winter " + year;
		case Fall:
			return "fall " + year;
		case Summer:
			return "summer " + year;
		}

		return "No Term Found";
	}

	@SuppressWarnings("null")
	@Transactional
	public double[] getSemesterStatistics(Semester semester, int year) {
		// num students doing coop term this semester, avg num of coops completed per
		// active students, avg coop completion (files submitted) for ongoing coops this
		// semester

		List<Student> students = getAllStudents();

		double numActiveStudents = 0;
		double numCompletedCoops = 0;
		double numSubmittedForms = 0;
		List<Coop> ongoingCoops = null;

		for (Student student : students) {

			boolean isActive = false;
			double tempNumCompletedCoops = 0;
			double tempNumSubmittedForms = 0;
			Coop ongoingCoop = null;

			Set<Coop> coops = student.getCoop();
			if (coops.isEmpty())
				break;

			for (Coop coop : coops) {
				if (coopRepository.isInSemester(coop, semester, year)) {
					isActive = true;
					ongoingCoop = coop;
					tempNumSubmittedForms = coop.getForm().size();
				} else if (coop.getForm().size() >= 4) {
					tempNumCompletedCoops++;
				}
			}

			if (isActive) {
				numActiveStudents++;
				numCompletedCoops += tempNumCompletedCoops;
				numSubmittedForms += tempNumSubmittedForms;
				ongoingCoops.add(ongoingCoop);
			}
		}
		double[] stats = { numActiveStudents, (numCompletedCoops / numActiveStudents),
				(numSubmittedForms / numActiveStudents) };
		return stats;
	}
	
	// List all forms for a given student
	@Transactional
	public Set<Form> getFormsFromStudent(int userId, Semester semester, int year) {
		
		Student student = getStudent(userId);
		Set<Coop> coops = student.getCoop();
		Set<Form> forms = null;

		for (Coop coop : coops) {
			if (coopRepository.isInSemester(coop, semester, year)) {
				forms = coop.getForm();
			}
		}
		return forms;
	}
	
	// List all forms for a given employer
	@Transactional
	public Set<Form> getFormsFromEmployer(int userId, Semester semester, int year) {
		
		Employer employer = getEmployer(userId);
		Set<Coop> coops = employer.getCoop();
		Set<Form> forms = null;

		for (Coop coop : coops) {
			if (coopRepository.isInSemester(coop, semester, year)) {
				forms = coop.getForm();
			}
		}
		return forms;
	}
	
	// Edit acceptance form
	@Transactional
	public void editAcceptanceForm(int formId, String attribute, Object value) {
		// attribute: attribute to be edited, value: value for the new attribute
		AcceptanceForm acceptanceForm = (AcceptanceForm) formRepository.findFormByFormId(formId);
		
		switch(attribute.toLowerCase()) {
		case "formid" :
			acceptanceForm.setFormId((int) value);
			break;
		case "submissiondate":
			acceptanceForm.setSubmissionDate((Date) value);
			break;
		}
	}
	
	// Edit coop evaluation
	@Transactional
	public void editCoopEvaluation(int formId, String attribute, Object value) {
		CoopEvaluation coopEvaluation = (CoopEvaluation) formRepository.findFormByFormId(formId);
		
		switch(attribute.toLowerCase()) {
		case "formid" :
			coopEvaluation.setFormId((int) value);
			break;
		case "submissiondate":
			coopEvaluation.setSubmissionDate((Date) value);
			break;
		case "workexperience":
			coopEvaluation.setWorkExperience(value.toString());
			break;
		case "employerevaluation":
			coopEvaluation.setEmployerEvaluation((int) value);
			break;
		case "softwaretechnologies":
			coopEvaluation.setSoftwareTechnologies(value.toString());
			break;
		case "usefulcourses":
			coopEvaluation.setUsefulCourses(value.toString());
			break;
		}
	}
	
	// Edit student evaluation
	@Transactional
	public void editStudentEvaluation(int formId, String attribute, Object value) {
		StudentEvaluation studentEvaluation = (StudentEvaluation) formRepository.findFormByFormId(formId);
		
		switch(attribute.toLowerCase()) {
		case "formid" :
			studentEvaluation.setFormId((int) value);
			break;
		case "submissiondate":
			studentEvaluation.setSubmissionDate((Date) value);
			break;
		case "studentworkexperience":
			studentEvaluation.setStudentWorkExperience(value.toString());
			break;
		case "studentperformance":
			studentEvaluation.setStudentPerformance((int) value);
			break;
		}
	}
	
	// Edit tasks workload report
	@Transactional
	public void editTasksWorkloadReport(int formId, String attribute, Object value) {
		TasksWorkloadReport tasksWorkloadReport = (TasksWorkloadReport) formRepository.findFormByFormId(formId);
		
		switch(attribute.toLowerCase()) {
		case "formid" :
			tasksWorkloadReport.setFormId((int) value);
			break;
		case "submissiondate":
			tasksWorkloadReport.setSubmissionDate((Date) value);
			break;
		case "tasks":
			tasksWorkloadReport.setTasks(value.toString());
			break;
		case "hoursperweek":
			tasksWorkloadReport.setHoursPerWeek((int) value);
			break;
		case "wage":
			tasksWorkloadReport.setWage((int) value);
			break;
		case "training":
			tasksWorkloadReport.setTraining(value.toString());
			break;
		}
	}

}
