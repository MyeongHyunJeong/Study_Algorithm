import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main1759_2 {
	
	public static String moum = "aiueo";
	public static int n;
	public static int m;
	public static ArrayList<String> all;
	public static int[] tempBI;
	public static ArrayList<String> bimil;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1759.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		all = new ArrayList<String>();
		String temp = "";
		for(int i=0; i<m; i++) {
			temp = st.nextToken();
			all.add(temp);
		}
		all.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.compareTo(o2)>0)	return 1;
				else return -1;
			}
		});
		
//		for(String s : all) {
//			System.out.print(s);
//		}System.out.println();
		
		tempBI = new int[n];
		select(all.size(), n, 0, 0);
		
		br.close();
	}
	
	public static void select(int N, int r, int idx, int depth) {
		if(idx==r) {
			bimil = new ArrayList<String>();
			int ja = 0;
			int mo = 0;
			for(int i=0; i<r; i++) {
				bimil.add(all.get(tempBI[i]));
				if(moum.contains(bimil.get(i))) {
					mo++;
				}else {
					ja++;
				}
			}
			if(ja>1 && mo>0) {
				for(String s : bimil) {
					System.out.print(s);
				}System.out.println();
			}
			return;
		}
		if(depth==N) return;
		
		tempBI[idx] = depth;
		select(N,r,idx+1,depth+1);
		select(N,r,idx,depth+1);
	}
	
}
