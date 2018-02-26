/**
 * 
 */
package cs241_proj3;


/**
 * @author bjhau
 *
 */
public class RedBlackTree<T extends Comparable<? super T>> implements SearchTreeInterface<T> {

	private RedBlackNode<T> root;
	
	public RedBlackTree() {
		root = null;
	}

	public RedBlackTree(T root) {
		this.root = new RedBlackNode<>(root);
	}

	public RedBlackTree(T root, RedBlackTree<T> left, RedBlackTree<T> right) {
		privateSetTree(root, left, right);
	}
	
	/**
	 * Creates a new tree with the given root and subtrees
	 * @param root	Root of the tree
	 * @param left	Left child of the root
	 * @param right	Right child of the root
	 */
	private void privateSetTree(T root, RedBlackTree<T> left, RedBlackTree<T> right) {
		this.root = new RedBlackNode<>(root);

		if ((left != null) && !left.isEmpty())
			this.root.setLeftChild(left.root.copy());
		if ((right != null) && !right.isEmpty()) {
			if (right != left)
				this.root.setRightChild(right.root);
			else
				this.root.setRightChild(right.root.copy());
		}
		if ((left != null) && (left != this))
			left.clear();
		if ((right != null) && (right != this))
			right.clear();
	}
	
	@Override
	public void clear() {
		root = null;
	}

	@Override
	public int getHeight() {
		int leftMax = 0;
		int rightMax = 0;
		int max = 0;
		if (root.hasLeftChild())
			leftMax = root.getLeftChild().getHeight();
		if (root.hasRightChild())
			rightMax = root.getRightChild().getHeight();
		max = Math.max(leftMax, rightMax);
		return max;
	}

	@Override
	public int getNumberOfNodes() {
		return root.getSize();
	}

	@Override
	public T getRootData() {
		return root.getData();
	}

	@Override
	public boolean isEmpty() {
		return (root == null);
	}

	@Override
	public T add(T newEntry) {
		T result = null;
		if (isEmpty())
			root = (new RedBlackNode<>(newEntry));
		else
			result = addEntry(root, newEntry).getData();
			root.setRed(false);
		return result;
	}

	private RedBlackNode <T> addEntry(RedBlackNode<T> rootNode, T newEntry) {
		int cmp = newEntry.compareTo(rootNode.getData());
		if (cmp < 0)
			rootNode.setLeftChild(addEntry (rootNode.getLeftChild(), newEntry));
		if(cmp > 0)
			rootNode.setRightChild(addEntry (rootNode.getRightChild(), newEntry));
		else
			rootNode.setData(newEntry);
		
		if(rootNode.getRightChild().isRed() && !rootNode.getLeftChild().isRed())
			rootNode = rotateLeft(rootNode);
		if(rootNode.getLeftChild().isRed() && rootNode.getLeftChild().getLeftChild().isRed())
			rootNode = rotateLeft(rootNode);
		if(rootNode.getLeftChild().isRed() && rootNode.getRightChild().isRed())
			flipColours(rootNode);
		rootNode.setSize(rootNode.getLeftChild().getSize() + rootNode.getRightChild().getSize() + 1);
		
		return rootNode;
	}
	
	private RedBlackNode <T> rotateLeft (RedBlackNode <T> node)
	{
		RedBlackNode <T> newNode = node.getRightChild();
		
		newNode.setRightChild(node.getLeftChild());
		newNode.setLeftChild(node);
		newNode.setRed(newNode.getLeftChild().isRed());
		newNode.getLeftChild().setRed(true);
		newNode.setSize(node.getSize());
		node.setSize(node.getLeftChild().getSize() + node.getRightChild().getSize() + 1);
		
		return newNode;
	}
	
	private RedBlackNode <T> flipColours (RedBlackNode<T> node)
	{
		node.setRed(!node.isRed());
		node.getLeftChild().setRed(!node.getLeftChild().isRed());
		node.getRightChild().setRed(!node.getRightChild().isRed());
		return node;
	}
	
	@Override
	public boolean contains(T entry) {
		return getEntry(entry) != null;
	}
	
	public T get (RedBlackNode <T> node, T entry)
	{
		T result = null;
		
		int cmp = entry.compareTo(node.getData());
		if(cmp < 0)
			result = get(node.getLeftChild(), entry);
		else if (cmp > 0)
			result = get(node.getRightChild(), entry);
		else 
			result = node.getData();
		return result;
	}

	@Override
	public T getEntry(T entry) {
		return get(root, entry);
	}

	@Override
	public T remove(T entry) {
		if (!contains(entry))
			return null;
		
		if(!root.getLeftChild().isRed() && !root.getRightChild().isRed())
			root.setRed(true);
		
		root = remove(root, entry);
		if(!isEmpty())
			root.setRed(false);
		
		return entry;
	}
	
	private RedBlackNode <T> remove(RedBlackNode<T> node, T entry)
	{
		if(entry.compareTo(node.getData()) < 0) {
			if (!node.getLeftChild().isRed() && !node.getLeftChild().getLeftChild().isRed()) {
				node = moveRedLeft(node);
			}
			node.setLeftChild(remove(node.getLeftChild(), entry));
		}
		else {
			if(node.getLeftChild().isRed())
				node = rotateRight(node);
			if(entry.compareTo(node.getData()) == 0 && (node.getRightChild() == null))
				return null;
			if(!node.getRightChild().isRed() && !node.getRightChild().getLeftChild().isRed())
				node = moveRedRight(node);
			if(entry.compareTo(node.getData()) == 0) {
				RedBlackNode <T> current = min (node.getData());
				node.getData() = current.getData();
				node.getRightChild() = deleteMin(node.getRightChild());
			}
			else node.getRightChild()=remove(node.getRightChild(), entry);
		}
		return balance(node);
	}
}
