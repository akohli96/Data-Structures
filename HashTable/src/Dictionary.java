import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Dictionary {

	public static void main(String [] args){

		int hashfunction;
		int tablesize;
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the tablesize. Make sure its prime and greater than 10!");
		tablesize=input.nextInt();

		//Tablesize must be prime and greater than 11
		while(tablesize<11 || (!Hash.isPrime(tablesize))){
			System.out.println("Reenter please");
			tablesize=input.nextInt();
		}

		//Hashfunction entered
		System.out.println("Please enter the hashfunction. 0 for basic, 1 for bitshift and any other number for polynomial");
		hashfunction=input.nextInt();
		while(hashfunction<0){
			System.out.println("Reenter please");
			hashfunction=input.nextInt();
		}
		Hash hashtable = new Hash(tablesize, hashfunction);

		String insert=null;

		//Queues created for storing the words that were inserted,searched and deleted
		Queue inserted = new Queue();
		Queue searched = new Queue();
		Queue deleted = new Queue();
		
		//Enters words to dictionary. O to stop
		System.out.println("Please enter words into the hashtable! Enter 0 to stop");
		do {
			insert=input.nextLine();
			inserted.enQ(insert);//Add it to queue
			hashtable.put(insert);//Add to hashtable
		} while (!insert.equals("0"));


		//Search the dictionary. 0 to stop. Add the searched word in queue
		System.out.println("Please enter words to search for in the hashtable. Enter 0 to stop.");
		do {
			insert=input.nextLine();
			String result=null;
			//If the word is found, then print so otherwise print that it was not found
			if(hashtable.search(insert)){
				 result=insert + " is present in the hashtable.";
				searched.enQ(result);//Add to queue
				System.out.println(result);
			}
			else{
				result=insert + " is NOT present in the hashtable.";
				searched.enQ(result);//Add to queue
				System.out.println(result);
			}
		} while (!insert.equals("0"));


		//Delete from dictionary. 0 to stop. Add the deleted word to queue
		System.out.println("Please enter words to delete for in the hashtable. Enter 0 to stop.");
		do {
			insert=input.nextLine();
			String result=null;
			//If can delete, then delete it
			if(hashtable.delete(insert)){
				 	result=insert + " was deleted in the hashtable.";
					deleted.enQ(result);//Add to queue
					System.out.println(result);
			}
			//Otherwise its not found
			else{
				result=insert + " is NOT present in the hashtable.";
				deleted.enQ(result);// Its not present in table,cant delete
				System.out.println(result);
			}
		} while (!insert.equals("0"));


		PrintWriter outputWriter =null;
		String outputFile="Analysis.txt";
		//Create file writer
		try {
		 outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//REKT
			e.printStackTrace();
		}

		//Take the hashtable and store values in a queue
		Queue result= hashtable.dump();
		int actualSize= result.getSize(); //Size of hashtable
		int maxinsert= inserted.getSize();//Size of attempted insert
		int delet= deleted.getSize();//Size of attempted delete
		int found = searched.getSize();//Size of search

		
		System.out.println("Writing the analysis to the file");
		//Write the inserted words to file by first copying the front of queue and then popping it,then dequeue
		outputWriter.println("ALL INSERTED WORDS: ");
		for(int i=0;i<maxinsert;i++){
			outputWriter.println(inserted.front().getInfo());
			inserted.deQ();
		}
		
		//Write the serached words to file by first copying the front of queue and then popping it,then dequeue
		outputWriter.println("ALL SEARCHED WORDS: ");
		for(int i=0;i<found;i++){
			outputWriter.println(searched.front().getInfo());
			searched.deQ();
		}
		
		//Write the attempted deleted words to file by first copying the front of queue and then popping it,then dequeue
		outputWriter.println("ALL ATTEMPTED DELETED WORDS: ");
		for(int i=0;i<delet;i++){
			outputWriter.println(deleted.front().getInfo());
			deleted.deQ();
		}
		
		//Write the hashtable words to file by first copying the front of queue and then popping it,then dequeue
		outputWriter.println("ALL WORDS IN HASHTABLE: ");
		for(int i=0;i<actualSize;i++){
			outputWriter.println(result.front().getInfo());
			result.deQ();
		}
		
		System.out.println("DONE!");

		//Flush memory
		input.close();
		outputWriter.flush();
	}
}
