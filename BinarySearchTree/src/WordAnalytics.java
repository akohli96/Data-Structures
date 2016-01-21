import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;


public class WordAnalytics {

	//Checks if String is valid
	private static boolean isValid(String element){
		for(int i=0; i<element.length();i++){
			if((element.charAt(i)>=65 && element.charAt(i)<=90) || (element.charAt(i)>=97 && element.charAt(i)<=122))
				return true;
			else return false;
		}
		return true;
	}


	public static void main(String[] args) throws IOException{

		BinaryTree<Word> book = new BinaryTree<Word>();

		String filename="GreatExpectations_Dickens.txt";

		Scanner scan = null;

		Scanner input = new Scanner(System.in);

		//Scan the file
		File file = new File(filename);
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
		}

		// The delimeter and the tokenizer are constructed
		String delim="! @#$%^&*()_+-=/*-+.,.;[]\\<>?:{}\"";
		StringTokenizer tokenizer;

		//Number of words found
		int numOfwords=0;

		//Loop through the entire file
		//Create new tokenizer
		//Get the next word
		//Store in a temp reference
		while(scan.hasNextLine()){
			tokenizer = new StringTokenizer(scan.nextLine(), delim);
			while (tokenizer.hasMoreElements()) {
				String nextword = tokenizer.nextToken().toLowerCase();

				Word temp = new Word(nextword);

				//Check validity
				if(isValid(nextword)){
					numOfwords++;
					//If duplicate found go and increase that duplicates word count
					if(book.search(temp)){
						temp = (Word) book.find(temp);
						temp.inCount();

					}
					//Else insert
					else{
						book.insert(temp);
					}
				}

			}
		}

		//Create array of words
		System.out.println("Your book is being analysed. Please wait...");
		Word[] Sorted = new Word[book.getNodes()];

		//Throw everything into the Array of Words
		for(int i=0;i<book.getNodes();i++){
			Node temp= book.dInorder().getAt(i);
			Sorted[i]=((Word) temp.getInfo());
		}


		//Sort it by frequency
		Arrays.sort((Sorted),  Word.FrequencyCompare);



		System.out.println();

		//Name of file to output
		String outputFile="Analytics.txt";

		System.out.println("DONE!");

		System.out.println();

		System.out.println("The total number of words detected : " + numOfwords);
		System.out.println("The total number of unique words detected : " + book.getNodes());
		
		System.out.println();
		
		
		System.out.println("The five most frequent words were :");
		for(int i=Sorted.length-1;i>Sorted.length-6;i--){
			System.out.println(Sorted[i]);
		}

		System.out.println();

		System.out.println("THe least frequent words were :");
		for(int i=0;i<5;i++){
			System.out.println(Sorted[i]);
		}

		System.out.println();
		System.out.println("Enter 1 to see frequency of a particular word");
		System.out.println("Enter 2 to see output the frequency analysis to a file");

		//Create the object to write to the file
		PrintWriter outputWriter = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
		int choice = input.nextInt();
		String word=null;

		if(choice==1){
			System.out.println("Please enter the word that you want to know about");
			System.out.println("Enter 0 to quit");

			input.nextLine();
			word =input.nextLine().toLowerCase();
			while(!word.equals("0")){
				Word temp = new Word(word);
				if(book.search(temp)){
					System.out.println("This word exists");
					System.out.println(book.find(temp));
				}
				else
					System.out.println("The word does not exist");
				System.out.println("Enter 0 to quit");
				word =input.nextLine();
			}
		}

		else{
			System.out.println("Enter 1 to output as a sorted list(alphabetically).");
			System.out.println("Enter 2 to output as a sorted list(frequency wise). ");
			System.out.println("Enter 3 to be boring and output as it is.");
			int outputc= input.nextInt();

			if(outputc==1){
				System.out.println("PLEASE WAIT!");
				for(int i=0;i<book.getNodes();i++){
					outputWriter.println(book.dInorder().getAt(i));
				}
				System.out.println("DONE!");
			}

			else if(outputc==2){
				System.out.println("PLEASE WAIT!");
				for(int i=0;i<book.getNodes();i++){
					outputWriter.println(Sorted[i]);
				}
				System.out.println("DONE!");
			}

			else{
				System.out.println("PLEASE WAIT!");
				for(int i=0;i<book.getNodes();i++){
					outputWriter.println(book.dPreorder().getAt(i));
				}
				System.out.println("DONE!");
			}
			
			
			
		}



		//Flush all the memory
		outputWriter.close();
		scan.close();
		input.close();
	}

}