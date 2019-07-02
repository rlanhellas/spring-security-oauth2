package com.example.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WelcomeService {

    private Logger logger = LoggerFactory.getLogger(WelcomeService.class);

    /**
    * Chama o resource server
    * */
    public void callResourceServer(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:9090/needscope", String.class);
        logger.info(responseEntity.getBody());
    }

}
