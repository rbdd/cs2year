package Packingbox;

public class test {
    public static void main(String args[]){
        ContainerBox b1 = new ContainerBox(1);
        ContainerBox b2 = new ContainerBox(2);
        b1.addBox(b2);
        System.out.println(b2.getParent()==b1);
    }
}
