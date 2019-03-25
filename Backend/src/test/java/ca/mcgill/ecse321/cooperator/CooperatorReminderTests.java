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
import ca.mcgill.ecse321.cooperator.dao.CoopRepository;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.dao.FormRepository;
import ca.mcgill.ecse321.cooperator.dao.ReminderRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.model.Coop;
import ca.mcgill.ecse321.cooperator.model.CoopEvaluation;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Faculty;
import ca.mcgill.ecse321.cooperator.model.Form;
import ca.mcgill.ecse321.cooperator.model.Reminder;
import ca.mcgill.ecse321.cooperator.model.Semester;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.service.CooperatorService;



/**
 * @author anudr, JulienLesaffre
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperatorReminderTests {
	@Test
	public void testJUnit4() {

	}

	@Mock
	private FormRepository formDao;
	@Mock
	private ReminderRepository reminderDao;
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
	private Coop coop;
	private CoopEvaluation coopEval;
	private Reminder reminder;
	private List<Reminder> remindersSent = new ArrayList<Reminder>();

	private static final int REMINDER_KEY = 1;
	private static final int INVALID_KEY = -60;
	private static final int STUDENT_KEY = 1;
	private static final int EMPLOYER_KEY = 1;
	private static final int COOP_KEY = 1;
	private static final int COOPEVAL_KEY = 40;



	@Before
	public void setMockOutput() {
		when(reminderDao.findReminderByReminderId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(REMINDER_KEY)) {
				reminder = new Reminder();
				return reminder;
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
		when(CoopDao.findCoopByCoopId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(COOP_KEY)) {
				return coop;
			} else {
				return null;
			}
		});
		when(studentDao.findAll()).thenAnswer((InvocationOnMock invocation)->{
			List<Student> list = new ArrayList<Student>();
			list.add(student);
			return list;
		});
		when(formDao.findAll()).thenAnswer((InvocationOnMock invocation)->{
			List<Form> list = new ArrayList<Form>();
			list.add(coopEval);
			return list;
		});
		
	}

	@Before
	public void setupMock() {
		Date today = new Date(System.currentTimeMillis());
		Date startDate = service.addDays(today, -12); // today + 15 days -> need a reminder if no forms submitted
		Date endDate =  service.addDays(today, 100);
		
		student = mock(Student.class);
		student = service.createStudent(STUDENT_KEY, 321332, "email", "firstName", "lastName", "password", Faculty.Education, 260, "major", "minor", "academicYear", null);
		employer = mock(Employer.class);
		employer = service.createEmployer(EMPLOYER_KEY, 123, "email", "firstName", "lastName", "password", "position", "company", "location");
		coop = mock(Coop.class);
		coop = service.createCoop(COOP_KEY, true, endDate, "jobDescription", 12, "location", true , Semester.Summer, startDate, student, employer);
		coopEval = mock(CoopEvaluation.class);
		coopEval = service.createCoopEvaluation(COOPEVAL_KEY, null, "workExperience", 5, "softwareTechnologies", "usefulCourses", coop);	
		reminder = mock(Reminder.class);
		reminder = service.createReminder("Reminder Subject", startDate, endDate, "This is a Reminder", 3, coop);
		
		
	}
	
	@Test
	public void testReminderCreation() {
		assertNotNull(REMINDER_KEY);
	}
	
	
	@Test
	public void testSendReminders() {
		remindersSent = service.sendReminders();
		assertNotNull(remindersSent);
	}

	@Test
	public void testReminderQueryFound() {
		assertEquals(REMINDER_KEY, service.getReminder(REMINDER_KEY).getReminderId());
	}

	@Test
	public void testReminderQueryNotFound() {
		assertNull(service.getReminder(INVALID_KEY));
	}
	
	@Test
	public void testGetAllReminders() {
		assertNotNull(service.getAllReminders());
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
