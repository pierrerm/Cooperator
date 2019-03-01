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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
	public void testGetAllStudents() {
		assertNotNull(service.getAllStudents());
	}

	@Test
	public void testGetAllStudentsWithFormErrors() {
		// assertNotNull(service.getAllStudentsWithFormError());
	}

	@Test
	public void testRestService() {
		try {

			// Create User
			URL urlS = new URL(
					"http://cooperator-backend-3417.herokuapp.com/student/438/TestStudentFirstName/TestStudentLastName/testing.student@mail.mcgill.ca/password/-1/260/U1/Software/NA");
			HttpURLConnection connS = (HttpURLConnection) urlS.openConnection();
			connS.setRequestMethod("POST");
			assertEquals(200, connS.getResponseCode());
			connS.disconnect();

			//Create Employer
			URL urlE = new URL(
					"http://cooperator-backend-3417.herokuapp.com/employer/438/TestEmployerFirst/TestEmployerLast/testing.employer@mail.mcgill.ca/password/-1/President/Mcgill/Montreal");
			HttpURLConnection connE = (HttpURLConnection) urlE.openConnection();
			connE.setRequestMethod("POST");
			assertEquals(200, connE.getResponseCode());
			connE.disconnect();
			
//			//Create Coop
//			URL urlC = new URL(
//					"http://cooperator-backend-3417.herokuapp.com/coop/-1/true/02-02-2018/Great Job/123/Montreal/false/winter/01-01-2018/-1/-1");
//			HttpURLConnection connC = (HttpURLConnection) urlC.openConnection();
//			connC.setRequestMethod("POST");
//			assertEquals(200, connC.getResponseCode());
//			connC.disconnect();
			
//			Date startDate = new Date(CooperatorRestController.createDate("01-01-2018"));
//			Date endDate = new Date(CooperatorRestController.createDate("02-02-2018"));
//			
//			service.createCoop(-1, true, endDate, "Great Job", 123, "Montreal", false, Semester.Winter, startDate, service.getStudent(-1), service.getEmployer(-1));
			
//			//Create Form
//			URL urlF = new URL(
//					"http://cooperator-backend-3417.herokuapp.com/form/acceptanceForm/-1/02-03-2016/-1");
//			HttpURLConnection connF = (HttpURLConnection) urlF.openConnection();
//			connF.setRequestMethod("POST");
//			assertEquals(200, connF.getResponseCode());
//			connF.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}
