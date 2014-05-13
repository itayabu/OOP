package data_structures;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;

public class AvlTree {
	private final static int PARENT=0, LEFT=1, RIGHT=2, MEMBERS=3, 
			NOT_EXIST=-1;
	Node root;
	int treeSize;
	/**
	 * A defult contructor
	 */
	public AvlTree(){
		root = null;
		treeSize=0;
	}

	/**
	 * A data constructor.
	 * a constructor that builds the tree by adding the elements in the input 
	 * array one-by-one.
	 * 
	 * @param data values to add
	 */
	public AvlTree (int[] data){

	}

	/**
	 * A copy constructor
	 * a constructor that build a tree copy of an existing tree
	 */
	public AvlTree( AvlTree tree){

	}

	/**
	 * minimum number of nodes
	 * This method calculates the minimum number of nodes in an AVL tree of 
	 * hight h
	 * 
	 * @param h hight of tree (a non negatve number)
	 * @return minimum number of nodes in the tree
	 */
	public static int findMinNodes (int h){
		if (h<0){
			System.out.println("must be non negative number");
			return 0;
		}
		int a=0, b=1, c, i;
		for (i=1; i<=h; i++){
			c= a+b+1;
			a=b;
			b=c;
		}
		return b;
	}

	/**
	 * find the position of the new value.
	 * return the node with this value if exist, 
	 * or the position to build it from if doesn't 
	 * @param value to search for
	 * @return Node contain value if exist, closet place if doesn't
	 */
	private Node findNodePlace (int value){
		Node currentNode =root;
		boolean serach=true;
		while (serach){
			// check if value greater than the current's
			if (currentNode.value > value){
				if (currentNode.left() != null){
					currentNode = currentNode.left();
				}
				else{
					serach= false;
				}
			}
			//check if the value lower than the current's
			if (currentNode.value < value){
				if (currentNode.right() != null){
					currentNode = currentNode.right();
				}
				else{
					serach= false;
				}
			}
			if (currentNode.value == value){
				serach= false;
			}
		}
		return currentNode;

	}


	/**
	 * build a new node as a son for existing node
	 * @param parent - Node witch will be the parent of the new Node 
	 * @param value- the value of the new Node
	 */
	private void buildNode (Node parent, int value){
		if (parent.value < value){
			parent.family[RIGHT] = new Node(value, parent.hightFromRoot+1);
			parent.family[RIGHT].family[PARENT]=parent;
			return;
		}
		if (parent.value > value){
			parent.family[LEFT] = new Node(value, parent.hightFromRoot+1);
			parent.family[LEFT].family[PARENT]=parent;
			return;
		}
	}


	/**
	 * Add a new node with key newValue into the tree
	 * 
	 * @param new value to add to the tree
	 * @return false iff new value already exist
	 */
	public boolean add (int newValue){
		// handle first Node
		if (root == null){
			root= new Node (newValue, 0);
			System.out.println("added "+ newValue + " to root");
			treeSize++;
			return true;
		}
		// handle other Nodes
		Node place = findNodePlace(newValue);
		if (place.value == newValue){
			//			System.out.println("didnt add "+ newValue + "because already exist");
			return false;
		}
		buildNode (place, newValue);
		System.out.println("added " + newValue+ " as son of " + place.value 
				+" hight: " + (place.hightFromRoot+1));
		treeSize++;
		checkHightDiff(place);
		return true;


	}

	/**
	 * Does tree contain a given value
	 * @param val value to search
	 * @return if value is found in the tree, return the depth of the value
	 * otherwise return -1
	 */
	public int contains (int searchVal){
		Node temp = findNodePlace(searchVal);
		if (temp.value == searchVal){
			return temp.hightFromRoot;
		}
		return NOT_EXIST;

	}

	/**
	 * remove a node from a tree, if exists.
	 * 
	 * @param toDelete value to delete
	 * @return true iff toDelete is found and deleted.
	 */
	public boolean delete (int toDelete){
		Node deleteNode = findNodePlace(toDelete);

		// case no Node has this value
		if (deleteNode.value != toDelete){
			System.out.println("no node");
			return false;
		}

		// case this Node is a leaf
		if ((deleteNode.right()==null) && (deleteNode.left()==null)){
			//			System.out.println("deleting leaf");
			deleteLeaf(deleteNode);
			treeSize--;
			//TODO recursive hight
			return true;
		}

		//case Node has one son
		if (! ((deleteNode.right()!=null) && (deleteNode.left()!=null))){
			System.out.println("deleting father of one child");
			deleteParentForOne(deleteNode);
			treeSize--;
			return true;
		}

		//case Node has two sons
		System.out.println("deleting father of tow");
		deleteParentForTwo(deleteNode);
		treeSize--;

		return true;
	}

