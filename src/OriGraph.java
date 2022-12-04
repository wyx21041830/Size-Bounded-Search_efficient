import java.util.*;
public class OriGraph {
    int MinDegree,MaxDegree;// 图中最大/小度数
    VertexSet vertices ;// 存点
    //HashMap <Integer,Vertex>Id2Vex;
    HashMap<Integer, VertexSet> Graph;
    //HashMap<Integer,Integer>CoreNumber; // cn[q]的值
    public OriGraph() {
        vertices =  new VertexSet();
        Graph = new HashMap<>();
    }
    public OriGraph(OriGraph G) {
        vertices =new VertexSet(G.vertices);
        Graph = new HashMap<>(G.Graph);
        this.MinDegree= G.MinDegree;
        this.MaxDegree=G.MaxDegree;
    }
    void DataReader(){
        //input
    }
    void CalDegree(){ // 计算每个点原图度数
        MinDegree=MaxDegree=Graph.get(vertices.Hset.iterator().next().id).Size();
        for(Vertex v:vertices.Hset){
            v.degree=Graph.get(v.id).Size();
            MinDegree=Math.min(MinDegree,v.degree);
            MaxDegree=Math.max(MaxDegree,v.degree);
        }
    }
    void CalCoreNum(){ // 计算所有k-core
        HashMap<Integer , VertexSet>G=Graph;
        Queue<Vertex>que= new LinkedList<>() ;//
        //剩余点
        HashSet<Vertex>vis=new HashSet<>();
        HashSet<Vertex> rest = new HashSet<>(vertices.Hset);
        for(int k=1;k<=MaxDegree;k++){ // 计算 coreness
            if(rest.isEmpty())break;
            for(Vertex u:rest){
                if(u.degree<k){
                    if(!vis.contains(u)){
                        vis.add(u);
                        u.coreNumber=k-1;
                        que.add(u);
                        rest.remove(u);
                    }
                }
            }
            while(que.size()>0){
                Vertex u=que.poll();
                for(Vertex v:G.get(u.id).Hset){
                    if(--v.degree<k){
                        que.add(v);
                        rest.remove(v);
                        if(!vis.contains(u)) {
                            vis.add(u);
                            u.coreNumber=k-1;                        }
                    }
                }
            }
        }
    }
    void DelFromG(Vertex u){//从图中删除同时
        Graph.remove(u.id);
        for(Vertex v:vertices.Hset){//
            Graph.get(v.id).DelAll(v);
        }
        for(Vertex v:Graph.get(u.id).Hset){ // 更新度
            v.degree--;
        }
    }
}
