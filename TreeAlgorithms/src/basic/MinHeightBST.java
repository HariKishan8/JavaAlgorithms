package basic;

public class MinHeightBST {
	int[] arr;
	Tree tree = new Tree();
	
	
	public MinHeightBST(int[] arr) {
		this.arr = arr;
//		Tree tree = new Tree();
		int begIndex = 0;
		int endIndex = arr.length - 1;
		
		this.tree.root = buildMinHeightBST(begIndex, endIndex);

	}

	public Node buildMinHeightBST(int begIndex, int endIndex){
		
		if(begIndex > endIndex)
			return null;
		int midIndex = (endIndex + begIndex)/2;
		Node a = new Node(this.arr[midIndex]);
		
//		Instead of passing parent just assign return node to the left which is indirectly passing parent
		a.left = buildMinHeightBST(begIndex, midIndex-1);
		a.right = buildMinHeightBST(midIndex+1, endIndex);
		
		return a; 
		
//		Alternate methods
//		inserting node takes more time: O(nlogn)
//		this.tree.insertNode(this.arr[midIndex]); 
		
//		by passing parent it takes O(n)
//		if (passParent == null)
//			passParent = a;
//		else if (this.arr[midIndex] <= passParent.key)
//			passParent.left = a;
//		else
//			passParent.right = a;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {2,5,7,10,12,15,17,20,22,25,28,30,32,35,40};
		MinHeightBST obj = new MinHeightBST(a);
		obj.tree.printInorder();
		System.out.println();
	}

}
