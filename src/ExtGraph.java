import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;

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
            if(Oppvertex.ID.contains(id)){//子图中存在该边
                //连边  更新度
                relation.get(u.id).add(id);
                relation2.get(id).add(u.id);
                Vertex v=Oppvertex.Id2Vex.get(id);
                v.degree++;
                u.degree++;
            }
        }

    }

    void InnerAdd2GById(int id, Graph G) {//从原图中加入
        if (vertices.ID.contains(id)) return; //本来就有
        Vertex v =vertices.Id2Vex.get(id);
        this.InnerAdd2GByInfo(v,G);
    }
    void OuterDelFromGByInfo(Vertex u) {//删对面的
        //删边
        for(int id:relation2.get(u.id)){
            if(vertices.ID.contains(id)){
                Vertex v= vertices.Id2Vex.get(id);
                relation.get(id).remove(u.id);
                v.degree--;
            }
        }
        relation2.remove(u.id);
        Oppvertex.DelAll(u);
        Oppvertex.Id2Vex.remove(u.id);
    }
    void OuterDelFromGById(int id){
        if(!Oppvertex.ID.contains(id))return;
        Vertex v= Oppvertex.Id2Vex.get(id);
        OuterDelFromGByInfo(v);
    }

    void OuterAdd2GByInfo(Vertex u, Graph G) {//从原图中加入
        if (Oppvertex.ID.contains(u.id)) return; //本来就有
        Oppvertex.Add(u);
        u.degree = 0;//刚加入
        Oppvertex.Id2Vex.put(u.id, u);
        relation2.put(u.id, new HashSet<>());
        for(int id:G.relation.get(u.id)){
            if(vertices.ID.contains(id)){//子图中存在该边
                //连边  更新度
                relation.get(id).add(u.id);
                relation2.get(u.id).add(id);
                Vertex v=Oppvertex.Id2Vex.get(id);
                v.degree++;
                u.degree++;
            }
        }
    }

    void OuterAdd2GById(int id, Graph G) {//从原图中加入
        if (Oppvertex.ID.contains(id)) return; //本来就有
        Vertex v =vertices.Id2Vex.get(id);
        this.OuterAdd2GByInfo(v,G);
    }
}
;
