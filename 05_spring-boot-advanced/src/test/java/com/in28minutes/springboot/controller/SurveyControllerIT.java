package com.in28minutes.springboot.controller;

import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
//import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.springboot.Application;
import com.in28minutes.springboot.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Before
	public void before() {
		headers.add("Authorization", createHttpAuthenticationHeaderValue("user1", "secret1"));
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testRetrieveSurveyQuestion() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/surveys/Survey1/questions/Question1"), HttpMethod.GET, entity, String.class);

		String expected ="{\"id\": \"Question1\",\"description\": \"Largest Country in the World\",\"correctAnswer\": \"Russia\"}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void retrieveAllSurveyQuestions() throws Exception {

		ResponseEntity<List<Question>> response = restTemplate.exchange(createURLWithPort("/surveys/Survey1/questions"),
				HttpMethod.GET, new HttpEntity<String>("DUMMY_DOESNT_MATTER", headers),
				new ParameterizedTypeReference<List<Question>>() {
				});

		Question sampleQuestion = new Question("Question1", "Largest Country in the World", "Russia",
				Arrays.asList("India", "Russia", "United States", "China"));

		assertTrue(response.getBody().contains(sampleQuestion));
	}

	@Test
	public void addQuestion() {

		Question question = new Question("DOESNTMATTER", "Question1", "Russia",
				Arrays.asList("India", "Russia", "United States", "China"));

		HttpEntity entity = new HttpEntity<Question>(question, headers); //HttpEntity converts question object to json given specified header

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/surveys/Survey1/questions"),
				HttpMethod.POST, entity, String.class);

		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		System.out.println("returned post-response: \n" + actual);
		assertTrue(actual.contains("/surveys/Survey1/questions/"));

	}

	@Test
	public void tempRestTemp() {
		// by default restTemplate return xml as response. to use json need pass Accept
		// type in http header
		String url = "http://localhost:" + port + "/surveys/Survey1/questions/Question1";
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		String output = testRestTemplate.getForObject(url, String.class);
		System.out.println("Response \n" + output);

	}
	
	@Test
	public void tempRestTempJsonVersion() throws JSONException {
		// by default restTemplate return xml as response. to use json need pass Accept
		// type in http header
		String url = "http://localhost:" + port + "/surveys/Survey1/questions/Question1";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", createHttpAuthenticationHeaderValue("user1", "secret1"));
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String>entity = new HttpEntity<String>(null, headers);
		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<String>response = testRestTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		
		System.out.println("Response \n" + response.getBody());
		assertTrue(response.getBody().contains("\"id\" : \"Question1\""));
		String expected="{\r\n    \"id\": \"Question1\",\r\n    \"description\": \"Largest Country in the World\",\r\n    \"correctAnswer\": \"Russia\",\r\n    \"options\": [\r\n        \"India\",\r\n        \"Russia\",\r\n        \"United States\",\r\n        \"China\"\r\n    ]\r\n}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private String createURLWithPort(final String uri) {
		return "http://localhost:" + port + uri;
	}

	private String createHttpAuthenticationHeaderValue(String userId, String password) {

		String auth = userId + ":" + password;

		byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));

		String headerValue = "Basic " + new String(encodedAuth);

		return headerValue;
	}

}
