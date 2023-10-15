package phoneBook;
import java.util.Date;

public class Event implements Comparable<Event> {
	
	//Fields for Event's  title/Date and Time/Location/related Contact
	private String eventTitle;
	private String eventContact;
	private String eventLocation;
	Date eventStartDate;
	Date eventEndDate;
	
	public Event() { //Default Constructor
		
		eventTitle = "No event title scheduled";
		eventLocation = "No event location scheduled";
		eventContact = null;
		eventStartDate = new Date();
		eventEndDate = new Date();
		
	}
	
	public Event(String eventTitle, String eventContact , String eventStartDate ,String eventEndDate , String eventLocation) { //Fields Constructor
		
		this.eventTitle = eventTitle;
		this.eventContact = eventContact;
		this.eventStartDate = new Date(eventStartDate);
		this.eventEndDate = new Date(eventEndDate);
		this.eventLocation = eventLocation;
		
	}
	
	
    // Setters/Getters for Event's fields
	public int compareTo(Event event) throws NullPointerException{
		return this.eventTitle.compareToIgnoreCase(event.getEventTitle());
	}
	public String getEventTitle() {
		return eventTitle; //bigO(1)
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle; //bigO(1)
	}


	public Date getEventStartDate() {
		return eventStartDate;
	}

	public void setEventStartDate(Date eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public Date getEventEndDate() {
		return eventEndDate;
	}

	public void setEventEndDate(Date eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	public String getEventLocation() {
		return eventLocation; //bigO(1)
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation; //bigO(1)
	}

	public String getEventContact() {
		return eventContact; //bigO(1)
	}

	public void setEventContact(String eventContact) {
		this.eventContact = eventContact; //bigO(1)
	}

	@Override
	public String toString() {
		return  "\neventTitle: " + eventTitle +
			    "\neventLocation: " + eventLocation +
			    "\neventContact: " + eventContact + 
			    "\neventStartDate: " + eventStartDate + 
			    "\neventEndDate: " + eventEndDate;
	}
	
	

}
