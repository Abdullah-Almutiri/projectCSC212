import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Phonebook {
	
	
	Scanner input = new Scanner(System.in);
	BST<Contact> ContactBST = new BST<Contact>();
	Event Event = new Event();
	
	public void AddContact() {

		//Input fields and input validation section
		try{
			System.out.print("\n* Enter the contact's name: ");
			String Name = input.nextLine();

			System.out.print("* Enter the contact's phone number: ");
			String Number = input.nextLine();
			
			
			//Name and Number are sensitive and required keys, we must ensure that they are valid and unique 
			{
				
					//1.Check if the entered number contains only numbers, it will throw exception otherwise
					Integer.parseInt(Number); 
					
					//2.To ensure the user fill the required labels correctly
					if(Number.length() < 10 || !(Number.charAt(0)=='0')) 
						throw new IllegalArgumentException();
					
					if(Name.length() == 0) {
						System.out.println("\n *** you have to fill the required lables (*). ***  ");
						return;
					}
					
					if(Name.indexOf(',')>-1) {
						System.out.println("The name should not contain comma ','");
						return;
					}
						
					//3.Checking if the contact name already exist
					if(ContactBST.findkey(Name)) {
						System.out.println("Contact Already Exist!");
						return;
					}
				
					//4.Checking if the contact number already exist
					if(ContactBST.findHelper(Number, 2)) {
						System.out.println("Contact Already Exist!");
						return;
					}
					
			}
			//The contact does not exist, and the phone number is valid. continue the program
			
			
			System.out.print("Enter the contact's email address: ");
			String EmailAddress = input.nextLine();

			System.out.print("Enter the contact's address: ");
			String Address = input.nextLine();

			System.out.print("Enter the contact's birthday (MM/DD/YYYY): ");
			String BirthDay = input.nextLine();

			System.out.print("Enter any notes for the contact: ");
			String Notes = input.nextLine();

			
//			Adding the contact after the input passed the validation
			{
				Contact C = new Contact(Name,Number,EmailAddress,Address,BirthDay,Notes);
				ContactBST.insert(C);
				System.out.println(" \n* Contact added successfully *");
			}

		
		}catch(IllegalArgumentException IAE){
			
			System.out.println("\n *** The phone number is not valid                  ***  ");
			  System.out.println(" *** The phone number must contain only numbers     ***");
			  System.out.println(" *** The phone number must contains 10 digits only  ***");
			  System.out.println(" *** The phone number must start with 0             ***");


		}catch(InputMismatchException IME){
			System.out.println(IME);
			
		}catch(NullPointerException NPE) {
			System.out.println(NPE);
			
		}catch(Exception E){
			System.out.println(E);
			
		}
	}
	
	public void SearchContact() {
		try {
			System.out.print(  "\nEnter search criteria:\r\n"
					 + "1. Name\r\n"
					 + "2. Phone Number\r\n"
					 + "3. Email Address\r\n"
					 + "4. Address\r\n"
					 + "5. Birthday\r\n"
					 + "\nEnter your choice: ");
			int criteria = input.nextInt();
			input.nextLine();
			switch(criteria) {
				case 1 : {
					System.out.print("Enter the contact's name: ");
					ContactBST.printFind(input.nextLine(), criteria);
					break;
				}
				case 2 : {
					System.out.print("Enter the contact's phone number: ");
					ContactBST.printFind(input.nextLine(), criteria);
					break;
				}
				case 3 : {
					System.out.print("Enter the contact's email address: ");
					ContactBST.printFind(input.nextLine(), criteria);
					break;
				}
				case 4 : {
					System.out.print("Enter the contact's address: ");
					ContactBST.printFind(input.nextLine(), criteria);
					break;
				}
				case 5 : {
					System.out.print("Enter the contact's birthday: ");
					ContactBST.printFind(input.nextLine(), criteria);
					break;
				}
				default : {
					System.out.println("****Invaild Input****");
					break;
				}
			}
		}catch(Exception E) {
			System.out.println(E);
			input.nextLine();
		}
	}
	
	public void DeleteContact() {
	    try {
	        System.out.print("\nEnter Contact name: ");
	        String contactName = input.nextLine();

	        // Check if the contact exists
	        if (!ContactBST.findkey(contactName)) {
	            System.out.println("Contact Does not exist");
	            return;
	        }

	        // Get the contact object to retrieve details before deletion
	        Contact deletedContact = ContactBST.retrieve();

	        // Remove the contact from the BST
	        if (ContactBST.remove_key(contactName)) {
	            System.out.println("Contact Deleted");

	            // Set current at first
	            Event.EventList.findFirst();

	            // Get the initial size of the EventList
	            int initialSize = Event.EventList.size;

	            // Iterate through the events to find and remove the contact
	            for (int i = 0; i < initialSize; i++) {
	                Event currentEvent = Event.EventList.retrieve();

	                // Check if the contact is part of the event
	                if (currentEvent.getEventContact().contains(contactName)) {
	                    // Remove only the contact from the event and update eventContact
	                    currentEvent.removeContact(contactName);

	                    // If the event has no associated contacts, remove the event
	                    if (currentEvent.getEventContact().isEmpty()) {
	                        Event.EventList.remove();
	                    }
	                }

	                // Move to the next event
	                Event.EventList.findNext();
	            }

	            System.out.println("The contact and associated events were removed.");
	        } else {
	            System.out.println("Contact Does not exist");
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	}
	
	public void EventSchedule() {
		
		try{
			//create date format so we can save and compare date safely
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			
			if(ContactBST.IsEmpty()) {
				System.out.println("\n *** you have to add at least one contact ***");
				return;
			}
			System.out.print("\nEnter type:\r\n"
						   + "1. event\r\n"
						   + "2. appointment\r\n"
						   + "Enter your choice: ");
			int choice = input.nextInt();
			input.nextLine();
			System.out.print("* Enter event title: ");
			String title = input.nextLine();
			
			String contact="";
			boolean IsAppointment=false;
			
			
			switch(choice) {
				case 1 : {
					System.out.print("* Enter contacts name separated by a comma: ");
					contact = input.nextLine();
					IsAppointment = false;
					break;
				}
				case 2 : {
					System.out.print("* Enter contact name: ");
					contact = input.nextLine();
					IsAppointment = true;
					break;
					
				}
				default : System.out.println("****Invaild Choice****");
			}
			
			
			//2.Checking if contact exist
			if(!IsAppointment) {
				String[] contacts = contact.split(",");
				for (int i=0; i<contacts.length;i++) {
					if (!ContactBST.findkey(contacts[i].trim())) {
						System.out.println("Contact not found: " + contacts[i].trim());
						return;
					}
				}
			}
			else if(IsAppointment) {
				if(!ContactBST.findkey(contact)) {
					System.out.println("\n *** Contact Does Not Exist! ***");
					return;
				}
			}
			
			
			System.out.print("* Enter event start date(MM/DD/YYYY HH:MM): ");
			String startDate = input.nextLine();
			Date startDateCheck = format.parse(startDate);
			
			System.out.print("* Enter event end date(MM/DD/YYYY HH:MM): ");
			String endDate = input.nextLine();
			Date endDateCheck = format.parse(endDate);
			
			
			System.out.print(" Enter event location: ");
			String location = input.nextLine();

			
			//Input checking
			{
				
				//1.To ensure the user fill the labels.
				if(title.length() == 0 || contact.length() == 0 || startDate.length() == 0 || endDate.length() == 0  )
					throw new InputMismatchException();
				
				//3.Comparing the date input with the acceptable format and range
				if( endDateCheck.compareTo(startDateCheck)<=0 || 
						startDateCheck.compareTo(new Date(System.currentTimeMillis()))<=0 ||
						!Event.isValidDate(startDate, "MM/dd/yyyy HH:mm") ||
						!Event.isValidDate(endDate, "MM/dd/yyyy HH:mm")) {
					
					System.out.println(" *** Please consider the following rules                                           ***");
					System.out.println(" *** 1 - The start date cannot be in the past (assuming you are not time traveler) ***");
					System.out.println(" *** 2 - The end date comes after the start date                                   ***");
					System.out.println(" *** 3 - You have to follow this format MM/DD/YYYY HH:MM                           ***");
					return;
				}
				
				//4.Checking for conflicts in time
				if(Event.hasConflict(startDateCheck, endDateCheck)) {
					System.out.println("\n *** you have an event at this date ***");
					System.out.println(Event.EventList.retrieve().toString()); //Print event that caused the conflict
					return;
				}
			
				
			}//Input checking is done.
			
			
			//start scheduling the event
			  {
		            Event event = new Event(IsAppointment, title, contact, startDate, endDate, location);

		            // Organize and count contacts
		            event.organizeAndCountContacts(contact);

		            if (!Event.EventList.isEmpty()) {
		                // Set current at first
		                Event.EventList.findFirst();

		                // Search for the right place to add the new event, the search is alphabetical-based
		                while (event.compareTo(Event.EventList.retrieve()) >= 0) {

		                    // Move to the next event if the current is not the last
		                    if (!Event.EventList.last())
		                        Event.EventList.findNext();

		                    // If the current is the last, insertAtEnd
		                    else {
		                        Event.EventList.insertAtEnd(event);
		                        System.out.println("\n * Event Scheduled successfully *");
		                        event.organizeAndCountContacts(contact); // Add this line
		                        return;
		                    }
		                }
		            }
		            Event.EventList.insert(event);
		            System.out.println("\n * Event Scheduled successfully *");
		            event.organizeAndCountContacts(contact); // Add this line
		        }


		}catch(InputMismatchException E){
			System.out.println("\n ***ERROR: title, name, startDate, and, endDate are required. ***");
	
		}catch(NullPointerException NPE) {
			System.out.println("somthing went wrong!. please try agin!");
			
		}catch(Exception E){
			System.out.println("somthing went wrong!. please try agin!");
			
		}
	}
	
	public void PrintEventDetails() {
		try {
		    if (Event.EventList.isEmpty()) {
		        System.out.println("Your event list is empty! There is nothing to search.");
		        return;
		    }

		    Event.EventList.findFirst(); // set current at first

		    // Receiving input, no need for validation
		    System.out.println("Enter search criteria:");
		    System.out.println("1. Contact Name");
		    System.out.println("2. Event Title");
		    System.out.print(" \nEnter your choice: ");

		    int choice = input.nextInt();
		    input.nextLine(); // Consume the newline character


		    System.out.print("\nEnter the search criteria: ");
		    String searchCriteria = input.nextLine();

		    boolean found = false;

		    for (int i = 0; i < Event.EventList.size; i++) {
		        switch (choice) {
		            case 1:
		                // Check if the current event's contact matches the entered contact
		                String eventContact = Event.EventList.retrieve().getEventContact();
		                String[] contacts = eventContact.split(",");
		                for (String contact : contacts) {
		                    if (contact.trim().equalsIgnoreCase(searchCriteria.trim())) {
		                        found = true;
		                        System.out.println("\nEvent found!\n");
		                        System.out.println(Event.EventList.retrieve());
		                        break; // Stop searching for this event
		                    }
		                }
		                break;
		            case 2:
		                // Check if the current event's title matches the entered title
		                found = Event.EventList.retrieve().getEventTitle().equalsIgnoreCase(searchCriteria);
		                if (found) {
		                    System.out.println("\nEvent found!\n");
		                    System.out.println(Event.EventList.retrieve());
		                }
		                break;
		        }

		        Event.EventList.findNext();
		    }

		    if (!found) {
		        System.out.println(" * Event not found! *");
		    }

		}catch (Exception E) {
		    System.out.println(E);
		}
	}
	
	public void PrintContactByFirstName() {
	    try {
	        // If we found any contact, we will mark it as true
	    	BooleanWrapper found = new BooleanWrapper(false);

	        if (ContactBST.IsEmpty()) {
	            System.out.println("Your contact list is empty! There is nothing to search.");
	            return;
	        }

	        System.out.print("Enter The First Name: ");
	        String FirstName = input.nextLine();

	        if (FirstName.length() == 0) {
	            System.out.println(" *** Invalid Input ***");
	            return;
	        }

	        // Set current at first
	        ContactBST.current = ContactBST.root;

	        // Perform an in-order traversal of the BST to find contacts with the specified first name
	        printContactsByFirstName(ContactBST.root, FirstName, found);

	        if (!found.getValue()) System.out.println("\n *** Contact not found! ***");

	    } catch (Exception E) {
	        System.out.println(E);

	    }
	}

	private void printContactsByFirstName(BSTNode<Contact> p, String firstName, BooleanWrapper found) {
	    if (p != null) {
	        printContactsByFirstName(p.left, firstName, found);

	        // Check if the current contact's first name matches the specified input
	        String contactFirstName = p.data.getContactName().split(" ")[0];
	        if (contactFirstName.equalsIgnoreCase(firstName)) {
	            found.setValue(true);
	            System.out.println(p.data);
	        }

	        printContactsByFirstName(p.right, firstName, found);
	    }
	}

	public void PrintOrderedEvents() {
		try {
			
			if(Event.EventList.isEmpty()) 
				System.out.println(" * There is no events to show *");
			
			//set current at first
			Event.EventList.findFirst();
			
			//Print the whole list directly
			for(int i=0;i<Event.EventList.size;i++) {
				System.out.println(Event.EventList.retrieve());
				Event.EventList.findNext();
			}
			
		}catch(Exception E) {	
			System.out.println(E);
			
		}
	}
	
	public void print() {
		ContactBST.printBST();
	}
	
}