
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
		myFirst.next = myLast;
		myAppends = 0;
		mySize = source.length();
		myCurrent = new Node("");
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
		myLast = dnaNode;
		myFirst.next = myLast;
		
		mySize += dna.length();
		myAppends++;
		return this;
	}

	@Override //creates a new object, NOT a mutator
	public IDnaStrand reverse() {
		LinkStrand reversed;
		Node temp = this.myFirst;
		StringBuilder backwards = new StringBuilder();
		while(temp != null) {
			StringBuilder tempSB = new StringBuilder(temp.info);
			tempSB.reverse();
			backwards.append(tempSB);
			temp = temp.next;
		}
		reversed = new LinkStrand(backwards.toString());
		return reversed;
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}

	@Override
	public char charAt(int index) {
		if(index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		
		if(!(index > myIndex)) {
			Node list = myFirst;
		
			while(myLocalIndex < index) {
				this.myLocalIndex++;
				if(myLocalIndex >= list.info.length()) {
					list = list.next;
				}
			}
		
			myCurrent = list;
			this.myIndex = index;
		}
		
		else {
			
		}
		
		return myCurrent.info.charAt(index);
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
