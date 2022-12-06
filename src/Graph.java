import java.util.*;

public class Graph {
    int MinDegree, MaxDegree;// 图中最大/小度数
    VertexSet vertices;// 存点
    // 获取该图中对应id的点信息
    HashMap<Integer, VertexSet> relation;

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
        MinDegree = MaxDegree = relation.get(vertices.Hset.iterator().next().id).Size();
        for (Vertex v : vertices.Hset) {
            v.degree = relation.get(v.id).Size();
            MinDegree = Math.min(MinDegree, v.degree);
            MaxDegree = Math.max(MaxDegree, v.degree);
        }
    }

    void CalCoreNum() { // 计算所有k-core
        HashMap<Integer, VertexSet> G = relation;
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
                for (Vertex v : G.get(u.id).Hset) {
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
    void InnerDelFromGByInfo(Vertex u) {// 删除点u (信息 度数完全匹配(同一图中对应点))
        //从图中删除同时更新内部度
        if (!vertices.ID.contains(u.id)) return; // 本来就没有
        relation.remove(u.id);
        vertices.DelAll(u);
        vertices.Id2Vex.remove(u.id);
        for (Vertex v : vertices.Hset) {//
            relation.get(v.id).DelAll(v);
        }
        for (Vertex v : relation.get(u.id).Hset) { // 更新度
            v.degree--;
        }
    }

    void InnerDelFromGById(int id) {// 删除id对应的同一个点 可能不同图中
        //通过映射到图内信息实现
        if (!vertices.ID.contains(id)) return; // 本来就没有
        Vertex v =vertices.Id2Vex.get(id);
        InnerDelFromGByInfo(v);
    }

    // 同理
    void InnerAdd2GByInfo(Vertex u, Graph G) {//从原图中加入
        if (vertices.ID.contains(u.id)) return; //本来就有
        vertices.Add(u);u.degree=0;//刚加入
        vertices.Id2Vex.put(u.id, u);
        relation.put(u.id, new VertexSet());
        for (Vertex v : vertices.Hset) {//
            if (G.relation.get(u.id).ID.contains(v.id)) {
                if(!relation.get(v.id).ID.contains(u.id))relation.get(v.id).Add2All(u);
                if(!relation.get(u.id).ID.contains(v.id))relation.get(u.id).Add2All(v);
                u.degree++;
                v.degree++;
            }
        }
    }


}
//外部删  Cr  Rc
