import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main10166 {

	public static int d1;
	public static int d2;
	public static ArrayList<Float> arr;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input10166.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		d1 = Integer.parseInt(st.nextToken());
		d2 = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList<Float>();
		
		int d = 0;
		float degree = 360;
		float seat = 0;
		boolean flag = false;

		for(int i=d1; i<=d2; i++) {
			d = i;
			
			for(int j=0; j<d; j++) {
				flag = false;
				seat = degree / d * j;
				for(Float f : arr) {
					if(f==seat) {
						flag = true;
						break;
					}
					if(f>seat) break;
				}
				if(!flag) arr.add(seat);
			}
		}
		
		System.out.println(arr.size());
		
		br.close();
	}

}
