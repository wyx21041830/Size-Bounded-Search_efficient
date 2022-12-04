import java.util.HashSet;
import java.util.TreeSet;

class VertexSet {
    HashSet<Vertex> Hset;// hashset 用于简单无序存放
    TreeSet<Vertex> Tset;//
    Comparators Cmp;
    public VertexSet() {
        Cmp = new Comparators();
        Hset = new HashSet<>();
        Tset = new TreeSet<>(Cmp.cmp);
    }

    public VertexSet(VertexSet VS) {
        Cmp = new Comparators();
        Hset = new HashSet<>(VS.Hset);
        Tset = new TreeSet<>(Cmp.cmp);
        Tset.addAll(Hset);
    }

    public Integer Size() {
        return Hset.size();
    }

    public void Add(Vertex v) {
        Hset.add(v);
    }

    public void Add2All(Vertex v) {
        Hset.add(v);
        Tset.add(v);
    }

    public void AddT1(Vertex v) {
        Hset.add(v);
        Tset.add(v);
    }
    public void DelV(Vertex v) {
        Hset.remove(v);
    }
    public void DelT1V(Vertex v) {
        Hset.remove(v);
        Tset.remove(v);
    }
    public void DelAll(Vertex v){
        Hset.remove(v);
        Tset.remove(v);
    }

    public void UpdateTset() { // 若一直只对Hset操作
        Tset = new TreeSet<>(Cmp.cmp);
        Tset.addAll(Hset);
    }


}