public class Edge implements Comparable<Edge> {
    Integer First;
    Integer Dest;
    int weight;
    public Edge(int source, int dest)
    {
        this.First = source;
        this.Dest = dest;
    }
    public Edge(int source, int dest,int weight)
    {
        this.First = source;
        this.Dest = dest;
        this.weight = weight;
    }
    public int compareTo(Edge compare)
    {
        return this.weight-compare.weight;
    }
    public int[] goTo()
    {
        int[] go = {this.First,this.Dest};
        return go;
    }
    public Integer first()
    {
        return this.First;
    }
    public Integer dest()
    {
        return this.Dest;
    }
    public void print()
    {
        System.out.println("From "+first()+" to "+dest());
    }
    public boolean equals(Edge o)
    {
        Edge e = (Edge) o;
        return ((first().equals(o.first()) &&
                dest().equals(o.dest())));
    }

}
