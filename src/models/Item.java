package models;

public class Item {
    //fields

    private String name;
    private  String category;
    private double price;
    private int amount;


    //constructors
    public Item(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // accessors and mutators
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    //toString
    @Override
    public String toString() {
        return name +"\t\t" +String.valueOf(price)+" ₺" ;
    }

}
