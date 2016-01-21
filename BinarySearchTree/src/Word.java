
import java.util.Comparator;

public class Word implements Comparable<Word> {
	private String word;
	private int time;
	
	//Constructor stores a word and count
	public Word(String word){
		this.word=word;
		time=1;
	}
	
	//Getter
	public String getWord(){
		return this.word;
	}
	
	//Setter
	public void setWord(String word){
		this.word=word;
	}
	
	//GetCount
	public int getCount(){
		return this.time;
	}
	
	//Increase count
	public int inCount(){
		this.time++;
		return this.time;
	}
	
	//Compare to method
	@Override
	public int compareTo(Word other) {
		return(this.word.compareTo(other.word));
//		if(this.word.compareTo(other.word)==0){
//			return 0;
//		}
//		else if(this.word.compareTo(other.word)<1)
//			return -1;
//		else
//			return 1;
	}
	
	//Comparator to sort on frequency
	public static Comparator<Word> FrequencyCompare = new Comparator<Word>() {
		
		public int compare(Word word1, Word word2){
			return (word1.getCount()-word2.getCount());
		}
	};
	
	//String representation
	public String toString(){
		return "The word : " + word + " appeared " + time + " times";
	}
	
	
}
