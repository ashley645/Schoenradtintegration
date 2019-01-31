import java.util.Scanner;


// Ashley Schoenradt
// Intergration Project
// Coffee ordering machine that takes orders, saves orders, and creates new drink menu options.



public class Main {

  public static MenuItem[] Drinks = new MenuItem[100];
  public static Order[] Orders = new Order[100];
  public static Scanner scanner;
  public static boolean Run = true;

  // booleans binary variables that have two possible values, usually true or false.
  public static void main(String[] args) {
    // This is the main method, or the starting point of the project.
    /*
     * public means that the method is visible and can be called from other objects of other types.
     * This means that you can call a static method without creating an object of the class. void
     * means that the method has no return value.
     */
    CreateOriginalDrinks();
    scanner = new Scanner(System.in);

    while (Run) {
      PrintMainMenu();
      int i = scanner.nextInt();
      scanner.nextLine();
      MainMenuInput(i);
    }
    System.out.println("Goodbye!");
    System.exit(0);

  }


  public static void CreateOriginalDrinks() {
    MenuItem m = new MenuItem();
    m.DrinkName = "Coffee";
    m.Price = 2.31;
    Drinks[3] = m;

    MenuItem n = new MenuItem();
    n.DrinkName = "Latte";
    n.Price = 3.15;
    Drinks[1] = n;

    MenuItem o = new MenuItem();
    o.DrinkName = "Iced Coffee";
    o.Price = 3.15;
    Drinks[2] = o;
  }

  // This is my coffee menu function of original drinks. I used classes and strings to create theses
  // menu items.
  public static void PrintMainMenu() {
    System.out.println("\n\nWelcome to the Coffee Creator!\n\n");
    System.out.println("\t1.Order a drink.");
    System.out.println("\t2.Create a drink.");
    System.out.println("\t3.View Orders.");
    System.out.println("\t4.Close.\n\n");

  }

  // This is my main menu that will print first in the program.
  // \n is an escape sequence that inserts a newline. \t is an escape sequence that inserts a tab.
  public static void MainMenuInput(int i) {

    switch (i) {
      case 1:
        OrderADrink();
        break;
      case 2:
        CreateMenuItemUserInput();
        break;
      case 3:
        DisplayAllOrders();
        break;
      case 4:
        quit();
        break;
      default:
        println("Please enter a valid input.");
        break;
    }

  }

  // I used a switch case to create user input to select an option from the main menu.
  // a switch statement is a multi-branch statement that provides an easy way to execute different
  // parts of code.
  public static void print(String s) {
    System.out.print(s);
  }

  public static void println(String s) {
    System.out.println(s);
  }

  public static void OrderADrink() {
    String name = AskName();
    int drink = AskDrink();
    CreateOrder(drink, name);
  }

  // this function tells the CreateOrder function to expect a String and an int from the user.
  public static void CreateMenuItemUserInput() {
    String DrinkName = DrinkName();
    double price = CustomOrderPrice();
    CreateMenuItem(DrinkName, price);

  }

  // This function tells the CreateMenuItem function to expect a String and a double from the user.
  public static double CustomOrderPrice() {
    System.out.println("Please provide a price.\n");
    double i = getDouble();
    return i;
  }

  // this function expects a double from the user.
  public static String DrinkName() {
    System.out.println("Please provide the name of drink.");
    return scanner.nextLine();
  }

  // This string prints out "please provide the name of drink" and expects user to return a string
  public static String AskName() {
    System.out.println("Please provide a name for the order.");
    return scanner.nextLine();
  }

  // This string prints out "please provide the name of the drink" and expects user to return a
  // string
  public static int AskDrink() {
    System.out.println("Please select a drink.\n");
    PrintDrinkMenu();
    int i = getNumber();
    return i;
  }

