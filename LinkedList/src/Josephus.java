import java.util.Scanner;


public class Josephus {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinkedList list = new LinkedList(); // Make a linked list
		Integer [] testing = new Integer[10];// Create an array of Integers
		for(int i=0;i<testing.length;i++){
			testing[i]=i+1; //adding elements into Integer array
			list.addTotail(testing[i]); // adding Integer elements into list
		}
		
		System.out.println(list.display()); 
		
		System.out.println(list.getSize());
		
		list.addTohead(10);
		
		System.out.println(list.display());
		
		System.out.println(list.getSize());
		
		list.insertAt(3, 3);
		
		System.out.println(list.display());
		
		list.deleteAt(5);
		
		System.out.println(list.display());
		
		list.removeFromtail();
		
		System.out.println(list.display());
		
		System.out.println(list.getHead() + " " + list.getTail());
		
		list.removeFromhead();
		
		list.removeFromhead();
		
		System.out.println(list.display());
		
		System.out.println(list.find(3));
		
		list.clear();
		
		System.out.println(list.isEmpty());
		
		System.out.println(list.getSize());
		
		System.out.println(list.display());
		
		System.out.println("Enter something to see the Josephus problem in motion");
		
		Scanner input = new Scanner(System.in);
		input.next();
		
		System.out.println("Please enter the number of people that will stand around in the circle");
		int numofpeople=input.nextInt(); // Number of people in the circle
		
		System.out.println("Please enter your skip count(in terms of index)");
		final int skipcount=input.nextInt()-1; // Number of people to skip
		
		input.close();
		
		Integer[] people = new Integer[numofpeople];// Array of Integer class variables
		for(int i=0;i<people.length;i++){
			people[i]=i+1; // Add ints to Integer class
			list.addTotail(people[i]); // Add Integers to list
		}
		
		System.out.println(list.display());
		int personindex=0; // The index to kill
		while(list.getSize()>1){
			personindex=(personindex + skipcount)%list.getSize(); // On the basis of the size the index changes
			list.deleteAt(personindex);
			System.out.println(list.display());
		}
			
			
		
			
	}

}
