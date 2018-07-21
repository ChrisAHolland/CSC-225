/*
*	Rahnuma Islam Nishat - January 17, 2018
*/
/*
Chris Holland
V00876844
CSC 225, Assignment 1
*/
import java.util.Stack; // I imported this, it was not included in the original file
import java.util.Scanner;
import java.io.File;
import java.util.Queue;
import java.util.LinkedList;
public class Stock {

	
	
	
	public static int[] CalculateSpan(int[] p){
		
		// Create a stack that holds integers 
		Stack<Integer> span_stack = new Stack<Integer>();
		
		// Create an array with an index for each day of the stock price
		int[] span = new int[p.length];
		
		// For loop iterates p.length times
		for (int i = 0; i < p.length; i++) 
		{
			// When the span_stack is not empty, and the price at 
			// the top of the stack's value index of the price array is
			// less than or equal to the price of the array index i,
			// pop the top off the stack
			while (!span_stack.isEmpty() && p[(int)span_stack.peek()] <= p[i]) 
			{
				span_stack.pop();
			}
				// If the stack is not empty, the span value at index i
				// is equal to i minues the value at the top of the stack
				if (!span_stack.isEmpty()) 
				{
					span[i] = i - (int)span_stack.peek();
				}
				// If the stack is empty, the span value at index i is i + 1
				// (The simple case of a price at day n being greater than the
				// price at day n-1)
				else 
				{
					span[i] = i + 1;
				}
			// Push the current value of i onto the stack
			span_stack.push(i);
		}
		// return our array of span values
		return span;
	}
	
	
	
	
	
	public static int[] readInput(Scanner s){
		Queue<Integer> q = new LinkedList<Integer>();
		int n=0;
		if(!s.hasNextInt()){
			return null;
		}
		int temp = s.nextInt();
		while(temp>=0){
			q.offer(temp);
			temp = s.nextInt();
			n++;
		}
		int[] inp = new int[q.size()];
		for(int i=0;i<n;i++){
			inp[i]= q.poll();
		}
		return inp;
	}
	public static void main(String[] args){
		Scanner s;
        Stock m = new Stock();
        if (args.length > 0){
        	try{
        		s = new Scanner(new File(args[0]));
        	} catch(java.io.FileNotFoundException e){
        		System.out.printf("Unable to open %s\n",args[0]);
        		return;
        	}
        	System.out.printf("Reading input values from %s.\n", args[0]);
        }else{
        	s = new Scanner(System.in);
        	System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
        }
            
        int[] price = m.readInput(s);
        System.out.println("The stock prices are:");
        for(int i=0;i<price.length;i++){
        	System.out.print(price[i]+ (((i+1)==price.length)? ".": ", "));
        }

        if(price!=null){
        	int[] span = m.CalculateSpan(price);
        	if(span!=null){
        		System.out.println("The spans are:");
        		for(int i=0;i<span.length;i++){
        			System.out.print(span[i]+ (((i+1)==span.length)? ".": ", "));
        		}
        	}
        }
    }
}
