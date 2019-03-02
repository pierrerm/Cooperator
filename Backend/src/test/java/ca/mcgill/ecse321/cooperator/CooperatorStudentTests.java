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


import java.sql.Date;

/**
 * @author anudr
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperatorStudentTests {

	@Test
	public void testJUnit4() {

	}

	@Mock
	private StudentRepository studentDao;
	@Mock
	private AdministratorRepository adminDao;

	@InjectMocks
	private CooperatorService service;
	@InjectMocks
	private CooperatorRestController controller;

	private Student student;

	private static final int VALID_STUDENT_KEY = 1;
	private static final int INVALID_STUDENT_KEY = -1;
	
	private Administrator admin;

	private static final int VALID_ADMIN_KEY = 1;
	

	@Before
	public void setMockOutput() {
		when(studentDao.findStudentByUserId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(VALID_STUDENT_KEY)) {
				return student;
			} else {
				return null;
			}
		});
	}

	@Before
	public void setupMock() {
		admin = mock(Administrator.class);
		admin = service.createAdministrator(VALID_ADMIN_KEY, 123, "email", "firstName", "lastName", "password", Faculty.Engineering, 260);
		student = mock(Student.class);
		student = service.createStudent(VALID_STUDENT_KEY, 321332, "email", "firstName", "lastName", "password", Faculty.Education, 260, "major", "minor", "academicYear", admin);

	}

	@Test
	public void testMockStudentCreation() {
		assertNotNull(student);
	}

	@Test
	public void testStudentQueryFound() {
		assertEquals(VALID_STUDENT_KEY, service.getStudent(VALID_STUDENT_KEY).getUserId());
		assertEquals(321332, service.getStudent(VALID_STUDENT_KEY).getPhone());
		assertEquals("email", service.getStudent(VALID_STUDENT_KEY).getEmail());
		assertEquals("firstName", service.getStudent(VALID_STUDENT_KEY).getFirstName());
		assertEquals("lastName", service.getStudent(VALID_STUDENT_KEY).getLastName());
		assertEquals("password", service.getStudent(VALID_STUDENT_KEY).getPassword());
		assertEquals(Faculty.Education, service.getStudent(VALID_STUDENT_KEY).getFaculty());
		assertEquals("major", service.getStudent(VALID_STUDENT_KEY).getMajor());
		assertEquals("minor", service.getStudent(VALID_STUDENT_KEY).getMinor());
		assertEquals("academicYear", service.getStudent(VALID_STUDENT_KEY).getAcademicYear());
	}

	@Test
	public void testStudentQueryNotFound() {
		assertNull(service.getStudent(INVALID_STUDENT_KEY));
	}

	@Test
	public void testGetAllStudents() {
		assertNotNull(service.getAllStudents());
	}

	@Test
	public void testGetAllStudentsWithFormErrors() {
		// assertNotNull(service.getAllStudentsWithFormError());
	}

	
}
