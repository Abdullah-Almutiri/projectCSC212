package phoneBook;


public class LinkedListADT<T>  implements list<T>{
    private Node<T> head;
    private Node<T> current;
    private Node<T> previous;
    public int size;
    
    public int getSize() {
		return size;
	}
    // Return ture when the list empty.
    public boolean isEmpty() {
        return head == null;
    }

    // Retrun false always (A LinkedList does not have a fixed size).
    public boolean isFull() {
        return false;
    }

    // Rereferencing the current to the first node.
    public void findFirst() {
       current = head;
    }

    // Rereferencing the currnet to the next node.
    public void findNext()throws NullPointerException {
       current = current.getNext();
    }

    public void findpevious()throws NullPointerException {
    	current = current.getPrevious();
    }

    // return true when the current referencing at last node.
    public boolean last()throws NullPointerException {
      return current.getNext() == null;
    }

    public T retreive() {
      return current.getData();
    }

    // Update the element at the current.
    public void ubdate(T data)throws NullPointerException {
       current.setData(data);
    }

    // Resive data, and, insert it at the next node of the current node.
    public void insert(T data){
    	Node<T> newNode = new Node<T>(data);
    	if(isEmpty()) {
    		head = newNode;
    	}
    	else if(current==head){
    		newNode.setNext(head);
    		head.setPrevious(newNode);
    		head=newNode;
    	}
    	else{
    		Node<T>	previous = current.getPrevious();
    		newNode.setNext(current);
    		newNode.setPrevious(previous);
    		previous.setNext(newNode);
    		current.setPrevious(newNode);
    	}
    	current = newNode;
    	size++;
    	
    }
    public void insertAtEnd(T data) {
    	Node<T> newNode = new Node<T>(data);
    	current.setNext(newNode);
		newNode.setPrevious(current);
		size++;
    }

    // Deletes the element at the current.
    public void remove(){

        if (isEmpty())
            return;

        if(current == head) {
            head = head.getNext();
            head.setPrevious(null);
            }
        	
        else if(last()) {
        	Node<T> previous = current.getPrevious();
        	previous.setNext(null);
        	current.setPrevious(null);
        	current=previous; 
        }
      
        else{
            Node<T> previous = current.getPrevious();
            previous.setNext(current.getNext());
            current=current.getNext();
            current.setPrevious(previous);
        }
  
        size--;
    }

}
 
