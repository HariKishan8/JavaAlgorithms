package basic;

import java.util.*;

public class RecursionDPAlgos {

	public static class Point{
		int x = 0;
		int y = 0;
		public Point(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	
	
//	can take 1, 2, or 3 steps
	public static int countWays(int n, int[] ways){
		if(n<0)
			return 0;
		if(n==0)
			return 1;
		if(ways[n] > 0)
			return ways[n];
		ways[n] = countWays(n-1, ways) + countWays(n-2, ways) + countWays(n-3, ways);
		return ways[n];
	}
	public static int countWays(int n){
		int[] ways = new int[n+1];
		return countWays(n, ways);
	}
	
	public static int countWaysGrid(int x, int y, int[][] ways){
		if(x<0 || y<0)
			return 0;
		if(x==0 && y==0)
			return 1;
		if(ways[x][y] > 0)
			return ways[x][y];
		ways[x][y] = countWaysGrid(x-1, y, ways) + countWaysGrid(x, y-1, ways);
		return ways[x][y];
		
	}
	public static int countWaysGrid(int x, int y){
		int[][] ways = new int[x+1][y+1];
		return countWaysGrid(x, y, ways);
	}
	
	public static boolean findPathGrid(int x, int y, ArrayList<Point> path, Hashtable<Point, Boolean> cache){
		if(x<0 || y<0)
			return false;
		Point p = new Point(x,y);
		boolean success = false;
		if(cache.containsKey(p)){
			return cache.get(p);
		}
		if(x==0 && y==0){
//			path.add(p);
//			cache.put(p, true);
			success = true;
		}
		if(x>=1 && isFree(x-1,y)){
			success = findPathGrid(x-1, y, path, cache);
		}
		if(y>=1 && !success && isFree(x, y-1)){
			success = findPathGrid(x, y-1, path, cache);
		}
			
		if(success)
			path.add(p);
		cache.put(p, success);
		return success;
		
	}
	public static boolean isFree(int x, int y){
		if(x==0 && y==1)
			return false;
		return true;
	}
	
	public static ArrayList<ArrayList<Integer>> findSubsets(ArrayList<Integer> set){
		ArrayList<ArrayList<Integer>> subsets = new ArrayList<ArrayList<Integer>>();
//		compute 2^n (number of subsets)
		int max= 1<<set.size();
		for(int k = 0; k<max; k++){
			subsets.add(convertIntToSubset(set, k));
		}
		return subsets;
	}
	public static ArrayList<Integer> convertIntToSubset(ArrayList<Integer> set, int num){
		ArrayList<Integer> subset = new ArrayList<Integer>();
		int index = 0;
		for(int i = num; i>0; i = i>>1){
//			last digit is 1 or 0, accordingly add that element to subset
			if((i & 1) == 1)
				subset.add(set.get(index));
			index++;
		}
		return subset;
	}
	
	
	
	
	public static void main(String[] args) {
//		System.out.print(countWays(3));
		
//		System.out.print(countWaysGrid(1, 2));
//		ArrayList<Point> path = new ArrayList<Point>();
//		if(findPathGrid(1, 1, path, new Hashtable<Point, Boolean>())){
//			for(Point p: path){
//				System.out.println(p.x + "," + p.y);
//			}
//		}
			
		ArrayList<Integer> set = new ArrayList<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);
		ArrayList<ArrayList<Integer>> subsets = findSubsets(set);
		System.out.println(subsets);

	}

}
