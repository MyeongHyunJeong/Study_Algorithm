import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main9466_2 {
	
	public static int testCase;
	public static int n;
	public static int[] map;
	public static boolean[] done;
	public static boolean[] resultChk;
	public static int result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input9466.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		testCase = Integer.parseInt(br.readLine());
		for(int t=0; t<testCase; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				map[i] = Integer.parseInt(st.nextToken())-1;
			}
//			System.out.println(Arrays.toString(map));
			
			done = new boolean[n];
			resultChk = new boolean[n];
			result = 0;
			for(int i=0; i<n; i++) {
				if(!done[i]) {
					getResult(i);
				}
//				System.out.println("DONE");
//				System.out.println(Arrays.toString(done));
			}
//			System.out.println(Arrays.toString(resultChk));
			
			System.out.println(n-result);
		}
		
		br.close();
		
	}
	
	public static void getResult(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		done[start] = true;
		
		int poll, next;
		while(!q.isEmpty()) {
			poll = q.poll();
			next = map[poll];
			if(!done[next]) {
				q.add(next);
				done[next] = true;
			}
			else {
				if(resultChk[next]) continue;
				for(int i=next; !resultChk[i]; i=map[i]) {
					resultChk[i] = true;
					result++;
				}
//				System.out.println("CHK");
//				System.out.println(Arrays.toString(resultChk));
			}
		}
		for(int i=start; !resultChk[i]; i=map[i]) resultChk[i] = true;
	}

}
