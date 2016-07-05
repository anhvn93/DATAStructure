package edu.iastate.cs228.hw5;

/**
 * @author ANH NGUYEN
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Dijkstra {

    /**
     * First, computes a shortest path from a source vertex to
     * a destination vertex in a graph by using Dijkstra's algorithm.
     * Second, visits and saves (in a stack) each vertex in the path,
     * in reverse order starting from the destination vertex,
     * by using the map object pred.
     * Third, uses a StringBuilder object to generate the return String
     * object by poping up the vertices from the stack;
     * the vertices in the String object are in the right order.
     * Note that the get_index() method is called from a Graph.Vertex object
     * to get its oringinal integer name.
     *
     * @param G
     *          - The graph in which a shortest path is to be computed
     * @param source
     *          - The first vertex of the shortest path
     * @param dest
     *          - The last vertex of the shortest path
     * @return a String object with three lines (separated by a newline character)
     *         such that line 1 shows the length of the shortest path,
     *         line 2 shows the cost of the path,
     *         and line 3 gives a list of the vertices (in the path)
     *         with a space between adjacent vertices.
     *
     *         The contents of an example String object:
     *         Path Length: 5
     *         Path Cost: 4
     *         Path: 0 4 2 5 7 9
     *
     * @throws NullPointerException
     *           - If any arugment is null
     *
     * @throws RuntimeException
     *           - If the given source or dest vertex is not in the graph
     *
     */
	
    public static String Dijkstra(Graph G, Graph.Vertex source, Graph.Vertex dest)
    {
    	int maxInt = Integer.MAX_VALUE;	
    	Vpair<Graph.Vertex, Integer> temp;
    	HashSet<Graph.Vertex> vertexSet = new HashSet<Graph.Vertex>();
		Heap<Vpair<Graph.Vertex, Integer>> heap = new Heap<Vpair<Graph.Vertex, Integer>>();
		HashMap<Graph.Vertex, Graph.Vertex> hmVertex = new HashMap<Graph.Vertex, Graph.Vertex>();
		HashMap<Graph.Vertex, Integer> hm1 = new HashMap<Graph.Vertex, Integer>();
		

		for (Graph.Vertex f : G.get_vertices()) {
			if(f != null){
				hm1.put(f, maxInt);
				hmVertex.put(f, null);
		}
		}
		hm1.put(source, 0);
		hmVertex.put(source, null);
		heap.add(new Vpair<Graph.Vertex, Integer>(source, 0));

		while (!heap.isEmpty()) {
			temp = heap.removeMin();
			Graph.Vertex w = temp.getVertex();
			if (!vertexSet.contains(w)) {
				vertexSet.add(w);
			}
			for (Graph.Edge j : w.get_edges()) {
				Graph.Vertex v = j.to;
				Integer AltenateDistance = hm1.get(w) + j.weight;
				Integer VertexDistance = hm1.get(v);

				if (!vertexSet.contains(v) && VertexDistance > AltenateDistance) {

					hm1.put(v, AltenateDistance);
					hmVertex.put(v, w);
					heap.add(new Vpair<Graph.Vertex, Integer>(v, AltenateDistance));
				}
			}
		}
		int VeterxCost = 0;
		int Vertex_length = 0;
		String Graph_path = "";
		VeterxCost = hm1.get(dest);

		while (dest != null) {

			Graph_path = " " + dest.hashCode() + Graph_path;

			dest = hmVertex.get(dest);
			Vertex_length++;

		}

		Graph_path = ("Path Length : " + (Vertex_length-1) + "\n" + "Cost : " + VeterxCost + "\n" + "Path :" + Graph_path);

		return Graph_path;
	}


/**
 * A pair class with two components of types V and C, where
 * V is a vertex type and C is a cost type.
 */

private static class Vpair<V, C extends Comparable<? super C> > implements Comparable<Vpair<V, C>>
{
     private V  node;
     private C  cost;

     Vpair(V n, C c)
     {
       node = n;
       cost = c;
     }

     public V getVertex() { return node;}
     public C getCost() { return cost;}
     public int compareTo( Vpair<V, C> other )
     {
       return cost.compareTo(other.getCost() );
     }

     public String toString()
     {
       return "<" +  node.toString() + ", " + cost.toString() + ">";
     }

     public int hashCode()
     {
       return node.hashCode();
     }

     public boolean equals(Object obj)
     {
       if(this == obj) return true;
       if((obj == null) || (obj.getClass() != this.getClass()))
        return false;
       // object must be Vpair at this point
       Vpair<?, ?> test = (Vpair<?, ?>)obj;
       return
         (node == test.node || (node != null && node.equals(test.node)));
     }
}

}
