package com.jac.project.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.jac.project.model.YesNoResponse;

@Component
public class YesNoAdapter {
    @Value("${api.yesno}")
    private String apiYesNo;

    public YesNoResponse fetchYesNoResponse() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiYesNo, YesNoResponse.class);
    }
}
