import java.util.NoSuchElementException;
import java.util.Iterator;


public class ListQueue<E>{
    /*
     * Eduardo Hernandez
     * CS 284 Section B
     * I pledge my Honor that I have abided by the Stevens Honor System.
     */

    //Inner class Node<E>
    public static class Node<E>{

        //fields declaration
        private E data; 
        private Node<E> next; 
        private int priority;
        
        /*
        Constructor Node. This constructor does not take a priority. Task is assigned 
        as low priority 
        */
        public Node(E dataItem){
            this.next = null;
            this.data = dataItem;
            this.priority = Integer.MAX_VALUE;
            
        }

        /*
         *  a constructor that creates a node holding dataItem, with priority as the
         *  priority of the task.
         */
        public Node(E dataItem, int priority){
            data = dataItem;
            next = null;
            this.priority = priority;
        }
        /*
         *  a constructor that creates a node holding dataItem, with
         *  next as next and priority as the priority of the task
         */
        public Node(E dataItem, Node<E> next, int priority){
            data = dataItem; 
            this.next = next;
            this.priority = priority; 
        }
        //getter
        public E getData(){
            return data; 
        }
        //getter
        public int getPriority(){
            return priority;
        }
        //getter
        public Node<E> getNext(){
            return next; 
        }
    }
    //Inner class Iter
     public class Iter implements Iterator<E>{

        //fields declaration
        private Node<E> next = front; 

        //returns true if the next node is not null
        public boolean hasNext(){
            if(next == null){
                return false;
            }
            return true; 
        }
        /*
         * Returns the data stored in the next node and updates the next node
         * will throw exception if there is no next node
         */
        public E next(){

            if(next == null){
                throw new NoSuchElementException();
            }
            Node<E> curr = next; 
            next = curr.next;
            return curr.getData();
        }
        /*
         * throws exception
         */
        public void remove(){
            throw new UnsupportedOperationException();
        }
        /*End of Iter class */
    }

    //Fields declaration for listQueue<E>
    private Node<E> front; 
    private int size; 

    //Creates an empty single list representing the priority queue
    public ListQueue(){ 
        front = null;
        size = 0;
    }
    //crates a one element single linked list representing the priority queue
    public ListQueue(Node<E> first){
        front = first;
        size = 1;
    }

    //getter
    public Node<E> getFront(){
        return front; 
    }
    
    //setter
    public void setFront(Node<E> front){
        this.front = front;
    }

    //getter
    public int getSize(){
        return size; 
    }
   //returns the information at the front of the queue
    public E peek(){
        if(front == null){
            return null; 
        }
        return front.getData();
    }
     /*
     * adds an item to certain position according to its priority
     * if the priority is the same, it will be added after the existing one
     * throws exception if the item sent to the method is null
     */
     public boolean offer(E item, int priority) {
        if (item == null) {
            throw new NullPointerException();
        }
    
         else {
            if(front == null){
                front = new Node<>(item, priority);
            }else{

                Node<E> current = front;
                Node<E> mynode = new Node<>(item, priority);
                
            while (current.next != null && priority >= current.next.priority) {
                    current = current.next;
                }
                if( front.priority > priority){
                    Node<E> node2 = front;
                    front = mynode;
                    mynode.next = node2;
                }else{
                    mynode.next = current.next;
                    current.next = mynode;
                }
            }
                size++;
                return true;
            }
    }
        
    //adds item to the end of the queue. It will always return true unless the item is null
    public boolean addRear(E item){
        if(item == null){
            throw new NullPointerException();
        }
        Node<E> newNode = new Node<E>(item);
        if(front == null){
            front = newNode; 
        }
        else {
            Node<E> current = front;
            while(current.next != null){
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }
    
    //returns the data at the front of the queue and removes it from the queue
    public E poll(){
        E item = peek();
        if(item == null){
            throw new NullPointerException();
        }
        if(size == 1){
            front = null;
        }
        else{
            front = front.next;
        }
        size--;
        return item;
    }
    //takes a node to be removed and removes it from the queue
    public boolean remove(Node<E> tobeRemoved){

        if(front.getData() == tobeRemoved.getData()){
            front = front.next;
            size--;
            return true;
        }
        Node<E> current = front;
        while(current.next != null){
            if(current.next.getData() == tobeRemoved.getData()){
                current.next = tobeRemoved.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false; 
    }
    //iterator method
    public Iterator<E> iterator(){
        return new Iter();
    }
}
