/**
 * 
 */
package cs241_proj3;

/**
 * @author bjhau
 * @param <T>
 *
 */
public class RedBlackNode<T> extends BinaryNode<T> {

	protected boolean red;
	private int size;
	
	/**
	 * @param black the black to set
	 */
	public void setRed(boolean red) {
		this.red = red;
	}

	public RedBlackNode()
	{
		this(null);
	}
	
	public RedBlackNode(T data)
	{
		this(data, null, null);
	}
	
	public RedBlackNode(T data, RedBlackNode<T> leftChild, RedBlackNode<T> rightChild)
	{
		this(data, leftChild, rightChild, true);
	}
	
	public RedBlackNode(T data, RedBlackNode<T> leftChild, RedBlackNode<T> rightChild, boolean red)
	{
		this(data, leftChild, rightChild, red, 0);
	}
	
	public RedBlackNode(T data, RedBlackNode<T> leftChild, RedBlackNode<T> rightChild, boolean red, int size)
	{
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.red = red;
		this.size = size;
		
	}
	
	public boolean isRed ()
	{
		return red;
	}
	
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	@Override
	/**
	 * copies one node to another
	 * @return the new root
	 */
	public RedBlackNode<T> copy() {
		RedBlackNode<T> newRoot = new RedBlackNode<>(data);
		if (leftChild != null)
			newRoot.setLeftChild(leftChild.copy());
		if (rightChild != null)
			newRoot.setRightChild(rightChild.copy());
		return newRoot;
	}
	
	@Override
	/**
	 * retrieves the value of the left child
	 * @return returns the value of the left child
	 */
	public RedBlackNode<T> getLeftChild()
	{
		return (RedBlackNode<T>) leftChild;
	}
	
	@Override
	/**
	 * checks to see if the node has a left child
	 * @return true if the left child exists, else false
	 */
	public boolean hasLeftChild()
	{
		return leftChild != null;
	}
	
	@Override
	/**
	 * retrieves the value of the right child
	 * @return returns the value of the right child
	 */
	public RedBlackNode<T> getRightChild()
	{
		return (RedBlackNode<T>) rightChild;
	}
	
	@Override
	/**
	 * checks to see if the node has a right child
	 * @return true if the right child exists, else false
	 */
	public boolean hasRightChild()
	{
		return rightChild != null;
	}
}