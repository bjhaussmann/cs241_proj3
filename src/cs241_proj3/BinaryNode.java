/**
 * 
 */
package cs241_proj3;

/**
 * @author bjhau
 *
 */
public class BinaryNode<T> {

	protected T data;
	protected BinaryNode<T> leftChild;
	protected BinaryNode<T> rightChild;

	public BinaryNode() {
		this(null);
	}

	public BinaryNode(T data) {
		this(data, null, null);
	}

	public BinaryNode(T data, BinaryNode<T> leftChild, BinaryNode<T> rightChild) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	/**
	 * copies one node to another
	 * @return the new root
	 */
	public BinaryNode<T> copy() {
		BinaryNode<T> newRoot = new BinaryNode<>(data);
		if (leftChild != null)
			newRoot.setLeftChild(leftChild.copy());
		if (rightChild != null)
			newRoot.setRightChild(rightChild.copy());
		return newRoot;
	}

	/**
	 * finds the data of the node
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * getHeight helper
	 * @return the height
	 */
	public int getHeight() {
		return getHeight(this);
	}

	/**
	 * retueves the height of the node
	 * @param node node ot be checked
	 * @return the height
	 */
	private int getHeight(BinaryNode<T> node) {
		int height = 0;
		if (node != null)
			height = 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
		return height;
	}

	/**
	 * retrieves the left child of the node
	 * @return the left child of the node
	 */
	public BinaryNode<T> getLeftChild() {
		return leftChild;
	}

	/**
	 * finds the number of nodes in a specified node
	 * @return the number of nodes
	 */
	public int getNumberOfNodes() {
		int leftNumber = 0;
		int rightNumber = 0;
		if (leftChild != null)
			leftNumber = leftChild.getNumberOfNodes();
		if (rightChild != null)
			rightNumber = rightChild.getNumberOfNodes();
		return 1 + leftNumber + rightNumber;
	}

	/**
	 * retrieves the value of the right child
	 * @return returns the value of the right child
	 */
	public BinaryNode<T> getRightChild() {
		return rightChild;
	}

	/**
	 * checks to see if the node has a left child
	 * @return true if the left child exists, else false
	 */
	public boolean hasLeftChild() {
		return leftChild != null;
	}

	/**
	 * checks to see if the node has a right child
	 * @return true if the right child exists, else false
	 */
	public boolean hasRightChild() {
		return rightChild != null;
	}

	/**
	 * check to see if the node is a leaf
	 * @return returns true if it is a leaf, else false
	 */
	public boolean isLeaf() {
		return (leftChild == null) && (rightChild == null);
	}

	/**
	 * set the data of the node
	 * @param data	 the data to be set to the node
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * set the left child of the node
	 * @param leftChild	 the left child of the node
	 */
	public void setLeftChild(BinaryNode<T> leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * set the right child of the node
	 * @param rightChild the right child to be set
	 */
	public void setRightChild(BinaryNode<T> rightChild) {
		this.rightChild = rightChild;
	}

}
