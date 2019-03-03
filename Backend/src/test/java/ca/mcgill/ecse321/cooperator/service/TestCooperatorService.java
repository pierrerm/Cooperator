package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCooperatorService {

	@Autowired
	private CooperatorService service;
	@Autowired
	private CoopRepository coopRepository;
	@Autowired
	private FormRepository formRepository;
	@Autowired
	private ReminderRepository reminderRepository;
	@Autowired
	private PDFRepository pdfRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private AdministratorRepository administratorRepository;
	@Autowired
	private EmployerRepository employerRepository;

	@Before
	@After
	public void clearDatabase() {
		try {

			formRepository.deleteAll();
			reminderRepository.deleteAll();
			pdfRepository.deleteAll();
			coopRepository.deleteAll();
			studentRepository.deleteAll();
			administratorRepository.deleteAll();
			employerRepository.deleteAll();
		} catch (NullPointerException e) {

		}
	}

	@Test
	public void testCreateCoop() {
		assertEquals(0, service.getAllCoops().size());

		int coopId = 1;
		int jobId = 1;
		boolean employerConfirmation = true;
		Date endDate = null;
		String jobDescription = "Java";
		String location = "Montreal";
		boolean needWorkPermit = true;
		Semester semester = Semester.Fall;
		Date startDate = null;

		try {
			Employer employer = service.createEmployer(1, 1, "google@gmail.com", "Bob", "Bobby", "password", "Google",
					"Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123",
					Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password",
					Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			service.createCoop(coopId, employerConfirmation, endDate, jobDescription, jobId, location, needWorkPermit,
					semester, startDate, student, employer);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Coop> allCoops = service.getAllCoops();

		assertEquals(1, allCoops.size());
		assertEquals(coopId, allCoops.get(0).getCoopId());
	}

	@Test
	public void testCreatePDF() {
		assertEquals(0, service.getAllPDFs().size());

		int docId = 1;
		String filePath = "Heyo";
		DocumentType docType = DocumentType.CoopPlacementProof;
		Date submissionDate = null;

		int coopId = 1;
		int jobId = 1;
		boolean employerConfirmation = true;
		Date endDate = null;
		String jobDescription = "Java";
		String location = "Montreal";
		boolean needWorkPermit = true;
		Semester semester = Semester.Fall;
		Date startDate = null;

		try {
			Employer employer = service.createEmployer(1, 1, "google@gmail.com", "Bob", "Bobby", "password", "Google",
					"Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123",
					Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password",
					Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			Coop coop = service.createCoop(coopId, employerConfirmation, endDate, jobDescription, jobId, location,
					needWorkPermit, semester, startDate, student, employer);
			service.createPDF(docId, filePath, docType, submissionDate, coop);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<PDF> allDownloadableDocs = service.getAllPDFs();

		assertEquals(1, allDownloadableDocs.size());
		assertEquals(docId, allDownloadableDocs.get(0).getDocId());
	}

	@Test
	public void testCreateEmployer() {
		assertEquals(0, service.getAllEmployers().size());

		int userId = 1;
		String email = "rger";
		String firstName = "rg";
		String lastName = "greg";
		String password = "erg";
		String company = "rgre";
		String location = "erger";
		long phone = 1;
		String position = "rgerg";

		try {
			service.createEmployer(userId, phone, email, firstName, lastName, password, position, company, location);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Employer> allEmployers = service.getAllEmployers();

		assertEquals(1, allEmployers.size());
		assertEquals(userId, allEmployers.get(0).getUserId());
	}

	@Test
	public void testCreateAdministrator() {
		assertEquals(0, service.getAllAdministrators().size());

		int userId = 1;
		long phone = 1234;
		String email = "rer";
		String firstName = "greg";
		String lastName = "rg";
		String password = "rg";
		Faculty faculty = Faculty.Engineering;
		int id = 134;

		try {
			service.createAdministrator(userId, phone, email, firstName, lastName, password, faculty, id);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Administrator> allAdministrators = service.getAllAdministrators();

		assertEquals(1, allAdministrators.size());
		assertEquals(userId, allAdministrators.get(0).getUserId());
	}

	@Test
	public void testCreateStudent() {
		assertEquals(0, service.getAllStudents().size());

		int userIdS = 1;
		String emailS = "ewr";
		long phoneS = 3559;
		String firstNameS = "ewr";
		String lastNameS = "re";
		String passwordS = "r4r";
		Faculty facultyS = Faculty.Engineering;
		int idS = 1;
		String majorS = "4r4";
		String minorS = "";
		String yearS = "U3";

		try {
			service.createStudent(userIdS, phoneS, emailS, firstNameS, lastNameS, passwordS, facultyS, idS, majorS,
					minorS, yearS, null);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Student> allStudents = service.getAllStudents();

		assertEquals(1, allStudents.size());
		assertEquals(userIdS, allStudents.get(0).getUserId());
	}

	@Test
	public void testCreateStudentSimple() {
		assertEquals(0, service.getAllStudents().size());

		int userIdS = 1;
		String emailS = "fddf";
		long phoneS = 3559;
		String firstNameS = "dfs";
		String lastNameS = "sdf";
		String passwordS = "dsf";
		Faculty facultyS = Faculty.Engineering;
		int idS = 1;
		String majorS = "df";
		String minorS = "re";
		String yearS = "U3";

		try {
			service.createStudent(userIdS, phoneS, emailS, firstNameS, lastNameS, passwordS, facultyS, idS, majorS,
					minorS, yearS, null);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Student> allStudents = service.getAllStudents();

		assertEquals(1, allStudents.size());
		assertEquals(userIdS, allStudents.get(0).getUserId());
	}

	@Test
	public void testCreateAcceptanceForm() {
		assertEquals(0, service.getAllForms().size());

		int formId = 1;
		Date submissionDate = null;

		int coopId = 1;
		int jobId = 1;
		boolean employerConfirmation = true;
		Date endDate = null;
		String jobDescription = "Java";
		String location = "Montreal";
		boolean needWorkPermit = true;
		Semester semester = Semester.Fall;
		Date startDate = null;

		try {
			Employer employer = service.createEmployer(1, 1, "google@gmail.com", "Bob", "Bobby", "password", "Google",
					"Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123",
					Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password",
					Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			Coop coop = service.createCoop(coopId, employerConfirmation, endDate, jobDescription, jobId, location,
					needWorkPermit, semester, startDate, student, employer);
			service.createAcceptanceForm(formId, submissionDate, coop);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Form> allForms = service.getAllForms();

		assertEquals(1, allForms.size());
		assertEquals(formId, allForms.get(0).getFormId());
	}

	@Test
	public void testCreateCoopEvaluation() {
		assertEquals(0, service.getAllForms().size());

		String usefulCourses = "none";
		String softwareTechnologies = "dialogflow";
		int employerEvaluation = 9;
		String workExperience = "great";
		int formId = 3;
		Date submissionDate = null;

		int coopId = 1;
		int jobId = 1;
		boolean employerConfirmation = true;
		Date endDate = null;
		String jobDescription = "Java";
		String location = "Montreal";
		boolean needWorkPermit = true;
		Semester semester = Semester.Fall;
		Date startDate = null;

		try {
			Employer employer = service.createEmployer(1, 1, "google@gmail.com", "Bob", "Bobby", "password", "Google",
					"Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123",
					Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password",
					Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			Coop coop = service.createCoop(coopId, employerConfirmation, endDate, jobDescription, jobId, location,
					needWorkPermit, semester, startDate, student, employer);
			service.createCoopEvaluation(formId, submissionDate, workExperience, employerEvaluation,
					softwareTechnologies, usefulCourses, coop);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Form> allForms = service.getAllForms();

		assertEquals(1, allForms.size());
		assertEquals(formId, allForms.get(0).getFormId());
	}

	@Test
	public void testCreateStudentEvaluation() {
		assertEquals(0, service.getAllForms().size());

		int studentPerformance = 10;
		String studentWorkExperience = "amazing";
		int formId = 4;
		Date submissionDate = null;

		int coopId = 1;
		int jobId = 1;
		boolean employerConfirmation = true;
		Date endDate = null;
		String jobDescription = "Java";
		String location = "Montreal";
		boolean needWorkPermit = true;
		Semester semester = Semester.Fall;
		Date startDate = null;

		try {
			Employer employer = service.createEmployer(1, 1, "google@gmail.com", "Bob", "Bobby", "password", "Google",
					"Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123",
					Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password",
					Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			Coop coop = service.createCoop(coopId, employerConfirmation, endDate, jobDescription, jobId, location,
					needWorkPermit, semester, startDate, student, employer);
			service.createStudentEvaluation(formId, submissionDate, studentWorkExperience, studentPerformance, coop);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Form> allForms = service.getAllForms();

		assertEquals(1, allForms.size());
		assertEquals(formId, allForms.get(0).getFormId());
	}

	@Test
	public void testCreateTasksWorkloadReport() {
		assertEquals(0, service.getAllForms().size());

		String training = "";
		int wage = 400;
		int hoursPerWeek = 35;
		String tasks = "testing";
		int formId = 5;
		Date submissionDate = null;

		int coopId = 1;
		int jobId = 1;
		boolean employerConfirmation = true;
		Date endDate = null;
		String jobDescription = "Java";
		String location = "Montreal";
		boolean needWorkPermit = true;
		Semester semester = Semester.Fall;
		Date startDate = null;

		try {
			Employer employer = service.createEmployer(1, 1, "google@gmail.com", "Bob", "Bobby", "password", "Google",
					"Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123",
					Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password",
					Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			Coop coop = service.createCoop(coopId, employerConfirmation, endDate, jobDescription, jobId, location,
					needWorkPermit, semester, startDate, student, employer);
			service.createTasksWorkloadReport(formId, submissionDate, tasks, hoursPerWeek, wage, training, coop);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Form> allForms = service.getAllForms();

		assertEquals(1, allForms.size());
		assertEquals(formId, allForms.get(0).getFormId());
	}

	@Test
	public void testCreateReminder() {
		assertEquals(0, service.getAllReminders().size());

		String description = "";
		int urgency = 3;
		String subject = "testing";
		int reminderId = 1;
		Date date = null;
		Date deadline = null;

		int coopId = 1;
		int jobId = 1;
		boolean employerConfirmation = true;
		Date endDate = null;
		String jobDescription = "Java";
		String location = "Montreal";
		boolean needWorkPermit = true;
		Semester semester = Semester.Fall;
		Date startDate = null;

		try {
			Employer employer = service.createEmployer(1, 1, "google@gmail.com", "Bob", "Bobby", "password", "Google",
					"Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123",
					Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password",
					Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			Coop coop = service.createCoop(coopId, employerConfirmation, endDate, jobDescription, jobId, location,
					needWorkPermit, semester, startDate, student, employer);
			service.createReminder(reminderId, subject, date, deadline, description, urgency, coop);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Reminder> allReminders = service.getAllReminders();

		assertEquals(1, allReminders.size());
		assertEquals(reminderId, allReminders.get(0).getReminderId());
	}
	
	@Test
	public void testSendReminders() {
		assertEquals(0, service.getAllReminders().size());
		
		int coopId = 1;
		int jobId = 1;
		boolean employerConfirmation = true;

		String jobDescription = "Java";
		String location = "Montreal";
		boolean needWorkPermit = true;
		Semester semester = Semester.Fall;
		Date today = new Date(System.currentTimeMillis()); // return today's date
		Date startDate = service.addDays(today, 15); // today + 15 days -> need a reminder if no forms submitted
		Date endDate =  service.addDays(today, 100);
		
		try {
			Employer employer = service.createEmployer(1, 1, "google@gmail.com", "Bob", "Bobby", "password", "Google",
					"Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123",
					Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Ngolo", "Kanté", "password",
					Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			service.createCoop(coopId, employerConfirmation, endDate, jobDescription, jobId, location,
					needWorkPermit, semester, startDate, student, employer);
			service.sendReminders();
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		List<Reminder> allReminders = service.getAllReminders();
		List<Student> everyStudents = service.getAllStudents();
		assertEquals(everyStudents.size(), allReminders.size());
		
	}
	
//	@Test
//	public void testGetFormsFromStudent() {
//		assertEquals(0, service.getAllForms().size());
//
//		String training = "";
//		int wage = 400;
//		int hoursPerWeek = 35;
//		String tasks = "testing";
//		int formId = 5;
//		Date submissionDate = null;
//		int studentPerformance = 10;
//		String studentWorkExperience = "amazing";
//		String usefulCourses = "none";
//		String softwareTechnologies = "dialogflow";
//		int employerEvaluation = 9;
//		String workExperience = "great";
//
//		int coopId = 1;
//		int jobId = 1;
//		boolean employerConfirmation = true;
//		Date endDate = null;
//		String jobDescription = "Java";
//		String location = "Montreal";
//		boolean needWorkPermit = true;
//		Semester semester = Semester.Fall;
//		Date startDate = null;
//		
//		Set<Form> formFromStudent = null;
//
//		try {
//			Employer employer = service.createEmployer(1, 1, "google@gmail.com", "Bob", "Bobby", "password", "Google",
//					"Montreal", "HR");
//			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123",
//					Faculty.Engineering, 260147532);
//			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password",
//					Faculty.Engineering, 260148654, "Software", "", "U2", admin);
//			Coop coop = service.createCoop(coopId, employerConfirmation, endDate, jobDescription, jobId, location,
//					needWorkPermit, semester, startDate, student, employer);
//			service.createAcceptanceForm(formId, submissionDate, coop);
//			service.createCoopEvaluation(formId+1, submissionDate, workExperience, employerEvaluation,
//					softwareTechnologies, usefulCourses, coop);
//			service.createStudentEvaluation(formId+2, submissionDate, studentWorkExperience, studentPerformance, coop);
//			service.createTasksWorkloadReport(formId+3, submissionDate, tasks, hoursPerWeek, wage, training, coop);
//			formFromStudent = service.getFormsFromStudent(3, semester, 2018);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//
//		List<Form> allForms = service.getAllForms();
//		assertEquals(4, allForms.size());
//		assertEquals(4, formFromStudent.size());
//		//assertEquals(formId, allForms.get(0).getFormId());
//	}
	
	@Test
	public void testEditAcceptanceForm() {
		assertEquals(0, service.getAllForms().size());

		int formId = 6;
		Date submissionDate = null;
		
		int newFormId = 7;
		Date newSubmissionDate = null;

		try {
			Employer employer = service.createEmployer(1, 1, "google@gmail.com", "Bob", "Bobby", "password", "Google",
					"Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123",
					Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password",
					Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			Coop coop = service.createCoop(1, true, null, "Java", 1, "Montreal", true, Semester.Fall, null, student, 
					employer);
			service.createAcceptanceForm(newFormId, submissionDate, coop);
			//service.editAcceptanceForm(formId, "formId", newFormId);
			service.editAcceptanceForm(newFormId, "submissionDate", newSubmissionDate);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Form> allForms = service.getAllForms();
		AcceptanceForm form = (AcceptanceForm) service.getForm(newFormId);
		
		assertEquals(1, allForms.size());
		assertEquals(newFormId, form.getFormId());
		assertEquals(newSubmissionDate, form.getSubmissionDate());
	}
	
	@Test
	public void testEditCoopEvaluation() {
		assertEquals(0, service.getAllForms().size());

		int formId = 8;
		Date submissionDate = null;
		String workExperience = "great";
		int employerEvaluation = 9;
		String softwareTechnologies = "dialogflow";
		String usefulCourses = "none";
		
		int newFormId = 9;
		Date newSubmissionDate = null;
		String newWorkExperience = "not great";
		int newEmployerEvaluation = 4;
		String newSoftwareTechnologies = "springboot";
		String newUsefulCourses = "ECSE 321";

		try {
			Employer employer = service.createEmployer(1, 1, "google@gmail.com", "Bob", "Bobby", "password", "Google",
					"Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123",
					Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password",
					Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			Coop coop = service.createCoop(1, true, null, "Java", 1, "Montreal", true, Semester.Fall, null, student, 
					employer);
			service.createCoopEvaluation(newFormId, submissionDate, workExperience, employerEvaluation,
					softwareTechnologies, usefulCourses, coop);
			//service.editCoopEvaluation(formId, "FormId", newFormId);
			service.editCoopEvaluation(newFormId, "SubmissionDate", newSubmissionDate);
			service.editCoopEvaluation(newFormId, "WorkExperience", newWorkExperience);
			service.editCoopEvaluation(newFormId, "EmployerEvaluation", newEmployerEvaluation);
			service.editCoopEvaluation(newFormId, "SoftwareTechnologies", newSoftwareTechnologies);
			service.editCoopEvaluation(newFormId, "UsefulCourses", newUsefulCourses);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Form> allForms = service.getAllForms();
		CoopEvaluation form = (CoopEvaluation) service.getForm(newFormId);
		
		assertEquals(1, allForms.size());
		assertEquals(newFormId, form.getFormId());
		assertEquals(newSubmissionDate, form.getSubmissionDate());
		assertEquals(newWorkExperience, form.getWorkExperience());
		assertEquals(newEmployerEvaluation, form.getEmployerEvaluation());
		assertEquals(newSoftwareTechnologies, form.getSoftwareTechnologies());
		assertEquals(newUsefulCourses, form.getUsefulCourses());
	}

	@Test
	public void testEditStudentEvaluation() {
		assertEquals(0, service.getAllForms().size());

		int formId = 10;
		Date submissionDate = null;
		String studentWorkExperience = "amazing";
		int studentPerformance = 10;
		
		int newFormId = 11;
		Date newSubmissionDate = null;
		String newStudentWorkExperience = "great";
		int newStudentPerformance = 8;

		try {
			Employer employer = service.createEmployer(1, 1, "google@gmail.com", "Bob", "Bobby", "password", "Google",
					"Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123",
					Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password",
					Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			Coop coop = service.createCoop(1, true, null, "Java", 1, "Montreal", true, Semester.Fall, null, student, 
					employer);
			service.createStudentEvaluation(newFormId, submissionDate, studentWorkExperience, studentPerformance, coop);
			//service.editStudentEvaluation(formId, "FormId", newFormId);
			service.editStudentEvaluation(newFormId, "SubmissionDate", newSubmissionDate);
			service.editStudentEvaluation(newFormId, "StudentWorkExperience", newStudentWorkExperience);
			service.editStudentEvaluation(newFormId, "StudentPerformance", newStudentPerformance);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Form> allForms = service.getAllForms();
		StudentEvaluation form = (StudentEvaluation) service.getForm(newFormId);
		
		assertEquals(1, allForms.size());
		assertEquals(newFormId, form.getFormId());
		assertEquals(newSubmissionDate, form.getSubmissionDate());
		assertEquals(newStudentWorkExperience, form.getStudentWorkExperience());
		assertEquals(newStudentPerformance, form.getStudentPerformance());
	}

	@Test
	public void testEditTasksWorkloadReport() {
		assertEquals(0, service.getAllForms().size());

		int formId = 12;
		Date submissionDate = null;
		String tasks = "testing";
		int hoursPerWeek = 35;
		int wage = 400;
		String training = "";
		
		int newFormId = 13;
		Date newSubmissionDate = null;
		String newTasks = "debugging";
		int newHoursPerWeek = 340;
		int newWage = 420;
		String newTraining = "none";

		try {
			Employer employer = service.createEmployer(1, 1, "google@gmail.com", "Bob", "Bobby", "password", "Google",
					"Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123",
					Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password",
					Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			Coop coop = service.createCoop(1, true, null, "Java", 1, "Montreal", true, Semester.Fall, null, student, 
					employer);
			service.createTasksWorkloadReport(newFormId, submissionDate, tasks, hoursPerWeek, wage, training, coop);
			//service.editTasksWorkloadReport(formId, "FormId", newFormId);
			service.editTasksWorkloadReport(newFormId, "SubmissionDate", newSubmissionDate);
			service.editTasksWorkloadReport(newFormId, "Tasks", newTasks);
			service.editTasksWorkloadReport(newFormId, "HoursPerWeek", newHoursPerWeek);
			service.editTasksWorkloadReport(newFormId, "Wage", newWage);
			service.editTasksWorkloadReport(newFormId, "Training", newTraining);
			
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Form> allForms = service.getAllForms();
		TasksWorkloadReport form = (TasksWorkloadReport) service.getForm(newFormId);
		
		assertEquals(1, allForms.size());
		assertEquals(newFormId, form.getFormId());
		assertEquals(newSubmissionDate, form.getSubmissionDate());
		assertEquals(newTasks, form.getTasks());
		assertEquals(newHoursPerWeek, form.getHoursPerWeek());
		assertEquals(newWage, form.getWage());
		assertEquals(newTraining, form.getTraining());
	}

}
