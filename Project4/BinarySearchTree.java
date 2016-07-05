package edu.iastate.cs228.hw4;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * Extension of the AbstractCollection class based on a Binary Search Tree.
 * Efficiencies may vary by implementation, but all methods should have at least
 * the worst case runtimes of a standard Tree.
 * 
 * @author anhvn93
 */
public class BinarySearchTree<E extends Comparable<? super E>> extends AbstractCollection<E> {

	/**
	 * Member variables to support the tree: - A Node referencing the root of
	 * the tree - An int specifying the element count
	 */
	private Node<E> root;
	private int size;

	/**
	 * Constructs an empty BinarySearchTree
	 */
	public BinarySearchTree() {
	}

	/**
	 * Constructs a new BinarySearchTree whose root is exactly the given Node.
	 * (For testing purposes, set the root to the given Node, do not clone it)
	 * 
	 * @param root
	 *            - The root of the new tree
	 * @param size
	 *            - The number of elements already contained in the new tree
	 */
	public BinarySearchTree(Node<E> root, int size) {
		this.root = root;
		this.size = size;
	}

	private Node<E> addHelp(Node<E> a, Node<E> b, E item) {
		if (b == null) {
			b = new Node<E>(null, null, a, item);
			if (item.compareTo(a.getData()) < 0)
				a.setLeft(b);
			if (item.compareTo(a.getData()) > 0)
				a.setRight(b);
			
			size++;
		} else {
			int compare = b.getData().compareTo(item);
			if (compare == 0)
				return null;
			else if (compare > 0)
				addHelp(b, b.getLeft(), item);
			else
				addHelp(b, b.getRight(), item);
			balance(b);
		}
		return b;
	}

	private void removeHelp(Node<E> a, Node<E> b, E item) {
		if (b == null) {
			return;
		} else {
			if (item.compareTo(b.getData()) == 0) {
				if (b.getRight() == null&& b.getLeft() == null ) {
					if (a.getLeft() == b)
						a.setLeft(null);
					else
						a.setRight(null);
					b.setParent(null);
					size--;
				} else if (b.getRight() == null&& b.getLeft() != null   ||  b.getRight() != null&& b.getLeft() == null ) {
					if (b.getLeft() == null) {
						if (a.getRight() == b) {
							b.getRight().setParent(a);
							a.setRight(b.getRight());
						} else {
							a.setLeft(b.getRight());
							b.getRight().setParent(a);
						}
						b.setParent(null);
						b.setRight(null);
					} else {
						if (a.getLeft() == b) {
							b.getLeft().setParent(a);
							a.setLeft(b.getLeft());
						} else {
							a.setRight(b.getLeft());
							b.getLeft().setParent(a);
						}
						b.setParent(null);
						b.setLeft(null);
					}
					size--;
				} else {
					Node<E> next = b.getSuccessor();
					removeHelp(next.getParent(), next, next.getData());
					b.setData(next.getData());
				}
			}
			if (item.compareTo(b.getData()) < 0)
				removeHelp(b, b.getLeft(), item);
			if (item.compareTo(b.getData()) > 0)
				removeHelp(b, b.getRight(), item);
			balance(a);
		}
		return;
	}

	private void LeftTurn(Node<E> x) {
		Node<E> nr = x.getRight();
		if (x == root) {
			root = nr;
			nr.setParent(null);
		} else {
			nr.setParent(x.getParent());

			if (x.getParent().getLeft() != x)
				x.getParent().setRight(nr);
			else
				x.getParent().setLeft(nr);
		}
		x.setParent(nr);
		x.setRight(nr.getLeft());
		if (nr.getLeft() != null)
			nr.getLeft().setParent(x);
		nr.setLeft(x);
		balanceHeight(x);
		balanceHeight(nr);
	}

	private void balanceHeight(Node<E> t) {
		int right = 0;
		int left = 0;
		right = t.getRight() == null ? 0 : t.getRight().getHeight();
		left = t.getLeft() == null ? 0 : t.getLeft().getHeight();
		t.setHeight((left > right ? left : right) + 1);
		t.setBalance(left - right);
	}

	private void rightTurn(Node<E> x) {
		Node<E> nr = x.getLeft();
		if (x == root) {
			root = nr;
			nr.setParent(null);
		} else {
			nr.setParent(x.getParent());
			if (x.getParent().getRight() != x)
				x.getParent().setLeft(nr);
			else
				x.getParent().setRight(nr);
		}
		x.setParent(nr);
		x.setLeft(nr.getRight());
		if (nr.getRight() != null)
			nr.getRight().setParent(x);
		nr.setRight(x);
		balanceHeight(x);
		balanceHeight(nr);
	}

