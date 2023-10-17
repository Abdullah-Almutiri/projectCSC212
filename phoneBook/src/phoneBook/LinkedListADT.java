package phoneBook;


public class LinkedListADT<T>  {
    private Node<T> head;
    private Node<T> current;
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
	
    // Rereferencing the currnet to the previous node.
    public void findPrevious()throws NullPointerException {
    	current = current.getPrevious();
    }

    // return true when the current referencing at last node.
    public boolean last()throws NullPointerException {
      return current.getNext() == null;
    }
	
    // Retrieve the current element.
    public T retreive() {
      return current.getData();
    }

    // Update the current element.
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
	
    // Adds a new element at the end of the list.
    public void insertAtEnd(T data) {
    	Node<T> newNode = new Node<T>(data);
    	current.setNext(newNode);
		newNode.setPrevious(current);
		current = newNode;
		size++;
    }

    // Deletes the element at the current.
    public void remove(){
    	//There is nothing to remove in this case
        if (isEmpty())
            return;

        
        //If the element is the first
        if(current == head) {
        	//If the last has more than 1 element
        	if(!last()) {
        		head=head.getNext();
        		head.setPrevious(null);
        	}
        	
        	//If the list has only 1 element
        	else {
        		head=head.getNext();
        	}
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
 
