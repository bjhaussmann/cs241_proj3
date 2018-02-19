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
}
