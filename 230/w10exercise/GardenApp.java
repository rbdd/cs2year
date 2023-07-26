import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

abstract class Garden {
	private Garden _parent = null;
	protected double _length;
	protected double _width;
    abstract double calculatePerimeter();
    public Garden(double length, double width) {
	    _length = length;
	    _width = width;
	 }
    public Garden getParent() {
		return _parent;
	}
	protected void setParent(Garden parent) {
		_parent = parent;
	}
}

class TerminalGarden extends Garden{
	public TerminalGarden(double length, double width) {
	    super(length, width);
	 }
	public double calculatePerimeter() {
    	double perimeter = 2*(_length+_width);
    	System.out.println("The perimeter of this "+getClass().getSimpleName()+" is "+perimeter);
    	return perimeter;
    }
}

//Complete this class as per question requirements
class AggregateGarden extends Garden{
	 private List<Garden> _childGardens;

	 public AggregateGarden(double length, double width) {
		 super(length, width);
		 _childGardens = new ArrayList<Garden>();
	 }
	 //iterates through the child gardens in a depth-first order
	 //and must also print the perimeter of this aggregate garden
	 //in the string format shown in the test case examples
	 public double calculatePerimeter() {
	    double t = 2*(_length+_width);
		System.out.println("The perimeter of this "+getClass().getSimpleName()+" is "+t);
		for (Garden g:_childGardens){
			t += g.calculatePerimeter();
		}
	    return t;
	 }
	 //adds a child garden to this aggregate garden after fullfilling the
	 //conditions as specified in the description
	 public boolean addGarden(Garden garden) {
	    if (garden._length < _length && garden._width < _width){
			_childGardens.add(garden);
			garden.setParent(this);
			return true;
		}
	    return false;
	 }
	 public List<Garden> getChildren(){
		 return _childGardens;
	 }
}

//Main class -- only for writing tests
public class GardenApp {
    public static void main(String[] args) {
    	//ADD TEST CODE e.g. CR example tests
    }
}