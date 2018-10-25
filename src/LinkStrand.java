
/**
 * LinkStrand class for the DNALinked project. This class uses a linked list
 * and nodes to represent the DNA rather than a string or string builder, 
 * making it more efficient. It also implements the IDnaStrand interface.
 * @author Virginia Capehart and Ben Williams
 * @date due 10/25/18
 */

public class LinkStrand implements IDnaStrand{
	
	/**
	 * Node class, used for implementation of the internal linked list.
	 * @author Virginia Capehart and Ben Williams
	 */
	private class Node {
		String info;
		Node next;
		
		/**
		 * Basic parameterized constructor for the Node class. Creates a Node
		 * object with the given String.
		 * @param s
		 */
		public Node(String s) {
			info = s;
			next = null;
		}
	}
		   
	private Node myFirst,myLast;
	private long mySize;
	private int myAppends;
	
	private Node myCurrent;
	private int myIndex;
	private int myLocalIndex; 
	
	
	/**
	 * Default constructor. Creates a new object with an empty String value
	 */
	public LinkStrand() {
		this("");
	}
	
	/**
	 * Parameterized constructor. Creates a new object using the parameter s
	 * @param s, the String to be the info for the LinkStrand
	 */
	public LinkStrand(String s) {
		initialize(s);
	}
	
	/**
	 * Overriden size method. Has O(1) runtime and returns the value of the size
	 * for the LinkStrand on which it was called.
	 * @return mySize, the instance variable for the size of this LinkStrand
	 */
	@Override
	public long size() {
		return mySize;
	}

	/**
	 * Overriden initialize method. Sets the source parameter equal to the first
	 * node, and initializes instance variables used later in charAt.
	 * @param source the string to be the first node
	 */
	@Override
	public void initialize(String source) {
		myFirst = new Node(source);
		myLast = myFirst;
		myAppends = 0;
		mySize = source.length();
		myCurrent = myFirst;
		myIndex = 0;
		myLocalIndex = 0;
	}

	/**
	 * Overriden getInstance method. Returns a new LinkStrand using the parameter
	 * source as the first node. 
	 * @param source, the string to be equal to the first node in the new 
	 * LinkStrand
	 * @return an IDnaStrand with the parameter source
	 */
	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	/**
	 * Overriden append method. Takes in a string, dna, and adds it to the end
	 * of the internal linked list. This method is the only way that a node can
	 * be added.
	 * @param dna, the string to be added to the LinkStrand
	 * @return the IDnaStrand on which the method is called, but with the 
	 * addition of the new node
	 */
	@Override
	public IDnaStrand append(String dna) {
		Node dnaNode = new Node(dna);
		myLast.next = dnaNode;
		myLast = myLast.next;
		
		mySize += dna.length();
		myAppends++;
		return this;
	}

	/**
	 * Overriden reverse method. This method is not a mutator, but rather it
	 * creates a new LinkStrand and fills it with the reverse of the LinkStrand
	 * on which the method was called. 
	 * @return an IDnaStrand object representing the reverse of the object on 
	 * which the method was called
	 */
	@Override
	public IDnaStrand reverse() {
		LinkStrand reversedList = new LinkStrand();
		Node temp = this.myFirst;
		Node First2 = null;
		Node List2 = null;
		while(temp != null) {
			StringBuilder tempSB = new StringBuilder(temp.info);
			First2 = new Node(temp.info);
			First2.info = tempSB.reverse().toString();
			First2.next = List2;
			List2 = First2;
			temp = temp.next;
		}
		reversedList.myFirst = First2;
		reversedList.mySize = this.mySize;
		reversedList.myAppends = this.myAppends;
		Node nextList = List2;
		while (List2 != null) {
			reversedList.myLast = nextList.next;
			List2 = List2.next;
		}
		return reversedList;
	}

	/**
	 * Overriden getAppendCount() method. Runs in O(1) time and returns the 
	 * instance variable myAppends, the number of appends to this LinkStrand.
	 * @return the value of myAppends
	 */
	@Override
	public int getAppendCount() {
		return myAppends;
	}

	/**
	 * Overriden charAt method. A more efficient implementation which works by
	 * saving the last called index and the node in which the sought character
	 * was found on the last call. 
	 * @param index the index at which the method should return the char
	 * @return the character at the specified index
	 */
	@Override
	public char charAt(int index) {
		if(index < 0 || index >= mySize) {
			throw new IndexOutOfBoundsException();
		}
		while(myIndex != index) {
			myIndex++;
			myLocalIndex++;
			if(myCurrent == null) {
				myCurrent = myFirst;
				myIndex = 0;
				myLocalIndex = 0;
			}

			if (myLocalIndex >= myCurrent.info.length()) {
				myLocalIndex = 0;
				myCurrent = myCurrent.next;
			}
		}
		return myCurrent.info.charAt(myLocalIndex);
	}
		

	/**
	 * Implementation of the toString method that uses a StringBuilder to 
	 * concatenate the contents of each node and returns that value as a String
	 * @return a string representation of the LinkStrand
	 */
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		Node temp = myFirst;
		while (temp != null) {
			ret.append(temp.info);
			temp = temp.next;
		}
		return ret.toString();
	}
}
