import java.util.Arrays;
import java.util.Collections;


//Generic typing of a Bag
public class Bag<Generic>{
	protected Generic[] items;// The array will be of Generic type and will be called items
	protected final int DEFAULTCAPACITY=52;//The default capacity is 52
	protected int numberOfEntries;//The number of entries
	protected final int length=DEFAULTCAPACITY;//the length of the array will be equivalent to default capacity

	//Initializing my Bag 
	//0 Entries and it will be an array with 52 'spaces'
	public Bag(){
		numberOfEntries=0;	
		items = (Generic[]) (new Object[DEFAULTCAPACITY]);// Its a new Object and hence must be type casted to Generic[]											
	}

	//This checks the number of elements in the array
	//No input. Output:Size of type integer
	public int getCurrentSize(){
		return numberOfEntries;
	}

	//This checks the whether array is full
	//No input. Output:true/false  type: boolean
	public boolean isFull(){
		return(numberOfEntries==DEFAULTCAPACITY);
	}

	//This checks the whether array is empty
	//No input. Output:true/false  type: boolean
	public boolean isEmpty(){
		return(numberOfEntries==0);
	}

	//This adds a new item in array 
	//True false indicates whether operation was successful
	//Input:Item to be added. Output:true/false  type: boolean
	public boolean add(Generic newitem){
		if(!isFull()){//First checks if its full or not
			items[numberOfEntries]=(newitem);
			numberOfEntries++;// Adds item in array
			return true;
		}
		else
			return false;
	}

	//This removes an existing element in the array by looping through entire array to check whether that item exists in the array
	// True false indicates whether operation was successful
	//Input:Item to be removed. Output:true/false  type: boolean
	public boolean remove(Generic item){
		int elementpos=1;//This is to make sure a null is not removed
		if(!isEmpty() && containE(item)){ //First checks whether its empty or not	
			for(int i=0; i<items.length;i++){
				if(item==(items[i])){
					while(items[items.length-elementpos]==null){//while you keep on hitting null go to previous element
						if(isFull())//If the thing is full there is no null so break from loop
							break;
						elementpos++;
					}
					Generic temp=items[items.length-elementpos];//This is to switch the element to be removed and position it at the end of the array
					items[items.length-elementpos]=items[i];//switch
					items[items.length-elementpos]=null;//Now nullify it
					items[i]=temp;//switch
					numberOfEntries--;//Decrease the number of entries
					return true;
				}
			}
		}
		return false;
	}

	//Loops through entire array and nullifies every element. Reduces numberofEntries to 0
	//Input:Nothing Output:Nothing
	public void clear(){
		for(int i=0;i<items.length;i++){
			if(items[i]!=null){//if item is not null then nullify it
				items[i]=null;
				numberOfEntries--;}
		}
	}
	//Loops through entire array and counts the number of times it picks up the element being searched for.
	//Input:item you are looking for  Output: Number of times its spotted
	public int getNumOf(Generic item){
		int count=0;
		if(!isEmpty()){//First check if its empty
			for(int i=0;i<items.length;i++){
				if(items[i]==((item)))
					count++;//increase count of item
			}
		}
		return count;
	}
	//Loops through entire array and counts the number of times it picks up the element being searched for.
	//Input:item you are looking for  Output: True/False
	public boolean containE(Generic item){
		return(((getNumOf(item))>0));
	}


	//This creates a random number based on default capacity then removes that random element
	//Input nothing Output element
	public Generic grab(){
		if(!isEmpty()){
			int elementr=(int)(Math.random()*numberOfEntries);//Random number
			Generic grabbed=items[elementr];//Assign the random number to an element in array
			if(grabbed==null)//if that element is already null repeat
				grab();
			remove(grabbed);//remove that element using the remove method
			return grabbed;
		}
		else
			return null;
	}
	//Outputs the element you are searching for
	//Input number output element
	public Generic output(int item){
		Generic search =items[item];
//		while(item>DEFAULTCAPACITY){
//		search=items[item];
//		}
		return search;
	}
	//Uses Java Collections and shuffles up the array
	//Input None Output None
	public void shuffle(){
		Collections.shuffle(Arrays.asList(items));
	}


}
