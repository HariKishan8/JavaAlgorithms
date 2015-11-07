package basic;

public class Tree {
	Node root;
	
	public Tree() {	
		this.root = null;
	}
	
	private void insertNode(Node a, Node root){
//		Assuming it's a Binary Search Tree
		if(a.key <= root.key)
			if(root.left == null)
				root.left = a;
			else
				insertNode(a, root.left);
		else
			if(root.right == null)
				root.right = a;
			else
				insertNode(a, root.right);
	}
	
	public void insertNode(int key){
		Node a = new Node(key);
		if (this.root == null){
			this.root = a;
			return;
		}
		insertNode(a, this.root);	
	}
	
	public void deleteNode(int key){
		Node a = searchNode(key);
		Node b = immSuccessor(a);
		
		Node c = findParentNode(b.key, this.root);
		a.key = b.key;
		if (c == null)
			b = null;
		if(c.left.key == b.key)
			c.left = null;
		else
			c.right = null;
	}
	
	public Node findParentNode(int key, Node root){
		Node b;
		if(key == root.key)
			return null;
		if((root.left == null) && (root.right == null))
			return null;
		if ((root.left.key == key) || (root.right.key == key))
			return root;
		
		if(key < root.key)
			if(root.left == null)
				b = null;
			else
				b = findParentNode(key, root.left);
		else
			if(root.right == null)
				b = null;
			else
				b = findParentNode(key, root.right);
		return b;
	}
	
	private Node searchNode(int key, Node root){
		Node b;
		if(key == root.key)
			b = root;
		else if(key < root.key)
			if(root.left == null)
				b = null;
			else
				b = searchNode(key, root.left);
		else
			if(root.right == null)
				b = null;
			else
				b = searchNode(key, root.right);
		return b;
	}
	
	public Node searchNode(int key){
		Node b = searchNode(key, this.root);
		return b;
	}
	
	public Node immSuccessor(Node a){
//		replace with the biggest element in its left subtree or smallest element in its right subtree
		if(a.left == null){
//			no left subtree
			if (a.right == null){
//				no left and right subtree, so no successor
				return a;
			}
//			replace with the smallest element in the right subtree
			Node b = Smallest(a.right);
			return b;
		}
//		replace with the Biggest element in the left subtree
		Node b = Biggest(a.left);
		return b;	
	}
	
	public Node Smallest(Node a){
		if(a.left == null)
			return a;
		Node b;
		b = Smallest(a.left);
		return b;	 
	}
	
	public Node Biggest(Node a){
		if(a.right == null)
			return a;
		Node b;
		b = Biggest(a.right);
		return b;
	}
	
	
	private void inorderPrintTree(Node a){
		if (a == null)
			return;
		
		inorderPrintTree(a.left);
		System.out.print(a.key);
		System.out.println();
		inorderPrintTree(a.right);
			
	}
	
	public void printTree(){
//		Inorder traversal print
		if (this.root == null){
			System.out.println("Empty tree");
			return;
		}
		inorderPrintTree(this.root);
		
	}
	
	public static void main(String[] args) {
		Tree t = new Tree();
		
		t.insertNode(20);
		t.insertNode(10);
		t.insertNode(30);
		t.insertNode(5);
		t.insertNode(15);
		t.insertNode(25);
		t.insertNode(35);
		t.insertNode(2);
		t.insertNode(7);
		t.insertNode(12);
		t.insertNode(17);
		t.insertNode(22);
		t.insertNode(28);
		t.insertNode(32);
		t.insertNode(40);
		
		t.printTree();
		System.out.println();
		t.deleteNode(40);
		t.printTree();
	}


}
