//Comment
//test
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

	private Node myInfo;
	
	public LinkStrand() {
		this("");
	}
	
	public LinkStrand(String s) {
		initialize(s);
	}
	
	@Override
	public long size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initialize(String source) {
		myInfo = new Node(source);
		myAppends = 0;
	}

	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	@Override
	public IDnaStrand append(String dna) {
		//myFirst = myInfo;
		myInfo = new Node(dna);
		//myLast = myInfo;
		mySize += dna.length();
		myAppends++;
		return this;
	}

	@Override
	public IDnaStrand reverse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}

	@Override
	public char charAt(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		Node temp = myFirst;
		StringBuilder ret = new StringBuilder();
		while (temp != null && temp.next != null) {
			ret.append(temp.info);
			temp = temp.next;
		}
		return ret.toString();
	}
}
