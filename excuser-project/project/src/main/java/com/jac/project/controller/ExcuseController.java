package com.jac.project.controller;

import com.jac.project.exception.ExcuseServiceException;
import com.jac.project.model.Excuse;
import com.jac.project.service.ExcuseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExcuseController {

    @Autowired
    ExcuseService excuserService;

    /*
    Get a random excuse
    https://excuser-three.vercel.app/v1/excuse
     */
    @GetMapping("/")
    public Excuse getRandomExcuse(){
        try{
            return excuserService.getRandomExcuse();
        } catch (Exception exception) {
            throw new ExcuseServiceException("Excuses are NOT FOUND");
            // TODO to check this exception
        }
    }

    /*
    Get a specific excuse having specific id
    https://excuser-three.vercel.app/v1/excuse/id/{id}
    */

    @GetMapping("/id/{id}")
    public Excuse getExcuseById(@PathVariable Long id){
        return excuserService.getExcuseById(id);
    }

    /*
    Get a certain number of random excuses
    https://excuser-three.vercel.app/v1/excuse/{number}
    */
    @GetMapping("/{number}")
    public List<Excuse> getListRandomExcuses(@PathVariable int number){
        return excuserService.getListRandomExcuses(number);
    }



    /*

    Get a random excuse for a specific category
    https://excuser-three.vercel.app/v1/excuse/office

    Get n random excuses for a specific category
    https://excuser-three.vercel.app/v1/excuse/college/4
     */

}
