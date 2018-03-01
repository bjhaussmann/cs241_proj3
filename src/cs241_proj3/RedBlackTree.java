/**
 * 
 */
package cs241_proj3;

import java.util.Scanner;

/**
 * @author bjhau
 *
 */
public class RedBlackTree<T extends Comparable<? super T>> implements SearchTreeInterface<T> {
	private final int RED = 0;
    private final int BLACK = 1;

    private final RedBlackNode<T> nil = new RedBlackNode<T>(-1); 
    private RedBlackNode<T> root = nil;

    public void printTree(RedBlackNode<T> node) {
        if (node == nil) {
            return;
        }
        printTree(node.leftChild);
        System.out.print(((node.colour==RED)?"Color: Red ":"Color: Black ")+"Key: "+node.data+" Parent: "+node.parent.data+"\n");
        printTree(node.rightChild);
    }

    private RedBlackNode<T> findNode(RedBlackNode<T> findNode, RedBlackNode<T> node) {
        if (root == nil) {
            return null;
        }

        if (findNode.data.compareTo(node.data) < 0) {
            if (node.leftChild != nil) {
                return findNode(findNode, node.leftChild);
            }
        } else if (findNode.data.compareTo(node.data) > 0) {
            if (node.rightChild != nil) {
                return findNode(findNode, node.rightChild);
            }
        } else if (findNode.data == node.data) {
            return node;
        }
        return null;
    }

    private void insert(RedBlackNode<T> node) {
    	RedBlackNode<T> temp = root;
        if (root == nil) {
            root = node;
            node.colour = BLACK;
            node.parent = nil;
        } else {
            node.colour = RED;
            while (true) {
                if (node.data.compareTo(temp.data) < 0) {
                    if (temp.leftChild == nil || temp.leftChild==null) {
                        temp.leftChild = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.leftChild;
                    }
                } else if (node.data.compareTo(temp.data) >= 0) {
                    if (temp.rightChild == nil || temp.rightChild==null) {
                        temp.rightChild = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.rightChild;
                    }
                }
            }
            fixTree(node);
        }
    }

    //Takes as argument the newly inserted node
    private void fixTree(RedBlackNode<T> node) {
        while (node.parent.colour == RED) {
        	RedBlackNode<T> uncle = nil;
            	if(node.parent.parent != null && node.parent.parent.leftChild != null && node.parent == node.parent.parent.leftChild) {
	                uncle = node.parent.parent.rightChild;
	
	                if (uncle != nil && uncle.colour == RED) {
	                    node.parent.colour = BLACK;
	                    uncle.colour = BLACK;
	                    node.parent.parent.colour = RED;
	                    node = node.parent.parent;
	                    continue;
	                } 
	                if (node == node.parent.rightChild) {
	                    //Double rotation needed
	                    node = node.parent;
	                    rotateLeft(node);
	                } 
	                node.parent.colour = BLACK;
	                node.parent.parent.colour = RED;
	                //if the "else if" code hasn't executed, this
	                //is a case where we only need a single rotation 
	                rotateRight(node.parent.parent);
            } else {
            	if(node.parent != null && node.parent.parent != null && node.parent.parent.leftChild != null) {
	                uncle = node.parent.parent.leftChild;
	                 if (uncle != nil && uncle.colour == RED && uncle != null) {
	                    node.parent.colour = BLACK;
	                    uncle.colour = BLACK;
	                    node.parent.parent.colour = RED;
	                    node = node.parent.parent;
	                    continue;
	                }
            	}
                if (node.parent != null && node.parent.leftChild != null && node == node.parent.leftChild) {
                    //Double rotation needed
                    node = node.parent;
                    rotateRight(node);
                }
                node.parent.colour = BLACK;
                if ( node.parent.parent != null)
                	node.parent.parent.colour = RED;
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation
                if ( node.parent.parent != null)
                	rotateLeft(node.parent.parent);
            }
        }
        root.colour = BLACK;
    }

    void rotateLeft(RedBlackNode<T> node) {
        if (node.parent != nil) {
            if (node == node.parent.leftChild) {
                node.parent.leftChild = node.rightChild;
            } else {
                node.parent.rightChild = node.rightChild;
            }
            node.rightChild.parent = node.parent;
            node.parent = node.rightChild;
            if (node.rightChild.leftChild != nil) {
                node.rightChild.leftChild.parent = node;
            }
            node.rightChild = node.rightChild.leftChild;
            node.parent.leftChild = node;
        } else {//Need to rotate root
        	RedBlackNode<T> right = root.rightChild;
            root.rightChild = right.leftChild;
            right.leftChild.parent = root;
            root.parent = right;
            right.leftChild = root;
            right.parent = nil;
            root = right;
        }
    }

    void rotateRight(RedBlackNode<T> node) {
        if (node.parent != nil) {
            if (node == node.parent.leftChild) {
                node.parent.leftChild = node.leftChild;
            } else {
                node.parent.rightChild = node.leftChild;
            }

            node.leftChild.parent = node.parent;
            node.parent = node.leftChild;
            if (node.leftChild.rightChild != nil) {
                node.leftChild.rightChild.parent = node;
            }
            node.leftChild = node.leftChild.rightChild;
            node.parent.rightChild = node;
        } else {//Need to rotate root
        	RedBlackNode<T> left = root.leftChild;
            root.leftChild = root.leftChild.rightChild;
            left.rightChild.parent = root;
            root.parent = left;
            left.rightChild = root;
            left.parent = nil;
            root = left;
        }
    }
    
