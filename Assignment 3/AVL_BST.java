/*
Chris Holland
CSC 225, Assignment #3
*/

public class AVL_BST{
	
	public static boolean DEBUG = false; // For debugging
	
	// Simply return the isBalanced() method implemented in my BST class
	public static boolean checkAVL(BST b){
		return b.isBalanced(b.root);
	}
	
	// Iterate through the array and insert each element
	public static BST createBST(int[] a){
		BST tree = new BST();
		
		for (int i = 0; i < a.length; i++) {
			tree.insert(a[i]);
		}
		return tree;
	}
	
	public static void main(String[] args){
		int[] A = {82,85,153,195,124,66,200,193,185,243,73,153,76};
		BST b = createBST(A);
		
		if (DEBUG) {
			System.out.println(b.size());
		}
		
		System.out.println(checkAVL(b));
	}
}

class BST {
	public static Node root;
	int size;
	
	public BST() {
		size = 0;
		root = null;
	}
	
	// Iterative insert method
	public void insert(int x) {
		size++;
		Node temp = new Node(x);
		if (root == null) {
			root = temp;
			return;
		}
		Node current = root;
		Node parent = null;
		
		while (1 > 0) {
			parent = current;
			
			if (temp.value < current.value) 
			{
				current = current.left;
				if (current == null) {
					parent.left = temp;
					return;
				}
			} 
			else 
			{
				current = current.right;
				if (current == null) {
					parent.right = temp;
					return;
				}
			}
		}
	}
	
	// Size of the BST
	public int size() {
		return size;
	}
	
	// Public height method, calls private recursive method
	public int height(Node n) {
		if (root == null) {
			return -1;
		}
		Node temp = root;
		return getHeight(temp);
	}
	
	// Private recursive height method
	private int getHeight(Node n) {
		int left_height = 0;
		int right_height = 0;
		
		if (n.left != null) {
			left_height = getHeight(n.left);
		}
		if (n.right != null) {
			right_height = getHeight(n.right);
		}
		if (left_height > right_height) {
			return left_height + 1;
		} else {
			return right_height + 1;
		}
	}
	
	// Returns true if the tree is balanced (AVL), false otherwise
	public boolean isBalanced(Node root) {
		if (root == null) {
			return true;
		}
		
		return maxDepth(root) - minDepth(root) <= 1;
	}
	
	// Calculates the minimum depth of the tree
	private int minDepth(Node root) {
		if (root == null) {
			return 0;
		}
		
		return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
	}
	
	// Calculates the maximum depth of the tree
	private int maxDepth(Node root) {
		if (root == null) {
			return 0;
		}
		
		return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
	}
}

// Node class for my BST
class Node {
	int value;
	Node left;
	Node right;
	
	public Node(int a) {
		this.value = a;
		left = null;
		right = null;
	}
}
