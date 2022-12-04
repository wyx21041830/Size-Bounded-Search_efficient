import java.util.HashMap;

public class SC_Enum {
    int n;
    Graph G, C, R, Cr, Rc;
    VertexSet H;
    int Kmin, Kmax;
    int l, h;
    Heuristical HH;  // 初始解
    HashMap<Integer,Vertex>Id2Vetx; // id到点的映射
    public SC_Enum(int q, int l, int h) {
        // input n
        G=new Graph();
        // input G
        G.DataReader();
        C=new Graph(); // null
        R=new Graph(G);// =G
        Cr=new Graph(); //null
        Rc=new Graph();//只有点 没有边
        // 初始化 Rc
        for(Vertex v:G.vertices.Hset){
            Rc.vertices.Add(v);
            Rc.relation.put(v.id,new VertexSet());
        }
    }

    public  void discardFromR(Vertex u){
        R.vertices.DelV(u);
        for(Vertex v:R.relation.get(u.id).Hset){

        }
        Rc.vertices.DelV(u);

    }
    public  void discardFromC(Vertex v){

    }
    public  void MoveFromC2R(Vertex v){

    }
    //撤销

}
