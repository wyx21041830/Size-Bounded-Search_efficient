import java.util.HashMap;

public class MainMethond { // 主方法接口
        int n;
        Graph G, C, R, Cr, Rc;
        VertexSet H;
        int Kmin, Kmax;
        int l, h;
        Heuristical HH;  // 初始解
        HashMap<Integer,Vertex> Id2Vetx; // id到点的映射
        public MainMethond(int q, int l, int h,Graph G,Graph C,Graph R,Graph Cr,Graph Rc) {
            // input n
            this.G=G;
            this.C=C;
            this.R=R;
            this.Cr=Cr;
            this.Rc=Rc;
            // 初始化 Rc
        }
        // 从R中删u去
        public  void discardFromR(Vertex u){
            R.InnerDelFromGByInfo(u);
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
