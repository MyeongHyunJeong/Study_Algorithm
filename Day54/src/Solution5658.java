import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution5658 {
	
	public static int testCase;
	public static int n;
	public static int k;
	public static int m;
	public static String num;
	public static String copy;
	public static int hex;
	public static ArrayList<Integer> arr;
	public static String chk;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input5658.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		testCase = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=testCase; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			m = n/4;
			num = br.readLine();
//			System.out.println(n + " " + k + " " + m);
//			System.out.println(num);

			copy = num;
			chk = "";
			arr = new ArrayList<Integer>();
			for(int i=0; i<m; i++) {
				for(int j=0; j<n; j+=m) {
//					System.out.println(num.substring(j,j+m));
					hex = Integer.parseInt(num.substring(j,j+m), 16);
//					System.out.println(hex);
//					System.out.println();
					if(!chk.contains(""+hex)) {
						arr.add(hex);
						chk += hex + " ";
					}
				}
				num = copy.substring(n-1) + copy.substring(0,n-1);
				copy = num;
			}
			
			arr.sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o1 < o2 ? 1 : -1;
				}
			});
			
			System.out.println("#" + t + " " + arr.get(k-1));
			
		}
	}

}
