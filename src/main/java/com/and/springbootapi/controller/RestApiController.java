package com.and.springbootapi.controller;

import com.and.springbootapi.model.PhoneContact;
import com.and.springbootapi.service.PhoneService;
import com.and.springbootapi.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by vamshikirangullapelly on 25/11/2018.
 */

@SuppressWarnings("ALL")
@RestController
@RequestMapping(value = "/api/v1")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    PhoneService phoneService;


    // -------------------Retrieve All phoneNumbers---------------------------------------------

    @RequestMapping(value = "/phoneNumbers", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<PhoneContact>> listAllPhoneNumbers() {
        List<PhoneContact> phoneNumbers = phoneService.findAllNumbers();
        if (phoneNumbers.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<PhoneContact>>(phoneNumbers, HttpStatus.OK);
    }


    // -------------------Retrieve PhoneNumbers By User Id------------------------------------------
    @RequestMapping(value = "/phoneNumbers/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPhoneContactByUserId(@PathVariable("userId") long userId) {
        logger.info("Fetching PhoneNumbers for User Id {}", userId);
        PhoneContact phoneContact = phoneService.findByUserId(userId);
        if (phoneContact == null) {
            logger.error("Phone Number(s) not found for the user {}.", userId);
            return new ResponseEntity(new CustomErrorType("Phone Number(s) not found for the user " + userId), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PhoneContact>(phoneContact, HttpStatus.OK);
    }


    @RequestMapping(value = "/phoneNumbers/{userId}/activate/{number}", method = RequestMethod.PUT)
    public ResponseEntity<?> activateNumber(@PathVariable("number") BigInteger number, @PathVariable("userId") long userId) {
        logger.info("Activating number {} for User {}", number, userId);
        boolean isActivated = phoneService.activatePhone(userId, number);
        if (!isActivated) {
            logger.error("Unable to activate Phone Number {}. Number not found for the user {}", number, userId);
            return new ResponseEntity(new CustomErrorType("Unable to activate Phone Number " + number
                    + ". Number not found for the user " + userId), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PhoneContact>(phoneService.findByUserId(userId), HttpStatus.OK);
    }


}