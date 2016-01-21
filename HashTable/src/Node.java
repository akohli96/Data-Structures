
public class Node {
	private Node next;//A link to the next node
	private String info; //Reference to the info being stored

	//Constructor that takes a link to the next node and some data
	public Node(Node next, String info){//Link to next node and data
		this.next=next;
		this.info=info;
	}
	
	//Constructor that takes just data
	public Node(String info){//Link to just data
		this.info=info;
		next=null;
	}
	
	//Getter method that gets some info
	public String getInfo(){
		return info;
	}
	
	//Setter method that sets some info
	public void setInfo(String info){
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
	
	


}

