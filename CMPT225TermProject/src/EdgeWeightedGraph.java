import java.util.Iterator;


public class EdgeWeightedGraph {
	// TODO: implement following textbook p611.
	
	private int numOfVertices; // number of vertices
	private int numOfEdges; // number of edges
	private SingleLinkedList1<Edge>[] adj; // adjacency lists
	
	@SuppressWarnings("unchecked")
	// The first Constructor: parameter is type of int, which represents number of vertices
	public EdgeWeightedGraph(int V){
		numOfVertices = V;
		numOfEdges = 0;
		adj = (SingleLinkedList1<Edge>[]) new SingleLinkedList1[V];
		for (int v = 0; v < V; v++){
			adj[v] = new SingleLinkedList1<Edge>();
		}
	}
	
	// The Second Constructor: parameter is type of In
	public EdgeWeightedGraph(In in){
		// See Exercise 4.3.9.
		this(in.readNextInt());
		numOfEdges = in.readNextInt();
		
		for(int i=0; i<numOfEdges; i++){
			int firstVertex = in.readNextInt();
		    int secondVertex = in.readNextInt();
		    double weight = in.readNextDouble();
		    Edge anEdge = new Edge(firstVertex, secondVertex, weight);
		    
		    // if the two vertices point to the same vertex, only one edge is added
		    if(firstVertex == secondVertex){
		    	adj[firstVertex].insertFront(anEdge);
		    }
		    // otherwise, two edges are added
		    else{
		    	adj[firstVertex].insertFront(anEdge);
		    	adj[secondVertex].insertFront(anEdge);
		    }
		}
		
	}
	
	
	public int getNumOfV(){ 
		return numOfVertices; 
	}
	
	
	public int getNumOfE(){
		return numOfEdges;
	}
	
	
	public void addEdge(Edge anotherEdge){
		int v = anotherEdge.either();
		int w = anotherEdge.other(v);
		adj[v].insertFront(anotherEdge);
		adj[w].insertFront(anotherEdge);
		numOfEdges++;
	}
	
	
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	
	
	public Iterable<Edge> edges(){
		// See page 609.
		SingleLinkedList1<Edge> b = new SingleLinkedList1<Edge>();
		for (int v = 0; v < numOfVertices; v++){
			for (Edge e : adj[v]){
				if (e.other(v) >= v){ /////
					b.insertFront(e);
				}
			}
		}
		return b;
	}
	
	
	public String toString(){
		String str = "graph G {\n";
		
		for(int i=0; i<numOfVertices; i++){
			Iterator<Edge> iterator1 = adj[i].iterator();
			while(iterator1.hasNext()){
				Edge e = iterator1.next();
				if((i <= e.either()) && (i <= e.other(e.either()))){
					str = str + e.either() + " -- " + e.other(e.either()) + " [label=" + e.getWeight() + "];\n";
				}
			}
		}
		str = str + "}";
		return str;
	}
}
