/*
 * Brighton Ulery
 * brighton.ulery@snhu.edu
 * 20 July 2025
 */

package main;
import java.util.ArrayList;
import java.util.List;

/**
 * A contact service for managing contacts in a List
 * Contacts can be added, updated, or removed
 */
public class ContactService {
	private List<Contact> contacts = new ArrayList<Contact>();
	
	/**
	 * A function for getting a copy of the contact list
	 * @return a safe copy of the contact list
	 */
	public List<Contact> getContacts() {
		return new ArrayList<>(contacts);
	}
	
	/**
	 * Function for adding a new contact to the contact list
	 * @param contactID
	 * @param firstName
	 * @param lastName
	 * @param phoneNumber
	 * @param address
	 */
	public void addContact(String contactID, String firstName, String lastName, String phoneNumber, String address) {
		for(Contact c : contacts) {
			if(c.getContactID().equals(contactID)) { // Check for existing contact ID in the contact list
				System.out.println("Contact ID already exists.");
				return;
			}
		}
		
		Contact contact = new Contact(contactID, firstName, lastName, phoneNumber, address);
		contacts.add(contact);
	}
	
	/**
	 * Function for removing a contact based on the contact ID
	 * @param contactID
	 */
	public void removeContact(String contactID) {
		for(int i = 0; i < contacts.size(); ++i) {
			if(contacts.get(i).getContactID().equals(contactID)) {
				contacts.remove(i);
				return;
			}
		}
		
		System.out.println("The contact ID " + contactID + " was not found."); // If the contact ID was not found
	}
	
	/**
	 * Function for updating a contact's first name based on the contact ID
	 * @param contactID
	 * @param firstName
	 */
	public void updateContactFirstName(String contactID, String firstName) {
		Contact contact = getContact(contactID);
		
		if(contact != null) {
			contact.setFirstName(firstName);
		} else {
			System.out.println("The contact ID " + contactID + " was not found."); // If the contact ID was not found
		}
	}
	
	/**
	 * Function for updating a contact's last name based on the contact ID
	 * @param contactID
	 * @param lastName
	 */
	public void updateContactLastName(String contactID, String lastName) {
		Contact contact = getContact(contactID);
		
		if(contact != null) {
			contact.setLastName(lastName);
		} else {
			System.out.println("The contact ID " + contactID + " was not found."); // If the contact ID was not found
		}
	}
	
	/**
	 * Function for updating a contact's phone number based on the contact ID
	 * @param contactID
	 * @param phoneNumber
	 */
	public void updateContactPhoneNumber(String contactID, String phoneNumber) {
		Contact contact = getContact(contactID);
		
		if(contact != null) {
			contact.setPhoneNumber(phoneNumber);
		} else {
			System.out.println("The contact ID " + contactID + " was not found."); // If the contact ID was not found
		}
	}
	
	/**
	 * Function for updating a contact's address based on the contact ID
	 * @param contactID
	 * @param address
	 */
	public void updateContactAddress(String contactID, String address) {
		Contact contact = getContact(contactID);
		
		if(contact != null) {
			contact.setAddress(address);
		} else {
			System.out.println("The contact ID " + contactID + " was not found."); // If the contact ID was not found
		}
	}
	
	/**
	 * Function for getting a contact from the contact list based on the contact ID
	 * @param contactID
	 * @return
	 */
	private Contact getContact(String contactID) {
		for(Contact c : contacts) {
			if(c.getContactID().equals(contactID)) {
				return c;
			}
		}
		
		return null; // If no contact found, return null
	}
}
