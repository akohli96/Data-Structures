
public class Queue {


	private int counter;//Keeping track of nodes
	private Node head;
	private Node tail;

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

	//Constructor
	public Queue(){
		head=null;
		tail=null;
		counter=0;
	}

	//Basic getter method that returns the tail
	public Node getTail(){
		return tail;
	}

	//Basic getter method that returns the head
	public Node front(){
		return head;
	}

	//Adding to the tail
	//Input:Information 
	//Output:Nothing
	public void enQ(String info){
		Node newtail= new Node(head, info);//make newtail point to head and store data
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




	//Remove from head
	//Input:Nothing 
	//Output:Nothing
	public void deQ(){
		if(isEmpty())
			return;
		else if(head==tail){//Nullify
			head=null;
			tail=null;
		}
		else{
			Node newhead=head.getNextnode();//making new head
			tail.setNextnode(newhead);//setting tail to point to new head
			head=newhead;//head is newhead
		}
		counter--;
	}	


}


