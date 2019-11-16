import javax.management.ValueExp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph
{
    //ArrayVertex
    ArrayList<Vertex> vertexlist;
    //Actual Working
    int V;
    ArrayList<Edge> adjlist[];
    int[][] adjmatrix;
    HashMap<Integer,ArrayList<Edge>> map;
    //new option
    public Graph (File f)
    {

    }
    public Graph()
    {
        this.vertexlist = new ArrayList<Vertex>();
    }
    public Graph(ArrayList<Vertex> vertices)
    {
        this.vertexlist = vertices;
    }
    public Vertex getVertex(int num)
    {
        return this.vertexlist.get(num);
    }
    public Vertex addVertex(Vertex v)
    {
        boolean alreadyin = false;
        this.vertexlist.add(v);
        this.V ++;
        return v;
    }
    public void addEdgenew(Vertex V,Edge e)
    {
        boolean alreadyin = false;
        for(int i = 0; i < this.vertexlist.size(); i++)
        {
            if(vertexlist.get(i).equals(V))
            {
                vertexlist.get(i).addEdges(e);
            }
        }
        if(vertexlist.contains(V)) {

        }else System.out.println("Vertex befindet sich nicht im Graph");
    }
    public void addEdgeList(Vertex v, ArrayList<Edge> e)
    {
        boolean alreadyin = false;
        for(int i = 0; i < this.vertexlist.size(); i++)
        {
            if(vertexlist.get(i).equals(v))
            {
                vertexlist.get(i).addListOfEdge(e);
            }
        }
        if(vertexlist.contains(v)) {

        }else System.out.println("Vertex befindet sich nicht im Graph");
    }
    public void createAdjMatrix()
    {
        System.out.println(this.V);
        this.adjmatrix = new int[this.V][this.V];
        for(int i = 0; i < this.V; i++)
        {
            for(int j = 0; j < this.V; j++)
            {
                this.adjmatrix[i][j] = 0;
            }
        }
        for (Vertex v: this.vertexlist
             ) {
            for (Edge e: v.edges
                 ) {
                this.adjmatrix[e.first()][e.dest()] = 1;
                this.adjmatrix[e.dest()][e.first()] = 1;
            }
        }
    }
    public void printAdjMatrix()
    {
        boolean beginrow = true;
        System.out.print("    ");
        for(int i = 0; i< this.V;i++)
        {
            System.out.print("  V"+i);
        }
        System.out.println();
        for(int i = 0; i < this.V;i++)
        {
            System.out.print("V"+ i + ":  ");
            for(int j = 0; j <this.V; j++)
            {
                System.out.print(" "+ this.adjmatrix[i][j] + "  ");
            }
            System.out.println("");
        }
    }
    //old option
    public Graph(int V)
    {
        this.V = V;
        this.adjlist = new ArrayList[V];
        this.map = new HashMap<Integer,ArrayList<Edge>>();
        for(int i = 0; i < V; i++)
        {
            this.adjlist[i] = new ArrayList<>();
            this.addVertex(i);
        }
    }
    public void addVertex(int V)
    {
        this.map.put(V, new ArrayList<Edge>());
        this.V ++;
    }
    public static void addEdge(Graph g, int source, int dest)
    {
        Edge e = new Edge(source,dest);
        boolean alreadyin = false;
        for(int i = 0; i < g.adjlist[source].size(); i++)
        {
            if(g.adjlist[source].get(i).equals(e))
            {
                alreadyin = true;
            }
        }
        if(!alreadyin)
        {
            g.adjlist[source].add(e);
            System.out.println(""+g.adjlist[source].equals(e));
        }else
        {
            System.out.println("Edge already in there");
        }
    }
    public static void addEdgeMap(Graph g, int source, int dest)
    {
        Edge e = new Edge(source,dest);
        g.map.get(source).add(e);
    }
    public static void printGraph(Graph g)
    {
        for(int i = 0; i < g.adjlist.length; i++)
        {
            for(int j = 0; j < g.adjlist[i].size(); j++)
            {
                g.adjlist[i].get(j).print();
            }
            System.out.println();
        }
    }
    public static void main(String args[])
    {
        //Graph1
        Graph g = new Graph(5);
        addEdge(g,1,2);
        addEdge(g,1,2);
        addEdge(g,1,3);
        addEdge(g,2,2);
        addEdge(g,2,4);
        addEdge(g,3,4);
        addEdge(g,4,5);
        printGraph(g);
        //New Stuff with Edges and Vertices
        Edge e  = new Edge(1,2);
        Edge e2 = new Edge(1,2);
        System.out.println(e.equals(e2));
        //adding graph2
        Graph g2 = new Graph();
        //creating seperate vertex v
        Vertex v = new Vertex(1);
        g2.addVertex(v);
        //adding Edge for Vertex v
        g2.addEdgenew(v,new Edge(1,2));
        //adding anonym vertex and Edge for it (Vertex 2)
        g2.addVertex(new Vertex(2)).addEdges(new Edge(2,1));
        g2.addEdgenew(new Vertex(1),new Edge(1,2));
        Vertex v2 = new Vertex("A");
        g2.addVertex(v2).addEdges(new Edge(v2.number,1));
        ArrayList<Edge> edge = new ArrayList<>();
        edge.add(new Edge(2,2));
        edge.add(new Edge(2,3));
        edge.add(new Edge(2,1));
        g2.addEdgeList(g2.getVertex(1),edge);
        //First important Graph
        Graph g3 = new Graph();
        g3.addVertex(new Vertex(0)).addListOfEdge(new ArrayList<Edge>() {{
                                                      add(new Edge(0,1));
                                                      add(new Edge(0,2));
                                                  }});
        g3.addVertex(new Vertex(1)).addListOfEdge(new ArrayList<Edge>() {{
                                                      add(new Edge(1,2));
                                                      add(new Edge(1,0));
        }});
        g3.addVertex(new Vertex(2)).addListOfEdge(new ArrayList<Edge>() {{
            add(new Edge(2,0));
            add(new Edge(2,1));
        }});
        g3.addVertex(new Vertex(3)).addListOfEdge(new ArrayList<Edge>() {{
            add(new Edge(3,2));
            add(new Edge(3,0));
        }});
        g3.addVertex(new Vertex(4));
        g3.createAdjMatrix();
        g3.printAdjMatrix();
        System.out.print("userdir"+System.getProperty("user.home"));
        File file = new File("/home/dominik/IdeaProjects/Graphen/src/K5.txt");
        try {

            Scanner sc = new Scanner(file);
            boolean begin = true;
            int count = 1;
            while (sc.hasNextLine()) {
                if(begin)
                {
                    int i = sc.nextInt();
                    System.out.println(i);
                    begin = false;
                }
                if(count % 2 ==1)
                {
                    int e1 = sc.nextInt();
                    System.out.print(e1);
                    count ++;
                }
                else {
                    int e3 = sc.nextInt();
                    System.out.println(e3);
                    count++;
                    count = count %2;
                }
            }
            sc.close();
        }
        catch (FileNotFoundException p) {
            p.printStackTrace();
        }


        System.out.println(("Test"));

    }
}
