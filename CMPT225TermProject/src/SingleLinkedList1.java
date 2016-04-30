import java.util.Iterator;

//--------------------------------------------------------------------------------------------------
// SingleLinkedList1 can insert at or remove from the front of a list.
// SingleLinkedList1 can work only as Stack.
//--------------------------------------------------------------------------------------------------
public class SingleLinkedList1<ItemType> implements Iterable<ItemType>{
		
	private class Node{
		ItemType e;
		Node next; 
		
		private Node(){
			e = null;
			next = null;
		}
	}
	
	// instance variables
	// this node acts like a 'head' of a list
	private Node first;
	// 'size' keeps track of the number of Nodes(elements) of a linked list
	private int size;
	
	
	//--------------------------------------------------------------------------------------------------
	// SingleLinkedList2: Construct an object without parameters (Default Constructor)
	//--------------------------------------------------------------------------------------------------
	public SingleLinkedList1(){
		first = null;
		size = 0;
	}
	
	
	//--------------------------------------------------------------------------------------------------
	// SingleLinkedList1(ItemType e): Construct an object with an edge
	//--------------------------------------------------------------------------------------------------
	public SingleLinkedList1(ItemType anotherItem){
		first = new Node();
		first.e = anotherItem;
		size = 1;
	}
	

	//--------------------------------------------------------------------------------------------------
	// void insertFront(ItemType item): Insert a Node with an item at the front of a list
	//--------------------------------------------------------------------------------------------------
	public void insertFront(ItemType anotherItem){
		// increment the size
		size++;
		
		// Save the previous first
		Node oldfirst = first;
		
		// Create the new node
		Node newfirst = new Node();
		newfirst.e = anotherItem;
		newfirst.next = oldfirst;
		
		// Set the new node as the first node
		first = newfirst;
	}
	
	
	//--------------------------------------------------------------------------------------------------
	// ItemType removeFront(): Remove the Node at the front of a list, and return the ItemType of the Node
	// Note: This function possibly throws a NullPointerException
	//--------------------------------------------------------------------------------------------------
	public ItemType removeFront() throws NullPointerException{
		/* TODO: this operation crashes if the list is empty; consider
		 * adding some error checking to make sure that does not happen.
		 */
		if(first == null){
			throw new NullPointerException("ERROR: Can't remove an element from an empty list!!");
		}
		
		// decrement the size
		size--;
		
		// Save the previous first
		Node oldfirst = first;
		
		// Follow the first's node (possibly empty)
		// and set the first to that pointer
		first = oldfirst.next;
		
		// Return the value of old first 
		return oldfirst.e;
	}
	
	
	//--------------------------------------------------------------------------------------------------
	// int length(): Return the length of the list
	//--------------------------------------------------------------------------------------------------
	public int length(){
		return size;
	}
	
	
	
	
	
	// *************
	// Iterator code
	// *************
	
	public Iterator<ItemType> iterator(){ 
		return new SLL4Iterator(); 
	}
	
    private class SLL4Iterator implements Iterator<ItemType>
    {
        private Node current = first;
        public boolean hasNext() { return (current != null); }
        public void remove() { /* we don't (yet?) allow arbitrary deletion */ }
        public ItemType next()
        {
            ItemType item = current.e; // extract the item value
            current = current.next;        // move the iterator to the next item
            return item;
        }
    }


}
