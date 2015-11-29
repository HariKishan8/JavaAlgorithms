package basic;

public class RankTree {
	private RankNode root = null;
	public void track(int number){
		if(root == null)
			root = new RankNode(number);
		else
			root.insert(number);
	}
	
	public int getRankOfNumber(int number){
		return root.getRank(number);
	}
	
	public static void main(String[] args) {
		RankTree t = new RankTree();
		t.track(20);
		t.track(15);
		t.track(25);
		t.track(12);
		t.track(17);
		t.track(23);
		t.track(27);
		
		System.out.print(t.getRankOfNumber(27));
		
	}

}
