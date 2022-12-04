import java.util.HashSet;
import java.util.TreeSet;

class VertexSet {
    HashSet<Vertex> Hset;// hashset 用于简单无序存放
    TreeSet<Vertex> Tset;//
    TreeSet<Vertex>Tset2;
    Comparators Cmp;
    public VertexSet() {
        Cmp = new Comparators();
        Hset = new HashSet<>();
        Tset = new TreeSet<>(Cmp.cmp1);
        Tset2 = new TreeSet<>(Cmp.cmp2);
    }

    public VertexSet(VertexSet VS) {
        Cmp = new Comparators();
        Hset = new HashSet<>(VS.Hset);
        Tset = new TreeSet<>(Cmp.cmp1);
        Tset.addAll(Hset);
        Tset2 = new TreeSet<>(Cmp.cmp2);
        Tset2.addAll(Hset);
    }

    public Integer Size() {
        return Hset.size();
    }

    public void Add(Vertex v) {
        Hset.add(v);
    }
    public void AddT1(Vertex v) {
        Hset.add(v);
        Tset.add(v);
    }
    public void AddT2(Vertex v) {
        Hset.add(v);
        Tset2.add(v);
    }

    public void Add2All(Vertex v) {
        Hset.add(v);
        Tset.add(v);
        Tset2.add(v);
    }


    public void DelV(Vertex v) {
        Hset.remove(v);
    }
    public void DelT1V(Vertex v) {
        Hset.remove(v);
        Tset.remove(v);
    }
    public void DelT2V(Vertex v) {
        Hset.remove(v);
        Tset.remove(v);
        Tset2.remove(v);
    }
    public void DelAll(Vertex v){
        Hset.remove(v);
        Tset.remove(v);
        Tset2.remove(v);
    }

    public void UpdateTset1() { // 若一直只对Hset操作
        Tset = new TreeSet<>(Cmp.cmp1);
        Tset.addAll(Hset);
    }
    public void UpdateTset2() { // 若一直只对Hset操作
        Tset = new TreeSet<>(Cmp.cmp2);
        Tset2.addAll(Hset);
    }

}