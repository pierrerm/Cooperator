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
public class CooperatorPDFTests {
	@Test
	public void testJUnit4() {
		
	}
	
	@Mock
	private PDFRepository pdfDao;
	@InjectMocks
	private CooperatorService service;

	@InjectMocks
	private CooperatorRestController controller;
	
	private PDF pdf;
	
	private static final int PDF_KEY = 50;
	private static final int INVALID_KEY = -50;
	
	@Before
	public void setMockOutput() {
		when(pdfDao.findPDFByDocId(anyInt())).thenAnswer((InvocationOnMock invocation)->{
			if (invocation.getArgument(0).equals(PDF_KEY)) {
				PDF pdf = new PDF();
				pdf.setDocId(PDF_KEY);
				return pdf;
			} else {
				return null;
			}
		});
	}
	
	@Before
	public void setupMock() {
		pdf = mock(PDF.class);

	}
	
	@Test
	public void testPDFCreation() {
		assertNotNull(PDF_KEY);
	}
	
	@Test
	public void testPDFQueryFound() {
		assertEquals(PDF_KEY, service.getPDF(PDF_KEY).getDocId());
	}
	
	@Test
	public void testPDFQueryNotFound() {
		assertNull(service.getPDF(INVALID_KEY));
	}
	
}
