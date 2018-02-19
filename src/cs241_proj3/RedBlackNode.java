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

	protected boolean black;
	
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
	
	public RedBlackNode(T data, RedBlackNode<T> leftChild, RedBlackNode<T> rightChild, boolean black)
	{
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.black = black;
	}
	
	public boolean isBlack ()
	{
		return black;
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
}