    //Deletion Code .
    
    //This operation doesn't care about the new Node's connections
    //with previous node's left and right. The caller has to take care
    //of that.
    void transplant(RedBlackNode<T> target, RedBlackNode<T> with){ 
          if(target.parent == nil){
              root = with;
          }else if(target == target.parent.leftChild){
              target.parent.leftChild = with;
          }else
              target.parent.rightChild = with;
          with.parent = target.parent;
    }
    
    boolean delete(RedBlackNode<T> z){
        if((z = findNode(z, root))==null)return false;
        RedBlackNode<T> x;
        RedBlackNode<T> y = z; // temporary reference y
        int y_original_color = y.colour;
        
        if(z.leftChild == nil){
            x = z.rightChild;  
            transplant(z, z.rightChild);  
        }else if(z.rightChild == nil){
            x = z.leftChild;
            transplant(z, z.leftChild); 
        }else{
            y = treeMinimum(z.rightChild);
            y_original_color = y.colour;
            x = y.rightChild;
            if(y.parent == z)
                x.parent = y;
            else{
                transplant(y, y.rightChild);
                y.rightChild = z.rightChild;
                y.rightChild.parent = y;
            }
            transplant(z, y);
            y.leftChild = z.leftChild;
            y.leftChild.parent = y;
            y.colour = z.colour; 
        }
        if(y_original_color==BLACK)
            deleteFixup(x);  
        return true;
    }
    
    void deleteFixup(RedBlackNode<T> x){
        while(x!=root && x.colour == BLACK){ 
            if(x == x.parent.leftChild){
            	RedBlackNode<T> w = x.parent.rightChild;
                if(w.colour == RED){
                    w.colour = BLACK;
                    x.parent.colour = RED;
                    rotateLeft(x.parent);
                    w = x.parent.rightChild;
                }
                if(w.leftChild.colour == BLACK && w.rightChild.colour == BLACK){
                    w.colour = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.rightChild.colour == BLACK){
                    w.leftChild.colour = BLACK;
                    w.colour = RED;
                    rotateRight(w);
                    w = x.parent.rightChild;
                }
                if(w.rightChild.colour == RED){
                    w.colour = x.parent.colour;
                    x.parent.colour = BLACK;
                    w.rightChild.colour = BLACK;
                    rotateLeft(x.parent);
                    x = root;
                }
            }else{
            	RedBlackNode<T> w = x.parent.leftChild;
                if(w.colour == RED){
                    w.colour = BLACK;
                    x.parent.colour = RED;
                    rotateRight(x.parent);
                    w = x.parent.leftChild;
                }
                if(w.rightChild.colour == BLACK && w.leftChild.colour == BLACK){
                    w.colour = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.leftChild.colour == BLACK){
                    w.rightChild.colour = BLACK;
                    w.colour = RED;
                    rotateLeft(w);
                    w = x.parent.leftChild;
                }
                if(w.leftChild.colour == RED){
                    w.colour = x.parent.colour;
                    x.parent.colour = BLACK;
                    w.leftChild.colour = BLACK;
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }
        x.colour = BLACK; 
    }
    
    RedBlackNode <T> treeMinimum(RedBlackNode<T> subTreeRoot){
        while(subTreeRoot.leftChild!=nil){
            subTreeRoot = subTreeRoot.leftChild;
        }
        return subTreeRoot;
    }
    
    /*public void consoleUI() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.- Add items\n"
                    + "2.- Delete items\n"
                    + "3.- Check items\n"
                    + "4.- Print tree\n"
                    + "5.- Delete tree\n");
            int choice = scan.nextInt();

            int item;
            RedBlackNode<T> node;
            switch (choice) {
                case 1:
                    item = scan.nextInt();
                    while (item != -999) {
                        node = new RedBlackNode<T>(item);
                        insert(node);
                        item = scan.nextInt();
                    }
                    printTree(root);
                    break;
                case 2:
                    item = scan.nextInt();
                    while (item != -999) {
                        node = new RedBlackNode<T>(item);
                        System.out.print("\nDeleting item " + item);
                        if (delete(node)) {
                            System.out.print(": deleted!");
                        } else {
                            System.out.print(": does not exist!");
                        }
                        item = scan.nextInt();
                    }
                    System.out.println();
                    printTree(root);
                    break;
                case 3:
                    item = scan.nextInt();
                    while (item != -999) {
                        node = new RedBlackNode<T>(item);
                        System.out.println((findNode(node, root) != null) ? "found" : "not found");
                        item = scan.nextInt();
                    }
                    break;
                case 4:
                    printTree(root);
                    break;
                case 5:
                    clear();
                    System.out.println("Tree deleted!");
                    break;
            }
        }
    }
    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();
        rbt.consoleUI();
    }*/

	@Override
	public void clear() {
		root = nil;
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
		return root.data;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T add(T newEntry) {
		RedBlackNode<T>node = new RedBlackNode<T>(newEntry);
        insert(node);
		return newEntry;
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
		RedBlackNode<T> node = new RedBlackNode<T>(entry);
		if (delete(node))
			return entry;
		else return null;
	}
}