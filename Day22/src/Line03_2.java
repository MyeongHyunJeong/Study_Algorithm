import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Line03_2 {
	
	public static int n;
	public static ArrayList<Integer>[] relation;
	public static boolean[] chk;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input03.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		relation = new ArrayList[1000001];
		
		int a,b;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			relation[a] = new ArrayList<Integer>();
			relation[b] = new ArrayList<Integer>();
			relation[a].add(b);
		}
		
		chk = new boolean[1000001];
		
		
		br.close();
	}

}
