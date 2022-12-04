import java.util.*;
public class Graph {
    int MinDegree,MaxDegree;// 图中最大/小度数
    VertexSet vertices ;// 存点
    //HashMap <Integer,Vertex>Id2Vex;
    HashMap<Integer, VertexSet> relation;
    //HashMap<Integer,Integer>CoreNumber; // cn[q]的值
    public Graph() {
        vertices =  new VertexSet();
        relation = new HashMap<>();
        MinDegree=MaxDegree=0;
    }
    public Graph(Graph G) {
        vertices =new VertexSet(G.vertices);
        relation = new HashMap<>(G.relation);
        this.MinDegree= G.MinDegree;
        this.MaxDegree=G.MaxDegree;
    }
    void DataReader(){
        //input
    }
    void CalDegree(){ // 计算每个点原图度数
        MinDegree=MaxDegree= relation.get(vertices.Hset.iterator().next().id).Size();
        for(Vertex v:vertices.Hset){
            v.degree= relation.get(v.id).Size();
            MinDegree=Math.min(MinDegree,v.degree);
            MaxDegree=Math.max(MaxDegree,v.degree);
        }
    }
    void CalCoreNum(){ // 计算所有k-core
        HashMap<Integer , VertexSet>G= relation;
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
    void DelFromG(Vertex u){//从图中删除同时更新内部度
        relation.remove(u.id);
        for(Vertex v:vertices.Hset){//
            relation.get(v.id).DelAll(v);
        }
        for(Vertex v: relation.get(u.id).Hset){ // 更新度
            v.degree--;
        }
    }
    void Add2G(Vertex u,Graph G){//从原图中加入
        relation.put(u.id,new VertexSet());
        for(Vertex v:vertices.Hset){//
            if(G.relation.get(u.id).ID.contains(v.id)){
                relation.get(v.id).Add2All(u);
                relation.get(u.id).Add2All(v);
                u.id++;
                v.id++;
            }

            }

    }
}
