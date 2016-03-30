
public class Queue<Generic> {


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
	public void enQ(Generic info){
		Node newtail= new Node<Generic>(head, info);//make newtail point to head and store data
		//Basically like add to head
		if(isEmpty()){
		/*
		head=newtail;
		head.setNextnode(head) //Should I really even have this
		tail=head
		*/
		/*
		Also I am not using tail at all in this if branch, if head's references are being played with and tail is 
		head, it should not break.
		*/
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


	
	//Displays the entire list
	//Input: Nothing
	//Output: All my elements as a string called Output
	public String display(){
		String output="";
		Node temp=head;
		if(!isEmpty()){
			while(temp.getNextnode()!=head){//loop through the entire list
				//System.out.println(temp.getInfo());
				output+=" " + temp.getInfo() + " ";//add the info to the the output
				temp=temp.getNextnode();
			}
			output+= " " + tail.getInfo() + " ";// I dont know why I have to do this
		}
		else
			output="";
		return output;
	}
}
