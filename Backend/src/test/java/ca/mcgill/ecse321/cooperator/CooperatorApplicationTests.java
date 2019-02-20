package ca.mcgill.ecse321.cooperator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.test.context.junit4.SpringRunner;
import ca.mcgill.ecse321.cooperator.controller.CooperatorRestController;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.service.CooperatorService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperatorApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Mock
	private StudentRepository studentDao;
	
	@InjectMocks
	private CooperatorService service;
	
	@Mock
	private CooperatorService serviceMock;

	@InjectMocks
	private CooperatorRestController controller;
	
	private Student student;
	private static final int STUDENT_KEY = 1;
	private static final int NONEXISTING_KEY = 0;
	
	@Before
	public void setMockOutput() {
	  when(studentDao.findStudentByUserId(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	    if(invocation.getArgument(0).equals(STUDENT_KEY)) {
	      Student student = new Student();
	      student.setUserId(STUDENT_KEY);
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
	public void testParticipantQueryFound() {
	  assertEquals(STUDENT_KEY, service.getStudent(STUDENT_KEY).getUserId());
	}

}

