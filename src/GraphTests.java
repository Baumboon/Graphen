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
        System.out.println("test");
        g3.BFS(0);
        System.out.print("userdir"+System.getProperty("user.home"));
        File file = new File("/home/dominik/IdeaProjects/Graphen/src/K5.txt");
        File file2 = new File("/home/dominik/IdeaProjects/Graphen/src/primkruskal.txt");
        Graph g6 = new Graph(file2,true);
        Graph g5 = new Graph(file,false);
        g5.createAdjMatrix();
        g5.printAdjMatrix();
        g6.createAdjMatrix();
        g6.printAdjMatrix();
        g5.createEdgeList();
        System.out.println("BFS für K5");
        g5.BFS(0);
        //BFS G6
        System.out.println("BFS für primkruskal");
        g6.BFS(0);
        System.out.println("Hier beginnt DFS!");
        g6.DFS(0);


        System.out.println("_____");


        g3.DFS(2);
        System.out.println("_______________");
        g5.DFS(0);
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
        g7.createAdjMatrix();
        g7.printAdjMatrix();
        g7.DFS(5);
        g7.topologicalSort();
        Graph g8 = g7.transpose();
        g8.createAdjMatrix();
        g8.printAdjMatrix();
        System.out.println("________");
        Graph g9 = new Graph();
        g9.addVertex(new Vertex(0)).addListOfEdge(new ArrayList<Edge>() {{
            add(new Edge(0,2));
            add(new Edge(0,3));
        }});
        g9.addVertex(new Vertex(1)).addListOfEdge(new ArrayList<Edge>() {{
            add(new Edge(1,0));
        }});
        g9.addVertex(new Vertex(2)).addListOfEdge(new ArrayList<Edge>() {{
            add(new Edge(2,1));
        }});
        g9.addVertex(new Vertex(3)).addListOfEdge(new ArrayList<Edge>() {{
            add(new Edge(3,4));
        }});
        g9.addVertex(new Vertex(4));
        g9.SSC();
        System.out.println("cut");
        //Prim Kruskal
        Graph g10 = new Graph(file2,true);
        g7.createEdgeList();
        g10.Kruskal();
        System.out.println("____");
        Graph g11 = new Graph(file2,true);
        g11.Prim();
        g11.saveGraph("Prim");
       /* g11.addVertex(new Vertex(0)).addListOfEdge(new ArrayList<Edge>() {{
            add(new Edge(0,1,4));
            add(new Edge(0,7,8));
        }});
        g11.addVertex(new Vertex(1)).addListOfEdge(new ArrayList<Edge>() {{
            add(new Edge(1,0,4));
            add(new Edge(0,7,8));
        }});*/
       //Shortest Path
        File fbellmann = new File("./src/bellmannford.txt");
        Graph g12 = new Graph(fbellmann,true);
        g12.createEdgeList();
        g12.bellmanFord(1);
        System.out.println("test");
        File floyd = new File("./src/FloydWarshall.txt");
        Graph g13 = new Graph(floyd,true);
        g13.floydWarshall();
       /* File spider = new File("./src/spider.txt");
        Graph gspider = new Graph(spider,true);
        gspider.createEdgeList();
        gspider.Kruskal();
        File ggrid = new File("./src/grid.txt");
        Graph grid = new Graph(ggrid,true);
        grid.bellmanFord(1);*/






    }
}
