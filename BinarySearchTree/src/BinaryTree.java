



public class BinaryTree<Generic extends Comparable<Generic>>  {

	
	private int numOfnodes;
	
	private BinaryNode root;

	//Constructor
	public BinaryTree(){
		root=null;
		numOfnodes=0;
	}
	
	//GetNodes method
	//INPUT: Nothing
	//OUTPUT: Integer - Number of nodes
	public int getNodes(){
		return numOfnodes;
	}

	//IsEmpty method
	//INPUT: Nothing
	//OUTPUT: Boolean - is empty or not
	public boolean isEmpty(){
		return (root==null);
	}
	
	//Clear method
	//INPUT: Nothing
	//OUTPUT: Nothing - deletes everything
	public void clear(){
		root=null;
		numOfnodes=0;
	}

	//Insert method
	//INPUT: information
	//OUTPUT: Boolean - Got inserted or not
	public boolean insert(Generic info){
		BinaryNode newNode= new BinaryNode(info);

		boolean exist=false;
		if(!search(info)){
			numOfnodes++;
			exist=true;
		}
		root = recInsert(root, newNode);
		 return exist;
				
	}

	//GetMax method
	//INPUT: The node
	//OUTPUT: The node - Biggest Node	
	private BinaryNode getMax(BinaryNode current){
		while(current!=null)
			current=current.right;
		return current;
	}
	
	//Getmin method
	//INPUT: The node
	//OUTPUT: The node - Smallest Node
	private BinaryNode getMin(BinaryNode current){
		while(current!=null)
			current=current.left;
		return current;
	}
	
	//RecInsert method
	//INPUT: The current node and the insert
	//OUTPUT: The node - Got inserted in correct place
	private BinaryNode recInsert(BinaryNode currenttree, BinaryNode insert){
		if(currenttree==null){
			currenttree=insert;
		}
		else if(insert.info.compareTo(currenttree.info)>0){
			 currenttree.right=recInsert(currenttree.right, insert);
		}

		else if(insert.info.compareTo(currenttree.info)<0){
			 currenttree.left=recInsert(currenttree.left, insert);
		}	
		else{
			return insert;
		}
		return currenttree;
		
	}

	//RecInsert method
	//INPUT: The info that needs to be deleted
	//OUTPUT: Boolean - Got deleted or not
	public boolean delete(Generic info){
		boolean exist=false;
		if(search(info)){//Search for that info
			numOfnodes--;//Decrease number of nodes
			exist=true; // It exists
		}
		 root=recDelete(root, info); //Recursively delete 
		 return exist;
	}
	
	//search method
	//INPUT: The info that needs to be searched
	//OUTPUT: Boolean - Found or not
	public boolean search(Generic info){
		return(recSearch(root, info)!=null);
	}
	
	//Find method
	//INPUT: The info that needs to be found
	//OUTPUT: Comparable - The information of the search.
	public Comparable find(Generic info){
		BinaryNode answer =recSearch(root, info);
		if(answer==null)
			return null;
		else
			return(answer.info);
		
	}
	
	//RecSearch method
	//INPUT: The Node and info
	//OUTPUT: Node - Search and return that node || return null
	private BinaryNode recSearch(BinaryNode currenttree, Generic info){
		while(currenttree!=null){
			if(info.compareTo((Generic) currenttree.getInfo())<0){
				currenttree=currenttree.left;
			}
			else if(info.compareTo((Generic) currenttree.getInfo())>0){
				currenttree=currenttree.right;
			}
			else return currenttree;
		}
			return null;
		
	}
	
	//RecDelete method
	//INPUT: The info that needs to be deleted && the currentnode
	//OUTPUT: The node - Return the deleted node || null
	private BinaryNode recDelete(BinaryNode currenttree, Generic info){
		//Recursively go through the tree
		if(currenttree!=null){
			if(info.compareTo((Generic) currenttree.getInfo())<0){
				currenttree.left=recDelete(currenttree.left, info);
			}
			else if(info.compareTo((Generic) currenttree.getInfo())>0){
				currenttree.right=recDelete(currenttree.right, info);
			}
			
			//MATCH FOUND 
			else {
				//TWO CHILDREN
				if(currenttree.left!=null && currenttree.right!=null){

					//Find rightmost in left subtree
					BinaryNode rightmost=currenttree.left; //this is a new reference

					while(rightmost.right!=null){
						rightmost=rightmost.right;
					}
					Generic rightinfo=(Generic) rightmost.getInfo();
					currenttree.left=recDelete(rightmost, rightinfo);
					currenttree.setInfo(rightinfo);

				}

				//ONE CHILD
				else if(currenttree.left!=null){
					currenttree=currenttree.left;
				}

				else if(currenttree.right!=null){
					currenttree=currenttree.right;
				}

				//NO CHILD
				else{
					currenttree.setInfo(null);
					currenttree=null;
				}

			}

		}

		//IF NODE DOES NOT EXIST
		else{
			return null;
		}
		
		return currenttree;
	}

	
	
	//GETMAX
	//INPUT: Nothing
	//OUTPUT:BinaryNode - Maximum Node
	public BinaryNode getMaX(){
		return getMax(root);
	}
	
	//GETMIN
	//INPUT: Nothing
	//OUTPUT:BinaryNode - Minimum Node
	public BinaryNode getMiN(){
		return getMin(root);
	}
	

	
	//Get Preorder Transversal
	//INPUT: Nothing
	//OUTPUT:Linked List of the Preorder Transversal
	public LinkedList dPreorder(){
		LinkedList display = new LinkedList();
		preOrder(root, display);
		return display;
	}
	
	//Preorder Transversal
	//INPUT: Current nood, array,indiex
	//OUTPUT:Nothing	
	private void preOrder(BinaryNode croot, LinkedList list){
		if(croot == null)
			return;
		else
		{
			list.addTotail(croot.info);
			preOrder(croot.left,list);
			preOrder(croot.right,list);
		}
	}
	
	//Get Inorder Transversal
	//INPUT: Nothing
	//OUTPUT:Linked List of the Inorder Transversal
	public LinkedList<Generic> dInorder(){
		LinkedList<Generic> display = new LinkedList<Generic>();
		inOrder(root,display);
		return display;
	}
	
	//Get Inorder Transversal
	//INPUT: Current root && list
	//OUTPUT:Nothing
	private void inOrder(BinaryNode croot, LinkedList list){
		if(croot==null){
		return ;
	}
		else{
			inOrder(croot.left,list);
			list.addTotail(croot.info);
			inOrder(croot.right,list);
		}
	}



	
	public String getRoot(){
		return root.info.toString();
	}

	

}
