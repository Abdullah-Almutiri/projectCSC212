package phoneBook;

public class Contact implements Comparable<Contact>  {

	private String name;
	private String phoneNumber;
	private String email;
	private String address;
	private String notes;
	private String birthday;
	
	public Contact(String name, String phoneNumber,String birthday, String email, String address, String notes) {

		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.notes = notes;
		this.birthday = birthday;
	}


	@Override // if "less than" return negative, if "greater than" return positive, and zero if "equal" 
	public int compareTo(Contact contact) {
		return this.contactName.compareToIgnoreCase(contact.getContactName());
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
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
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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
	
	
	
	
	

}
