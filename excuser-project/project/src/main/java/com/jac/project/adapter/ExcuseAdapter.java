package com.jac.project.adapter;

import com.jac.project.model.Excuse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Data
public class ExcuseAdapter {

    @Value("${api.address}")
    private String apiUrl;

    public Excuse getRandomExcuse(){
        RestTemplate restTemplate = new RestTemplate();
        Excuse[] excuses = restTemplate.getForObject(apiUrl, Excuse[].class);
        if (excuses != null && excuses.length > 0) {
            return excuses[0];
        } else {
            return null;
        }
    }
}
