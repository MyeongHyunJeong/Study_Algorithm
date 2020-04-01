import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main3425 {
	
	public static String start;
	public static Queue<String> instructions;
	public static String tins;
	public static int n;
	public static int ip;
	public static int inssize;
	public static boolean flag;
	public static int result;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input3425.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			start = br.readLine();
			if(start.equals("QUIT")) {
				break;
			}
			
			//¸í·É¾î ÀúÀå
			instructions = new LinkedList<String>();
			instructions.add(start);
			while(true) {
				tins = br.readLine();
				if(tins.equals("END")) {
					break;
				}
				instructions.add(tins);
			}
			inssize = instructions.size();
			
			n = Integer.parseInt(br.readLine());
			for(int i=0; i<n; i++) {
				ip = Integer.parseInt(br.readLine());
				flag = false;
				cal();
				if(flag) {
					System.out.println("ERROR");
				}else {
					System.out.println(result);
				}
			}
			System.out.println();
			//ºóÄ­ Á¦°Å
			br.readLine();
		}
	}
	
	public static void cal() {
		String temp;
		int pop1;
		int pop2;
		int tresult;
		Stack<Integer> stack = new Stack<Integer>();
		Queue<String> ins = new LinkedList<String>();
		ins.addAll(instructions);
		stack.add(ip);
		for(int i=0; i<inssize; i++) {
			temp = ins.poll();
			if(temp.contains("NUM")) {
				stack.add(Integer.parseInt(temp.split(" ")[1]));
			}else if(temp.equals("POP")) {
				if(stack.size()<1) {
					flag = true;
					break;
				}
				stack.pop();
			}else if(temp.equals("INV")) {
				if(stack.size()<1) {
					flag = true;
					break;
				}
				stack.add(-stack.pop());
			}else if(temp.equals("DUP")) {
				if(stack.size()<1) {
					flag = true;
					break;
				}
				stack.add(stack.peek());
			}else if(temp.equals("SWP")) {
				if(stack.size()<2) {
					flag = true;
					break;
				}
				pop1 = stack.pop();
				pop2 = stack.pop();
				stack.add(pop1);
				stack.add(pop2);
			}else if(temp.equals("ADD")) {
				if(stack.size()<2) {
					flag = true;
					break;
				}
				pop1 = stack.pop();
				pop2 = stack.pop();
				tresult = pop1 + pop2;
				if(tresult>1000000000) {
					flag = true;
					break;
				}
				stack.add(tresult);
			}else if(temp.equals("SUB")) {
				if(stack.size()<2) {
					flag = true;
					break;
				}
				pop1 = stack.pop();
				pop2 = stack.pop();
				tresult = pop2 - pop1;
				if(tresult>1000000000) {
					flag = true;
					break;
				}
				stack.add(tresult);
			}else if(temp.equals("MUL")) {
				if(stack.size()<2) {
					flag = true;
					break;
				}
				pop1 = stack.pop();
				pop2 = stack.pop();
				tresult = pop1 * pop2;
				if(tresult>1000000000) {
					flag = true;
					break;
				}
				stack.add(tresult);
			}else if(temp.equals("DIV")) {
				if(stack.size()<2) {
					flag = true;
					break;
				}
				pop1 = stack.pop();
				pop2 = stack.pop();
				if(pop1==0) {
					flag = true;
					break;
				}
				if((pop1<0 && pop2>=0) || (pop1>=0 && pop2<0)) {
					stack.add(-(Math.abs(pop2)/Math.abs(pop1)));
				}else {
					stack.add(Math.abs(pop2)/Math.abs(pop1));
				}
			}else if(temp.equals("MOD")) {
				if(stack.size()<2) {
					flag = true;
					break;
				}
				pop1 = stack.pop();
				pop2 = stack.pop();
				if(pop1==0) {
					flag = true;
					break;
				}
				if(pop2<0) {
					stack.add(-(Math.abs(pop2)%Math.abs(pop1)));
				}else {
					stack.add(Math.abs(pop2)%Math.abs(pop1));
				}
			}
			
			ins.add(temp);
		}
		if(stack.size()==1) {
			result = stack.pop();
		}else {
			flag = true;
		}
	}

}
