package com.example.mockserver;

import com.example.mockserver.service.RestClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@DirtiesContext
@RestClientTest(RestClientService.class)
class MockServerExampleApplicationTests {

	@Autowired
	private MockRestServiceServer server;

	@Autowired
	private RestClientService restClientService;

	@Autowired
	private RestTemplate restTemplate;


	@Before
	public void setUp() {
		server = MockRestServiceServer.createServer(restTemplate);
	}

	@Test
	public void clientGetCallTest() {
		server.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/student/123" ))
				.andRespond(MockRestResponseCreators.withSuccess("Shaan", MediaType.APPLICATION_JSON));
		String student = restClientService.getStudentById("123");
		Assert.assertEquals(student, "Shaan");
		server.verify();

	}

}
