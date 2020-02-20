import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main14499 {

	public static int n;
	public static int m;
	public static int x;
	public static int y;
	public static int k;
	public static int[][] map;
	public static int[] ins;
	public static int[] jusa;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input14499.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//초기화
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ins = new int[k];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<k; i++) {
			ins[i] = Integer.parseInt(st.nextToken());
		}
		
		//초기화 확인
//		printMap();
//		printIns();
		
		//주사위 초기화
		jusa = new int[7];
		//출발지점의 가장아랬면 저장
		
		start();
	}
	
	public static void start() {
		int in;
		int nx;
		int ny;
		//1:동 2:서 3:남 4:북
		for(int i=0; i<k; i++) {
			in = ins[i];
			nx = x;
			ny = y;
			if(in==1) {
				ny = y+1;
			}else if(in==2) {
				ny = y-1;
			}else if(in==3) {
				nx = x-1;
			}else if(in==4) {
				nx = x+1;
			}
			
			if(nx>-1 && ny>-1 && nx<n && ny<m) {
				x = nx;
				y = ny;
				moveJusa(in);
				if(map[nx][ny]==0) {
					map[nx][ny] = jusa[6];
				}else {
					jusa[6] = map[nx][ny];
					map[nx][ny] = 0;
				}
				System.out.println(jusa[1]);
			}
			
		}
	}
	
	public static void moveJusa(int in) {
		int[] temp = jusa.clone();
		
		if(in==1) {
			jusa[1] = temp[4];
			jusa[3] = temp[1];
			jusa[4] = temp[6];
			jusa[6] = temp[3];
		}else if(in==2) {
			jusa[1] = temp[3];
			jusa[3] = temp[6];
			jusa[4] = temp[1];
			jusa[6] = temp[4];
		}else if(in==3) {
			jusa[1] = temp[5];
			jusa[2] = temp[1];
			jusa[5] = temp[6];
			jusa[6] = temp[2];
		}else {
			jusa[1] = temp[2];
			jusa[2] = temp[6];
			jusa[5] = temp[1];
			jusa[6] = temp[5];
		}
	}
	
	public static void printMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j] + " ");
			}System.out.println();
		}System.out.println();
	}
	
	public static void printIns() {
		for(int i=0; i<k; i++) {
			System.out.print(ins[i] + " ");
		}System.out.println();
	}

}
