
public class DisjointSet implements IDisjointSet {
	protected int[] parent;
	protected int[] rank;
	protected int   count;
	
	// This class implements UnionFind with
	//  - (weighted) union-by-rank
	//  - path compression
	// guaranteeing an O(a^(-1)(n)) complexity.
	
	public DisjointSet(int N)
	{
		count = N;
		
		parent = new int[N];
		for(int i = 0; i < N; i++) parent[i] = i;
		
		rank = new int[N];
		for(int i = 0; i < N; i++) rank[i] = 0;
	}
	
	public int count() { return count; }
	public boolean connected(int p, int q) { return find(p) == find(q); }
	
	public int find(int p)
	{
		// First find root of tree containing p.
		int root = p;
		while(root != parent[root])
			root = parent[root];
		// Then compress path.
		int next;
		while(p != parent[p]) {
			next = parent[p];
			parent[p] = root;
			p = next;
		}
		// Return the root.
		return root;
	}
	
	public void union(int p, int q)
	{
		int i = find(p);
		int j = find(q);
		
		// If p and q already belong to the same set,
		// nothing needs to be done.
		if (i == j) return;
		
		// We link the component with the smallest rank
		// to the one with the largest one. The rank
		// approximates the depth of the tree.
		if(rank[i] < rank[j])      { parent[i] = j; }
		else if(rank[j] < rank[i]) { parent[j] = i; }
		else
		{
			parent[j] = i;
			rank[i] = rank[i] + 1;
		}
	}
}
