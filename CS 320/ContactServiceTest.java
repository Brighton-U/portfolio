package test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Contact;
import main.ContactService;

public class ContactServiceTest {
	private ContactService contactService;
	
	@BeforeEach
	public void setup() {
		contactService = new ContactService();
	}
	
	@Test
	public void testAddContact() {
		contactService.addContact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		List<Contact> contacts = contactService.getContacts();
		assertEquals(1, contacts.size());
		assertEquals("Bartholemi", contacts.get(0).getFirstName());
	}
	
	@Test
	public void testAddContactDuplicateContactID() {
		contactService.addContact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		contactService.addContact("1234567890", "Allejandro", "Williamson", "5555555515", "124 Broadway S New York City, NY");
		List<Contact> contacts = contactService.getContacts();
		assertEquals(1, contacts.size());
		assertEquals("Bartholemi", contacts.get(0).getFirstName());
	}
	
	@Test
	public void testRemoveContact() {
		contactService.addContact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		contactService.removeContact("1234567890");
		assertTrue(contactService.getContacts().isEmpty());
	}
	
	@Test
	public void testRemoveContactNotFound() {
		contactService.removeContact("1234567890");
		assertTrue(contactService.getContacts().isEmpty());
	}
	
	@Test
	public void testUpdateContactFirstName() {
		contactService.addContact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		contactService.updateContactFirstName("1234567890", "Allejandro");
		assertEquals("Allejandro", contactService.getContacts().get(0).getFirstName());
	}
	
	@Test
	public void testUpdateContactLastName() {
		contactService.addContact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		contactService.updateContactLastName("1234567890", "Williamson");
		assertEquals("Williamson", contactService.getContacts().get(0).getLastName());
	}
	
	@Test
	public void testUpdateContactPhoneNumber() {
		contactService.addContact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		contactService.updateContactPhoneNumber("1234567890", "5555555515");
		assertEquals("5555555515", contactService.getContacts().get(0).getPhoneNumber());
	}
	
	@Test
	public void testUpdateContactAddress() {
		contactService.addContact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		contactService.updateContactAddress("1234567890", "124 1st St S New York City, NY");
		assertEquals("124 1st St S New York City, NY", contactService.getContacts().get(0).getAddress());
	}
	
	@Test
	public void testUpdateNonExistentContact() {
		assertDoesNotThrow(() -> contactService.updateContactFirstName("1234567890", "Allejandro"));
	}
	
	@Test
	public void testUpdateContactFirstNameNull() {
		contactService.addContact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contactService.updateContactFirstName("1234567890", null)
		);
		
		assertTrue(e.getMessage().contains("First name"));
	}
	
	@Test
	public void testUpdateContactFirstNameTooLong() {
		contactService.addContact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contactService.updateContactFirstName("1234567890", "Bartholomew")
		);
		
		assertTrue(e.getMessage().contains("First name"));
	}
	
	@Test
	public void testUpdateContactLastNameNull() {
		contactService.addContact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contactService.updateContactLastName("1234567890", null)
		);
		
		assertTrue(e.getMessage().contains("Last name"));
	}
	
	@Test
	public void testUpdateContactLastNameTooLong() {
		contactService.addContact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contactService.updateContactLastName("1234567890", "Richardsons")
		);
		
		assertTrue(e.getMessage().contains("Last name"));
	}
	
	@Test
	public void testUpdateContactPhoneNumberNull() {
		contactService.addContact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contactService.updateContactPhoneNumber("1234567890", null)
		);
		
		assertTrue(e.getMessage().contains("Phone number"));
	}
	
	@Test
	public void testUpdateContactPhoneNumberTooShort() {
		contactService.addContact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contactService.updateContactPhoneNumber("1234567890", "555555555")
		);
		
		assertTrue(e.getMessage().contains("Phone number"));
	}
	
	@Test
	public void testUpdateContactPhoneNumberTooLong() {
		contactService.addContact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contactService.updateContactPhoneNumber("1234567890", "55555555555")
		);
		
		assertTrue(e.getMessage().contains("Phone number"));
	}

	@Test
	public void testUpdateContactPhoneNumberNonDigits() {
		contactService.addContact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contactService.updateContactPhoneNumber("1234567890", "55-555-555")
		);
		
		assertTrue(e.getMessage().contains("Phone number"));
	}
	
	@Test
	public void testUpdateContactAddressNull() {
		contactService.addContact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contactService.updateContactAddress("1234567890", null)
		);
		
		assertTrue(e.getMessage().contains("Address"));
	}
	
	@Test
	public void testUpdateContactAddressTooLong() {
		contactService.addContact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contactService.updateContactAddress("1234567890", "4630 Cavalier Circle South Apartment 53 Louisiville, KY")
		);
		
		assertTrue(e.getMessage().contains("Address"));
	}
}
