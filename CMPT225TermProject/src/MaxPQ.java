public class MaxPQ<KeyType extends Comparable<KeyType>> extends AbstractBinHeap<KeyType> {

	public MaxPQ(int capacity) { super(capacity); }
	public MaxPQ(KeyType[] array) { super(array); }
	
	@Override
	protected boolean shouldBeBelow(int top, int bottom) {
		// In a max-heap the smaller elements are at the bottom,
		// so array[top] should be below if it is smaller than
		// array[bottom].
		
		// Check array[top] < array[bottom].
		return array[top].compareTo(array[bottom]) < 0;
	}
}