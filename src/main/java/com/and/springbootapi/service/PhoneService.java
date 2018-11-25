package com.and.springbootapi.service;


import com.and.springbootapi.model.PhoneContact;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by vamshikirangullapelly on 25/11/2018.
 */
public interface PhoneService {

    List<PhoneContact> findAllNumbers();
    PhoneContact findByUserId(long userId);
    boolean activatePhone(long userId, BigInteger phoneNumber);
}
