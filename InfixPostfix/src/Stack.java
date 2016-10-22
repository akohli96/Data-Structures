
public class Stack<Generic> {

	private int counter;//Keeping track of nodes

	private Node head;
	private Node tail;

	//Constructor
	public Stack(){
		head=null;
		tail=null;
		counter=0;
	}
	
	//Checking whether linked list is empty
	//Input:Nothing 
	//Output: True/False
	public boolean isEmpty(){
		return(getSize()==0);
	}
	
	//Get size
	//Input:Nothing
	//Output: Integer
	public int getSize(){
		return counter;
	}

	//Adding to the tail
		//Input:Information 
		//Output:Nothing
		public void push(Generic info){
			Node newtail= new Node<Generic>(head, info);//make newtail point to head and store data
			Node temp=head;//create temp point to head
			//Basically like add to head
			if(isEmpty()){
				head=newtail;
				newtail.setNextnode(head);
				tail=head;
			}
			else{
				tail.setNextnode(newtail);//make tail point to newtail
				tail=newtail;//Reassign tail
			}
			counter++;
		}

		//Remove from tail
		//Input:Nothing 
		//Output:Nothing
		public void pop(){

			Node temp=head;
			if(isEmpty())
				return;
			else if(head==tail){
				head=null;
				tail=null;
			}
			else{
				while(temp.getNextnode()!=tail)//Move until he hit tail
					temp=temp.getNextnode();
				temp.setNextnode(head);//set the previousTotail point to head
				tail=temp;//Reassign tail
				tail.setNextnode(head);//Set the new tail pointing to head
			}
			counter--;
		}
	
	//Find the element
	//Input:Information 
	//Output:True/False
	public boolean find(Generic info){
		boolean found=false;
		int size=1;
		Node travel=head;

		while(!found && size<getSize()){// Loop until you either hit the element or till counter is less than size
			if(travel.getInfo().equals(info)){
				found=true;
			}
			travel=travel.getNextnode();
			size++;
		}

		return found;
	}
	
	//Basic getter method that returns the tail
	public Node getTail(){
		return tail;
	}
	
	//Basic getter method that returns the head
	public Node getHead(){
		return head;
	}
	
	//Nullifies the entire list
	//Input: Nothing
	//Output: Nothing
	public void clear(){
		while(!isEmpty())//Till the size of the list is !=0 keep on removing elements from the linked list
			pop();
	}

	//Displays the entire list
	//Input: Nothing
	//Output: All my elements as a string called Output
	public String display(){
		String output="";
		Node temp=head;
		if(!isEmpty()){
			while(temp.getNextnode()!=null){//loop through the entire list
				//System.out.println(temp.getInfo());
				output+=" " + temp.getInfo() + " ";//add the info to the the output
				temp=temp.getNextnode();
			}
			//output+= " " + tail.getInfo() + " ";
		}
		else
			output="";
		return output;
	}
	
}


