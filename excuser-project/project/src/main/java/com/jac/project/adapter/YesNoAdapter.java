package com.jac.project.adapter;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.jac.project.model.YesNoResponse;

@Component
public class YesNoAdapter {

    private static final String YES_NO_API_URL = "https://yesno.wtf/api";

    public YesNoResponse fetchYesNoResponse() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(YES_NO_API_URL, YesNoResponse.class);
    }
}
