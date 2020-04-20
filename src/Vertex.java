import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Vertex
{
  String name;
  int number;
  static int vertexnumber = 0;
  boolean visited;
  Vertex predecessor;
  int distance;
  int finishtime;
  int keyValue;
  String color;
  ArrayList<Edge> edges;
  public Vertex(String name)
  {
      this.name = name;
      this.edges = new ArrayList<Edge>();
      this.number = vertexnumber;
      this.visited = false;
      this.distance = Integer.MAX_VALUE;
      this.predecessor = null;
      vertexnumber ++;
  }
  public Vertex(int number)
  {
      this.number = number;
      this.edges = new ArrayList<Edge>();
      this.visited = false;
      this.distance = Integer.MAX_VALUE;
      this.predecessor = null;
      this.vertexnumber++;
  }
    public Vertex(int number, int weight)
    {
        this.number = number;
        this.keyValue = weight;
        this.edges = new ArrayList<Edge>();
        this.visited = false;
        this.distance = Integer.MAX_VALUE;
        this.predecessor = null;
        this.vertexnumber++;
    }
  public void setFinishTime(int value)
  {
      this.finishtime = value;
  }
  public void setVisited(){this.visited = true;}
  public void setUnVisited(){this.visited = false;}
  public void setPredecessor(Vertex v){this.predecessor = v;}
  public void setPredecessorNull(){this.predecessor = null;}
  public void setDistance(int distance){this.distance = distance;}
  public String getName()
  {
      return this.name;
  }
  public int getNumber()
  {
      return this.number;
  }
  public void addEdges(Edge e)
  {
      boolean alreadyin = false;
     // System.out.println(""+edges.size());
      for(int i = 0; i < edges.size(); i++)
      {
          if(edges.get(i).equals(e))
          {
              alreadyin = true;
          }
      }
      if(!alreadyin)
      {
          edges.add(e);
      }
      else
      {
          System.out.println("Edges is already in");
      }
  }
  public void addListOfEdge(ArrayList<Edge> eL)
  {
      if(this.edges.isEmpty()){
          this.edges = eL;
      }
      else
      {
      boolean edouble = false;
      for (Edge e: eL
           ) {
          for (Edge e2: this.edges
               ) {
              if(e.equals(e2))
              {
                  edouble = true;
              }

          }
          if(!edouble) {
              this.edges.add(e);
          }else
          {
              System.out.println("Edge is already in ");
          }
      }
  }
  }
    public boolean equals(Vertex o)
    {
        Vertex e = (Vertex) o;
      //  System.out.println(""+getName()+" "+getNumber());
        if(getName() == null || o.getName() == null)
        {
            return (getNumber() == (o.getNumber()));
        }
            return ((getName().equals(o.getName()) &&
                    getNumber() == (o.getNumber())));

    }
}
