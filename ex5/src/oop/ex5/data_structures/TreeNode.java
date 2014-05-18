package oop.ex5.data_structures;

class TreeNode {private TreeNode leftNode, rightNode, parentNode;
	int heightFromRoot,value ;
	
	TreeNode(int val, int height){
		value= val;
		this.heightFromRoot = height;
		leftNode = null;
		parentNode = null;
		rightNode = null;
	}

	/**
	 * return the parent
	 * return null if this is the root
	 * @return parent if exist, null if doesnt
	 */
	TreeNode getParent(){
		return parentNode;
	}

	void setParent (TreeNode set){
		parentNode=set;
	}
	/**
	 * return left child
	 * return null if doest exist
	 * @return left child if exist, null if doesnt
	 */
	TreeNode getLeft(){

		return leftNode;
	}

	/**
	 * set the Right child
	 * @param set- the Node that will be the right child
	 */
	void setLeft (TreeNode set){
		leftNode=set;
	}

	/**
	 * return right child
	 * return null if doesnt exist
	 * @return right child if exist, null if doesnt
	 */
	TreeNode getRight(){
		return rightNode;
	}

	/**
	 * set the Right child
	 * @param set- the Node that will be the right child
	 */
	void setRight (TreeNode set){
		rightNode=set;
	}
}
