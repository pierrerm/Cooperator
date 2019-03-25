package ca.mcgill.ecse321.cooperator.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class TestCooperatorRESTServices {

	@Test
	public void testStudentCreation() {
		try {
			URL url = new URL(
					"http://cooperator-backend-3417.herokuapp.com/student/438/TestStudentFirstName/TestStudentLastName/a@gmail.com/password/-1/260710646/U2/compEng/eng");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			assertEquals(200, conn.getResponseCode());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testEmployerCreation() {
		try {
			URL urlE = new URL(
					"http://cooperator-backend-3417.herokuapp.com/employer/438/TestEmployerFirst/TestEmployerLast/testing.employer@mail.mcgill.ca/password/-1/President/Mcgill/Montreal");
			HttpURLConnection connE = (HttpURLConnection) urlE.openConnection();
			connE.setRequestMethod("POST");
			assertEquals(200, connE.getResponseCode());
			connE.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCoopCreation() {
		try {
			URL url = new URL(
					"http://cooperator-backend-3417.herokuapp.com/student/438/TestStudentFirstName/TestStudentLastName/a@gmail.com/password/-2/260710646/U2/compEng/eng");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			assertEquals(200, conn.getResponseCode());
			URL urlE = new URL(
					"http://cooperator-backend-3417.herokuapp.com/employer/438/TestEmployerFirst/TestEmployerLast/testing.employer@mail.mcgill.ca/password/-2/President/Mcgill/Montreal");
			HttpURLConnection connE = (HttpURLConnection) urlE.openConnection();
			connE.setRequestMethod("POST");
			assertEquals(200, connE.getResponseCode());
			URL urlC = new URL(
					"http://cooperator-backend-3417.herokuapp.com/coop/-3/true/04-06-2019/GreatJob/123/Montreal/false/winter/25-2-2019/-2/-2");
			HttpURLConnection connC = (HttpURLConnection) urlC.openConnection();
			connC.setRequestMethod("POST");
			assertEquals(200, connC.getResponseCode());
			connC.disconnect();
			conn.disconnect();
			connE.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAcceptanceFormCreation() {
		try {
			URL url = new URL(
					"http://cooperator-backend-3417.herokuapp.com/student/438/TestStudentFirstName/TestStudentLastName/a@gmail.com/password/-3/260710646/U2/compEng/eng");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			assertEquals(200, conn.getResponseCode());
			URL urlE = new URL(
					"http://cooperator-backend-3417.herokuapp.com/employer/438/TestEmployerFirst/TestEmployerLast/testing.employer@mail.mcgill.ca/password/-3/President/Mcgill/Montreal");
			HttpURLConnection connE = (HttpURLConnection) urlE.openConnection();
			connE.setRequestMethod("POST");
			assertEquals(200, connE.getResponseCode());
			URL urlC = new URL(
					"http://cooperator-backend-3417.herokuapp.com/coop/-4/true/04-06-2019/GreatJob/123/Montreal/false/winter/25-2-2019/-3/-3");
			HttpURLConnection connC = (HttpURLConnection) urlC.openConnection();
			connC.setRequestMethod("POST");
			assertEquals(200, connC.getResponseCode());
			connC.disconnect();
			conn.disconnect();
			connE.disconnect();
			URL urlF = new URL("http://cooperator-backend-3417.herokuapp.com/form/acceptanceForm/-10/02-03-2016/-4");
			HttpURLConnection connF = (HttpURLConnection) urlF.openConnection();
			connF.setRequestMethod("POST");
			assertEquals(200, connF.getResponseCode());
			connF.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCoopEvaluationFormCreation() {
		try {
			URL url = new URL(
					"http://cooperator-backend-3417.herokuapp.com/student/438/TestStudentFirstName/TestStudentLastName/a@gmail.com/password/-3/260710646/U2/compEng/eng");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			assertEquals(200, conn.getResponseCode());
			URL urlE = new URL(
					"http://cooperator-backend-3417.herokuapp.com/employer/438/TestEmployerFirst/TestEmployerLast/testing.employer@mail.mcgill.ca/password/-3/President/Mcgill/Montreal");
			HttpURLConnection connE = (HttpURLConnection) urlE.openConnection();
			connE.setRequestMethod("POST");
			assertEquals(200, connE.getResponseCode());
			URL urlC = new URL(
					"http://cooperator-backend-3417.herokuapp.com/coop/-4/true/04-06-2019/GreatJob/123/Montreal/false/winter/25-2-2019/-3/-3");
			HttpURLConnection connC = (HttpURLConnection) urlC.openConnection();
			connC.setRequestMethod("POST");
			assertEquals(200, connC.getResponseCode());
			connC.disconnect();
			conn.disconnect();
			connE.disconnect();
			URL urlF = new URL(
					"http://cooperator-backend-3417.herokuapp.com/form/coopEvaluation/-11/02-03-2016/workExperince/5/software/courses/-4");
			HttpURLConnection connF = (HttpURLConnection) urlF.openConnection();
			connF.setRequestMethod("POST");
			assertEquals(200, connF.getResponseCode());
			connF.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testStudentEvaluationFormCreation() {
		try {
			URL url = new URL(
					"http://cooperator-backend-3417.herokuapp.com/student/438/TestStudentFirstName/TestStudentLastName/a@gmail.com/password/-3/260710646/U2/compEng/eng");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			assertEquals(200, conn.getResponseCode());
			URL urlE = new URL(
					"http://cooperator-backend-3417.herokuapp.com/employer/438/TestEmployerFirst/TestEmployerLast/testing.employer@mail.mcgill.ca/password/-3/President/Mcgill/Montreal");
			HttpURLConnection connE = (HttpURLConnection) urlE.openConnection();
			connE.setRequestMethod("POST");
			assertEquals(200, connE.getResponseCode());
			URL urlC = new URL(
					"http://cooperator-backend-3417.herokuapp.com/coop/-4/true/04-06-2019/GreatJob/123/Montreal/false/winter/25-2-2019/-3/-3");
			HttpURLConnection connC = (HttpURLConnection) urlC.openConnection();
			connC.setRequestMethod("POST");
			assertEquals(200, connC.getResponseCode());
			connC.disconnect();
			conn.disconnect();
			connE.disconnect();
			URL urlF = new URL(
					"http://cooperator-backend-3417.herokuapp.com/form/studentEvaluation/-12/02-03-2016/5/work/-4");
			HttpURLConnection connF = (HttpURLConnection) urlF.openConnection();
			connF.setRequestMethod("POST");
			assertEquals(200, connF.getResponseCode());
			connF.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testTasksWorkloadReportFormCreation() {
		try {
			URL url = new URL(
					"http://cooperator-backend-3417.herokuapp.com/student/438/TestStudentFirstName/TestStudentLastName/a@gmail.com/password/-3/260710646/U2/compEng/eng");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.getResponseCode();
			URL urlE = new URL(
					"http://cooperator-backend-3417.herokuapp.com/employer/438/TestEmployerFirst/TestEmployerLast/testing.employer@mail.mcgill.ca/password/-3/President/Mcgill/Montreal");
			HttpURLConnection connE = (HttpURLConnection) urlE.openConnection();
			connE.setRequestMethod("POST");
			connE.getResponseCode();
			URL urlC = new URL(
					"http://cooperator-backend-3417.herokuapp.com/coop/-4/true/04-06-2019/GreatJob/123/Montreal/false/winter/25-2-2019/-3/-3");
			HttpURLConnection connC = (HttpURLConnection) urlC.openConnection();
			connC.setRequestMethod("POST");
			connC.getResponseCode();
			connC.disconnect();
			conn.disconnect();
			connE.disconnect();
			URL urlF1 = new URL(
					"http://cooperator-backend-3417.herokuapp.com/form/tasksWorkloadReport/-13/02-03-2016/40/tasks/training/20/-4");
			HttpURLConnection connF1 = (HttpURLConnection) urlF1.openConnection();
			connF1.setRequestMethod("POST");
			assertEquals(200, connF1.getResponseCode());
			connF1.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSendReminders() {
		try {
			URL url = new URL(
					"http://cooperator-backend-3417.herokuapp.com/student/438/TestStudentFirstName/TestStudentLastName/a@gmail.com/password/-6/260710646/U2/compEng/eng");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.getResponseCode();
			URL urlE = new URL(
					"http://cooperator-backend-3417.herokuapp.com/employer/438/TestEmployerFirst/TestEmployerLast/testing.employer@mail.mcgill.ca/password/-3/President/Mcgill/Montreal");
			HttpURLConnection connE = (HttpURLConnection) urlE.openConnection();
			connE.setRequestMethod("POST");
			connE.getResponseCode();
			URL urlC = new URL(
					"http://cooperator-backend-3417.herokuapp.com/coop/-6/true/30-05-2019/GreatJob/123/Montreal/false/winter/25-02-2019/-6/-3");
			HttpURLConnection connC = (HttpURLConnection) urlC.openConnection();
			connC.setRequestMethod("POST");
			connC.getResponseCode();
			connC.disconnect();
			conn.disconnect();
			connE.disconnect();

			// Send Reminders to problematic students
			URL urlR = new URL("http://cooperator-backend-3417.herokuapp.com/reminders/send");
			HttpURLConnection connR = (HttpURLConnection) urlR.openConnection();
			connR.setRequestMethod("GET");
			assertEquals(200, connR.getResponseCode());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAllStudents() {
		try {
			URL url = new URL("http://cooperator-backend-3417.herokuapp.com/students");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			assertEquals(200, conn.getResponseCode());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAllCoops() {
		try {
			URL url = new URL("http://cooperator-backend-3417.herokuapp.com/coops");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			assertEquals(200, conn.getResponseCode());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAllEmployers() {
		try {
			URL url = new URL("http://cooperator-backend-3417.herokuapp.com/employers");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			assertEquals(200, conn.getResponseCode());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAllForms() {
		try {
			URL url = new URL("http://cooperator-backend-3417.herokuapp.com/forms");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			assertEquals(200, conn.getResponseCode());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetStudentForms() {
		try {
			URL urlF = new URL("http://cooperator-backend-3417.herokuapp.com/forms/student/-3/winter/2019");
			HttpURLConnection connF = (HttpURLConnection) urlF.openConnection();
			connF.setRequestMethod("GET");
			assertEquals(200, connF.getResponseCode());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetEmployerForms() {
		try {
			URL url = new URL("http://cooperator-backend-3417.herokuapp.com/forms/employer/-3/winter/2019");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			assertEquals(200, conn.getResponseCode());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEditForm() {
		try {

			URL url = new URL(
					"http://cooperator-backend-3417.herokuapp.com/student/438/TestStudentFirstName/TestStudentLastName/a@gmail.com/password/-3/260710646/U2/compEng/eng");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			assertEquals(200, conn.getResponseCode());
			URL urlE = new URL(
					"http://cooperator-backend-3417.herokuapp.com/employer/438/TestEmployerFirst/TestEmployerLast/testing.employer@mail.mcgill.ca/password/-3/President/Mcgill/Montreal");
			HttpURLConnection connE = (HttpURLConnection) urlE.openConnection();
			connE.setRequestMethod("POST");
			assertEquals(200, connE.getResponseCode());
			URL urlC = new URL(
					"http://cooperator-backend-3417.herokuapp.com/coop/-4/true/04-06-2019/GreatJob/123/Montreal/false/winter/25-2-2019/-3/-3");
			HttpURLConnection connC = (HttpURLConnection) urlC.openConnection();
			connC.setRequestMethod("POST");
			assertEquals(200, connC.getResponseCode());
			connC.disconnect();
			conn.disconnect();
			connE.disconnect();

//			URL urlF1 = new URL(
//					"http://cooperator-backend-3417.herokuapp.com/form/acceptanceForm/-10/02-03-2016/-4");
//			HttpURLConnection connF1 = (HttpURLConnection) urlF1.openConnection();
//			connF1.setRequestMethod("POST");
//			assertEquals(200, connF1.getResponseCode());
//			connF1.disconnect();
//			URL urlF2 = new URL(
//					"http://cooperator-backend-3417.herokuapp.com/form/-10/acceptanceForm/submissionDate/03-03-2016");
//			HttpURLConnection connF2 = (HttpURLConnection) urlF2.openConnection();
//			connF2.setRequestMethod("PUT");
//			assertEquals(200, connF2.getResponseCode());
//			connF2.disconnect();

//			URL urlF3 = new URL(
//					"http://cooperator-backend-3417.herokuapp.com/form/coopEvaluation/-11/02-03-2016/workExperince/5/software/courses/-4");
//			HttpURLConnection connF3 = (HttpURLConnection) urlF3.openConnection();
//			connF3.setRequestMethod("POST");
//			assertEquals(200, connF3.getResponseCode());
//			connF3.disconnect();
//			URL urlF4 = new URL(
//					"http://cooperator-backend-3417.herokuapp.com/form/-11/coopEvaluation/employerEvaluation/7");
//			HttpURLConnection connF4 = (HttpURLConnection) urlF4.openConnection();
//			connF4.setRequestMethod("PUT");
//			assertEquals(200, connF4.getResponseCode());
//			connF4.disconnect(); 

			URL urlF5 = new URL(
					"http://cooperator-backend-3417.herokuapp.com/form/studentEvaluation/-12/02-03-2016/5/work/-4");
			HttpURLConnection connF5 = (HttpURLConnection) urlF5.openConnection();
			connF5.setRequestMethod("POST");
			assertEquals(200, connF5.getResponseCode());
			connF5.disconnect();
			URL urlF6 = new URL(
					"http://cooperator-backend-3417.herokuapp.com/form/-12/studentEvaluation/studentWorkExperience/experience");
			HttpURLConnection connF6 = (HttpURLConnection) urlF6.openConnection();
			connF6.setRequestMethod("PUT");
			assertEquals(200, connF6.getResponseCode());
			connF6.disconnect();

			URL urlF7 = new URL(
					"http://cooperator-backend-3417.herokuapp.com/form/tasksWorkloadReport/-13/02-03-2016/40/tasks/training/20/-4");
			HttpURLConnection connF7 = (HttpURLConnection) urlF7.openConnection();
			connF7.setRequestMethod("POST");
			assertEquals(200, connF7.getResponseCode());
			connF7.disconnect();
			URL urlF8 = new URL(
					"http://cooperator-backend-3417.herokuapp.com/form/-13/tasksWorkloadReport/training/none");
			HttpURLConnection connF8 = (HttpURLConnection) urlF8.openConnection();
			connF8.setRequestMethod("PUT");
			assertEquals(200, connF8.getResponseCode());
			connF8.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