  // this function prints out "please select a drink" and expects an int from the user. Also prints
  // out drink menu.
  public static void CreateOrder(int i, String name) {
    Order o = new Order();
    MenuItem m = GetDrink(i);
    o.Name = name;
    o.Drink = m.DrinkName;
    o.Price = m.Price;
    CreateOrder(o);
    DisplayAllOrders();
  }

  // this function expects an int and a string from the user, name and menu item. It then creates
  // the order with the info given from the user.
  public static void CreateMenuItem(String DrinkName, Double price) {
    MenuItem m = new MenuItem();
    m.DrinkName = DrinkName;
    m.Price = price;
    CreateMenuItem(m);
  }

  // casting forces Java to treat a variable as a different data type...
  // MenuItem m is a string and a double and can be cast to a string and a double.. to create menu
  // item.
  // this function expects a string and a double from the user, the drink name and price, then
  // creates them into the coffee menu.
  public static int getNumber() {
    int i = scanner.nextInt();
    scanner.nextLine();
    return i;
  }

  public static double getDouble() {
    double i = scanner.nextDouble();
    scanner.nextLine();
    return i;
  }

  public static MenuItem GetDrink(int x) {
    return Drinks[x];
  }

  public static void PrintDrinkMenu() {
    System.out.println("\n");
    for (int i = 1; i < Drinks.length - 1; i++) {
      if (Drinks[i] != null) {
        System.out.println(i + ". " + Drinks[i].DrinkName + "\t$" + Drinks[i].Price);

      }
    }
    //
    System.out.println("\n");

  }

  // this just prints a new line.
  public static void DisplayAllOrders() {
    for (int i = 1; i < Orders.length - 1; i++) {
      if (Orders[i] != null) {
        System.out.println(i + ". Name: " + Orders[i].Name + "\t Price: $" + Orders[i].Price
            + "\tDrink: " + Orders[i].Drink);

      }
    }
  }

  // this displays all orders and prints the name, price, and drink of the order.
  public static void CreateOrder(Order o) {
    for (int i = 1; i < Orders.length - 1; i++) {
      if (Orders[i] == null) {
        Orders[i] = o;
        break;
      }
    }
  }

  // Orders is an array. Adds order to empty spot and then stops looping and saves the new order.
  // for(int i=1; i<Orders.length-1; i++) is a for loop.
  public static void CreateMenuItem(MenuItem m) {
    for (int i = 1; i < Drinks.length - 1; i++) {
      if (Drinks[i] == null) {
        Drinks[i] = m;
        break;
      }
    }
  }

  // Drinks is an array. Adds drink to empty spot and then stops looping and saves the new drink to
  // the menu. For loop is looping through the array.
  public static void quit() {
    Run = false;
  }
  // boolean that ends program.
}

/*
 * List of Data Types in Java 1. Byte- is an 8-bit complement integer. With the minimum value of
 * -128, and the maximum value of of 127. Byte is used to save space in large arrays. 2. Short- is a
 * 116-bit complement integer. With a minimum value of -32768, and a maximum value of 32767. Short
 * can be used to save memory. 3. Int- is an unsigned 32-bit integer. With a minimum value of 0, and
 * a maximum value of 2^32-1. 4. Long- is a 64-bit complement integer. With a minimum value of -2^32
 * and a maximum value of 2^63-1. Use when in need of a larger range than an int. 5. Float- is a
 * single-precision 32-bit IEEE 754 floating point. Use a float (instead of double) if you need to
 * save memory in large arrays of floating point numbers. This data type should never be used for
 * precise values, such as currency. 6. Double-is a double-precision 64-bit IEEE 754 floating point.
 * For decimal values, this data type is generally the default choice. This data type should never
 * be used for precise values, such as currency. 7. Boolean- has only two possible values: true and
 * false. Use this data type for simple flags that track true/false conditions. 8. Char- is a single
 * 16-bit Unicode character. It has a minimum value of '\u0000' (or 0) and a maximum value of
 * '\uffff' (or 65,535).
 */

// Variable is a
// Scope is where a variable is only available where the method is prepared.
// control+shift+f to format  