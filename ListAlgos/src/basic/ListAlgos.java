package basic;
import java.util.*;
public class ListAlgos {

	
	public static void removeDuplicates(LinkedList<Integer> l){
		Hashtable<Integer, Integer> count = new Hashtable<Integer, Integer>();
		
		for(int i = 0; i<l.size(); i++){
			if(count.containsKey(l.get(i))){
				l.remove(i);
				continue;
			}
			count.put(l.get(i), i);
		}
		
	}
	
	
	public static void main(String[] args) {
//		LinkedList<Integer> l = new LinkedList<Integer>();
//		l.add(3);
//		l.add(4);
//		l.add(3);
//		l.add(5);
//		l.add(1);
//		l.add(4);
//		
//		removeDuplicates(l);
//		System.out.println(l);
		
		
	}
}