	private void balance(Node<E> n) {
		if (n == null)
			return;
		balanceHeight(n);
		if (n.getBalance() == -2) {
			if (n.getRight().getBalance() == -1)
				LeftTurn(n);
			else {
				rightTurn(n.getRight());
				LeftTurn(n);
			}
		}
		if (n.getBalance() == 2) {
			if (n.getLeft().getBalance() == 1)
				rightTurn(n);
			else {
				LeftTurn(n.getLeft());
				rightTurn(n);
			}
		}

	}

	/**
	 * Adds the given item to the tree if it is not already there.
	 * 
	 * @return false if item already exists in the tree and true otherwise.
	 * @param item
	 *            - Item to be added to the tree
	 * @throws IllegalArgumentException
	 *             - If item is null
	 */
	@Override
	public boolean add(E item) throws IllegalArgumentException {
		int sizes = size;
		if (item == null)
			throw new IllegalArgumentException();
		if (size == 0) {
			root = new Node<E>(item);
			size++;
		}
		addHelp(null, root, item);
		return sizes < size ? true : false;
	}

	/**
	 * Removes the given item from the tree if it is there. Because the item is
	 * an Object it will need to be cast to an E type. To verify that this is a
	 * safe cast, compare its class to the class of the root Node's data.
	 * 
	 * @return false if the list is empty or item does not exist in the tree,
	 *         true otherwise
	 * @param item
	 *            - The item to be removed from the tree
	 */

	@Override
	public boolean remove(Object item) {
		if (item == null)
			return false;
		if (size == 0 || root.getData().getClass() != item.getClass())
			return false;
		int sizes = size;
		removeHelp(null, root, (E) item);
		if (sizes > size)
			return true;
		else
			return false;

	}

	/**
	 * Retrieves data of the Node in the tree that contains item. i.e. the data
	 * such that Node.data.equals(item) is true
	 * 
	 * @return null if item does not exist in the tree, otherwise the data
	 *         stored at the Node that meets the condition above.
	 * @param item
	 *            - The item to be retrieved
	 */
	public E get(E item) {
		E returnval = null;
		Node<E> tmp = root;
		while (tmp != null) {
			if (tmp.getData().compareTo(item) > 0)
				tmp = tmp.getLeft();
			else if (tmp.getData().compareTo(item) < 0)
				tmp = tmp.getRight();
			else {
				returnval = tmp.getData();
				break;
			}
		}
		return returnval;
	}

	/**
	 * Tests whether or not item exists in the tree. i.e. this should only
	 * return true if a Node exists in the tree such that Node.data.equals(item)
	 * is true
	 * 
	 * @return false if item does not exist in the tree, otherwise true
	 * @param item
	 *            - The item check
	 */

	@Override
	public boolean contains(Object item) {
		boolean Okay = false;
		if (item == null)
			return false;
		if (size == 0 || root.getData().getClass() != item.getClass())
			return false;
		Node<E> tmp = root;
		while (tmp != null) {
			if (tmp.getData().compareTo((E) item) > 0)
				tmp = tmp.getLeft();
			else if (tmp.getData().compareTo((E) item) == 0) {
				Okay = true;
				break;
			} else
				tmp = tmp.getRight();
		}
		return Okay;
	}

	/**
	 * Removes all elements from the tree
	 */
	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * Tests whether or not the tree contains any elements.
	 * 
	 * @return false if the tree contains at least one element, true otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Retrieves the number of elements in the tree.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns a new BSTIterator instance.
	 */
	@Override
	public Iterator<E> iterator() {
		return new BSTIterator();
	}

	/**
	 * Returns an ArrayList containing all elements in the tree in the order
	 * given by a preorder traversal of the tree.
	 * 
	 * @return an ArrayList of elements from the traversal.
	 */
	public ArrayList<E> getPreorderTraversal() {
		Stack<Node<E>> stack = new Stack<Node<E>>();
		ArrayList<E> list = new ArrayList<E>();
		Node<E> tmp = root;
		stack.push(tmp);
		while (!stack.isEmpty()) {
			tmp = stack.pop();
			while (tmp != null) {
				list.add(tmp.getData());
				if (tmp.getRight() != null)
					stack.push(tmp.getRight());
				tmp = tmp.getLeft();
			}
		}
		return list;
	}

	/**
	 * Returns an ArrayList containing all elements in the tree in the order
	 * given by a postorder traversal of the tree.
	 * 
	 * @return an ArrayList of elements from the traversal.
	 */
	public ArrayList<E> getPostOrderTraversal() {
		Stack<Node<E>> res = new Stack<Node<E>>();
		ArrayList<E> list = new ArrayList<E>();
		Stack<Node<E>> stack = new Stack<Node<E>>();
		Node<E> tmp = root;
		stack.push(tmp);
		while (!stack.isEmpty()) {
			tmp = stack.pop();
			while (tmp != null) {
				res.push(tmp);
				if (tmp.getLeft() != null)
					stack.push(tmp.getLeft());
				tmp = tmp.getRight();
			}
		}
		while (!res.isEmpty())
			list.add(res.pop().getData());
		return list;

	}

