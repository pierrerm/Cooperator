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
public class TestGroup19RESTServices {
	
	@Test
	public void testQueryServices() {
		
		try {
			//get all coops
			URL url1 = new URL(
					"https://cooperator-backend-260.herokuapp.com/allCoops");
			HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
			conn1.setRequestMethod("GET");
			assertEquals(200, conn1.getResponseCode());
			conn1.disconnect();
			
			//get all current coops
			URL url2 = new URL(
					"https://cooperator-backend-260.herokuapp.com/allCurrentCoops");
			HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
			conn2.setRequestMethod("GET");
			assertEquals(200, conn2.getResponseCode());
			conn2.disconnect();
			
			//get list of ranked courses
			URL url3 = new URL(
					"https://cooperator-backend-260.herokuapp.com/ranking");
			HttpURLConnection conn3 = (HttpURLConnection) url3.openConnection();
			conn3.setRequestMethod("GET");
			assertEquals(200, conn3.getResponseCode());
			conn3.disconnect();
			
			//get all courses
			URL url4 = new URL(
					"https://cooperator-backend-260.herokuapp.com/allCourses");
			HttpURLConnection conn4 = (HttpURLConnection) url4.openConnection();
			conn4.setRequestMethod("GET");
			assertEquals(200, conn4.getResponseCode());
			conn4.disconnect();
			
			//get problematic students
			URL url5 = new URL(
					"https://cooperator-backend-260.herokuapp.com/problematicStudents");
			HttpURLConnection conn5 = (HttpURLConnection) url5.openConnection();
			conn5.setRequestMethod("GET");
			assertEquals(200, conn4.getResponseCode());
			conn5.disconnect();
			
			//get all students
			URL url6 = new URL(
					"https://cooperator-backend-260.herokuapp.com/allStudents");
			HttpURLConnection conn6 = (HttpURLConnection) url6.openConnection();
			conn6.setRequestMethod("GET");
			assertEquals(200, conn6.getResponseCode());
			conn6.disconnect();

			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
