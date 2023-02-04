import models.User;
import services.Auth;
import services.Menu;
import services.Order;
import services.Payment;

import java.util.Locale;
import java.util.Scanner;


public class Main {
    //static objects
    static Scanner input = new Scanner(System.in);
    static Auth auth = new Auth();
    static Menu menu = new Menu();
    static Order order = new Order();
    static Payment payment = new Payment(order);
    //conditions for navigate in program
    static boolean showAuthenticationPage = true;
    static boolean showHomePage = false;
    static boolean exit = false;
    static boolean showMenuPage = false;
    static boolean showPaymentPage = false;
    static boolean isPaying = true;
    static boolean payingWithCash;
    static boolean showUserSettings = false;
    //global variables for choices that user make
    static int choice;
    static int amount;
    static double money;
    static String strChoice;


    public static void main(String[] args) {


        while (!exit) {

            if (showAuthenticationPage) {
                authentication();

            }
            else if (showHomePage) {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tHOME PAGE");
                System.out.println("*******************************************************************************************************************************************");
                System.out.println("[Press 1 to see the MENU]\t\t[press 2 to PAYMENT]\t\t[press 3 to USER SETTINGS]\t\t[press -1 to log out]\t\t[press -2 to exit]");
                System.out.println("*******************************************************************************************************************************************");

                validateChoice();
                if (choice == -1) {//LOG OUT
                    showAuthenticationPage = true;
                    showHomePage = false;
                    order.clearOrderHistory();
                }
                else if (choice == -2) {//EXIT
                    if (order.getSize() > 0) {
                        System.out.println("if you exit, the orders will be DELETED");
                        System.out.println("do you want to EXIT [Y] [N]");
                        System.out.print("Choice: ");
                        strChoice = input.next().toUpperCase(Locale.ROOT);
                        if (strChoice.equals("Y")) {
                            order.clearAllOrder();
                            showHomePage = false;
                            exit = true;
                        } else {
                            System.out.println("invalid input please enter [Y]or[N]");
                        }
                    } else {
                        showHomePage = false;
                        exit = true;
                    }

                }
                else if (choice == 3) {
                    showHomePage = false;
                    showUserSettings = true;
                }
                else if (choice == 1) {//MENU PAGE
                    showMenuPage = true;
                    showHomePage = false;
                }
                else if (choice == 2) {//PAYMENT PAGE
                    if (payment.getTotal() > 0) {
                        showPaymentPage = true;
                        showHomePage = false;
                    } else System.out.println("you need to order for pay");
                }
                else {
                    System.out.println("invalid input. Enter a valid integer value!!!");
                }

            }
            else if (showMenuPage) {
                System.out.println(menu.getAllMenu());
                System.out.println("[press 1 to ORDER]\t\t[press 2 to HOME PAGE]\t\t[press -1 to LOG OUT]\t\t[press -2 to EXIT]");
                validateChoice();
                if (choice == 1) {//show menu items
                    while (true) {
                        displayMenuChoices();
                        validateChoice();
                        if (choice == 1) {
                            addBreakfast();
                        } else if (choice == 2) {
                            addMainDishes();
                        } else if (choice == 3) {
                            addDeserts();
                        } else if (choice == 4) {
                            addBevereges();
                        } else if (choice == 5) {//delete order that user wants
                            System.out.println(order.toString());
                            System.out.print("Enter order number that you want to delete: ");
                            validateChoice();
                            order.removeOrder(choice - 1);
                            System.out.println("order successfully deleted");
                        } else if (choice == 6) {// clear all orders
                            order.clearAllOrder();
                            System.out.println("All orders successfully cleared");
                        } else if (choice == 7) {//display orders
                            System.out.println(order.toString());
                            System.out.println("Total: " + payment.calculateTotal() + " ₺");

                        } else if (choice == -1) {//continue or go to payment page
                            System.out.println("[press 1 to continue ordering]\t[press 2 to PAY]");
                            System.out.print("Choice:");
                            choice = input.nextInt();
                            if (choice == 1) {
                                continue;
                            } else if (choice == 2) {//go to payment page
                                if (order.getSize() > 0) {
                                    showMenuPage = false;
                                    showPaymentPage = true;
                                    break;
                                } else {
                                    System.out.println("first you need to order to pay");
                                }
                            }
                        } else {
                            System.out.println("invalid input. Enter a valid integer value!!!");
                        }
                    }
                } else if (choice == 2) {//go to home page
                    showHomePage = true;
                    showMenuPage = false;
                } else if (choice == -1) {// go to authentication page
                    showAuthenticationPage = true;
                    showMenuPage = false;
                } else if (choice == -2) {// EXIT
                    exit = true;
                } else {
                    System.out.println("enter a valid email and password with bigger than 6 letters");
                }
            }
            else if (showPaymentPage) {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tPAYMENT PAGE");
                while (true) {
                    if (order.getSize() == 0) break;
                    System.out.println("***************************************************************************************************************************");
                    System.out.println("[press 1 for CASH]\t[press 2 for CREDIT CARD]\t[press 3 for HOME PAGE]\t[press 4 to continue ORDERING]\t[press -2 to EXIT]");
                    System.out.println("***************************************************************************************************************************");

                    validateChoice();
                    if (choice == 1) {
                        while (true) {
                            System.out.println("your total payment is: " + payment.getTotal() + " ₺");
                            validateMoney();
                            payingWithCash = payment.payWithCash(money);
                            if (payingWithCash) {
                                showHomePage = true;
                                showPaymentPage = false;
                                break;
                            }
                        }
                    } else if (choice == 2) {
                        System.out.println("your total payment is: " + payment.getTotal() + " ₺");
                        while (true) {
                            System.out.print("enter your credit card number(16 digits): ");
                            String creditCardNumber = input.next();
                            System.out.print("enter your password(4 digits): ");
                            String password = input.next();
                            boolean ispayed = payment.payWithCreditCard(creditCardNumber, password);
                            if (ispayed) {
                                showHomePage = true;
                                showPaymentPage = false;
                                break;
                            }
                        }
                    } else if (choice == 3) {
                        showPaymentPage = false;
                        showHomePage = true;
                        break;
                    } else if (choice == 4) {
                        showPaymentPage = false;
                        showMenuPage = true;
                        break;
                    } else if (choice == -2) {
                        if (order.getSize() > 0) {
                            System.out.println("if you exit, the orders will be DELETED");
                            System.out.println("do you want to EXIT [Y] [N]");
                            System.out.print("Choice: ");
                            strChoice = input.next().toUpperCase(Locale.ROOT);
                            if (strChoice.equals("Y")) {
                                order.clearAllOrder();
                                exit = true;
                                break;
                            } else if (strChoice.equals("N")) {
                                continue;
                            } else {
                                System.out.println("invalid input please enter [Y]or[N]");
                            }
                        } else {
                            exit = true;
                            break;
                        }
                    } else {
                        System.out.println("invalid input. Enter a valid integer value!!!");
                    }
                }
            }
            else if (showUserSettings) {
                while (true) {
                    System.out.println("\n\n\t\t\t\t\t\t\tUSER SETTING\n");
                    System.out.println("*******************************************************************");
                    System.out.println("[press 1 to see my profile]\t\t[press 2 to HOME PAGE]");
                    System.out.println("*******************************************************************");
                    validateChoice();
                    if (choice == 1) {
                        User user = auth.getCurrentUser();
                        System.out.println("\n\n");
                        System.out.println(user.toString());
                        System.out.println(order.orderHıstoryToString());
                    } else if (choice == 2) {
                        showUserSettings = false;
                        showHomePage = true;
                        break;
                    } else {
                        System.out.println("invalid input. Enter a valid integer value!!!");
                    }
                }
            }
        }
    }

