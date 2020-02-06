import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main14502 {

	public static int n;
	public static int m;
	public static int[][] map;
	public static int zcount;
	public static Node[] z;
	public static int result = Integer.MIN_VALUE;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input14502.txt"));
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		m = s.nextInt();
		map = new int[n][m];
		zcount = 0;
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				map[i][j] = s.nextInt();
				if(map[i][j]==0) {
					zcount++;
				}
			}
		}

		z = new Node[zcount];
		int tempCount = 0;
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				if(map[i][j]==0) {
					z[tempCount++] = new Node(i, j);
				}
			}
		}
		
		int[] arr = new int[3];
		combination(arr, zcount, 3, 0, 0);
		
		System.out.println(result);
	}
	
	public static void combination(int[] arr, int n, int r, int index, int target) {
		if(r==0) {
//			System.out.println("INDEX: " + arr[0] + " x: " + z[arr[0]].x + " y: " + z[arr[0]].y);
//			System.out.println("INDEX: " + arr[1] + " x: " + z[arr[1]].x + " y: " + z[arr[1]].y);
//			System.out.println("INDEX: " + arr[2] + " x: " + z[arr[2]].x + " y: " + z[arr[2]].y);
//			System.out.println("================================================================");
			getResult(z[arr[0]], z[arr[1]], z[arr[2]]);
			return;
		}
		if(n==target) {
			return;
		}
		
		arr[index] = target;
		combination(arr, n, r-1, index+1, target+1);
		combination(arr, n, r, index, target+1);
	}
	
	public static void getResult(Node a, Node b, Node c) {
		int[][] tempMap = new int[n][m];
		boolean[][] chk = new boolean[n][m];
		Queue<Node> q = new LinkedList<Node>();
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				tempMap[i][j] = map[i][j];
				if(a.x==i && a.y==j) {
					tempMap[i][j] = 1;
				}else if(b.x==i && b.y==j) {
					tempMap[i][j] = 1;
				}else if(c.x==i && c.y==j) {
					tempMap[i][j] = 1;
				}
				if(tempMap[i][j]==2) {
					q.add(new Node(i, j));
					chk[i][j] = true;
				}else if(tempMap[i][j]==1) {
					chk[i][j] = true;
				}
			}
		}
		
		Node poll;
		int x;
		int y;
		int nx;
		int ny;
		int count = zcount-3;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			for(int i=0; i<dir.length; i++) {
				nx = x + dir[i][0];
				ny = y + dir[i][1];
				if(nx>-1 && ny>-1 && nx<tempMap.length && ny<tempMap[0].length && tempMap[nx][ny]==0 && !chk[nx][ny]) {
					tempMap[nx][ny] = 2;
					chk[nx][ny] = true;
					count--;
					q.add(new Node(nx, ny));
				}
			}
		}
		
		if(count>result) {
			result = count;
		}
		
	}
	
	public static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
