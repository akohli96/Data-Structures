
public class LinkedList<Generic> {

	private int counter;//Keeping track of nodes

	private Node head;
	private Node tail;

	//Constructor
	public LinkedList(){
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

	//Add to head
	//Input: information 
	//Output:Nothing
	public void addTohead(Generic info){
		Node temp = new Node(info);//Create a temp
		temp.setNextnode(head);//Set temp to point to head
		//If there is no node
		if(head==null){
			head=temp;
			tail=temp;
		}

		else{
			tail.setNextnode(temp);//Set tail to point to temp
			head=temp;//Make temp your head
		}
		counter++;

	}

	//Remove from head
	//Input:Nothing 
	//Output:Nothing
	public void removeFromhead(){
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
	
	//Adding to the tail
	//Input:Information 
	//Output:Nothing
	public void addTotail(Generic info){
		Node newtail= new Node<Generic>(head, info);//make newtail point to head and store data
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
	public void removeFromtail(){

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

	//Inserting a node
	//Input: The information and and index at which you want to insert
	//Output: Nothing
	public void insertAt(Generic info,int index){
		if(index>getSize() || index<0)//Check valid index
			return;
		if(!isEmpty()){
			if(index==getSize()){//If last index, add to tail
				addTotail(info);
			}
			else if(index==0){//if first index add to head
				addTohead(info);
			}
			else{
				Node newElement= new Node<Generic>(info);//Create the new element
				Node travel=head;

				for(int i=1;i<index;i++){//till you hit index keep on moving forward
					travel=travel.getNextnode();
				}

				newElement.setNextnode(travel.getNextnode());//set the new element to point to travels next
				travel.setNextnode(newElement);//Set travel to point to the new Element
				counter++;
			}
		}

		
		else{//If its empty addTohead
			addTohead(info);
		}

	}

	//Deleting a node
	//Input: The information and and index at which you want to delete
	//Output: Nothing
	public void deleteAt(int index){
		if(index>getSize() || index<0)//check valid index
			return;
		if(!isEmpty()){
			if(index==getSize()-1){//If detail at last index,remove from tail
				removeFromtail();
			}
			else if(index==0){//if remove first element remove from head
				removeFromhead();
			}
			else{
				Node travel=head;
				for(int i=1;i<index;i++){//keep on moving till you hit the position you want to remove
					travel=travel.getNextnode();
				}
				travel.setNextnode(travel.getNextnode().getNextnode());//set the node to point to two nodes after
				counter--;
			}
		}
		else
			return;
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
			removeFromhead();
	}

	//Displays the entire list
	//Input: Nothing
	//Output: All my elements as a string called Output
	public String display(){
		String output="";
		Node temp=head;
		if(!isEmpty()){
			while(temp.getNextnode()!= head){//loop through the entire list
				//System.out.println(temp.getInfo());
				output+=" " + temp.getInfo() + " ";//add the info to the the output
				temp=temp.getNextnode();
			}
			output+=" " + tail.getInfo() " ";
		}
		else
			output="NULL";
		return output;
	}

}


