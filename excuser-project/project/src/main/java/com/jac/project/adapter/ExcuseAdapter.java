package com.jac.project.adapter;

import com.jac.project.exception.ExcuseNotFoundException;
import com.jac.project.exception.ExcuseServiceException;
import com.jac.project.model.Excuse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Data
public class ExcuseAdapter {

    @Value("${api.address}")
    private String apiUrl;

    /*
    Get a random excuse
    https://excuser-three.vercel.app/v1/excuse
    */

    /**
     * Gets a random excuse from the remote API
     *
     * @return  object of class Excuse representing a random excuse, or null if
     *          no excuses are returned or the array is empty
     */
    public Excuse getRandomExcuse(){
        RestTemplate restTemplate = new RestTemplate();
        Excuse[] excuses = restTemplate.getForObject(apiUrl, Excuse[].class);
        if (excuses != null && excuses.length > 0) {
            return excuses[0];
        } else {
            return null;
        }
    }

    /*
    Get an excuse having specific id
    https://excuser-three.vercel.app/v1/excuse/id/{id}
    */

    /**
     * Gets a specific excuse having specific id
     *
     * @param id specific ID of the excuse in external API
     * @return  object of class Excuse representing excuse of specific id
     */
    public Excuse getExcuseById(Long id){
        RestTemplate restTemplate = new RestTemplate();
        String apiUrlId = apiUrl + "/id/" + id;
        Excuse excuse = restTemplate.getForObject(apiUrlId, Excuse.class);
        if(excuse == null){
            throw new ExcuseNotFoundException(id);
        }
        return excuse;
    }

    /*
    Get a certain number of random excuses
    https://excuser-three.vercel.app/v1/excuse/{number}
    */

    /**
     * Gets a certain number of random excuses
     *
     * @param number the quantity of excuses to be retrieved from API
     * @return  a list of objects of class Excuse representing a specific number of excuses,
     *          or an empty list if no excuses are returned     *
     * @throws ExcuseNotFoundException if no excuses are found for the given number
     * @throws ExcuseServiceException  if there is an error fetching excuses
     */
    public List<Excuse> getListRandomExcuses(int number){
            RestTemplate restTemplate = new RestTemplate();
            String apiUrlWithNumber = apiUrl + number;
           Excuse[] excuses = restTemplate.getForObject(apiUrlWithNumber, Excuse[].class);

            if(excuses == null || excuses.length == 0){
                throw new ExcuseNotFoundException(number);
            }

            return Arrays.asList(excuses);

    }
}
