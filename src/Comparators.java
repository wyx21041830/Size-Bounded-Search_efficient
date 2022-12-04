import java.util.Comparator;

public class Comparators {
    Comparator<Vertex> cmp1,cmp2;
    public Comparators() {
        cmp1 = Comparator.comparingInt(o -> o.degree);
        cmp2= (o1, o2) -> Integer.compare(o2.connectionScore, o1.connectionScore);
    }


}