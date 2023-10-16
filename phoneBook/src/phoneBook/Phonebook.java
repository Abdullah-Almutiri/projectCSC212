package phoneBook;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Scanner;

import javax.xml.crypto.MarshalException;
public class Phonebook {


	//The whole Start function represent the main, to start the program just create main class and call the Start function.
	static LinkedListADT<Contact> ContactList = new LinkedListADT<Contact>();
	static LinkedListADT<Event> EventList = new LinkedListADT<Event>();

	static Scanner input = new Scanner(System.in);



	//================================
	public void AddContact() {

		try{
			System.out.print("\n* Enter the contact's name: ");
			String Name = input.nextLine();

			System.out.print("* Enter the contact's phone number: ");
			String Number = input.nextLine();
	
			System.out.print("Enter the contact's email address: ");
			String EmailAddress = input.nextLine();

			System.out.print("Enter the contact's address:");
			String Address = input.nextLine();

			System.out.print("Enter the contact's birthday (MM/DD/YYYY): ");
			String BirthDay = input.nextLine();

			System.out.print("Enter any notes for the contact: ");
			String Notes = input.nextLine();



			// To ensure the user fill the required lables correctlly
			if(Number.length() < 10)
				throw new IllegalArgumentException();

			if(Name.length() == 0)
				throw new Exception();	



			Contact C = new Contact(Name,Number,EmailAddress,Address,BirthDay,Notes);

			
			if(!ContactList.isEmpty()) {
				
				ContactList.findFirst();
				
				while(C.compareTo(ContactList.retreive()) >= 0) {
					
					if(C.compareTo(ContactList.retreive()) == 0) {
						System.out.println("\nContact Already Exists");
						return;
					}
					else if(!ContactList.last()) 
					ContactList.findNext();

					else {
						ContactList.insertAtEnd(C);
						return;
					}
				}
			}

			ContactList.insert(C);
		
			
		
		}catch(IllegalArgumentException IAE){
			System.out.println(" \n*** The phone number is not valid. ***  ");


		}catch(Exception ex){
			System.out.println(" \n*** you have to fill the required lables (*). ***  ");
	}
	
	
	}
	

	
	//================================
	public void Search() {
		
		int choice=0;
		boolean VaildInput = false;
			
			System.out.println("\nEnter search criteria:\n");

			System.out.println("1. Name");
			System.out.println("2. Phone Number");
			System.out.println("3. Email Address");
			System.out.println("4. Address");
			System.out.println("5. Birthday");
			System.out.println("6. Cancel\n");
			System.out.print("Enter your chioce: ");


		do{
			try {
				choice = input.nextInt();
				VaildInput = true;

			}catch(java.util.InputMismatchException e){
				System.out.println("Invaild choice, please re-enter a vaild choice: ");
				input.nextLine();
			}


		}while( !VaildInput && choice > 0 && choice < 9 ) ;

		if (ContactList.isEmpty()) {
            System.out.println("Contact not found!");
            return;
            }

		input.nextLine();
		ContactList.findFirst();
		
		
		switch(choice) {

		
			//Search by Name.	
			case 1 : 
				
				System.out.print("Enter the contact's name: ");  
                String name = input.nextLine();  

                for (int i = 0; i < ContactList.size ; i++){  
                    if (ContactList.retreive().getContactName().compareToIgnoreCase(name) == 0){  
                        System.out.println("Contact found!");  
                        System.out.println(ContactList.retreive());  
                        return; 
                    }  

                    ContactList.findNext();  
                }

                System.out.println("Contact not found!");
                break;

		    //Search by phone number.	
			case 2 : 
				System.out.print("Enter the contact's phone Number: ");  
                String phoneNumber = input.nextLine();  

                for (int i = 0; i < ContactList.size ; i++){  
                    if (ContactList.retreive().getPhoneNumber().compareToIgnoreCase(phoneNumber) == 0){  
                        System.out.println("Contact found!");  
                        System.out.println(ContactList.retreive());  
                        return;  
                    }  

                    ContactList.findNext();  
                }

                System.out.println("Contact not found!");
                break;

			//Search by Email Address.	
			case 3 : 
			
				System.out.print("Enter the contact's Email Address: ");  
                String email = input.nextLine();  
				
				if(email.length() != 0)
                for (int i = 0; i < ContactList.size ; i++){  
                    if (ContactList.retreive().getAddress().compareToIgnoreCase(email) == 0){  
                        System.out.println("Contact found!");  
                        System.out.println(ContactList.retreive());  
                        return;  
                    }  

                    ContactList.findNext();  
                }

                System.out.println("Contact not found!");
                break;
			
			//Search by Address.
			case 4 : 

				System.out.print("Enter the contact's Address: ");  
                String address = input.nextLine();  

              	if(address.length() != 0)
				for (int i = 0; i < ContactList.size ; i++){  
                    if (ContactList.retreive().getAddress().compareToIgnoreCase(address) == 0){  
                        System.out.println("Contact found!");  
                        System.out.println(ContactList.retreive());  
                        return; 
                    }  

                    ContactList.findNext();  
                }

                System.out.println("Contact not found!");
                break;
			
				
			//Search by Birthday.
			case 5 : 
				
				System.out.print("Enter the contact's birth day: ");  
                String birthDay = input.nextLine();  

				if(birthDay.length() != 0)
                for (int i = 0; i < ContactList.size ; i++){  
                    if (ContactList.retreive().getBirthday().compareToIgnoreCase(birthDay)==0){  
                        System.out.println("Contact found!");  
                        System.out.println(ContactList.retreive());  
                        return;  
                    }  

                    ContactList.findNext();  
                }

                System.out.println("Contact not found!");
                break;
			
			// Exit
			case 6 :
				break;
				
			default :
				System.out.print("Invaild choice, please re enter a vaild choice: ");
				break;
				
		}
		
	}
	


