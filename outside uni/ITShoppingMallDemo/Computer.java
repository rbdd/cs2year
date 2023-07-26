package ITShoppingMallDemo;

//Computer class.
//This class demostrates a factory pattern. 
public class Computer {
    public Product createComputer(String name, double price, int stock, Brand brand){
        if (brand == Brand.SAMSUNG){return new SamsungComputer(name, price, stock, brand);}
        if (brand == Brand.APPLE){return new AppleComputer(name, price, stock, brand);}
        if (brand == Brand.LENOVO){return new LenovoComputer(name, price, stock, brand);}
        if (brand == Brand.DELL){return new DellComputer(name, price, stock, brand);}
        if (brand == Brand.ASUS){return new AsusComputer(name, price, stock, brand);}
        else {
            System.out.printf("%s does not make computers.", brand.name());
            return null;
        }
    }
}

class SamsungComputer extends Product{
    public SamsungComputer(){super();}
    public SamsungComputer(String name, double price, int stock, Brand brand){super(name, price, stock, brand);}
    public String getManufacturer(){return "Samsung";}
}

class AppleComputer extends Product{
    public AppleComputer(){super();}
    public AppleComputer(String name, double price, int stock, Brand brand){super(name, price, stock, brand);}
    public String getManufacturer(){return "Apple";}
}

class LenovoComputer extends Product{
    public LenovoComputer(){super();}
    public LenovoComputer(String name, double price, int stock, Brand brand){super(name, price, stock, brand);}
    public String getManufacturer(){return "Lenovo";}
}

class DellComputer extends Product{
    public DellComputer(){super();}
    public DellComputer(String name, double price, int stock, Brand brand){super(name, price, stock, brand);}
    public String getManufacturer(){return "Dell";}
}

class AsusComputer extends Product{
    public AsusComputer(){super();}
    public AsusComputer(String name, double price, int stock, Brand brand){super(name, price, stock, brand);}
    public String getManufacturer(){return "Asus";}
}



