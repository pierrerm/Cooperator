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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.controller.CooperatorRestController;
import ca.mcgill.ecse321.cooperator.dao.AdministratorRepository;
import ca.mcgill.ecse321.cooperator.model.Administrator;
import ca.mcgill.ecse321.cooperator.model.Faculty;
import ca.mcgill.ecse321.cooperator.service.CooperatorService;


/**
 * @author anudr
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperatorAdminTest {
	@Test
	public void testJUnit4() {
		
	}
	
	@Mock
	private AdministratorRepository adminDao;
	
	
	@InjectMocks
	private CooperatorService service;

	
	@InjectMocks
	private CooperatorRestController controller;
	
	private Administrator admin;

	private static final int VALID_ADMIN_KEY = 1;
	private static final int INVALID_ADMIN_KEY = -1;
	
	@Before
	public void setMockOutput() {
		when(adminDao.findAdministratorByUserId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(VALID_ADMIN_KEY)) {
				return admin;
			} else {
				return null;
			}
		});
	}
	
	@Before
	public void setupMock() {
		admin = mock(Administrator.class);
		admin = service.createAdministrator(123, "email", "firstName", "lastName", "password", Faculty.Engineering, 260);
	}
	
	@Test
	public void testMockAdminCreation() {
		assertNotNull(admin);
	}
	
	@Test
	public void testAdministratorQueryFound() {
		//assertEquals(VALID_ADMIN_KEY, service.getAdministrator(VALID_ADMIN_KEY).getUserId());
		assertEquals(123, service.getAdministrator(VALID_ADMIN_KEY).getPhone());
		assertEquals("email", service.getAdministrator(VALID_ADMIN_KEY).getEmail());
		assertEquals("firstName", service.getAdministrator(VALID_ADMIN_KEY).getFirstName());
		assertEquals("lastName", service.getAdministrator(VALID_ADMIN_KEY).getLastName());
		assertEquals("password", service.getAdministrator(VALID_ADMIN_KEY).getPassword());
		assertEquals(Faculty.Engineering, service.getAdministrator(VALID_ADMIN_KEY).getFaculty());
		assertEquals(260, service.getAdministrator(VALID_ADMIN_KEY).getId());
		
	}
	
	@Test
	public void testAdministratorQueryNotFound() {
		assertNull(service.getAdministrator(INVALID_ADMIN_KEY));
	}
	
	@Test
	public void testGetAllAdministrators() {
		assertNotNull(service.getAllAdministrators());
	}
}
