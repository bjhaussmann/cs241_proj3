/**
 * 
 */
package cs241_proj3;

/**
 * @author bjhau
 *
 */
public interface SearchTreeInterface<T extends Comparable<? super T>> extends TreeInterface<T> {

	/**
	 * Add a new entry to a tree
	 * @param newEntry	The new entry to be added
	 * @return	return the input if it already exists
	 */
	public T add(T newEntry);

	/**
	 * Check to see if a value is already contained in the tree
	 * @param entry	 the value to check for
	 * @return true if the value already exists, else false
	 */
	public boolean contains(T entry);

	/**
	 * return an entry
	 * @param entry	the entry to check for
	 * @return returns the entry if it exists
	 */
	public T getEntry(T entry);

	/**
	 * Removes a given entry from the tree
	 * @param entry	the entry to be removed
	 * @return	returns the entry if it was successfully removed, else null
	 */
	public T remove(T entry);
}
