package phoneBook;

import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Phonebook e = new Phonebook();
		


        int choice=0;

	    System.out.println("\nWelcome to the Swift Phonebook!");
		do {

			System.out.println("\n\nPlease choose an option:\n");
			System.out.println("1. Add a contact");
			System.out.println("2. Search for a contact");
			System.out.println("3. Delete a contact");
			System.out.println("4. Schedule an event");
			System.out.println("5. Print event details");
			System.out.println("6. Print contacts by first name");
			System.out.println("7. Print all events alphabetically");
			System.out.println("8. Exit\n");
			System.out.print("Enter your chioce: ");

			boolean VaildInput=false;

			while(!VaildInput) {

				try {
					choice = input.nextInt();
					VaildInput = true;
				}catch(java.util.InputMismatchException ex){
					System.out.print("Invaild choice, please re enter a vaild choice: ");
					input.nextLine();
				}
			}
			input.nextLine(); //when entering the value x, we click "enter", this field catches that "enter" so it wont disturb the program.
			
			
			switch(choice) {
			case 1 : 
				e.AddContact();
				break;
					
			case 2 :
				e.Search();
				break;
				
			case 3 :
				System.out.print("Enter contact's name: ");
				e.Remove(input.nextLine());
				break;

			case 4 :
				e.EventSchedule();
				break;
				
			case 5 :
				e.PrintEventDetails();
				break;

			case 6 :
				e.PrintContactsByFirstName();
				break;

			case 7 :
				e.PrintOrdredEvents();
				break;

			case 8 :
				System.out.println("Goodbye!");
				break;

			default :
				System.out.println("\nPlease Enter The Correct Number");
				break;
			}
			
			
		}while(choice!=8);	
    }
}
