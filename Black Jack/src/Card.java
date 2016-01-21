
public class Card
{
	private int number;// the number you are tossing in
	private char letter;// if you are storing an A,J,Q,K
	private  int c;// this is to figure out whether its a letter or numberr
	public Card( int number){
		this.number=number;
		c=0;//its a number
	}
	public Card( char letter){
		this.letter=letter;
		c=1;//its a number
	}

	//	public String toString(){
	//		return (letter + " ");	
	//	}

	//loops through and changes all the aces, jacks, queens,kings to what i want
	public int getNum(){
		if(c==1){
			switch (letter) {
			case 'A':
				number=1;
				break;
			case 'J':
				number=10;
				break;
			case 'Q':
				number=10;
				break;
			case 'K':
				number=10;
				break;
			default:
				break;
			}
		}
		return number;
	}
	//this is to decide whether i want an ace to act as a 1 or 11
	public int setNum(int number){
		this.number=number;
		return number;
	}
	
	public String toString(){
		if(c==0)// checks whether that card was letter or not and returns the respective value
			return (number + " ");	
		else
			return(letter + " ");
	}
}

