package phoneBook;

public class Contact implements Comparable<Contact>  {

	private String name;
	private String phoneNumber;
	private String emailAddress;
	private String address;
	private String notes;
	private String birthDay;
	
	public Contact(String contactName, String phoneNumber,String birthday, String email, String address, String notes) {

		this.name = contactName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = email;
		this.address = address;
		this.notes = notes;
		this.birthDay = birthday;
	}


	@Override // if "less than" return negative, if "greater than" return positive, and zero if "equal" 
	public int compareTo(Contact contact) {
		return this.name.compareToIgnoreCase(contact.getContactName());
	}


	public String getContactName() {
		return contactName;
	}


	public void setContactName(String name) {
		this.contactName = name;
	}


	public String getBirthday() {
		return birthDay;
	}


	public void setBirthday(String birthday) {
		this.birthDay = birthday;
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
	public String toString() {
		return "\nName: "         + name + 
			   "\nPhoneNumber: "  + phoneNumber + 
			   "\nEmailAddress: " + email + 
			   "\nAddress: "      + address + 
			   "\nBirthDay: "     + birthday + 
			   "\nNotes: "        + notes;
	}//Prints contact details
	
	
	
	

}
