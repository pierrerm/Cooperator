package ca.mcgill.ecse321.cooperator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.controller.CooperatorRestController;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.model.Coop;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Semester;
import ca.mcgill.ecse321.cooperator.service.CooperatorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperatorEmployerTests {
	@Test
	public void testJUnit4() {
		
	}
	
	@Mock
	private EmployerRepository employerDao;
	
	@InjectMocks
	private CooperatorService service;
	@InjectMocks
	private CooperatorRestController controller;
	
	private Employer employer;
	private Employer emp;
	
	private static final int VALID_EMPLOYER_KEY = 1;
	private static final int INVALID_EMPLOYER_KEY = 3;
	
	@Before
	public void setMockOutput() {
		when(employerDao.findEmployerByUserId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(VALID_EMPLOYER_KEY)) {
				return employer;
			} else {
				return null;
			}
		});
	}
	
	@Before
	public void setupMock() {
		employer = mock(Employer.class);
		employer = service.createEmployer(VALID_EMPLOYER_KEY, 123, "firstName", "lastName", "email", "password", "position", "company", "location");
	}
	
	@Test
	public void testEmployerCreation() {
		assertNotNull(employer);
	}
	
	@Test
	public void testEmployerQueryFound() {
		assertEquals(VALID_EMPLOYER_KEY, service.getEmployer(VALID_EMPLOYER_KEY).getUserId());
		assertEquals(123, service.getEmployer(VALID_EMPLOYER_KEY).getPhone());
		assertEquals("email", service.getEmployer(VALID_EMPLOYER_KEY).getEmail());
		assertEquals("firstName", service.getEmployer(VALID_EMPLOYER_KEY).getFirstName());
		assertEquals("lastName", service.getEmployer(VALID_EMPLOYER_KEY).getLastName());
		assertEquals("password", service.getEmployer(VALID_EMPLOYER_KEY).getPassword());
		assertEquals("position", service.getEmployer(VALID_EMPLOYER_KEY).getPosition());
		assertEquals("company", service.getEmployer(VALID_EMPLOYER_KEY).getCompany());
		assertEquals("location", service.getEmployer(VALID_EMPLOYER_KEY).getLocation());
		
	}
	
	@Test
	public void testEmployerQueryNotFound() {
		assertNull(service.getEmployer(INVALID_EMPLOYER_KEY));
	}
	
	@Test
	public void testGetAllEmployers() {
		assertNotNull(service.getAllEmployers());
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void testIllegalArgument() {
		emp = mock(Employer.class);
		employer = service.createEmployer(VALID_EMPLOYER_KEY, 123, "", "", "", "", "", "", "location");

	}
}
