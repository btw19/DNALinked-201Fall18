
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
	
	@Override
	public long size() {
		return mySize;
	}

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

	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		Node dnaNode = new Node(dna);
		myLast.next = dnaNode;
		myLast = myLast.next;
		
		mySize += dna.length();
		myAppends++;
		return this;
	}

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

	@Override
	public int getAppendCount() {
		return myAppends;
	}

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
