import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
	baseURL: backendUrl,
	headers: { 'Access-Control-Allow-Origin': frontendUrl }
})



//Constructor menthods
function AdministratorDto (phone, firstName, lastName, email, password, userId, faculty, id, student){
	this.phone = phone
	this.firstName = firstName
	this.lastName = lastName
	this.email = email
	this.password = password
	this.userId = userId
	this.faculty = faculty
	this.id = id
	this.student = student
}

function CoopDto (coopId, employerConfirmation, endDate, jobDescription, jobId, location, needWorkPermit, semester, startDate, studentId, employerId, PDFIds, formIds, reminderIds) {
	this.coopId = coopId;
	this.employerConfirmation = employerConfirmation;
	this.endDate = endDate;
	this.jobDescription = jobDescription;
	this.jobId = jobId;
	this.location = location;
	this.needWorkPermit = needWorkPermit;
	this.semester = semester;
	this.startDate = startDate;
	this.studentId = studentId;
	this.employerId = employerId;
	this.PDFIds = PDFIds;
	this.formIds = formIds;
	this.reminderIds = reminderIds;
}

function EmployerDto(userId, phone, firstName, lastName, email, password, position, company, location) {
	this.phone = phone;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.password = password;
	this.userId = userId;
	this.position = position;
	this.company = company;
	this.location = location;
}

//Acceptance form
function FormDto(formId, submissionDate, coopId) {
	this.formId = formId;
	this.submissionDate = submissionDate;
	this.coopId = coopId;
}

//Coop evaluation
function FormDto(formId, submissionDate, coopId, employerEvaluation, softwareTechnologies, usefulCourses, studentWorkExperience) {
	this.formId = formId;
	this.submissionDate = submissionDate;
	this.coopId = coopId;
	this.employerEvaluation = employerEvaluation;
	this.softwareTechnologies = softwareTechnologies;
	this.usefulCourses = usefulCourses;
	this.studentWorkExperience = studentWorkExperience;
}

//Student Evaluation
function FormDto(formId, submissionDate, coopId, studentWorkExperience, studentPerformance) {
	this.formId = formId;
	this.submissionDate = submissionDate;
	this.coopId = coopId;
	this.studentPerformance = studentPerformance;
	this.studentWorkExperience = studentWorkExperience;
}

//Tasks workload report
function FormDto(formId, submissionDate, coopId, tasks, hoursPerWeek, wage, training) {
	this.formId = formId;
	this.submissionDate = submissionDate;
	this.coopId = coopId;
	this.tasks = tasks;
	this.hoursPerWeek = hoursPerWeek;
	this.wage = wage;
	this.training = training;
}

function FormDto(formId, submissionDate, coopId, workExperience, employerEvaluation, softwareTechnologies, usefulCourses, studentPerformance, tasks, hoursPerWeek, wage, training) {
	this.formId = formId;
	this.submissionDate = submissionDate;
	this.coopId = coopId;
	this.employerEvaluation = employerEvaluation;
	this.softwareTechnologies = softwareTechnologies;
	this.usefulCourses = usefulCourses;
	this.studentPerformance = studentPerformance;
	this.tasks = tasks;
	this.hoursPerWeek = hoursPerWeek;
	this.wage = wage;
	this.training = training;
}

function PDFDto(docId, filePath, coop, documentType, submissionDate) {
	this.docId = docId;
	this.filePath = filePath;
	this.coop = coop;
	this.documentType = documentType;
	this.submissionDate = submissionDate;
}

function ReminderDto(reminderId, subject, date, deadLine, description, urgency, coop) {
	this.reminderId = reminderId;
	this.subject = subject;
	this.date = date;
	this.deadLine = deadLine;
	this.description = description;
	this.urgency = urgency;
	this.coop = coop;
}

function StudentDto(phone, firstName, lastName, email, password, userId, id, academicYear, major, minor, administrator, faculty, coop) {
	this.phone = phone;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.password = password;
	this.userId = userId;
	this.id = id;
	this.academicYear = academicYear;
	this.major = major;
	this.minor = minor;
	this.administrator = administrator;
	this.faculty = faculty;
	this.coop = coop;
}


created: function () {
  // Test data
  const p1 = new PersonDto('John')
  const p2 = new PersonDto('Jill')
  // Sample initial content
  this.people = [p1, p2]
},
Add event handling method createPerson():

methods: {
  createPerson: function (personName) {
    // Create a new person and add it to the list of people
    var p = new PersonDto(personName)
    this.people.push(p)
    // Reset the name field for new people
    this.newPerson = ''
  }
}
