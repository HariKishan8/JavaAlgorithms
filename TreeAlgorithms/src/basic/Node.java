package basic;

public class Node {
	int key;
	Node left;
	Node right;
	Node parent;
	boolean visited;
	public Node(int key) {
//		super();
		this.key = key;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.visited = false;
	}
}
