package com.SpringbootRestJpaH2.StudentRegistry;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class StudentRegistryApplicationTests {

	@Autowired
	private TestRestTemplate template;

	@Test
	void retrieveSpecificStudentById() throws JSONException {
		String SPECIFIC_QUESTION_URL = "/students/1";
		ResponseEntity<String> responseEntity = template.getForEntity(SPECIFIC_QUESTION_URL, String.class);
		 String expectedResponse = """
     			{"id":1,"name":"Anuj","address":"Pune","branch":"CS"}
				 """;
		 assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
		 assertEquals("application/json",responseEntity.getHeaders().get("Content-type").get(0));
		 JSONAssert.assertEquals(expectedResponse,responseEntity.getBody(),true);

		System.out.println(responseEntity.getBody());
		System.out.println(responseEntity.getHeaders());

	}

//	@Test
//	void retrieveAllStudent() throws JSONException {
//		String GENERIC_QUESTION_URL="/students/";
//		ResponseEntity<String> responseEntity = template.getForEntity(GENERIC_QUESTION_URL, String.class);
//		String responseBody = """
//				 [
//				    {
//				        "id": 1,
//				        "name": "Anuj",
//				        "address": "Pune",
//				        "branch": "CS"
//				    },
//				    {
//				        "id": 2,
//				        "name": "Santosh",
//				        "address": "Pune",
//				        "branch": "CS"
//				    }
//				]
//				""";
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Type","application/json");
//		HttpEntity<String> httpEntity = new HttpEntity<String>(responseBody,headers);
//
//		ResponseEntity<String> responseEntity1 = template.exchange(GENERIC_QUESTION_URL, HttpMethod.POST,httpEntity,String.class);
//		System.out.println(responseEntity1.getHeaders());
//		System.out.println(responseEntity1.getBody());
//
//		assertTrue(responseEntity1.getStatusCode().is2xxSuccessful());
//		assertEquals("application/json",responseEntity.getHeaders().get("Content-type").get(0));
//		JSONAssert.assertEquals(expectedResponse,responseEntity.getBody(),true);
//		System.out.println(responseEntity.getBody());
//		System.out.println(responseEntity.getHeaders());
//		System.out.println();

	}


