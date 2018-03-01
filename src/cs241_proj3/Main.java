/**
 * 
 */
package cs241_proj3;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author bjhau
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int option = 0;
		boolean quit = false;
		Scanner in = new Scanner(System.in);
		boolean accepted = false;
		RedBlackTree <Integer> rbTree = new RedBlackTree<Integer>();
		BinarySearchTree <Integer> bsTree = new BinarySearchTree<Integer>();
		
		while (!quit)
		{
			System.out.println("Please make a selection:");
			System.out.println("\t1. Insert a new value.");
			System.out.println("\t2. Delete a value.");
			System.out.println("\t3. Print a count of the leaves on both trees.");
			System.out.println("\t4. Print all values in the tree between two numbers.");
			System.out.println("\t5. Delete the first 20 values.");
			System.out.println("\t6. Quit.");
			System.out.println("Selection: ");
			
			accepted = false;
			option = 0;
			
while (!accepted)
			{
				try {
					option = in.nextInt();
					if (option < 1 || option > 6)
					{
						accepted = false;
						System.out.println("\nInvalid command. Try again: ");
					}else
					{
						accepted = true;
					}
				}catch (NumberFormatException e) {
					System.out.print("\nInvalid command. Try again: ");
					accepted = false;
				}
				catch (InputMismatchException e)
				{
					System.out.print("\nInvalid command. Try again: ");
					in.nextLine();
					accepted = false;
				}
			}
			
			switch (option)
			{
				case 1:
					System.out.println("Enter integer to be added: ");
					int next = in.nextInt();
					rbTree.add(next);
					bsTree.add(next);
					break;
				case 2:
					System.out.println("Enter integer to be deleted: ");
					next = in.nextInt();
					if (rbTree.remove(next) == null)
						System.out.println("Item was not in tree!");
					bsTree.remove(next);
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					quit = true;
					break;
				default:
			}
		}
		in.close();
	}
}