	/**
	 * delete Node that has no sons
	 * @param deleteNode the node to delete
	 */
	private void deleteLeaf(Node deleteNode) {
		// case parent is the root
		if (deleteNode==root){
			root=null;
		}
		// case leaf is on left side of parent
		if (deleteNode.parent().value > deleteNode.value){
			deleteNode.parent().family[LEFT]=null;
			System.out.println("successfully deleted right leaf "+ deleteNode.value);
		}

		// case leaf is on right side of its parent
		if (deleteNode.parent().value < deleteNode.value){
			deleteNode.parent().family[RIGHT]=null;
			System.out.println("successfully deleted right leaf "+ deleteNode.value);
		}
		checkHightDiff(deleteNode.parent());
	}


	/**
	 * delete Node that has one child
	 * @param deleteNode the node to delete
	 */
	private void deleteParentForOne(Node deleteNode) {

		// marking the son of the Node to delete
		Node replaceNode;
		if (deleteNode.right() != null){
			replaceNode = deleteNode.right();
		}
		else {
			replaceNode = deleteNode.left();
		}

		// case deleteNode is the root
		replaceNode.family[PARENT]= deleteNode.parent();
		if (deleteNode==root){
			root=replaceNode;
			root.family[PARENT]= null;
//			decreaseHight(root);
		}

		// case deleteNode is on left side of parent
		else if (deleteNode.parent().value > deleteNode.value){
			deleteNode.parent().family[LEFT]=replaceNode;
//			decreaseHight(replaceNode);
			System.out.println("successfully deleted right Node "+
					deleteNode.value + "and replace it with" +replaceNode.value);
		}

		// case leaf is on right side of its parent
		else if (deleteNode.parent().value < deleteNode.value){
			deleteNode.parent().family[RIGHT]=replaceNode;
//			decreaseHight(replaceNode);
			System.out.println("successfully deleted right Node "+
					deleteNode.value + "and replace it with" +replaceNode.value);
		}	
		decreaseHight(replaceNode);
		checkHightDiff(replaceNode.parent());
		
	}


	private void decreaseHight(Node decrease) {
		decrease.hightFromRoot--;
		if (decrease.left() != null){
			decreaseHight(decrease.left());
		}
		if (decrease.right() != null){
			decreaseHight (decrease.right());
		}

	}


	/**
	 * deleting Node parent of two sons.
	 * replacing the node with Node's successor
	 * @param deleteNode the Node to delete
	 */
	private void deleteParentForTwo(Node deleteNode) {
		//finding successor
		Node successor = deleteNode.right();
		while (successor.left() != null){
			successor= successor.left();
		}
		System.out.println("placing "+successor.value+" instead of"+deleteNode.value);
		Node oldParent=null;
		
		//updating successor's family members
		if ((successor.parent() != null) && (successor.parent() != deleteNode)){
			successor.parent().family[LEFT] = successor.right();
			oldParent = successor.parent();
		}
		if (successor.right() != null){
			successor.right().family[PARENT] = successor.parent();
			decreaseHight(successor.right());
		}
		//successor doesn't have left son (this is how we chose it)


		// replacing nodes- updating successor's family array and fields
		successor.family[PARENT]= deleteNode.parent();
		if(successor.parent() == null){
			root = successor;
		}
		successor.family[LEFT]= deleteNode.left();
		if (deleteNode.right().value != successor.value){
			successor.family[RIGHT]= deleteNode.right();
			successor.hightFromRoot = deleteNode.hightFromRoot;
		}


		//updating family of deleteNode with new Node:
		//updating parent of deleteNode
		if(successor.family[PARENT] != null){
			if (successor.parent().left().value == deleteNode.value){
				System.out.println("replacing left son of parent");
				successor.parent().family[LEFT] = successor;
				successor.hightFromRoot = successor.parent().hightFromRoot+1;
			}
			else{
				System.out.println("replacing right son of parent");
				successor.parent().family[RIGHT] = successor;
				successor.hightFromRoot = 0;
			}
		}
		//updating right son of deleteNode
		if (successor.right() != null){
			if (deleteNode.right().value != successor.value){
				successor.right().family[PARENT] = successor;
			}
			else{
				successor.right().family[PARENT] = null;
			}
		}
		// updating left son of deleteNode;
		successor.left().family[PARENT] = successor;
		checkHightDiff(oldParent);
		
		

	}

