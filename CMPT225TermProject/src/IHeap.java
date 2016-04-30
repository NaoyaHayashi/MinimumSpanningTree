
public interface IHeap<Key extends Comparable<Key>> {
	void insert(Key v);
	Key delete();
	boolean isEmpty();
	int size();
}
