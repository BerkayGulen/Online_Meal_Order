package services;
import models.Item;

public class Payment {
    //private static final double tax = 0.08;
    private double total;
    private Order order = new Order();

    //constructor
    public Payment(Order order) {
        this.order = order;
    }

    // accessors and mutators
    public double getTotal() {
        return calculateTotal();
    }

    //calculate total
    public double calculateTotal(){
        total = 0;
        for(int i=0; i<order.getSize();i++){
            Item item = order.getItemFromOrder(i);
            total += item.getPrice();
        }
        return total;
    }
    //clear payment
    public void clearPayment(){
        total = 0;
    }

    //pay with cash
    public boolean payWithCash(double money){
        calculateTotal();
            if(money==total){
                System.out.println("enough money");
                order.populateOrderHistory();
                order.clearAllOrder();
                clearPayment();
                return true;
            }
            else if(money>total){
                System.out.println("here is your change: "+String.valueOf((money-total))+" ₺");
                order.populateOrderHistory();
                order.clearAllOrder();
                clearPayment();
                return true;
            }
            else if (total>money){
                System.out.println("not enough money. you need to pay "+String.valueOf((total-money))+" ₺"+"  more");
                return false;

            }

        return false;
    }

    //pay with credit card
    public boolean payWithCreditCard(String creditCardNumber,String password){
        if(creditCardNumber.length()<16){
            System.out.println("enter a valid credit card number with 16 digits");
            return false;
        }
        else if(password.length()!=4){
            System.out.println("enter a valid password with 4 digits");
            return false;
        }else if (creditCardNumber.length()<16 && password.length()!=4){
            System.out.println("enter valid credit card number and password");
            return false;
        }
        else {
            System.out.println("payment is successfully done");
            order.populateOrderHistory();
            order.clearAllOrder();
            total = 0;
            return true;
        }
    }






}
