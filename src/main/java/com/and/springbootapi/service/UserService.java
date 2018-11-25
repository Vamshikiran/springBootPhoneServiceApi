package com.and.springbootapi.service;


import com.and.springbootapi.model.User;

import java.util.List;

/**
 * Created by vamshikirangullapelly on 25/11/2018.
 */

public interface UserService {
	
	User findById(long id);
	
	User findByName(String name);
	
	void updateUser(User user);
	
	List<User> findAllUsers();
	
}
