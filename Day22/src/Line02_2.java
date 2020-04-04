import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Line02_2 {

	public static Node home;
	public static Node[] stores;
	public static int numStore;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input02.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x,y,coupon,distance;
		String name;
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		home = new Node(x,y,"HOME",0,0);
		numStore = Integer.parseInt(st.nextToken());
		stores = new Node[numStore];
		for(int i=0; i<numStore; i++) {	//O(n)
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			name = st.nextToken();
			coupon = Integer.parseInt(st.nextToken());
			distance = (int)Math.sqrt(Math.pow(Math.abs(x-home.x), 2)+(int)Math.pow(Math.abs(y-home.y), 2));
			distance -= distance%100;
			System.out.println(distance);
			stores[i] = new Node(x,y,name,coupon,distance);
		}
		
		Node temp;
		int idx;
		for(int i=0; i<numStore-1; i++) {	//O(n)
			temp = stores[i];
			idx = i;
			for(int j=i+1; j<numStore; j++) {	//O(n^2)
				if(stores[j].distance<temp.distance) {
					temp = stores[j];
					idx = j;
				}else if(stores[j].distance==temp.distance) {
					if(temp.coupon<stores[j].coupon) {
						temp = stores[j];
					}else if(temp.coupon == stores[j].coupon) {
						if(temp.name.compareTo(stores[j].name)>0) {
							temp = stores[j];
							idx = j;
						}
					}
				}else {
					continue;
				}
			}
			
			if(idx!=i) {
				stores[idx] = stores[i];
				stores[i] = temp;
			}
		}
		
		for(int i=0; i<numStore; i++) {	//O(n)
			System.out.println(stores[i].x + " " + stores[i].y + " " + stores[i].name + " " + stores[i].coupon);
		}
		
		
		br.close();
	}	//O(n^2)
	
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
