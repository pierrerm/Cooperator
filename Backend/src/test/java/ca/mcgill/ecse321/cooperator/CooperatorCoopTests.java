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
	
	@InjectMocks
	private CooperatorService service;
	@InjectMocks
	private CooperatorRestController controller;
	
	private Coop coop;
	private Employer employer;
	private Student student;

	private static final int VALID_STUDENT_KEY = 1;
	private static final int VALID_EMPLOYER_KEY = 2;
	private static final int VALID_COOP_KEY = 3;
	private static final int INVALID_COOP_KEY = -1;

	@Before
	public void setMockOutput() {
		when(coopDao.findCoopByCoopId(anyInt())).thenAnswer((InvocationOnMock invocation)->{
			if (invocation.getArgument(0).equals(VALID_COOP_KEY)) {
				return coop;
			} else {
				return null;
			}
		});
	}
	
	@Before
	public void setupMock() {
		student = mock(Student.class);
		student = service.createStudent(VALID_STUDENT_KEY, 321332, "email", "firstName", "lastName", "password", Faculty.Education, 260, "major", "minor", "academicYear", null);
		employer = mock(Employer.class);
		employer = service.createEmployer(VALID_EMPLOYER_KEY, 123, "email", "firstName", "lastName", "password", "position", "company", "location");
		coop = mock(Coop.class);
		coop = service.createCoop(VALID_COOP_KEY, true, null, "jobDescription", 12, "location", true , Semester.Fall, null, student, employer);

	}
	
	@Test
	public void testCoopCreation() {
		assertNotNull(coop);
	}
	
	@Test
	public void testCoopQueryFound() {
		assertEquals(VALID_COOP_KEY, service.getCoop(VALID_COOP_KEY).getCoopId());
		assertEquals(true, service.getCoop(VALID_COOP_KEY).getEmployerConfirmation());
		assertNull(service.getCoop(VALID_COOP_KEY).getEndDate());
		assertEquals("jobDescription", service.getCoop(VALID_COOP_KEY).getJobDescription());
		assertEquals(12, service.getCoop(VALID_COOP_KEY).getJobId());
		assertEquals("location", service.getCoop(VALID_COOP_KEY).getLocation());
		assertEquals(true, service.getCoop(VALID_COOP_KEY).getNeedWorkPermit());
		assertEquals(Semester.Fall, service.getCoop(VALID_COOP_KEY).getSemester());
		assertNull(service.getCoop(VALID_COOP_KEY).getStartDate());
		assertEquals(student, service.getCoop(VALID_COOP_KEY).getStudent());
		assertEquals(employer, service.getCoop(VALID_COOP_KEY).getEmployer());
		
	}
	
	@Test
	public void testCoopQueryNotFound() {
		assertNull(service.getCoop(INVALID_COOP_KEY));
	}
}
