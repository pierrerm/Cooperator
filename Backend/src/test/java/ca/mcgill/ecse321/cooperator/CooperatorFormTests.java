/**
 * 
 */
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
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author anudr
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperatorFormTests {
	@Test
	public void testJUnit4() {
		
	}
	
	@Mock
	private FormRepository formDao;
	@Mock
	private CoopRepository CoopDao;
	@Mock
	private StudentRepository studentDao;
	@Mock
	private EmployerRepository employerDao;
	
	@InjectMocks
	private CooperatorService service;

	@InjectMocks
	private CooperatorRestController controller;
	
	private Student student;
	private Employer employer;
	private CoopEvaluation coopEval;
	private AcceptanceForm accepForm;
	private StudentEvaluation studentEval;
	private TasksWorkloadReport taskReport;
	private Coop coop;
	private List<Form> returnedForms = new ArrayList<Form>();
	private Set<Form> formsFromStudent = new HashSet<Form>();
	private Set<Form> formsFromEmployer = new HashSet<Form>();

	private static final int STUDENT_KEY = 1;
	private static final int EMPLOYER_KEY = 1;
	private static final int COOP_KEY = 1;
	private static final int COOPEVAL_KEY = 40;
	private static final int STUDENTEVAL_KEY = 41;
	private static final int ACCEP_KEY = 42;
	private static final int TASKREP_KEY = 43;
	private static final int INVALID_KEY = -4;
	
	@Before
	public void setMockOutput() {
		when(formDao.findFormByFormId(anyInt())).thenAnswer((InvocationOnMock invocation)->{
			if (invocation.getArgument(0).equals(COOPEVAL_KEY)) {
				return coopEval;
			} 
			else if (invocation.getArgument(0).equals(STUDENTEVAL_KEY)) {
				return studentEval;
			}
			else if (invocation.getArgument(0).equals(ACCEP_KEY)) {
				return accepForm;
			}
			else if (invocation.getArgument(0).equals(TASKREP_KEY)) {
				return taskReport;
			}
			
			else {
				return null;
			}
		});	
		
		when(formDao.findAll()).thenAnswer((InvocationOnMock invocation)->{
			List<Form> list = new ArrayList<Form>();
			list.add(coopEval);
			list.add(studentEval);
			list.add(accepForm);
			list.add(taskReport);
			return list;
		});
		
		when(studentDao.findStudentByUserId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(STUDENT_KEY)) {
				return student;
			} else {
				return null;
			}
		});
		
		when(employerDao.findEmployerByUserId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(EMPLOYER_KEY)) {
				return employer;
			} else {
				return null;
			}
		});
		
		when(CoopDao.isInSemester(any(), any(), anyInt())).thenAnswer((InvocationOnMock invocation)->{
			return true;
		});
	}
	
	@Before
	public void setupMock() {
		
		student = mock(Student.class);
		student = service.createStudent(STUDENT_KEY, 321332, "email", "firstName", "lastName", "password", Faculty.Education, 260, "major", "minor", "academicYear", null);
		employer = mock(Employer.class);
		employer = service.createEmployer(EMPLOYER_KEY, 123, "email", "firstName", "lastName", "password", "position", "company", "location");
		coop = mock(Coop.class);
		coop = service.createCoop(COOP_KEY, true, new Date(createDate("31-08-2018")), "jobDescription", 12, "location", true , Semester.Summer, new Date(createDate("01-05-2018")), student, employer);
		coopEval = mock(CoopEvaluation.class);
		coopEval = service.createCoopEvaluation(COOPEVAL_KEY, null, "workExperience", 5, "softwareTechnologies", "usefulCourses", coop);	
		studentEval = mock(StudentEvaluation.class);
		studentEval = service.createStudentEvaluation(STUDENTEVAL_KEY, null, "studentWorkExperience", 5, coop);
		accepForm = mock(AcceptanceForm.class);
		accepForm = service.createAcceptanceForm(ACCEP_KEY, null, coop);
		taskReport = mock(TasksWorkloadReport.class);
		taskReport = service.createTasksWorkloadReport(TASKREP_KEY, null, "tasks", 40, 20, "training", coop);
		
		returnedForms.add(coopEval);
		returnedForms.add(studentEval);
		returnedForms.add(accepForm);
		returnedForms.add(taskReport);
		
		formsFromStudent.add(coopEval);
		formsFromStudent.add(studentEval);
		formsFromStudent.add(accepForm);
		formsFromStudent.add(taskReport);
	}
	
	@Test
	public void testCoopEvaluationCreation() {
		assertNotNull(coopEval);
	}
	
	@Test
	public void testCoopEvaluationQueryFound() {
		CoopEvaluation cE = (CoopEvaluation) service.getForm(COOPEVAL_KEY);
		assertEquals(COOPEVAL_KEY, service.getForm(COOPEVAL_KEY).getFormId());
		assertEquals(null, cE.getSubmissionDate());
		assertEquals("workExperience", cE.getWorkExperience());
		assertEquals(5, cE.getEmployerEvaluation());
		assertEquals("softwareTechnologies", cE.getSoftwareTechnologies());
		assertEquals("usefulCourses", cE.getUsefulCourses());
		assertEquals(coop, cE.getCoop());
	}
	
	@Test 
	public void formQueryNotFound() {
		assertNull(service.getForm(INVALID_KEY));
	}
	
	@Test
	public void testStudentEvaluationCreation() {
		assertNotNull(studentEval);
	}
	
	@Test
	public void testStudentEvaluationQueryFound() {
		StudentEvaluation sE = (StudentEvaluation) service.getForm(STUDENTEVAL_KEY);
		assertEquals(STUDENTEVAL_KEY, sE.getFormId());
		assertEquals(null, sE.getSubmissionDate());
		assertEquals("studentWorkExperience", sE.getStudentWorkExperience());
		assertEquals(5, sE.getStudentPerformance());
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
		TasksWorkloadReport tWR = (TasksWorkloadReport) service.getForm(TASKREP_KEY);
		assertEquals(TASKREP_KEY, tWR.getFormId());
		assertEquals(null, tWR.getSubmissionDate());
		assertEquals("tasks", tWR.getTasks());
		assertEquals(40, tWR.getHoursPerWeek());
		assertEquals(20, tWR.getWage());
		assertEquals("training", tWR.getTraining());
	}
	
	@Test
	public void testGetAllForms() {
		assertEquals(returnedForms, service.getAllForms());
	}
	
	@Test
	public void testCountForms() {
		assertEquals(4, service.countForms(coop));
	}
	
	@Test
	public void testGetFormsFromStudent() {
		assertEquals(formsFromStudent, service.getFormsFromStudent(STUDENT_KEY, Semester.Summer, 2018));
	}
	
	@Test
	public void testGetFormsFromEmployer() {
		assertEquals(formsFromStudent, service.getFormsFromEmployer(EMPLOYER_KEY, Semester.Summer, 2018));
	}
	
	@Test
	public void testEditAcceptanceForm() {
		service.editAcceptanceForm(ACCEP_KEY, "SubmissionDate", new Date(createDate("01-09-2018")));
		AcceptanceForm aF = (AcceptanceForm) service.getForm(ACCEP_KEY);
		//assertNotNull(service.getForm(90));
		assertEquals(new Date(createDate("01-09-2018")), aF.getSubmissionDate());
	}
	
	@Test
	public void testEditCoopEvaluation() {
		service.editCoopEvaluation(COOPEVAL_KEY, "SubmissionDate", new Date(createDate("01-09-2018")));
		service.editCoopEvaluation(COOPEVAL_KEY, "WorkExperience", "work");
		service.editCoopEvaluation(COOPEVAL_KEY, "EmployerEvaluation", 10);
		service.editCoopEvaluation(COOPEVAL_KEY, "SoftwareTechnologies", "software");
		service.editCoopEvaluation(COOPEVAL_KEY, "UsefulCourses", "course");
		
		CoopEvaluation cE = (CoopEvaluation) service.getForm(COOPEVAL_KEY);
		assertEquals(new Date(createDate("01-09-2018")), cE.getSubmissionDate());
		assertEquals("work", cE.getWorkExperience());
		assertEquals(10, cE.getEmployerEvaluation());
		assertEquals("software", cE.getSoftwareTechnologies());
		assertEquals("course", cE.getUsefulCourses());
	}
	
	@Test
	public void testEditStudentEvaluation() {
		service.editStudentEvaluation(STUDENTEVAL_KEY, "SubmissionDate", new Date(createDate("01-09-2018")));
		service.editStudentEvaluation(STUDENTEVAL_KEY, "StudentWorkExperience", "amazing");
		service.editStudentEvaluation(STUDENTEVAL_KEY, "StudentPerformance", 10);
		
		StudentEvaluation sE = (StudentEvaluation) service.getForm(STUDENTEVAL_KEY);
		assertEquals(new Date(createDate("01-09-2018")), sE.getSubmissionDate());
		assertEquals("amazing", sE.getStudentWorkExperience());
		assertEquals(10, sE.getStudentPerformance());
	}
	
	@Test
	public void testEditTasksWorkloadReport() {
		service.editTasksWorkloadReport(TASKREP_KEY, "SubmissionDate", new Date(createDate("01-09-2018")));
		service.editTasksWorkloadReport(TASKREP_KEY, "Tasks", "task");
		service.editTasksWorkloadReport(TASKREP_KEY, "HoursPerWeek", 35);
		service.editTasksWorkloadReport(TASKREP_KEY, "Wage", 21);
		service.editTasksWorkloadReport(TASKREP_KEY, "Training", "none");
		
		TasksWorkloadReport tWR = (TasksWorkloadReport) service.getForm(TASKREP_KEY);
		assertEquals(new Date(createDate("01-09-2018")), tWR.getSubmissionDate());
		assertEquals("task", tWR.getTasks());
		assertEquals(35, tWR.getHoursPerWeek());
		assertEquals(21, tWR.getWage());
		assertEquals("none", tWR.getTraining());
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
