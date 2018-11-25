package com.and.springbootapi.service;

import com.and.springbootapi.model.PhoneContact;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by vamshikirangullapelly on 25/11/2018.
 */
@Service("phoneService")
public class PhoneServiceImpl implements PhoneService {

    private static Map<Long,PhoneContact> phoneNumbersMap;


    public static Map<Long, PhoneContact> getPhoneNumbersMap() {
        return phoneNumbersMap;
    }

    public static void setPhoneNumbersMap(Map<Long, PhoneContact> phoneNumbersMap) {
        PhoneServiceImpl.phoneNumbersMap = phoneNumbersMap;
    }

    @Override
    public List<PhoneContact> findAllNumbers() {
        List<PhoneContact> result = phoneNumbersMap.values().stream()
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public PhoneContact findByUserId(long userId) {
        return phoneNumbersMap.get(userId);
    }

    @Override
    public boolean activatePhone(long userId, BigInteger phoneNumber) {
        boolean found = false;
        PhoneContact contact = findByUserId(userId);
        if (contact != null && contact.getPhoneNumber() != null) {
            if (findNumber(contact.getPhoneNumber(), phoneNumber)) {
                found = true;
                contact.getPhoneNumber().forEach((k, v) -> {
                    if (phoneNumber.equals(k)) {
                        contact.getPhoneNumber().replace(k, true);
                    }
                });
            }
        }
        return found;
    }

    private boolean findNumber(Map<BigInteger, Boolean> contacts, BigInteger phoneNumber) {
         boolean result = contacts.entrySet()
                .stream()
                .anyMatch(entry -> entry.getKey().equals(phoneNumber));
        return result;
    }
}
