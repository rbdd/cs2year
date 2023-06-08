import java.util.ArrayList;
import java.util.List;
class Table {
	private static final int MAX_CHAIRS_POSSIBLE = 4;
	private List<Chair> _chairs;
	//constructor
	public Table() {
	   _chairs = new ArrayList<Chair>();
       Chair chair = new Chair();
       _chairs.add(chair);
       chair.attachTo(this);
	}
	//method to assemble chairs to the table
	public boolean assembleWith(Chair chair) {
	   if( _chairs.size() == MAX_CHAIRS_POSSIBLE )
	       return false;
	   _chairs.add(chair);
	   chair.attachTo(this);
	   return true;
	}
	//method to disassemble the chairs from the table
	public boolean disassemble() {
		if( _chairs.size() == 1 )
		     return false;
        for (int i = _chairs.size() - 1; i > 0; i--){
            _chairs.get(i).detach();
            _chairs.remove(i);
        }
		return true;
	}

	public List<Chair> getChairs(){
	   return _chairs;
	}
}

class Chair {
	private Table _table;
	private boolean _isAttached = false;
	public Chair() {
	}
	//method for attaching a chair to a table
	public boolean attachTo(Table table) {
	    if(_isAttached)
	    	return false; //returns failure
	    _table = table;
	    _isAttached = true;
	    return _isAttached;  //returns success
	}
	//method for detaching a chair from a table
	public boolean detach() {
		if(!_isAttached)
	    	return false; //returns failure
		_table = null;
		_isAttached = false;
		return true; //returns success
	}

	public Table getTable(){
		return _table;
	}
}

public class DiningFurnitureDemo{
	public static void main(String[] args){
		Table t1 = new Table();	
        for(int i=0; i<10; i++){
            t1.assembleWith(new Chair());	
        }	
        System.out.println(t1.getChairs().size()==4);
	}
}