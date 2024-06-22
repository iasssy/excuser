package com.jac.project.controller;

import com.jac.project.model.Excuse;
import com.jac.project.service.ExcuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/excuse")
public class ExcuserController {

    @Autowired
    ExcuserService excuserService;

    /*
    Get a random excuse
    https://excuser-three.vercel.app/v1/excuse
     */
    @GetMapping("/")
    public Excuse getRandomExcuse(){
       return excuserService.getRandomExcuse();
    }


    /*
    Get a specific excuse having specific id
    https://excuser-three.vercel.app/v1/excuse/id/101

    Get n random excuses
    https://excuser-three.vercel.app/v1/excuse/3

    Get a random excuse for a specific category
    https://excuser-three.vercel.app/v1/excuse/office

    Get n random excuses for a specific category
    https://excuser-three.vercel.app/v1/excuse/college/4
     */

}
