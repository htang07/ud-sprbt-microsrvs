package com.in28minutes.springboot.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.in28minutes.springboot.model.Question;
import com.in28minutes.springboot.service.SurveyService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SurveyController.class, secure = false)
public class SurveyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	// Mock @Autowired
	@MockBean
	private SurveyService surveyService;

	@Test
	public void retrieveDetailsForQuestion() throws Exception {
		Question mockQuestion = new Question("Question1", "Largest Country in the World", "Russia",
				Arrays.asList("India", "Russia", "United States", "China"));

		Mockito.when(surveyService.retrieveQuestion(Mockito.anyString(), Mockito.anyString())).thenReturn(mockQuestion);

		// Build GET Request
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/surveys/Survey1/questions/Question1")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "{\"id\":\"Question1\",\"description\":\"Largest Country in the World\",\"correctAnswer\":\"Russia\"}";

		System.out.println("response result \n" + result.getResponse().getContentAsString());
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

		// Assert
	}

	@Test
	public void createSurveyQuestion() throws Exception {
		Question mockQuestion = new Question("1", "Smallest Number", "1", Arrays.asList("1", "2", "3", "4"));

		String questionJson = "{\"description\":\"Smallest Number\",\"correctAnswer\":\"1\",\"options\":[\"1\",\"2\",\"3\",\"4\"]}";
		// surveyService.addQuestion to respond back with mockQuestion
		Mockito.when(surveyService.addQuestion(Mockito.anyString(), Mockito.any(Question.class)))
				.thenReturn(mockQuestion);

		// Send question as body to /surveys/Survey1/questions
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/surveys/Survey1/questions")
				.accept(MediaType.APPLICATION_JSON).content(questionJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/surveys/Survey1/questions/1", response.getHeader(HttpHeaders.LOCATION));
	}

	@Test
	public void retrieveSurveyQuestons() throws Exception {
		List<Question> mockList = Arrays.asList(
				new Question("Question1", "First Alphabet", "A", Arrays.asList("A", "B", "C", "D")),
				new Question("Question2", "Second Beta", "B", Arrays.asList("X", "Y", "Z")));

		Mockito.when(surveyService.retrieveQuestions(Mockito.anyString())).thenReturn(mockList);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/surveys/Survey1/questions").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		/*
		 * [{"id": "Question1","description": "First Alphabet","correctAnswer": "A",
		 * "options": ["A", "B", "C", "D"]}, {"id": "Question2","description":
		 * "Second Beta","correctAnswer": "B", "options": ["X", "Y", "Z"]}]
		 */
		// String expected="[{\r\n\t\t\"id\": \"Question1\",\r\n\t\t\"description\":
		// \"First Alphabet\",\r\n\t\t\"correctAnswer\": \"A\",\r\n\t\t\"options\":
		// [\"A\", \"B\", \"C\", \"D\"]\r\n\t},\r\n\t{\r\n\t\t\"id\":
		// \"Question2\",\r\n\t\t\"description\": \"Second
		// Beta\",\r\n\t\t\"correctAnswer\": \"B\",\r\n\t\t\"options\": [\"X\", \"Y\",
		// \"Z\"]\r\n\t}\r\n]";
		String expected = "[{\"id\": \"Question1\",\"description\": \"First Alphabet\",\"correctAnswer\": \"A\","
				+ "\"options\": [\"A\", \"B\", \"C\", \"D\"]},"
				+ "{\"id\": \"Question2\",\"description\": \"Second Beta\",\"correctAnswer\": \"B\","
				+ "\"options\": [\"X\", \"Y\", \"Z\"]}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		System.out.println("reponse result \n" + result.getResponse().getContentAsString());

	}

	@Test
	public void tempTest() {

	}
}
