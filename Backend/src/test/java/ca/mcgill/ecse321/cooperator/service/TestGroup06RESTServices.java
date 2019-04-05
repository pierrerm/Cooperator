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
public class TestGroup06RESTServices {

	@Test
	public void testQueryServices() {
		
		try {
			//get all employers
			URL url1 = new URL(
					"https://cooperator-backend-060606.herokuapp.com/employers");
			HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
			conn1.setRequestMethod("GET");
			assertEquals(200, conn1.getResponseCode());
			conn1.disconnect();
			
			//get all events
			URL url2 = new URL(
					"https://cooperator-backend-060606.herokuapp.com/events");
			HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
			conn2.setRequestMethod("GET");
			assertEquals(200, conn2.getResponseCode());
			conn2.disconnect();

			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
