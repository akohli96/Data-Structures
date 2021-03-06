
public class Hash {
	
	private final double LOAD=0.5; //LoadFactor
	private int size=0; 
	private int actualSize;
	private NodeH[] Table; //Array of Nodes

	private static int HASHCHOSEN; // HASHFUNCTION CHOSEN
	

	//Constructor
	//Input:TableSize and HashFunctionType
	//Output:Nothing
	public Hash(int tablesize, int hashfunc){
		size=tablesize;
		actualSize=0;
		HASHCHOSEN=hashfunc;
		Table = new NodeH[size];//Create an array of Nodes with specified size
		
		//Loop through entire array and nullyify the whole list
		for(int i=0;i<Table.length;i++){
			Table[i]=new NodeH(NodeH.EMPTY,null);
		}
	}
	

	//Nullify
	//Input:Nothing 
	//Output:Removes all data and sets flag to empty for all the nodes
	private void nullify(){
		for(int i=0;i<Table.length;i++){
			Table[i].setBoth(NodeH.EMPTY,null);
		}
	}
	

	//Get legit size
	//Input:Nothing 
	//Output:Returns int of actual size
	public int getActualsize(){
		int counter = 0;
		for(int i=0;i<size;i++){//Loop through list and check whether its occupied
			if (Table[i].getFlag()==NodeH.OCCUPIED){
				counter++;//Increase counter if occupied
			}
		}
		actualSize=counter;//actualSize is = counter
		return actualSize;
	}
	
	
	//Print all actual values
	//Input:Nothing 
	//Output:Nothing //Prints all values stored in hashtable
	public void getAll(){
		for(int i=0;i<size;i++){
			if(Table[i].getInfo()!=null)
				System.out.println(Table[i].toString());
		}
	}
	
	//Get index of searched object
	//Input:Starting index and the string being searched for
	//Output:Returns int of index where the probe stopped
	private int probeSearch(int startingindex,String search){
		int newhash=startingindex;
		int power=1;
		//While you hit a deleted or an occupied flag and you dont hit the actual string you are searching for
		//Keep probing
		while((Table[newhash].getFlag() == NodeH.DELETED || Table[newhash].getFlag() == NodeH.OCCUPIED) && (!Table[newhash].equals(search)) ){
			newhash= (startingindex + (int) (( Math.pow(power, 2))))%size; //Quadratic probe
			power++;
		}
		return newhash; //Return index where last probe reached
	}
	
	//Get index of available index
	//Input:Starting index 
	//Output:Returns int of index where the probe stopped(Available index)
	private int probeInsert(int startingindex){
		int newhash=startingindex;
		int power=1;
		while(Table[newhash].getFlag() == NodeH.OCCUPIED ){//While hit occupied, probe
			newhash= (startingindex + (int) (( Math.pow(power, 2))))%size; //Quadratic probe
			power++;
		}
		return newhash; //Return available index
	}
	
	
	//Check whether hashtable is full
	//Input:Nothing
	//Output:Boolean whether its full or not
	public boolean isFull(){
		return (getActualsize()==getSize());
	}
	
	//Get size of hash table
	//Input:Nothing
	//Output:Returns size of hash table
	public int getSize(){
		return size;
	}
	
	//Search whether the string exists or not
	//Input:String
	//Output:boolean
	public boolean search(String input){
		return(searchf(input).equals(input));
	}
	
	//Delete the word
	//Input:String
	//Output:boolean
	public boolean delete(String input){
		if(search(input)){//search for it
			searchf(input).setBoth(NodeH.DELETED, null);//If it exists, go to node and nullify it
			actualSize--;//decrease size
			return true;
		}
		else{
			return false;
		}
	}
	
	
	//Insert
	//Input:String
	//Output:boolean
	public boolean put(String input){
		if(isHeavy()){//If load is heavy
			rehash();//Rehash
		}
		NodeH temp = new NodeH(NodeH.OCCUPIED,input);
		int key;
		//On the basis of chosen hash hash the string
		if(HASHCHOSEN==0){
			key=hashBasic(input);
		}
		else if(HASHCHOSEN==1){
			key=hashShift(input);
		}
		else{
			key=hashPolynomial(input);
		}
		//Find the available hashkey
		int newhash=probeInsert(key);
		
		Table[newhash]=temp;//Add to Hash Table
		actualSize++;//Increase size
		return true;
	}
	
