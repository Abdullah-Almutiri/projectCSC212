package phoneBook;


public class LinkedListADT<T>  implements list<T>{
    private Node<T> head;
    private Node<T> current;
    

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
      
       if(isEmpty())
        current = head = newNode;

       newNode.setNext(current.getNext());
       current.setNext(newNode);
       current = newNode;
    }

    // Deletes the element at the current.
    public void remove(){

        if (isEmpty())
            return;

        if(current == head)
            head = head.getNext();
      
        else{
            Node<T> tempNode = head;

            while(tempNode.getNext() != current){
                tempNode.setNext(tempNode.getNext());
            }

            tempNode.setNext(current.getNext());
        }
  
        if(last())
          current = head;
        
        else
          current = current.getNext();
        
    }

}
 