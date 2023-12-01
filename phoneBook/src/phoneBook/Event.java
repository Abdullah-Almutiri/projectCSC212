package phoneBook;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Event implements Comparable<Event> {
	
	//Data fields for event's details , type and its related data-structure (LinkedList) to store its related .
	private String eventTitle , eventLocation , eventContact;
	private Boolean IsAppointment;
	private int contactCounter;
	Date eventStartDate, eventEndDate;
	LinkedListADT<Event> EventList;

	
	public Event() { //Default Constructor
		
		eventTitle = "No event title scheduled";
		eventLocation = "No event location scheduled";
		eventContact = null;
		eventStartDate = new Date();
		eventEndDate = new Date();
		EventList = new LinkedListADT<Event>();
		
	}
	
	public Event(String eventTitle, String eventContact , String eventStartDate ,String eventEndDate , String eventLocation) { //Fields Constructor
		
		this.eventTitle = eventTitle;
		this.eventContact = eventContact;
		this.eventStartDate = new Date(eventStartDate);
		this.eventEndDate = new Date(eventEndDate);
		this.eventLocation = eventLocation;
		EventList = new LinkedListADT<Event>();
		
	}
	
	
    // Setters/Getters for Event's fields
	public int compareTo(Event event) throws NullPointerException{
		return this.eventTitle.compareToIgnoreCase(event.getEventTitle());
	}


	//================================
	public boolean isValidDate(String Date, String format){ //  Method to tells us if the input date valid or not.
	
		SimpleDateFormat var = new SimpleDateFormat(format);
        var.setLenient(false);

        try{
            Date date = var.parse(Date);
			return true; // If parsing is successful, date is valid

        }catch (ParseException ex){
            return false; // Parsing failed, date is not valid
        }
    }


	// A method to solve "date conflicts issue" , return True if there's a conflict with another 'exist' event.
	public boolean hasConflict(Date startDate, Date endDate){

		if(EventList.isEmpty())
				return false;

		EventList.findFirst();
		for(int i=0;i<EventList.size;i++) {
			Date eventStartDate = EventList.retreive().getEventStartDate();
       		Date eventEndDate = EventList.retreive().getEventEndDate();

			if ((eventStartDate.compareTo(startDate) <= 0 && eventEndDate.compareTo(startDate) >= 0) ||
				(eventStartDate.compareTo(endDate) <= 0 && eventEndDate.compareTo(endDate) >= 0)) { // method compareTo in class Date
				return true;
			}
			EventList.findNext();	
		}
		return false;
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
	public String toString() { //toString method for all event's details.
		return  "\n***********************************" +
				"\neventTitle: " + eventTitle +
			    "\neventLocation: " + eventLocation +
			    "\neventContact: " + eventContact + 
			    "\neventStartDate: " + eventStartDate + 
			    "\neventEndDate: " + eventEndDate +
			    "\n***********************************";
	}
	
	

}
