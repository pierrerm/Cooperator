//package ca.mcgill.ecse321.cooperator.service;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
//import java.sql.Date;
//import java.sql.Time;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Calendar;
//import java.util.List;
//
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import ca.mcgill.ecse321.cooperator.dao.FormRepository;
//import ca.mcgill.ecse321.cooperator.dao.ReminderRepository;
//import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
//import ca.mcgill.ecse321.cooperator.dao.DownloadableDocRepository;
//import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
//import ca.mcgill.ecse321.cooperator.dao.AdministratorRepository;
//import ca.mcgill.ecse321.cooperator.dao.CoopRepository;
//import ca.mcgill.ecse321.cooperator.model.Form;
//import ca.mcgill.ecse321.cooperator.model.Administrator;
//import ca.mcgill.ecse321.cooperator.model.Coop;
//import ca.mcgill.ecse321.cooperator.model.DocumentType;
//import ca.mcgill.ecse321.cooperator.model.Reminder;
//import ca.mcgill.ecse321.cooperator.model.Semester;
//import ca.mcgill.ecse321.cooperator.model.Student;
//import ca.mcgill.ecse321.cooperator.model.DownloadableDoc;
//import ca.mcgill.ecse321.cooperator.model.Employer;
//import ca.mcgill.ecse321.cooperator.model.Faculty;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TestCooperatorService {
//	
//	@Autowired
//	private CooperatorService service;
//	
//	@Autowired
//	private CoopRepository coopRepository;
//	@Autowired
//	private FormRepository formRepository;
//	@Autowired
//	private ReminderRepository reminderRepository;
//	@Autowired
//	private DownloadableDocRepository downloadableDocRepository;
//	@Autowired
//	private StudentRepository studentRepository;
//	@Autowired
//	private AdministratorRepository administratorRepository;
//	@Autowired
//	private EmployerRepository employerRepository;
//	
//	@After
//	public void clearDatabase() {
//		try {
//		coopRepository.deleteAll();
//		formRepository.deleteAll();
//		reminderRepository.deleteAll();
//		downloadableDocRepository.deleteAll();
//		studentRepository.deleteAll();
//		administratorRepository.deleteAll();
//		employerRepository.deleteAll();
//		}
//		catch(NullPointerException e){
//			
//		}
//	}
//	
////	@Test
////	public void testCreateCoop() {
////		assertEquals(0, service.getAllCoops().size());
////
////		int jobId = 1;
////		boolean employerConfirmation = true;
////		Date endDate = null;
////		String jobDescription = "Java";
////		String location = "Montreal";
////		boolean needWorkPermit = true;
////		Semester semester = Semester.Fall;
////		Date startDate = null;
////		String year = "1998";
////
////		try {
////			DownloadableDoc doc = service.createDownloadableDoc(4, "C:", DocumentType.CoopPlacementProof);
////			Employer employer = service.createEmployer(1, "google@gmail.com", "Bob", "Bobby", "password", "Google", "Montreal", 123548, "HR");
////			Form form = service.createStudentEvaluation("C:", "1", startDate);
////			Administrator admin = service.createAdministrator(2, "@gmail.com", "Robert", "njdnfs", "password123", Faculty.Engineering, 260147532);
////			Student student = service.createStudent(3, "@gmail.com", "Terry", "sdfsdf", "password", Faculty.Engineering, 260148654, "Software", "", 1998, admin);
////			service.createCoop(jobId, employerConfirmation, endDate, jobDescription, location, needWorkPermit, semester, startDate, year, doc, employer, form, student);
////		} catch (IllegalArgumentException e) {
////			fail();
////		}
////
////		List<Coop> allCoops = service.getAllCoops();
////
////		assertEquals(1, allCoops.size());
////		assertEquals(jobId, allCoops.get(0).getJobId());
////	}
//	
//	@Test
//	public void testCreateDownloadableDoc() {
//		assertEquals(0, service.getAllDownloadableDocs().size());
//
//		int docId = 1;
//		String filePath = "Heyo";
//		DocumentType docType = DocumentType.CoopPlacementProof;
//
//		try {
//			service.createDownloadableDoc(docId, filePath, docType);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//
//		List<DownloadableDoc> allDownloadableDocs = service.getAllDownloadableDocs();
//
//		assertEquals(1, allDownloadableDocs.size());
//		assertEquals(docId, allDownloadableDocs.get(0).getDocId());
//	}
//	
//	@Test
//	public void testCreateEmployer() {
//		assertEquals(0, service.getAllEmployers().size());
//
//		int userId = 1;
//		String email = "";
//		String firstName = "";
//		String lastName = "";
//		String password = "";
//		String company  = "";
//		String location = "";
//		int phone = 1;
//		String position = "";
//
//		try {
//			service.createEmployer(userId, email, firstName, lastName, password, company, location, phone, position);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//
//		List<Employer> allEmployers = service.getAllEmployers();
//
//		assertEquals(1, allEmployers.size());
//		assertEquals(userId, allEmployers.get(0).getUserId());
//	}
//	
//	@Test
//	public void testCreateAdministrator() {
//		assertEquals(0, service.getAllAdministrators().size());
//
//		int userId = 1;
//		String email = "";
//		String firstName = "";
//		String lastName = "";
//		String password = "";
//		Faculty faculty = Faculty.Engineering;
//		int id = 1;
//
//		try {
//			service.createAdministrator(userId, email, firstName, lastName, password, faculty, id);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//
//		List<Administrator> allAdministrators = service.getAllAdministrators();
//
//		assertEquals(1, allAdministrators.size());
//		assertEquals(userId, allAdministrators.get(0).getUserId());
//	}
//	
//	@Test
//	public void testCreateStudent() {
//		assertEquals(0, service.getAllStudents().size());
//
//		int userIdA = 1;
//		String emailA = "";
//		String firstNameA = "";
//		String lastNameA = "";
//		String passwordA = "";
//		Faculty facultyA = Faculty.Engineering;
//		int idA = 1;
//		
//		int userIdS = 1;
//		String emailS = "";
//		String firstNameS = "";
//		String lastNameS = "";
//		String passwordS = "";   
//		Faculty facultyS = Faculty.Engineering;
//		int idS = 1;
//		String majorS = "";
//		String minorS = "";
//		int yearS = 1;
//
//		try {
//			Administrator admin = service.createAdministrator(userIdA, emailA, firstNameA, lastNameA, passwordA, facultyA, idA);
//			service.createStudent(userIdS, emailS, firstNameS, lastNameS, passwordS, facultyS, idS, majorS, minorS, yearS, admin);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//
//		List<Student> allStudents = service.getAllStudents();
//
//		assertEquals(1, allStudents.size());
//		assertEquals(userIdS, allStudents.get(0).getUserId());
//	}
//	
//	@Test
//	public void testCreateAcceptanceForm() {
//		assertEquals(0, service.getAllForms().size());
//
//		String filePath = "";
//		String formId = "1";
//		Date submissionDate = null;
//
//		try {
//			service.createAcceptanceForm(filePath, formId, submissionDate);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//
//		List<Form> allForms = service.getAllForms();
//
//		assertEquals(1, allForms.size());
//		assertEquals(formId, allForms.get(0).getFormId());
//	}
//	
//	@Test
//	public void testCreateTechnicalReport() {
//		assertEquals(0, service.getAllForms().size());
//
//		String filePath = "";
//		String formId = "2";
//		Date submissionDate = null;
//
//		try {
//			service.createTechnicalReport(filePath, formId, submissionDate);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//
//		List<Form> allForms = service.getAllForms();
//
//		assertEquals(1, allForms.size());
//		assertEquals(formId, allForms.get(0).getFormId());
//	}
//	
//	@Test
//	public void testCreateCoopEvaluation() {
//		assertEquals(0, service.getAllForms().size());
//
//		String filePath = "";
//		String formId = "3";
//		Date submissionDate = null;
//
//		try {
//			service.createCoopEvaluation(filePath, formId, submissionDate);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//
//		List<Form> allForms = service.getAllForms();
//
//		assertEquals(1, allForms.size());
//		assertEquals(formId, allForms.get(0).getFormId());
//	}
//	
//	@Test
//	public void testCreateStudentEvaluation() {
//		assertEquals(0, service.getAllForms().size());
//
//		String filePath = "";
//		String formId = "4";
//		Date submissionDate = null;
//
//		try {
//			service.createStudentEvaluation(filePath, formId, submissionDate);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//
//		List<Form> allForms = service.getAllForms();
//
//		assertEquals(1, allForms.size());
//		assertEquals(formId, allForms.get(0).getFormId());
//	}
//	
//	@Test
//	public void testCreateTasksWorkloadReport() {
//		assertEquals(0, service.getAllForms().size());
//
//		String filePath = "";
//		String formId = "5";
//		Date submissionDate = null;
//
//		try {
//			service.createTasksWorkloadReport(filePath, formId, submissionDate);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//
//		List<Form> allForms = service.getAllForms();
//
//		assertEquals(1, allForms.size());
//		assertEquals(formId, allForms.get(0).getFormId());
//	}
//	
//	@Test
//	public void testCreateEmployerContract() {
//		assertEquals(0, service.getAllForms().size());
//
//		String filePath = "";
//		String formId = "6";
//		Date submissionDate = null;
//
//		try {
//			service.createEmployerContract(filePath, formId, submissionDate);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//
//		List<Form> allForms = service.getAllForms();
//
//		assertEquals(1, allForms.size());
//		assertEquals(formId, allForms.get(0).getFormId());
//	}
//	
//	@Test
//	public void testCreateAllForms() {
//		assertEquals(0, service.getAllForms().size());
//
//		try {
//			service.createAcceptanceForm("", "1", null);
//			service.createEmployerContract("", "2", null);
//			service.createTechnicalReport("", "3", null);
//			service.createCoopEvaluation("", "4", null);
//			service.createStudentEvaluation("", "5", null);
//			service.createTasksWorkloadReport("", "6", null);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//
//		List<Form> allForms = service.getAllForms();
//
//		assertEquals(6, allForms.size());
//	}
//	
//}
