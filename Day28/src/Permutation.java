import java.util.Arrays;

public class Permutation {

	public static int[] temp;
	public static int count;

	public static void main(String[] args) {
		System.out.println("순열입니다");

		temp = new int[3];
		count = 0;
		permutation(7,3,0,new boolean[7]);
		System.out.println(count);
	}

	public static void permutation(int n, int r, int idx, boolean[] chk) {
		if(idx==r) {
			System.out.println(Arrays.toString(temp));
			count++;
			return;
		}
				for(int i=0; i<n; i++) {	//	순열
					if(!chk[i]) {
						chk[i] = true;
						temp[idx] = i;
						permutation(n,r,idx+1,chk);
					}
				}
//		for(int i=0; i<n; i++) {	//	중복순열
//			temp[idx] = i;
//			permutation(n,r,idx+1,chk);
//		}
	}

}
