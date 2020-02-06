import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution01 {

	public static int r, c;
	public static char[][] map;
	public static boolean[][] chk;
	public static int v = 0;
	public static int k = 0;
	public static int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	
	public static void main(String[] args) throws Exception{
		
		System.setIn(new FileInputStream("input01.txt"));
		Scanner s = new Scanner(System.in);
		r = s.nextInt();
		c = s.nextInt();
//		System.out.println(r + " " + c);
		map = new char[r][c];
		chk = new boolean[r][c];
		
		String temp = "";
		for(int i=0; i<map.length; i++) {
			temp = s.next();
			map[i] = temp.toCharArray();
		}
		
//		System.out.println(Arrays.deepToString(map));
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				if(map[i][j]!='#' && !chk[i][j]) {
					count(i, j);
				}
			}
		}
		System.out.println(k + " " + v);
		
	}
	
	public static void count(int i, int j) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(i, j));
		chk[i][j] = true;
		
		Node poll;
		int x, y;
		int countV = 0;
		int countK = 0;
		if(map[i][j]=='v') {
			countV++;
		}else if(map[i][j]=='k') {
			countK++;
		}
		while(!q.isEmpty()) {
			poll = q.poll();
			chk[poll.x][poll.y]= true;
			for(int k=0; k<dir.length; k++) {
				x = dir[k][0] + poll.x;
				y = dir[k][1] + poll.y;
				if(x>-1 && y>-1 && x<map.length && y<map[0].length && !chk[x][y]) {
					if(map[x][y]=='#') {
						continue;
					}else {
						if(map[x][y]=='v') {
							countV++;
						}else if(map[x][y]=='k') {
							countK++;
						}
						q.add(new Node(x,y));
					}
					chk[x][y] = true;
				}
			}
		}
//		System.out.println(Arrays.deepToString(chk));
//		System.out.println(countV +  " " + countK);
		
		if(countV >= countK) {
			v += countV;
		}else {
			k += countK;
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