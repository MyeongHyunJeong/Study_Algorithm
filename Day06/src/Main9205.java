import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main9205 {
	
	public static int testCase;
	public static int n;
	public static Node home;
	public static Node[] shop;
	public static Node target;
	public static boolean[] chk;
	public static boolean flag;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input9205.txt"));
		Scanner s = new Scanner(System.in);
		testCase = s.nextInt();
		
		for(int t=0; t<testCase; t++) {
			n = s.nextInt();
			home = new Node(s.nextInt(), s.nextInt());
			shop = new Node[n];
			chk = new boolean[n];
			for(int i=0; i<shop.length; i++) {
				shop[i] = new Node(s.nextInt(), s.nextInt());
			}
			target = new Node(s.nextInt(), s.nextInt());
			
			flag = false;
			getResult();
			if(flag) {
				System.out.println("happy");
			}else {
				System.out.println("sad");
			}
		}
		
	}
	
	public static void getResult() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(home);
		
		Node poll;
		int x;
		int y;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			if((x==target.x && y==target.y) || Math.abs(x-target.x)+Math.abs(y-target.y)<=1000) {
				flag = true;
				break;
			}
			
			for(int i=0; i<shop.length; i++) {
				if(!chk[i] && Math.abs(x-shop[i].x)+Math.abs(y-shop[i].y)<=1000) {
					q.add(shop[i]);
					chk[i] = true;
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
