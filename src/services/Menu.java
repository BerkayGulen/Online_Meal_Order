package services;

import models.Item;

import java.util.ArrayList;

public class Menu {
    //fields
    private ArrayList<Item> breakfast = new ArrayList<>();
    private ArrayList<Item> deserts = new ArrayList<>();
    private ArrayList<Item> mainDishes = new ArrayList<>();
    private ArrayList<Item> beverages = new ArrayList<>();


    //constructors
    public Menu() {
        populateMenu();
    }

    public void populateMenu() {
        //breakfast
        breakfast.add(new Item("Omlette\t\t        ", "Breakfast", 12.0));
        breakfast.add(new Item("Sandiwiche\t\t     ", "Breakfast", 14.0));
        breakfast.add(new Item("Pancake\t\t\t      ", "Breakfast", 16.25));
        breakfast.add(new Item("Baxon With Eggs\t\t", "Breakfast", 17.0));
        breakfast.add(new Item("Gronola and Yogurt\t ", "Breakfast", 14.0));
        breakfast.add(new Item("Toast\t\t         ", "Breakfast", 13.50));


        //deserts
        deserts.add(new Item("Cheesecake           ", "Deserts", 17.25));
        deserts.add(new Item("Ice Cream              ", "Deserts", 14.0));
        deserts.add(new Item("Lemon Cake            ", "Deserts", 14.50));
        deserts.add(new Item("Banana Split         ", "Deserts", 17.25));
        deserts.add(new Item("Waffle                 ", "Deserts", 14.0));
        deserts.add(new Item("Apple Pie              ", "Deserts", 18.0));


        //mainDishes
        mainDishes.add(new Item("Steak\t             ", "Main Dish", 40.0));
        mainDishes.add(new Item("Spaghetti\t         ", "Main Dish", 26.0));
        mainDishes.add(new Item("Burger\t            ", "Main Dish", 38.0));
        mainDishes.add(new Item("Fish\t\t            ", "Main Dish", 35.0));
        mainDishes.add(new Item("Roast Beef\t        ", "Main Dish", 40.0));
        mainDishes.add(new Item("Grilled Chicken\t\t", "Main Dish", 36.25));



        //beverages
        beverages.add(new Item("Cup of Coffe\t        ", "Bevarages", 4.0));
        beverages.add(new Item("Glass of Wine\t    ", "Bevarages", 14.50));
        beverages.add(new Item("Orange Juice\t        ", "Bevarages", 5.0));
        beverages.add(new Item("Water\t               ", "Bevarages", 2.0));
        beverages.add(new Item("Beer\t\t            ", "Bevarages", 10.0));
        beverages.add(new Item("Cup of Tea\t        ", "Bevarages", 3.25));

    }

    //get all menu
    public String getAllMenu() {
        String menu = "\n\t\t\t\t\t\t\t\t\t\t\t\tMENU\n";
        menu += getAllBreakfast();
        menu += getAllMainDishes();
        menu += getAllDeserts();
        menu += getAllBeverages();

        return menu;
    }


    //get all deserts
    public String getAllDeserts() {
        String desertMenu = "DESERTS:\n";
        int i = 1;
        for (Item desert : deserts) {
            desertMenu += String.valueOf(i) + ". " + desert.toString() + "\n";
            i++;
        }
        desertMenu +="\n";
        return desertMenu;
    }

    //get all breakfasts
    public String getAllBreakfast() {
        String breakfastMenu = "BREAKFAST:\n";
        int i = 1;
        for (Item breakfast : breakfast) {
            breakfastMenu += String.valueOf(i) + ". " + breakfast.toString() + "\n";
            i++;
        }
        breakfastMenu +="\n";
        return breakfastMenu;
    }

    // get all main dishes
    public String getAllMainDishes() {
        String mainDishMenu = "MAIN DISHES:\n";
        int i = 1;
        for (Item mainDish : mainDishes) {
            mainDishMenu += String.valueOf(i) + ". " + mainDish.toString() + "\n";
            i++;

        }
        mainDishMenu +="\n";
        return mainDishMenu;
    }

    //get all beverages
    public String getAllBeverages() {
        String beverageMenu = "BEVERAGES:\n";
        int i = 1;
        for (Item beverage : beverages) {
            beverageMenu += String.valueOf(i) + ". " + beverage.toString() + "\n";
            i++;
        }
        beverageMenu +="\n";
        return beverageMenu;
    }

    //get breakfast array item
    public Item getItemFromBreakfast(int index) {
        return breakfast.get(index - 1);
    }

    public Item getItemFromMainDishes(int index) {
        return mainDishes.get(index - 1);
    }

    public Item getItemFromDeserts(int index) {
        return deserts.get(index - 1);
    }

    public Item getItemFromBeverages(int index) {
        return beverages.get(index - 1);
    }

}
