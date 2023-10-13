package phoneBook;

public interface list<T> {

    public boolean isEmpty();
	public boolean isFull();
	public void findFirst();
	public void findNext();
	public boolean last();
	public T retreive();
	public void ubdate(T data);
	public void insert(T data);
	public void remove();
	
}