	//Returns a node that was searched for
	//Input:String being searched for
	//Output:Returns Node
	private NodeH searchf(String input){
		int key;
		//Hash the input based on hash function selected during the constructor
		if(HASHCHOSEN==0){
			key=hashBasic(input);
		}
		else if(HASHCHOSEN==1){
			key=hashShift(input);
		}
		else{
			key=hashPolynomial(input);
		}
		//The newhash key will be generated by probesearch 
		int newhash=probeSearch(key,input);
		
		//Create an empty node
		NodeH result = new NodeH(NodeH.EMPTY,null);
		//Set the nodes data and flag as the things found in the searched Node
		result.setBoth(Table[newhash].getFlag(), Table[newhash].getInfo());
		Table[newhash]=result; //Hash the result
		return result;
	}
	
	//Basic hashfunction
	//Input:String
	//Output:Hash key
	private int hashBasic(String in){
		int input=0;
		for(int i=0;i<in.length();i++){//Add to the value on the basis of the ascii value
			input+=in.charAt(i);
		}
		return (input % size);//Mod it so it fits in array
	}

	//Bitshift hashfunction
	//Input:String
	//Output:Hash key
	private int hashShift(String in){
		int input=0;
		for(int i=0;i<in.length();i++){
		int current = in.charAt(i);
		input+=  ((current << 5) | (current >> 27));//Bitshift operation
		}
		return (input % size);
	}
	
	//Polynomial hashfunction
	//Input:String
	//Output:Hash key
	private int hashPolynomial(String in){
		int input=0;
		int powerc=in.length()-1;
		for(int i=0;i<in.length();i++){
			int j=(int) (in.charAt(i)*Math.pow(2, powerc));//Polynomial addition of ascii values
			input+=j;
			powerc--;
		}

		return (input % size);
	}
	
	//Check load factor
	//Input:Nothing
	//Output:Whether load is high or not
	private boolean isHeavy(){
		return (((double)actualSize/size)>LOAD);
	}
	
	//Dump all the nodes with values in a queue
	//Input:Nothing
	//Output:Queue
	public Queue dump(){
		Queue temp = new Queue();
		for(int i=0;i<size;i++){//Loop through entire thing
			if(Table[i].getInfo()!=null){//If has some value
				temp.enQ((Table[i].getInfo()));//Enqueue that into a temp Queue
			}
		}
		return temp;
	}
	
	//Rehashses the entire Table
	//Input:Nothing
	//Output:Nothing
	private void rehash(){
		int newsize = size*2;//Gets newsize
		newsize=getNextprime(newsize);//Makes sure its prime
		Queue elements=dump();//Dumps all the elements into a queue
		nullify();//Makes everything null
		size=newsize;//Changes size
		actualSize=0;//Resets actualsize
		Table = new NodeH[newsize];//Creates a new table with the newsize
		for(int i=0;i<Table.length;i++){
			Table[i]=new NodeH(NodeH.EMPTY,null);//Loops through it and nullifies it
		}
		int elementsSize=elements.getSize();
		for(int i =0; i<elementsSize;i++){
			put(elements.front().getInfo());//Loops through the queue and adds everything into newhash table
			elements.deQ();//Pops from it
		}
		
	}
	
	//Gets the next closest prime value
	//Input:Starting number
	//Output:Next closest prime value
	private int getNextprime(int insert){
		insert+=1;
		while(!isPrime(insert)){//While you dont hit a prime, check if the sucessor is prime
			insert++;
		}
		return insert;
	}

	//Checks wheter a number is prime or not
	//Input:The number
	//Output:Is prime
	public static boolean isPrime(int num){
		if(num%2==0)
			return false;//If divides by 2 its not prime
		
		for(int i=3;i<=Math.sqrt(num);i+=2){
			if(num%i==0)//Loop through the odd values till the squareroot and if its divisible by a number till then, its not prime
				return false;
		}
		
		return true;
	}

}
