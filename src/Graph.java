import java.util.*;

public class Graph {
    int MinDegree, MaxDegree;// 图中最大/小度数
    VertexSet vertices;// 存点
    // 获取该图中对应id的点信息
    HashMap<Integer, HashSet<Integer>> relation;
    //这里只需要hashset就行
    //构造函数的浅拷贝问题
    public Graph() {
        vertices = new VertexSet();
        relation = new HashMap<>();
        MinDegree = MaxDegree = 0;
    }
    public Graph(Graph G) {// 浅拷贝问题
        vertices = new VertexSet(G.vertices);
        relation = new HashMap<>(G.relation);
        this.MinDegree = G.MinDegree;
        this.MaxDegree = G.MaxDegree;
    }

    void DataReader() {
        //input
    }


    void CalDegree() { // 计算每个点原图度数 O(n)
        MinDegree = MaxDegree = relation.get(vertices.Hset.iterator().next().id).size();
        for (Vertex v : vertices.Hset) {
            v.degree = relation.get(v.id).size();
            MinDegree = Math.min(MinDegree, v.degree);
            MaxDegree = Math.max(MaxDegree, v.degree);
        }
    }

    void CalCoreNum() { // 计算所有k-core
        HashMap<Integer, HashSet<Integer>> G = relation;
        Queue<Vertex> que = new LinkedList<>();//
        //剩余点
        HashSet<Vertex> vis = new HashSet<>();
        HashSet<Vertex> rest = new HashSet<>(vertices.Hset);
        for (int k = 1; k <= MaxDegree; k++) { // 计算 coreness
            if (rest.isEmpty()) break;
            for (Vertex u : rest) {
                if (u.degree < k) {
                    if (!vis.contains(u)) {
                        vis.add(u);
                        u.coreNumber = k - 1;
                        que.add(u);
                        rest.remove(u);
                    }
                }
            }
            while (que.size() > 0) {
                Vertex u = que.poll();
                for (int id : G.get(u.id)) {
                    Vertex v=vertices.Id2Vex.get(id);
                    if (--v.degree < k) {
                        que.add(v);
                        rest.remove(v);
                        if (!vis.contains(u)) {
                            vis.add(u);
                            u.coreNumber = k - 1;
                        }
                    }
                }
            }
        }
    }

    //内部增删 ： R，C
    void InnerDelFromG (Vertex u) {// 删除点u (信息 度数完全匹配(同一图中对应点))
        //从图中删除同时更新内部度
        if (!vertices.ID.contains(u.id)) return; // 本来就没有
        vertices.DelAll(u);
        vertices.Id2Vex.remove(u.id);
        for (int id : relation.get(u.id)) { // 更新度
            relation.get(id).remove(u.id);
            vertices.Id2Vex.get(id).degree--;
        }
        relation.remove(u.id);
    }

    void InnerDelFromG(int id) {// 删除id对应的同一个点 可能不同图中
        //通过映射到图内信息实现
        if (!vertices.ID.contains(id)) return; // 本来就没有
        Vertex v =vertices.Id2Vex.get(id);
        InnerDelFromG(v);
    }

    // 同理
    void InnerAdd2G(Vertex u, Graph G) {//从原图中加入
        if (vertices.ID.contains(u.id)) return; //本来就有
        vertices.Add(u);u.degree=0;//刚加入
        vertices.Id2Vex.put(u.id, u);
        relation.put(u.id, new HashSet<>());
        for(int id:G.relation.get(u.id)){
            if(vertices.ID.contains(id)){
                relation.get(u.id).add(id);
                relation.get(id).add(u.id);
                u.degree++;
                vertices.Id2Vex.get(id).degree++;
            }
        }
    }

    void InnerAdd2G(int id, Graph G) {//从原图中加入
        if (vertices.ID.contains(id)) return; //本来就有
        Vertex v =vertices.Id2Vex.get(id);
        InnerAdd2G(v,G);
    }
}
//外部删  Cr  Rc
