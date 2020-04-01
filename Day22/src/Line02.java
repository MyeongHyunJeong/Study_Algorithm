import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Line02 {
	
	public static int n;
	public static Node home; 
	public static Node[] shops;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input02.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int x,y;
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		home = new Node(x,y,"home",0,0);
		shops = new Node[n];
		
		String name;
		int coupon;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			name = st.nextToken();
			coupon = Integer.parseInt(st.nextToken());
			shops[i] = new Node(x,y,name,coupon,calDistance(x,y));
		}
		
		int select;
		int current;
		Node temp;
		for(int i=0; i<n-1; i++) {		//O(N)
			select = i;
			for(int j=i+1; j<n; j++) {	//O(N)
				if(shops[select].distance>shops[j].distance) {
					select = j;
				}else if(shops[select].distance==shops[j].distance) {
					if(shops[select].coupon<shops[j].coupon) {
						select = j;
					}else if(shops[select].coupon == shops[j].coupon) {
						if(shops[select].name.compareTo(shops[j].name)>0) {
							select = j;
						}
					}
				}
			}
			temp = shops[i];
			shops[i] = shops[select];
			shops[select] = temp;
		}	//O(N^2)
		
		for(int i=0; i<n; i++) {
			if(shops[i].distance<100) {
				continue;
			}else {
				System.out.println(shops[i].x + " " + shops[i].y + " " + shops[i].name + " " + shops[i].coupon);
			}
		}
		
		br.close();
	}
	
	public static void swap(Node a, Node b) {
		
	}
	
	public static int calDistance(int x, int y) {
		int distance = (int)Math.sqrt(Math.pow(Math.abs(home.x-x), 2) + Math.pow(Math.abs(home.y-y), 2));
		distance -= distance%100;
		return distance;
	}

	public static class Node {
		int x;
		int y;
		String name;
		int coupon;
		int distance;
		public Node(int x, int y, String name, int coupon, int distance) {
			this.x = x;
			this.y = y;
			this.name = name;
			this.coupon = coupon;
			this.distance = distance;
		}
	}
}
