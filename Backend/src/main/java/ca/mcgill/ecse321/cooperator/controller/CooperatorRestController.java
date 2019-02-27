package ca.mcgill.ecse321.cooperator.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.cooperator.dto.CoopDto;
import ca.mcgill.ecse321.cooperator.dto.EmployerDto;
import ca.mcgill.ecse321.cooperator.dto.FormDto;
import ca.mcgill.ecse321.cooperator.dto.StudentDto;
import ca.mcgill.ecse321.cooperator.model.AcceptanceForm;
import ca.mcgill.ecse321.cooperator.model.Coop;
import ca.mcgill.ecse321.cooperator.model.CoopEvaluation;
import ca.mcgill.ecse321.cooperator.model.Employer;
import ca.mcgill.ecse321.cooperator.model.Faculty;
import ca.mcgill.ecse321.cooperator.model.Semester;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.model.StudentEvaluation;
import ca.mcgill.ecse321.cooperator.model.TasksWorkloadReport;
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
	
	// Student
		@PostMapping(value = {
				"/s",
				"/s/" })
		public StudentDto createStudent2(@RequestParam long phone, @RequestParam String firstName,
				@RequestParam String lastName, @RequestParam String email,
				@RequestParam String password, @RequestParam int userId, @RequestParam int id,
				@RequestParam String academicYear, @RequestParam String major, @RequestParam String minor
				) throws IllegalArgumentException {
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
		Employer employer = service.getEmployer(employerId);

		Semester semester = getSemester(semesterStr);

		Date endDate = new Date(createDate(endDateStr));
		Date startDate = new Date(createDate(startDateStr));

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
				c.getJobId(), c.getLocation(), c.getNeedWorkPermit(), c.getSemester(), c.getStartDate(),
				c.getStudent().getUserId(), c.getEmployer().getUserId());
		return coopDto;
	}

	// Employer
	@PostMapping(value = {
			"/employer/{phone}/{firstName}/{lastName}/{email}/{password}/{userId}/{position}/{company}/{location}",
			"/employer/{phone}/{firstName}/{lastName}/{email}/{password}/{userId}/{position}/{company}/{location}/" })
	public EmployerDto createEmployer(@PathVariable("phone") long phone, @PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName, @PathVariable("email") String email,
			@PathVariable("password") String password, @PathVariable("userId") int userId,
			@PathVariable("position") String position, @PathVariable("company") String company,
			@PathVariable("location") String location) throws IllegalArgumentException {
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
		EmployerDto employerDto = new EmployerDto(e.getUserId(), e.getPhone(), e.getEmail(), e.getFirstName(),
				e.getLastName(), e.getPassword(), e.getPosition(), e.getCompany(), e.getLocation());
		return employerDto;
	}

	// Form
	@PostMapping(value = {
			"/form/{type}/{formId}/{submissionDate}/{employerEvaluation}/{softwareTechnologies}/{usefulCourses}/{hoursPerWeek}/{tasks}/{training}/{wage}/{studentPerformance}/{studentWorkExperience}/{coopId}",
			"/form/{type}/{formId}/{submissionDate}/{employerEvaluation}/{softwareTechnologies}/{usefulCourses}/{hoursPerWeek}/{tasks}/{training}/{wage}/{studentPerformance}/{studentWorkExperience}/{coopId}/" })
	public FormDto createForm(@PathVariable("type") String type, @PathVariable("formId") int formId,
			@PathVariable("submissionDate") String submissionDateStr,
			@PathVariable("employerEvaluation") int employerEvaluation,
			@PathVariable("softwareTechnologies") String softwareTechnologies,
			@PathVariable("usefulCourses") String usefulCourses, @PathVariable("hoursPerWeek") int hoursPerWeek,
			@PathVariable("tasks") String tasks, @PathVariable("training") String training,
			@PathVariable("wage") int wage, @PathVariable("studentPerformance") int studentPerformance,
			@PathVariable("studentWorkExperience") String studentWorkExperience, @PathVariable("coopId") int coopId)
			throws IllegalArgumentException {
		// @formatter:on
		Coop coop = service.getCoop(coopId);
		Date submissionDate = new Date(createDate(submissionDateStr));

		switch (type.toLowerCase()) {
		case "acceptanceform":
			AcceptanceForm aForm = service.createAcceptanceForm(formId, submissionDate, coop);
			return convertToDto(aForm);
		case "coopevaluation":
			CoopEvaluation cForm = service.createCoopEvaluation(formId, submissionDate, studentWorkExperience,
					employerEvaluation, softwareTechnologies, usefulCourses, coop);
			return convertToDto(cForm);
		case "studentevaluation":
			StudentEvaluation sForm = service.createStudentEvaluation(formId, submissionDate, studentWorkExperience,
					studentPerformance, coop);
			return convertToDto(sForm);
		case "tasksworkloadreport":
			TasksWorkloadReport tForm = service.createTasksWorkloadReport(formId, submissionDate, tasks, hoursPerWeek,
					wage, training, coop);
			return convertToDto(tForm);
		}
		return null;
	}

//	@GetMapping(value = { "/forms", "/forms/" })
//	public List<FormDto> getAllFormss() {
//		List<FormDto> formDtos = new ArrayList<>();
//		for (Form form : service.getAllForms()) {
//			formDtos.add(convertToDto(form));
//		}
//		return formDtos;
//	}

	private FormDto convertToDto(AcceptanceForm f) {
		FormDto formDto = new FormDto(f.getFormId(), f.getSubmissionDate(), f.getCoop().getCoopId());
		return formDto;
	}

	private FormDto convertToDto(CoopEvaluation f) {
		FormDto formDto = new FormDto(f.getFormId(), f.getSubmissionDate(), f.getCoop().getCoopId(),
				f.getEmployerEvaluation(), f.getSoftwareTechnologies(), f.getUsefulCourses(), f.getWorkExperience());
		return formDto;
	}

	private FormDto convertToDto(StudentEvaluation f) {
		FormDto formDto = new FormDto(f.getFormId(), f.getSubmissionDate(), f.getCoop().getCoopId(),
				f.getStudentWorkExperience(), f.getStudentPerformance());
		return formDto;
	}

	private FormDto convertToDto(TasksWorkloadReport f) {
		FormDto formDto = new FormDto(f.getFormId(), f.getSubmissionDate(), f.getCoop().getCoopId(), f.getTasks(),
				f.getHoursPerWeek(), f.getWage(), f.getTraining());
		return formDto;
	}

	@GetMapping(value = { "/student/problem/{term}", "/student/problem/{term}" })
	public List<StudentDto> getAllStudentsWithFormError() {
		List<StudentDto> studentDtos = new ArrayList<>();
		for (Student student : service.getAllStudentsWithFormError()) {
			studentDtos.add(convertToDto(student));
		}
		return studentDtos;
	}

	public Semester getSemester(String input) {
		switch (input.toLowerCase()) {
		case "fall":
			return Semester.Fall;
		case "winter":
			return Semester.Winter;
		case "summer":
			return Semester.Summer;
		}
		return null;
	}

	public long createDate(String date) {
		java.util.Date dateFormat = null;
		try {
			dateFormat = new SimpleDateFormat("dd-MM-yyyy").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateFormat.getTime();
	}
}
