package com.and.springbootapi.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Map;

/**
 * Created by vamshikirangullapelly on 25/11/2018.
 */
public class PhoneContact implements Serializable{

    private Map<BigInteger,Boolean> phoneNumber;
    private long userId;

    public PhoneContact(Map<BigInteger, Boolean> phoneNumber, long userId) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }

    public Map<BigInteger, Boolean> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Map<BigInteger, Boolean> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