	/**
	 * check differences in children hights
	 */
	private void checkHightDiff(Node current){
		if (current == null){
			return;
		}
		// initialize help ints
		int maxHight=0, rightHight=-1, leftHight=-1;
		// put values in help ints
		if (current.left() != null){
			leftHight=current.left().hightFromLeaf;
		}
		if (current.right() != null){
			rightHight=current.right().hightFromLeaf;
		}

		// check witch is bigger
		maxHight =(rightHight>leftHight)? (rightHight): (leftHight);
		if (maxHight - rightHight > 1){
			System.out.println("need to rotate right at "+ current.value);
		}
		if (maxHight - leftHight > 1){
			System.out.println("hight error in "+ current.value);	
		}
		if (current.hightFromLeaf != (maxHight+1)){
			current.hightFromLeaf = maxHight+1;
			checkHightDiff(current.parent());
		}
	}

	/**
	 * @return number of nodes in the tree
	 */
	public int size(){
		return treeSize;
	}

	/**
	 * @return iterator to the Avl Tree. The returned iterator can pass over
	 * the tree nodes in ascending order
	 */
	public Iterator <Integer> iterator(){
		return new TreeIterator(root);	
	}

	public static class Node{
		Node[] family = new Node[MEMBERS];
		int hightDifferance, hightFromRoot, /*hightRight, hightLeft,*/ value, hightFromLeaf;
		//		boolean changed;

		Node(int val, int hight){
			value= val;
			hightFromLeaf=0;
			//			hightDifferance=0;
			this.hightFromRoot = hight;
			//			hightRight=0;
			//			hightLeft=0;
			for (int i=0; i<MEMBERS; i++){
				family[i]=null;
			}
		}

		/**
		 * return the parent
		 * return null if this is the root
		 * @return parent if exist, null if doesnt
		 */
		private Node parent(){
			return family[PARENT];
		}

		/**
		 * return left child
		 * return null if doest exist
		 * @return left child if exist, null if doesnt
		 */
		private Node left(){
			//			if (family[LEFT]==null){
			//				return null;
			//			}
			return family[LEFT];
		}

		/**
		 * return right child
		 * return null if doesnt exist
		 * @return right child if exist, null if doesnt
		 */
		private Node right(){
			if (family[RIGHT]== null){
				return null;
			}
			return family[RIGHT];
		}
	}

	private class TreeIterator implements Iterator<Integer>{
		Node iterRoot, iterCurrent;
		TreeIterator(Node root){
			this.iterRoot = root;
			this.iterCurrent=root;
			minimum();

		}
		@Override
		public boolean hasNext() {
			if (iterCurrent != null){
				return true;
			}
			return false;
		}

		@Override
		public Integer next() {
			Node toReturn= iterCurrent;
			if (toReturn == null){
				return null;
			}
			// case current have a right son, return its leftmost sibling
			if (iterCurrent.right() != null){
				iterCurrent= iterCurrent.right();
				minimum();
				return toReturn.value;
			}

			// case current is the right-hand son of parent, need to go up until we find we find 
			// a parent that current is a left hand son of it. if doesnt exist, then we were at the 
			// right end of the tree
			if ((iterCurrent != iterRoot) && (iterCurrent == iterCurrent.parent().left())){
				iterCurrent = iterCurrent.parent();
				return toReturn.value;
			}
			while ((iterCurrent != iterRoot) && (iterCurrent == iterCurrent.parent().right())){
				iterCurrent=iterCurrent.parent();

			}
			if ((iterCurrent != iterRoot) && (iterCurrent == iterCurrent.parent().left())){
				iterCurrent = iterCurrent.parent();
				return toReturn.value;
			}

			iterCurrent = null;
			return toReturn.value;
		}

		/**
		 * we have been told NOT to implement this method.
		 */
		@Override
		public void remove() {
		}

		/**
		 * find the minimum Node
		 * @return true if there is smaller Node than the current one, 
		 * false if not
		 */
		private boolean minimum(){
			Node temp = iterCurrent;
			while (temp.left() != null){
				temp= temp.left();
			}
			if (temp == iterCurrent){
				return false;
			}
			iterCurrent= temp;
			return true;
		}

	}
}