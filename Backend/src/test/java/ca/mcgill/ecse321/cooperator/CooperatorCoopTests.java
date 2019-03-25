/**
 * 
 */
package ca.mcgill.ecse321.cooperator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.controller.CooperatorRestController;
import ca.mcgill.ecse321.cooperator.dao.CoopRepository;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.dao.FormRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.model.AcceptanceForm;
import ca.mcgill.ecse321.cooperator.model.Coop;
import ca.mcgill.ecse321.cooperator.model.CoopEvaluation;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Faculty;
import ca.mcgill.ecse321.cooperator.model.Form;
import ca.mcgill.ecse321.cooperator.model.Semester;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.StudentEvaluation;
import ca.mcgill.ecse321.cooperator.model.TasksWorkloadReport;
import ca.mcgill.ecse321.cooperator.service.CooperatorService;


/**
 * @author anudr
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperatorCoopTests {
	@Test
	public void testJUnit4() {
		
	}
	
	@Mock
	private CoopRepository coopDao;
	@Mock
	private StudentRepository studentDao;
	@Mock
	private EmployerRepository employerDao;
	@Mock
	private FormRepository formDao;
	
	@InjectMocks
	private CooperatorService service;
	@InjectMocks
	private CooperatorRestController controller;
	
	private Coop coop;
	private CoopEvaluation coopEval;
	private AcceptanceForm accepForm;
	private StudentEvaluation studentEval;
	private TasksWorkloadReport taskReport;
	private Employer employer;
	private Student student;
	
	
	private static final int VALID_STUDENT_KEY = 1;
	private static final int VALID_EMPLOYER_KEY = 2;
	private static final int VALID_COOP_KEY = 3;
	private static final int INVALID_COOP_KEY = -1;
	private static final int COOPEVAL_KEY = 40;
	private static final int STUDENTEVAL_KEY = 41;
	private static final int ACCEP_KEY = 42;
	private static final int TASKREP_KEY = 43;
	private List<Coop> expectedList = new ArrayList<Coop>();
	private List<Coop> expectedList2 = new ArrayList<Coop>();
	private Set<Form> coopForms = new HashSet<Form>();

	@Before
	public void setMockOutput() {
		when(coopDao.findCoopByCoopId(anyInt())).thenAnswer((InvocationOnMock invocation)->{
			if (invocation.getArgument(0).equals(VALID_COOP_KEY)) {
				return coop;
			} else {
				return null;
			}
		});
		when(coopDao.findAll()).thenAnswer((InvocationOnMock invocation)->{
			List<Coop> list = new ArrayList<Coop>();
			list.add(coop);
			return list;
		});
	}
	
	@Before
	public void setupMock() {
		student = mock(Student.class);
		student = service.createStudent(VALID_STUDENT_KEY, 321332, "email", "firstName", "lastName", "password", Faculty.Education, 260, "major", "minor", "academicYear", null);
		employer = mock(Employer.class);
		employer = service.createEmployer(VALID_EMPLOYER_KEY, 123, "firstName", "lastName", "email", "password", "position", "company", "location");
		coop = mock(Coop.class);
		coop = service.createCoop(VALID_COOP_KEY, true, new Date(createDate("31-12-2018")), "jobDescription", 12, "location", true , Semester.Fall, new Date(createDate("31-08-2018")), student, employer);
		//coop2 = mock(Coop.class);
		//coop2 = service.createCoop(VALID_COOP_KEY, true, new Date(createDate("31-12-2018")), "jobDescription", 12, "location", true , Semester.Fall, new Date(createDate("31-08-2018")), student, employer);
		coopEval = mock(CoopEvaluation.class);
		coopEval = service.createCoopEvaluation(COOPEVAL_KEY, null, "workExperience", 5, "softwareTechnologies", "usefulCourses", coop);	
		studentEval = mock(StudentEvaluation.class);
		studentEval = service.createStudentEvaluation(STUDENTEVAL_KEY, null, "studentWorkExperience", 5, coop);
		accepForm = mock(AcceptanceForm.class);
		accepForm = service.createAcceptanceForm(ACCEP_KEY, null, coop);
		taskReport = mock(TasksWorkloadReport.class);
		taskReport = service.createTasksWorkloadReport(TASKREP_KEY, null, "tasks", 40, 20, "training", coop);
		expectedList.add(coop);
		coopForms.add(coopEval);
		coopForms.add(studentEval);
		coopForms.add(accepForm);
		coopForms.add(taskReport);
	}
	
	@Test
	public void testCoopCreation() {
		assertNotNull(coop);
	}
	
	@Test
	public void testGetAllCoops() {
		assertNotNull(service.getAllCoops());
	}
	
	@Test
	public void testCoopQueryFound() {
		assertEquals(VALID_COOP_KEY, service.getCoop(VALID_COOP_KEY).getCoopId());
		assertEquals(true, service.getCoop(VALID_COOP_KEY).getEmployerConfirmation());
		assertEquals(new Date(createDate("31-12-2018")), service.getCoop(VALID_COOP_KEY).getEndDate());
		assertEquals("jobDescription", service.getCoop(VALID_COOP_KEY).getJobDescription());
		assertEquals(12, service.getCoop(VALID_COOP_KEY).getJobId());
		assertEquals("location", service.getCoop(VALID_COOP_KEY).getLocation());
		assertEquals(true, service.getCoop(VALID_COOP_KEY).getNeedWorkPermit());
		assertEquals(Semester.Fall, service.getCoop(VALID_COOP_KEY).getSemester());
		assertEquals(new Date(createDate("31-08-2018")), service.getCoop(VALID_COOP_KEY).getStartDate());
		assertEquals(student, service.getCoop(VALID_COOP_KEY).getStudent());
		assertEquals(employer, service.getCoop(VALID_COOP_KEY).getEmployer());
		assertEquals(coopForms, service.getCoop(VALID_COOP_KEY).getForm());
	}
	
	@Test
	public void testGetAllActiveCoops() {
		String term = service.getTerm(Semester.Fall, new Date(createDate("31-08-2018")), new Date(createDate("31-12-2018")));
		assertEquals(expectedList, service.getAllActiveCoops(term));
	}
	
	@Test
	public void testGetAllCompletedActiveCoops() {
		String term = service.getTerm(Semester.Fall, new Date(createDate("31-08-2018")), new Date(createDate("31-12-2018")));
		assertEquals(expectedList, service.getAllCompletedActiveCoops(term));
	}
	
	@Test
	public void testGetCompletedActiveCoops() {
		String term = service.getTerm(Semester.Fall, new Date(createDate("31-08-2018")), new Date(createDate("31-12-2018")));
		assertEquals(expectedList2, service.getCompletedActiveCoops(VALID_STUDENT_KEY, term));
	}
	
	@Test
	public void testGetPreviouslyCompletedCoops() {
		String term = service.getTerm(Semester.Winter, new Date(createDate("06-01-2019")), new Date(createDate("30-04-2019")));
		assertEquals(expectedList2, service.getPreviouslyCompletedCoops(VALID_STUDENT_KEY, term));
	}
	
	@Test
	public void testGetAllPreviouslyCompletedCoops() {
		String term = service.getTerm(Semester.Winter, new Date(createDate("06-01-2019")), new Date(createDate("30-04-2019")));
		assertEquals(expectedList2, service.getAllPreviouslyCompletedCoops(term));
	}
	
	@Test
	public void testCoopQueryNotFound() {
		assertNull(service.getCoop(INVALID_COOP_KEY));
	}
	
	public static long createDate(String date) {
		java.util.Date dateFormat = null;
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateFormat.getTime();
	}
}
