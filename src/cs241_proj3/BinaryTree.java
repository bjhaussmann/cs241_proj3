/**
 * 
 */
package cs241_proj3;

/**
 * @author bjhau
 *
 */
public class BinaryTree<T> implements BinaryTreeInterface<T> {

	private BinaryNode<T> root;

	public BinaryTree() {
		root = null;
	}

	public BinaryTree(T root) {
		this.root = new BinaryNode<>(root);
	}

	public BinaryTree(T root, BinaryTree<T> left, BinaryTree<T> right) {
		privateSetTree(root, left, right);
	}

	@Override
	/**
	 * erases the tree
	 */
	public void clear() {
		root = null;
	}

	/**
	 * Returns the height of the tree.
	 */
	@Override
	public int getHeight() {
		return root.getHeight();
	}

	/**
	 * Returns the number of nodes in the tree.
	 */
	@Override
	public int getNumberOfNodes() {
		return root.getNumberOfNodes();
	}

	/**
	 * Returns the data from the root of the tree.
	 */
	@Override
	public T getRootData() {
		if (isEmpty())
			return null;
		else
			return root.getData();
	}

	/**
	 * Returns the entire root node object.
	 */
	protected BinaryNode<T> getRootNode() {
		return root;
	}

	/**
	 * Inorder Traversal helper
	 * @return String containing the traversal
	 */
	public String inorderTraverse() {
		return inorderTraverse(root);
	}

	/**
	 * Performs an inorder traversal on a binary tree
	 * @param node	Root to start the traversal
	 * @return	result	String containing the correct traversal
	 */
	private String inorderTraverse(BinaryNode<T> node) {
		String result = "";
		if (node != null) {
			result += inorderTraverse(node.getLeftChild());
			result += node.getData() + " ";
			result += inorderTraverse(node.getRightChild());
		}
		return result;
	}

	/**
	 * returns true if the tree is empty, else false.
	 */
	@Override
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Postorder traversal helper
	 * @return String containing the traversal
	 */
	public String postorderTraverse() {
		return postorderTraverse(root);
	}

	/**
	 * Performs a postorder traversal of a given node
	 * @param node	Root to start the traversal
	 * @return result	String containing the correct traversal
	 */
	private String postorderTraverse(BinaryNode<T> node) {
		String result = "";
		if (node != null) {
			result += postorderTraverse(node.getLeftChild());
			result += postorderTraverse(node.getRightChild());
			result += node.getData() + " ";
		}
		return result;
	}

	/**
	 * Preorder traversal helper
	 * @return String containing the traversal
	 */
	public String preorderTraverse() {
		return preorderTraverse(root);
	}

	/**
	 * Performs a preorder traversal of a given node
	 * @param node	Root to start the traversal
	 * @return	result	String containing the correct traversal
	 */
	private String preorderTraverse(BinaryNode<T> node) {
		String result = "";
		if (node != null) {
			result += node.getData() + " ";
			result += preorderTraverse(node.getLeftChild());
			result += preorderTraverse(node.getRightChild());
		}
		return result;
	}

	/**
	 * Creates a new tree with the given root and subtrees
	 * @param root	Root of the tree
	 * @param left	Left child of the root
	 * @param right	Right child of the root
	 */
	private void privateSetTree(T root, BinaryTree<T> left, BinaryTree<T> right) {
		this.root = new BinaryNode<>(root);

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

	protected void setRootData(T root) {
		this.root.setData(root);
	}

	protected void setRootNode(BinaryNode<T> root) {
		this.root = root;
	}

	public void setTree(T root) {
		this.root = new BinaryNode<>(root);
	}

	public void setTree(T root, BinaryTreeInterface<T> left, BinaryTreeInterface<T> right) {
		privateSetTree(root, (BinaryTree<T>) left, (BinaryTree<T>) right);
	}
}
