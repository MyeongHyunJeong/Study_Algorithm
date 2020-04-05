import java.util.Stack;

public class Solution {

	public static void main(String[] args) {
		solution("Hello, world!");
	}
	
	public static int solution(String inputString) {
	    int answer = -1;
	    Stack<Character> s = new Stack<Character>();
	    char[] str = inputString.toCharArray();
	    char temp;
	    int count = 0;
	    for(int i=0; i<str.length; i++) {
	    	if(str[i]=='[' || str[i]=='(' || str[i]=='{' || str[i]=='<') {
	    		s.add(str[i]);
	    	}else if(str[i]==']' || str[i]==')' || str[i]=='}' || str[i]=='>') {
	    		if(s.size()==0) {
	    			return -1;
	    		}
	    		s.pop();
	    		count++;
	    	}
	    }
	    if(s.size()==0) answer = count;
	    else answer = -1;
	    
	    return answer;
	}

}
