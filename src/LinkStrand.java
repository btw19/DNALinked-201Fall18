
/**
 * LinkStrand class for the DNALinked project. This class uses a linked list
 * and nodes to represent the DNA rather than a string or string builder, 
 * making it more efficient. It also implements the IDnaStrand interface.
 * @author Virginia Capehart and Ben Williams
 * @date due 10/25/18
 */

public class LinkStrand implements IDnaStrand{
	
	private class Node {
		String info;
		Node next;
		
		public Node(String s) {
			info = s;
			next = null;
		}
	}
		   
	private Node myFirst,myLast;
	private long mySize;
	private int myAppends;
	
	public LinkStrand() {
		this("");
	}
	
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
	}

	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		StringBuilder newFirst = new StringBuilder(myFirst.info);
		newFirst.append(myLast.info);
		this.myFirst.info = newFirst.toString();
		myLast = new Node(dna);
		mySize += dna.length();
		myAppends++;
		return this;
	}

	@Override
	public IDnaStrand reverse() {
		return this;
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}

	@Override
	public char charAt(int index) {
		return 0;
	}

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
