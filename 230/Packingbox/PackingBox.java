package Packingbox;
import java.util.ArrayList;
import java.util.List;

abstract class PackingBox {
    private PackingBox _parent = null;
    protected int _type = 0; //default for leaf box
    abstract void verifyBox(); 
    public PackingBox getParent() {
        return _parent;
      //Add code for getting the parent
    }
    public void setParent(PackingBox parent) {
        _parent = parent;
      //Add code for setting the parent
    }
    public int getType() {
        return _type;
      //Add code to get the value of _type
    }
}
class ContainerBox extends PackingBox{
    private List<PackingBox> _childBoxes;
    public ContainerBox(int type) {	 
        if (type > 0) {_type = type; _childBoxes = new ArrayList<PackingBox>();}
    }
    //Complete the following function. It should recursively iterate 
    //the full object hierarchy of the current container.
    public void verifyBox() {
        System.out.println("The box currently being verified is a "+getClass().getSimpleName());
        for (PackingBox pb: _childBoxes){
            pb.verifyBox();
        }
    }
    public boolean addBox(PackingBox box) {
        //Add code that fulfils the rules provided in the description
        if (box.getType() == 0) {_childBoxes.add(box); box.setParent(this); return true;} 
        else {
            if (getType() < box.getType() && getType()%2 == box.getType()%2) {
                for (PackingBox pb:_childBoxes) {
                    if (pb.getType() == box.getType()) return false;
                }
                _childBoxes.add(box);
                box.setParent(this);
            }
        }
        return true;

    }
    public boolean removeBox(PackingBox box) {
        try{
            _childBoxes.remove(box);
        }
        catch (Exception e) {return false;}
        return true;
    }
   public List<PackingBox> getChildren(){
        return _childBoxes;
    }	 
}

class LeafBox extends PackingBox{
	public void verifyBox() {
        System.out.println("The box currently being verified is a "+getClass().getSimpleName());
    }
}