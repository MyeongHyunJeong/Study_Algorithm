import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main7562 {

	public static int testCase;
	public static int n;
	public static int[][] map;
	public static boolean[][] chk;
	public static int sx;
	public static int sy;
	public static int ex;
	public static int ey;
	public static Node start;
	public static Node end;
	public static int result;
	public static int[][] dir = {{-1,-2},{-2,-1},{1,-2},{2,-1},{-2,1},{-1,2},{2,1},{1,2}};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input7562.txt"));
		Scanner s = new Scanner(System.in);
		testCase = s.nextInt();
		
		for(int t=0; t<testCase; t++) {
			n = s.nextInt();
			sx = s.nextInt();
			sy = s.nextInt();
			ex = s.nextInt();
			ey = s.nextInt();
			map = new int[n][n];
			chk = new boolean[n][n];
			start = new Node(sx, sy);
			end = new Node(ex, ey);
			result = 0;
			if(sx==ex && sy==ey) {
				System.out.println(result);
				continue;
			}
			getResult(start);
			System.out.println(map[ex][ey]);
		}
	}
	
	public static void getResult(Node start) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(start);
		chk[start.x][start.y] = true;
		
		Node poll;
		int x = 0;
		int y = 0;
		int nx = 0;
		int ny = 0;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			if(x==ex && y==ey) {
				break;
			}
			
			for(int i=0; i<dir.length; i++) {
				nx = x + dir[i][0];
				ny = y + dir[i][1];
				if(nx>-1 && ny>-1 && nx<map.length && ny<map[0].length && !chk[nx][ny]) {
					q.add(new Node(nx, ny));
					chk[nx][ny] = true;
					map[nx][ny] = map[x][y] + 1;
				}
			}
			
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
