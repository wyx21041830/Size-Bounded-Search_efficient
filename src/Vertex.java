class Vertex  {
    int id;
    int degree;
    int coreNumber;
    public int getId() {
        return id;
    }
    public Vertex(int id){
        this.id=id;
        degree=0;
        coreNumber=0;
    }
}
