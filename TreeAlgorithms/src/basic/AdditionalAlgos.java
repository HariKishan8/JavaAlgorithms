package basic;


import java.util.*;

public class AdditionalAlgos {
	Tree tree;

	public AdditionalAlgos(Tree tree) {
		this.tree = tree;
	}

	public boolean isBalanced(){
		if(this.tree.isEmpty()){
			System.out.println("Empty Tree");
			return true;
		}
		
		if(isBalanced(this.tree.root) == -1){
			System.out.println("Not balanced Tree");
			return false;
		}
		else 
		{
			System.out.println("Balanced Tree");
			return true;
		}

	}
	
//	Function to get the height at a particular node
	private int isBalanced(Node a){
		int heightLeftTree, heightRightTree;
		if(a == null){
			return 0;
		}
		
		heightLeftTree =  1+ isBalanced(a.left);
		heightRightTree =  1+ isBalanced(a.right);
		
		if (Math.abs(heightLeftTree - heightRightTree) > 1)
			return -1;
		
		return Math.max(heightLeftTree, heightRightTree);
	}

	public void NodeListDepth(int depth, ArrayList<LinkedList<Node>> arrList, Node currNode){
		if(currNode == null)
			return;
		
		if(arrList.size() < depth+1){
			LinkedList<Node> listAtDepth =  new LinkedList<Node>();
			listAtDepth.add(currNode);
			arrList.add(depth, listAtDepth);	
		}
		else
		{
//			Add node to the list whose index is its depth
			LinkedList<Node> listAtDepth = arrList.get(depth);	
			listAtDepth.add(currNode);
		}

		NodeListDepth(depth+1, arrList, currNode.left);
		NodeListDepth(depth+1, arrList, currNode.right);
	
		return;
	}
	
	public boolean isBST(Node n){
		return isBST(n, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	public boolean isBST(Node n, int min, int max){
		if(n == null)
			return true;
		
		if(n.key <= min && n.key > max)
			return false;
		
		if((!isBST(n.left, min, n.key)) || (!isBST(n.right, n.key, max)))
				return false;
		
		return true;
	}
	
	public Node firstCmnAncestor(int key1, int key2){
		Node a = this.tree.searchNode(key1);
		Node b = this.tree.searchNode(key2);
		return firstCmnAncestor(a, b);
	}
	
	public Node firstCmnAncestor(Node a, Node b){
//		if the elements are on either side of the root - this only works in BST
//		for general solution I'm commenting it out
//		if (((a.key <= this.tree.root.key)  && (this.tree.root.key < b.key))|| 
//			((b.key <= this.tree.root.key)  && (this.tree.root.key < a.key))){
//			return this.tree.root;   
//		   }
//		they are on same side
//		start from node a and track back to root marking nodes n path to be visited
		
		while(a != this.tree.root){
			a.visited = true;
			a = a.parent;
		}
//		even marking root to be visited
		a.visited = true;
//		completed tracking back form node a
//		now start tracking from node b and check if it's already visited
		while(!b.visited){
			b = b.parent;
		}
//		loop stops where it is already visited which is the first common ancestor
		return b;
	}
	
//	Failed Attempt !!!
//	public Node isBST(Node node, Node lastNode){
//		if(node == null)
//			return null;
//		if(node.left != null){
//			lastNode = isBST(node.left, lastNode);
//			if(lastNode == null){
////				if it's just the root in the tree.	
//				if(node == this.tree.root)
//					return node;
//				return null;
//			}
//					
//		}
//
//		if(node.key < lastNode.key){
//			System.out.println("Not BST");
//			return null;
//		}
//		lastNode = node;
//		if(node.right != null){
//			lastNode = isBST(node.right, lastNode);
//			if(lastNode == null)
//				return null;
//			if(node.key >= lastNode.key){
//				System.out.println("Not BST");
//				return null;
//			}
//				
//		}
//		
//		return lastNode;
//	}
	
 
	
}
