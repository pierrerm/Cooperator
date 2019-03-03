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
import static org.junit.Assert.fail;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



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
	private Reminder reminder;
	private List<Reminder> remindersSent = new ArrayList<Reminder>();

	private static final int REMINDER_KEY = 60;
	private static final int INVALID_KEY = -60;
	private static final int STUDENT_KEY = 1;
	private static final int EMPLOYER_KEY = 1;
	private static final int COOP_KEY = 1;
	private static final int COOPEVAL_KEY = 40;
	private static final int STUDENTEVAL_KEY = 41;
	private static final int ACCEP_KEY = 42;
	private static final int TASKREP_KEY = 43;


	@Before
	public void setMockOutput() {
		when(reminderDao.findReminderByReminderId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(REMINDER_KEY)) {
				Reminder reminder = new Reminder();
				reminder.setReminderId(REMINDER_KEY);
				return reminder;
			} else {
				return null;
			}
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
	}

	@Before
	public void setupMock() {
		Date today = new Date(System.currentTimeMillis());
		Date startDate = service.addDays(today, 15); // today + 15 days -> need a reminder if no forms submitted
		Date endDate =  service.addDays(today, 100);
		student = mock(Student.class);
		student = service.createStudent(STUDENT_KEY, 321332, "email", "firstName", "lastName",
				"password", Faculty.Education, 260, "major", "minor", "academicYear", null);
		employer = mock(Employer.class);
		employer = service.createEmployer(EMPLOYER_KEY, 123, "email", "firstName", "lastName",
				"password", "position", "company", "location");
		coop = mock(Coop.class);
		coop = service.createCoop(COOP_KEY, true, endDate, "jobDescription", 12, "location",
				true , Semester.Summer, startDate, student, employer);
		reminder = mock(Reminder.class);
		remindersSent = service.sendReminders();
		
	}

	@Test
	public void testReminderCreation() {
		assertNotNull(REMINDER_KEY);
	}
	
	@Test
	public void testSendReminders() {
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
