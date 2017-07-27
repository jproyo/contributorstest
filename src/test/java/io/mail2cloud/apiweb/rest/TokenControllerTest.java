//package io.mail2cloud.apiweb.rest;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import io.mail2cloud.apiweb.Application;
//import io.mail2cloud.apiweb.Security;
//import io.mail2cloud.apiweb.storage.domain.Storage;
//import io.mail2cloud.apiweb.storage.domain.TokenUser;
//import io.mail2cloud.apiweb.storage.security.StorageTokenService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.embedded.LocalServerPort;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {Application.class, Security.class},  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class TokenControllerTest {
//
//	@LocalServerPort
//	private int port;
//	
//	@Autowired
//	private ObjectMapper jacksonObjectMapper;
//	
//	@Autowired
//	private StorageTokenService storageTokenService;
//	
//	TestRestTemplate restTemplate = new TestRestTemplate();
//	
//	@Test
//	public void testCreateAccessToken() throws Exception {
//		String user = "smith@example.com";
//		Storage storage = Storage.box;
//		TokenUser tokenUser = new TokenUser();
//		tokenUser.setStorage(storage);
//		tokenUser.setUser(user);
//		storageTokenService.createRefreshToken(tokenUser);
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Type", "application/json");
//		HttpEntity<String> entity = new HttpEntity<String>(jacksonObjectMapper.writeValueAsString(tokenUser),headers);
//		
//		ResponseEntity<TokenUser> response = restTemplate.exchange(
//				createURLWithPort("/storage/accessToken"),
//				HttpMethod.POST, entity, TokenUser.class);
//
//		Assert.assertEquals(HttpStatus.CREATED.value(),response.getStatusCodeValue());
//		Assert.assertEquals(user,storageTokenService.getUser(response.getBody().getAccessToken()).getUser());
//	}
//	
//	private String createURLWithPort(String uri) {
//		return "http://localhost:" + port + uri;
//	}
//
//}
