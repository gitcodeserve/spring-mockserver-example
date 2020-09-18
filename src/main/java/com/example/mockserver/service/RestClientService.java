package com.example.mockserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientService {

    private final RestTemplate restTemplate;

    public RestClientService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }


    public String getAllStudentsDetail() {
        String result = restTemplate.getForObject("http://localhost:8080", String.class);
        System.out.println("getStudentDetails: " + result);

        return result;
    }

    public String getStudentById(String studentId) {
        String result = restTemplate.getForObject("http://localhost:8080/student/" + studentId, String.class);
        System.out.println("getStudentDetails: " + result);

        return result;
    }

    public String registerStudent(String student) {
        String result = null;
        try {
            result = restTemplate.postForObject("http://localhost/student", student, String.class);
            System.out.println("registerStudent: " + result);
        } catch (HttpClientErrorException e) {
            result = e.getMessage();
        }

        return result;
    }

}