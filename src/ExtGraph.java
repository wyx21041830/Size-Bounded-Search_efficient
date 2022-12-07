import java.util.HashMap;
import java.util.HashSet;

public class ExtGraph extends  Graph {//Rc  Cr
    VertexSet Oppvertex;//存对面的点  度信息为图内部度 eg:Rc中c的点在R中的度
    HashMap<Integer, HashSet<Integer>> relation2;

    public ExtGraph() {
        super();
        Oppvertex = new VertexSet();
    }

    public ExtGraph(Graph G) {
        super(G);
        Oppvertex = new VertexSet();
    }

    public ExtGraph(ExtGraph G) {
        super(G);
        Oppvertex = new VertexSet(G.Oppvertex);
    }

    void CalOpDegree() {//算对面度计算

    }

    //内部删
    void InnerDelFromGByInfo(Vertex u) {// 删除点u (信息 度数完全匹配(同一图中对应点))
        if (!vertices.ID.contains(u.id)) return; // 本来就没有
        vertices.DelAll(u);
        vertices.Id2Vex.remove(u.id);
        for (int id:relation.get(u.id)) {//u的邻居
            relation2.get(id).remove(u.id);//
            Vertex v=Oppvertex.Id2Vex.get(id);
            v.degree--;
        }
        relation.remove(u.id);
    }

    void InnerDelFromGById(int id) {// 删除id对应的同一个点 可能不同图中
        //通过映射到图内信息实现
        if (!vertices.ID.contains(id)) return; // 本来就没有
        Vertex v = vertices.Id2Vex.get(id);
        this.InnerDelFromGByInfo(v);
    }
    // 同理
    void InnerAdd2GByInfo(Vertex u, Graph G) {//从原图中加入
        if (vertices.ID.contains(u.id)) return; //本来就有
        vertices.Add(u);
        u.degree = 0;//刚加入
        vertices.Id2Vex.put(u.id, u);
        relation.put(u.id, new HashSet<>());
        for(int id:G.relation.get(u.id)){
            if(Oppvertex.ID.contains(u.id)){//子图中存在该边
                //连边  更新度
                relation.get(u.id).add(id);
                relation2.get(id).add(u.id);
            }
        }
        for (Vertex v : Oppvertex.Hset) {//
            if (G.relation.get(u.id).ID.contains(v.id)) {
                if (!relation.get(u.id).ID.contains(v.id)) relation.get(u.id).Add2All(v);
                if (!relation2.get(v.id).ID.contains(u.id)) relation.get(v.id).Add2All(u);//
                u.degree++;
                v.degree++;
            }
        }
    }

    void InnerAdd2GById(int id, Graph G) {//从原图中加入
        if (vertices.ID.contains(id)) return; //本来就有
        Vertex v =vertices.Id2Vex.get(id);
        this.InnerAdd2GByInfo(v,G);
    }
    void OuterDelFromGByInfo(Vertex u) {//删对面的
        Oppvertex.DelAll(u);
        Oppvertex.Id2Vex.remove(u.id);
        for (Vertex v : vertices.Hset) {//
            relation.get(v.id).DelAll(v);
        }
        for (Vertex v : relation2.get(u.id).Hset) { // 更新度
            v.degree--;
        }

    }

}
;
