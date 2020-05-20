import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution5644 {
	
	public static int testCase;
	public static boolean[][][] map;
	public static int m;
	public static int n;
	public static Point A;
	public static Point B;	
	public static int[] a;
	public static int[] b;
	public static BC[] bcs;
	public static int[][] dir = {{0,0},{-1,0},{0,1},{1,0},{0,-1}};
	public static int charge;
	public static ArrayList<BC> abc;
	public static ArrayList<BC> bbc;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input5644.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		testCase = Integer.parseInt(br.readLine());
		
		for(int t=0; t<testCase; t++) {
//			System.out.println("========== t : " + (t+1) + " ===============");
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			map = new boolean[10][10][n];
			a = new int[m];
			b = new int[m];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<m; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<m; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}
			
			int x,y,c,p,connect;
			bcs = new BC[n];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				y = Integer.parseInt(st.nextToken())-1;
				x = Integer.parseInt(st.nextToken())-1;
				c = Integer.parseInt(st.nextToken());
				p = Integer.parseInt(st.nextToken());
				bcs[i] = new BC(x,y,c,p,0);
				chkBCRange(i);
			}
//			printMap();
			
			A = new Point(0,0);
			B = new Point(9,9);
			charge = 0;
			move();
			
			System.out.println("#" + (t+1) + " " + charge);
		}
		
	}
	
	public static void move() {
		abc = new ArrayList<BC>();
		bbc = new ArrayList<BC>();
		int max=0;
		for(int i=0; i<n; i++) {
			if(map[A.x][A.y][i]) {
				abc.add(bcs[i]);
			}
			if(map[B.x][B.y][i]) {
				bbc.add(bcs[i]);
			}
		}
		
		if(abc.size()==1 && bbc.size()==1) {
			if(abc.get(0).x==bbc.get(0).x && abc.get(0).y==bbc.get(0).y) {
				max = abc.get(0).p;
			}else {
				max = abc.get(0).p + bbc.get(0).p;
			}
		}else if(abc.size()==0 && bbc.size()==1) {
			max = bbc.get(0).p;
		}else if(abc.size()==1 && bbc.size()==0) {
			max = abc.get(0).p;
		}else if(abc.size()==0 && bbc.size()==0) {
			max = 0;
		}else {
			for(int i=0; i<abc.size(); i++) {
				for(int j=0; j<bbc.size(); j++) {
					if(abc.get(i).x==bbc.get(j).x && abc.get(i).y==bbc.get(j).y) {
						if(max<abc.get(i).p) {
							max = abc.get(i).p;
						}
					}else {
						if(max<abc.get(i).p+bbc.get(j).p) {
							max = abc.get(i).p + bbc.get(j).p;
						}
					}
				}
			}
		}
		charge += max;
		abc.clear();
		bbc.clear();
		System.out.println("T:0 -> " + charge);

		for(int t=0; t<m; t++) {
			A.x = A.x + dir[a[t]][0];
			A.y = A.y + dir[a[t]][1];
			B.x = B.x + dir[b[t]][0];
			B.y = B.y + dir[b[t]][1];

			max = 0;
			for(int i=0; i<n; i++) {
				if(map[A.x][A.y][i]) {
					abc.add(bcs[i]);
				}
				if(map[B.x][B.y][i]) {
					bbc.add(bcs[i]);
				}
			}
			
			if(abc.size()==1 && bbc.size()==1) {
				if(abc.get(0).x==bbc.get(0).x && abc.get(0).y==bbc.get(0).y) {
					max = abc.get(0).p;
				}else {
					max = abc.get(0).p + bbc.get(0).p;
				}
			}else if(abc.size()==0 && bbc.size()==1) {
				max = bbc.get(0).p;
			}else if(abc.size()==1 && bbc.size()==0) {
				max = abc.get(0).p;
			}else if(abc.size()==0 && bbc.size()==0) {
				max = 0;
			}else {
				for(int i=0; i<abc.size(); i++) {
					for(int j=0; j<bbc.size(); j++) {
						if(abc.get(i).x==bbc.get(j).x && abc.get(i).y==bbc.get(j).y) {
							if(max<abc.get(i).p) {
								max = abc.get(i).p;
							}
						}else {
							if(max<abc.get(i).p+bbc.get(j).p) {
								max = abc.get(i).p + bbc.get(j).p;
							}
						}
					}
				}
			}
			
			
			charge += max;
			System.out.println("T:" + (t+1) + " -> " + charge);
			abc.clear();
			bbc.clear();
		}
	}
	
	public static void chkBCRange(int idx) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(bcs[idx].x, bcs[idx].y,0));
		map[bcs[idx].x][bcs[idx].y][idx] = true;
		
		Node poll;
		int x,y,nx,ny,depth;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			depth = poll.depth;
			for(int k=1; k<5; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<10 && ny<10 && !map[nx][ny][idx] && depth<bcs[idx].c) {
					q.add(new Node(nx,ny,depth+1));
					map[nx][ny][idx] = true;
				}
			}
		}
	}
	
	public static void printMap() {
		for(int k=0; k<n; k++) {
			System.out.println(bcs[k].x + " " + bcs[k].y + " " + bcs[k].c);
			for(int i=0; i<10; i++) {
				for(int j=0; j<10; j++) {
					System.out.print(map[i][j][k] + "\t");
				}System.out.println();
			}System.out.println();
		}
	}
	
	public static class Node{
		int x;
		int y;
		int depth;
		public Node(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}
	
	public static class BC {
		int x;
		int y;
		int c;
		int p;
		int connect;
		public BC(int x, int y, int c, int p, int connect) {  
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
			this.connect = connect;
		}
	}

}
