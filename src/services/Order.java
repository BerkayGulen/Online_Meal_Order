package services;
import models.Item;
import java.util.ArrayList;

public class Order {

    //fields
    private ArrayList<Item> order = new ArrayList<Item>();
    private ArrayList<Item> orderHıstory = new ArrayList<>();


    //constructors
    public Order(){}
    //add item to order
    public void addItemToOrder(Item item){
        order.add(item);
    }
    //get item from order
    public Item getItemFromOrder(int index){
        return order.get(index);
    }
    //get size of order
    public int getSize(){
        return order.size();
    }
    //clear order
    public void clearAllOrder(){
        if(order.size()>0){
            order.clear();
        }
    }
    //add order to order history
    public void populateOrderHistory(){
        for (int i=0;i<order.size();i++){
            orderHıstory.add(getItemFromOrder(i));
        }
    }
    public void clearOrderHistory(){
        orderHıstory.clear();
    }
    //remove order
    public void removeOrder (int index){
        order.remove(index);
    }

    //toString -- order
    @Override
    public String toString() {
        int i = 1;
        String displayOrder = "\t\tORDERS\n";
        for (Item item: order){

            displayOrder += String.valueOf(i)+". "+item.toString()+"\n";
            i++;
        }
        return displayOrder;
    }


    //TO String ORDER HISTORY
    public String orderHıstoryToString(){
        String displayOrderHıstory = "\t\tPAYMENT HISTORY\n";
        for (Item item : orderHıstory){
            displayOrderHıstory += item.toString()+"\n";
        }
        return displayOrderHıstory;
    }

}
