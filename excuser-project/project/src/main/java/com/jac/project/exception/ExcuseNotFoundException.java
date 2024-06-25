package com.jac.project.exception;

public class ExcuseNotFoundException extends RuntimeException {

    /**
     *
     * @param number number of random excuses to be fetched from the external API
     */
    public ExcuseNotFoundException(int number) {
        super("No excuses found for the given number: " + number);
    }

    /**
     *
     * @param id a specific ID of the excuse in the external API
     */
    public ExcuseNotFoundException(Long id) {
        super("Excuse with ID " + id + " is NOT FOUND");
    }
}
