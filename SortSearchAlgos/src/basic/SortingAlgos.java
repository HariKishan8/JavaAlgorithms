package basic;

import java.io.*;
import java.util.*;

public class SortingAlgos {

	public static void mergesort(int[] arr){
		if(arr.length>0)
			mergesort(arr, 0, arr.length -1);
		
	}	
	public static void mergesort(int[] arr, int beg , int end){
		if(beg>=end)
			return;
		
		int mid = (beg + end)/2;
		mergesort(arr, beg, mid);
		mergesort(arr, mid+1, end);
		
		merge(arr, beg, mid, end);
		
		
	}	
	public static void merge(int[] arr, int beg, int mid, int end){
		int i = beg; 
		int j = mid+1;
		int k = 0;
		int[] res = new int[end - beg + 1];
		while(i<=mid && j<=end){
			if(arr[i] <= arr[j]){
				res[k] = arr[i];
				i++;
			}
			else{
				res[k] = arr[j];
				j++;
			}
			k++;			
		}
//		copying rest of left array no need of right array
		while(i<=mid){
			res[k] = arr[i];
			i++;
			k++;
		}
		
//		copy back to arr
		k--;
		i = beg + k;
		while(k>=0){
			arr[i] = res[k];
			i--;
			k--;
		}
	}
	
//	use hash table
	public static void groupAnagrams(String[] sArr){
		Hashtable<String, LinkedList<String>> h = new  Hashtable<String, LinkedList<String>>();
		for(String s: sArr){
			String key = sortChars(s);
			if(!h.containsKey(key)){
				h.put(key, new LinkedList<String>());
			}
			LinkedList<String> anagrams = h.get(key);
			anagrams.push(s);			
		}
		
		int i = 0;
		for(String k: h.keySet()){
			LinkedList<String> l = h.get(k);
			for(String s: l){
				sArr[i++] = s;
			}
		}
		
	}	
	public static String sortChars(String s){
		char[] c = s.toCharArray();
		Arrays.sort(c);
		return new String(c);
	}
	public static boolean isAnagram(String s1, String s2){
		if(s1.length() != s2.length())
			return false;
		int[] count = new int[256];
		
		for(int i=0; i<s1.length(); i++){	
			count[(int)s1.charAt(i)]++;	
		}
		for(int i=0; i<s2.length(); i++){
			if(count[(int)s1.charAt(i)]>0){
				count[(int)s1.charAt(i)]--;	
			}
			else 
				return false;			
		}
		return true;
	}

	public static int findRotInt(int[] arr, int n, int beg, int end){
		int index;
		
		if(beg > end)
			return Integer.MIN_VALUE;
		
		int mid = (beg + end)/2;
		
		if(arr[mid] == n)
			return mid;
		if(arr[beg] < arr[mid]){
			if(n<arr[mid] && n>=arr[beg])
				index = findRotInt(arr, n, beg, mid);
			else{
				index = findRotInt(arr,n, mid+1, end);
			}
		}
		else if(arr[beg] > arr[mid]){
			if(n>arr[mid] && n<=arr[end])
				index = findRotInt(arr, n, mid+1, end);
			else{
				index = findRotInt(arr,n, beg, mid);
			}
		}
		else{
			if(arr[mid] < arr[end]){
				index = findRotInt(arr, n, mid+1, end);
			}
			else{
				index = findRotInt(arr,n, beg, mid);
				if(index == Integer.MIN_VALUE)
					index = findRotInt(arr, n, mid+1, end);
			}	
		}
		
		return index;
	}
	public static int findRotInt(int[] arr, int n){
		return findRotInt(arr, n, 0, arr.length-1);
	}
	
	public static void sortFile(File f) throws IOException{
		FileReader fr = null;
		BufferedReader bf = null;
		try {
			try {
				fr = new FileReader(f);
				bf = new BufferedReader(fr);
			} catch (Exception e) {
				
			}
			String line;
			while ((line = bf.readLine()) != null) {
				System.out.println(line);
			}
	      }finally {
	         if (bf != null) {
	            bf.close();
	         }	         
	      }
		
	}

	public static int findString(String[] arr,String s, int beg, int end){
		if(beg>end)
			return -1;
		
		int mid = (beg+end)/2;
			
//		if mid is empty string find a non empty string nearb and make it mid
		if(arr[mid].isEmpty()){
			int left = mid-1;
			int right = mid+1;
			
			while(left>=beg && right<=end){
				if(!arr[right].isEmpty()){
					mid = right;
					break;
				}				
				else if(!arr[left].isEmpty()){
					mid = left;
					break;
				}
				else{
					left--;
					right++;
				}
			}
		}
//		do binary search now
		if(arr[mid].compareTo(s) == 0)
			return mid;
		if(arr[mid].compareTo(s)<0)
			return findString(arr, s, mid+1, end);
		
		if(arr[mid].compareTo(s)>0)
			return findString(arr, s, beg, mid-1);
		
		return -1;
	}
	
