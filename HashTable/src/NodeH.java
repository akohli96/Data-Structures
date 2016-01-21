
public class NodeH {
	
	final static int EMPTY = 1;
	final static int DELETED = 2;
	final static int OCCUPIED = 3;
	 
	private int flag;//1 means its empty, 2 means its deleted and 3 means its occupied
	private String info; //Reference to the info being stored

	//Constructor that takes a link to the next node and some data
	public NodeH(int flag, String info){
		this.flag=flag;
		this.info=info;
	}
	

	//Getter method that gets some info
	public String getInfo(){
		return info;
	}
	
	//Setter method that sets some info
	public void setInfo(String info){
		this.info=info;		
	}
	
	//Setter methdo that sets both the flag and the string
	public void setBoth(int newflag,String info){
		this.info=info;		
		this.flag=newflag;
	}
	
	//Getter method that returns the flag
	public int getFlag(){
		return flag;
	}
	
	//Setter method that establishes the flag
	public void setFlag(int nextflag){
		this.flag=nextflag;
	}
	
	//Override toString so user can see output of data
	public String toString(){
		return (info.toString()) ;
	}
	
	
	//Checks whether a string is equal to the string stored in NodeH
	public boolean equals(String other){
		if(this.info!=null){
			if(this.info.equals(other)){
			return true;
			}
		}
		return false;
	}
	

}