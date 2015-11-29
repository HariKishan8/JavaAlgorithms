package basic;

public class HtWt implements Comparable {
	int Ht;
	int Wt;
	
	public HtWt(int Ht, int Wt){
		this.Ht = Ht;
		this.Wt = Wt;
	}
	
	public int compareTo(Object s){
//		type cast
		HtWt second = (HtWt) s;
		if(this.Ht == second.Ht)
			return ((Integer)this.Wt).compareTo(second.Wt);
		else
			return ((Integer)this.Ht).compareTo(second.Ht);
	}
	
	public boolean isBefore(HtWt s){
		if(this.Ht < s.Ht && this.Wt < s.Wt)
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) {
		

	}

}
