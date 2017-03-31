package poker;

public class Points {
    
    private int type;
    private int main;
    private int other;

    public int getType() {
        return type;
    }

    public int getMain() {
        return main;
    }

    public int getOther() {
        return other;
    }

    public Points(int type, int main, int other) {
        this.type = type;
        this.main = main;
        this.other = other;
    }

    public Points(int type, int main) {
        this.type = type;
        this.main = main;
        this.other = 0;
    }
    
    
}
