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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
	
	@InjectMocks
	private CooperatorService service;

	@InjectMocks
	private CooperatorRestController controller;
	
	private CoopEvaluation coopEval;
	private AcceptanceForm accepForm;
	private StudentEvaluation studentEval;
	private TasksWorkloadReport taskReport;
	
	private static final int COOPEVAL_KEY = 40;
	private static final int STUDENTEVAL_KEY = 41;
	private static final int ACCEP_KEY = 42;
	private static final int TASKREP_KEY = 43;
	private static final int INVALID_KEY = -4;
	
	@Before
	public void setMockOutput() {
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
	}
	
	@Before
	public void setupMock() {
		coopEval = mock(CoopEvaluation.class);
		studentEval = mock(StudentEvaluation.class);
		accepForm = mock(AcceptanceForm.class);
		taskReport = mock(TasksWorkloadReport.class);
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
	public void formQueryNotFound() {
		assertNull(service.getForm(INVALID_KEY));
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
	

}
