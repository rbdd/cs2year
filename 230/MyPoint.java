public class MyPoint{
    private int x, y;
    
    private MyPoint(){
        this(0,0);
    }
    private MyPoint(int i1, int i2){
        x = i1;
        y = i2;
    }
    
    public String toString(){
        return String.format("(%d, %d)", x, y);
    } 
}