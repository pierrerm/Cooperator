package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.dao.FormRepository;
import ca.mcgill.ecse321.cooperator.dao.ReminderRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.dao.PDFRepository;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.dao.AdministratorRepository;
import ca.mcgill.ecse321.cooperator.dao.CoopRepository;
import ca.mcgill.ecse321.cooperator.model.Form;
import ca.mcgill.ecse321.cooperator.model.Administrator;
import ca.mcgill.ecse321.cooperator.model.Coop;
import ca.mcgill.ecse321.cooperator.model.DocumentType;
import ca.mcgill.ecse321.cooperator.model.Reminder;
import ca.mcgill.ecse321.cooperator.model.Semester;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.PDF;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Faculty;

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
		}
		catch(NullPointerException e){
			
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
			Employer employer = service.createEmployer(1,1, "google@gmail.com", "Bob", "Bobby", "password", "Google", "Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123", Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password", Faculty.Engineering, 260148654, "Software", "", "U2", admin);
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
			Employer employer = service.createEmployer(1,1, "google@gmail.com", "Bob", "Bobby", "password", "Google", "Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123", Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password", Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			Coop coop = service.createCoop(coopId, employerConfirmation, endDate, jobDescription, jobId, location, needWorkPermit,
					semester, startDate, student, employer);
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
		String email = "";
		String firstName = "";
		String lastName = "";
		String password = "";
		String company  = "";
		String location = "";
		long phone = 1;
		String position = "";

		try {
			service.createEmployer(userId, phone, email, firstName, lastName, password, position,
					company, location);
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
		String email = "";
		String firstName = "";
		String lastName = "";
		String password = "";
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

		int userIdA = 1;
		String emailA = "as";
		long phoneA = 234;
		String firstNameA = "df";
		String lastNameA = "rwe";
		String passwordA = "ewre";
		Faculty facultyA = Faculty.Engineering;
		int idA = 1;
		
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
			Administrator admin = service.createAdministrator(userIdA, phoneA, emailA, firstNameA, lastNameA, passwordA, facultyA, idA);
			service.createStudent(userIdS, phoneS, emailS, firstNameS, lastNameS, passwordS, facultyS, idS, majorS, minorS, yearS, null);
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
		String emailS = "";
		long phoneS = 3559;
		String firstNameS = "";
		String lastNameS = "";
		String passwordS = "";   
		Faculty facultyS = Faculty.Engineering;
		int idS = 1;
		String majorS = "";
		String minorS = "";
		String yearS = "U3";

		try {
			service.createStudent(userIdS, phoneS, emailS, firstNameS, lastNameS, passwordS, facultyS, idS, majorS, minorS, yearS, null);
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
			Employer employer = service.createEmployer(1,1, "google@gmail.com", "Bob", "Bobby", "password", "Google", "Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123", Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password", Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			Coop coop = service.createCoop(coopId, employerConfirmation, endDate, jobDescription, jobId, location, needWorkPermit,
					semester, startDate, student, employer);
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
			Employer employer = service.createEmployer(1,1, "google@gmail.com", "Bob", "Bobby", "password", "Google", "Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123", Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password", Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			Coop coop = service.createCoop(coopId, employerConfirmation, endDate, jobDescription, jobId, location, needWorkPermit,
					semester, startDate, student, employer);
			service.createCoopEvaluation(formId, submissionDate, workExperience, employerEvaluation, softwareTechnologies, usefulCourses, coop);
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
			Employer employer = service.createEmployer(1,1, "google@gmail.com", "Bob", "Bobby", "password", "Google", "Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123", Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password", Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			Coop coop = service.createCoop(coopId, employerConfirmation, endDate, jobDescription, jobId, location, needWorkPermit,
					semester, startDate, student, employer);
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
			Employer employer = service.createEmployer(1,1, "google@gmail.com", "Bob", "Bobby", "password", "Google", "Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123", Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password", Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			Coop coop = service.createCoop(coopId, employerConfirmation, endDate, jobDescription, jobId, location, needWorkPermit,
					semester, startDate, student, employer);
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
			Employer employer = service.createEmployer(1,1, "google@gmail.com", "Bob", "Bobby", "password", "Google", "Montreal", "HR");
			Administrator admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123", Faculty.Engineering, 260147532);
			Student student = service.createStudent(3, 1, "@gmail.com", "Terry", "sdfsdf", "password", Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			Coop coop = service.createCoop(coopId, employerConfirmation, endDate, jobDescription, jobId, location, needWorkPermit,
					semester, startDate, student, employer);
			service.createReminder(reminderId, subject, date, deadline, description, urgency, coop);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Reminder> allReminders = service.getAllReminders();

		assertEquals(1, allReminders.size());
		assertEquals(reminderId, allReminders.get(0).getReminderId());
	}
	
}
