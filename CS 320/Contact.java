/*
 * Brighton Ulery
 * brighton.ulery@snhu.edu
 * 20 July 2025
 */
package main;

/**
 * The Contact object class for holding information regarding the Contact
 */
public class Contact {
	private final String contactID; // Contact ID is immutable
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	
	/**
	 * Constructor for a Contact object
	 * @param contactID
	 * @param firstName
	 * @param lastName
	 * @param phoneNumber
	 * @param address
	 */
	public Contact(String contactID, String firstName, String lastName, String phoneNumber, String address) {
		this.contactID = validateValue(contactID, 10, "Contact ID");
		this.firstName = validateValue(firstName, 10, "First name");
		this.lastName = validateValue(lastName, 10, "Last name");
		this.phoneNumber = validatePhoneNumber(phoneNumber);
		this.address = validateValue(address, 30, "Address");
	}
	
	/**
	 * Getter for the contact ID
	 * @return the contact ID
	 */
	public String getContactID() {
		return this.contactID;
	}
	
	/**
	 * Getter for the contact's first name
	 * @return the contact's first name
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * Setter for the contact's first name
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = validateValue(firstName, 10, "First name");
	}
	
	/**
	 * Getter for the contact's last name
	 * @return the contact's last name
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * Setter for the contact's last name
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = validateValue(lastName, 10, "Last name");
	}
	
	/**
	 * Getter for the contact's phone number
	 * @return the contact's phone number
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	/**
	 * Setter for the contact's phone number
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = validatePhoneNumber(phoneNumber);
	}
	
	/**
	 * Getter for the contact's address
	 * @return the contact's address
	 */
	public String getAddress() {
		return this.address;
	}
	
	/**
	 * Setter for the contact's address
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = validateValue(address, 30, "Address");
	}
	
	/**
	 * Returns a String representation of the Contact object when printing the object directly
	 * @return a String representation of the Contact object
	 */
	@Override
	public String toString() {
		return String.format("%s %s\nPhone: %s\nAddress: %s", firstName, lastName, phoneNumber, address);
	}
	
	/**
	 * Validates the value based on the maximum length of the String and throws a relevant exception
	 * based on the field being validated.
	 * @param value
	 * @param maxLength
	 * @param field
	 * @return a trimmed copy of the value
	 */
	private String validateValue(String value, int maxLength, String field) {
		if(value == null) {
			throw new IllegalArgumentException("ERROR: " + field + " cannot be null.");
		}
		
		String trimmed = value.trim();
		
		if(trimmed.length() > maxLength) {
			throw new IllegalArgumentException("ERROR: " + field + " cannot be longer than " + maxLength + " characters.");
		}
		
		return trimmed;
	}
	
	/**
	 * Validates the phone number is of the required length and throws an appropriate exception if invalid.
	 * @param phoneNumber
	 * @return a trimmed copy of the phone number
	 */
	private String validatePhoneNumber(String phoneNumber) {
		if(phoneNumber == null) {
			throw new IllegalArgumentException("ERROR: Phone number cannot be null.");
		}
		
		String trimmed = phoneNumber.trim();
		
		if(!trimmed.matches("\\d{10}")) {
			throw new IllegalArgumentException("ERROR: Phone number must be exactly 10 digits.");
		}
		
		return trimmed;
	}
}
