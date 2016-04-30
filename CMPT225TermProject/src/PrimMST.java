import java.util.Iterator;

public class PrimMST{
	private boolean[] marked; // MST vertices
	private DoubleLinkedList<Edge> mst; // MST edges
	private DoubleLinkedList<Edge> nonmst; // Non-MST edges
	private MinPQ<Edge> pq; // crossing (and not eligible) edges
	private double totalWeightSoFar;
	final int INITIAL_SIZE = 50;
	
	public PrimMST(EdgeWeightedGraph G){
		totalWeightSoFar = 0.0;
		pq = new MinPQ<Edge>(INITIAL_SIZE); 
		marked = new boolean[G.getNumOfV()];
		mst = new DoubleLinkedList<Edge>();
		nonmst = new DoubleLinkedList<Edge>();
		visit(G, 0); // assumes G is connected (see Exercise 4.3.22)
		
		while (!pq.isEmpty()){
			Edge e = pq.delete(); // Get lowest-weighted edge
			int v = e.either();
			int w = e.other(v); // edge from pq.
			if (marked[v] && marked[w]){
				// storing nonmst Edges
				nonmst.insertFront(e);
				continue; // Skip if not eligible
			}
			mst.insertFront(e); // Add edge to tree.
			totalWeightSoFar = totalWeightSoFar + e.getWeight(); // update the weight for mst
			
			// marking v and w (making them true)
			if (!marked[v]){ 
				visit(G, v); // Add vertex to tree
			}
			if (!marked[w]){ 
				visit(G, w); // (either v or w).
			}
		}
	}
	
	
	private void visit(EdgeWeightedGraph G, int v){ 
		// Mark v and add to pq all edges from v to unmarked vertices.
		marked[v] = true;
		for (Edge e : G.adj(v)){
			// this if statement considers the case for self loop
			if(e.either() == e.other(e.either())){
				pq.insert(e);
			}
			else if (!marked[e.other(v)]){
				pq.insert(e);
			}
		}
	}
	
	
	public Iterable<Edge> edges(){
		return mst;
	}
	
	
	public double getTotalweight(){
		// See Exercise 4.3.31.
		return totalWeightSoFar;
	}
	
	
	public void printInfo(){
		System.out.println("The MST {");
		Iterator<Edge> iterator1 = mst.iterator();
		while(iterator1.hasNext()){
			Edge e = iterator1.next();
			int v = e.either();
			int w = e.other(v);
			double weight = e.getWeight();
			System.out.println(v + " -- " + w + "  " + weight);
		}
		System.out.println("}");
	}
	
	public String MSTinDotFormat(){
		String str = "graph G {\n";
		Iterator<Edge> iterator1 = nonmst.iterator();
		// create a String representing non-mst edges
		while(iterator1.hasNext()){
			Edge e = iterator1.next();
			int v = e.either();
			int w = e.other(v);
			double weight = e.getWeight();
			str = str + v + " -- " + w + " [label=" + weight + "];\n";
		}
		str = str + "edge [color=red];\n"; // this line makes mst-edges red
		Iterator<Edge> iterator2 = mst.iterator();
		// String representing mst edges
		while(iterator2.hasNext()){
			Edge e = iterator2.next();
			int v = e.either();
			int w = e.other(v);
			double weight = e.getWeight();
			str = str + v + " -- " + w + " [label=" + weight + "];\n";
		}
		str = str + "}";
		return str;
	}
}