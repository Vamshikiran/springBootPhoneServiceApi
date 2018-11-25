package com.and.springbootapi.service;

import com.and.springbootapi.model.PhoneContact;
import com.and.springbootapi.model.User;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by vamshikirangullapelly on 25/11/2018.
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final AtomicLong counter = new AtomicLong();

    private static List<User> users;

    static {
        users = populateDummyUsers();
    }

    private static List<User> populateDummyUsers() {
        List<User> users = new ArrayList<>();


        Map<Long, PhoneContact> dummyContactListMap = new HashMap<>();

        User sam = new User(counter.incrementAndGet(), "Sam");
        addUserContacts(users, sam, new BigInteger("077234567112"), new BigInteger("07765025335"));
        dummyContactListMap.put(sam.getId(), sam.getContactList());


        User tom = new User(counter.incrementAndGet(), "Tom");
        addUserContacts(users, tom, new BigInteger("077234527112"), new BigInteger("07765035335"));
        dummyContactListMap.put(tom.getId(), tom.getContactList());

        User jerome = new User(counter.incrementAndGet(), "Jerome");
        addUserContacts(users, jerome, new BigInteger("077234567132"), new BigInteger("07765025345"));
        dummyContactListMap.put(jerome.getId(), jerome.getContactList());

        User silvia = new User(counter.incrementAndGet(), "Silvia");
        addUserContacts(users, silvia, new BigInteger("077234167132"), new BigInteger("07735025345"));
        dummyContactListMap.put(silvia.getId(), silvia.getContactList());


        User gavin = new User(counter.incrementAndGet(), "Gavin");
        addUserContacts(users, gavin);
        dummyContactListMap.put(gavin.getId(), gavin.getContactList());


        PhoneServiceImpl.setPhoneNumbersMap(dummyContactListMap);

        return users;
    }

    private static void addUserContacts(List<User> users, User user, BigInteger... contacts) {
        Map<BigInteger, Boolean> contactsMap = new HashMap<>();
        Random rand = new Random();
        //As to assign true/false to different contacts using this loop
        for (BigInteger contact : contacts) {
            contactsMap.put(contact, rand.nextBoolean());
        }
        PhoneContact userContactList = new PhoneContact(contactsMap, user.getId());
        user.setContactList(userContactList);
        users.add(user);
    }

    public List<User> findAllUsers() {
        return users;
    }

    public User findById(long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User findByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public void updateUser(User user) {
        int index = users.indexOf(user);
        users.set(index, user);
    }

}
