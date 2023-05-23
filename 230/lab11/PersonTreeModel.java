import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.util.*;

class PersonTreeModel implements TreeModel{
    private ProjectLeader root;
    private ArrayList<TreeModelListener> treeModelListeners = new ArrayList<TreeModelListener>();
    public PersonTreeModel(ProjectLeader pl){root = pl;}
    public ProjectLeader getRoot(){return root;}
    public void setRoot(ProjectLeader r){root = r;}
    public Person getChild(Object parent, int index){
        if (!(parent instanceof ProjectLeader)) return null;
        else if (((ProjectLeader) parent).getSize() <= index || index < 0) return null;
        return ((ProjectLeader) parent).get(index);}
    public int getChildCount(Object parent){if (!(parent instanceof ProjectLeader))return 0; return ((ProjectLeader) parent).getSize();}
    public int getIndexOfChild(Object parent, Object child){if (!(parent instanceof ProjectLeader))return -1; return ((ProjectLeader)parent).indexOf((Person) child);}
    public boolean isLeaf(Object node){return node instanceof Person && !(node instanceof ProjectLeader);}
    public void insertNodeInto(Person newChild, ProjectLeader parent, int index){
        parent.add(newChild);
        int[] ia = {parent.indexOf(newChild)};
        Object[] oa = {newChild};
        fireTreeNodesInserted(this, parent.getPath(), ia, oa);
        
    }
    public void removeNodeFromParent(Person selectedNode){
        ProjectLeader pl = (ProjectLeader) selectedNode.getParent();
        int index = pl.indexOf(selectedNode);
        pl.remove(selectedNode);
        int[] ia = {index};
        Object[] oa = {selectedNode};
        fireTreeNodesRemoved(this, pl.getPath(), ia, oa);
    }


    protected void fireTreeNodesRemoved(Object source, Object[] path,int[] childIndices,Object[] children) {
        System.out.printf("Called fireTreeNodesRemoved: path=%s, childIndices=%s, children=%s\n", Arrays.toString(path), Arrays.toString(childIndices), Arrays.toString(children));
        final TreeModelEvent event = new TreeModelEvent(source, path, childIndices, children);
        for (final TreeModelListener tml : treeModelListeners)
          tml.treeNodesRemoved(event);
    }
    protected void fireTreeNodesInserted(Object source, Object[] path,int[] childIndices,Object[] children) {
        System.out.printf("Called fireTreeNodesInserted: path=%s, childIndices=%s, children=%s\n", Arrays.toString(path), Arrays.toString(childIndices), Arrays.toString(children));
        final TreeModelEvent event = new TreeModelEvent(source, path, childIndices, children);
        for (final TreeModelListener tml : treeModelListeners)
          tml.treeNodesInserted(event);
    }
    public void addTreeModelListener(TreeModelListener listener) {treeModelListeners.add(listener);}
    public void removeTreeModelListener(TreeModelListener listener) {treeModelListeners.remove(listener);}
    public void valueForPathChanged(TreePath path, Object newValue) {  }
}