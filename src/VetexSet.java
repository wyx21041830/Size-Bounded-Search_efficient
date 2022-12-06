import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

// 当某一步要开始使用Tset/Tset2时 先进行update1/2  然后使用Del/AddT1/T2方法
class VertexSet {
    HashSet<Integer> ID; // *用于查找 不同图中度数不同但还是同一个点

    HashMap<Integer, Vertex> Id2Vex;// 建立id到vertex的映射
    HashSet<Vertex> Hset;// hashset 用于简单无序存放
    TreeSet<Vertex> Tset;//
    TreeSet<Vertex> Tset2;
    Comparators Cmp;

    public VertexSet() {
        Cmp = new Comparators();
        ID = new HashSet<>();
        Id2Vex = new HashMap<>();
        Hset = new HashSet<>();
        Tset = new TreeSet<>(Cmp.cmp1);
        Tset2 = new TreeSet<>(Cmp.cmp2);
    }

    public VertexSet(VertexSet VS) {
        Cmp = new Comparators();
        Hset = new HashSet<>(VS.Hset);
        ID = new HashSet<>(VS.ID);
        Id2Vex = new HashMap<>(VS.Id2Vex);
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
        Id2Vex.put(v.id,v);
    }

    public void AddT1(Vertex u) {
        Vertex v= new Vertex(u);
        ID.add(v.id);
        Id2Vex.put(v.id,v);
        Tset.add(v);
    }

    public void AddT2(Vertex u) {
       Vertex v= new Vertex(u);
        Hset.add(v);
        ID.add(v.id);
        Id2Vex.put(v.id,v);
        Tset2.add(new Vertex(v));
    }

    public void Add2All(Vertex u) {
        Vertex v= new Vertex(u);
        Hset.add(v);
        ID.add(v.id);
        Id2Vex.put(v.id,v);
        Tset.add(v);
    }


    public void DelV(Vertex v) {
        Hset.remove(v);
        Id2Vex.remove(v.id);
        ID.remove(v.id);
    }

    public void DelAll(Vertex v) {
        Hset.remove(v);
        Id2Vex.remove(v.id);
        ID.remove(v.id);
        Tset.remove(v);
        Tset2.remove(v);
    }
    public void DelAll(int id) {
        Vertex v= Id2Vex.get(id);
        DelAll(v);
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