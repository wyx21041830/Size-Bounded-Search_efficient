class Vertex  {
    int id;
    int degree;
    int coreNumber;
    int connectionScore;
    public int getId() {
        return id;
    }
    public Vertex(int id){
        this.id=id;
        degree=0;
        coreNumber=0;
    }
    public Vertex(Vertex v){
        this.id=v.id;
        this.degree=v.degree;
        this.coreNumber=v.coreNumber;
    }
}
