package com.jac.project.controller;

import com.jac.project.exception.ExcuseServiceException;
import com.jac.project.model.Excuse;
import com.jac.project.service.ExcuseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/excuse")
public class ExcuseController {

    @Autowired
    ExcuseService excuseService;

    /*
    Get a random excuse
    https://excuser-three.vercel.app/v1/excuse
     */
    @GetMapping("/")
    public Excuse getRandomExcuse(){
        try{
            return excuseService.getRandomExcuse();
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
    public ResponseEntity<Excuse> getExcuseById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(excuseService.getExcuseById(id), HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /*
    Get a specific number of random excuses
    https://excuser-three.vercel.app/v1/excuse/{number}
    */
    @GetMapping("/{number}")
    public ResponseEntity<List<Excuse>> getListRandomExcuses(@PathVariable int number){
        try {
            return new ResponseEntity<>(excuseService.getListRandomExcuses(number), HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    /*

    Get a random excuse for a specific category
    https://excuser-three.vercel.app/v1/excuse/office
    TODO validation of category

    Get a specific number of random excuses for a specific category
    https://excuser-three.vercel.app/v1/excuse/college/4
     */

}
