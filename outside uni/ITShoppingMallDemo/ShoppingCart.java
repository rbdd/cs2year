package ITShoppingMallDemo;

import java.sql.Array;
import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Product> items = new ArrayList<Product>();

    public ShoppingCart(){}

    public void addItem(Product product){
        if (items.contains(product)){items.get(items.indexOf(product)).manageStock(1);}
        else items.add(product);
    }
    public void removeItem(Product product){
        if (items.contains(product)){items.remove(product);}
    }
    public double getTotalPrice(){
        double total = 0;
        for (Product item: items){
            total += item.getPrice();
        }
        return total;
    }
    public void clearItems(){
        items = new ArrayList<Product>();
    }



}