	public static int[] searchMat(int[][] m, int n){
		int rows = m.length;
		int cols = m[0].length;

//		end indices
		int l = cols-1;
		
		for(int i = 0; i<=rows-1 ; i++){
	//		find the max column
			while(l>=0 && m[i][l]>n)
				l--;
			if(l>=0 && m[i][l] == n)
				return new int[]{i, l};
		}
		
//		another method
//		int i = 0;
//		while(i<=k && l>=0){
//			if(m[i][l] == n)
//				return new int[]{i, l};
//			else if(m[i][l]>n)
//				l--;
//			else
//				i++;
//			
//		}
		
//		Wrong
//		if(begi>endi || begj>endj)
//			return null;
////		Finding the middle element
//		int cols = m[0].length;
//		int k = begi*cols + begj;
//		int l= endi*cols + endj;
//		int p = (k+l)/2;
//		
//		int midi = p/cols;
//		int midj = p%cols;
//		
//		if(m[midi][midj] == n)
//			return new int[]{midi, midj};
//		
//		if(n<m[midi][midj])
//			return searchMat(m, n, begi, begj, midi, midj-1);
//		if(n>m[midi][midj])
//			return searchMat(m, n, midi, midj+1, endi, endj);
		
		return null;
	}
	
	public static ArrayList<HtWt> longestIncreasingSubSequence(ArrayList<HtWt> array){
		ArrayList<HtWt>[] solutions = new ArrayList[array.size()];
		longestIncreasingSubSequence(array, solutions, 0);
		ArrayList<HtWt> best_sequence = null;
		for(int i = 0; i<solutions.length; i++){
				best_sequence = seqWithMaxLength(best_sequence, solutions[i]);
		}
		return best_sequence;		
	}
	public static void longestIncreasingSubSequence(ArrayList<HtWt> array, ArrayList<HtWt>[] solutions, int current_index){
		if (current_index >= array.size() || current_index < 0) 
			return;
		HtWt current_element = array.get(current_index);
//		best sequence to append current element
		ArrayList<HtWt> best_sequence = null;
		for(int i = 0; i<current_index; i++){
			if(array.get(i).isBefore(current_element)){
				best_sequence = seqWithMaxLength(best_sequence, solutions[i]);
			}
		}
//		append current element
		ArrayList<HtWt> new_solution = new ArrayList<HtWt>();
		if(best_sequence != null){
			new_solution.addAll(best_sequence);
		}
		new_solution.add(current_element);
		
		solutions[current_index] = new_solution;
		
		longestIncreasingSubSequence(array, solutions, current_index+1);
		
		
	}	
	public static ArrayList<HtWt> seqWithMaxLength(ArrayList<HtWt> seq1, ArrayList<HtWt> seq2){
		if(seq1 == null)
			return seq2;
		if(seq2 == null)
			return seq1;
		return (seq1.size()>seq2.size() ? seq1 : seq2);
	}
	public static ArrayList<HtWt> getLongestIncreasingSubSequence(ArrayList<HtWt> items){		
		Collections.sort(items);
		return longestIncreasingSubSequence(items);	
	}
	
	public static void main(String[] args) throws IOException {
//		int[] arr = {1, 2, 3, 5, 7, 8, 9};
//		mergesort(arr);	
//		System.out.print(Arrays.toString(arr));
		
//		System.out.print(isAnagram("Babay", "Yabba"));	
//		String[] sArr = {"baby","rugby", "abbay", "yabba", "bygur", "abby", "babay"};	
//		groupAnagrams(sArr);
//		System.out.print(Arrays.deepToString(sArr));
		
//		int[] arr = {10,15, 20, 0,5};
//		int[] arr1 = {50, 5, 20, 30,40};
//		int[] arr2 = {2,2,2,2,2,4,5};
//		int[] arr3 = {2,4,5,2,2,2,2,};
//		System.out.println(findRotInt(arr, 5));
//		System.out.println(findRotInt(arr1, 5));
//		System.out.println(findRotInt(arr2, 5));
//		System.out.println(findRotInt(arr3, 5));
		
//	    File file = new File("C:\\Users\\Manish\\Desktop\\Hari\\tst.txt");	
//		sortFile(file);
		
//		String[] sArr = {"at","","","","ball","","","car","","","dad","",""};	
//		System.out.print(findString(sArr, "ball", 0, sArr.length-1));
		
//		int[][] m = new int[][]{
//				{1,2,3,4},
//				{5,6,7,8},
//				{9,10,11,12},
//				{13,14,15,16}
//		};
//		System.out.print(Arrays.toString(searchMat(m,8)));
		
		ArrayList<HtWt> h = new ArrayList<HtWt>();
		h.add(new HtWt(65,100));
		h.add(new HtWt(75,115));
		h.add(new HtWt(56,150));
		h.add(new HtWt(55,170));
		h.add(new HtWt(60,95));
		h.add(new HtWt(68,110));
		ArrayList<HtWt> a = getLongestIncreasingSubSequence(h);
		for(HtWt s: a){
			System.out.println(s.Ht + "," + s.Wt);
		}
		
	}

}
