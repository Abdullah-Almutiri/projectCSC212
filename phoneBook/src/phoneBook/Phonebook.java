package phoneBook;
import java.util.Scanner;
public class Phonebook {
	//The whole Start function represent the main, to start the program just create main class and call the Start function.
	void Start(){
		Scanner input = new Scanner(System.in);
		int x;
		do {
			
			
			System.out.println("Please choose an option:");
			System.out.println("1. Add a contact");
			System.out.println("2. Search for a contact");
			System.out.println("3. Delete a contact");
			System.out.println("4. Schedule an event");
			System.out.println("5. Print event details");
			System.out.println("6. Print contacts by first name");
			System.out.println("7. Print all events alphabetically");
			System.out.println("8. Exit");
			System.out.print("\nPlease choose an option: ");
			x = input.nextInt();
			String leftover = input.nextLine(); //when entering the value x, we click "enter", this field catches that "enter" so it wont disturb the program.
			
			
			if (x < 1 || x > 8) {
				x=0;
				System.out.println("\nPlease Enter The Correct Number");
			}
			
			
			if(x==1) {
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
				//Add the contact c to the linked list
			}
			
			
			if(x==2) {/**Calling the search function in the LinkedListADT class**/}
			
			
			if(x==3) {/**Calling the delete function in the LinkedListADT class**/}
			
			
			if(x==4) {/**Calling the Schedule function in the LinkedListADT class**/}
			
			
			if(x==5) {/**Calling the search + print function in the LinkedListADT class**/}
			
			
			if(x==6) {/**Calling the search + print function in the LinkedListADT class**/}
			
			
			if(x==7) {/**Calling the sort + print function in the LinkedListADT class**/}
			
			
			if(x==8) {
				System.out.println("Goodbye!");
			}
			
			
		}while(x!=8);
		
		
	}
	//it should be more methods down below, but all of them depends on the nature of the Linked List we are dealing with. so we have to implement it first.
}
