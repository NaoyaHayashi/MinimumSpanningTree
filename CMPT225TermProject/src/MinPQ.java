
public class MinPQ<Key extends Comparable<Key>> extends AbstractBinHeap<Key> {
	
	public MinPQ(int capacity){ 
		super(capacity);
	}
	public MinPQ(Key[] array){ 
		super(array); 
	}
	
	@Override
	// top(parent) shouldBeBelow bottom (child)
	// return true if top < bottom
	//        false otherwise
	protected boolean shouldBeBelow(int top, int bottom) {
		// In a min-heap the larger elements are at the bottom,
		// so array[top] should be below if it is larger than
		// array[bottom].
		
		// Check array[top] > array[bottom].
		return (array[top].compareTo(array[bottom]) > 0);
	}
}
