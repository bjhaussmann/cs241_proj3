/**
 * 
 */
package cs241_proj3;

/**
 * @author bjhau
 *
 */
public interface TreeInterface<T> {

	public void clear();

	public int getHeight();

	public int getNumberOfNodes();

	public T getRootData();

	public boolean isEmpty();
}
