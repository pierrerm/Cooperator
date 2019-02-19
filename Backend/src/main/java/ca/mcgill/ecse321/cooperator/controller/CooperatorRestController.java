package ca.mcgill.ecse321.cooperator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.cooperator.dto.StudentDto;
import ca.mcgill.ecse321.cooperator.model.Faculty;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.service.CooperatorService;

@CrossOrigin(origins = "*")
@RestController
public class CooperatorRestController {

	@Autowired
	private CooperatorService service;

	@PostMapping(value = {
			"/student/{phone}/{firstName}/{lastName}/{email}/{password}/{userId}/{id}/{academicYear}/{major}/{minor}",
			"/student/{phone}/{firstName}/{lastName}/{email}/{password}/{userId}/{id}/{academicYear}/{major}/{minor}/" })
	public StudentDto createStudent(@PathVariable("phone") long phone, @PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName, @PathVariable("email") String email,
			@PathVariable("password") String password, @PathVariable("userId") int userId, @PathVariable("id") int id,
			@PathVariable("academicYear") String academicYear, @PathVariable("major") String major,
			@PathVariable("minor") String minor) throws IllegalArgumentException {
		//@formatter:on
		Student student = service.createStudent(userId, phone, email, firstName, lastName, password,
				Faculty.Engineering, id, major, minor, academicYear, null);
		return convertToDto(student);
	}
	
	@GetMapping(value = { "/students", "/students/" })
	public List<StudentDto> getAllStudentss() {
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

}
