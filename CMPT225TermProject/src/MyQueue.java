
public class MyQueue<ItemType> {
	private DoubleLinkedList<ItemType> list;
	
	public MyQueue(){
		list = new DoubleLinkedList<ItemType>();
	}
	
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	public int size(){
		return list.length();
	}
	
	// Add an item. Same behavior as push.
	public void enqueue(ItemType item){
		list.insertFront(item);
	}
	
	// Remove a least recently added item. 
	// Different from pop (,which removes a most recently added item).
	public ItemType dequeue() throws NullPointerException{
		try{
			return list.removeEnd();
		}
		catch(NullPointerException e){
			throw e;
		}
	}
}
