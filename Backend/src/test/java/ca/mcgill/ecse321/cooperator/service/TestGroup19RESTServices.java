/**
 * 
 */
package ca.mcgill.ecse321.cooperator.service;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author anudr
 *
 */
public class TestGroup19RESTServices {
	
	@Test
	public void testQueryServices() {
		
		try {
			//get all coops
//			URL url1 = new URL(
//					"https://cooperator-backend-260.herokuapp.com/coops");
//			HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
//			conn1.setRequestMethod("GET");
//			assertEquals(200, conn1.getResponseCode());
//			conn1.disconnect();
//			
//			//get all courses
//			URL url2 = new URL(
//					"https://cooperator-backend-260.herokuapp.com/courses");
//			HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
//			conn2.setRequestMethod("GET");
//			assertEquals(200, conn2.getResponseCode());
//			conn2.disconnect();
//			
//			//get list of ranked courses
//			URL url3 = new URL(
//					"https://cooperator-backend-260.herokuapp.com/ranking");
//			HttpURLConnection conn3 = (HttpURLConnection) url3.openConnection();
//			conn3.setRequestMethod("GET");
//			assertEquals(200, conn3.getResponseCode());
//			conn3.disconnect();

			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
