package phoneBook;

public class Contact implements Comparable<Contact>  {
	// Data fields for the Contact's details.
	private String name , phoneNumber , emailAddress , address , notes , birthday;
	
	public Contact(String contactName, String phoneNumber,String birthday, String email, String address, String notes) {
		this.name = contactName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = email;
		this.address = address;
		this.notes = notes;
		this.birthday = birthday;
	}


	@Override // Overriden method of Comparable interface to compare this contact to another one by the name ,
		  // returns positive integer in the "greater than" case , zero when equals , negative when "less than".
	public int compareTo(Contact contact) {
		return this.name.compareToIgnoreCase(contact.getContactName());
	}

	//Setters\Getters for the data fields.
	public String getContactName() {
		return name;
	}


	public void setContactName(String name) {
		this.name = name;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return emailAddress;
	}


	public void setEmail(String email) {
		this.emailAddress = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Override
	public String toString() { //toString method for all contact's details.
		return 	"\n***********************************"+
				"\nName: "         + name + 
				"\nPhoneNumber: "  + phoneNumber + 
				"\nEmailAddress: " + emailAddress + 
				"\nAddress: "      + address + 
				"\nBirthDay: "     + birthday + 
				"\nNotes: "        + notes +
				"\n***********************************";
	}

}
