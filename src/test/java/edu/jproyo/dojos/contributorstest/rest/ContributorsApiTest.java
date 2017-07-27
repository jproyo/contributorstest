package edu.jproyo.dojos.contributorstest.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import edu.jproyo.dojos.contributorstest.Application;
import edu.jproyo.dojos.contributorstest.Security;
import edu.jproyo.dojos.contributorstest.model.Contributor;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, Security.class},  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContributorsApiTest {

	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	@Test
	public void test_given_city_barcelona_and_top_fifty_return_list() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("Authorization", "TEST");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<List<Contributor>> response = restTemplate.exchange(createURLWithPort("/contributors/barcelona/top/50"), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Contributor>>(){});
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(new Integer(50), new Integer(response.getBody().size()));
	}
	
	
	@Test
	public void test_given_city_barcelona_and_top_100_return_list() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("Authorization", "TEST");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<List<Contributor>> response = restTemplate.exchange(createURLWithPort("/contributors/barcelona/top/100"), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Contributor>>(){});
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(new Integer(100), new Integer(response.getBody().size()));
	}
	
	@Test
	public void test_given_city_barcelona_and_top_150_return_list() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("Authorization", "TEST");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<List<Contributor>> response = restTemplate.exchange(createURLWithPort("/contributors/barcelona/top/150"), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Contributor>>(){});
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(new Integer(150), new Integer(response.getBody().size()));
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
