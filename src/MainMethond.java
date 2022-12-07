import java.util.HashMap;

public class MainMethond { // 主方法接口
        int n;
        Graph G, C, R;
        ExtGraph CR;
        VertexSet H;
        int Kmin, Kmax;
        int l, h;
        Heuristical HH;  // 初始解
        HashMap<Integer,Vertex> Id2Vetx; // id到点的映射
        public MainMethond(int q, int l, int h,Graph G,Graph C,Graph R,ExtGraph CR) {
            // input n
            this.G=G;
            this.C=C;
            this.R=R;
            this.CR=CR;
            // 初始化 Rc
        }
        // 从R中删u去
        public  void discardFromR(Vertex u){
            R.InnerDelFromG(u);
            CR.OuterDelFromG(u);
        }
        public  void discardFromR(int id){
            R.InnerDelFromG(id);
            CR.OuterDelFromG(id);
        }
        public  void discardFromC(Vertex v){
            C.InnerDelFromG(v);
            CR.InnerDelFromG(v);
        }
        public  void MoveFromC2R(int id){
            C.InnerDelFromG(id);
            R.InnerAdd2G(id,G);
            CR.InnerDelFromG(id);
            CR.OuterAdd2G(id,G);
        }
        //撤销



}
