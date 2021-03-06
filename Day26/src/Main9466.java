import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main9466 {

	public static int testCase;
	public static int n;
	public static int[] map;
	public static boolean[] chk;
	public static boolean[] rchk;
	public static boolean[] enter;
	public static int student;
	public static boolean flag;
	public static int result;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input9466.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		 testCase = Integer.parseInt(br.readLine());
		 for(int t=0; t<testCase; t++) {
			 result = 0;
			 n = Integer.parseInt(br.readLine());
			 rchk = new boolean[n];
			 enter = new boolean[n];
			 map = new int[n];
			 st = new StringTokenizer(br.readLine());
			 for(int i=0; i<n; i++) {
				 map[i] = Integer.parseInt(st.nextToken())-1;
			 }
			 
			 
			 for(int i=0; i<n; i++) {
				 if(!enter[i]) {
					 chk = new boolean[n];
					 flag = false;
					 student = i;
					 makeGroup(i,0);
					 for(int j=0; j<n; j++) {
						 if(chk[j] && !enter[j]) {
							 enter[j] = true;
						 }
					 }
				 }
			 }
			 
//			 System.out.println(Arrays.toString(rchk));
			 for(int i=0; i<n; i++) {
				 if(!rchk[i]) {
					 result++;
				 }
			 }
			 System.out.println(result);
			 
		 }
		
		br.close();
	}
	
	public static void makeGroup(int idx, int depth) {
		if(student==map[idx] && depth==0) {
			rchk[idx] = true;
			return;
		}
		if(depth>n) {
			return;
		}
		if(enter[idx]) {
			return;
		}
		if(chk[idx]) {
			student = idx;
			flag = true;
			return;
		}
		chk[idx] = true;
		makeGroup(map[idx], depth+1);
		if(flag) {
			rchk[idx] = true;
			if(idx==student) flag = false;
		}
		
	}
	

}
