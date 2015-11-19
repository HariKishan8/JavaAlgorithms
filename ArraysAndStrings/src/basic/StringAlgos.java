package basic;

import java.util.Arrays;

public class StringAlgos {
	
	public static boolean hasUniqueChars(String str){
		if(str.length() >256)
			return false;
		boolean arr[] = new boolean[256];
		int charAti;
		for (int i = 0; i<str.length(); i++){
			charAti = str.charAt(i);
			if(arr[charAti] == true)
				return false;
			arr[charAti] = true;
		}
		return true;
	}
	
	public static boolean hasUniqueCharsBW(String str){
		int checker = 0;
		str = str.toLowerCase().replaceAll("\\s", "");
		for(int i=0; i<str.length(); i++){
			int value = str.charAt(i) - 'a';
			if((checker & (1<<value)) > 0)
				return false;
			checker |= (1<<value);		
		}
		System.out.println("Unique !!");
		return true;
	}
	
	public static boolean isPermutation(String str1, String str2){
		if(str1.length() != str2.length())
			return false;
		char[] chrs1 = str1.toLowerCase().replaceAll("\\s", "").toCharArray();
		Arrays.sort(chrs1);
		char[] chrs2 = str2.toLowerCase().replaceAll("\\s", "").toCharArray();
		Arrays.sort(chrs2);
		if (Arrays.equals(chrs1, chrs2)){
			System.out.println("Yes");
			return true;
		}
		return false;
	}
	
	public static boolean isPermutationCount(String str1, String str2){
		if(str1.length() != str2.length())
			return false;
		char[] chrs1 = str1.toLowerCase().replaceAll("\\s", "").toCharArray();
		str2 = str2.toLowerCase().replaceAll("\\s", "");
		int[] count1 = new int[256];

//		Trying out iterable
		for (char c : chrs1){
			count1[c]++;
		}
		for(int i = 0; i<str2.length(); i++){
			if((--count1[str2.charAt(i)]) < 0)
				return false;
		}

		System.out.println("Yes");
		return true;
	}

	public static void replaceSpace(String str){
		char[] chrs = str.trim().toCharArray();
		char[] newchrs = new char[str.length()];
		int j = 0;
		
		for(char c: chrs){
			if (c == ' '){
				newchrs[j] = '%';
				newchrs[++j]='2';
				newchrs[++j]='0';
			}
			else
				newchrs[j]=c;
			j++;
		}
		System.out.println(newchrs);
	}
	
	public static char[] replaceSpace(char[] str, int length){
		int newLength;
		int spaceCount = 0;
		for(int i = 0; i<length; i++){
			if(str[i] == ' ')
				spaceCount++;
		}
		newLength = length + spaceCount*2;
		
		
		for(int i = length-1; i>=0; i--){
			if(str[i]== ' '){
				str[newLength-1] = '0';
				str[newLength-2] = '2';
				str[newLength-3] = '%';
				newLength = newLength - 3;
			}
			else{
				str[newLength-1] = str[i];
				newLength = newLength - 1;
			}
		}
		return str;
	}
	
	public static void setZero(int[][] mat){
//		no of rows
		int m = mat.length;
//		no of columns
		int n = mat[0].length;
		boolean[] row = new boolean[m];
		boolean[] column = new boolean[n];
		
		for (int i = 0; i<m; i++){
			for(int j =0; j<n; j++){
				if(mat[i][j] == 0){
					row[i] = true;
					column[j] = true;
				}
			}
		}
		for (int i =0; i<m; i++){
			for(int j =0; j<n; j++){
				if(row[i] || column[j]){
					mat[i][j] = 0;
				}
			}		
		}
	}
	
	public static String compressString(String str){
		char[] sArr = str.toCharArray();
		char[] newSArr= new char[str.length()];
		StringBuffer newStr = new StringBuffer();
		int count = 1, j= 0, i;
		for(i = 0; i<str.length(); i++){
			if((i != 0)&& (sArr[i] == sArr[i-1])){
				count++;
			}
			else{
				if(count!=1){
					newStr.append(String.valueOf(count));
					count = 1;
				}
					
				newStr.append(sArr[i]);
			}
		}
		if(i == str.length()){
			if(count != 1){
				newStr.append(String.valueOf(count));
			}
		}
		
		if(newStr.length() < str.length())
			return (newStr.toString());
		else
			return str;
		
		
	}
	
	public static void main(String[] args) {
		String str1 = "I am wrong    ";
//		if(hasUniqueCharsBW(str))
//			System.out.println("Unique !!");
//		String str2 = "Am I wrong";
//		isPermutationCount(str1, str2);
		
//		System.out.println(replaceSpace(str1.toCharArray(), str1.length()-4));
		
//		int[][] mat = {
//				{1, 2, 3, 4},
//				{10, 0, 12, 13},
//				{20, 21, 22, 23}				
//		};
//		setZero(mat);
//		System.out.print(Arrays.deepToString(mat));
		
		System.out.println(compressString("aabbcc"));
		
	}
	

}
