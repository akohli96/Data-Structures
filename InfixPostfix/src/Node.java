
public class Node<Generic> {
	private Node next;//A link to the next node
	private Generic info; //Reference to the info being stored

	//Constructor that takes a link to the next node and some data
	public Node(Node next, Generic info){//Link to next node and data
		this.next=next;
		this.info=info;
	}
	
	//Constructor that takes just data
	public Node(Generic info){//Link to just data
		this.info=info;
		next=null;
	}
	
	//Getter method that gets some info
	public Generic getInfo(){
		return info;
	}
	
	//Setter method that sets some info
	public void setInfo(Generic info){
		this.info=info;
			
	}
	
	//Getter method that returns the next node
	public Node getNextnode(){
		return next;
	}
	
	//Setter method that establishes the link to the next node
	public void setNextnode(Node next){
		this.next=next;
	}
	
	//Override toString so user can see output of data
	public String toString(){
		return info.toString();
	}
}

