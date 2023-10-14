package phoneBook;
import java.util.Scanner;
public class PhoneBook {
	//The whole Start function represent the main, to start the program just create main class and call the Start function.
	static LinkedListADT<Contact> ContactList = new LinkedListADT<Contact>();
	static LinkedListADT<Event> EventList = new LinkedListADT<Event>();
	static Scanner input = new Scanner(System.in);
	void Start(){
	    int choice=0;
	    System.out.println("Welcome to the Linked Tree Phonebook!");
		do {
			System.out.print("Please choose an option:"
					+"\n1. Add a contact"
					+"\n2. Search for a contact"
					+"\n3. Delete a contact"
					+"\n4. Schedule an event"
					+"\n5. Print event details"
					+"\n6. Print contacts by first name"
					+"\n7. Print all events alphabetically"
					+"\n8. Exit"
					+"\n9. Print the current linked list( temporary choice ) "
					+"\nPlease choose an option: ");
			boolean VaildInput=false;
			while(!VaildInput) {
				try {
					choice = input.nextInt();
					VaildInput = true;
				}catch(java.util.InputMismatchException e){
					System.out.print("Invaild choice, please re enter a vaild choice: ");
					input.nextLine();
				}
			}
			input.nextLine(); //when entering the value x, we click "enter", this field catches that "enter" so it wont disturb the program.
			
			
			switch(choice) {
			case 1 : 
				AddContact();
				System.out.println();
				break;
					
			case 2 :
				Search();
				break;
				
			case 3 :
				System.out.print("Enter contact's name: ");
				Remove(input.nextLine());
				break;
			case 4 :
				break;
			case 5 :
				break;
			case 6 :
				break;
			case 7 :
				break;
			case 8 :
				System.out.println("Goodbye!");
				break;
			case 9 :
				PrintAll();
				break;
			default :
				System.out.println("\nPlease Enter The Correct Number");
				break;
			}
			
			
		}while(choice!=8);	
	}
	public void AddContact() {
		System.out.print("\nEnter the contact's name: ");
		String Name = input.nextLine();
		System.out.print("Enter the contact's phone number: ");
		String Number = input.nextLine();
		System.out.print("Enter the contact's email address: ");
		String EmailAddress = input.nextLine();
		System.out.print("Enter the contact's address:");
		String Address = input.nextLine();
		System.out.print("Enter the contact's birthday: ");
		String BirthDay = input.nextLine();
		System.out.print("Enter any notes for the contact: ");
		String Notes = input.nextLine();
		Contact C = new Contact(Name,Number,EmailAddress,Address,BirthDay,Notes);
		if(!ContactList.isEmpty()) {
			ContactList.findFirst();
			while(C.compareTo(ContactList.retreive())>=0) {
				if(C.compareTo(ContactList.retreive())==0) {
					System.out.println("\nError : Contact Already Exists");
					return;
				}
				else if(!ContactList.last()) ContactList.findNext();
				else {
					ContactList.insertAtEnd(C);
					return;
				}
			}
		}
		ContactList.insert(C);
	}
	
	public void Search() {
		int choice=0;
		boolean VaildInput = false;
		System.out.println("Enter search criteria:"
						+  "\n1. Name"
						+  "\n2. Phone Number"
						+  "\n3. Email Address"
						+  "\n4. Address"
						+  "\n5. Birthday"
						+  "\n6. Cancel");
		do{
			try {
				choice = input.nextInt();
				VaildInput = true;
			}catch(java.util.InputMismatchException e){
				System.out.print("Invaild choice, please re enter a vaild choice: ");
				input.nextLine();
			}
		}while(!VaildInput&&choice>0&&choice<9);
		if (ContactList.isEmpty()) {
            System.out.println("Contact not found!");
            return;
            }
		input.nextLine();
		ContactList.findFirst();
		switch(choice) {
			case 1 : 
				
				System.out.print("Enter the contact's name: ");  
                String name = input.nextLine();  
                for (int i = 0; i < ContactList.size ; i++)  
                {  
                    if (ContactList.retreive().getContactName().compareToIgnoreCase(name)==0)  
                    {  
                        System.out.println("Contact found!");  
                        System.out.println(ContactList.retreive());  
                        break;  
                    }  
                    ContactList.findNext();  
                }
				break;
				
			case 2 : 
				System.out.print("Enter the contact's phone Number: ");  
                String phoneNumber = input.nextLine();  
                for (int i = 0; i < ContactList.size ; i++)  
                {  
                    if (ContactList.retreive().getPhoneNumber().compareToIgnoreCase(phoneNumber)==0)  
                    {  
                        System.out.println("Contact found!");  
                        System.out.println(ContactList.retreive());  
                        break;  
                    }  
                    ContactList.findNext();  
                }
				break;
				
			case 3 : 
				System.out.print("Enter the contact's Email Address: ");  
                String email = input.nextLine();  
                for (int i = 0; i < ContactList.size ; i++)  
                {  
                    if (ContactList.retreive().getEmailAddress().compareToIgnoreCase(email)==0)  
                    {  
                        System.out.println("Contact found!");  
                        System.out.println(ContactList.retreive());  
                        break;  
                    }  
                    ContactList.findNext();  
                }
				break;
				
			case 4 : 
				System.out.print("Enter the contact's Address: ");  
                String address = input.nextLine();  
                for (int i = 0; i < ContactList.size ; i++)  
                {  
                    if (ContactList.retreive().getAddress().compareToIgnoreCase(address)==0)  
                    {  
                        System.out.println("Contact found!");  
                        System.out.println(ContactList.retreive());  
                        break;  
                    }  
                    ContactList.findNext();  
                }
				break;
				
			case 5 : 
				System.out.print("Enter the contact's birth day: ");  
                String birthDay = input.nextLine();  
                for (int i = 0; i < ContactList.size ; i++)  
                {  
                    if (ContactList.retreive().getBirthDay().compareToIgnoreCase(birthDay)==0)  
                    {  
                        System.out.println("Contact found!");  
                        System.out.println(ContactList.retreive());  
                        break;  
                    }  
                    ContactList.findNext();  
                }
				break;
				
			case 6 :
				return;
				
			default :
				System.out.print("Invaild choice, please re enter a vaild choice: ");
				break;
				
		}
		
	}
	
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
//		
//	}
	//it should be more methods down below, but all of them depends on the nature of the Linked List we are dealing with. so we have to implement it first.
}
