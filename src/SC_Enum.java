import java.util.Iterator;
public class SC_Enum {
    int n;
    OriGraph G;
    VertexSet C,H,R;
    int Kmin,Kmax;
    int l,h;
    Heuristical HH;  // 初始解
public SC_Enum(int q, int l, int h){
    // input n
    G=new OriGraph(n);
    // input G
    G.DataReader();
    Kmin=G.MinDegree;
    Kmax=Math.min(G.CoreNumber.get(q),h-1);
    for(Vertex v: G.vertices)R.Add(v);
    C=new VertexSet();
}
    public void SCEnum(){
        Heuristical HH=new Heuristical();  // 初始解
        H=HH.H;
        if(Kmin<Kmax){
            for(Vertex v:G.vertices){
                if(G.CoreNumber.get(v)<=Kmin){
                    G.DelFromG(v); //
                }
            }
            C.UpdateTset1();//生成有序set (每次要计算Dmin(C))
            // Reducing
            ReducingTechniques Tec=new ReducingTechniques(G,C,R,l,h,Kmin,Kmax);
            Tec.reduce();
            dfs(C,R);
        }
    }
    void dfs( VertexSet C, VertexSet R){
        int DminC= C.Tset1.iterator().next().degree;// C中最小度
        if(C.Size()>=l&&C.Size()<=h&&DminC>Kmin){
            Kmin=DminC;
            H=C;
        }
        if(C.Size()<h&&R.Size()>0){
            for(Vertex v: R.Hset){ // 我才知道java全是传引用
                VertexSet C1 =new VertexSet(C);
                VertexSet R1=new VertexSet(R);
                VertexSet C2 =new VertexSet(C);
                VertexSet R2=new VertexSet(R);
                Instance Inst1= new Instance(G,C1,R1);
                Instance Inst2=new Instance(G,C2,R2);
                Inst1.SelectFromC2R(v);
                Inst2.DelFromR(v);
                //更新R1 R2 中点的degreeC**
                dfs(C1, R1);
                dfs(C2, R2);
            }

        }


    }
    public  void Output(){
        for(Vertex v:H.Hset){
            System.out.println(v.id);
        }
    }
}
