import java.io.File;
import java.util.ArrayList;

public class GraphTests {
    public static void main(String args[])
    {
        //Graph1
        //Graph g = new Graph(5);
       /* addEdge(g,1,2);
        addEdge(g,1,2);
        addEdge(g,1,3);
        addEdge(g,2,2);
        addEdge(g,2,4);
        addEdge(g,3,4);
        addEdge(g,4,5);
        printGraph(g);
        */
        //New Stuff with Edges and Vertices
        //Testing basic stuff:
        System.out.println("Testing some basics Graph Operations..... To see Basic functions are working");
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
            add(new Edge(2,3));
        }});
        g3.addVertex(new Vertex(3)).addListOfEdge(new ArrayList<Edge>() {{
            add(new Edge(3,3));
        }});
        g3.createAdjMatrix();
        g3.printAdjMatrix();
        System.out.println("test of first files:");
        System.out.print("userdir"+System.getProperty("user.home"));
        File file = new File("/home/dominik/IdeaProjects/Graphen/src/K5.txt");
        File file2 = new File("/home/dominik/IdeaProjects/Graphen/src/primkruskal.txt");
        File file3 = new File("/home/dominik/IdeaProjects/Graphen/src/tgraph.txt");
        Graph g6 = new Graph(file2,true);
        Graph g5 = new Graph(file,false);
        g5.createAdjMatrix();
        g5.printAdjMatrix();
        g6.createAdjMatrix();
        g6.printAdjMatrix();
        g5.createEdgeList();
        System.out.println("BFS für K5");
      //  g5.BFS(1);
        //BFS G6
        System.out.println("BFS für primkruskal");
       // g6.BFS(1);
        System.out.println("Hier beginnt DFS!");
        g6.DFS(0,3);
        System.out.println("_______________________________________TEST_______________________________");
        Graph g15 = new Graph(file3,true);
        g15.BFS(1);
        g15.saveGraphsearch("Testgraph");
        g15.DFS(0,3);
        g15.SSC();

        System.out.println("_____");

        //topological search
        Graph g7 = new Graph();
        g7.addVertex(new Vertex(0));
        g7.addVertex(new Vertex(1));
        g7.addVertex(new Vertex(2)).addListOfEdge(new ArrayList<Edge>() {{
            add(new Edge(2,3));
        }});
        g7.addVertex(new Vertex(3)).addListOfEdge(new ArrayList<Edge>() {{
            add(new Edge(3,1));
        }});
        g7.addVertex(new Vertex(4)).addListOfEdge(new ArrayList<Edge>() {{
            add(new Edge(4,1));
            add(new Edge(4,0));
        }});
        g7.addVertex(new Vertex(5)).addListOfEdge(new ArrayList<Edge>() {{
            add(new Edge(5,2));
            add(new Edge(5,0));
        }});
        //Prim Kruskal
        System.out.println("KruskalTEST_________________________________________________:");
        Graph g10 = new Graph(file2,true);
        g7.createEdgeList();
        g10.Kruskal();
        System.out.println("____");
        System.out.println("PrimTEST______________________________________________________");
        Graph g11 = new Graph(file2,true);
        g11.Prim();
        g11.saveGraph("Prim");
       //Shortest Path
        System.out.println("Bellmann");
        File fbellmann = new File("./src/bellmannford.txt");
        Graph g12 = new Graph(fbellmann,true);
        g12.createEdgeList();
        g12.bellmanFord(1);
        System.out.println("Floyd:");
        File floyd = new File("./src/FloydWarshall.txt");
        Graph g13 = new Graph(floyd,true);
        g13.floydWarshall();
        System.out.println("Dijkstra:");
        File dijkstra = new File("./src/dijkstra.txt");
        Graph g14 = new Graph(dijkstra,true);
        g14.dijkstra(1);

        //Big graph tests
        System.out.println("TEST SPIDER");
        /*File spider = new File("./src/spider.txt");
        Graph gspider = new Graph(spider,true);
        gspider.createEdgeList();
        gspider.BFS(1);
        gspider.saveGraphsearch("SpiderBFS");*/
       // gspider.Kruskal();
        File ggrid = new File("./src/grid.txt");
        Graph grid = new Graph(ggrid,true);
        //grid.bellmanFord(1);
        grid.BFS(1);
        grid.saveGraphsearch("gridBFS");
        grid.Kruskal();
        //Grid topo
        System.out.println("________________Grid DSF_________________");
        grid.DFS(0,3);
        System.out.println("__________________Grid Topsorted____________________________");
        grid.printTopsortedList();
        System.out.println("_________________________GRID SSC________________________________");
        grid.SSC();
        System.out.println("_______________________GRID Dijkstra");
        grid.dijkstra(1);
        System.out.println("_____________________GRID FloyDWarshall__________________");
        //grid.floydWarshall(); // Dauert ewig
        System.out.println("___________________________Grid Bellmann___________________");
        grid.bellmanFord(2400);

        // Dot output is enables for BFS, Prim, Kruskal and DFS








    }
}
