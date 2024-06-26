package com.jac.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jac.project.service.YesNoService;
import com.jac.project.model.YesNoResponse;

@RestController
@RequestMapping("/yesno")
@CrossOrigin
public class YesNoController {

    @Autowired
    private YesNoService yesNoService;

    @GetMapping("/")
    public ResponseEntity<YesNoResponse> getYesNo() {
        return new ResponseEntity<>(yesNoService.getYesNoResponse(), HttpStatus.OK);
    }
}
