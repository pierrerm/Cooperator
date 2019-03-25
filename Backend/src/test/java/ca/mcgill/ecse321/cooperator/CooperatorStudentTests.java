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
import java.util.List;

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
import ca.mcgill.ecse321.cooperator.dao.CoopRepository;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.dao.FormRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.model.Administrator;
import ca.mcgill.ecse321.cooperator.model.Coop;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Faculty;
import ca.mcgill.ecse321.cooperator.model.Semester;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.service.CooperatorService;

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
	@Mock
	private FormRepository formDao;
	@Mock
	private CoopRepository coopRepo;
	@Mock
	private EmployerRepository employerDao;

	@InjectMocks
	private CooperatorService service;
	@InjectMocks
	private CooperatorRestController controller;

	private Student student;
	private Administrator admin;
	private Employer employer;
	private Coop coop;
	private List<Student> expectedList = new ArrayList<Student>();
	
	private static final int VALID_STUDENT_KEY = 1;
	private static final int VALID_COOP_KEY = 1;
	private static final int INVALID_STUDENT_KEY = -1;
		
	

	@Before
	public void setMockOutput() {
		when(studentDao.findStudentByUserId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(VALID_STUDENT_KEY)) {
				return student;
			} else {
				return null;
			}
		});
		when(studentDao.findAll()).thenAnswer((InvocationOnMock invocation)->{
			List<Student> list = new ArrayList<Student>();
			list.add(student);
			return list;
		});
	}

	@Before
	public void setupMock() {
		employer = mock(Employer.class);
		employer = new Employer();
		admin = mock(Administrator.class);
		admin = service.createAdministrator(123, "email", "firstName", "lastName", "password", Faculty.Engineering, 260);
		student = mock(Student.class);
		student = service.createStudent(VALID_STUDENT_KEY, 321332, "email", "firstName", "lastName", "password", Faculty.Education, 260, "major", "minor", "academicYear", admin);
		coop = mock(Coop.class);
		coop = service.createCoop(VALID_COOP_KEY, true, new Date(createDate("31-08-2018")), "jobDescription", 12, "location", true , Semester.Summer, new Date(createDate("01-05-2018")), student, employer);
		expectedList.add(student);
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
		String term = service.getTerm(Semester.Summer, new Date(createDate("02-05-2018")), new Date(createDate("29-08-2018")));
		assertEquals(expectedList, service.getAllStudentsWithFormError(term));
	}
	
	@Test
	public void testGetAllActiveStudents() {
		String term = service.getTerm(Semester.Summer, new Date(createDate("02-05-2018")), new Date(createDate("29-08-2018")));
		assertEquals(expectedList, service.getAllActiveStudents(term));
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
