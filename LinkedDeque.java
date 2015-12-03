// file: LinkedDeque.java
// author: Kevin Irace
// date: February 10, 2014
//
// This is an ADT for a Linked Double Ended Queue.
//

public class LinkedDeque<T> implements Deque<T> {
    
    // Instance variables.
    private Node left;
    private Node right;
    private int N;
    
    
    private class Node {
        T info;
        Node next;
        Node prev;
        
        private Node(T info, Node prev, Node next) { //node constructor
            this.info = info;
            this.next = next;
            this.prev = prev;
        }
        private T getInfo() { return this.info; }
        private Node getNext() { return this.next; }
        private Node getPrevious() { return this.prev; }
        
        private Node getFirst(){ //gets first element of linked nodes to the right. necessary to link left and right nodes at root
        Node first = this;
        
        while(first.getPrevious() != null){
            first = first.getPrevious();
        }
        
        return first;
        }
    
        private Node getLast(){ //gets last element of linked nodes to the left
            Node last = this; 
        
            while(last.getNext() != null){
                last = last.getNext();
            }
        
            return last;
        }
    }
    
    
    public LinkedDeque() { // A constructor of LinkedDeque.
        this.left = null;
        this.right = null;
        this.N = 0;
    }
   
    
    public void pushLeft(T info) {
        Node oldLeft = left;
        this.left = new Node(info, null, oldLeft); //updates left to new node
        this.N++;
        
        if(oldLeft != null){
            oldLeft.prev = this.left; //links next to previous
        }
           
        else if(this.right != null){
            Node rightFirst = this.right.getFirst();
            this.left.next = rightFirst; //links left and right
            rightFirst.prev = this.left; //
        }
    }
    
    public void pushRight(T info) {
        Node oldRight = right;
        this.right = new Node(info, oldRight, null); //updates right to new node
        this.N++;
        
        if( oldRight != null){
            oldRight.next = this.right; //links next to previous
        }
        else if(this.left != null ){
            Node leftLast = this.left.getLast();
            this.right.prev = leftLast; //links left and right
            leftLast.next = this.right; //
        }
    }
    
    public boolean isEmpty() { return this.N == 0; }

    public T popLeft() {
        if (this.isEmpty())
            throw new java.util.NoSuchElementException("Stack Underflow.");
        else {
            T info = this.left.getInfo();
            this.left = this.left.getNext();
            this.N--;
            return info;
        }
    }
    
     public T popRight() {
        if (this.isEmpty())
            throw new java.util.NoSuchElementException("Stack Underflow.");
        else {
            T info = this.right.getInfo();
            Node newRight = this.right.getPrevious();
            newRight.next = null;
            this.right = newRight;
            this.N--;
            return info;
        }
    }
    
    public int size(){ return N;}  
     
     
    public String toString() { 
        StringBuilder sb = new StringBuilder("stack: ");
        Node l = this.left;
        while (l != null) {
            sb.append(l.getInfo().toString() + " ");
            l = l.getNext();
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        LinkedDeque<String> myStack = new LinkedDeque<String>();
        //LinkedDeque<Integer> intStack = new LinkedDeque<Integer>();
        
        
        // Simple test
        System.out.println(myStack.toString());
        System.out.println("3 pushes to left");
        myStack.pushLeft("A");
        myStack.pushLeft("B");
        myStack.pushLeft("C");
        System.out.println(myStack.toString());
        System.out.println("2 pushes to right");
        myStack.pushRight("D");
        myStack.pushRight("E");
        System.out.println(myStack.toString());
        System.out.println("1 pop from left, 1 pop from right");
        myStack.popLeft();
        myStack.popRight();
        System.out.println(myStack.toString());
        System.out.println("3 pops from left");
        myStack.popLeft();
        myStack.popLeft();
        myStack.popLeft();
        System.out.println(myStack.toString());
    }
    
}
