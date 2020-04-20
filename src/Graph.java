
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
    ArrayList<Vertex> searchlist;
    ArrayList<Edge> adjlist[];
    ArrayList<Edge> edgelist;
    int[][] adjmatrix;
    Vertex root;
    Stack s;
    ArrayList<Vertex> topsortedList;
    int time;
    Vertex sVertex;
    HashMap<Integer,ArrayList<Edge>> map;
    String graphCode;
    //new option
    public Graph (File f, boolean directed)
    {
        this.vertexlist= new ArrayList<Vertex>();
        this.s = new Stack();
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
    public void printTopsortedList()
    {
        for(Vertex v : this.topsortedList)
        {
            System.out.println("Vertex : " + v.number );
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
        this.searchlist = new ArrayList<Vertex>();
        for (Vertex v: this.vertexlist) {
            v.setUnVisited();
            v.setDistance(0);
        }
        this.vertexlist.get(vnumber).setVisited();
        this.vertexlist.get(vnumber).setDistance(0);
        Queue queueA = new LinkedList();
        queueA.add(this.vertexlist.get((vnumber-1)));
        while(!queueA.isEmpty())
        {
           Vertex ver= (Vertex) queueA.remove();
           System.out.println(ver.number);
            this.searchlist.add(this.vertexlist.get((ver.number-1)));
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
        public void DFS(int vnumber, int goal)
        {
            this.time = 0;
            this.searchlist = new ArrayList<Vertex>();
            this.topsortedList = new ArrayList<Vertex>();
            for (Vertex v: this.vertexlist) {
                v.setUnVisited();
                v.distance = 0;
                v.setTime(0);
                v.setPredecessorNull();
                v.setFinishTime(0);

            }
            utilDFS(vnumber,goal);
            System.out.println("test");
           if(sVertex != null)
           {
               System.out.println("Result DFS: From End to Start");
               Vertex v = sVertex;
               System.out.println("ID:" + (v.number-1) + ", Time: " + v.time + ", FinishTime:" + v.finishtime);
               while(v.getPreddessor() != null)
               {
                   v = v.getPreddessor();
                   System.out.println("ID: "+ (v.number-1) + ", Time: "+ v.time + ", FinishTime:" + v.finishtime);
               }
           }
        }
        public void utilDFSDot(int vnumber,int time)
        {
            this.time = time+1;
            this.vertexlist.get(vnumber).setTime(time);
            this.vertexlist.get(vnumber).setVisited();
            System.out.print(vnumber+1);
            this.searchlist.add(this.vertexlist.get(vnumber));
            for (Edge e:this.vertexlist.get(vnumber).edges) {
                if(!this.vertexlist.get(e.dest()).visited)
                {
                    this.vertexlist.get(e.dest()).setPredecessor(this.vertexlist.get(e.first()));
                    utilDFSDot(e.dest(),time);
                }
            }

            //System.out.println(time-1);
            time = time +1;
            this.vertexlist.get(vnumber).setFinishTime(time);
        }
    public void utilDFS(int vnumber,int goal)
    {
        this.time++;
        this.vertexlist.get(vnumber).setVisited();
        this.vertexlist.get(vnumber).setTime(time);
        for (Edge e:this.vertexlist.get(vnumber).edges) {
            if(!this.vertexlist.get(e.dest()).visited)
            {
                this.vertexlist.get(e.dest()).setPredecessor(this.vertexlist.get(e.first()));
                utilDFS(e.dest(),goal);
            }
        }
        s.push(vnumber);

        //System.out.println(time-1);
        this.time++;
        this.vertexlist.get(vnumber).setFinishTime(time);
        topsortedList.add(0,this.vertexlist.get(vnumber));
        if(vnumber == goal)
        {
            sVertex = this.vertexlist.get(goal);
        }
    }


        public Graph transpose()
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
        public void SSC()
        {
            this.DFS(1,2);
            Graph g = this.transpose();
            g.setUnvisitedNodes();
            System.out.println("cut");
            ArrayList<ArrayList<Vertex>> sCCs = new ArrayList<ArrayList<Vertex>>();
            while(!this.s.isEmpty())
            {
                int ver = ((int)this.s.pop());
               ArrayList<Vertex> sCC = new ArrayList<Vertex>();
                if(!g.vertexlist.get(ver).visited) {
                    g.SSCutiity(ver, sCC);
                    sCCs.add(sCC);
                    System.out.println();
                }
            }
            System.out.println("The result is:");
            for(ArrayList<Vertex> connected : sCCs)
            {
                System.out.println("__________________________");
                for(Vertex v : connected)
                {
                    System.out.println("Ver: " + v.number);
                }
            }

        }
        private void SSCutiity(int vnumber, ArrayList<Vertex> sCC)
        {
            if(!this.vertexlist.get(vnumber).visited)
            {
                this.vertexlist.get(vnumber).setVisited();
                for(Edge e: this.vertexlist.get(vnumber).edges)
                {
                    if(!this.vertexlist.get(e.dest()).visited)
                    {
                        SSCutiity(e.dest(),sCC);
                    }
                }
                sCC.add(this.vertexlist.get(vnumber));
            }
        }
        public void setUnvisitedNodes()
        {
            for(int i = 0; i < this.V; i++)
            {
                this.vertexlist.get(i).setUnVisited();
            }
        }
       public void printEdgeList()
        {
            for(Edge e: this.edgelist)
            {
                System.out.println((e.first()+1) + " -----> " + (e.dest()+1));
            }
        }
        //Minimal spanning Tree Algorithmen
    public Graph  Kruskal()
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
    public int primutil(int key[], Boolean minisptree[])
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
    public void Prim()
    {
        ArrayList<Edge> elist = new ArrayList<>();
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
    //Here starts convert to dot logic
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
    public void convertSearchtoDot()
    {
        this.graphCode="";
        StringBuilder sb = new StringBuilder();
        sb.append("graph G {"+"\n");
        int counter = 1;
        for( Vertex v: this.searchlist)
        {
            counter ++;
            if(counter <= this.searchlist.size()) {
                sb.append((v.number+1)).append("--");
            }
            else
            {
                sb.append(v.number);
            }
        }

        sb.append(";"+"\n}");
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
    public void saveGraphsearch(String name)
    {
        this.convertSearchtoDot();
        this.writeIntoFile(DIRECT + name +".dot",this.graphCode);
    }
    //Here starts shortest path algorithms
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
        ArrayList<Vertex> vlist = new ArrayList<Vertex>();
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

        while(!q.isEmpty())
        {
            Vertex v = q.poll();
            vlist.add(v);
            for(Edge e: v.edges )
            {
                if(q.remove(this.vertexlist.get(e.dest())))
                {
                    int weight = e.weight;
                    relax(v,this.vertexlist.get(e.dest()),weight);
                    q.add(this.vertexlist.get(e.dest()));
                }
            }

        }
        for(Vertex v: this.vertexlist)
        {
            System.out.println("Vertex:" + (v.number) +" Distanz: "+ v.distance);
        }
    }
    public void relax(Vertex u, Vertex v, int w)
    {
        if(v.distance > u.distance+w)
        {
            v.distance = u.distance +w;
            v.setPredecessor(u);
        }
    }
    void floydWarshall()
    {
        this.createAdjMatrixWeighted();
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
