package edu.iastate.cs228.hw3;

/*
 * @author ANHNGUYEN
 */

import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the list interface based on linked nodes that store
 * multiple items per node. Rules for adding and removing elements ensure that
 * each node (except possibly the last one) is at least half full.
 *
 * A link to the JavaDoc documentation for the interface AbstractSequentialList
 * <E> is provided next to the pdf spec on Blackboard.
 *
 * You should carefully study the complete methods given below to learn how to
 * go about implementing other methods.
 *
 * You are encouraged to introduce private methods that can be used to simplify
 * the implementation of public methods.
 */
public class ChunkyList<E extends Comparable<? super E>> extends AbstractSequentialList<E> {
	/**
	 * Default number of elements that may be stored in each node.
	 */
	private static final int DEFAULT_NODESIZE = 4;

	/**
	 * Number of elements that can be stored in each node.
	 */
	private final int nodeSize;

	/**
	 * Dummy node for head. It should be private but set to public here only for
	 * grading purpose. In practice, you should always make the head of a linked
	 * list a private instance variable.
	 */
	public Node head;

	/**
	 * Dummy node for tail.
	 */
	private Node tail;

	/**
	 * Number of elements in the list.
	 */
	private int size;

	private NodeInfo tempnode_modified = new NodeInfo(null,0);

	/**
	 * Constructs an empty list with the default node size.
	 */
	public ChunkyList() {
		this(DEFAULT_NODESIZE);
	}

	/**
	 * Constructs an empty list with the given node size.
	 * 
	 * @param nodeSize
	 *            number of elements that may be stored in each node, must be an
	 *            even number
	 */
	public ChunkyList(int nodeSize) {
		if (nodeSize <= 0 || nodeSize % 2 != 0)
			throw new IllegalArgumentException();

		// dummy nodes
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.previous = head;
		this.nodeSize = nodeSize;
	}

