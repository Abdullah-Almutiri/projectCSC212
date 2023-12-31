package phoneBook;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Phonebook {

	
	
	static LinkedListADT<Contact> ContactList = new LinkedListADT<Contact>();
	static Event Event = new Event();
	static Scanner input = new Scanner(System.in);



	// add a new unique contact
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
						
				
				
				//3.Checking if the contact already exist
				if(!ContactList.isEmpty()) {
					
					ContactList.findFirst();
					
					for(int i=0;i<ContactList.size;i++) { 
						if( ContactList.retreive().getPhoneNumber().compareToIgnoreCase(Number)==0 ||
							ContactList.retreive().getContactName().compareToIgnoreCase(Name)==0) {
							
							System.out.println("\n *** ERROR: Contact Already Exists! ***");
							return;
							
						}
						
						ContactList.findNext();
					}
				}//The contact does not exist, and the phone number is valid. continue the program
			}
			
			
			System.out.print("Enter the contact's email address: ");
			String EmailAddress = input.nextLine();

			System.out.print("Enter the contact's address:");
			String Address = input.nextLine();

			System.out.print("Enter the contact's birthday (MM/DD/YYYY): ");
			String BirthDay = input.nextLine();

			System.out.print("Enter any notes for the contact: ");
			String Notes = input.nextLine();

			
			//Adding the contact after the input passed the validation
			{
				Contact C = new Contact(Name,Number,EmailAddress,Address,BirthDay,Notes);
				
				if(!ContactList.isEmpty()) {
					
					//set current at first
					ContactList.findFirst();
					
					//search for the right place to add the new contact, the search is alphabetical-base
					while(C.compareTo(ContactList.retreive()) > 0) {
						
						//move current to the next contact if it the current isn't the last
						if(!ContactList.last()) 
							ContactList.findNext();
						
						//if the current is the last, insertAtEnd
						else {
							ContactList.insertAtEnd(C);
							System.out.println(" \n* Contact added successfully *");
							return;
						}
					}
				}

				ContactList.insert(C);
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

	
	//Search for contact  
	 public  void search() {
        
        if (ContactList.isEmpty()) {
            System.out.println("Your phonebook is empty!");
            return;
        }


        System.out.println("By which criteria do you want to search?\n ");
            System.out.println("1. Name: ");
            System.out.println("2. Phone Number: ");
            System.out.println("3. Email Address: ");
            System.out.println("4. Address: ");
            System.out.println("5. Birthday: ");
            System.out.print("Enter the field to search by: ");

           
            int choice;

            if (input.hasNextInt()){
                choice = input.nextInt();
                input.nextLine();
            }else {
                System.out.println("wrong");
                return;
            }
     


        System.out.print("\nEnter the search criteria: ");
        String searchCriteria = input.nextLine();

        boolean found = false;

        ContactList.findFirst();

        for (int i = 0; i < ContactList.size; i++) { 
          
            Contact contact = ContactList.retreive();
          
            switch (choice) {
                case 1:
                    found = ContactList.retreive().getContactName().compareTo(searchCriteria) == 0; // never be empty
                    break;
                case 2:
                    found = ContactList.retreive().getPhoneNumber().compareTo(searchCriteria) == 0; // never be empty
                    break;
                case 3:
                    found = searchCriteria.length() != 0 && ContactList.retreive().getEmail().compareTo(searchCriteria) == 0 ;
                    break;
                case 4:
                    found = searchCriteria.length() != 0 &&ContactList.retreive().getAddress().compareTo(searchCriteria) == 0;
                    break;
                case 5:
                    found = searchCriteria.length() != 0 &&ContactList.retreive().getBirthday().compareTo(searchCriteria) == 0;
                    break;
            }

            if (found) {
                System.out.println("\nContact found!\n");
                System.out.println(contact);
                return; // Stop searching after the first match.
            }
            ContactList.findNext();
        }
		System.out.println("\nContact not found!\n");

    }
	
	
	// removes the contact by name and any Associated events
	public void Remove() {
	
		try{
			System.out.print("Enter Contact's Name: ");
			String name = input.nextLine();
			
				if(ContactList.isEmpty()) {
					System.out.println("\n *** Contact not found! ***");
					return;
				}
				boolean found = false;
				//set current at first
				ContactList.findFirst();
				
				for(int i=0; i < ContactList.size ;i++) { // O(n)
					
					//check the entered name with current's name
					if(ContactList.retreive().getContactName().compareToIgnoreCase(name) == 0) {
						
						ContactList.remove();
						System.out.println(" * Contact is deleted. *");
						found = true;
				}
					//move current to the next contact
					ContactList.findNext();
			
				}
				
				//remove related events
				//set current at first
				if(found){
					Event.EventList.findFirst();

					int numberOfRemovedEvents = 0 ,size = Event.EventList.size; //we may have to delete multiple events which will decrease the size, 
																				//to make sure we check the whole list. we have to save the size in a variable so it wont change during the loop
					//checking the whole EventList
					for(int j=0;j<size;j++) {
						
						//if the participated contact is the removed contact, delete the event
						if(Event.EventList.retreive().getEventContact().compareToIgnoreCase(name) == 0) {
							Event.EventList.remove();
							numberOfRemovedEvents++;	
						}
						//move current to the next event
						Event.EventList.findNext();
					}
					System.out.println(" * " + numberOfRemovedEvents + " Event\\s Connected to this contact removed *");
					return;
				}
					
				if(!found)
					System.out.println("\nContact not found.");
		
		}catch(NullPointerException NPE) {
			System.out.println(NPE);
			
		}catch(InputMismatchException IME) {
			System.out.println(IME);
			
		}catch(Exception E) {
			System.out.println(E);	
		}
	}
		
	// Schedules an event with an existing contact
	public void EventSchedule() {
		
		try{
			//create date format so we can save and compare date safely
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			
			if(ContactList.isEmpty()) {
				System.out.println("\n *** you have to add at least one contact ***");
				return;
		}
			//request data from the user
			System.out.print("* Enter event title: ");
			String title = input.nextLine();

			System.out.print("* Enter contact name: ");
			String name = input.nextLine();
			
			
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
				if(title.length() == 0 || name.length() == 0 || startDate.length() == 0 || endDate.length() == 0  )
					throw new InputMismatchException();
					
				
				//2.Checking if contact exist
				if(!ContactList.isEmpty()) {	

					ContactList.findFirst();	
					for(int i=0;i<ContactList.size;i++) {
						if(ContactList.retreive().getContactName().compareToIgnoreCase(name)==0) break;
						else if(ContactList.last()) {
							System.out.println("\n *** Contact Does Not Exist! ***");
							return;
						}	
						ContactList.findNext();
					}	
				}
				else {
					System.out.println("\n *** Contact Does Not Exist! ***");
					return;
				}
				
				
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
					System.out.println(Event.EventList.retreive().toString()); //Print event that caused the conflict
					return;
				}
			
				
			}//Input checking is done.
			
			
			//start scheduling the event
			{
				Event event = new Event(title,name,startDate,endDate,location);
				
				if(!Event.EventList.isEmpty()) {
					
					//set current at first
					Event.EventList.findFirst();
					
					//search for the right place to add the new event, the search is alphabetical-base
					while(event.compareTo(Event.EventList.retreive())>=0) {
						
						//if the same name title exist
						if(event.compareTo(Event.EventList.retreive())==0) {
							System.out.println("\n Event Already Exists");
							return;
						}
						
						//move to the next event if the current is not the last
						else if(!Event.EventList.last()) 
							Event.EventList.findNext();

						//if the current is the last, insertAtEnd
						else {
							Event.EventList.insertAtEnd(event);
							System.out.println("\n * Event Ssheduled successfully *");
							return;
						}
					}
				}	
				Event.EventList.insert(event);
				System.out.println("\n * Event Ssheduled successfully *");

			}


		}catch(InputMismatchException E){
			System.out.println("\n ***ERROR: title, name, startDate, and, endDate are required. ***");
	
		}catch(NullPointerException NPE) {
			System.out.println("somthing went wrong!. please try agin!");
			
		}catch(Exception E){
			System.out.println("somthing went wrong!. please try agin!");
			
		}
	}

	// print the event details
	public void PrintEventDetails() {
		
		try {
			
			if(Event.EventList.isEmpty()) {
				System.out.println("Your event list is empty!, there is nothing to search.");
				return;
			}
			
			//set current at first
			Event.EventList.findFirst();
			
			//Receiving input, no need for validation
			System.out.println("Enter search citeria:");
			System.out.println("1. Contact Name");
			System.out.println("2. Event Title");
			System.out.print(" \nEnter your choice: ");

			int choice;

            if (input.hasNextInt()){
                choice = input.nextInt();
                input.nextLine(); //when entering the value x, we click "enter", this field catches that "enter" so it wont disturb the program.
            }else {
                System.out.println("wrong");
                return;
            }

			System.out.print("\nEnter the search criteria: ");
			String searchCriteria = input.nextLine();
	
			 
			boolean found = false;

			for (int i = 0; i < Event.EventList.size ; i++){
				
				switch(choice) {

					case 1: 
							//check if current event's contact matches the entered contact 
							found = Event.EventList.retreive().getEventContact().compareToIgnoreCase(searchCriteria) == 0;
							break;
					case 2 :
							//check if current event's title matches the entered title 
							found = Event.EventList.retreive().getEventTitle().compareToIgnoreCase(searchCriteria) == 0;
							break;
						}
						if (found) {
							System.out.println("\nContact found!\n");
							System.out.println(Event.EventList.retreive());
							return; // Stop searching after the first match.
					}
					Event.EventList.findNext(); 
					
				}

						System.out.println(" * Event not found! *");
						return;

			
		}catch(InputMismatchException IME) {
			System.out.println("somthing went wrong! Input Mismatch . please try agin");
			
		 }catch(NullPointerException NPE) {
			System.out.println("somthing went wrong!. please try agin");
			
		}catch(Exception E) {
			System.out.println("somthing went wrong!. please try agin");
			
		}
	}

		
	// print the contacts by first name
	public void PrintContactListByFirstName() {
		
		try{
			
			//if we found any contact, we will remark it to true
			boolean found=false;
	
			if(ContactList.isEmpty()) {
				System.out.println("Your contact list is empty!, there is nothing to search.");
				return;
			}
	
			System.out.print("Enter The First Name: ");
			String FirstName = input.nextLine();

			if(FirstName.length() == 0) {
				System.out.println(" *** Invaild Input ***");
				return;
			}
				
			//set current at first
			ContactList.findFirst();

			for (int i = 0; i < ContactList.size ; i++){
				
				
				//if the current name contains letter at much as the input name, check if they are the same and print (this field check for ContactList that have only first name)
				if (ContactList.retreive().getContactName().length()==FirstName.length()&&
					ContactList.retreive().getContactName().substring(0, FirstName.length()).compareToIgnoreCase(FirstName)==0){ 
					found=true;
					System.out.println(ContactList.retreive()); 
				} 
					
				/***if the current name contains letter more than input name, add to the entered name " " space and compare it with the current name (this field check for ContactList that have more than the first name
				 	actually the main goal for this section is to eliminate  first names that share their first part with the enter name
				 	for an example assume the first name i am looking for is "Mesh", this section will eliminate names such as "Meshal" and look for only names that starts with Mesh then " " (space) ***/
				
					else if (ContactList.retreive().getContactName().length()>FirstName.length()&&
					ContactList.retreive().getContactName().substring(0, FirstName.length()+1).compareToIgnoreCase(FirstName+" " )== 0){ 
					found=true;
					System.out.println(ContactList.retreive()); 
				} 

				ContactList.findNext();  
			}
			
			if(!found) System.out.println("\n *** Contact not found! ***");
			
		}catch(NullPointerException NPE){
			System.out.println("somthing went wrong!. please try agin");
			
		}catch(InputMismatchException IME) {
			System.out.println("somthing went wrong! Input Mismatch . please try agin");
			
		}
		
		catch(Exception E) {
			System.out.println("somthing went wrong!. please try agin");
			
		}
		
	}

	
	// print the events
	public void PrintOrdredEvents() {
		
		try {
			
			if(Event.EventList.isEmpty()) 
				System.out.println(" * There is no events to show *");
			
			//set current at first
			Event.EventList.findFirst();
			
			//Print the whole list directly
			for(int i=0;i<Event.EventList.size;i++) {
				System.out.println(Event.EventList.retreive());
				Event.EventList.findNext();
			}
			
		}catch(NullPointerException NPE) {
			System.out.println("somthing went wrong!. please try agin");
			
		}catch(Exception E) {	
			System.out.println("somthing went wrong!. please try agin");
			
		}
	}
	

}
