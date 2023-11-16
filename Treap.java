import java.util.Stack;
import java.util.Random;

/**
 * Eduardo Hernandez
 * CS 284 HW 4
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

public class Treap <E extends Comparable<E>>{

    // Node class
    private static class Node<E>{

        //data fields
        public E data; 
        public int priority;
        public Node<E> left;
        public Node<E> right;

        
        /**
         * Initializes left and right to null
         * constructor 
         * @param data
         * @param priority
         */
        public Node(E data, int priority){
            if(data != null){
            this.priority = priority;
            this.data = data;
            this.left = null;
            this.right = null;
            }
            else{
                throw new NullPointerException();
            }
        }
        /**
         * performs a right rotation
         * returns a reference to the root of the result.
         * @return
         */
        public Node<E> rotateRight(){
            if(left == null){
                return this;
            }
            Node<E> rigthRoot = left;
            Node<E> node = this;
            node.left = left.right; 
            rigthRoot.right = this;
            return rigthRoot;

        }
        /**
         * performs a left rotation
         * returns a reference to the root of the result.
         * @return
         */
        public Node<E> rotateLeft(){
            if(right == null){
                return this;
            }
            Node<E> leftRoot = right;
            right = right.left; 
            leftRoot.left = this;
            return leftRoot;

        }
        //converts the strings to be printed to the required output, displaying the key and its respective priority
        public String toString() {
			return "(key=" + data.toString() + ", priority=" + priority + ")";
		}
    }

    //data fields
    private Random priorityGenerator;
    private Node<E> root; 

    //constructor
    /**
     * Constructs a new Treap with a randomly generated priority using the generator
     */
    public Treap(){
        this.priorityGenerator = new Random(); 
        this.root = null; 
    }

    //constructor
    /**
     * Constructs a new Treap with the specified seed for the priority generator
     * @param seed
     */
    public Treap(long seed){
        this.root = null;
        this.priorityGenerator = new Random(seed); 
    }

    /**
    * Adds a new node with the specified key and a randomly generated priority to the Treap, 
    * maintaining the heap property
    * @param key
    * @return
    */
    public boolean add(E key){
        int priority = priorityGenerator.nextInt();
        return add(key, priority);

    }

    /**
     * This method adds a node to the treap
     * The method returns true, if a node with the key was successfully added to the treap. 
     * If there is already a node containing the
     * given key, the method returns false and does not modify the treap
     * @param key
     * @param priority
     * @return
     */
    public boolean add(E key, int priority){
        if(root == null){
            root = new Node(key, priority);
            return true;
        }
        Stack<Node<E>> addToTree = new Stack<Node<E>>();
        Node<E> newRoot = root;
        Node<E> newnode = new Node(key, priority);
        while(true){

            if(newRoot.data.compareTo(key) < 0 ){
                addToTree.push(newRoot);

                if(newRoot.right == null){
                    newRoot.right = newnode;
                    reheap(addToTree, newRoot.right);
                    break;
                }
                newRoot = newRoot.right;
            }
            else if(newRoot.data.compareTo(key)== 0){
                return false; 
            }
            else{
                addToTree.push(newRoot);
                if(newRoot.left == null){
                    newRoot.left =newnode;
                    reheap(addToTree, newRoot.left);
                    break;
                }
                newRoot = newRoot.left;
            }
        }
        return true;
    }
    /**
     * Performs a reheap operation on the given stack of ancestor nodes and the given new node,updating 
     * the Treap to maintain the heap property by rotating nodes as needed
     * @param addToTree
     * @param newNode
     */
    public void reheap(Stack<Node<E>> addToTree, Node<E> newNode) {

        while(!addToTree.empty()) {
            Node<E> parent = addToTree.pop();
            if(newNode.priority > parent.priority) {
            
                if(parent.data.compareTo(newNode.data) > 0){ //rotate right if the parent is greater than newnode
                    newNode = parent.rotateRight();
                }
                else if(parent.data.compareTo(newNode.data)< 0){ //rotate left if parent node is less than newnode
                    newNode = parent.rotateLeft();
                }
                    if(!addToTree.empty()) {
                
                        if(addToTree.peek().left == parent) {
                            addToTree.peek().left = newNode;
                        } else {
                            addToTree.peek().right = newNode;
                        }
                    }
                else {
                        root = newNode;
                     }
                    }
                }
            }   
    
    /**
     * Deletes the node with the given key from the given root node of the treap
     * If the key is not found in the Treap, returns the root node unchanged
     * @param root
     * @param key
     * @return
     */
    public Node<E> delete(Node<E> root, E key) {
        if (root == null) {
            return null; 
        }
        if (key.compareTo(root.data) < 0) {
            root.left = delete(root.left, key);

        } else if (key.compareTo(root.data) > 0) {
            root.right = delete(root.right, key);
            
        } else { 

            if (root.left == null) {
                root = root.right;

            } else if (root.right == null) {
                root = root.left;

            } else {
                if (root.right.priority < root.left.priority) {
                    root = root.rotateRight();
                    root.right = delete(root.right, key);

                } else {
                    root = root.rotateLeft();
                    root.left = delete(root.left, key);
                }
            }
        }
        return root;
    }

    /**
     * deletes the node with the given key from the treap and returns true. If
     * the key was not found, the method does not modify the treap and returns false.
     * @param key
     * @return
     */
    public boolean delete(E key){
        if(root == null){
            return false;
        }

    if(find(key)){
        root = delete(root, key);
        return true;
    }
    else{
        return false;
    }
    }

    /**
     * Finds a node with the given key in the treap and
     * returns true if it finds it and false otherwise
     * @param key
     * @return
     */
    public boolean find(E key) {
		if(key == null) {
			return false;
		}
		if(root == null) {
			return false;
		} else {
			return find(root, key);
		}
	}

    /**
     * Finds a node with the given key in
     * the treap rooted at root and returns true if it finds it and false otherwise
     * @param root
     * @param key
     * @return
     */
    private boolean find(Node<E> root, E key){
        if(root == null){
            return false;
        }

        if(root.data == key){
            return true;
        }

        else if(root.data.compareTo(key)>0){
            return find(root.left, key);
        }

        else{
            return find(root.right, key);
        }
    }

    /**
     * Carries out a preorder traversal of the tree and returns a repre sentation of the nodes as a string. 
     * this method was taken from canvas but slight modifications were made
     * @param current
     * @param level
     * @return
     */
    private StringBuilder toString(Node<E> current, int level){
        StringBuilder s = new StringBuilder();
        
        for(int i = 0; i < level; i++){
            s.append(" ");
        }
            if(current == null){
                s.append("null\n");
            }
            else{
                s.append(current.toString() + "\n");
                s.append(toString(current.left, level +1));
                s.append(toString(current.right, level + 1));
            }
        return s;
    }
    /**
     * converts to string 
     */
    public String toString(){
        return toString(root, 0).toString();
    }


   public static void main(String[] args) {
    
   }
}