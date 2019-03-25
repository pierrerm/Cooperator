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
import ca.mcgill.ecse321.cooperator.dao.PDFRepository;
import ca.mcgill.ecse321.cooperator.model.Coop;
import ca.mcgill.ecse321.cooperator.model.DocumentType;
import ca.mcgill.ecse321.cooperator.model.PDF;
import ca.mcgill.ecse321.cooperator.service.CooperatorService;

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
	@Mock
	private CoopRepository coopRepo;
	@InjectMocks
	private CooperatorService service;

	@InjectMocks
	private CooperatorRestController controller;
	
	private PDF pdf;
	private Coop coop;
	
	private static final int PDF_KEY = 50;
	private static final int INVALID_KEY = -50;
	
	@Before
	public void setMockOutput() {
		when(pdfDao.findPDFByDocId(anyInt())).thenAnswer((InvocationOnMock invocation)->{
			if (invocation.getArgument(0).equals(PDF_KEY)) {
				return pdf;
			} else {
				return null;
			}
		});
	}
	
	@Before
	public void setupMock() {
		coop = mock(Coop.class);
		coop = new Coop();
		pdf = mock(PDF.class);
		pdf = service.createPDF(PDF_KEY, "filePath", DocumentType.TaxForm, null, coop);

	}
	
	@Test
	public void testPDFCreation() {
		assertNotNull(PDF_KEY);
	}
	
	@Test
	public void testPDFQueryFound() {
		assertEquals(PDF_KEY, service.getPDF(PDF_KEY).getDocId());
		assertEquals("filePath", service.getPDF(PDF_KEY).getFilePath());
		assertEquals(DocumentType.TaxForm, service.getPDF(PDF_KEY).getDocumentType());
		assertEquals(null, service.getPDF(PDF_KEY).getSubmissionDate());
		assertEquals(coop, service.getPDF(PDF_KEY).getCoop());
	}
	
	@Test
	public void testPDFQueryNotFound() {
		assertNull(service.getPDF(INVALID_KEY));
	}
	
	@Test
	public void testGetAllPDFs() {
		assertNotNull(service.getAllPDFs());
	}
	
}
