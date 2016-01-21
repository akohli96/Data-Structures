
public class BinaryNode<Generic extends Comparable<Generic>>   {
	public Generic info;
	public BinaryNode left;
	public BinaryNode right;
	
	//Setther
	public void setInfo(Generic info){
		this.info=info;
	}
	
	//Constructor
	public BinaryNode(Generic info, BinaryNode left, BinaryNode right){
		this.info=info;
		this.left=left;
		this.right=right;
	}
	
	//Constructor
	public BinaryNode(Generic info){
		this.info=info;
	}
	
	//Getinfo
	public Generic getInfo(){
		return info;
	}


	
}
