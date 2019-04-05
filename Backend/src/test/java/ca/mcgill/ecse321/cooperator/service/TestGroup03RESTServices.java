/**
 * 
 */
package ca.mcgill.ecse321.cooperator.service;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author anudr
 *
 */
public class TestGroup03RESTServices {
	
	@Test
	public void testQueryServices() {

		try {
			//get all students
			URL url1 = new URL(
					"https://sturegistration-backend-009b01.herokuapp.com/allStudents");
			HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
			conn1.setRequestMethod("GET");
			assertEquals(200, conn1.getResponseCode());
			conn1.disconnect();
			
			//get all users
			URL url2 = new URL(
					"https://sturegistration-backend-009b01.herokuapp.com/allUsers");
			HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
			conn2.setRequestMethod("GET");
			assertEquals(200, conn2.getResponseCode());
			conn2.disconnect();
			
			//get all coop positions
			URL url3 = new URL(
					"https://sturegistration-backend-009b01.herokuapp.com/allCoopPositions");
			HttpURLConnection conn3 = (HttpURLConnection) url3.openConnection();
			conn3.setRequestMethod("GET");
			assertEquals(200, conn3.getResponseCode());
			conn3.disconnect();
			
			//get all coop courses
			URL url4 = new URL(
					"https://sturegistration-backend-009b01.herokuapp.com/allCoopCourses");
			HttpURLConnection conn4 = (HttpURLConnection) url4.openConnection();
			conn4.setRequestMethod("GET");
			assertEquals(200, conn4.getResponseCode());
			conn4.disconnect();
			
			//get all evaluation forms
			URL url5 = new URL(
					"https://sturegistration-backend-009b01.herokuapp.com/allEvaluationForms");
			HttpURLConnection conn5 = (HttpURLConnection) url5.openConnection();
			conn5.setRequestMethod("GET");
			assertEquals(200, conn5.getResponseCode());
			conn5.disconnect();

			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