	/**
	 * Constructor for grading only. Fully implemented.
	 * 
	 * @param head
	 * @param tail
	 * @param nodeSize
	 * @param size
	 */
	public ChunkyList(Node head, Node tail, int nodeSize, int size) {
		this.head = head;
		this.tail = tail;
		this.nodeSize = nodeSize;
		this.size = size;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean add(E item) {

		int SizeTemp = size;
		add(size, item);
		boolean t = SizeTemp != size;
		return t;
	}

	@Override
	public void add(int pos, E item) {
		if (item == null)
			throw new NullPointerException();
		if (pos > size || pos < 0)
			throw new IndexOutOfBoundsException();
		NodeInfo newNodeNI = find(pos);
		Node NInode = newNodeNI.node;
		int NIOffset = newNodeNI.offSet;
		addNI(NInode, NIOffset, item);

	}

	@Override
	public E remove(int pos) {
		if (pos >= size || pos < 0)
			throw new IndexOutOfBoundsException();
		NodeInfo newNodeNI = find(pos);
		Node NInode = newNodeNI.node;
		int NIOffset = newNodeNI.offSet;
		return removeNI(NInode, NIOffset);

	}

	/**
	 * Removes each element (from the list) that is equal to an element in an
	 * array arr[]. You should use an efficient algorithm to implement this
	 * method. One efficient implementation is given as follows. Sort the array
	 * arr[] with Arrays.sort(). Use a ListIterator object to access each
	 * element in this list. If the element is found in the array arr[] with
	 * Arrays.binarySearch(), then remove the element from the list.
	 * 
	 * @param arr
	 *            array of (unsorted) elements
	 */
	public void removeAll(E[] arr) {
		Arrays.sort(arr);
		Iterator<E> iter = this.listIterator();
		E temp = null;
		while (iter.hasNext()) {
			temp = iter.next();
			if (Arrays.binarySearch(arr, temp) >= 0)
				iter.remove();
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new ChunkyIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new ChunkyListIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		return new ChunkyListIterator(index);
	}

	/**
	 * Returns a string representation of this list showing the internal
	 * structure of the nodes.
	 */
	public String toStringInternal() {
		return toStringInternal(null);
	}

	/**
	 * Returns a string representation of this list showing the internal
	 * structure of the nodes and the position of the iterator.
	 *
	 * This complete example illustrates how a method in this data structure is
	 * implemented. You should study this code carefully and use this method to
	 * show the contents of the list every time you implement and use a new
	 * method.
	 *
	 * @param iter
	 *            an iterator for this list
	 */
	public String toStringInternal(ListIterator<E> iter) {
		int count = 0;
		int position = -1;
		if (iter != null) {
			position = iter.nextIndex();
		}

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		Node current = head.next;
		while (current != tail) {
			sb.append('(');
			for (int i = 0; i < nodeSize; ++i) {
				if (i > 0)
					sb.append(", ");
				E data = current.data[i];
				if (data == null) {
					sb.append("-");
				} else {
					if (position == count) {
						sb.append("| ");
						position = -1;
					}
					sb.append(data.toString());
					++count;

					// iterator at end
					if (position == size && count == size) {
						sb.append(" |");
						position = -1;
					}
				}
			}
			sb.append(')');
			current = current.next;
			if (current != tail)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	
	/**
	 * an inner private class to represent a node and offset.
	 * @author anhvn93
	 *
	 */
	private class NodeInfo {
		private Node node ;
		private int offSet ;
		
		public NodeInfo (Node node, int offset){
	this.node = node;
	this.offSet = offset;
}
	}
	/**
	 * returns the node and offset for the given logical index
	 * 
	 * @param pos
	 *            logical index
	 * @return new node information
	 */
	private NodeInfo find(int pos) {
		NodeInfo nodeIF = new NodeInfo(null,0);
		int count = 0, offSet = 0;
		Node tempnode = head;
		while (pos >= count && tempnode != tail) {
			tempnode = tempnode.next;
			offSet = count;
			if (tempnode == tail) {
				if (tail.previous.count < nodeSize && tempnode.previous != head) {
					tempnode = tail.previous;
					offSet = pos - tempnode.count;
					break;
				}
			}
			count += tempnode.count;
		}
		nodeIF.node = tempnode;
		nodeIF.offSet = pos - offSet;
		return nodeIF;
	}
/*
 * helper add method whose arguments include the node and offset containing the index pos
  */
	private void addNI(Node n, int offset, E item) {
		if (offset == 0) {
			if ((n.previous == head || n.previous.count == nodeSize) && n.count == nodeSize) {
				Node temp = n.next;
				Node newnode = new Node();
				newnode.next = temp;
				temp.previous = newnode;
				n.next = newnode;
				newnode.previous = n;
				for (int i = nodeSize / 2, j = 0; i < nodeSize; i++, j++) {
					newnode.data[j] = n.data[i];
					n.data[i] = null;
				}
				n.count -= nodeSize / 2;
				newnode.count = nodeSize / 2;
				n.addItem(offset, item);
				size++;
				tempnode_modified.node = n;
				tempnode_modified.offSet = offset;
			} else if ((n.previous == head || n.previous.count == nodeSize) && n == tail) {
				Node newnode = new Node();
				Node tempNode = n.previous;
				n.previous = newnode;
				newnode.next = n;
				tempNode.next = newnode;
				newnode.previous = tempNode;
				newnode.addItem(offset, item);
				size++;
				tempnode_modified.node = newnode;
				tempnode_modified.offSet = offset;
			}

			else if (n.count < nodeSize && n.previous != head && n.previous.count == nodeSize) {
				n.addItem(offset, item);
				size++;
				tempnode_modified.node = n;
				tempnode_modified.offSet = offset;

			} else if (n.previous.count < nodeSize && n.previous != head) {
				n.previous.addItem(n.previous.count, item);
				tempnode_modified.node = n.previous;
				tempnode_modified.offSet = n.previous.count - 1;
				size++;
			}

		}

		else {
			if (n.count >= nodeSize) {
				Node tempnode = n.next;
				Node newnode = new Node();
				newnode.next = tempnode;
				tempnode.previous = newnode;
				n.next = newnode;
				newnode.previous = n;
				for (int i = nodeSize / 2, j = 0; i < nodeSize; i++, j++) {
					newnode.data[j] = n.data[i];
					n.data[i] = null;
				}
				n.count -= nodeSize / 2;
				newnode.count += nodeSize / 2;
				if (offset <= nodeSize / 2) {
					n.addItem(offset, item);
					size++;
					tempnode_modified.node = n;
					tempnode_modified.offSet = offset;
				} else {
					newnode.addItem(offset - nodeSize / 2, item);
					size++;
					tempnode_modified.node = newnode;
					tempnode_modified.offSet = offset - nodeSize / 2;
				}
			} else {
				n.addItem(offset, item);
				size++;
				tempnode_modified.node = n;
				tempnode_modified.offSet = offset;
			}

		}
	}

	/*
	 * helper remove method whose arguments include the node and offset containing the index pos
	 */

	private E removeNI(Node node_modified, int Offset) {
		E curr_element = null;
		if (node_modified.count >= 2 && node_modified.next == tail) {
			curr_element = node_modified.data[Offset];
			node_modified.removeItem(Offset);
			size--;
			tempnode_modified.node = node_modified;
			tempnode_modified.offSet = Offset;
		} else if (node_modified.count == 1 && node_modified.next == tail) {
			curr_element = node_modified.data[Offset];
			node_modified = node_modified.previous;
			node_modified.next = tail;
			tail.previous = node_modified;
			size--;
			tempnode_modified.node = node_modified;
			tempnode_modified.offSet = node_modified.count;
		}

		else if (node_modified.next != tail) {
			curr_element = node_modified.data[Offset];
			node_modified.removeItem(Offset);
			size--;
			tempnode_modified.node = node_modified;
			tempnode_modified.offSet = Offset;
			if (node_modified.count < nodeSize / 2) {
				if (node_modified.next.count <= nodeSize / 2) {
					for (int j = 0; j < node_modified.next.count; j++) {
						node_modified.addItem(node_modified.next.data[j]);
					}
					Node remove = node_modified.next;
					Node temp = remove.next;
					node_modified.next = temp;
					temp.previous = node_modified;
				} else {
					node_modified.addItem(node_modified.next.data[0]);
					node_modified.next.removeItem(0);
				}

			}
		}
		return curr_element;
	}

	/**
	 * Node type for this list. Each node holds a maximum of nodeSize elements
	 * in an array. Empty slots are null.
	 */
	private class Node {
		/**
		 * Array of actual data elements.
		 */
		// Unchecked warning unavoidable.
		public E[] data = (E[]) new Comparable[nodeSize];

		/**
		 * Link to next node.
		 */
		public Node next;

		/**
		 * Link to previous node;
		 */
		public Node previous;

		/**
		 * Index of the next available offset in this node, also equal to the
		 * number of elements in this node.
		 */
		public int count;

		/**
		 * Adds an item to this node at the first available offset.
		 * Precondition: count < nodeSize
		 * 
		 * @param item
		 *            element to be added
		 */
		void addItem(E item) {
			if (count >= nodeSize) {
				return;
			}
			data[count++] = item;
		}

		/**
		 * Adds an item to this node at the indicated offset, shifting elements
		 * to the right as necessary.
		 * 
		 * Precondition: count < nodeSize
		 * 
		 * @param offset
		 *            array index at which to put the new element
		 * @param item
		 *            element to be added
		 */
		void addItem(int offset, E item) {
			if (count >= nodeSize) {
				return;
			}
			for (int i = count - 1; i >= offset; --i) {
				data[i + 1] = data[i];
			}
			++count;
			data[offset] = item;

		}

		/**
		 * Deletes an element from this node at the indicated offset, shifting
		 * elements left as necessary. Precondition: 0 <= offset < count
		 * 
		 * @param offset
		 */
		void removeItem(int offset) {
			E item = data[offset];
			for (int i = offset + 1; i < nodeSize; ++i) {
				data[i - 1] = data[i];
			}
			data[count - 1] = null;
			--count;
		}
	}

	private class ChunkyListIterator implements ListIterator<E> {
		private int logical_cursor;
		private Node currentNode;
		private int current_offset;
		private int last;

		/**
		 * Default constructor
		 */
		public ChunkyListIterator() {
			logical_cursor = current_offset = 0;
			currentNode = head.next;
			last = -1;
		}

		/**
		 * Constructor finds node at a given position.
		 * 
		 * @param pos
		 */
		public ChunkyListIterator(int pos) {
			NodeInfo temp = find(pos);
			logical_cursor = pos;
			currentNode = temp.node;
			current_offset = temp.offSet;
			last = -1;
		}

		@Override
		public boolean hasNext() {
			return logical_cursor < size;
		}

		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			E curr_element;
			if (current_offset >= nodeSize || currentNode.data[current_offset] == null) {
				current_offset = 0;
				currentNode = currentNode.next;
			}
			curr_element = currentNode.data[current_offset++];
			last = current_offset - 1;
			logical_cursor++;
			return curr_element;
		}

		@Override
		public void remove() {
			if (last == -1)
				throw new IllegalStateException();
			removeNI(currentNode, last);
			Node temp = head.next;
			int total = 0;
			while (temp != tempnode_modified.node) {
				total += temp.count;
				temp = temp.next;
			}
			logical_cursor = tempnode_modified.offSet + total;
			NodeInfo nodeIF = find(logical_cursor);
			current_offset = nodeIF.offSet;
			currentNode = nodeIF.node;
			last = -1;
		}

		@Override
		public void add(E item) {
			if (item == null)
				throw new NullPointerException();
			addNI(currentNode, current_offset, item);
			Node temp = head.next;
			int total = 0;
			while (temp != tempnode_modified.node) {
				total += temp.count;
				temp = temp.next;
			}
			logical_cursor = total + tempnode_modified.offSet + 1;
			NodeInfo nodeIF = find(logical_cursor);
			currentNode = nodeIF.node;
			current_offset = nodeIF.offSet;
			last = -1;
		}

		@Override
		public boolean hasPrevious() {
			return logical_cursor > 0;
		}

		@Override
		public int nextIndex() {
			return logical_cursor;
		}

		@Override
		public E previous() {
			if (!hasPrevious())
				throw new NoSuchElementException();
			E curr_element = null;
			current_offset--;
			if ( currentNode == tail||current_offset < 0) {
				currentNode = currentNode.previous;
				current_offset = currentNode.count - 1;
			}
			curr_element = currentNode.data[current_offset];
			last = current_offset;
			logical_cursor--;
		return curr_element;
		}

		@Override
		public int previousIndex() {
			return logical_cursor - 1;
		}

		@Override
		public void set(E item) {
			
			if (last == -1)
				throw new IllegalStateException();
			currentNode.data[last] = item;
			last = -1;
		}
	}
/*
 *  ChunkyIterator class which only has 2 methods: hasNext() and next() to be overriden.
 *  User will be able to iterate forward only.
 */
	private class ChunkyIterator implements Iterator<E> {
		private Node currentNode ;
		private int logical_cursor ;
		private int Offset ;
		public ChunkyIterator(){
			currentNode = head.next	;
			logical_cursor = 0;
			Offset = 0;
			
		}

		@Override
		public boolean hasNext() {
			return logical_cursor < size;
		}

		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			E curr_element;
			if ( Offset >= nodeSize ) {
				Offset = 0;
				currentNode = currentNode.next;
			}
			logical_cursor++;
			curr_element = currentNode.data[Offset++];
			return curr_element;
		}

		
	}

	}
