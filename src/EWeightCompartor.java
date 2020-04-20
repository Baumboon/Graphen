import java.util.Comparator;

public class EWeightCompartor implements Comparator<Edge> {
    @Override
    public int compare(Edge e1, Edge e2)
    {
        return Double.compare(e1.weight,e2.weight);
    }
}
