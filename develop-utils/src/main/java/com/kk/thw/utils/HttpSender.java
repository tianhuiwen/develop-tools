package com.kk.thw.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpSender {

    @Value("${report.dept.performance.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public <T> T post(String path, String jsonBody, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> httpEntity = new HttpEntity<String>(jsonBody, headers);
        ResponseEntity<T> result = restTemplate.postForEntity(url + "/" + path, httpEntity, clazz);
        return result.getBody();
    }
}