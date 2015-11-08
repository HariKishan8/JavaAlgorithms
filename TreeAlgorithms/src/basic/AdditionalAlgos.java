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
	

	
}
