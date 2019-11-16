import java.util.ArrayList;
import java.util.LinkedList;
import javafx.util.Pair;
import java.util.HashMap;
import java.util.List;
public class Graph1 {
    int V;
    LinkedList<Integer> adjlistArr[];
    LinkedList<Pair<Integer,Integer>> name[];
    ArrayList<Pair<Integer,Integer>> tuple;
    HashMap<Integer, ArrayList<Pair<Integer,Integer>>>  graphmap ;

    public Graph1(int V)
    {
        this.V = V;
        this.adjlistArr = new LinkedList[V];
        this.tuple = new ArrayList<Pair<Integer,Integer>>();


        for(int i = 0; i < V; i++) {
            this.adjlistArr[i] = new LinkedList<>();
            this.graphmap.put(i,new ArrayList<Pair<Integer,Integer>>());
        }


    }


    public static void addEdge(Graph1 g, int src, int dest)
    {
        g.adjlistArr[src].add(dest);
        g.adjlistArr[dest].add(src);
    }
    public static void addEdgeHashmap(Graph1 g, int src, int dest)
    {

    }
    public static void readEdges(Graph1 g)
    {

    }
    static void printGraph(Graph1 graph)
    {
        for(int v = 0; v < graph.V; v++)
        {
            System.out.println("Adjacency list of vertex "+ v);
            System.out.print("head");
            for(Integer pCrawl: graph.adjlistArr[v]){
                System.out.print(" -> "+pCrawl);
            }
            System.out.println("\n");
        }
    }
    public static void main(String args[])
    {
        System.out.println(""+ new Pair(0,0));
        Graph1 g = new Graph1(6);
        addEdge(g,1,2);
        addEdge(g,1,3);
        addEdge(g,2,2);
        addEdge(g,2,4);
        addEdge(g,3,4);
        addEdge(g,4,5);
        printGraph(g);

    }
}