	/**
	 * Returns an ArrayList containing all elements in the tree in the order
	 * given by a inorder traversal of the tree.
	 * 
	 * @return an ArrayList of elements from the traversal.
	 */
	public ArrayList<E> getInorderTravseral() {
		Node<E> prev = null;
		ArrayList<E> list = new ArrayList<E>();
		Node<E> tmp = root;
		while (tmp != null) {
			if (tmp.getLeft() == null) {
				list.add(tmp.getData());
				tmp = tmp.getRight();
			} else {
				prev = tmp.getLeft();
				while (prev.getRight() != tmp && prev.getRight() != null ) {
					prev = prev.getRight();
				}
				if (prev.getRight() == null) {
					prev.setRight(tmp);
					tmp = tmp.getLeft();
				} else {
					prev.setRight(null);
					list.add(tmp.getData());
					tmp = tmp.getRight();
				}
			}
		}

		return list;
	}

	/**
	 * Implementation of the Iterator interface which returns elements in the
	 * order of an inorder traversal using Nodes predecessor and successor.
	 * 
	 * @author AnhNguyen
	 */
	private class BSTIterator implements Iterator<E> {
		Node<E> current, lastNode;

		public BSTIterator() {
			current = root;
			if (current != null)
				while (current.getLeft() != null)
					current = current.getLeft();
			lastNode = null;
		}

		/**
		 * Returns true if more elements exist in the inorder traversal, false
		 * otherwise.
		 */
		@Override
		public boolean hasNext() {
			boolean re = ( current != null);
			return re;
		}

		/**
		 * Returns the next item in the inorder traversal.
		 * 
		 * @return the next item in the traversal.
		 * @throws IllegalStateException
		 *             - if no more elements exist in the traversal.
		 */
		@Override
		public E next() throws IllegalStateException {
			if (!hasNext())
				throw new IllegalStateException();
			lastNode = current;
			current = current.getSuccessor();
			 E returnval = lastNode.getData();
			 return returnval;
		}

		/**
		 * Removes the last item that was returned by calling next().
		 * 
		 * @throws IllegalStateException
		 *             - if next() has not been called yet or remove() is called
		 *             multiple times in a row.
		 * 
		 * 
		 * 
		 */
		@Override
		public void remove() throws IllegalStateException {
			if (lastNode == null)
				throw new IllegalStateException();
			if (lastNode.getRight() == null&& lastNode.getLeft() == null ) {
				if (lastNode.getParent().getLeft() == lastNode)
					lastNode.getParent().setLeft(null);
				else
					lastNode.getParent().setRight(null);
				lastNode.setParent(null);
			} else if (lastNode.getLeft() == null && lastNode.getRight() != null|| lastNode.getLeft() != null && lastNode.getRight() == null) {
				if (lastNode.getLeft() == null) {
					if (lastNode.getParent().getRight() == lastNode) {
						lastNode.getRight().setParent(lastNode.getParent());
						lastNode.getParent().setRight(lastNode.getRight());
						lastNode.setParent(null);
						lastNode.setRight(null);
					} else {
						lastNode.getParent().setLeft(lastNode.getRight());
						lastNode.getRight().setParent(lastNode.getParent());
						lastNode.setParent(null);
						lastNode.setRight(null);
					}
				} else {
					if (lastNode.getParent().getLeft() == lastNode) {
						lastNode.getLeft().setParent(lastNode.getParent());
						lastNode.getParent().setLeft(lastNode.getLeft());
						lastNode.setParent(null);
						lastNode.setLeft(null);
					} else {
						lastNode.getParent().setRight(lastNode.getLeft());
						lastNode.getLeft().setParent(lastNode.getParent());
						lastNode.setParent(null);
						lastNode.setLeft(null);
					}
				}
			} else {
				Node<E> node = lastNode.getRight();
				if (node.getLeft() == null) {
					lastNode.setData(node.getData());
					if (node.getRight() == null) {
						lastNode.setRight(null);
						node.setParent(null);
					} else {
						lastNode.setRight(node.getRight());
						node.getRight().setParent(lastNode);
						node.setParent(null);
						node.setRight(null);
					}
				} else {
					while (node.getLeft() != null)
						node = node.getLeft();
					lastNode.setData(node.getData());
					if (node.getRight() == null) {
						node.getParent().setLeft(null);
						node.setParent(null);
					} else {
						node.getParent().setLeft(node.getRight());
						node.getRight().setParent(node.getParent());
						node.setParent(null);
						node.setRight(null);
					}
				}
			}
			lastNode = null;
			size--;
			
		}
	}

}
