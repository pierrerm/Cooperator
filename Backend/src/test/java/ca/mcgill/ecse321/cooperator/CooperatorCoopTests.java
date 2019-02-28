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
public class CooperatorCoopTests {
	@Test
	public void testJUnit4() {
		
	}
	
	@Mock
	private CoopRepository coopDao;
	
	@InjectMocks
	private CooperatorService service;
	@InjectMocks
	private CooperatorRestController controller;
	
	private Coop coop;
	
	private static final int VALID_COOP_KEY = 1;
	private static final int INVALID_COOP_KEY = -1;

	@Before
	public void setMockOutput() {
		when(coopDao.findCoopByCoopId(anyInt())).thenAnswer((InvocationOnMock invocation)->{
			if (invocation.getArgument(0).equals(VALID_COOP_KEY)) {
				Coop coop = new Coop();
				coop.setCoopId(VALID_COOP_KEY);
				return coop;
			} else {
				return null;
			}
		});
	}
	
	@Before
	public void setupMock() {
		coop = mock(Coop.class);
	}
	
	@Test
	public void testCoopCreation() {
		assertNotNull(coop);
	}
	
	@Test
	public void testCoopQueryFound() {
		assertEquals(VALID_COOP_KEY, service.getCoop(VALID_COOP_KEY).getCoopId());
	}
	
	@Test
	public void testCoopQueryNotFound() {
		assertNull(service.getCoop(INVALID_COOP_KEY));
	}
}
