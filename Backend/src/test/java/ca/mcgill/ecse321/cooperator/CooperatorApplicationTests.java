package ca.mcgill.ecse321.cooperator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.controller.CooperatorRestController;

import ca.mcgill.ecse321.cooperator.service.CooperatorService;

import ca.mcgill.ecse321.cooperator.model.*;

import ca.mcgill.ecse321.cooperator.dao.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperatorApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Mock
	private StudentRepository studentDao;
	@Mock
	private AdministratorRepository adminDao;
	@Mock
	private EmployerRepository employerDao;
	@Mock
	private CoopRepository coopDao;
	@Mock
	private FormRepository formDao;
	@Mock
	private PDFRepository pdfDao;
	@Mock
	private ReminderRepository reminderDao;

	@InjectMocks
	private CooperatorService service;

	@Mock
	private CooperatorService serviceMock;

	@InjectMocks
	private CooperatorRestController controller;

	private Student student;
	private Administrator admin;
	private Employer employer;
	private Coop coop;
	private CoopEvaluation coopEval;
	private AcceptanceForm accepForm;
	private StudentEvaluation studentEval;
	private TasksWorkloadReport taskReport;
	private PDF pdf;
	private Reminder reminder;
	
	private static final int STUDENT_KEY = 1;
	private static final int ADMIN_KEY = 2;
	private static final int EMPLOYER_KEY = 3;
	private static final int COOP_KEY = 30;
	private static final int COOPEVAL_KEY = 40;
	private static final int STUDENTEVAL_KEY = 41;
	private static final int ACCEP_KEY = 42;
	private static final int TASKREP_KEY = 43;
	private static final int PDF_KEY = 50;
	private static final int REMINDER_KEY = 60; 
	private static final int NOTEXISTING_KEY = 100;
	
	@Before
	public void setMockOutput() {
		when(studentDao.findStudentByUserId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(STUDENT_KEY)) {
				Student student = new Student();
				student.setUserId(STUDENT_KEY);
				return student;
			} else {
				return null;
			}
		});
		
		when(adminDao.findAdministratorByUserId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ADMIN_KEY)) {
				Administrator admin = new Administrator();
				admin.setUserId(ADMIN_KEY);
				return admin;
			} else {
				return null;
			}
		});
		
		when(employerDao.findEmployerByUserId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(EMPLOYER_KEY)) {
				Employer employer = new Employer();
				employer.setUserId(EMPLOYER_KEY);
				return employer;
			} else {
				return null;
			}
		});
		
		when(coopDao.findCoopByCoopId(anyInt())).thenAnswer((InvocationOnMock invocation)->{
			if (invocation.getArgument(0).equals(COOP_KEY)) {
				Coop coop = new Coop();
				coop.setCoopId(COOP_KEY);
				return coop;
			} else {
				return null;
			}
		});
		
		when(formDao.findFormByFormId(anyInt())).thenAnswer((InvocationOnMock invocation)->{
			if (invocation.getArgument(0).equals(COOPEVAL_KEY)) {
				CoopEvaluation coopEval = new CoopEvaluation();
				coopEval.setFormId(COOPEVAL_KEY);
				return coopEval;
			} 
			else if (invocation.getArgument(0).equals(STUDENTEVAL_KEY)) {
				StudentEvaluation studentEval = new StudentEvaluation();
				studentEval.setFormId(STUDENTEVAL_KEY);
				return studentEval;
			}
			else if (invocation.getArgument(0).equals(ACCEP_KEY)) {
				AcceptanceForm accepForm = new AcceptanceForm();
				accepForm.setFormId(ACCEP_KEY);
				return accepForm;
			}
			else if (invocation.getArgument(0).equals(TASKREP_KEY)) {
				TasksWorkloadReport taskReport = new TasksWorkloadReport();
				taskReport.setFormId(TASKREP_KEY);
				return taskReport;
			}
			
			else {
				return null;
			}
		});
		
		when(pdfDao.findPDFByDocId(anyInt())).thenAnswer((InvocationOnMock invocation)->{
			if (invocation.getArgument(0).equals(PDF_KEY)) {
				PDF pdf = new PDF();
				pdf.setDocId(PDF_KEY);
				return pdf;
			} else {
				return null;
			}
		});
		
		when(reminderDao.findReminderByReminderId(anyInt())).thenAnswer((InvocationOnMock invocation)->{
			if (invocation.getArgument(0).equals(REMINDER_KEY)) {
				Reminder reminder = new Reminder();
				reminder.setReminderId(REMINDER_KEY);
				return reminder;
			} else {
				return null;
			}
		});
		
	}

	@Before
	public void setupMock() {
		student = mock(Student.class);
		admin = mock(Administrator.class);
		employer = mock(Employer.class);
		coop = mock(Coop.class);
		coopEval = mock(CoopEvaluation.class);
		studentEval = mock(StudentEvaluation.class);
		accepForm = mock(AcceptanceForm.class);
		taskReport = mock(TasksWorkloadReport.class);
		pdf = mock(PDF.class);
		reminder = mock(Reminder.class);
	}

	@Test
	public void testMockStudentCreation() {
		assertNotNull(student);
	}

	@Test
	public void testStudentQueryFound() {
		assertEquals(STUDENT_KEY, service.getStudent(STUDENT_KEY).getUserId());
	}
	
	@Test
	public void testMockAdminCreation() {
		assertNotNull(admin);
	}
	
	@Test
	public void testAdministratorQueryFound() {
		assertEquals(ADMIN_KEY, service.getAdministrator(ADMIN_KEY).getUserId());
	}
	
	@Test
	public void testEmployerCreation() {
		assertNotNull(employer);
	}
	
	@Test
	public void testEmployerQueryFound() {
		assertEquals(EMPLOYER_KEY, service.getEmployer(EMPLOYER_KEY).getUserId());
	}
	
	@Test
	public void testCoopCreation() {
		assertNotNull(coop);
	}
	
	@Test
	public void testCoopQueryFound() {
		assertEquals(COOP_KEY, service.getCoop(COOP_KEY).getCoopId());
	}
	
	@Test
	public void testCoopEvaluationCreation() {
		assertNotNull(coopEval);
	}
	
	@Test
	public void testCoopEvaluationQueryFound() {
		assertEquals(COOPEVAL_KEY, service.getForm(COOPEVAL_KEY).getFormId());
	}
	
	@Test
	public void testStudentEvaluationCreation() {
		assertNotNull(studentEval);
	}
	
	@Test
	public void testStudentEvaluationQueryFound() {
		assertEquals(STUDENTEVAL_KEY, service.getForm(STUDENTEVAL_KEY).getFormId());
	}
	
	@Test
	public void testAcceptanceFormCreation() {
		assertNotNull(accepForm);
	}
	
	@Test
	public void testAcceptanceFormQueryFound() {
		assertEquals(ACCEP_KEY, service.getForm(ACCEP_KEY).getFormId());
	}
	
	@Test
	public void testTasksWorkloadReportCreation() {
		assertNotNull(TASKREP_KEY);
	}
	
	@Test
	public void testTasksWorkloadReportQueryFound() {
		assertEquals(TASKREP_KEY, service.getForm(TASKREP_KEY).getFormId());
	}
	
	@Test
	public void testPDFCreation() {
		assertNotNull(PDF_KEY);
	}
	
	@Test
	public void testPDFQueryFound() {
		assertEquals(PDF_KEY, service.getPDF(PDF_KEY).getDocId());
	}
	
	@Test
	public void testReminderCreation() {
		assertNotNull(REMINDER_KEY);
	}
	
	@Test
	public void testReminderQueryFound() {
		assertEquals(REMINDER_KEY, service.getReminder(REMINDER_KEY).getReminderId());
	}

}
