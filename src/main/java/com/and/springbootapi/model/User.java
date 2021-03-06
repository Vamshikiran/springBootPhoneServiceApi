package com.and.springbootapi.model;

/**
 * Created by vamshikirangullapelly on 25/11/2018.
 */

public class User {

	private long id;
	private PhoneContact contactList;

	private String name;
	

	public User(){
		id=0;
	}
	
	public User(long id, String name){
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PhoneContact getContactList() {
		return contactList;
	}

	public void setContactList(PhoneContact contactList) {
		this.contactList = contactList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", contactList=" + contactList +
				'}';
	}
}
