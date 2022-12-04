import java.util.Comparator;

public class Comparators {
    Comparator<Vertex> cmp;

    public Comparators() {
        Comparator<Vertex> cmp = (o1, o2) -> Integer.compare(o1.degree, o2.degree);
    }
}