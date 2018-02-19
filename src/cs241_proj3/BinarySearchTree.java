/**
 * 
 */
package cs241_proj3;

/**
 * @author bjhau
 *
 */
public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> implements SearchTreeInterface<T> {
	public BinarySearchTree() {
		super();
	}

	public BinarySearchTree(T rootEntry) {
		super();
		setRootNode(new BinaryNode<T>(rootEntry));
	}

	/**
	 * add entry helper
	 */
	@Override
	public T add(T newEntry) {
		T result = null;
		if (isEmpty())
			setRootNode(new BinaryNode<>(newEntry));
		else
			result = addEntry(getRootNode(), newEntry);
		return result;
	}

	/**
	 * Add an entry to the tree
	 * @param rootNode	 root of the tree
	 * @param newEntry	entry to be added
	 * @return	returns the replaced value if the entry already existed, else null
	 */
	private T addEntry(BinaryNode<T> rootNode, T newEntry) {
		assert rootNode != null;
		T result = null;
		int comparison = newEntry.compareTo(rootNode.getData());

		if (comparison == 0) {
			result = rootNode.getData();
			rootNode.setData(newEntry);
		} else if (comparison < 0) {
			if (rootNode.hasLeftChild())
				result = addEntry(rootNode.getLeftChild(), newEntry);
			else
				rootNode.setLeftChild(new BinaryNode<>(newEntry));
		} else {
			assert comparison > 0;
			if (rootNode.hasRightChild())
				result = addEntry(rootNode.getRightChild(), newEntry);
			else
				rootNode.setRightChild(new BinaryNode<>(newEntry));
		}
		return result;
	}

	/**
	 * check to see if the tree contains a specified value
	 */
	@Override
	public boolean contains(T entry) {
		return getEntry(entry) != null;
	}

	/**
	 * Finds an entry and returns it
	 * @param rootNode	 root of the tree containing the entry
	 * @param entry the entry to be found
	 * @return the entry if it is found. else null
	 */
	private T findEntry(BinaryNode<T> rootNode, T entry) {
		T result = null;
		if (rootNode != null) {
			T rootEntry = rootNode.getData();
			if (entry.equals(rootEntry))
				result = rootEntry;
			else if (entry.compareTo(rootEntry) < 0)
				result = findEntry(rootNode.getLeftChild(), entry);
			else
				result = findEntry(rootNode.getRightChild(), entry);
		}
		return result;
	}

	private BinaryNode<T> findLargest(BinaryNode<T> rootNode) {
		if (rootNode.hasRightChild())
			rootNode = findLargest(rootNode.getRightChild());
		return rootNode;
	}

	/**
	 * Find the predecessor helper method
	 * @param num	num looking for the predecessor for
	 * @return the predecessor of the given value. returns the value if it is the first in the traversal
	 */
	public int findPredecessor(int num) {
		int count = 0;
		String[] strArray = this.inorderTraverse().split("\\s+");
		return privatePredecessor(num, strArray, count);
	}

	/** 
	 * Find successor helper method
	 * @param num	num looking for the successor for
	 * @return	the successor of the given value. returns the given value if it is the last in the traversal
	 */
	public int findSuccessor(int num) {
		int count = 0;
		String[] strArray = this.inorderTraverse().split("\\s+");
		return privateSuccessor(num, strArray, count);
	}

	/**
	 * Returns the entry.
	 */
	@Override
	public T getEntry(T entry) {
		return findEntry(getRootNode(), entry);
	}

	/**
	 * Finds the predecessor of a given entry
	 * @param num	entry looking for
	 * @param strArray	inorder array
	 * @param count	level of recursion
	 * @return	pred	the predecessor of the given entry. returns the entry if it the the first in the traversal
	 */
	private int privatePredecessor(int num, String[] strArray, int count) {
		int pred = num;
		if (num != Integer.parseInt(strArray[0]))
			if (Integer.parseInt(strArray[count]) == num)
				pred = Integer.parseInt(strArray[count - 1]);
			else if (count < strArray.length - 1)
				pred = privatePredecessor(num, strArray, count + 1);
		return pred;
	}

	/**
	 * Finds the successor of a given entry
	 * @param num	entry looking for
	 * @param strArray	inorder array
	 * @param count	level of recursion
	 * @return	succ	the successor to the given entry. returns the entry if it is the last one int he traversal
	 */
	private int privateSuccessor(int num, String[] strArray, int count) {
		int succ = num;
		if (num == Integer.parseInt(strArray[strArray.length - 1]))
			return succ;
		if (Integer.parseInt(strArray[count]) == num)
			succ = Integer.parseInt(strArray[count + 1]);
		else if (count < strArray.length - 1)
			succ = privateSuccessor(num, strArray, count + 1);
		return succ;
	}

	/**
	 * RemovesEntry helper method
	 */
	@Override
	public T remove(T entry) {
		T oldEntry = null;
		BinaryNode<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);
		setRootNode(newRoot);
		return oldEntry;
	}

	/**
	 * Removes an entry from the tree
	 * @param rootNode	root of the tree that contains the value
	 * @param entry	Entry to be removed
	 * @param oldEntry	Only used if the entry already existed
	 * @return	rootNode	null if the entry did not exist, else the entry removed.
	 */
	private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T entry, T oldEntry) {
		if (rootNode != null) {
			T rootData = rootNode.getData();
			int comparison = entry.compareTo(rootData);

			if (comparison == 0) {
				oldEntry = rootData;
				rootNode = removeFromRoot(rootNode);
			} else if (comparison < 0) {
				BinaryNode<T> leftChild = rootNode.getLeftChild();
				BinaryNode<T> subtreeRoot = removeEntry(leftChild, entry, oldEntry);
				rootNode.setLeftChild(subtreeRoot);
			} else {
				BinaryNode<T> rightChild = rootNode.getRightChild();
				rootNode.setRightChild(removeEntry(rightChild, entry, oldEntry));
			}
		}
		return rootNode;
	}

	/**
	 * Helps the remove entry
	 * @param rootNode Root of the tree the entry is being removed from
	 * @return	rootNode	root of the tree
	 */
	private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode) {
		if (rootNode.hasLeftChild() && rootNode.hasRightChild()) {
			BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild();
			BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);

			rootNode.setData(largestNode.getData());
			rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
		} else if (rootNode.hasRightChild())
			rootNode = rootNode.getRightChild();
		else
			rootNode = rootNode.getLeftChild();
		return rootNode;
	}

	/**
	 * Helps remove the entry
	 * @param rootNode	root of the tree containing the entry
	 * @return	rootNode	root of the tree
	 */
	private BinaryNode<T> removeLargest(BinaryNode<T> rootNode) {
		if (rootNode.hasRightChild()) {
			BinaryNode<T> rightChild = rootNode.getRightChild();
			rightChild = removeLargest(rightChild);
			rootNode.setRightChild(rightChild);
		} else
			rootNode = rootNode.getLeftChild();
		return rootNode;
	}

	public void setTree(T rootData) {
		throw new UnsupportedOperationException();
	}
}
