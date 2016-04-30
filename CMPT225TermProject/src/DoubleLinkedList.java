
import java.util.Iterator;

public class DoubleLinkedList<ItemType> implements Iterable<ItemType> {
		
	private class DoubleNode{
		ItemType value;
		DoubleNode next; 
		DoubleNode previous;
	}
	
	private DoubleNode first;
	private DoubleNode last;
	private int size;
	
	public DoubleLinkedList(){
		first = null;
		last = null;
		size = 0;
	}
	
	public boolean isEmpty(){
		return (first == null);
	}
	
	public void insertFront(ItemType item){
		// Save the previous first
		DoubleNode oldfirst = first;
		
		// Create the new node
		DoubleNode newfirst = new DoubleNode();
		newfirst.value = item;
		newfirst.next = oldfirst;
		
		// If oldfirst is null, oldfirst.previous cause runtime error
		// This happens only if insert an element from an empty list
		if(oldfirst == null){
			last = newfirst;
		}
		else{
			oldfirst.previous = newfirst;
		}
		
		// Set the new node as the first node
		first = newfirst;
		newfirst.previous = first;
		size++;
	}
	
	public ItemType removeFront() throws NullPointerException{
		if(size <= 0){
			throw new NullPointerException("ERROR: Can't remove an element from an empty list!! So, nothing was done.");
		}
		else if(size == 1){
			ItemType val = first.value;
			first = null;
			last = null;
			size--;
			return val;
		}
		else{
			// Save the previous first
			DoubleNode oldfirst = first;
		
			// Follow the first's node (possibly empty)
			// and set the first to that pointer
			first = oldfirst.next;
			first.previous = first;
			size--;
			
			// Return the value of old first 
			return oldfirst.value;
		}
	}
	
	
	public void insertEnd(ItemType item){
		// case: inserting an element to an empty list, so reusing insertFront because both do exactly the same in this case
		if(size <= 0){
			insertFront(item);
		}
		else{
			last.next = new DoubleNode();
			last.next.value = item;
			
			DoubleNode oldlast = last;
			last = last.next;
			last.previous = oldlast;
			size++;
		}
	}
	
	public ItemType removeEnd() throws NullPointerException{
		if(size <= 0){
			throw new NullPointerException("ERROR: Can't remove an element from an empty list!! So, nothing was done.");
		}
		else if(size == 1){
			return removeFront();
		}
		else{
			DoubleNode oldlast = last;
			last = last.previous;
			last.next = null;
		
			size--;
			return oldlast.value;
		}
	}
		
	public int length(){
		return size;
	}
	
	public ItemType seeFirstValue() throws NullPointerException{
		if(first == null){
			throw new NullPointerException("ERROR: Can't see an element from an empty list!! So, nothing was done.");
		}
		else{
			return first.value;
		}
	}
	
	public ItemType seeLastValue() throws NullPointerException{
		if(last == null){
			throw new NullPointerException("ERROR: Can't see an element from an empty list!! So, nothing was done.");
		}
		else{
			return last.value;
		}
	}
	
	
	public Iterator<ItemType> iterator(){
		return new DLLIterator();
	}
	
    private class DLLIterator implements Iterator<ItemType>{
        private DoubleNode current = first;
        
        public boolean hasNext(){
        	return (current != null);
        }
        public void remove(){ 
        	/* we don't (yet?) allow arbitrary deletion */ 
        }
        public ItemType next(){
            ItemType item = current.value; // extract the item value
            current = current.next;        // move the iterator to the next item
            return item;
        }
    }
	
	
}
