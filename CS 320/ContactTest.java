package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import main.Contact;

public class ContactTest {
	@Test
	public void testValidContactCreation() {
		Contact contact = new Contact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		assertEquals("1234567890", contact.getContactID());
		assertEquals("Bartholemi", contact.getFirstName());
		assertEquals("Richardson", contact.getLastName());
		assertEquals("5555555555", contact.getPhoneNumber());
		assertEquals("123 Main St New York City, NY", contact.getAddress());
	}
	
	@Test
	public void testInvalidContactIDNull() {
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			new Contact(null, "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY")
		);
		
		assertTrue(e.getMessage().contains("Contact ID"));
	}
	
	@Test
	public void testInvalidContactIDTooLong() {
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			new Contact("12345678910", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY")
		);
		
		assertTrue(e.getMessage().contains("Contact ID"));
	}
	
	@Test
	public void testInvalidFirstNameNull() {
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			new Contact("1234567890", null, "Richardson", "5555555555", "123 Main St New York City, NY")
		);
		
		assertTrue(e.getMessage().contains("First name"));
	}
	
	@Test
	public void testInvalidFirstNameTooLong() {
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			new Contact("1234567890", "Bartholomew", "Richardson", "5555555555", "123 Main St New York City, NY")
		);
		
		assertTrue(e.getMessage().contains("First name"));
	}
	
	@Test
	public void testInvalidLastNameNull() {
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			new Contact("1234567890", "Bartholemi", null, "5555555555", "123 Main St New York City, NY")
		);
		
		assertTrue(e.getMessage().contains("Last name"));
	}
	
	@Test
	public void testInvalidLastNameTooLong() {
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			new Contact("1234567890", "Bartholemi", "Richardsons", "5555555555", "123 Main St New York City, NY")
		);
		
		assertTrue(e.getMessage().contains("Last name"));
	}
	
	@Test
	public void testInvalidPhoneNumberNull() {
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			new Contact("1234567890", "Bartholemi", "Richardson", null, "123 Main St New York City, NY")
		);
		
		assertTrue(e.getMessage().contains("Phone number"));
	}
	
	@Test
	public void testInvalidPhoneNumberTooShort() {
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			new Contact("1234567890", "Bartholemi", "Richardson", "555555555", "123 Main St New York City, NY")
		);
		
		assertTrue(e.getMessage().contains("Phone number"));
	}
	
	@Test
	public void testInvalidPhoneNumberTooLong() {
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			new Contact("1234567890", "Bartholemi", "Richardson", "55555555555", "123 Main St New York City, NY")
		);
		
		assertTrue(e.getMessage().contains("Phone number"));
	}
	
	@Test
	public void testInvalidPhoneNumberNonDigits() {
		Exception e = assertThrows(IllegalArgumentException.class, () ->
		new Contact("1234567890", "Bartholemi", "Richardson", "55-555-555", "123 Main St New York City, NY")
		);
		
		assertTrue(e.getMessage().contains("Phone number"));
	}
	
	@Test
	public void testInvalidAddressNull() {
		Exception e = assertThrows(IllegalArgumentException.class, () ->
		new Contact("1234567890", "Bartholemi", "Richardson", "5555555555", null)
		);
		
		assertTrue(e.getMessage().contains("Address"));
	}
	
	@Test
	public void testInvalidAddressTooLong() {
		Exception e = assertThrows(IllegalArgumentException.class, () ->
		new Contact("1234567890", "Bartholemi", "Richardson", "5555555555", "4630 Cavalier Circle South Apartment 53 Louisiville, KY")
		);
		
		assertTrue(e.getMessage().contains("Address"));
	}
	
	@Test
	public void testSetFirstName() {
		Contact contact = new Contact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		contact.setFirstName("Allejandro");
		assertEquals("Allejandro", contact.getFirstName());
	}
	
	@Test
	public void testSetFirstNameNull() {
		Contact contact = new Contact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contact.setFirstName(null)
		);
	
		assertTrue(e.getMessage().contains("First name"));
	}
	
	@Test
	public void testSetFirstNameTooLong() {
		Contact contact = new Contact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contact.setFirstName("Bartholomew")
		);
		
		assertTrue(e.getMessage().contains("First name"));
	}
	
	@Test
	public void testSetLastName() {
		Contact contact = new Contact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		contact.setLastName("Williamson");
		assertEquals("Williamson", contact.getLastName());
	}
	
	@Test
	public void testSetLastNameNull() {
		Contact contact = new Contact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contact.setLastName(null)
		);
	
		assertTrue(e.getMessage().contains("Last name"));
	}
	
	@Test
	public void testSetLastNameTooLong() {
		Contact contact = new Contact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contact.setLastName("Richardsons")
		);
		
		assertTrue(e.getMessage().contains("Last name"));
	}
	
	@Test
	public void testSetPhoneNumber() {
		Contact contact = new Contact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		contact.setPhoneNumber("5555555515");
		assertEquals("5555555515", contact.getPhoneNumber());
	}
	
	@Test
	public void testSetPhoneNumberNull() {
		Contact contact = new Contact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contact.setPhoneNumber(null)
		);
	
		assertTrue(e.getMessage().contains("Phone number"));
	}
	
	@Test
	public void testSetPhoneNumberTooShort() {
		Contact contact = new Contact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contact.setPhoneNumber("555555555")
		);
		
		assertTrue(e.getMessage().contains("Phone number"));
	}
	
	@Test
	public void testSetPhoneNumberTooLong() {
		Contact contact = new Contact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contact.setPhoneNumber("55555555555")
		);
		
		assertTrue(e.getMessage().contains("Phone number"));
	}
	
	@Test
	public void testSetPhoneNumberNonDigits() {
		Contact contact = new Contact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contact.setPhoneNumber("55-555-555")
		);
		
		assertTrue(e.getMessage().contains("Phone number"));
	}
	
	@Test
	public void testSetAddress() {
		Contact contact = new Contact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		contact.setAddress("124 1st St S New York City, NY");
		assertEquals("124 1st St S New York City, NY", contact.getAddress());
	}
	
	@Test
	public void testSetAddressNull() {
		Contact contact = new Contact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contact.setAddress(null)
		);
	
		assertTrue(e.getMessage().contains("Address"));
	}
	
	@Test
	public void testSetAddressTooLong() {
		Contact contact = new Contact("1234567890", "Bartholemi", "Richardson", "5555555555", "123 Main St New York City, NY");
		Exception e = assertThrows(IllegalArgumentException.class, () ->
			contact.setAddress("4630 Cavalier Circle South Apartment 53 Louisiville, KY")
		);
		
		assertTrue(e.getMessage().contains("Address"));
	}
}