	//================================
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


	//================================
	public void Remove(String name) {
	
		if(ContactList.isEmpty()) {
			System.out.println("\nContact not found.");
			return;
			}

		ContactList.findFirst();
		for(int i=0;i<ContactList.size;i++) {
			if(ContactList.retreive().getContactName().compareToIgnoreCase(name)==0) {
//				System.out.println("Contact Info:");
				ContactList.remove();
				System.out.println("Contact is deleted.");
				return;
			}

			ContactList.findNext();
		}

		System.out.println("\nContact not found.");
	}
		
	

	public void PrintAll() {
		ContactList.findFirst();
		for(int i=0;i<ContactList.size;i++) {
			System.out.println(ContactList.retreive());
			ContactList.findNext();
		}
	}


	
	//================================
	public void EventSchedule() {

		try{
			System.out.print("* Enter event title: ");
			String title = input.nextLine();

			System.out.print("* Enter contact name: ");
			String name = input.nextLine();

			System.out.print("* Enter event start date(MM/DD/YYYY HH:MM): ");
			String startDate = input.nextLine();

			System.out.print("* Enter event end date(MM/DD/YYYY HH:MM): ");
			String endDate = input.nextLine();

			System.out.print("* Enter event location: ");
			String location = input.nextLine();

			// To ensure the user fill the lables.
			if(title.length() == 0 || name.length() == 0 || startDate.length() == 0 || endDate.length() == 0  )
				throw new Exception();
		
			if (!isValidDate(startDate, "MM/dd/yyyy HH:mm") || !isValidDate(endDate, "MM/dd/yyyy HH:mm")) {
				System.out.println("\nInvalid date format. you have to follow this format MM/DD/YYYY HH:MM ");
				return;
			}
		
			Event event = new Event(title,name,startDate,endDate,location);
			
			if(!EventList.isEmpty()) {
			
				EventList.findFirst();
				
				while(event.compareTo(EventList.retreive())>=0) {
					if(event.compareTo(EventList.retreive())==0) {
						System.out.println("\nError : Event Already Exists");
						return;
					}
					else if(!EventList.last()) 
						EventList.findNext();

					else {
						EventList.insertAtEnd(event);
						return;
					}
				}
			}
			
			EventList.insert(event);

	}catch(Exception ex){
		System.out.println(" \n*** title, name, startDate, and, endDate are required. ***  ");
	}
	}


	
	//================================
	public void PrintEventDetails() {
		
		if(EventList.isEmpty()) {
			System.out.println("Your event list is empty!, there is nothing to search.");
			return;
		}

		EventList.findFirst();
		System.out.println("Enter search citeria:");
		System.out.println("1. Contact Name");
		System.out.println("2. Event Title");


		int choice = input.nextInt();
		input.nextLine();

		switch(choice) {

		case 1: 
			System.out.print("Enter Contact Name: "); String contactName=input.nextLine();
			
			for (int i = 0; i < EventList.size ; i++){
	            if (EventList.retreive().getEventTitle().compareToIgnoreCase(contactName)==0){ 
	                System.out.println(EventList.retreive());  
	                return; 
	            }  

	            EventList.findNext();  
	        }
	        return;


		case 2 :
			System.out.print("Enter Event Title: "); 
			String eventTitle=input.nextLine();

			for (int i = 0; i < EventList.size ; i++){
	            if (EventList.retreive().getEventTitle().compareToIgnoreCase(eventTitle)==0){ 
	                System.out.println(EventList.retreive());  
	                return; 
	            }  

	            EventList.findNext();  
	        }

			System.out.println("Event not found!");
	        return;
		}
	}



	//================================
	public void PrintContactsByFirstName() {
	
		if(ContactList.isEmpty()) {
			System.out.println("Your contact list is empty!, there is nothing to search.");
			return;
		}

		try{
			System.out.print("Enter The First Name: ");
			String FirstName = input.nextLine();

			if(FirstName.length() == 0)
				throw new MarshalException();
			

			ContactList.findFirst();

			for (int i = 0; i < ContactList.size ; i++){  
				if (ContactList.retreive().getContactName().substring(0, FirstName.length()).compareToIgnoreCase(FirstName)==0){ 
					System.out.println(ContactList.retreive()); 
				}  

				ContactList.findNext();  
			}

			System.out.println("\nContact not found!");
			
		}catch(MarshalException ex){
			System.out.print("\n** Please enter valid name **");
		}
		
	}

		
	//================================
	public void PrintOrdredEvents() {

		EventList.findFirst();

		for(int i=0;i<EventList.size;i++) {
			System.out.println(EventList.retreive());
			EventList.findNext();
		}
	}

}
