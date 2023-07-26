package ITShoppingMallDemo;

import java.awt.*;
import java.util.ArrayList;

//Product class
//this class is the parent of all other sub product class.
public abstract class Product{
    public final String DEFAULT_NAME = "Unknown Product";
    public final double DEFAULT_PRICE = 0.00;
    public final int DEFAULT_STOCK = 0;
    public final Brand DEFAULT_BRAND = Brand.FROMITSHOPPINGMALL;
    //public final Category

    private String name;
    private double price;
    private static int stock;
    private Brand brand;

    public Product(){
        name = DEFAULT_NAME;
        price = DEFAULT_PRICE;
        stock = DEFAULT_STOCK;
        brand = DEFAULT_BRAND;
    }

    public Product(String name, double price, int s, Brand brand){
        this.name = name;
        this.price = price;
        stock = s;
        this.brand = brand;
    }

    public String getName(){return name;}
    public double getPrice(){return price;}
    public static int getAvailable(){return stock;}
    public Brand getBrand(){return brand;}
    abstract String getManufacturer();

    protected void manageStock(int value){stock += value;}
}