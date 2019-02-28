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
public class CooperatorStudentTests {
	
	@Test
	public void testJUnit4() {
		
	}
	
	@Mock
	private StudentRepository studentDao;
	
	@InjectMocks
	private CooperatorService service;
	@InjectMocks
	private CooperatorRestController controller;
	
	private Student student;
	
	private static final int VALID_STUDENT_KEY = 1;
	private static final int INVALID_STUDENT_KEY = -1;
	
	
	@Before
	public void setMockOutput() {
		when(studentDao.findStudentByUserId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(VALID_STUDENT_KEY)) {
				Student student = new Student();
				student.setUserId(VALID_STUDENT_KEY);
				return student;
			} else {
				return null;
			}
		});
	}
	
	@Before
	public void setupMock() {
		student = mock(Student.class);
	}
	
	@Test
	public void testMockStudentCreation() {
		assertNotNull(student);
	}

	@Test
	public void testStudentQueryFound() {
		assertEquals(VALID_STUDENT_KEY, service.getStudent(VALID_STUDENT_KEY).getUserId());
	}
	
	@Test 
	public void testStudentQueryNotFound() {
		assertNull(service.getStudent(INVALID_STUDENT_KEY));
	}
	
	@Test
	public void  testGetAllStudents() {
		assertNotNull(service.getAllStudents());
	}
	
	@Test
	public void testGetAllStudentsWithFormErrors() {
	//	assertNotNull(service.getAllStudentsWithFormError());
	}
	

}
