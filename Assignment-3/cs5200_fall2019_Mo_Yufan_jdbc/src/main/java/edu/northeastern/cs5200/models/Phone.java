package edu.northeastern.cs5200.models;
import java.util.*;

public class Phone {
	private Boolean isPrimary;
	private String phone;
	private Person person;
	
	public Phone() {
		
	}
	
	public Phone(Boolean isPrimary, String phone, Person person) {
		this.isPrimary = isPrimary;
		this.phone = phone;
		this.person = person;
	}
	
	public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getPrimary() {
        return this.isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
