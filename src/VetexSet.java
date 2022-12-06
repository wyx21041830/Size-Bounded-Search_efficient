import java.util.HashSet;
import java.util.TreeSet;

// 当某一步要开始使用Tset/Tset2时 先进行update1/2  然后使用Del/AddT1/T2方法
class VertexSet {
    HashSet<Integer> ID; // *用于查找 不同图中度数不同但还是同一个点
    HashSet<Vertex> Hset;// hashset 用于简单无序存放
    TreeSet<Vertex> Tset;//
    TreeSet<Vertex> Tset2;
    Comparators Cmp;

    public VertexSet() {
        Cmp = new Comparators();
        ID = new HashSet<>();
        Hset = new HashSet<>();
        Tset = new TreeSet<>(Cmp.cmp1);
        Tset2 = new TreeSet<>(Cmp.cmp2);
    }

    public VertexSet(VertexSet VS) {
        Cmp = new Comparators();
        Hset = new HashSet<>(VS.Hset);
        ID = new HashSet<>(VS.ID);
        Tset = new TreeSet<>(Cmp.cmp1);
        Tset.addAll(Hset);
        Tset2 = new TreeSet<>(Cmp.cmp2);
        Tset2.addAll(Hset);
    }

    public Integer Size() {
        return Hset.size();
    }

    public void Add(Vertex v) {
        Hset.add(new Vertex(v));
        ID.add(v.id);
    }

    public void AddT1(Vertex v) {
        Hset.add(new Vertex(v));
        ID.add(v.id);
        Tset.add(new Vertex(v));
    }

    public void AddT2(Vertex v) {
        Hset.add(new Vertex(v));
        ID.add(v.id);
        Tset2.add(new Vertex(v));
    }

    public void Add2All(Vertex v) {
        Hset.add(new Vertex(v));
        ID.add(v.id);
        Tset.add(new Vertex(v));
        Tset2.add(new Vertex(v));
    }


    public void DelV(Vertex v) {
        Hset.remove(v);
        ID.remove(v.id);
    }

    public void DelT1(Vertex v) {
        Hset.remove(v);
        ID.remove(v.id);
        Tset.remove(v);
    }

    public void DelT2(Vertex v) {
        Hset.remove(v);
        ID.remove(v.id);
        Tset.remove(v);
        Tset2.remove(v);
    }

    public void DelAll(Vertex v) {
        Hset.remove(v);
        ID.remove(v.id);
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