/*
Chris Holland
V00876844
CSC 225, assignment #2
*/
import java.util.Scanner;


public class median{
	static minHeap min;
	static maxHeap max;
	static boolean DEBUG;
	
	public median(){
		min = new minHeap();
		max = new maxHeap();
		DEBUG = false; // debugging variable
	}
	
	
	// Inserts x into the correct heap, modifies them, then returns the median
	public static int calculateMedian(int x){
		if (x < max.peek()) {
			max.insert(x);
		}
		else {
			min.insert(x);
		}
		
		if (min.size() - max.size() > 1) {
			max.insert(min.removeMin());
		}
		if (max.size() - min.size() > 1) {
			min.insert(max.removeMax());
		}
		
		if (max.size() > min.size()) {
			return max.peek();
		}
		else {
			return min.peek();
		}
	}
	
	
	
	
	
	
	public static void main(String[] args){
		median m = new median();
		
		System.out.println("Enter a list of non negative integers. To end enter a negative integers.");
		Scanner s = new Scanner(System.in);
		int current = s.nextInt();

		while(current >=0){
			System.out.println("current median:" + m.calculateMedian(current));
			
			// Debugging code, relies on DEBUG being turned on to 'true'
			if (DEBUG) {
			min.to_String();
			System.out.println(min.size());
			max.to_String();
			System.out.println(max.size());
			}
			
			current = s.nextInt();
			if(current<0)break;
			m.calculateMedian(current);
			current = s.nextInt();
			
		}	
	}
}

/*
########################## M I N    H E A P ####################################
*/

class minHeap{
	private int[] heap;
	private int size;
	
	// For personal debugging, please ignore
	public void to_String() {
		for (int i = 1; i <= size(); i++) {
			System.out.print(heap[i] + " ");
		}
	}
	
	public minHeap(){
		heap=new int[10000];
		size=0;
	}
	
	public boolean isEmpty(){
		return (size==0);
	}
	
	public int size(){
		return size;
	}
	
	// Insert an element into the heap
	public void insert(int x){
		heap[++size] = x;
		bubbleup(size);
	}
	
	// Move and element up to its proper place
	public void bubbleup(int k){
		int parent = k/2;
		
		if (k ==1 || heap[parent] < heap[k]) {
			return;
		}
		exchange(k, parent);
		bubbleup(parent);
	}
	
	// Swap the data at heap index i and j
	public void exchange(int i,int j){
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	
	// Move an element down to its proper location
	public void bubbledown(int k){
		
		if (k*2 > size) return;
		int right = k*2 +1;
		int left = k*2;
		int min_child;
		
		if (right > size) {
			min_child = left;
		} else {
			min_child = (heap[left] < heap[right]) ? left : right;
		}
		
		if (heap[k] < heap[min_child]) {
			return;
		}
		
		exchange(min_child, k);
		bubbledown(min_child);
	}
	
	// Return the value at the top of the heap
	public int peek(){
		if (size == 0) 
		{
			return -1;
		}
		return heap[1];
	}
	
	// Remove the root (min) from the heap
	public int removeMin(){
		if (isEmpty()) 
		{
			return -1;
		}
		
		int root = heap[1];
		heap[1] = heap[size];
		size--;
		bubbledown(1);
		return root;
	}
}

/*
####################### M A X   H E A P ################################
*/

class maxHeap{
	private int[] heap;
	private int size;
	
	// For testing purposes, please ignore
	public void to_String() {
		for (int i = 1; i <= size(); i++) {
			System.out.print(heap[i] + " ");
		}
	}
	
	public maxHeap(){
		heap=new int[10000];
		size=0;
	}
	
	public boolean isEmpty(){
		return (size==0);
	}
	
	public int size(){
		return size;
	}
	
	// Insert an element at the end of the heap
	public void insert(int x){
		heap[++size] = x;
		bubbleup(size);
	}
	
	// Move an inserted element up to its proper location
	public void bubbleup(int k){
		int parent = k/2;
		
		if (k ==1 || heap[parent] > heap[k]) {
			return;
		}
		exchange(k, parent);
		bubbleup(parent);
	}
	
	// Swap the values at index i and j
	public void exchange(int i,int j){
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	
	// Move an element down the heap to its proper location
	public void bubbledown(int k){
		
		if (k*2 > size) return;
		int right = k*2 +1;
		int left = k*2;
		int min_child;
		
		if (right > size) {
			min_child = left;
		} else {
			min_child = (heap[left] > heap[right]) ? left : right;
		}
		
		if (heap[k] > heap[min_child]) {
			return;
		}
		
		exchange(min_child, k);
		bubbledown(min_child);
	}
	
	// Return the value at the top of the heap
	public int peek(){
		if (size == 0) 
		{
			return -1;
		}
		return heap[1];
	}
	
	// Delete the root (max) nd return its value
	public int removeMax(){
		if (isEmpty()) 
		{
			return -1;
		}
		
		int max = heap[1];
		heap[1] = heap[size];
		size--;
		bubbledown(1);
		return max;
	}
}
