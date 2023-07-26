package ITShoppingMallDemo;
//Phone class.
//This class demostrates a factory pattern. 
public class Phone{
    public Product createPhone(String name, double price, int stock, Brand brand){
        if (brand == Brand.SAMSUNG){return new SamsungPhone(name, price, stock, brand);}
        if (brand == Brand.APPLE){return new ApplePhone(name, price, stock, brand);}
        if (brand == Brand.SONY){return new SonyPhone(name, price, stock, brand);}
        if (brand == Brand.ASUS){return new AsusPhone(name, price, stock, brand);}
        else {
            System.out.printf("%s does not make phones.", brand.name());
            return null;
        }
    }
}

class SamsungPhone extends Product{
    public SamsungPhone(){super();}
    public SamsungPhone(String name, double price, int stock, Brand brand){super(name, price, stock, brand);}
    public String getManufacturer(){return "Samsung";}
}

class ApplePhone extends Product{
    public ApplePhone(){super();}
    public ApplePhone(String name, double price, int stock, Brand brand){super(name, price, stock, brand);}
    public String getManufacturer(){return "Apple";}
}

class SonyPhone extends Product{
    public SonyPhone(){super();}
    public SonyPhone(String name, double price, int stock, Brand brand){super(name, price, stock, brand);}
    public String getManufacturer(){return "Sony";}
}

class AsusPhone extends Product{
    public AsusPhone(){super();}
    public AsusPhone(String name, double price, int stock, Brand brand){super(name, price, stock, brand);}
    public String getManufacturer(){return "Asus";}
}