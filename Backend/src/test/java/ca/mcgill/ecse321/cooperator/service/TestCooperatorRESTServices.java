package ca.mcgill.ecse321.cooperator.service;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.model.Employer;

public class TestCooperatorRESTServices {
	
	@Test
	public void testStudentCreation() {
		try {
			URL url = new URL("http://cooperator-backend-3417.herokuapp.com/student/438/TestStudentFirstName/TestStudentLastName/a@gmail.com/password/-1/260710646/U2/compEng/eng");
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
			URL url = new URL("http://cooperator-backend-3417.herokuapp.com/student/438/TestStudentFirstName/TestStudentLastName/a@gmail.com/password/-2/260710646/U2/compEng/eng");
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
			URL url = new URL("http://cooperator-backend-3417.herokuapp.com/student/438/TestStudentFirstName/TestStudentLastName/a@gmail.com/password/-3/260710646/U2/compEng/eng");
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
					"http://cooperator-backend-3417.herokuapp.com/form/acceptanceForm/-10/02-03-2016/-4");
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
			URL url = new URL("http://cooperator-backend-3417.herokuapp.com/student/438/TestStudentFirstName/TestStudentLastName/a@gmail.com/password/-3/260710646/U2/compEng/eng");
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
			URL url = new URL("http://cooperator-backend-3417.herokuapp.com/student/438/TestStudentFirstName/TestStudentLastName/a@gmail.com/password/-3/260710646/U2/compEng/eng");
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
			URL url = new URL("http://cooperator-backend-3417.herokuapp.com/student/438/TestStudentFirstName/TestStudentLastName/a@gmail.com/password/-3/260710646/U2/compEng/eng");
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
			URL url = new URL("http://cooperator-backend-3417.herokuapp.com/student/438/TestStudentFirstName/TestStudentLastName/a@gmail.com/password/-6/260710646/U2/compEng/eng");
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

			//Send Reminders to problematic students
			URL urlR = new URL(
					"http://cooperator-backend-3417.herokuapp.com/reminders/send");
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
	
//	@Test
//	public void testGetStudentForms() {
//		try {
//			URL url = new URL("http://cooperator-backend-3417.herokuapp.com/forms/student/-1/winter/2018");
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			assertEquals(200, conn.getResponseCode());
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testGetEmployerForms() {
//		try {
//			URL url = new URL("http://cooperator-backend-3417.herokuapp.com/forms/employer/-1/winter/2018");
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			assertEquals(200, conn.getResponseCode());
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void testEditForm() {
		try {
			URL url = new URL("http://cooperator-backend-3417.herokuapp.com/student/438/TestStudentFirstName/TestStudentLastName/a@gmail.com/password/-3/260710646/U2/compEng/eng");
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
			conn.disconnect();
			connE.disconnect();
			connC.disconnect();
			URL urlF = new URL(
					"http://cooperator-backend-3417.herokuapp.com/form/studentEvaluation/-12/02-03-2016/5/work/-4");
			HttpURLConnection connF = (HttpURLConnection) urlF.openConnection();
			connF.setRequestMethod("POST");
			assertEquals(200, connF.getResponseCode());
			connF.disconnect();
			URL urlF2 = new URL(
					"http://cooperator-backend-3417.herokuapp.com/form/-12/studentEvaluation/workExperience/experience");
			HttpURLConnection connF2 = (HttpURLConnection) urlF2.openConnection();
			connF2.setRequestMethod("PUT");
			System.out.print("RESPONSE CODE: " + connF2.getResponseCode());
			assertEquals(200, connF2.getResponseCode());
			connF2.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
