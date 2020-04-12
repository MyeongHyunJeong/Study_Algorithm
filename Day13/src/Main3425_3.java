import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main3425_3 {
	
	public static ArrayList<Instruction> ins;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input3425.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		ins = new ArrayList<Instruction>();
		while(true) {
			String[] temp = br.readLine().split(" ");
			if(temp[0].equals("END")) {
//				System.out.println("함수 실행");
				int testCase = Integer.parseInt(br.readLine());
				for(int t=0; t<testCase; t++) {
					startTest(Long.parseLong(br.readLine()));
				}
				System.out.println();
				ins.clear();
			}else if(temp[0].equals("NUM")) {
				ins.add(new Instruction(temp[0], Long.parseLong(temp[1])));
			}else if(temp[0].equals("")){
				continue;
			}else if(temp[0].equals("QUIT")) {
				break;
			}else {
				ins.add(new Instruction(temp[0]));
			}
		}

		br.close();
	}
	
	public static void startTest(Long num) {
		Stack<Long> stack = new Stack<Long>();
		stack.add(num);
		
		for(Instruction i : ins) {			
			if(i.ins.equals("NUM")) {
				stack.add(i.num);
			}else if(i.ins.equals("POP")) {
				if(stack.size()==0) {
					System.out.println("ERROR");
					return;
				}else {
					stack.pop();
				}
			}else if(i.ins.equals("INV")) {
				if(stack.size()==0) {
					System.out.println("ERROR");
					return;
				}else {
					stack.add(-stack.pop());
				}
			}else if(i.ins.equals("DUP")) {
				if(stack.size()==0) {
					System.out.println("ERROR");
					return;
				}else {
					stack.add(stack.peek());
				}
			}else if(i.ins.equals("SWP")) {
				if(stack.size()<2) {
					System.out.println("ERROR");
					return;
				}else {
					Long a = stack.pop();
					Long b = stack.pop();
					stack.add(a);
					stack.add(b);
				}
			}else if(i.ins.equals("ADD")) {
				if(stack.size()<2) {
					System.out.println("ERROR");
					return;
				}else {
					Long a = stack.pop();
					Long b = stack.pop();
					if(Math.abs(a+b)>1000000000) {
						System.out.println("ERROR");
						return;
					}else stack.add(a+b);
				}
			}else if(i.ins.equals("SUB")) {
				if(stack.size()<2) {
					System.out.println("ERROR");
					return;
				}else {
					Long a = stack.pop();
					Long b = stack.pop();
					if(Math.abs(b-a)>1000000000) {
						System.out.println("ERROR");
						return;
					}else stack.add(b-a);
				}
			}else if(i.ins.equals("MUL")) {
				if(stack.size()<2) {
					System.out.println("ERROR");
					return;
				}else {
					Long a = stack.pop();
					Long b = stack.pop();
					if(Math.abs(a*b)>1000000000) {
						System.out.println("ERROR");
						return;
					}else stack.add(a*b);
				}
			}else if(i.ins.equals("DIV")) {
				if(stack.size()<2) {
					System.out.println("ERROR");
					return;
				}else {
					Long a = stack.pop();
					Long b = stack.pop();
					if(a==0) {
						System.out.println("ERROR");
						return;
					}else {
						if(a*b<0) stack.add(Math.abs(b/a));
						else stack.add(-Math.abs(b/a));
					}
				}
			}else if(i.ins.equals("MOD")) {
				if(stack.size()<2) {
					System.out.println("ERROR");
					return;
				}else {
					Long a = stack.pop();
					Long b = stack.pop();
					if(a==0) {
						System.out.println("ERROR");
						return;
					}else {
						if(b<0) stack.add(-Math.abs(b/a));
						else stack.add(Math.abs(b/a));
					}
				}
			}else {
				System.out.println("ERROR");
				return;
			}
		}
		if(stack.size()==1) {
			System.out.println(stack.pop());
		}else {
			System.out.println("ERROR");
		}
	}
	
	public static class Instruction {
		String ins;
		Long num;
		public Instruction(String ins) {
			this.ins = ins;
		}
		public Instruction(String ins, Long num) {
			this.ins = ins;
			this.num = num;
		}
	}

}
