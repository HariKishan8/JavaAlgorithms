package basic;

public class RankNode {
	public int data = 0;
	public RankNode left, right;
	public int left_size =0;
	public RankNode(int d){
		data = d;
	}
	
	public void insert(int d){
		if(d<=this.data){
			if(this.left != null)
				this.left.insert(d);
			else
				this.left = new RankNode(d);
			this.left_size++;
		}
		else{
			if(this.right != null)
				this.right.insert(d);
			else
				this.right = new RankNode(d);				
		}
	}
	
	public int getRank(int d){
		if(d == this.data)
			return this.left_size;
		else if(d<this.data){
			if(this.left == null)
				return -1;
			else
				return this.left.getRank(d);
		}
		else{
			if(this.right == null)
				return -1;
			else
				return (this.left_size + 1 + this.right.getRank(d));
		}
	}
}
