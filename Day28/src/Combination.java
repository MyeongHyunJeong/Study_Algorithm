import java.util.Arrays;

public class Combination {

	public static int[] temp;
	public static int count;
	
	public static void main(String[] args) {
		System.out.println("안녕하세요");
		temp = new int[3];
		count = 0;
		combi(7,3,0,0);
		System.out.println(count);
	}
	
	public static void combi(int n, int r, int idx, int depth) {
		if(idx==r) {
			System.out.println(Arrays.toString(temp));
			count++;
			return;
		}
		if(depth==n) return;
		temp[idx] = depth;
		combi(n,r,idx+1, depth);	//조합 구할 때 추석
//		combi(n,r,idx+1,depth+1);	//중복조합 구할 때 주석
		combi(n,r,idx,depth+1);
	}

}