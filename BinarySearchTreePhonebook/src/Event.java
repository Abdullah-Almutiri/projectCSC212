import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Event implements Comparable<Event> {
	
	//Fields for event's details , type and its related list.
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
		IsAppointment = false;
		this.contactCounter=0;
		
	}
	
	public Event(boolean IsAppointment, String eventTitle, String eventContact , String eventStartDate ,String eventEndDate , String eventLocation) { //Fields Constructor
		this.IsAppointment = IsAppointment;
		this.eventTitle = eventTitle;
		this.eventContact = eventContact;
		this.eventStartDate = new Date(eventStartDate);
		this.eventEndDate = new Date(eventEndDate);
		this.eventLocation = eventLocation;
		if (IsAppointment) {
            // Appointment has maximum 1 contact
            this.contactCounter = 1;
        } else {
            // Event has no max number of contacts
            organizeAndCountContacts(eventContact);
        }
		EventList = new LinkedListADT<Event>();
		
	}
	
	public void organizeAndCountContacts(String eventContact) {
	    // Remove extra spaces and duplicate commas
	    eventContact = eventContact.replaceAll("\\s+", " ").trim();
	    eventContact = eventContact.replaceAll(",+", ",");

	    // Split the contact string into an array
	    String[] contactsArray = eventContact.split(",");

	    // Count the number of contacts
	    contactCounter = 0;
	    StringBuilder organizedContacts = new StringBuilder();

	    for (String contact : contactsArray) {
	        if (!contact.trim().isEmpty()) {
	            organizedContacts.append(contact.trim()).append(",");
	            contactCounter++;
	        }
	    }

	    // Update the eventContact field
	    this.eventContact = organizedContacts.toString().replaceAll(",$", "");
	}
	
	public void removeContact(String deletedContact) {
	    // Check if the event has contacts
	    if (contactCounter > 0) {
	        // Remove the deleted contact from the contact string
	        eventContact = eventContact.replaceAll(deletedContact + ",", "").replaceAll("," + deletedContact, "").replaceAll(deletedContact, "");

	        // Decrement the contact counter
	        contactCounter--;

	        // If the counter reaches 0, remove the event
	        if (contactCounter == 0) {
	            EventList.remove();
	        }
	    }
	}
	

	public int compareTo(Event event) throws NullPointerException{
		return this.eventTitle.compareToIgnoreCase(event.getEventTitle());
	}


	//================================
	// A method to tell us whether the input date is valid or not.
	public boolean isValidDate(String Date, String format){
	
		SimpleDateFormat var = new SimpleDateFormat(format);
        var.setLenient(false);

        try{
            Date date = var.parse(Date);
			return true; // If parsing is successful, date is valid

        }catch (ParseException ex){
            return false; // Parsing failed, date is not valid
        }
    }


	// A method to tell us whether there's date conflicts or not.
	public boolean hasConflict(Date startDate, Date endDate){

		if(EventList.isEmpty())
				return false;

		EventList.findFirst();
		for(int i=0;i<EventList.size;i++) {
			Date eventStartDate = EventList.retrieve().getEventStartDate();
       		Date eventEndDate = EventList.retrieve().getEventEndDate();

			if ((eventStartDate.compareTo(startDate) <= 0 && eventEndDate.compareTo(startDate) >= 0) ||
				(eventStartDate.compareTo(endDate) <= 0 && eventEndDate.compareTo(endDate) >= 0)) { // method compareTo in class Date
				return true;
			}
			EventList.findNext();	
		}
		return false;
	}

	 // Setters/Getters for Event's data fields.
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
	

	public Boolean getIsAppointment() {
		return IsAppointment;
	}

	public void setIsAppointment(Boolean appointment) {
		IsAppointment = appointment;
	}
	
	public String EventType() {
		if(IsAppointment) return "Appointment";
		return "Event";
	}

	@Override
	public String toString() { //toString method for all event's details.
		return  "\n***********************************" +
				"\neventType: " + EventType() +
				"\neventTitle: " + eventTitle +
			    "\neventLocation: " + eventLocation +
			    "\neventContact: " + eventContact + 
			    "\neventStartDate: " + eventStartDate + 
			    "\neventEndDate: " + eventEndDate +
			    "\n***********************************";
	}

}
