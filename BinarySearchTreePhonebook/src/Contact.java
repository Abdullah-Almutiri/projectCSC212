
public class Contact implements Comparable<Contact>  {

	private String name;
	private String phoneNumber;
	private String emailAddress;
	private String address;
	private String notes;
	private String birthday;
	
	
	public Contact(String contactName, String phoneNumber,String birthday, String email, String address, String notes) {
		this.name = contactName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = email;
		this.address = address;
		this.notes = notes;
		this.birthday = birthday;
	}


	@Override // if "less than" return negative, if "greater than" return positive, and zero if "equal" 
	public int compareTo(Contact contact) {
		return this.name.compareToIgnoreCase(contact.getContactName());
	}


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
	public String toString() {
		return 	"\n***********************************"+
				"\nName: "         + name + 
				"\nPhoneNumber: "  + phoneNumber + 
				"\nEmailAddress: " + emailAddress + 
				"\nAddress: "      + address + 
				"\nBirthDay: "     + birthday + 
				"\nNotes: "        + notes +
				"\n***********************************";
	}//Prints contact details
	
	
	
	

}