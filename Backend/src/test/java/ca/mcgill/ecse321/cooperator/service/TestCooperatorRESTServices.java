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
			URL urlC = new URL(
					"http://cooperator-backend-3417.herokuapp.com/coop/-3/true/02-02-2018/GreatJob/123/Montreal/false/winter/01-01-2018/-1/-1");
			HttpURLConnection connC = (HttpURLConnection) urlC.openConnection();
			connC.setRequestMethod("POST");
			assertEquals(200, connC.getResponseCode());
			connC.disconnect();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testAcceptanceFormCreation() {
		try {
			URL urlF = new URL(
					"http://cooperator-backend-3417.herokuapp.com/form/acceptanceForm/-10/02-03-2016/-3");
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
			URL urlF = new URL(
					"http://cooperator-backend-3417.herokuapp.com/form/coopEvaluation/-11/02-03-2016/workExperince/5/software/courses/-3");
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
			URL urlF = new URL(
					"http://cooperator-backend-3417.herokuapp.com/form/studentEvaluation/-12/02-03-2016/5/work/-3");
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
	
	
	
//	@Test 
//	public void testTasksWorkloadReportFormCreation() {
//		try {
//			URL urlF1 = new URL(
//					"http://cooperator-backend-3417.herokuapp.com/form/tasksWorkloadReport/-13/02-03-2016/40/tasks/training/20/-3/");
//			HttpURLConnection connF1 = (HttpURLConnection) urlF1.openConnection();
//			connF1.setRequestMethod("POST");
//			assertEquals(200, connF1.getResponseCode());
//			connF1.disconnect();
//
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void testSendReminders() {
//		try {
//
//			//Send Reminders to problematic students
//			URL url = new URL(
//					"http://cooperator-backend-3417.herokuapp.com/reminders/send");
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			conn.setRequestProperty("Accept", "application/json");
//			assertEquals(200, conn.getResponseCode());
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
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
//			URL url = new URL("http://cooperator-backend-3417.herokuapp.com/getStudentForms/-1/winter/2018");
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
//			URL url = new URL("http://cooperator-backend-3417.herokuapp.com/getEmployerForms/-1/winter/2018");
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			assertEquals(200, conn.getResponseCode());
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
