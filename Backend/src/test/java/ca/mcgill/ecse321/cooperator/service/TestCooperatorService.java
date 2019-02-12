package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.dao.FormRepository;
import ca.mcgill.ecse321.cooperator.dao.ReminderRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.dao.DownloadableDocRepository;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.dao.AdministratorRepository;
import ca.mcgill.ecse321.cooperator.dao.CoopRepository;
import ca.mcgill.ecse321.cooperator.model.Form;
import ca.mcgill.ecse321.cooperator.model.Coop;
import ca.mcgill.ecse321.cooperator.model.DocumentType;
import ca.mcgill.ecse321.cooperator.model.Reminder;
import ca.mcgill.ecse321.cooperator.model.Semester;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.DownloadableDoc;
import ca.mcgill.ecse321.cooperator.model.Employer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCooperatorService {
	
	@Autowired
	private CooperatorService service;
	
	@Autowired
	private CoopRepository coopRepository;
	@Autowired
	private FormRepository formRepository;
	@Autowired
	private ReminderRepository reminderRepository;
	@Autowired
	private DownloadableDocRepository downloadableDocRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private AdministratorRepository administratorRepository;
	@Autowired
	private EmployerRepository employerRepository;
	
	@After
	public void clearDatabase() {
		try {
		coopRepository.deleteAll();
		formRepository.deleteAll();
		reminderRepository.deleteAll();
		downloadableDocRepository.deleteAll();
		}
		catch(NullPointerException e){
			
		}
	}
	
//	@Test
//	public void testCreateCoop() {
//		assertEquals(0, service.getAllCoops().size());
//
//		String year = "1998";
//		String location = "Montreal";
//		String jobDescription = "Cool Story Bro";
//		Boolean needWorkPermit = true;
//		Boolean employerConfirmation = false;
//		int jobId = 1;
//		Semester semester = Semester.Fall;
//		Date startDate = null;
//		Date endDate = null;
//		Employer employer = new Employer();
//		employer.setUserId(1);
//
//		try {
//			service.createCoop(year, location, jobDescription, needWorkPermit, employerConfirmation, jobId, semester, startDate, endDate, employer);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//
//		List<Coop> allCoops = service.getAllCoops();
//
//		assertEquals(1, allCoops.size());
//		assertEquals(jobId, allCoops.get(0).getJobId());
//	}
	
	@Test
	public void testCreateDownloadableDoc() {
		assertEquals(0, service.getAllDownloadableDocs().size());

		int docId = 1;
		String filePath = "Heyo";
		DocumentType docType = DocumentType.CoopPlacementProof;

		try {
			service.createDownloadableDoc(docId, filePath, docType);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<DownloadableDoc> allDownloadableDocs = service.getAllDownloadableDocs();

		assertEquals(1, allDownloadableDocs.size());
		assertEquals(docId, allDownloadableDocs.get(0).getDocId());
	}
	
}
