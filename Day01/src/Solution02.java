import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution02 {

	public static int n;
	public static int m;
	public static boolean[][] map;
	public static int min = Integer.MAX_VALUE;
	public static int result = 0;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input02.txt"));
		Scanner s = new Scanner(System.in);
		
		n = s.nextInt();
		m = s.nextInt();
		map = new boolean[n+1][n+1];
//		System.out.println(map[0].length);
		int temp = 0;
		int x = 0;
		int y = 0;
		for(int i=0; i<m; i++) {
			x = s.nextInt();
			y = s.nextInt();
			map[x][y] = true;
			map[y][x] = true;
		}
		
//		System.out.println(Arrays.deepToString(map));
		
		for(int i=1; i<n+1; i++) {
			getResult(i);
		}
		System.out.println(result);
	}
	
	public static void getResult(int start) {
		Queue<Node> q = new LinkedList<Node>();
		boolean[] chk = new boolean[n+1];
		q.add(new Node(start, 1));
		chk[start] = true;
		
		Node poll;
		int count = 0;
		int index = 0;
		int depth = 0;
		while(!q.isEmpty()) {
			poll = q.poll();
			index = poll.x;
			depth = poll.depth;
			for(int i=1; i<map[index].length; i++) {
				if(i==index) {
					continue;
				}
				if(map[index][i] && !chk[i]) {
					q.add(new Node(i, depth+1));
					chk[i] = true;
					count += depth;
				}
			}
		}
		if(count<min) {
			min = count;
			result = start;
		}
	}

	public static class Node {
		int x;
		int depth;
		public Node(int x, int depth) {
			this.x = x;
			this.depth = depth;
		}
		
		
	}
}

