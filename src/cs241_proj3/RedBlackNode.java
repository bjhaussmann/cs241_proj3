/**
 * 
 */
package cs241_proj3;

/**
 * @author bjhau
 * @param <T>
 *
 */
public class RedBlackNode<T extends Comparable<? super T>> extends BinaryNode<T> {

	protected final static int RED = 0;
	protected final static int BLACK = 1;
	protected int colour;
	private int size;
	protected RedBlackNode<T> parent = (RedBlackNode<T>) super.parent;
	protected RedBlackNode<T> leftChild = (RedBlackNode<T>) super.leftChild;
	protected RedBlackNode<T> rightChild = (RedBlackNode<T>) super.rightChild;
	
	
	/**
	 * @return the parent
	 */
	public RedBlackNode<T> getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(RedBlackNode<T> parent) {
		this.parent = parent;
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
		this(data, leftChild, rightChild, BLACK);
	}
	
	public RedBlackNode(T data, RedBlackNode<T> leftChild, RedBlackNode<T> rightChild, int colour)
	{
		this(data, leftChild, rightChild, colour, 1);
	}
	
	public RedBlackNode(T data, RedBlackNode<T> leftChild, RedBlackNode<T> rightChild, int colour, int size)
	{
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.colour = colour;
		this.size = size;
		
	}
	
	public RedBlackNode(int i) {
		Integer iD = i;
		data = (T) iD;
	}

	/**
	 * @return the colour
	 */
	public int getColour() {
		return colour;
	}

	/**
	 * @param colour the colour to set
	 */
	public void setColour(int colour) {
		this.colour = colour;
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