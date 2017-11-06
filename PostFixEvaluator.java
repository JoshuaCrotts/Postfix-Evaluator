import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

public class PostFixEvaluator{

	public static void main(String[] args){

		Scanner file = null;

		try{
			file = new Scanner(new File("postfix.txt"));
		}catch(Exception e) {
			e.printStackTrace();
		}

		int inputs = file.nextInt();

		for(int i = 0; i < inputs; i++) {
			Stack<Integer> operands = new Stack<Integer>();
			ArrayList<String> parts = new ArrayList<String>();

			String next = "";

			while(true) {
				try {
					next = file.next();
				}catch(NoSuchElementException ex) {
					break;
				}
				if(next.equals("."))
					break;
				parts.add(next);
			}
			//System.out.println(parts);

			for(int j = 0; j < parts.size(); j++){

				String ch = parts.get(j);

				if(!isOperation(ch)){
					operands.push((int) Double.parseDouble(ch));
				}else {
					int one = operands.pop();
					int two = operands.pop();
					
					switch(ch){
					case "+": operands.push(two + one); break;
					case "-": operands.push(two - one); break;
					case "*": operands.push(two * one); break;
					case "/": operands.push(two / one); break;
					}
				}

				//System.out.println("Stack on iteration "+(j+1)+": "+operands);
			}
			System.out.print(operands.pop()+": ");

			for(int j = 0; j < parts.size(); j++) {
				System.out.print(parts.get(j)+" ");
			}
			System.out.println("");
		}
	}

	private static boolean isOperation(String ch){
		return ch.equals("+") || 
				ch.equals("-") || 
				ch.equals("*") || 
				ch.equals("/");
	}
}