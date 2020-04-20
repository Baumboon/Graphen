
import java.awt.image.DirectColorModel;
import java.io.*;
import java.io.File;
import java.util.*;
import org.jgrapht.alg.util.UnionFind;
public class Graph
{
    static final String DIRECT = "./";
    //ArrayVertex
    ArrayList<Vertex> vertexlist;
    //Actual Working
    int V;
    ArrayList<Edge> adjlist[];
    ArrayList<Edge> edgelist;
    int[][] adjmatrix;
    Vertex root;
    HashMap<Integer,ArrayList<Edge>> map;
    String graphCode;
    //new option
    public Graph (File f, boolean directed)
    {
        this.vertexlist= new ArrayList<Vertex>();
        try {
            this.edgelist = new ArrayList<Edge>();
            Scanner sc = new Scanner(f);
            boolean begin = true;
            boolean weighted = false;
            sc.nextLine();
            if(sc.nextLine().length() > 3)
            {
                weighted = true;
            }
            sc = new Scanner(f);
            int vert = sc.nextInt();
            for(int i = 1; i <= vert ;i++)
            {
                //Bestimmt die Anzahl der Knoten ( auch start)
                this.addVertex(new Vertex(i));
            }
            if(!directed)
            {
                if(weighted) {
                    while (sc.hasNext()) {
                        int j = sc.nextInt() - 1;
                        int w = sc.nextInt();
                        int k = sc.nextInt() - 1;
                        this.vertexlist.get(j).addEdges(new Edge(j, k, w));
                        this.vertexlist.get(k).addEdges(new Edge(k, j, w));
                    }
                }
                else
                {
                    while (sc.hasNext()) {
                        int j = sc.nextInt() - 1;
                        int k = sc.nextInt() - 1;
                        this.vertexlist.get(j).addEdges(new Edge(j, k));
                        this.vertexlist.get(k).addEdges(new Edge(k, j));
                }
            }
            }
            else {
                if(weighted) {
                    while (sc.hasNext()) {
                        //Start of the edges -1 because array indexing start at 0
                        int j = sc.nextInt() - 1;
                        int w = sc.nextInt();
                        int k = sc.nextInt() - 1;
                        this.vertexlist.get(j).addEdges(new Edge(j, k,w));
                    }

                }
                else
                {
                    while (sc.hasNext()) {
                        //Start of the edges -1 because array indexing start at 0
                        int j = sc.nextInt() - 1;
                        int k = sc.nextInt() - 1;
                        this.vertexlist.get(j).addEdges(new Edge(j, k));
                }
            }
        }
        }
        catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    }
    public Graph()
    {
        this.vertexlist = new ArrayList<Vertex>();
        this.edgelist = new ArrayList<Edge>();
    }
    public Graph(int vertexnumber)
    {
        this.edgelist = new ArrayList<Edge>();
        this.vertexlist = new ArrayList<Vertex>();
        for(int i = 0; i < vertexnumber; i++)
        {
            this.vertexlist.add(new Vertex(i));
        }
    }
    public Graph(int vertexnumber, Vertex root)
    {
        this.root = root;
        this.edgelist = new ArrayList<Edge>();
        this.vertexlist = new ArrayList<Vertex>();
        for(int i = 0; i < vertexnumber; i++)
        {
            this.vertexlist.add(new Vertex(i));
        }
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
        System.out.println(this.V+1);
        this.adjmatrix = new int[this.V+1][this.V+1];
        for(int i = 0; i < this.V+1; i++)
        {
            for(int j = 0; j < this.V+1; j++)
            {
                this.adjmatrix[i][j] = 0;
            }
        }
        for (Vertex v: this.vertexlist
             ) {
            for (Edge e: v.edges
                 ) {
                System.out.println(e.first());
                System.out.println(e.dest());
                this.adjmatrix[(e.first())][(e.dest())] = 1;
                this.adjmatrix[(e.dest())][(e.first())] = 1;
            }
        }
    }
    public void createAdjMatrixWeighted()
    {
        System.out.println(this.V+1);
        this.adjmatrix = new int[this.V+1][this.V+1];
        for(int i = 0; i < this.V+1; i++)
        {
            for(int j = 0; j < this.V+1; j++)
            {
                this.adjmatrix[i][j] = 0;
            }
        }
        for (Vertex v: this.vertexlist
        ) {
            for (Edge e: v.edges
            ) {
                System.out.println(e.first());
                System.out.println(e.dest());
                this.adjmatrix[(e.first())][(e.dest())] = e.weight;
               // this.adjmatrix[(e.dest())][(e.first())] = e.weight;
            }
        }
    }
    public int[][] copyAdjMatirx(int[][] matrix)
    {
        int[][] ergeb = new int[matrix[0].length][matrix.length];
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[0].length;j++)
            {
                ergeb[i][j] = matrix[i][j];
            }
        }
        return ergeb;
    }
    public void createEdgeList()
    {
        this.edgelist = new ArrayList<Edge>();
        for (Vertex v: this.vertexlist) {
            for (Edge e : v.edges) {
                this.edgelist.add(e);

            }

        }
    }
    public void createEdgelist2(ArrayList<Edge> e)
    {
        this.edgelist = e;
    }
    public void printAdjMatrix()
    {
        boolean beginrow = true;
        System.out.print("    ");
        for(int i = 0; i< this.V;i++)
        {
            System.out.print("  V"+(i+1));
        }
        System.out.println();
        for(int i = 0; i < this.V;i++)
        {
            System.out.print("V"+ (i+1) + ":  ");
            for(int j = 0; j <this.V; j++)
            {
                System.out.print(" "+ this.adjmatrix[i][j] + "  ");
            }
            System.out.println("");
        }
    }
    public void BFS(int vnumber)
    {
        for (Vertex v: this.vertexlist) {
            v.setUnVisited();
            v.setDistance(0);
        }
        this.vertexlist.get(vnumber).setVisited();
        this.vertexlist.get(vnumber).setDistance(0);
        Queue queueA = new LinkedList();
        queueA.add(this.vertexlist.get(vnumber));
        while(!queueA.isEmpty())
        {
           Vertex ver= (Vertex) queueA.remove();
           System.out.println(ver.number);
            for (Edge e: ver.edges) {
                if(!this.vertexlist.get(e.dest()).visited) {
                    this.vertexlist.get(e.dest()).setVisited();
                    this.vertexlist.get(e.dest()).setPredecessor(ver);
                    this.vertexlist.get(e.dest()).setDistance(this.vertexlist.get(this.vertexlist.indexOf(ver)).distance+1);
                    queueA.add(this.vertexlist.get(e.dest()));
                }

            }
            //System.out.println(this.vertexlist.indexOf(ver)+1);



            }

        }
        public void DFS(int vnumber)
        {
            int time = 0;
            for (Vertex v: this.vertexlist) {
                v.setUnVisited();
                v.distance = 0;
                v.setPredecessorNull();
                v.setFinishTime(0);

            }
            utilDFS(vnumber,time);
            System.out.println("test");

        }
        public void utilDFS(int vnumber,int time)
        {
            time = time+1;
            this.vertexlist.get(vnumber).setDistance(time);
            this.vertexlist.get(vnumber).setVisited();
            System.out.print(vnumber+1);
            for (Edge e:this.vertexlist.get(vnumber).edges) {
                if(!this.vertexlist.get(e.dest()).visited)
                {
                    this.vertexlist.get(e.dest()).setPredecessor(this.vertexlist.get(e.first()));
                    utilDFS(e.dest(),time);
                }
            }

            //System.out.println(time-1);
            time = time +1;
            this.vertexlist.get(vnumber).setFinishTime(time);
        }
        void topologicalSort()
        {
            Stack s = new Stack();
            for (Vertex v: this.vertexlist) {
                v.setUnVisited();
            }
            for(int i = 0; i < this.V; i++)
            {
                if(!this.vertexlist.get(i).visited)
                {
                  topoUtil(i,s);
                }
            }
            while (!s.isEmpty())
            {
                System.out.println(s.pop());
            }

        }
        void topoUtil(int vnumber, Stack s)
        {
            this.vertexlist.get(vnumber).setVisited();
            s.push(vnumber + 1 );
            for(Edge e: this.vertexlist.get(vnumber).edges)
            {
                if(!this.vertexlist.get(e.dest()).visited)
                {
                    topoUtil(e.dest(), s);
                }
            }
        }
        void SSCutil(int vnumber, Stack s)
        {
            this.vertexlist.get(vnumber).setVisited();
            for(Edge e : this.vertexlist.get(vnumber).edges)
            {
                if(! this.vertexlist.get(e.dest()).visited)
                {
                    SSCutil(e.dest(),s);
                }
            }
            s.push(vnumber);
        }
        Graph transpose()
        {
            Graph g = new Graph();
            for( int v = 0; v < this.V; v++)
            {
                g.addVertex(new Vertex(v));
            }
            for(int v = 0; v < this.V; v++)
            {
                for(Edge e : this.vertexlist.get(v).edges)
                {
                    g.addEdgenew(this.vertexlist.get(e.dest()),new Edge(e.dest(),e.first()));
                }
            }
            return g;
        }
        void SSC()
        {
            Stack s = new Stack();
            for(int i = 0; i < this.V; i++)
            {
                if(!this.vertexlist.get(i).visited) {
                    this.SSCutil(i, s);
                }
            }
            Graph g = this.transpose();
            g.setUnvisitedNodes();
            System.out.println("cut");
            while(!s.isEmpty())
            {
                int ver = ((int)s.pop());
                if(!g.vertexlist.get(ver).visited) {
                    g.utilDFS(ver, 0);
                    System.out.println();
                }
            }
        }
        void setUnvisitedNodes()
        {
            for(int i = 0; i < this.V; i++)
            {
                this.vertexlist.get(i).setUnVisited();
            }
        }
        void printEdgeList()
        {
            for(Edge e: this.edgelist)
            {
                System.out.println((e.first()+1) + " -----> " + (e.dest()+1));
            }
        }
        //Minimal spanning Tree Algorithmen
     Graph  Kruskal()
    {
      Graph sptree = new Graph(this.vertexlist.size());
      UnionFind<Vertex> unionFind = new UnionFind<>(new HashSet<>());
      for(Vertex v: this.vertexlist)
      {
          unionFind.addElement(v);
      }
      this.createEdgeList();
      this.edgelist.sort(new EWeightCompartor());
      System.out.println("__");
      for(Edge e : this.edgelist)
      {
          if(!unionFind.inSameSet(this.vertexlist.get(e.first()),this.vertexlist.get(e.dest())))
          {
              sptree.vertexlist.get(e.first()).addEdges(new Edge(e.first(),e.dest()));
              unionFind.union(this.vertexlist.get(e.first()),this.vertexlist.get(e.dest()));
          }
      }
      sptree.createEdgeList();
      sptree.saveGraph("Kruskal");
      sptree.printEdgeList();
      return sptree;
    }
    int primutil(int key[], Boolean minisptree[])
    {
        int min = Integer.MAX_VALUE;
        int min_ind = -1;
        for(int i = 0; i < this.vertexlist.size();i++)
        {
            if(minisptree[i] == false && key[i] < min)
            {
                min = key[i];
                min_ind = i;
            }
        }
        return min_ind;
    }
    //PRIM algorithmus
    void Prim()
    {
        ArrayList<Edge> elist = new ArrayList<>();
       /* Graph sptree = new Graph(this.vertexlist.size());
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        for(Vertex v: this.vertexlist)
        {
            if(v == this.root){
                queue.offer()
            }
            queue.offer(v)
        }
        return sptree;*/
       this.createAdjMatrixWeighted();
       this.printAdjMatrix();
       Graph sptree = new Graph(this.vertexlist.size());
       int p[] = new int[this.vertexlist.size()];
       int key[] = new int[this.vertexlist.size()];
       Boolean minisptree[] = new Boolean[this.vertexlist.size()];
       for(int i = 0; i < this.vertexlist.size();i++)
       {
           key[i] = Integer.MAX_VALUE;
           minisptree[i] = false;
       }
       key[0] = 0;
       p[0] = -1;
       for(int i = 0 ; i < this.vertexlist.size();i++)
       {
        int k = primutil(key,minisptree);
        minisptree[k] = true;

        for(int j = 0 ; j < this.vertexlist.size();j++)
        {
            if(this.adjmatrix[k][j] != 0 && minisptree[j] == false && this.adjmatrix[k][j] < key[j])
            {
                p[j] = k;
                key[j] = this.adjmatrix[k][j];
            }
        }
       }
       for(int i = 1; i < p.length; i++)
       {
           System.out.println((i+1) + " <-------> " +(p[i]+1));
           elist.add(new Edge(i,p[i]));
       }
       this.createEdgelist2(elist);
    }
    public void convertDot()
    {
      StringBuilder sb = new StringBuilder();
      sb.append("graph G {");
      for( Edge e : this.edgelist)
      {
          sb.append("\n\t").append((e.first()+1)).append("--").append((e.dest()+1)).append(";");
      }
      sb.append("\n}");
      this.graphCode = sb.toString();
    }
    public void writeIntoFile(String filepath, String text)
    {
        File f = new File(filepath);
        try(FileWriter fWriter = new FileWriter(f))
        {
            if(f.exists())
            {
                fWriter.append(text);
            }
            else
            {
                fWriter.write(text);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void saveGraph(String name)
    {
        this.convertDot();
        this.writeIntoFile(DIRECT + name +".dot",this.graphCode);
    }
    void bellmanFord(int source)
    {
        int dist[] = new int[this.vertexlist.size()];
        this.createEdgeList();
        for(Vertex v: this.vertexlist)
        {
            v.distance = Integer.MAX_VALUE;
        }
        this.vertexlist.get(source-1).distance = 0;
        for(Vertex v : this.vertexlist)
        {
            for(Edge e : v.edges)
            {
                int u = e.first();
                int k = e.dest();
                if(this.vertexlist.get(u).distance != Integer.MAX_VALUE && this.vertexlist.get(k).distance > e.weight + this.vertexlist.get(u).distance)
                {
                   this.vertexlist.get(k).distance = this.vertexlist.get(u).distance+e.weight;
                }


            }
        }
        for(Edge e: this.edgelist)
        {
            int s = e.first();
            int d = e.dest();
            if(this.vertexlist.get(s).distance != Integer.MAX_VALUE && this.vertexlist.get(d).distance > e.weight +  this.vertexlist.get(s).distance)
            {
                System.out.println("There is a negative cycle in the graph");
                return;
            }
        }
        for(Vertex v : this.vertexlist)
        {
            System.out.println("Vertex:" + (v.number) +" Distanz: "+ v.distance);
        }
    }
    void dijkstra(int source)
    {
        //initialize
        for(Vertex v: this.vertexlist)
        {
            v.distance = Integer.MAX_VALUE;
            v.predecessor = null;
            //test if dijkstra can be used
            for(Edge e : v.edges)
            {
                if(e.weight < 0)
                {
                    return;
                }
            }
        }
        this.vertexlist.get(source-1).distance = 0;
        PriorityQueue<Vertex> q = new PriorityQueue<Vertex>(this.V,new VertexComparator());
        for(Vertex v : this.vertexlist)
        {
            q.add(v);
        }


    }
    void floydWarshall()
    {
        this.createAdjMatrixWeighted();
        this.printAdjMatrix();
        int [][] adjdist = this.copyAdjMatirx(this.adjmatrix);
        System.out.println("test");
        //Initialization
        for(int i = 0; i < this.V; i++)
        {
            for(int j = 0; j < this.V; j++)
            {
                if(adjdist[i][j] == 0)
                {
                    adjdist[i][j] = Integer.MAX_VALUE/2;
                }
                if(i == j)
                {
                    adjdist[i][j] = 0;
                }
            }
        }


        for(int i = 0; i < this.V; i++)
        {
            for(int j = 0; j < this.V; j++)
            {
                for(int k= 0; k < this.V; k++)
                {
                    if( adjdist[j][i] + adjdist[i][k] < adjdist[j][k] )
                    {
                        adjdist[j][k] = adjdist[j][i] + adjdist[i][k];
                    }
                }
            }
            System.out.println("Durchlauf" + i);
            for(int j = 0;j < this.V; j++)
            {
                for(int k = 0; k < this.V; k++)
                {
                    System.out.print(adjdist[j][k] + " ");
                }
                System.out.println("");
            }
        }

    }
    //
    //old option
    /*
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
    }*/
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

    }
}
