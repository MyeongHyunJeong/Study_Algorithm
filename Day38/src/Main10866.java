import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main10866 {
	
	public static int n;
	public static Deque<Integer> dq;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input10866.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		dq = new LinkedList<Integer>();
		
		String instruction = "";
		for(int i=0; i<n; i++) {
//			System.out.println(dq.toString());
			st = new StringTokenizer(br.readLine());
			instruction = st.nextToken();
			if(instruction.equals("push_front")) {
				dq.addFirst(Integer.parseInt(st.nextToken()));
			}else if(instruction.equals("push_back")) {
				dq.addLast(Integer.parseInt(st.nextToken()));
			}else if(instruction.equals("pop_front")) {
				if(dq.size()>0) System.out.println(dq.removeFirst());
				else System.out.println(-1);
			}else if(instruction.equals("pop_back")) {
				if(dq.size()>0) System.out.println(dq.removeLast());
				else System.out.println(-1);
			}else if(instruction.equals("size")) {
				System.out.println(dq.size());
			}else if(instruction.equals("empty")) {
				if(dq.isEmpty()) System.out.println(1);
				else System.out.println(0);
			}else if(instruction.equals("front")) {
				if(dq.size()>0) System.out.println(dq.getFirst());
				else System.out.println(-1);
			}else if(instruction.equals("back")) {
				if(dq.size()>0) System.out.println(dq.getLast());
				else System.out.println(-1);
			}
		}
		
		br.close();
	}

}
