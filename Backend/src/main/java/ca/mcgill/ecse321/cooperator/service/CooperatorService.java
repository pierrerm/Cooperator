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

	/**
	 * Create a Coop and save it to the database table
	 * <p>
	 * In this method we get all the necessary information to create a coop. We make
	 * sure the inputs are valid (Employer/Student cannot be null, Dates cannot be
	 * null etc.). If the inputs pass all the tests then we create a new Coop
	 * object, and set its attributes to the desired inputs. Finally we update the
	 * student coops attribute and employers coops, and save all the objects to the
	 * database.
	 * </p>
	 * 
	 * @param coopId               The coops unique identifier
	 * @param employerConfirmation If the employer has confirmed this coop
	 * @param endDate              The coops end date
	 * @param jobDescription       The description of the job linked to this coop
	 * @param jobId                The id of the job from myFuture
	 * @param location             Where the coop is located
	 * @param needWorkPermit       If the student requires a work permit to work in
	 *                             this country
	 * @param semester             Which semester the coop is happening
	 * @param startDate            The coops start date
	 * @param student              The student object taking the coop
	 * @param employer             The employer object who is hiring the student
	 * @return Coop object with input attributes
	 */
	@Transactional
	public Coop createCoop(int coopId, boolean employerConfirmation, Date endDate, String jobDescription, int jobId,
			String location, boolean needWorkPermit, Semester semester, Date startDate, Student student,
			Employer employer) {

		// Check the inputs are valid
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

		// Create the new coop and update attributes
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

		// Update student/employer attributes to reflect new coop
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

		// Add objects to the database
		coopRepository.save(coop);
		studentRepository.save(student);
		employerRepository.save(employer);
		return coop;
	}

	/**
	 * Find a Coop in the database given a coopId
	 * 
	 * @param coopId The coopId of the coop you want to retrieve from the database
	 * @return A single coop with the desired coopId
	 */
	@Transactional
	public Coop getCoop(int coopId) {
		Coop coop = coopRepository.findCoopByCoopId(coopId);
		return coop;
	}

	/**
	 * Get all coops stored in the database
	 * 
	 * @return A list of coops
	 */
	@Transactional
	public List<Coop> getAllCoops() {
		return toList(coopRepository.findAll());
	}

	/**
	 * Create an Acceptance Form and save it to the database
	 * <p>
	 * In this method we get all the necessary information to create an acceptance
	 * form. We make sure the inputs are valid (Coop cannot be null). If the inputs
	 * pass all the tests then we create a new Acceptance Form object, and set its
	 * attributes to the desired inputs. Finally we update the coops form attribute,
	 * and save all the objects to the database.
	 * </p>
	 * 
	 * @param formId         The forms unique identifier
	 * @param submissionDate The date this form was submitted online
	 * @param coop           The coop this form is linked to
	 * @return An acceptance form with the desired attributes
	 */
	@Transactional
	public AcceptanceForm createAcceptanceForm(int formId, Date submissionDate, Coop coop) {

		// Make sure the inputs are valid
		if (coop == null) {
			throw new IllegalArgumentException("Coop cannot be null! ");
		}

		// Create an Acceptance form object and set its attributes
		AcceptanceForm acceptanceForm = new AcceptanceForm();
		acceptanceForm.setFormId(formId);
		acceptanceForm.setSubmissionDate(submissionDate);
		acceptanceForm.setCoop(coop);

		// Update the Coop object to reflect the new form
		if (coop.getForm() == null) {
			Set<Form> forms = new HashSet<Form>();
			forms.add(acceptanceForm);
			coop.setForm(forms);
		} else {
			Set<Form> forms = coop.getForm();
			forms.add(acceptanceForm);
			coop.setForm(forms);
		}

		// Add object to the database
		formRepository.save(acceptanceForm);
		return acceptanceForm;
	}

	/**
	 * Create a Coop Evaluation Form and save it to the database
	 * <p>
	 * In this method we get all the necessary information to create a coop
	 * evaluation form. We make sure the inputs are valid (Coop cannot be null). If
	 * the inputs pass all the tests then we create a new CoopEvaluation Form
	 * object, and set its attributes to the desired inputs. Finally we update the
	 * coops form attribute, and save all the objects to the database.
	 * </p>
	 * 
	 * @param formId               The forms unique identifier
	 * @param submissionDate       The date the form was submitted online
	 * @param workExperience       What kind of work experience was needed
	 * @param employerEvaluation   A grade on the employers performance
	 * @param softwareTechnologies Description of the software technologies used
	 * @param usefulCourses        Description of the courses which were useful for
	 *                             the coop
	 * @param coop                 The coop this form is linked to
	 * @return A CoopEvaluation object with desired attributes
	 */
	@Transactional
	public CoopEvaluation createCoopEvaluation(int formId, Date submissionDate, String workExperience,
			int employerEvaluation, String softwareTechnologies, String usefulCourses, Coop coop) {

		// Make sure the inputs are valid
		if (coop == null) {
			throw new IllegalArgumentException("Coop cannot be null! ");
		}

		// Create a CoopEvaluation object and set the attributes
		CoopEvaluation coopEvaluation = new CoopEvaluation();
		coopEvaluation.setFormId(formId);
		coopEvaluation.setSubmissionDate(submissionDate);
		coopEvaluation.setWorkExperience(workExperience);
		coopEvaluation.setEmployerEvaluation(employerEvaluation);
		coopEvaluation.setSoftwareTechnologies(softwareTechnologies);
		coopEvaluation.setUsefulCourses(usefulCourses);
		coopEvaluation.setCoop(coop);

		// Update the coop to reflect the new form
		if (coop.getForm() == null) {
			Set<Form> forms = new HashSet<Form>();
			forms.add(coopEvaluation);
			coop.setForm(forms);
		} else {
			Set<Form> forms = coop.getForm();
			forms.add(coopEvaluation);
			coop.setForm(forms);
		}

		// Add object to the database
		formRepository.save(coopEvaluation);
		return coopEvaluation;
	}

	/**
	 * Create a Student Evaluation Form and save it to the database
	 * <p>
	 * In this method we get all the necessary information to create a student
	 * evaluation form. We make sure the inputs are valid (Coop cannot be null). If
	 * the inputs pass all the tests then we create a new StudentEvaluation Form
	 * object, and set its attributes to the desired inputs. Finally we update the
	 * coops form attribute, and save all the objects to the database.
	 * </p>
	 * 
	 * @param formId                The forms unique identifier
	 * @param submissionDate        The date the form was submitted online
	 * @param studentWorkExperience The work experience of the student
	 * @param studentPerformance    A grade representing the students performance
	 * @param coop                  The coop the form is linked to
	 * @return A StudentEvaluation object with the desired attributes
	 */
	@Transactional
	public StudentEvaluation createStudentEvaluation(int formId, Date submissionDate, String studentWorkExperience,
			int studentPerformance, Coop coop) {

		// Make sure the inputs are valid
		if (coop == null) {
			throw new IllegalArgumentException("Coop cannot be null! ");
		}

		// Create a StudentEvaluation object and set the attributes
		StudentEvaluation studentEvaluation = new StudentEvaluation();
		studentEvaluation.setFormId(formId);
		studentEvaluation.setSubmissionDate(submissionDate);
		studentEvaluation.setStudentWorkExperience(studentWorkExperience);
		studentEvaluation.setStudentPerformance(studentPerformance);
		studentEvaluation.setCoop(coop);

		// Update the Coop to reflect the new form
		if (coop.getForm() == null) {
			Set<Form> forms = new HashSet<Form>();
			forms.add(studentEvaluation);
			coop.setForm(forms);
		} else {
			Set<Form> forms = coop.getForm();
			forms.add(studentEvaluation);
			coop.setForm(forms);
		}

		// Save the object to the database
		formRepository.save(studentEvaluation);
		return studentEvaluation;
	}

	/**
	 * Create a Tasks Workload Report Form and save it to the database
	 * <p>
	 * In this method we get all the necessary information to create a tasks
	 * workload report form. We make sure the inputs are valid (Coop cannot be
	 * null). If the inputs pass all the tests then we create a new
	 * TasksWorkloadReport Form object, and set its attributes to the desired
	 * inputs. Finally we update the coops form attribute, and save all the objects
	 * to the database.
	 * </p>
	 * 
	 * @param formId         The forms unique identifier
	 * @param submissionDate The date the form was submitted online
	 * @param tasks          The tasks completed by the student
	 * @param hoursPerWeek   The hours worked by the student
	 * @param wage           The wage of the student ($/hour)
	 * @param training       The training the student had to go through
	 * @param coop           The coop this form is linked to
	 * @return A TasksWorkloadReport object with the desired attributes
	 */
	@Transactional
	public TasksWorkloadReport createTasksWorkloadReport(int formId, Date submissionDate, String tasks,
			int hoursPerWeek, int wage, String training, Coop coop) {

		// Make sure the inputs are valid
		if (coop == null) {
			throw new IllegalArgumentException("Coop cannot be null! ");
		}

		// Create a TasksWorkloadReport object and set the attributes
		TasksWorkloadReport tasksWorkloadReport = new TasksWorkloadReport();
		tasksWorkloadReport.setFormId(formId);
		tasksWorkloadReport.setSubmissionDate(submissionDate);
		tasksWorkloadReport.setTasks(tasks);
		tasksWorkloadReport.setHoursPerWeek(hoursPerWeek);
		tasksWorkloadReport.setWage(wage);
		tasksWorkloadReport.setTraining(training);
		tasksWorkloadReport.setCoop(coop);

		// Update the Coop object to reflect the changes
		if (coop.getForm() == null) {
			Set<Form> forms = new HashSet<Form>();
			forms.add(tasksWorkloadReport);
			coop.setForm(forms);
		} else {
			Set<Form> forms = coop.getForm();
			forms.add(tasksWorkloadReport);
			coop.setForm(forms);
		}

		// Save the object to the database
		formRepository.save(tasksWorkloadReport);
		return tasksWorkloadReport;
	}

	/**
	 * Retrieve a form from the database with the given formId
	 * 
	 * @param formId The desired forms formId
	 * @return A form
	 */
	@Transactional
	public Form getForm(int formId) {
		Form form = formRepository.findFormByFormId(formId);
		return form;
	}

	/**
	 * Get all forms stored in the database
	 * 
	 * @return A list of forms stored in the database
	 */
	@Transactional
	public List<Form> getAllForms() {
		return toList(formRepository.findAll());
	}

	/**
	 * Create a Reminder and save it to the database
	 * <p>
	 * In this method we get all the necessary information to create a reminder. We
	 * make sure the inputs are valid (Coop cannot be null, Dates cannot be null,
	 * etc.). If the inputs pass all the tests then we create a new Reminder object,
	 * and set its attributes to the desired inputs. Finally we update the coops
	 * reminder attribute, and save all the objects to the database.
	 * </p>
	 * 
	 * @param subject     The subject of the reminder
	 * @param date        The date the reminder was created
	 * @param deadline    The deadline by which the form has to be submitted
	 * @param description A brief description of the reminder
	 * @param urgency     How urgent is the email (between 1 and 3)
	 * @param coop        The coop this reminder is linked to
	 * @return A reminder object with the desired attributes
	 */
	@Transactional
	public Reminder createReminder(String subject, Date date, Date deadline, String description, int urgency,
			Coop coop) {

		// Make sure the inputs are valid
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

		// Create a Reminder object and set the attributes
		Reminder reminder = new Reminder();
		reminder.setSubject(subject);
		reminder.setDate(date);
		reminder.setDeadLine(deadline);
		reminder.setDescription(description);
		reminder.setUrgency(urgency);
		reminder.setCoop(coop);

		// Update the coop to reflect the new Reminder
		if (coop.getReminder() == null) {
			Set<Reminder> reminders = new HashSet<>();
			reminders.add(reminder);
			coop.setReminder(reminders);
		} else {
			Set<Reminder> reminders = coop.getReminder();
			reminders.add(reminder);
			coop.setReminder(reminders);
		}

		// Save the objects to the database
		reminderRepository.save(reminder);
		coopRepository.save(coop);
		return reminder;
	}

	/**
	 * Get a specific reminder from the database using the reminderId
	 * 
	 * @param reminderId The reminderId of the desired reminder
	 * @return A reminder object
	 */
	@Transactional
	public Reminder getReminder(int reminderId) {
		Reminder reminder = reminderRepository.findReminderByReminderId(reminderId);
		return reminder;
	}

	/**
	 * A list of all the reminders stored in the database
	 * 
	 * @return A list of reminders
	 */
	@Transactional
	public List<Reminder> getAllReminders() {
		return toList(reminderRepository.findAll());
	}

	/**
	 * 
	 * @return
	 */
	@Transactional
	public List<Reminder> sendReminders() {
		int urgency;
		String subject, description;
		Date date, deadline;
		List<Student> problematicStudents = getAllStudents();
		List<Reminder> remindersSent = new ArrayList<Reminder>();
		if (problematicStudents.isEmpty()) {
			return remindersSent;
		}
		for (Student student : problematicStudents) {
			Set<Coop> coops = student.getCoop();

			if (!coops.isEmpty()) {
				for (Coop coop : coops) {
					Date startDate = coop.getStartDate();
					date = new Date(System.currentTimeMillis()); // return today's date
					deadline = addDays(startDate, 14);
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
						if (deadline.after(date)) {// if there is still time to send the report
							urgency = 3;
							subject = "Tasks Workload Report Submission";
							description = "You only have 3 days left to submit your Task Workload Report!";
							remindersSent.add(createReminder(subject, date, deadline, description, urgency, coop));
						} else { // today's date is after the deadline -> too late to submit
							urgency = 3;
							subject = "Tasks Workload Report Submission";
							description = "It is now too late to submit the Task Workload Report";
							remindersSent
									.add(createReminder(subject, date, addDays(date, 1), description, urgency, coop));

						}

					}
				}
			}
		}
		return remindersSent;
	}

	/**
	 * Add or subtract a desired number of days to a Date
	 * 
	 * @param date The initial date
	 * @param days The number of days to add or subtract
	 * @return A date object with the new date
	 */
	public Date addDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return new Date(c.getTimeInMillis());
	}

	/**
	 * Create a PDF and save it to the database
	 * <p>
	 * In this method we get all the necessary information to create a PDF. We make
	 * sure the inputs are valid (Coop cannot be null). If the inputs pass all the
	 * tests then we create a new PDF object, and set its attributes to the desired
	 * inputs. Finally we update the coops pdf attribute, and save all the objects
	 * to the database.
	 * </p>
	 * 
	 * @param docId          The documents unique identifier
	 * @param filePath       The link to the file online
	 * @param docType        What kind of document this is
	 * @param submissionDate The date the document was submitted online
	 * @param coop           The coop the PDF is linked to
	 * @return A PDF object with the desired attributes
	 */
	@Transactional
	public PDF createPDF(int docId, String filePath, DocumentType docType, Date submissionDate, Coop coop) {

		// Make sure the inputs are valid
		if (coop == null) {
			throw new IllegalArgumentException("Coop cannot be null! ");
		}

		// Create a new PDF object and update the attibutes
		PDF pdf = new PDF();
		pdf.setDocId(docId);
		pdf.setFilePath(filePath);
		pdf.setDocumentType(docType);
		pdf.setSubmissionDate(submissionDate);
		pdf.setCoop(coop);

		// Update the coop to reflect the new PDF
		if (coop.getPDF() == null) {
			Set<PDF> pdfs = new HashSet<>();
			pdfs.add(pdf);
			coop.setPDF(pdfs);
		} else {
			Set<PDF> pdfs = coop.getPDF();
			pdfs.add(pdf);
			coop.setPDF(pdfs);
		}

		// Save the objects to the database
		PDFRepository.save(pdf);
		coopRepository.save(coop);
		return pdf;
	}

	/**
	 * Get a PDF with a given docId
	 * 
	 * @param docId The id of the desired PDF
	 * @return A PDF object
	 */
	@Transactional
	public PDF getPDF(int docId) {
		PDF pdf = PDFRepository.findPDFByDocId(docId);
		return pdf;
	}

	/**
	 * A list of all the PDFs in the database
	 * 
	 * @return A list of PDFs
	 */
	@Transactional
	public List<PDF> getAllPDFs() {
		return toList(PDFRepository.findAll());
	}

	/**
	 * Create a Student and save it to the database
	 * <p>
	 * In this method we get all the necessary information to create a student. We
	 * make sure the inputs are valid. If the inputs pass all the tests then we
	 * create a new Student object, and set its attributes to the desired inputs.
	 * Finally we update the administrators student attribute, and save all the
	 * objects to the database.
	 * </p>
	 * 
	 * @param userId       The students unique identifier
	 * @param phone        The students phone number
	 * @param email        The students email
	 * @param firstName    The students first name
	 * @param lastName     The students last name
	 * @param password     The students password
	 * @param faculty      The students faculty
	 * @param id           The students McGill id
	 * @param major        The students major
	 * @param minor        The students minor
	 * @param academicYear The students academic year (ex. U3)
	 * @param admin        The administrator linked to the student
	 * @return A student object with the desired attributes
	 */
	@Transactional
	public Student createStudent(int userId, long phone, String email, String firstName, String lastName,
			String password, Faculty faculty, int id, String major, String minor, String academicYear,
			Administrator admin) {

		// Make sure the inputs are valid
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

		// Create a new Student object and set the attributes
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

		// Update the administrator to reflect the new student
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

		// Save the objects to the database
		studentRepository.save(student);
		if (admin != null) {
			administratorRepository.save(admin);
		}
		return student;
	}

	/**
	 * Get a student with a given userId
	 * 
	 * @param userId The id of the desired student
	 * @return A student object
	 */
	@Transactional
	public Student getStudent(int userId) {
		Student student = studentRepository.findStudentByUserId(userId);
		return student;
	}

	/**
	 * A list of all the students in the database
	 * 
	 * @return A list of students
	 */
	@Transactional
	public List<Student> getAllStudents() {
		return toList(studentRepository.findAll());
	}

	/**
	 * Create an Employer and save it to the database
	 * <p>
	 * In this method we get all the necessary information to create a Employer . We
	 * make sure the inputs are valid. If the inputs pass all the tests then we
	 * create a new Employer object, and set its attributes to the desired inputs.
	 * Finally we save the new employer to the database.
	 * </p>
	 * 
	 * @param userId    The employers unique identifier
	 * @param phone     The employers phone number
	 * @param firstName The employers first name
	 * @param lastName  The employers last name
	 * @param email     The employers email
	 * @param password  The employers password
	 * @param position  The employers position within the company
	 * @param company   The company the employer works at
	 * @param location  The location of the company
	 * @return An Employer object with the desired attributes
	 */
	@Transactional
	public Employer createEmployer(int userId, long phone, String firstName, String lastName, String email,
			String password, String position, String company, String location) {

		// Make sure the inputs are valid
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

		// Create a new Employer object and set the attributes
		Employer employer = new Employer();
		employer.setUserId(userId);
		employer.setFirstName(firstName);
		employer.setLastName(lastName);
		employer.setEmail(email);
		employer.setPassword(password);
		employer.setPhone(phone);
		employer.setCompany(company);
		employer.setLocation(location);
		employer.setPosition(position);

		// Save the employer to the database
		employerRepository.save(employer);
		return employer;
	}

	/**
	 * Get an employer from the database with the given id
	 * 
	 * @param userId The id of the desired employer
	 * @return An employer object
	 */
	@Transactional
	public Employer getEmployer(int userId) {
		Employer employer = employerRepository.findEmployerByUserId(userId);
		return employer;
	}

	/**
	 * Get all the employers stored in the database
	 * 
	 * @return A list of Employers
	 */
	@Transactional
	public List<Employer> getAllEmployers() {
		return toList(employerRepository.findAll());
	}

	/**
	 * Create an Administrator and save it to the database
	 * <p>
	 * In this method we get all the necessary information to create a
	 * Administrator. We make sure the inputs are valid. If the inputs pass all the
	 * tests then we create a new Administrator object, and set its attributes to
	 * the desired inputs. Finally we update save the new Administrator to the
	 * database.
	 * </p>
	 * 
	 * @param phone     The administrators phone number
	 * @param email     The administrators email
	 * @param firstName The administrators first name
	 * @param lastName  the administrators last name
	 * @param password  The administrators password
	 * @param faculty   The administrators faculty
	 * @param id        The administrators McGill ID
	 * @return A Administrator object with the desired attributes
	 */
	@Transactional
	public Administrator createAdministrator(long phone, String email, String firstName, String lastName,
			String password, Faculty faculty, int id) {

		// Make sure the inputs are valid
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

		// Create a new administrator object and set the attributes
		Administrator administrator = new Administrator();
		administrator.setEmail(email);
		administrator.setFirstName(firstName);
		administrator.setLastName(lastName);
		administrator.setPassword(password);
		administrator.setPhone(phone);
		administrator.setFaculty(faculty);
		administrator.setId(id);

		// Save the object to the database
		administratorRepository.save(administrator);
		return administrator;
	}

	/**
	 * Get a administrator from the database with a given userId
	 * 
	 * @param userId The id of the desired administrator
	 * @return An administrator object
	 */
	@Transactional
	public Administrator getAdministrator(int userId) {
		Administrator administrator = administratorRepository.findAdministratorByUserId(userId);
		return administrator;
	}

	/**
	 * Get all the administrators stored in the database
	 * 
	 * @return A list of Administrators
	 */
	@Transactional
	public List<Administrator> getAllAdministrators() {
		return toList(administratorRepository.findAll());
	}

	/**
	 * Get all the students that have a coop which is missing a form from a given
	 * term
	 * <p>
	 * This method is used to retrieve all the students who have coops in the given
	 * term which are missing one or more forms. First it gets all the students from
	 * the database. For each student it gets its coops. For each coop it makes sure
	 * its during the desired term. If yes then it counts the number of forms. If it
	 * is inferior to 4 then the student is added to the list of return students
	 * </p>
	 * 
	 * @param term The academic term the coops have to be from
	 * @return A list of problematic students
	 */
	@Transactional
	public List<Student> getAllStudentsWithFormError(String term) {

		//Tur
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
	
	/**
	 * Get all the coops that are missing a form from a given term
	 * <p>
	 * This method is used to retrieve all the coops in the given term which are
	 * missing one or more forms. First it gets all the coops from the database.
	 * For each coop it makes sure it is during the desired term. If yes, then it
	 * counts the number of forms. If it is inferior to 4 the the coop is added to
	 * the list of returned coops.
	 * </p>
	 * 
	 * @param term The academic term the coops have to be from
	 * @return A list of problematic coops
	 */

	@Transactional
	public List<Coop> getAllProblematicCoop(String term) {

		term = term.toLowerCase();

		List<Coop> coops = new ArrayList<>();
		for (Coop c : getAllCoops()) {
			if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
				if (c.getForm() == null) {
					coops.add(c);
				} else if (countForms(c) < 4) {
					System.out.println(countForms(c));
					coops.add(c);
				}
			}
		}

		return coops;
	}
	
	/**
	 * Get the number of forms for a given coop
	 * <p>
	 * This method is used to retrieve the number of forms of different type for a
	 * given coop. First it gets all the forms from the given coop. Then it counts
	 * the number of forms, ensuring only to count forms of the same type once. If
	 * there are 2 acceptance forms for the given coop for example, only one will
	 * be counted. Once all forms have been counted, the number is returned as an
	 * integer.
	 * </p>
	 * 
	 * @param c The desired coop
	 * @return An integer of the number of forms
	 */

	// US2-5 - Get forms submitted for coop
	public int countForms(Coop c) {
		Set<Form> forms = c.getForm();
		int count = 0;
		boolean aFound = false, cFound = false, sFound = false, tFound = false;

		if (!forms.isEmpty()) {
			for (Form f : forms) {
				if (f.getClass().getName().equalsIgnoreCase("ca.mcgill.ecse321.cooperator.model.AcceptanceForm")
						&& !aFound) {
					count++;
					aFound = true;
				} else if (f.getClass().getName().equalsIgnoreCase("ca.mcgill.ecse321.cooperator.model.CoopEvaluation")
						&& !cFound) {
					count++;
					cFound = true;
				} else if (f.getClass().getName()
						.equalsIgnoreCase("ca.mcgill.ecse321.cooperator.model.StudentEvaluation") && !sFound) {
					count++;
					sFound = true;
				} else if (f.getClass().getName()
						.equalsIgnoreCase("ca.mcgill.ecse321.cooperator.model.TasksWorkloadReport") && !tFound) {
					count++;
					tFound = true;
				}
			}
		}

		if (count > 4) {
			throw new IllegalArgumentException();
		}

		return count;
	}
	
	/**
	 * Get the corresponding academic term of a semester and dates
	 * <p>
	 * This method is used to retrieve the corresponding academic term of a semester,
	 * start date and end date. It is most often used to get the academic term of a
	 * coop, using its semester and date attributes. First it determines the year of
	 * the term by comparing the start date and end date. If they are not in the same
	 * year, it uses the semester to determine which date to use. It the constructs
	 * and returns the string value of the academic term, composed of the semester
	 * and year.
	 * </p>
	 * 
	 * @param semester The semester from which the term is to be extracted
	 * @param startDate The start date from which the term is to be extracted
	 * @param endDate The end date from which the term is to be extracted
	 * @return
	 */

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
			return "winter" + year;
		case Fall:
			return "fall" + year;
		case Summer:
			return "summer" + year;
		}

		return "No Term Found";
	}
	
	@Transactional
	public boolean isPriorToTerm(String term, Semester semester, Date startDate, Date endDate) {
		String coopTerm = getTerm(semester, startDate, endDate);
		int i;
		char c1 = 'z', c2 = 'z', c3 = 'z', c4 = 'z';
		term = term.toLowerCase();
		coopTerm = coopTerm.toLowerCase();
		StringBuilder s1 = new StringBuilder();
		StringBuilder s2 = new StringBuilder();
		for (i = 0; i < term.length(); i++) {
			c1 = term.charAt(i);
			if (c1 >= 'a' && c1 <= 'z')
				break;
		}
		for (i = 0; i < coopTerm.length(); i++) {
			c2 = coopTerm.charAt(i);
			if (c2 >= 'a' && c2 <= 'z')
				break;
		}
		for (i = 0; i < term.length(); i++) {
			c3 = term.charAt(i);
			if (c3 >= '0' && c3 <= '9')
				s1.append(c3);
		}
		for (i = 0; i < coopTerm.length(); i++) {
			c4 = coopTerm.charAt(i);
			if (c4 >= '0' && c4 <= '9')
				s2.append(c4);
		}
		int limitYear = Integer.parseInt(s1.toString());
		int coopYear = Integer.parseInt(s2.toString());

		if (limitYear > coopYear)
			return true;
		else if (limitYear == coopYear) {
			if (c1 == 'f')
				return !(c2 == 'f');
			if (c1 == 's')
				return (c2 == 'w');
			else
				return false;
		} else
			return false;
//		String limitSemester;
//		int limitYear, i, year = Integer.MAX_VALUE;
//
//		for(i = 0; i < term.length(); i++) {
//			char c = term.charAt(i);
//			if (c >= '0'&& c <= '9') break;
//		}
//		
//		limitSemester = term.substring(0, i-1).replaceAll("\\s","").toLowerCase();
//		limitYear = Integer.parseInt(term.substring(i).replaceAll("\\s","").toLowerCase());
//		
//		Calendar calendarStart = new GregorianCalendar();
//		calendarStart.setTime(startDate);
//		int yearStart = calendarStart.get(Calendar.YEAR);
//
//		Calendar calendarEnd = new GregorianCalendar();
//		calendarEnd.setTime(endDate);
//		int yearEnd = calendarEnd.get(Calendar.YEAR);
//		
//		if (yearStart == yearEnd) {
//			year = yearStart;
//		} else if (yearStart == yearEnd - 1) {
//			if (semester == Semester.Fall) {
//				year = yearStart;
//			} else if (semester == Semester.Winter) {
//				year = yearEnd;
//			} else {
//				year = yearStart;
//			}
//		}
//		
//		if (year < limitYear) return true;
//		else if (year == limitYear){
//			switch(limitSemester.charAt(0)) {
//			case 'w': return false;
//			case 's': if (semester == Semester.Winter) return true;
//				      else return false;
//			case 'f': if (semester == Semester.Fall) return false;
//					  else return true;
//			default: return false;
//			}
//		}
//		return false;
	}

	/**
	 * Get the general statistics of the coop program for a given term
	 * <p>
	 * This method is used to retrieve the general statistics of the coop program
	 * for a given term. It constructs four distinct statistics: the number of coops
	 * that are taking place during the given semester, the ratio of coops taking place
	 * during the given semester that have been completed, the average number of forms
	 * per coop taking place during the given semester and the number of problematic 
	 * students for the given semester. It returns these statistics as an array of
	 * doubles, to take into account possible decimals for the completion index and
	 * average number of forms.
	 * </p>
	 * 
	 * @param term The academic term for which the statistics have to be constructed
	 * @return A double array with length 4, each corresponding to a statistic (in order mentioned above)
	 */
	
	@Transactional
	public double[] getSemesterStatistics(String term) {
		double[] stats = { 0, 0, 0, 0 };
		stats[0] = getAllActiveCoops(term).size();
		if (stats[0] != 0)
			stats[1] = (double) getAllCompletedActiveCoops(term).size() / (double) stats[0];
		double forms = 0;
		for (Coop c : getAllActiveCoops(term)) {
			forms += (double) countForms(c);
		}
		if (stats[0] != 0)
			stats[2] = forms / (double) stats[0];
		stats[3] = getAllStudentsWithFormError(term).size();
		return stats;
	}
	
	/**
	 * Get the form statistics of the coop program for a given term
	 * <p>
	 * This method is used to retrieve the form statistics of the coop program
	 * for a given term. It constructs five distinct statistics: the number of active
	 * coops that have no forms, the number of active coops that have one form, the 
	 * number of active coops that have two forms, the number of active coops that 
	 * have three forms, the number of active coops that have all four forms (completed
	 * coops). It returns these statistics as an array of integers.
	 * </p>
	 * 
	 * @param term The academic term for which the statistics have to be constructed
	 * @return An int array with length 5, each corresponding to a statistic (in order mentioned above)
	 */

	@Transactional
	public int[] getFormStatistics(String term) {
		int[] stats = { 0, 0, 0, 0, 0 };
		stats[0] = getActiveCoopsWithNoForms(term).size();
		stats[1] = getActiveCoopsWithOneForm(term).size();
		stats[2] = getActiveCoopsWithTwoForms(term).size();
		stats[3] = getActiveCoopsWithThreeForms(term).size();
		stats[4] = getAllCompletedActiveCoops(term).size();
		return stats;
	}

	/**
	 * /**
	 * Get the form type statistics of the coop program for a given term
	 * <p>
	 * This method is used to retrieve the form type statistics of the coop program
	 * for a given term. It constructs four distinct statistics: the number of active
	 * coops that have acceptance forms, the number of active coops that have coop
	 * evaluation forms, the number of active coops that have student evaluation forms,
	 * the number of active coops that have task & workload report forms. It returns 
	 * these statistics as an array of integers.
	 * </p>
	 * 
	 * @param term The academic term for which the statistics have to be constructed
	 * @return An int array with length 4, each corresponding to a statistic (in order mentioned above)
	 */
	
	@Transactional
	public int[] getFormTypeStatistics(String term) {
		int[] stats = { 0, 0, 0, 0 };
		stats[0] = getActiveCoopsWithAForms(term).size();
		stats[1] = getActiveCoopsWithCEForms(term).size();
		stats[2] = getActiveCoopsWithSEForms(term).size();
		stats[3] = getActiveCoopsWithTWRForms(term).size();
		return stats;
	}

	/**
	 * Get all the coops that have an acceptance form from a given term
	 * <p>
	 * This method is used to retrieve all the coops in the given term which have
	 * an acceptance form. First it gets all the coops from the database. For each
	 * coop it makes sure it is during the desired term. If yes, then it gets all
	 * forms from the coop. It then checks the types of the forms. If one of them
	 * is an acceptance form then the coop is added to the list of returned coops.
	 * </p>
	 * 
	 * @param term The academic term the coops have to be from
	 * @return A list of coops that have acceptance forms
	 */
	
	@Transactional
	public List<Coop> getActiveCoopsWithAForms(String term) {
		term = term.toLowerCase();
		List<Coop> coopsWithAForms = new ArrayList<Coop>();
		for (Coop c : getAllCoops()) {
			if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
				for (Form f : c.getForm()) {
					if (f.getClass().getName().equalsIgnoreCase("ca.mcgill.ecse321.cooperator.model.AcceptanceForm")) {
						coopsWithAForms.add(c);
						break;
					}
				}
			}
		}
		return coopsWithAForms;
	}
	
	/**
	 * Get all the coops that have a coop evaluation form from a given term
	 * <p>
	 * This method is used to retrieve all the coops in the given term which have
	 * a coop evaluation form. First it gets all the coops from the database. For
	 * each coop it makes sure it is during the desired term. If yes, then it gets
	 * all forms from the coop. It then checks the types of the forms. If one of them
	 * is a coop evaluation form then the coop is added to the list of returned coops.
	 * </p>
	 * 
	 * @param term The academic term the coops have to be from
	 * @return A list of coops that have coop evaluation forms
	 */

	@Transactional
	public List<Coop> getActiveCoopsWithCEForms(String term) {
		term = term.toLowerCase();
		List<Coop> coopsWithCEForms = new ArrayList<Coop>();
		for (Coop c : getAllCoops()) {
			if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
				for (Form f : c.getForm()) {
					if (f.getClass().getName().equalsIgnoreCase("ca.mcgill.ecse321.cooperator.model.CoopEvaluation")) {
						coopsWithCEForms.add(c);
						break;
					}
				}
			}
		}
		return coopsWithCEForms;
	}
	
	/**
	 * Get all the coops that have a student evaluation form from a given term
	 * <p>
	 * This method is used to retrieve all the coops in the given term which have
	 * a student evaluation form. First it gets all the coops from the database. For
	 * each coop it makes sure it is during the desired term. If yes, then it gets
	 * all forms from the coop. It then checks the types of the forms. If one of them
	 * is a student evaluation form then the coop is added to the list of returned coops.
	 * </p>
	 * 
	 * @param term The academic term the coops have to be from
	 * @return A list of coops that have student evaluation forms
	 */

	@Transactional
	public List<Coop> getActiveCoopsWithSEForms(String term) {
		term = term.toLowerCase();
		List<Coop> coopsWithSEForms = new ArrayList<Coop>();
		for (Coop c : getAllCoops()) {
			if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
				for (Form f : c.getForm()) {
					if (f.getClass().getName()
							.equalsIgnoreCase("ca.mcgill.ecse321.cooperator.model.StudentEvaluation")) {
						coopsWithSEForms.add(c);
						break;
					}
				}
			}
		}
		return coopsWithSEForms;
	}

	/**
	 * Get all the coops that have a task & workload report form from a given term
	 * <p>
	 * This method is used to retrieve all the coops in the given term which have
	 * a task & workload report form. First it gets all the coops from the database. For
	 * each coop it makes sure it is during the desired term. If yes, then it gets
	 * all forms from the coop. It then checks the types of the forms. If one of them
	 * is a task & workload report form then the coop is added to the list of returned coops.
	 * </p>
	 * 
	 * @param term The academic term the coops have to be from
	 * @return A list of coops that have task & workload report forms
	 */
	
	@Transactional
	public List<Coop> getActiveCoopsWithTWRForms(String term) {
		term = term.toLowerCase();
		List<Coop> coopsWithTWRForms = new ArrayList<Coop>();
		for (Coop c : getAllCoops()) {
			if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
				for (Form f : c.getForm()) {
					if (f.getClass().getName()
							.equalsIgnoreCase("ca.mcgill.ecse321.cooperator.model.TasksWorkloadReport")) {
						coopsWithTWRForms.add(c);
						break;
					}
				}
			}
		}
		return coopsWithTWRForms;
	}
	
	/**
	 * Get all the students that have a coop which has an acceptance form from a given term
	 * <p>
	 * This method is used to retrieve all the students in the given term which have a coop
	 * that has an acceptance form. First it gets all the students from the database. For each
	 * student it gets its coops. For each coop it makes sure it is during the desired
	 * term. If yes, then it gets all forms from the coop. It then checks the types of
	 * the forms. If one of them is an acceptance form then the student is added to the list
	 * of returned students.
	 * </p>
	 * 
	 * @param term The academic term the coops have to be from
	 * @return A list of students that have a coop which has an acceptance form
	 */

	@Transactional
	public List<Student> getActiveStudentsWithAForms(String term) {
		term = term.toLowerCase();
		List<Student> students = new ArrayList<Student>();
		for (Student s : getAllStudents()) {
			for (Coop c : s.getCoop()) {
				if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
					for (Form f : c.getForm()) {
						if (f.getClass().getName()
								.equalsIgnoreCase("ca.mcgill.ecse321.cooperator.model.AcceptanceForm")) {
							students.add(s);
							break;
						}
					}
				}
			}
		}
		return students;
	}

	/**
	 * Get all the students that have a coop which has a coop evaluation form from a given term
	 * <p>
	 * This method is used to retrieve all the students in the given term which have a
	 * coop that has a coop evaluation form. First it gets all the students from the database.
	 * For each student it gets its coops. For each coop it makes sure it is during the desired
	 * term. If yes, then it gets all forms from the coop. It then checks the types of the 
	 * forms. If one of them is a coop evaluation form then the student is added to the list
	 * of returned students.
	 * </p>
	 * 
	 * @param term The academic term the coops have to be from
	 * @return A list of students that have a coop which has a coop evaluation form
	 */
	
	@Transactional
	public List<Student> getActiveStudentsWithCEForms(String term) {
		term = term.toLowerCase();
		List<Student> students = new ArrayList<Student>();
		for (Student s : getAllStudents()) {
			for (Coop c : s.getCoop()) {
				if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
					for (Form f : c.getForm()) {
						if (f.getClass().getName()
								.equalsIgnoreCase("ca.mcgill.ecse321.cooperator.model.CoopEvaluation")) {
							students.add(s);
							break;
						}
					}
				}
			}
		}
		return students;
	}
	
	/**
	 * Get all the students that have a coop which has a student evaluation form from a given term
	 * <p>
	 * This method is used to retrieve all the students in the given term which have a
	 * coop that has a student evaluation form. First it gets all the students from the database.
	 * For each student it gets its coops. For each coop it makes sure it is during the desired
	 * term. If yes, then it gets all forms from the coop. It then checks the types of the 
	 * forms. If one of them is a student evaluation form then the student is added to the list
	 * of returned students.
	 * </p>
	 * 
	 * @param term The academic term the coops have to be from
	 * @return A list of students that have a coop which has a student evaluation form
	 */

	@Transactional
	public List<Student> getActiveStudentsWithSEForms(String term) {
		term = term.toLowerCase();
		List<Student> students = new ArrayList<Student>();
		for (Student s : getAllStudents()) {
			for (Coop c : s.getCoop()) {
				if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
					for (Form f : c.getForm()) {
						if (f.getClass().getName()
								.equalsIgnoreCase("ca.mcgill.ecse321.cooperator.model.StudentEvaluation")) {
							students.add(s);
							break;
						}
					}
				}
			}
		}
		return students;
	}
	
	/**
	 * Get all the students that have a coop which has a task & workload report form from a given term
	 * <p>
	 * This method is used to retrieve all the students in the given term which have a
	 * coop that has a task & workload report form. First it gets all the students from the database.
	 * For each student it gets its coops. For each coop it makes sure it is during the desired
	 * term. If yes, then it gets all forms from the coop. It then checks the types of the 
	 * forms. If one of them is a task & workload report form then the student is added to the list
	 * of returned students.
	 * </p>
	 * 
	 * @param term The academic term the coops have to be from
	 * @return A list of students that have a coop which has a task & workload report form
	 */

	@Transactional
	public List<Student> getActiveStudentsWithTWRForms(String term) {
		term = term.toLowerCase();
		List<Student> students = new ArrayList<Student>();
		for (Student s : getAllStudents()) {
			for (Coop c : s.getCoop()) {
				if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
					for (Form f : c.getForm()) {
						if (f.getClass().getName()
								.equalsIgnoreCase("ca.mcgill.ecse321.cooperator.model.TasksWorkloadReport")) {
							students.add(s);
							break;
						}
					}
				}
			}
		}
		return students;
	}
	
	/**
	 * Get all the students that have a coop during a given term (active)
	 * <p>
	 * This method is used to retrieve all the students in the given term which have a
	 * coop during that term. First it gets all the students from the database. For each
	 * student it gets its coops. For each coop it makes sure it is during the desired
	 * term. If yes, then the student is added to the list of returned students.
	 * </p>
	 * 
	 * @param term The academic term the coops have to be from
	 * @return A list of students that have a coop during the given term
	 */

	// US2 - Get all active Students (currently enrolled in coop term)
	@Transactional
	public List<Student> getAllActiveStudents(String term) {
		term = term.toLowerCase();
		List<Student> activeStudents = new ArrayList<Student>();
		for (Student s : getAllStudents()) {
			for (Coop c : s.getCoop()) {
				if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
					activeStudents.add(s);
					break;
				}
			}
		}
		return activeStudents;
	}
	
	/**
	 * Get all the employers that have a coop during a given term (active)
	 * <p>
	 * This method is used to retrieve all the employers in the given term which have a
	 * coop during that term. First it gets all the employers from the database. For each
	 * employer it gets its coops. For each coop it makes sure it is during the desired
	 * term. If yes, then the employer is added to the list of returned employers.
	 * </p>
	 * 
	 * @param term The academic term the coops have to be from
	 * @return A list of employers that have a coop during the given term
	 */

	@Transactional
	public List<Employer> getAllActiveEmployers(String term) {
		term = term.toLowerCase();
		List<Employer> activeEmployers = new ArrayList<Employer>();
		for (Employer e : getAllEmployers()) {
			for (Coop c : e.getCoop()) {
				if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
					activeEmployers.add(e);
					break;
				}
			}
		}
		return activeEmployers;
	}
	
	/**
	 * Get all the coops that are during a given term (active)
	 * <p>
	 * This method is used to retrieve all the coops that are being taken during the 
	 * given term. First it gets all the coops from the database. For each coop it 
	 * makes sure it is during the desired term. If yes, then the coop is added
	 * to the list of returned coops.
	 * </p>
	 * 
	 * @param term The academic term the coops have to be from
	 * @return A list of coops that are during the given term
	 */

	// US2 - Get all active Coops (currently ongoing coop term)
	@Transactional
	public List<Coop> getAllActiveCoops(String term) {
		term = term.toLowerCase();
		List<Coop> activeCoops = new ArrayList<Coop>();
		for (Coop c : getAllCoops()) {
			if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
				activeCoops.add(c);
			}
		}
		return activeCoops;
	}

	/**
	 * Get all the coops that are during a given term and have all forms (completed and active)
	 * <p>
	 * This method is used to retrieve all the coops that are being taken during the 
	 * given term and have all four forms, meaning they are completed and active. 
	 * First it gets all the coops from the database. For each coop it makes sure it
	 * is during the desired term. If yes, it checks that the coop has four distinct
	 * forms. If yes, then the coop is added to the list of returned coops.
	 * </p>
	 * 
	 * @param term The academic term the coops have to be from
	 * @return A list of coops that are completed and during the given term
	 */
	
	// US2 - Get all Completed active Coops (completed coops for current term)
	@Transactional
	public List<Coop> getAllCompletedActiveCoops(String term) {
		term = term.toLowerCase();
		List<Coop> completedCoops = new ArrayList<Coop>();
		for (Coop c : getAllCoops()) {
			if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
				if (countForms(c) >= 4) {
					completedCoops.add(c);
				}
			}
		}
		return completedCoops;
	}

	/**
	 * Get all the coops that are during a given term and have no forms (incomplete and active)
	 * <p>
	 * This method is used to retrieve all the coops that are being taken during the 
	 * given term and have no forms, meaning they are incomplete and active. 
	 * First it gets all the coops from the database. For each coop it makes sure it
	 * is during the desired term. If yes, it checks that the coop has no forms.
	 * If yes, then the coop is added to the list of returned coops.
	 * </p>
	 * 
	 * @param term The academic term the coops have to be from
	 * @return A list of coops that have no forms and are during the given term
	 */
	
	@Transactional
	public List<Coop> getActiveCoopsWithNoForms(String term) {
		term = term.toLowerCase();
		List<Coop> coopsWithNoForms = new ArrayList<Coop>();
		for (Coop c : getAllCoops()) {
			if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
				if (countForms(c) == 0) {
					coopsWithNoForms.add(c);
				}
			}
		}
		return coopsWithNoForms;
	}
	
	/**
	 * Get all the coops that are during a given term and have one form (incomplete and active)
	 * <p>
	 * This method is used to retrieve all the coops that are being taken during the 
	 * given term and have one form, meaning they are incomplete and active. 
	 * First it gets all the coops from the database. For each coop it makes sure it
	 * is during the desired term. If yes, it checks that the coop has one forms.
	 * If yes, then the coop is added to the list of returned coops.
	 * </p>
	 * 
	 * @param term The academic term the coops have to be from
	 * @return A list of coops that have one form and are during the given term
	 */

	@Transactional
	public List<Coop> getActiveCoopsWithOneForm(String term) {
		term = term.toLowerCase();
		List<Coop> coopsWithOneForm = new ArrayList<Coop>();
		for (Coop c : getAllCoops()) {
			if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
				if (countForms(c) == 1) {
					coopsWithOneForm.add(c);
				}
			}
		}
		return coopsWithOneForm;
	}
	
	/**
	 * Get all the coops that are during a given term and have two forms (incomplete and active)
	 * <p>
	 * This method is used to retrieve all the coops that are being taken during the 
	 * given term and have two forms, meaning they are incomplete and active. 
	 * First it gets all the coops from the database. For each coop it makes sure it
	 * is during the desired term. If yes, it checks that the coop has two forms.
	 * If yes, then the coop is added to the list of returned coops.
	 * </p>
	 * 
	 * @param term The academic term the coops have to be from
	 * @return A list of coops that have two forms and are during the given term
	 */

	@Transactional
	public List<Coop> getActiveCoopsWithTwoForms(String term) {
		term = term.toLowerCase();
		List<Coop> coopsWithTwoForms = new ArrayList<Coop>();
		for (Coop c : getAllCoops()) {
			if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
				if (countForms(c) == 2) {
					coopsWithTwoForms.add(c);
				}
			}
		}
		return coopsWithTwoForms;
	}
	
	/**
	 * Get all the coops that are during a given term and have three forms (incomplete and active)
	 * <p>
	 * This method is used to retrieve all the coops that are being taken during the 
	 * given term and have three forms, meaning they are incomplete and active. 
	 * First it gets all the coops from the database. For each coop it makes sure it
	 * is during the desired term. If yes, it checks that the coop has three forms.
	 * If yes, then the coop is added to the list of returned coops.
	 * </p>
	 * 
	 * @param term The academic term the coops have to be from
	 * @return A list of coops that have three forms and are during the given term
	 */

	@Transactional
	public List<Coop> getActiveCoopsWithThreeForms(String term) {
		term = term.toLowerCase();
		List<Coop> coopsWithThreeForms = new ArrayList<Coop>();
		for (Coop c : getAllCoops()) {
			if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
				if (countForms(c) == 3) {
					coopsWithThreeForms.add(c);
				}
			}
		}
		return coopsWithThreeForms;
	}
	
	/**
	 * 
	 * @param userId
	 * @param term
	 * @return
	 */

	// US2 - Get completed active coops for student (completed coops for current
	// term)
	@Transactional
	public List<Coop> getCompletedActiveCoops(int userId, String term) {
		term = term.toLowerCase();
		List<Coop> completedCoops = new ArrayList<Coop>();
		for (Student s : getAllStudents()) {
			if (s.getUserId() == userId) {
				for (Coop c : s.getCoop()) {
					if (term.equalsIgnoreCase(getTerm(c.getSemester(), c.getStartDate(), c.getEndDate()))) {
						if (countForms(c) >= 4) {
							completedCoops.add(c);
						}
					}
				}
			}
		}
		return completedCoops;
	}
	
	/**
	 * 
	 * @param term
	 * @return
	 */

	// US2 - Get all previously completed coops (completed coops prior to current
	// term)
	@Transactional
	public List<Coop> getAllPreviouslyCompletedCoops(String term) {
		term = term.toLowerCase();
		List<Coop> completedCoops = new ArrayList<Coop>();
		for (Student s : getAllStudents()) {
			for (Coop c : s.getCoop()) {
				if (isPriorToTerm(term, c.getSemester(), c.getStartDate(), c.getEndDate())) {
					if (countForms(c) == 4) {
						completedCoops.add(c);
					}
				}
			}
		}
		return completedCoops;
	}
	
	/**
	 * 
	 * @param userId
	 * @param term
	 * @return
	 */

	// US2 - Get all previously completed coops for student (completed coops prior
	// to current term)
	@Transactional
	public List<Coop> getPreviouslyCompletedCoops(int userId, String term) {
		term = term.toLowerCase();
		List<Coop> completedCoops = new ArrayList<Coop>();
		for (Student s : getAllStudents()) {
			if (s.getUserId() == userId) {
				for (Coop c : s.getCoop()) {
					if (isPriorToTerm(term, c.getSemester(), c.getStartDate(), c.getEndDate())) {
						if (countForms(c) == 4) {
							completedCoops.add(c);
						}
					}
				}
			}
		}
		return completedCoops;
	}
	
	/**
	 * 
	 * @param userId
	 * @param semester
	 * @param year
	 * @return
	 */

	// US1 - List all forms for a given student
	@Transactional
	public Set<Form> getFormsFromStudent(int userId, Semester semester, int year) {

		Set<Form> forms = new HashSet<Form>();
		Student student = getStudent(userId);
		Set<Coop> coops = student.getCoop();

		for (Coop coop : coops) {
			if (coopRepository.isInSemester(coop, semester, year)) {
				forms = coop.getForm();
			}
		}
		return forms;
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 */

	@Transactional // by user ID only
	public Set<Form> getFormsForStudent(int userId) {

		Set<Form> forms = new HashSet<Form>();
		Student student = getStudent(userId);
		Set<Coop> coops = student.getCoop();

		for (Coop coop : coops) {

			forms = coop.getForm();

		}
		return forms;
	}
	
	/**
	 * 
	 * @param userId
	 * @param semester
	 * @param year
	 * @return
	 */

	// US1 - List all forms for a given employer
	@Transactional
	public Set<Form> getFormsFromEmployer(int userId, Semester semester, int year) {

		Set<Form> forms = new HashSet<Form>();
		Employer employer = getEmployer(userId);
		Set<Coop> coops = employer.getCoop();

		for (Coop coop : coops) {
			if (coopRepository.isInSemester(coop, semester, year)) {
				forms = coop.getForm();
			}
		}
		return forms;
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 */

	@Transactional // by user ID only
	public Set<Form> getFormsForEmployer(int userId) {

		Set<Form> forms = new HashSet<Form>();
		Employer employer = getEmployer(userId);
		Set<Coop> coops = employer.getCoop();

		for (Coop coop : coops) {

			forms = coop.getForm();

		}
		return forms;
	}
	
	/**
	 * 
	 * @param formId
	 * @param attribute
	 * @param value
	 */

	// Edit acceptance form
	@Transactional
	public void editAcceptanceForm(int formId, String attribute, Object value) {
		// attribute: attribute to be edited, value: value for the new attribute
		AcceptanceForm acceptanceForm = (AcceptanceForm) formRepository.findFormByFormId(formId);

		switch (attribute.toLowerCase()) {
		case "submissiondate":
			acceptanceForm.setSubmissionDate((Date) value);
			break;
		}
	}
	
	/**
	 * 
	 * @param formId
	 * @param attribute
	 * @param value
	 */

	// Edit coop evaluation
	@Transactional
	public void editCoopEvaluation(int formId, String attribute, Object value) {
		CoopEvaluation coopEvaluation = (CoopEvaluation) formRepository.findFormByFormId(formId);

		switch (attribute.toLowerCase()) {
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
	
	/**
	 * 
	 * @param formId
	 * @param attribute
	 * @param value
	 */

	// Edit student evaluation
	@Transactional
	public void editStudentEvaluation(int formId, String attribute, Object value) {
		StudentEvaluation studentEvaluation = (StudentEvaluation) formRepository.findFormByFormId(formId);

		switch (attribute.toLowerCase()) {
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
	
	/**
	 * 
	 * @param formId
	 * @param attribute
	 * @param value
	 */

	// Edit tasks workload report
	@Transactional
	public void editTasksWorkloadReport(int formId, String attribute, Object value) {
		TasksWorkloadReport tasksWorkloadReport = (TasksWorkloadReport) formRepository.findFormByFormId(formId);

		switch (attribute.toLowerCase()) {
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

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
