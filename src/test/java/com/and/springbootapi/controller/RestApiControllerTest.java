package com.and.springbootapi.controller;

import com.and.springbootapi.model.PhoneContact;
import com.and.springbootapi.model.User;
import com.and.springbootapi.service.PhoneService;
import com.and.springbootapi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vamshikirangullapelly on 22/11/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestApiControllerTest {

    private static final String BASE_URL = "/api/v1";
    @Autowired
    UserService userService;

    @Autowired
    PhoneService phoneService;

    List<User> users;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listAllUsers() throws Exception {
        List<User> allUsers = userService.findAllUsers();
        Assert.notNull(allUsers, "Should return list of users");
    }


    @Test
    public void listAllPhoneNumbers() throws Exception {
        this.mockMvc.perform(get(BASE_URL + "/phoneNumbers")).andDo(print()).andExpect(status().isOk());

    }


    @Test
    public void getPhoneNumber() throws Exception {
        long id = userService.findAllUsers().get(0).getId();
        this.mockMvc.perform(get(BASE_URL + "/phoneNumbers/" + id)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getUserWithNoPhoneNumber() throws Exception {
        User user = userService.findByName("Gavin");
        long id = user.getId();
        this.mockMvc.perform(get(BASE_URL + "/phoneNumbers/" + id)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void activatePhoneNumber() throws Exception {

        long id = userService.findAllUsers().get(0).getId();
        PhoneContact phoneContact = phoneService.findByUserId(id);
        Map<BigInteger, Boolean> phoneNumber = phoneContact.getPhoneNumber();
        Optional<Entry<BigInteger, Boolean>> first = phoneNumber.entrySet().stream().findFirst();
        //Activate phone number
        BigInteger contactNumber = first.get().getKey();
        this.mockMvc.perform(put(BASE_URL + "/phoneNumbers/" + id + "/activate/" + contactNumber))
                .andDo(print()).andExpect(status().isOk());
        Boolean aBoolean = phoneService.findByUserId(id).getPhoneNumber().get(contactNumber);
        Assert.isTrue(aBoolean, "Should return true");
    }

    @Test
    public void activatePhoneNumberNotAvailable() throws Exception {

        long userId = userService.findAllUsers().get(0).getId();
        //Activate phone number
        this.mockMvc.perform(put(BASE_URL + "/phoneNumbers/" + userId + "/activate/" + "3212321232"))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value("Unable to activate Phone Number 3212321232. Number not found for the user " + userId));
    }


}