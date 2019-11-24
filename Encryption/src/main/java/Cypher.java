public class Cypher {
    private CypherType type;
    private String keyword;
    private int shift;

    public Cypher(String keyword){
        this.keyword = keyword;
        this.type = CypherType.XOR;
    }
    public Cypher(int shift){
        this.shift = shift;
        this.type = CypherType.Caesar;
    }

    public CypherType getType(){
        return type;
    }

    public String getKeyword() {
        return keyword;
    }

    public int getShift() {
        return shift;
    }
}
