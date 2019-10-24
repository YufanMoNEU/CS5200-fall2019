package edu.northeastern.cs5200.models;
import java.util.*;

public class User extends Person {
	private boolean userAgreement;
	
	public User(int id, String firstName, String lastName) {
		//extends and super inheritance keyword: to inherit from another class
		super(id, firstName, lastName);
		this.userAgreement = false;
	}
	
	public User(int id, String firstName, String lastName,
            String username, String password, String email, Date dob) {
		super(id, firstName, lastName, username, password, email, dob);
		this.userAgreement = false;
	}
	
	public User(int id, String firstName, String lastName,
            String username, String password, String email, Date dob, boolean userAgreement) {
		super(id, firstName, lastName, username, password, email, dob);
		this.userAgreement = userAgreement;
	}
	
	public boolean getUserAgreement() {
		return userAgreement;
	}
	
	public void setUserAgreement(boolean userAgreement) {
		this.userAgreement = userAgreement;
	}
	
	
}
