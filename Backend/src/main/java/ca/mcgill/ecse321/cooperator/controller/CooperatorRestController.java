package ca.mcgill.ecse321.cooperator.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.cooperator.dto.CoopDto;
import ca.mcgill.ecse321.cooperator.dto.EmployerDto;
import ca.mcgill.ecse321.cooperator.dto.StudentDto;
import ca.mcgill.ecse321.cooperator.model.Coop;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Faculty;
import ca.mcgill.ecse321.cooperator.model.Semester;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.service.CooperatorService;

@CrossOrigin(origins = "*")
@RestController
public class CooperatorRestController {

	@Autowired
	private CooperatorService service;

	// Student
	@PostMapping(value = {
			"/student/{phone}/{firstName}/{lastName}/{email}/{password}/{userId}/{id}/{academicYear}/{major}/{minor}",
			"/student/{phone}/{firstName}/{lastName}/{email}/{password}/{userId}/{id}/{academicYear}/{major}/{minor}/" })
	public StudentDto createStudent(@PathVariable("phone") long phone, @PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName, @PathVariable("email") String email,
			@PathVariable("password") String password, @PathVariable("userId") int userId, @PathVariable("id") int id,
			@PathVariable("academicYear") String academicYear, @PathVariable("major") String major,
			@PathVariable("minor") String minor) throws IllegalArgumentException {
		// @formatter:on
		Student student = service.createStudent(userId, phone, email, firstName, lastName, password,
				Faculty.Engineering, id, major, minor, academicYear, null);
		return convertToDto(student);
	}

	@GetMapping(value = { "/students", "/students/" })
	public List<StudentDto> getAllStudents() {
		List<StudentDto> studentDtos = new ArrayList<>();
		for (Student student : service.getAllStudents()) {
			studentDtos.add(convertToDto(student));
		}
		return studentDtos;
	}

	private StudentDto convertToDto(Student s) {
		StudentDto studentDto = new StudentDto(s.getPhone(), s.getFirstName(), s.getLastName(), s.getEmail(),
				s.getPassword(), s.getUserId(), s.getId(), s.getAcademicYear(), s.getMajor(), s.getMinor(), null, null,
				null);
		return studentDto;
	}

	// Coop
	@PostMapping(value = {
			"/coop/{coopId}/{employerConfirmation}/{endDate}/{jobDescription}/{jobId}/{location}/{needWorkPermit}/{semester}/{startDate}/{studentId}/{employerId}",
			"/coop/{coopId}/{employerConfirmation}/{endDate}/{jobDescription}/{jobId}/{location}/{needWorkPermit}/{semester}/{startDate}/{studentId}/{employerId}/" })
	public CoopDto createCoop(@PathVariable("coopId") int coopId,
			@PathVariable("employerConfirmation") boolean employerConfirmation,
			@PathVariable("endDate") String endDateStr, @PathVariable("jobDescription") String jobDescription,
			@PathVariable("jobId") int jobId, @PathVariable("location") String location,
			@PathVariable("needWorkPermit") boolean needWorkPermit, @PathVariable("semester") String semesterStr,
			@PathVariable("startDate") String startDateStr, @PathVariable("studentId") int studentId,
			@PathVariable("employerId") int employerId) throws IllegalArgumentException {
		// @formatter:on
		Student student = service.getStudent(studentId);
		Employer employer = null;
		try {
			employer = service.getEmployer(employerId);
		} catch (IllegalArgumentException e) {
		}
		Semester semester = Semester.Fall;

		Date endDate = new Date(1, 1, 2018);
		Date startDate = new Date(2, 2, 2018);

		Coop coop = service.createCoop(coopId, employerConfirmation, endDate, jobDescription, jobId, location,
				needWorkPermit, semester, startDate, student, employer);
		return convertToDto(coop);
	}

	@GetMapping(value = { "/coops", "/coops/" })
	public List<CoopDto> getAllCoops() {
		List<CoopDto> coopDtos = new ArrayList<>();
		for (Coop coop : service.getAllCoops()) {
			coopDtos.add(convertToDto(coop));
		}
		return coopDtos;
	}

	private CoopDto convertToDto(Coop c) {
		CoopDto coopDto = new CoopDto(c.getCoopId(), c.getEmployerConfirmation(), c.getEndDate(), c.getJobDescription(),
				c.getJobId(), c.getLocation(), c.getNeedWorkPermit(), c.getSemester(), c.getStartDate(), c.getStudent(),
				c.getEmployer());
		return coopDto;
	}

	// Employer
	@PostMapping(value = {
			"/employer/{phone}/{firstName}/{lastName}/{email}/{password}/{userId}/{position}/{company}/{location}",
			"/employer/{phone}/{firstName}/{lastName}/{email}/{password}/{userId}/{position}/{company}/{location}/" })
	public EmployerDto createEmployer(@PathVariable("phone") long phone, @PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName, @PathVariable("email") String email,
			@PathVariable("password") String password, @PathVariable("userId") int userId,
			@PathVariable("position") String position, @PathVariable("company") String company, @PathVariable("location") String location)
			throws IllegalArgumentException {
		// @formatter:on
		Employer employer = service.createEmployer(userId, phone, email, firstName, lastName, password, position,
				company, location);
		return convertToDto(employer);
	}

	@GetMapping(value = { "/employers", "/employers/" })
	public List<EmployerDto> getAllEmployers() {
		List<EmployerDto> employerDtos = new ArrayList<>();
		for (Employer employer : service.getAllEmployers()) {
			employerDtos.add(convertToDto(employer));
		}
		return employerDtos;
	}

	private EmployerDto convertToDto(Employer e) {
		EmployerDto employerDto = new EmployerDto(e.getUserId(), e.getPhone(), e.getEmail(), e.getFirstName(), e.getLastName(), e.getPassword(), e.getPosition(), e.getCompany(), e.getLocation());
		return employerDto;
	}
	
	@GetMapping(value = { "/student/problem/{term}", "/student/problem/{term}" })
	public List<StudentDto> getAllStudentsWithFormError() {
		List<StudentDto> studentDtos = new ArrayList<>();
		for (Student student : service.getAllStudentsWithFormError()) {
			studentDtos.add(convertToDto(student));
		}
		return studentDtos;
	}
}
