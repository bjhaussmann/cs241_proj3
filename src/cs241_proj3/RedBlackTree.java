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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfNodes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T getRootData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T add(T newEntry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(T entry) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T getEntry(T entry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T remove(T entry) {
		// TODO Auto-generated method stub
		return null;
	}

}
