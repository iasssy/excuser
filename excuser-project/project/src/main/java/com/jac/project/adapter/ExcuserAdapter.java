package com.jac.project.adapter;

import com.jac.project.model.Excuse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExcuserAdapter {

    @Value("${api.address}")
    private String apiUrl;

    public Excuse getRandomExcuse(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, Excuse.class);
    }
}
