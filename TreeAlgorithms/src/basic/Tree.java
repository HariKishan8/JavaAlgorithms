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
	
	public void insertNode(Node a){
		if (this.root == null){
			this.root = a;
			return;
		}
		insertNode(a, this.root);	
	}
	
	public void deleteNode(Node b){
		
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
		t.insertNode(new Node(8));
		t.insertNode(new Node(10));
		t.insertNode(new Node(1));
		t.insertNode(new Node(2));
		t.printTree();
		
	}


}
