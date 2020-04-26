import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main01 {

	public static int n;
	public static int result;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input01.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		Queue<Dragon> dragons = new LinkedList<Dragon>();
		Queue<Egg> eggs = new LinkedList<Egg>();
		eggs.add(new Egg(2));
		
		int ecount = 0;
		int ydcount = 0;
		int odcount = 0;
		
		Egg pollEgg;
		Dragon pollDragon;
		
		for(int i=0; i<=n; i++) {
			System.out.println("DAY " + i);
			ecount = eggs.size();
			for(int j=0; j<ecount; j++) {
				pollEgg = eggs.poll();
				if(pollEgg.age==2) {
					eggs.add(new Egg(1));
				}else if(pollEgg.age==1) {
					eggs.add(new Egg(0));
				}else {
					dragons.add(new Dragon(0));
				}
			}
			
			ydcount = dragons.size();
			for(int j=0; j<ydcount; j++) {
				pollDragon = dragons.poll();
				if(pollDragon.age==4) {
					odcount++;
				}else {
					dragons.add(new Dragon(pollDragon.age+1));
					eggs.add(new Egg(1));
				}
			}
			
			System.out.println("Young Dragon : " + dragons.size());
			System.out.println("Egg : " + eggs.size());
			System.out.println("Old Dragon  : " + odcount);
			System.out.println();
		}
		
		result = dragons.size() + eggs.size() + odcount;
		System.out.println(result);
		
		br.close();
		
	}
	
	public static class Dragon{
		int age;
		public Dragon(int age) {
			this.age = age;
		}
	}
	
	public static class Egg{
		int age;
		public Egg(int age) {
			this.age = age;
		}
	}

}
