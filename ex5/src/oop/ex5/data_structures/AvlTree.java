package oop.ex5.data_structures;
import java.util.Iterator;

public class AvlTree {
	private final static int LEFT=1, RIGHT=2, NOT_EXIST=-1;
	TreeNode root;
	int treeSize;
	
	/**
	 * A default constructor
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
		this();
		for (int i=0; i< data.length; i++){
			add(data[i]);
		}
	}

	/**
	 * A copy constructor
	 * a constructor that build a tree copy of an existing tree
	 */
	public AvlTree( AvlTree tree){
		Iterator <Integer> iter = tree.iterator();
		while (iter.hasNext()){
			add(iter.next());
		}
	}

	/**
	 * minimum number of nodes
	 * This method calculates the minimum number of nodes in an AVL tree of 
	 * height h
	 * 
	 * @param h height of tree (a non negative number)
	 * @return minimum number of nodes in the tree
	 */
	public static int findMinNodes (int h){
		if (h<0){
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
	private TreeNode findNodePlace (int value){
		TreeNode currentNode =root;
		boolean serach=true;
		if (root == null){
			return root;
		}
		while (serach){
			// check if value greater than the current's
			if (currentNode.value > value){
				if (currentNode.getLeft() != null){
					currentNode = currentNode.getLeft();
				}
				else{
					serach= false;
				}
			}
			//check if the value lower than the current's
			if (currentNode.value < value){
				if (currentNode.getRight() != null){
					currentNode = currentNode.getRight();
				}
				else{
					serach= false;
				}
			}
			// check if the value equals to current's
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
	private void buildNode (TreeNode parent, int value){
		// right case
		if (parent.value < value){
			parent.setRight(new TreeNode(value, parent.heightFromRoot+1));
			parent.getRight().setParent(parent);
			return;
		}
		//left case
		if (parent.value > value){
			parent.setLeft(new TreeNode(value, parent.heightFromRoot+1));
			parent.getLeft().setParent(parent);
			return;
		}
	}

	/**
	 * Add a new node with key newValue into the tree
	 * @param new value to add to the tree
	 * @return false iff new value already exist
	 */
	public boolean add (int newValue){
		// handle first Node
		if (root == null){
			root= new TreeNode (newValue, 0);
			treeSize++;
			return true;
		}
		// handle other Nodes
		TreeNode place = findNodePlace(newValue);
		// if Node already exists
		if (place.value == newValue){
			return false;
		}
		buildNode (place, newValue);
		treeSize++;
		checkHeightDiff(place);
		return true;

	}

	/**
	 * Does tree contain a given value
	 * @param val value to search
	 * @return if value is found in the tree, return the depth of the value
	 * otherwise return -1
	 */
	public int contains (int searchVal){
		TreeNode temp = findNodePlace(searchVal);
		if ((temp == null) || (temp.value != searchVal)){
			return NOT_EXIST;
		}
		return temp.heightFromRoot;
	}

	/**
	 * remove a node from a tree, if exists.
	 * 
	 * @param toDelete value to delete
	 * @return true iff toDelete is found and deleted.
	 */
	public boolean delete (int toDelete){
		TreeNode deleteNode = findNodePlace(toDelete);

		// case no Node has this value
		if (deleteNode == null || deleteNode.value != toDelete){
			return false;
		}

		// case this Node is a leaf
		if ((deleteNode.getRight()==null) && (deleteNode.getLeft()==null)){
			deleteLeaf(deleteNode);
			treeSize--;
			return true;
		}

		//case Node has one son
		if (! ((deleteNode.getRight()!=null) && (deleteNode.getLeft()!=null))){
			deleteParentForOne(deleteNode);
			treeSize--;
			return true;
		}

		//case Node has two sons
		deleteParentForTwo(deleteNode);
		treeSize--;

		return true;
	}

	/**
	 * delete Node that has no sons
	 * @param deleteNode the node to delete
	 */
	private void deleteLeaf(TreeNode deleteNode) {
		// case parent is the root
		if (deleteNode==root){
			root=null;
		}
		// case leaf is on left side of parent
		if (deleteNode.getParent()!= null && 
				deleteNode.getParent().value > deleteNode.value){
			deleteNode.getParent().setLeft(null);
		}

		// case leaf is on right side of its parent
		if (deleteNode.getParent()!= null &&
				deleteNode.getParent().value < deleteNode.value){
			deleteNode.getParent().setRight(null);
		}
		checkHeightDiff(deleteNode.getParent());
	}


	/**
	 * delete Node that has one child
	 * @param deleteNode the node to delete
	 */
	private void deleteParentForOne(TreeNode deleteNode) {

		// marking the son of the Node to delete
		TreeNode replaceNode;
		if (deleteNode.getRight() != null){
			replaceNode = deleteNode.getRight();
		}
		else {
			replaceNode = deleteNode.getLeft();
		}

		// case deleteNode is the root
		replaceNode.setParent(deleteNode.getParent());
		if (deleteNode==root){
			root=replaceNode;
			root.setParent(null);
		}

		// case deleteNode is on left side of parent
		else if (deleteNode.getParent().value > deleteNode.value){
			deleteNode.getParent().setLeft(replaceNode);
		}

		// case leaf is on right side of its parent
		else if (deleteNode.getParent().value < deleteNode.value){
			deleteNode.getParent().setRight(replaceNode);
		}	
		decreaseHeight(replaceNode);
		checkHeightDiff(replaceNode.getParent());

	}

	/**
	 * increase height from root to Node and all its siblings
	 * @param increase
	 */
	private void increaseHeight(TreeNode increase){
		if (increase == null){
			return;
		}
		increase.heightFromRoot++;
		increaseHeight(increase.getLeft());
		increaseHeight (increase.getRight());
	}

	private void decreaseHeight(TreeNode decrease) {
		if (decrease == null){
			return;
		}
		decrease.heightFromRoot--;
		decreaseHeight(decrease.getLeft());
		decreaseHeight (decrease.getRight());
	}


	/**
	 * deleting Node parent of two sons.
	 * replacing the node with Node's successor
	 * @param deleteNode the Node to delete
	 */
	private void deleteParentForTwo(TreeNode deleteNode) {
		//finding successor
		TreeNode successor = deleteNode.getRight();
		while (successor.getLeft() != null){
			successor= successor.getLeft();
		}
		TreeNode oldParent=null;

		//updating successor's family members
		if ((successor.getParent() != null) && 
				(successor.getParent() != deleteNode)){
			successor.getParent().setLeft(successor.getRight());
			oldParent = successor.getParent();
		}
		if (successor.getRight() != null){
			successor.getRight().setParent(successor.getParent());
			decreaseHeight(successor.getRight());
		}
		//successor doesn't have left son (this is how we chose it)


		// replacing nodes- updating successor's family array and fields
		successor.setParent(deleteNode.getParent());
		if(successor.getParent() == null){
			root = successor;
		}
		successor.setLeft(deleteNode.getLeft());
		if (deleteNode.getRight().value != successor.value){
			successor.setRight(deleteNode.getRight());
			successor.heightFromRoot = deleteNode.heightFromRoot;
		}


		//updating family of deleteNode with new Node:
		//updating parent of deleteNode
		if(successor.getParent() != null){
			if (successor.getParent().getLeft().value == deleteNode.value){
				successor.getParent().setLeft(successor);
				successor.heightFromRoot = 
						successor.getParent().heightFromRoot+1;
			}
			else{
				successor.getParent().setRight(successor);
				successor.heightFromRoot = 0;
			}
		}
		//updating right son of deleteNode
		if (successor.getRight() != null){
			if (deleteNode.getRight().value != successor.value){
				successor.getRight().setParent(successor);
			}
			else{
				successor.getRight().setParent(null);
			}
		}
		// updating left son of deleteNode;
		successor.getLeft().setParent(successor);
		checkHeightDiff(oldParent);



	}

	/**
	 * check differences in children heights
	 */
	private void checkHeightDiff(TreeNode current){
		if (current == null){
			return;
		}
		// initialize help ints
		int maxHeight, rightHeight=getHeightFromLeaf(current.getRight()),
				leftHeight=getHeightFromLeaf(current.getLeft());

		// check witch is bigger
		maxHeight =(rightHeight>leftHeight)? (rightHeight): (leftHeight);
		if (maxHeight - rightHeight > 1){
			manageRotations(current, LEFT);
		}
		else if (maxHeight - leftHeight > 1){
			manageRotations(current, RIGHT);
		}
		else{
			checkHeightDiff(current.getParent());
		}
	}

	/**
	 * main method to control all rotations
	 * @param balanceNode
	 * @param direction
	 */
	private void manageRotations(TreeNode balanceNode, int direction){

		// cases left side is bigger
		if (direction == LEFT){
			if (getHeightFromLeaf(balanceNode.getLeft().getRight()) >
				getHeightFromLeaf(balanceNode.getLeft().getLeft())) {
				complexRotationRight(balanceNode.getLeft());
			}
			complexRotationLeft(balanceNode);
			return;

		}
		if (direction == RIGHT){
			if(getHeightFromLeaf(balanceNode.getRight().getLeft()) >
			getHeightFromLeaf(balanceNode.getRight().getRight())){
				complexRotationLeft(balanceNode.getRight());
			}
			complexRotationRight(balanceNode);
			return;
		}
	}
	
	/**
	 * manage rotations to the left.
	 * wrap simpLeftRotation, taking care to all Nodes infected from the
	 * rotation of the current Node.
	 */
	private void complexRotationLeft (TreeNode currNode){
		// tempNode might be a "free" sub tree after simple rotation
		TreeNode tempNode = currNode.getLeft().getRight();
		simpleLeftRotation(currNode);
		
		// manage the "free" sub tree
		currNode.setLeft(tempNode);
		if (tempNode != null){
			tempNode.setParent(currNode);
			tempNode.heightFromRoot = tempNode.getParent().heightFromRoot+1;
		}
		
		// manage heights
		increaseHeight(currNode.getRight());
		decreaseHeight(currNode.getParent().getLeft());
		return;
	}
	
	/**
	 * manage rotations to the right.
	 * wrap simpRightRotation, taking care to all Nodes infected from the
	 * rotation of the current Node.
	 */
	private void complexRotationRight(TreeNode currNode){
		// tempNode might be a "free" sub tree after simple rotation
		TreeNode tempNode = currNode.getRight().getLeft();
		simpleRightRotation(currNode);
		
		//manage the "free" sub tree
		currNode.setRight(tempNode);
		if (tempNode != null){
			tempNode.setParent(currNode);
			tempNode.heightFromRoot = tempNode.getParent().heightFromRoot+1;
		}
		
		//manage heights
		increaseHeight(currNode.getLeft());
		decreaseHeight(currNode.getParent().getRight());
		return;
		
	}

	/**
	 * rotate left a sub tree. right hand become the new parent and old parent
	 * become left 
	 */
	private void simpleLeftRotation(TreeNode toRotate){
		if (toRotate == root){
			root = toRotate.getLeft();
		}
		else if (toRotate.getParent().getLeft() == toRotate) {
			toRotate.getParent().setLeft(toRotate.getLeft());
		}
		else{
			toRotate.getParent().setRight(toRotate.getLeft());
		}
		toRotate.getLeft().setParent(toRotate.getParent());
		toRotate.getLeft().setRight(toRotate);
		toRotate.setParent(toRotate.getLeft());
		toRotate.setLeft(null);
		toRotate.heightFromRoot++;
		toRotate.getParent().heightFromRoot--;
	}

	/**
	 * rotate right a sub tree. left hand become the new parent and old parent
	 * become left 
	 */
	private void simpleRightRotation(TreeNode toRotate){
		
		// manage parent of rotation couple
	if (toRotate == root){
			root = toRotate.getRight();
		}
		else if (toRotate.getParent().getLeft() == toRotate) {
			toRotate.getParent().setLeft(toRotate.getRight());
		}
		else{
			toRotate.getParent().setRight(toRotate.getRight());
		}
	
		// manage rotation couple pointers and heights
		toRotate.getRight().setParent(toRotate.getParent());
		toRotate.getRight().setLeft(toRotate);
		toRotate.setParent(toRotate.getRight());
		toRotate.setRight(null);
		toRotate.heightFromRoot++;
		toRotate.getParent().heightFromRoot--;
	}
	
	// compare heights from teh farthest leaf on both sides
	private int getHeightFromLeaf(TreeNode node){
		int left, right, maxHeight;
		if (node == null){
			return NOT_EXIST;
		}
		left = getHeightFromLeaf(node.getLeft());
		right = getHeightFromLeaf(node.getRight());
		maxHeight = left> right? left: right;
		return maxHeight+1;
		
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

	private class TreeIterator implements Iterator<Integer>{
		TreeNode iterRoot, iterCurrent;
		TreeIterator(TreeNode root){
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
			TreeNode toReturn= iterCurrent;
			if (toReturn == null){
				return null;
			}
			// case current have a right son, return its leftmost sibling
			if (iterCurrent.getRight() != null){
				iterCurrent= iterCurrent.getRight();
				minimum();
				return toReturn.value;
			}

			// case current is the right-hand son of parent, need to go up until we find we find 
			// a parent that current is a left hand son of it. if doesnt exist, then we were at the 
			// right end of the tree
			if ((iterCurrent != iterRoot) && (iterCurrent == iterCurrent.getParent().getLeft())){
				iterCurrent = iterCurrent.getParent();
				return toReturn.value;
			}
			while ((iterCurrent != iterRoot) && (iterCurrent == iterCurrent.getParent().getRight())){
				iterCurrent=iterCurrent.getParent();

			}
			if ((iterCurrent != iterRoot) && (iterCurrent == iterCurrent.getParent().getLeft())){
				iterCurrent = iterCurrent.getParent();
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
			TreeNode temp = iterCurrent;
			while (temp.getLeft() != null){
				temp= temp.getLeft();
			}
			if (temp == iterCurrent){
				return false;
			}
			iterCurrent= temp;
			return true;
		}

	}
}
