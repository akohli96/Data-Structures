import java.util.Scanner;

//Ayush Kohli
//SIU853253156

public class Main {
	static Bag<Card> Deck = new Bag<Card>();//Bag that stores CardObjects
	private static int PlayerTotal=0;//Inintializing total
	private static int DealerTotal=0;//Inintializing total
	private static int Cardsdrawn=0;//Cardsdrawn
	private static final int BLACKJACK=21;//number to get to blackjack
	private static int PlayerIndex=0;//Used to count players cards
	private static int DealerIndex=0;//Used to count dealers cards
	static Scanner scan= new Scanner(System.in);
	static int [] playerhand= new int[6];//Array for storing players cards
	static int [] dealerhand= new int[6];//Array for storing dealers cards
	private static int MustHit=16;//If dealers total is less than this, then dealer must hit

	public static void main(String [] args){
								Bag grocerybag = new Bag();
								int a=0;
								for(int i=0;i<grocerybag.length;i++){
									System.out.print(grocerybag.add(a) + " ");
									a++;
								}
									System.out.println();
									grocerybag.shuffle();
								for(int i=0;i<grocerybag.length;i++)
									System.out.print(grocerybag.output(i) + " ");	
								System.out.println();
								System.out.println(grocerybag.remove(5));
								System.out.println(grocerybag.grab());
								System.out.println(grocerybag.grab());
								System.out.println(grocerybag.getCurrentSize());
								System.out.println(grocerybag.containE(5));
								System.out.println(grocerybag.add(90));
								System.out.println(grocerybag.add(90));
								for(int i=0;i<grocerybag.length;i++)
									System.out.print(grocerybag.output(i) + " ");	
								System.out.println();
								System.out.println(grocerybag.getNumOf(90));
								System.out.println(grocerybag.add(34));
								System.out.println(grocerybag.isFull());
								for(int i=0;i<grocerybag.length;i++)
									System.out.print(grocerybag.output(i) + " ");	
								System.out.println();
								grocerybag.clear();
								System.out.println(grocerybag.getCurrentSize());
								System.out.println(grocerybag.isEmpty());
								for(int i=0;i<grocerybag.length;i++)
								System.out.print(grocerybag.output(i) + " ");	
								System.out.println();



//Storing stuff into my deck
		for(int i =0;i<52;i++){
			for(int j=0;j<4;j++){
				if(i==0)
					Deck.add(new Card('A'));
				else if(i==10){
					Deck.add(new Card('J'));
				}
				else if(i==11)
					Deck.add(new Card('Q'));
				else if(i==12)
					Deck.add(new Card('K'));
				else
					Deck.add(new Card(i+1));
			}
		}

//printing that out
		for(int i =0;i<Deck.length;i++){
			System.out.print(Deck.output(i) + " ");
		}

		System.out.println();
		Deck.shuffle();
		for(int i =0;i<Deck.length;i++){
			System.out.print(Deck.output(i) + " ");
		}


		System.out.println();
		boolean result=false;//if a result has occured
		System.out.println("The dealer draws " + drawCard(1) + "and a hidden card");//dealer draws card
		System.out.println("The dealers hidden card was " + drawCard(1));//dealers hidden card
		System.out.println("The player draws " + drawCard(0) +" and  " + drawCard(0) );// player draws 2 cards
		System.out.println("The dealers total " + Addup(1));//dealer total
		System.out.println("The players total "+  Addup(0));//playertotal
		String hit= scan.nextLine();
		if(hit.equalsIgnoreCase("hit")){//while player draws
			System.out.println("You drew " +drawCard(0));//draw a card
			checkWin();
			hit=scan.nextLine();
		}






	}
	private static String drawCard(int person){// if person==0 player draws, else dealer draws
		if(playerhand[0]==1){//if the first thing drawn is an ace drawagain
			drawCard(0);
		}
		Card drawn=Deck.grab();// grab card from deck
		Cardsdrawn=drawn.getNum();//get interger value
		if(Cardsdrawn==1 && person==0){
			System.out.println("You drew an Ace. Would you like to set it to 1 or 11.");
			int num;		
			do{
				num=scan.nextInt();//set value for ace
			}while(num!=1 && num!=11);
			Cardsdrawn=drawn.setNum(num);//set value for ace
		}
		String Carddrawn=drawn.toString();//change the drawn to string and return

		if(person==0){
			PlayerTotal+=Cardsdrawn;//if player draws it, add to player total
			//if(PlayerTotal>=BLACKJACK)
			//checkWin();
			playerhand[PlayerIndex]=Cardsdrawn;//store the number value in player array
			PlayerIndex++;//increase hand
		}
		else{//repeat for dealer
			DealerTotal+=Cardsdrawn;
			//if(DealerTotal>=BLACKJACK)
			//checkWin();
			dealerhand[DealerIndex]=Cardsdrawn;
			DealerIndex++;
		}
		return Carddrawn ;
	}//add stuff up
	private static int Addup(int person){
		if (person==0)
			return PlayerTotal;
		else
			return DealerTotal;
	}
	private static Card indexCard(int i){
		return Deck.output(i);
	}

	private static boolean checkWin(){
		if(PlayerTotal>BLACKJACK){
			System.out.println(" Your total is greater than 21. You lose");
			return false;
		}
		else if(PlayerTotal==BLACKJACK){
			System.out.println(" Your total is 21. You win");
			return true;
		}
				else if(DealerTotal>BLACKJACK){
					System.out.println(" Delars total is greater than 21. You win");
					return true;
				}
				else if(PlayerTotal<=BLACKJACK){
					System.out.println(" Your total is less than 21. You win");
					return true;
				}
				else if(PlayerTotal>=DealerTotal){
					System.out.println(" Your total is greater than dealers total. You win");
					return true;
				}
				else if (PlayerTotal<DealerTotal){
					System.out.println(" Your total is less than dealers total. You lose");
					return false;
				}
		else{
			System.out.println("My code is messed up");
			return false;
		}

	}

}


