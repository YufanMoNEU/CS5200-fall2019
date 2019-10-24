package edu.northeastern.cs5200.models;
import java.util.*;

public class Developer extends Person {
	private String developerKey;
	
	public Developer(String developerkey, int id, String firstName, String lastName) {
		super(id, firstName, lastName);
		this.developerKey = developerkey;
	}
	
	public Developer(String developerKey, int id, String firstName, String lastName, 
			String username, String password, String email, Date dob) {
		super(id, firstName, lastName, username, password, email, dob);
		this.developerKey = developerKey;
	}
	
	public Developer(String developerKey, int id, String firstName, String lastName,
            String username, String password, String email, Date dob,
            Collection<Phone> phones, Collection<Address> addresses) {
		super(id, firstName, lastName, username, password, email, dob, phones, addresses);
		this.developerKey = developerKey;
	}
	
	public String getDeveloperKey() {
		return developerKey;
	}
	
	public void setDeveloperKey(String developerKey) {
		this.developerKey = developerKey;
	}
	
}
