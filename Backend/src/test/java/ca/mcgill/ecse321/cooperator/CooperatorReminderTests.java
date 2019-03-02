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
import java.util.List;

/**
 * @author anudr
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

	@InjectMocks
	private CooperatorService service;

	@InjectMocks
	private CooperatorRestController controller;

	private Reminder reminder;

	private static final int REMINDER_KEY = 60;
	private static final int INVALID_KEY = -60;

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
	}

	@Before
	public void setupMock() {
		reminder = mock(Reminder.class);
	}

	@Test
	public void testReminderCreation() {
		assertNotNull(REMINDER_KEY);
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
	public void testSendReminders() {
		assertEquals(0, service.getAllReminders().size());

		Employer employer;
		Administrator admin;
		Student student;
		Coop coop;

		int coopId = 1;
		int jobId = 1;
		boolean employerConfirmation = true;

		String jobDescription = "Java";
		String location = "Montreal";
		boolean needWorkPermit = true;
		Semester semester = Semester.Fall;
		Date today = new Date(System.currentTimeMillis()); // return today's date
		Date startDate = service.addDays(today, 15); // today + 15 days -> need a reminder if no forms submitted
		Date endDate = service.addDays(today, 100);

		try {
			employer = mock(Employer.class);
			employer = service.createEmployer(1, 1, "google@gmail.com", "Bob", "Bobby", "password", "Google",
					"Montreal", "HR");
			admin = mock(Administrator.class);
			admin = service.createAdministrator(2, 1, "@gmail.com", "Robert", "njdnfs", "password123",
					Faculty.Engineering, 260147532);
			student = mock(Student.class);
			student = service.createStudent(3, 1, "@gmail.com", "Ngolo", "Kanté", "password",
					Faculty.Engineering, 260148654, "Software", "", "U2", admin);
			coop = mock(Coop.class);
			coop = service.createCoop(coopId, employerConfirmation, endDate, jobDescription, jobId, location, needWorkPermit,
					semester, startDate, student, employer);
			service.sendReminders();
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Reminder> allReminders = service.getAllReminders();
		List<Student> everyStudents = service.getAllStudents();
		assertEquals(everyStudents.size(), allReminders.size());

	}

}
