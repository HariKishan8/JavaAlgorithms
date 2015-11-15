package basic;

//import java.util.*;

public class Tree {
	Node root;
	
	public Tree() {	
		this.root = null;
	}
	
	
	public boolean isEmpty(){
		return (this.root == null);	
	}
	
	private Node insertNode(int key, Node root){
//		Assuming it's a Binary Search Tree
		if (root == null){
			root = new Node(key);
		}
		else if(key <= root.key){
			root.left = insertNode(key, root.left);
			root.left.parent = root;
		}	
		else{
			root.right = insertNode(key, root.right);
			root.right.parent = root;
		}
		return root;	
	}
	
	public void insertNode(int key){
		this.root = insertNode(key, this.root);	
	}
	
	
	public void deleteNode(int key){
		Node a = searchNode(key);
		Node b = replacementNode(a);
		
		Node c = findParentNode(b);
		a.key = b.key;
		if (c == null)
			b = null;
		if(c.left.key == b.key)
			c.left = null;
		else
			c.right = null;
	}
	
	
	public Node findParentNode(Node a){
		return findParentNode(a.key, this.root);
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
	
	
	public Node replacementNode(Node a){
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
	
	
	public Node inorderSuccessor(int key){
		Node a = searchNode(key);
		
		if (a.right != null){
//			get the Biggest element in the right subtree
			Node b = Smallest(a.right);
			return b;
		}
//		no right subtree so track back: find the  first left subtree (from bottom) it's part of
		Node leftParent = firstLeftParent(null, a, this.root);
		return leftParent;
	}
		
	public Node Smallest(Node a){
		if(a == null)
			return null;
		while(a.left != null)
			a = a.left;	
		return a;	 
	}
	
	public Node Biggest(Node a){
		if(a == null)
			return null;
		while(a.right != null)
			a = a.right;	
		return a;	
	}
	
	
	public Node firstLeftParent(Node leftParent, Node node, Node currNode){
		if(node == currNode){
			return leftParent;
		}
		
		if(node.key <= currNode.key){
			leftParent = currNode;
			leftParent = firstLeftParent(leftParent, node, currNode.left);
		}
		else 
		{
			leftParent = firstLeftParent(leftParent, node, currNode.right);
		}
		
			 
		return leftParent;
	}
	
	
	private void preorderPrintTree(Node a){
		if (a == null)
			return;
		
		System.out.print(a.key);
		System.out.println();
		preorderPrintTree(a.left);		
		preorderPrintTree(a.right);
	}
	
	private void postorderPrintTree(Node a){
		if (a == null)
			return;

		postorderPrintTree(a.left);		
		postorderPrintTree(a.right);
		System.out.print(a.key);
		System.out.println();
	}
	
	private void inorderPrintTree(Node a){
		if (a == null)
			return;
		
		inorderPrintTree(a.left);
		System.out.print(a.key);
		System.out.println();
		inorderPrintTree(a.right);
			
	}
	
	public void printInorder(){
//		Inorder traversal print
		if (this.root == null){
			System.out.println("Empty tree");
			return;
		}
		inorderPrintTree(this.root);
		
	}
	
	public void printPreorder(){
//		Preorder traversal print
		if (this.root == null){
			System.out.println("Empty tree");
			return;
		}
		preorderPrintTree(this.root);
		
	}
	
	public void printPostorder(){
//		Postorder traversal print
		if (this.root == null){
			System.out.println("Empty tree");
			return;
		}
		postorderPrintTree(this.root);
		
	}
	
	
	public static boolean containsTree(Node r1, Node r2){
		if(r2 == null)
			return true;
		
		return subtree(r1, r2);
	}
	
	public static boolean subtree(Node r1, Node r2){
		if(r1 == null)
			return false;
		if(r1.key == r2.key)
			return matchTree(r1, r2);
		
//		if(!subtree(r1.left, r2))
//			return subtree(r1.right, r2);	
//		return true;	
		
		return (subtree(r1.left, r2) || subtree(r1.right, r2));
	}
	
	public static boolean matchTree(Node r1, Node r2){	
		if(r2 == null && r1 == null)
			return true;
//		if cut the node at the T2 root in T1, both should be same, so, both should be null at the same time
		if (r1 == null || r2 == null) 
			return false;
		
		if(r1.key != r2.key)
			return false;
			
		return (matchTree(r1.left, r2.left) && matchTree(r1.right, r2.right));		
	}

	
	public int depth(Node node){
		if (node == null)
			return 0;
		return (1 + Math.max(depth(node.left), depth(node.right)));
	}
	
	public void printPath(int[] path, int start, int end){
		for (int i = start; i<=end; i++){
			System.out.print(path[i] + " ");
		}
		System.out.println();
	}
	
	public void pathOfSum(Node node, int sum){
		int depth = this.depth(this.root);
		int[] path = new int[depth];
		this.pathOfSum(node, sum, path, 0);
	}
	
	public void pathOfSum(Node node, int sum, int[] path, int level){
		
		int i ;
		int tempSum = 0;
		
		if(node == null)
			return;
		
		path[level] = node.key;
		for(i= level; i>=0; i-- ){
			tempSum += path[i];
			if(tempSum == sum){
				printPath(path, i, level);
			}
		}
		
		pathOfSum(node.left, sum, path, level+1);
		pathOfSum(node.right, sum, path, level+1);
//		freeing the space
		path[level] =Integer.MIN_VALUE;
	}
	
	
	public static void main(String[] args) {
		Tree t1 = new Tree();
		Tree t2 = new Tree();
		Tree t3 = new Tree();
		Tree t4 = new Tree();
		
		t1.insertNode(20);
		t1.insertNode(10);
		t1.insertNode(30);
		t1.insertNode(5);
		t1.insertNode(15);
		t1.insertNode(25);
		t1.insertNode(35);
		t1.insertNode(2);
		t1.insertNode(7);
		t1.insertNode(12);
		t1.insertNode(17);
		t1.insertNode(22);
		t1.insertNode(28);
		t1.insertNode(32);
		t1.insertNode(40);
		
		t2.insertNode(5);
		t2.insertNode(2);
		t2.insertNode(1);
		t2.insertNode(3);
		t2.insertNode(7);
		t2.insertNode(6);
		t2.insertNode(8);
		
		t3.insertNode(30);
		t3.insertNode(25);
		t3.insertNode(35);
		t3.insertNode(22);
		t3.insertNode(28);
		t3.insertNode(32);
		t3.insertNode(40);
		
		t4.insertNode(8);
		t4.insertNode(6);
		t4.insertNode(10);
		t4.insertNode(2);
		t4.insertNode(7);
		t4.insertNode(9);
		t4.insertNode(11);
		
		
//		t1.printInorder();
//		System.out.println();
//		t.printPreorder();
//		System.out.println();
//		t.printPostorder();
//		System.out.println();
		
//		AdditionalAlgos obj = new AdditionalAlgos(t1);
//		obj.isBalanced();
//		LinkedList<Node> list = new LinkedList<Node>();
//		list.add(new Node(1));
//		list.add(new Node(2));
//		list.add(new Node(3));
//		ArrayList<LinkedList<Node>> arrList1 = new ArrayList<LinkedList<Node>>() ;
//		arrList1.add(list);
//		System.out.println(list);
		
		
//		ArrayList<LinkedList<Node>> arrList = new ArrayList<LinkedList<Node>>() ;
//		LinkedList<Node> list = new LinkedList<>();
//		arrList.add(list);
//		arrList[0].add(new Node(0));
//		obj.NodeListDepth(0, arrList, t.root);
		
//		if(obj.isBST(t.root))
//			System.out.println("Tree is BST");
//		else
//			System.out.println("Tree is not BST");
		
//		Node successor = t.inorderSuccessor(40);
//		if(successor != null)
//			System.out.println(successor.key);
//		else
//			System.out.println("No successor");
		
//		common ancestor
		
//		Node cmnAncestor = obj.firstCmnAncestor(17, 5);
//		System.out.println(cmnAncestor.key);
		
//		if(containsTree(t1.root, t2.root))
//			System.out.println("T3 is in T1");
//		else
//			System.out.println("T3 is not in T1");
		
		t4.pathOfSum(t4.root, 15);
		
	}
	



}
