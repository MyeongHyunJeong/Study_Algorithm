import java.util.ArrayList;
import java.util.Arrays;

public class Solution03 {
	
	public static int[] temp;
	public static ArrayList<Integer> list;
	public static char[] str;
	public static int result;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution("001100", 5));
	}

	public static int solution(String road, int n) {
		int answer = -1;
		str = road.toCharArray();
		list = new ArrayList<Integer>();
		for(int i=0; i<str.length; i++) {
			if(str[i]=='0') list.add(i);
		}

		result = Integer.MIN_VALUE;
		if(list.size()<n) {
			temp = new int[list.size()];
			combination(list.size(), list.size(), 0, 0);
		}
		else {
			temp = new int[n];
			combination(list.size(), n, 0, 0);
		}
		answer = result;


		return answer;
	}

	public static void combination(int n, int r, int idx, int depth) {
		if(idx==r) {
			int count = 0;
			int save = Integer.MIN_VALUE;
			for(int i=0; i<r; i++) {
				str[list.get(temp[i])] = '1';
			}
			for(int i=0; i<str.length; i++) {
				if(str[i]=='1') {
					count++;
				}else {
					if(save<count) save = count;
					count = 0;
				}
			}
			for(int i=0; i<r; i++) {
				str[list.get(temp[i])] = '0';
			}
			
			if(count>0 && save==Integer.MIN_VALUE) save = count;
			
			if(save>result)	result = save;
			
			return;
		}
		if(depth==n) return;
		temp[idx] = depth;
		combination(n,r,idx+1,depth+1);	//중복조합 구할 때 주석
		combination(n,r,idx,depth+1);
	}

}
