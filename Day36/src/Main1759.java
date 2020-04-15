import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main1759 {
	
	public static String moum = "aiueo";
	public static int n;
	public static int m;
	public static ArrayList<String> ja;
	public static ArrayList<String> mo;
	public static ArrayList<String> all;
	public static int[] tempMO;
	public static int[] tempJA;
	public static int[] tempNA;
	public static ArrayList<String> bimil;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1759.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		ja = new ArrayList<String>();
		mo = new ArrayList<String>();
		all = new ArrayList<String>();
		String temp = "";
		for(int i=0; i<m; i++) {
			temp = st.nextToken();
			if(moum.contains(temp)) mo.add(temp);
			else ja.add(temp);
			all.add(temp);
		}
		
		ja.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.compareTo(o2)>0)	return 1;
				else return -1;
			}
		});
		mo.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.compareTo(o2)>0)	return 1;
				else return -1;
			}
		});
		all.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1.compareTo(o2)>0)	return 1;
				else return -1;
			}
		});
		
		tempMO = new int[1];
		tempJA = new int[2];
		tempNA = new int[n-3];
		selectMo(mo.size(), 1, 0, 0);
		
		br.close();
	}
	
	public static void selectNa(int N, int r, int idx, int depth) {
		if(r==idx) {
			for(int i=0; i<r; i++) {
				bimil.add(all.get(tempNA[i]));
			}
			bimil.sort(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					if(o1.compareTo(o2)>0)	return 1;
					else return -1;
				}
			});
			for(String s : bimil) {
				System.out.print(s);
			}System.out.println();
			
			for(int i=0; i<r; i++) {
				bimil.remove(all.get(tempNA[i]));
			}
			return;
		}
		if(N==depth) return;
		
		tempNA[idx] = depth;
		selectNa(N, r, idx+1, depth+1);
		selectNa(N, r, idx, depth+1);
	}
	
	public static void selectJa(int N, int r, int idx, int depth) {
		if(r==idx) {
//			System.out.println(Arrays.toString(tempMO));
			bimil.add(ja.get(tempJA[0]));
			bimil.add(ja.get(tempJA[1]));
//			System.out.println(bimil.toString());
			
			if(n<=3) {
				bimil.sort(new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						if(o1.compareTo(o2)>0)	return 1;
						else return -1;
					}
				});
				for(String s : bimil) {
					System.out.print(s);
				}System.out.println();
			}else {
				all.remove(ja.get(tempJA[0]));
				all.remove(ja.get(tempJA[1]));
				selectNa(all.size(), n-3, 0, 0);
				all.add(ja.get(tempJA[0]));
				all.add(ja.get(tempJA[1]));
			}
			
			bimil.remove(ja.get(tempJA[0]));
			bimil.remove(ja.get(tempJA[1]));
			return;
		}
		if(N==depth) return;
		
		tempJA[idx] = depth;
		selectJa(N, r, idx+1, depth+1);
		selectJa(N, r, idx, depth+1);
	}

	public static void selectMo(int N, int r, int idx, int depth) {
		if(r==idx) {
//			System.out.println(Arrays.toString(tempMO));
			bimil = new ArrayList<String>();
			bimil.add(mo.get(tempMO[0]));
			all.remove(mo.get(tempMO[0]));
			selectJa(ja.size(), 2, 0, 0);
//			System.out.println(bimil.toString());
			all.add(mo.get(tempMO[0]));
			bimil.clear();
			return;
		}
		if(N==depth) return;
		
		tempMO[idx] = depth;
		selectMo(N, r, idx+1, depth+1);
		selectMo(N, r, idx, depth+1);
	}
	
}
