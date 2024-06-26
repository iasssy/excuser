package com.jac.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jac.project.adapter.YesNoAdapter;
import com.jac.project.model.YesNoResponse;

@Service
public class YesNoService {

    @Autowired
    YesNoAdapter yesNoAdapter;

    public YesNoResponse getYesNoResponse() {
        return yesNoAdapter.fetchYesNoResponse();
    }
}
