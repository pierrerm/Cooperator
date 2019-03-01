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
		when(reminderDao.findReminderByReminderId(anyInt())).thenAnswer((InvocationOnMock invocation)->{
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

}
