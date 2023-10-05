package phoneBook;

public class Event {
	
	//Fields for Event's  title/Date and Time/Location/related Contact
	private String eventTitle , eventDateTime , eventLocation;
	private Contact eventContact;
	
	
	public Event() { //Default Constructor
		
		eventTitle = "No event title scheduled";
		eventDateTime = "No event date scheduled" ;
		eventLocation = "No event location scheduled";
		eventContact = null;
		
	}
	
	public Event(String eventTitle, String eventDateTime , String eventLocation , Contact eventContact) { //Fields Constructor
		
		this.eventTitle = eventTitle;
		this.eventDateTime = eventDateTime;
		this.eventLocation = eventLocation;
		this.eventContact = eventContact;
		
	}
	
	
    // Setters/Getters for Event's fields
	
	public String getEventTitle() {
		return eventTitle; //bigO(1)
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle; //bigO(1)
	}

	public String getEventDateTime() {
		return eventDateTime; //bigO(1)
	}

	public void setEventDateTime(String eventDateTime) {
		this.eventDateTime = eventDateTime; //bigO(1)
	}

	public String getEventLocation() {
		return eventLocation; //bigO(1)
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation; //bigO(1)
	}

	public Contact getEventContact() {
		return eventContact; //bigO(1)
	}

	public void setEventContact(Contact eventContact) {
		this.eventContact = eventContact; //bigO(1)
	}
	
	

}

