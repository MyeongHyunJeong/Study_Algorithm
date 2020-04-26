import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2660 {

	public static int n;
	public static int[][] map;
	public static int min = Integer.MAX_VALUE;
	public static int[] relation;
	public static int result;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input2660.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		int a,b;
		while(true) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if(a==-1 && b==-1) break;
			
			map[a-1][b-1] = 1;
			map[b-1][a-1] = 1;
		}
		
		relation = new int[n];
		result = 0;
		for(int i=0; i<n; i++) {
			countRelation(i);
		}
		
//		System.out.println(Arrays.toString(relation));
		System.out.println(min + " " + result);
		for(int i=0; i<n; i++) {
			if(relation[i]==min) {
				System.out.print((i+1) + " ");
			}
		}
		
		br.close();
	}

	public static void countRelation(int a) {
		boolean[] chk = new boolean[n];
		boolean flag = false;
		int count = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(a);
		chk[a] = true;
		
		int poll;
		while(!q.isEmpty()) {
			poll = q.poll();
			for(int i=0; i<n; i++) {
				if(chk[i]) continue;
				if(map[poll][i]==1 && !chk[i]) {
					chk[i] = true;
					flag = true;
					q.add(i);
				}
			}
			
			if(flag) count++;
			flag = false;
		}
		
		if(count<min) {
			min = count;
			result = 1;
		}else if(count==min) {
			result++;
		}
		
		relation[a] = count;
	}
	
}