    public static void authentication() {
        String dummyEmail;
        String dummyPassword;
        System.out.println("\n\n\n\n\t\t\t\t\t\t\tAUTHENTICATION PAGE\n");

        while (true) {
            auth.fetchData();
            System.out.println("[press 1 for SIGN IN]\t\t[press 2 for REGISTER]\t\t[press -1 to EXIT]");
            validateChoice();

            if (choice == -1) {//EXIT
                exit = true;
                break;
            }

            if (choice == 1) {//SIGN IN
                System.out.print("email: ");
                dummyEmail = input.next();
                System.out.print("password: ");
                dummyPassword = input.next();

                if (auth.signInWithEmailAndPassword(dummyEmail, dummyPassword)) {
                    showAuthenticationPage = false;
                    showHomePage = true;
                    break;
                }

            } else if (choice == 2) {//REGISTER
                System.out.print("enter email: ");
                dummyEmail = input.next();
                System.out.print("enter password: ");
                dummyPassword = input.next();

                if (dummyEmail.contains("@") && dummyPassword.length() > 6) {
                    auth.registerWithEmailAndPassword(dummyEmail, dummyPassword);
                } else {
                    System.out.println("enter a valid email like [example@gmail.com] and password with bigger than 6 letters");
                }
            }
        }
    }
    public static void validateChoice() {
        while (true) {
            System.out.print("Choice: ");
            if (input.hasNextInt()) {
                choice = input.nextInt();
                if (choice > 7) System.out.println("Enter Valid Number");
                else {
                    break;
                }
            }
            else if(input.hasNextDouble()){
                money = input.nextDouble();
                break;
            }
            else {
                input.next();
                System.out.println("invalid input. Enter a valid integer value!!!");
            }
        }
    }
    public static void validateMoney() {
        while (true) {
            System.out.print("Enter Money: ");
            if (input.hasNextInt()) {
                money = input.nextInt();
                if (money < 0) System.out.println("Enter Valid Number");
                else {
                    break;
                }
            }
            else if(input.hasNextDouble()){
                money = input.nextDouble();
                break;
            }
            else {
                input.next();
                System.out.println("invalid input. Enter a valid integer value!!!");
            }
        }
    }
    public static void validateAmount() {
        while (true) {
            System.out.print("Amount: ");
            if (input.hasNextInt()) {
                amount = input.nextInt();
                break;
            } else {
                input.next();
                System.out.println("invalid input. Enter a valid integer value!!!");
            }
        }
    }
    public static void displayMenuChoices() {
        System.out.println("**************************************************************************************************************");
        System.out.println("[press 1 for Breakfast]\t[press 2 for Main Dishes]\t[press 3 for Deserts]\t[press 4 for Beverages]");
        System.out.println("[press 5 clear order]\t[press 6 clear all orders]\t[press 7 for see the order]\t[press -1 for PAYMENT]");
        System.out.println("**************************************************************************************************************");
    }
    public static void addBreakfast() {
        System.out.println(menu.getAllBreakfast());
        System.out.print("enter your meal number to order: ");
        validateChoice();
        System.out.print("Amount: ");
        validateAmount();
        for (int i = 0; i < amount; i++) {
            order.addItemToOrder(menu.getItemFromBreakfast(choice));
        }
    }
    public static void addMainDishes() {
        System.out.println(menu.getAllMainDishes());
        System.out.print("enter your meal number to order: ");
        validateChoice();
        System.out.print("Amount: ");
        validateAmount();
        for (int i = 0; i < amount; i++) {
            order.addItemToOrder(menu.getItemFromMainDishes(choice));
        }

    }
    public static void addDeserts() {
        System.out.println(menu.getAllDeserts());
        System.out.print("enter your meal number to order: ");
        validateChoice();
        System.out.print("Amount: ");
        validateAmount();
        for (int i = 0; i < amount; i++) {
            order.addItemToOrder(menu.getItemFromDeserts(choice));
        }
    }
    public static void addBevereges() {
        System.out.println(menu.getAllBeverages());
        System.out.print("enter your meal number to order: ");
        validateChoice();
        System.out.print("Amount: ");
        validateAmount();
        for (int i = 0; i < amount; i++) {
            order.addItemToOrder(menu.getItemFromBeverages(choice));
        }
    }
}



