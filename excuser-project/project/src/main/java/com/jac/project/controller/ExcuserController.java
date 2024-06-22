package com.jac.project.controller;

import com.jac.project.service.ExcuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/excuse")
public class ExcuserController {

    @Autowired
    ExcuserService excuserService;



}
