/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author Hannah Foley
 *
 *************************************************************************/

import java.util.NoSuchElementException;
import java.lang.Math;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    /**
     * Private node class.
     */
    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() { return size() == 0; }

    // return number of key-value pairs in BST
    public int size() { return size(root); }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { return get(root, key); }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Tree height.
     *
     * Asymptotic worst-case running time using Theta notation:
     * Line 1 : This is a constant time operation,  big theta (1)
     * Lines 2-4: This calls the isEmpty function which has a constant run time, big theta (1)
     * However in the worst case, the tree will not be null, so this if statement will not be entered.
     * Similarly, in the worst case, the middle else statement will not be entered as the tree has more than 1 node.
     * Instead, the second else statement would be entered. This calls on the recursive treeHeight function
     *
     * Within the treeHeight function, there are only constant time operations, so the run time for one recursive
     * iteration is big theta (1).
     * However, this function is called recursively n times, N being the number of nodes in the tree.
     * Therefore the worst case asymptotic run time of calling this recursive function from the main function is big theta (N).
     *
     * All together the worst case run time is
     * big theta (1) + big theta (N)
     * The properties of asymptotic run time tell us that we should only keep the highest order terms when adding. So
     * therefore the worst case run time of height for a tree with N nodes is: big theta (N)
     *
     * T(N) = big theta (N)
     *
     * @return the number of links from the root to the deepest leaf.
     *
     * Example 1: for an empty tree this should return -1.
     * Example 2: for a tree with only one node it should return 0.
     * Example 3: for the following tree it should return 2.
     *   B
     *  / \
     * A   C
     *      \
     *       D
     */
    public int height() {
        Node n = root;
        if(isEmpty()) {
            return -1;
        }
        else if ((n.left == null)&&(n.right == null)){
                return 0;
        }
        else
        {
            //maximum number of links from a leaf to root
            return treeHeight(n);
        }
    }

    private int treeHeight(Node node){
        if (node == null)
        {
            return -1;
        }
        else
        {
            //RECURSION TO FIND NO OF LINKS BETWEEN NODES
            int countL = treeHeight(node.left);
            int countR = treeHeight(node.right);
            //ADDING THE LINK FOR THE ROOT NODE
            countL++;
            countR++;
            return Math.max(countL, countR);
        }
    }

    /**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key
     * is the element at position (N+1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     *
     * NEEDS TO RUN IN WORST CASE O(H) TIME WHERE H IS HEIGHT OF THE TREE
     * (rank and select methods from lecture and book)
     *
     * Upper bound (big O run time)
     * Line 1: isEmpty function = constant run time O(1)
     * Line 2: maths = constant time operation O(1)
     * Line 3: calls to select function = O(h)
     *
     * Select function calls to recursive select function. Recursive select function has a run time of O(1) and is called
     * recursively in the worst case scenario, the biggest height of the tree times. So all together this = O(h)
     *
     * Line 4: return = constant time O(1)
     *
     * Added = O(1) + O(1) + O(h) + O(1)
     * = O(h) keeping highest order term
     * T(h) = O(h)
     * h = height of tree
     */
    public Key median() {
        if (isEmpty()) return null;
        //1. GET INDEX OF MEDIAN
        int median = ((size()+1)/2) - 1;
        //Node.N = number of nodes in that nodes subtree//
        Key median1 = select(median);
        //2. RETURN THE MEDIAN KEY
        return median1;
    }

    //select code from lecture slides
    public Key select(int n) {
        if (n < 0 || n >= size()) return null;
        Node x = select(root, n);
        return x.key;
    }

    private Node select(Node x, int n) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > n) return select(x.left, n);
        else if (t < n) return select(x.right, n-t-1);
        else return x;
    }



    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */
    public String printKeysInOrder() {
        if (isEmpty()) return "()";
        String output = "";

        output = getString(root);

        return output;
    }

    private String getString(Node n)
    {
        String output ="";
        if (n == null)
        {
            output += "()";
        }
        else {
            output += "(" + getString(n.left) + n.key + getString(n.right) + ")";
        }
        return output;
    }

    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for details.
     *
     * @return a multi-line string with the pretty ascii picture of the tree.
     */
    public String prettyPrintKeys() {
        String output = "";

        if (root == null)
        {
            return "-null\n";
        }

        output = prettyPrint(root, "");

        return output;
    }

    private String prettyPrint(Node node, String prefix){
        if (node == null) {
            return prefix + "-null\n";
        }
        //int depth = root.N - node.N;
        String line = "|";
        String space = " ";
        String output = "";

        output += prefix + "-" + node.key + "\n" ;
        output += prettyPrint(node.left, prefix + space + line);
        output += prettyPrint(node.right, prefix + space + space);

        return output;
    }

    /**
     * Deteles a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     *
     * @param key the key to delete
     */
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;

            //otherwise replace with PREDECESSOR
            Node temp = x;
            x = max(temp.left);
            //Change references so that the predecessor now points
            //to the children of the node we just deleted
            x.left = deleteMax(temp.left);
            x.right = temp.right;
        }
        //change size
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node max(Node n)
    {
        while (n.right != null)
        {
            n = n.right;
        }
        return n;
    }

    private Node deleteMax(Node n)
    {
        if (n.right == null)
        {
            return n.left;
        }
        n.right = deleteMax(n.right);
        n.N = 1 + size(n.left) + size(n.right);
        return n;
    }
}