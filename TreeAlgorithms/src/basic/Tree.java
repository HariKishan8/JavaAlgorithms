package basic;

import java.util.*;

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
		if (root == null)
			root = new Node(key);
		else if(key <= root.key){
			root.left = insertNode(key, root.left);
		}	
		else{
			root.right = insertNode(key, root.right);
		}
		return root;	
	}
	
	public void insertNode(int key){
		this.root = insertNode(key, this.root);	
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
	
	public static void main(String[] args) {
		Tree t = new Tree();
		
//		t.insertNode(20);
//		t.insertNode(10);
//		t.insertNode(30);
//		t.insertNode(5);
//		t.insertNode(15);
//		t.insertNode(25);
//		t.insertNode(35);
//		t.insertNode(2);
//		t.insertNode(7);
//		t.insertNode(12);
//		t.insertNode(17);
//		t.insertNode(22);
//		t.insertNode(28);
//		t.insertNode(32);
//		t.insertNode(40);
		
		t.insertNode(5);
		t.insertNode(2);
		t.insertNode(1);
		t.insertNode(3);
		t.insertNode(7);
		t.insertNode(6);
		t.insertNode(8);
		
		
//		t.printInorder();
//		System.out.println();
//		t.printPreorder();
//		System.out.println();
//		t.printPostorder();
//		System.out.println();
		
		AdditionalAlgos obj = new AdditionalAlgos(t);
//		obj.isBalanced();
//		LinkedList<Node> list = new LinkedList<Node>();
//		list.add(new Node(1));
//		list.add(new Node(2));
//		list.add(new Node(3));
//		ArrayList<LinkedList<Node>> arrList1 = new ArrayList<LinkedList<Node>>() ;
//		arrList1.add(list);
//		System.out.println(list);
		
		
		ArrayList<LinkedList<Node>> arrList = new ArrayList<LinkedList<Node>>() ;
//		LinkedList<Node> list = new LinkedList<>();
//		arrList.add(list);
//		arrList[0].add(new Node(0));
		obj.NodeListDepth(0, arrList, t.root);
	}


}
