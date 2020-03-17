import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2644 {
	
	public static int n;
	public static int a;
	public static int b;
	public static int m;
	public static int[][] map;
	public static boolean[] chk;
	public static int result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input2644.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken())-1;
		b = Integer.parseInt(st.nextToken())-1;
		m = Integer.parseInt(br.readLine());
		map = new int[n][n];
		int tempA, tempB;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			tempA = Integer.parseInt(st.nextToken())-1;
			tempB = Integer.parseInt(st.nextToken())-1;
			map[tempA][tempB] = 1;
			map[tempB][tempA] = 1;
		}
//		printMap();
		
		result = -1;
		chk = new boolean[n];
		getChonsu();
		
		System.out.println(result);
		
		br.close();
	}
	
	public static void getChonsu() {
		Queue<Person> q = new LinkedList<Person>();
		q.add(new Person(a,0));
		chk[a] = true;
		
		Person poll;
		int name, chonsu;
		while(!q.isEmpty()) {
			poll = q.poll();
			name = poll.name;
			chonsu = poll.calChonsu;
			if(name==b) {
				result = chonsu;
				break;
			}
			
			for(int i=0; i<n; i++) {
				if(map[name][i]==1 && !chk[i]) {
					q.add(new Person(i, chonsu+1));
					chk[i] = true;
				}
			}
		}
	}

	public static void printMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(map[i][j] + "\t");
			}System.out.println();
		}System.out.println();
	}
	
	public static class Person {
		int name;
		int calChonsu;
		public Person(int name, int calChonsu) {
			this.name = name;
			this.calChonsu = calChonsu;
		}
	}
}
