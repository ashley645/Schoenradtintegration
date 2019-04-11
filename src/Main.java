import java.util.ArrayList;
import java.util.Scanner;

// Ashley Schoenradt
// Intergration Project
// Coffee ordering machine that takes orders, saves orders, and creates new drink menu options.

public class Main {

	public static ArrayList<Order> Orders = new ArrayList<Order>();
	public static MenuItem[] Drinks = new MenuItem[100];
	public static Scanner scanner;
	public static boolean Run[][] = new boolean[1][1];
	public static MenuItem[] Drinks = new MenuItem[100];
	public static Order[] Orders = new Order[100];
	public static Scanner scanner;
	public static boolean Run = true; 
	//booleans binary variables that have two possible values, usually true or false.
	public static void main(String[] args) {		
		/*public means that the method is visible and can be called from other objects of other types.
		 * This means that you can call a static method without creating an object of the class. 
		 * void means that the method has no return value.
		 */
		createOriginalDrinks();
		scanner = new Scanner(System.in);
		
		while(Run){
			printMainMenu();
			int i = scanner.nextInt();
			scanner.nextLine();
			mainMenuInput(i);
		}
		System.out.println("Goodbye!");
		System.exit(0);
		
	}
	//This is my main method, or just the main starting point of my project.
	
	public static void createOriginalDrinks() {
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
	//This is my coffee menu function of original drinks. I used classes and strings to create theses menu items.
	public static void printMainMenu() {
		System.out.println("\n\nWelcome to the Coffee Creator!\n\n");
		System.out.println("\t1.Order a drink.");
		System.out.println("\t2.Create a drink.");
		System.out.println("\t3.View Orders.");
		System.out.println("\t4.Close.\n\n");

	// booleans binary variables that have two possible values, usually true or
	// false.
	// This is the main method, or the starting point of the project.
	/*
	 * public means that the method is visible and can be called from other objects
	 * of other types. This means that you can call a static method without creating
	 * an object of the class. void means that the method has no return value.
	 */
	public static void main(String[] args) {
		createOriginalDrinks();
		scanner = new Scanner(System.in);
	}
	//This is my main menu that will print first in the program.
	// \n is an escape sequence that inserts a newline. \t is an escape sequence that inserts a tab. 
	public static void mainMenuInput(int i) {

		Run[0][0] = true;
				 switch (i) { 
			        case 1: 
			            orderADrink();
			            break; 
			        case 2: 
			        	createMenuItemUserInput();
			            break; 
			        case 3: 
			        	displayAllOrders();
			            break; 
			        case 4: 
			        	quit();
			        	break;
			        default: 
			            println("Please enter a valid input.");
			            break; 
			        } 	

		while (Run[0][0]) {
			printMainMenu();
			try {
				int i = scanner.nextInt();
				scanner.nextLine();
				mainMenuInput(i);
			} catch (java.util.InputMismatchException e) {
				println("Hey you entered something bad. Please try again");
				scanner.nextLine();
			} catch (Exception e) {
				println(e.getMessage());
				scanner.nextLine();
			}
		}
		System.out.println("Goodbye!");
		System.exit(0);
	}
	//I used a switch case to create user input to select an option from the main menu. 
	// a switch statement is a multi-branch statement that provides an easy way to execute different parts of code. 
	public static void print(String s) {
		System.out.print(s);
	}
	//using system.out.print
	public static void println(String s) {
		System.out.println(s);
	}
	//using system.out.print
	public static void orderADrink() {
		String name = askName();
		int drink = askDrink();
		createOrder(drink, name);
	}
	//this function tells the CreateOrder function to expect a String and an int from the user.
	public static void createMenuItemUserInput() {
		String DrinkName = drinkName();
		double price = customOrderPrice();
		createMenuItem(DrinkName, price);
		
	}
	//This function tells the CreateMenuItem function to expect a String and a double from the user.
	public static double customOrderPrice() {
		System.out.println("Please provide a price.\n");
		double i = getDouble();
		return i;
	} 
	//this function expects a double from the user. 
	public static String drinkName() {
		System.out.println("Please provide the name of drink.");
		return scanner.nextLine();
	}
	//This string prints out "please provide the name of drink" and expects user to return a string
	public static String askName() {
		System.out.println("Please provide a name for the order.");
		return scanner.nextLine();
	}
	// This string prints out "please provide the name of the drink" and expects user to return a string
	public static int askDrink() {
		System.out.println("Please select a drink, or press 0 for random.\n");
		printDrinkMenu();
		int i = getNumber();
		return i;
	}
	// this function prints out "please select a drink" and expects an int from the user. Also prints out drink menu.
	public static void createOrder(int i, String name) {
		Order o = new Order();
		MenuItem m = getDrink(i);
		o.Name = name;
		o.Drink = m.DrinkName;
		o.Price = m.Price;
		createOrder(o);
		displayAllOrders();
	}
	// this function expects an int and a string from the user, name and menu item. It then creates the order with the info given from the user. 
	public static void createMenuItem(String DrinkName, Double price) {
		MenuItem m = new MenuItem();
		m.DrinkName = DrinkName;
		m.Price = price;
		
		if(checkForDuplicate(DrinkName)) 
		{ 
			System.out.println("You can't! That drink name is already taken! \n");
		}
		else {
			createMenuItem(m);
		}
		
		
		
	}
	
	public static Boolean checkForDuplicate(String s) {
		
		for(int i = 1; i < Drinks.length-1; i++) {
			if(Drinks[i] != null && ( Drinks[i].DrinkName.compareTo(s) == 0 || Drinks[i].DrinkName.equals(s))) {
				//== compares object references, it checks to see if the two operands point to the same object 
				return true;
				}
			}
		return false;
		
	}
	
	
	
	
	
	
	
	//casting... MenuItem m is a string and a double and can be cast to a string and a double.. to create menu item.  
	// this function expects a string and a double from the user, the drink name and price, then creates them into the coffee menu. 
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
	
	public static MenuItem getDrink(int x) {
		int count = getMenuItemCount(); 
		return x == 0  ? Drinks[1 + (int)(Math.random() * ((count - 1) + 1))]: Drinks [x] ;
	}
	
	public static int getMenuItemCount() { 
		int count = 0;
		for(int i = 1; i < Drinks.length-1; i++) {
			if(Drinks[i] != null) {
				count += 2;
				count --;
				}
			}
		return count; 
	}
	
	public static void printDrinkMenu() {
		System.out.println("\n");
		for(int i = 1; i < Drinks.length-1; i++) {
			if(Drinks[i] != null) {
				System.out.println(i +". "+Drinks[i].DrinkName+"\t$"+Drinks[i].Price);
			
			}
		}
	//
		System.out.println("\n");
		
	}
	// this just prints a new line.
   public static void displayAllOrders() {
		for(int i = 1; i < Orders.length-1; i++) {
			if(Orders[i] != null) {
				System.out.println(i +". Name: "+Orders[i].Name+"\t Price: $"+Orders[i].Price + "\tDrink: "+Orders[i].Drink);
				continue;//This will stop anything else in the loop from running, but will continue looping. 
			}
		}
   }
	//this displays all orders and prints the name, price, and drink of the order. 
	public static void createOrder(Order o) {
		for(int i = 1; i<Orders.length-1; i++) {
			if(Orders[i] == null) {
				Orders[i] = o;
				break;
			}
		}
	}
	//Orders is an array. Adds order to empty spot and then stops looping and saves the new order. for(int i=1; i<Orders.length-1; i++) is a for loop. 
	public static void createMenuItem(MenuItem m) {
		for(int i = 1; i<Drinks.length-1; i++) {
			if(Drinks[i] == null) {
				Drinks[i] = m;
				break;
				//When the break statement is encountered inside a loop, the loop is immediately terminated and the program control resumes at the next statement
			}
		}
	}
	// Drinks is an array. Adds drink to empty spot and then stops looping and saves the new drink to the menu. For loop is looping through the array.  
	public static void quit() {
		Run = false;
		
		if(getMenuItemCount() % 2 == 1) {
			do {
				println("Theres and odd number of items on the menu. ");
			}while(Run);
			
		}else {
			println("Theres and even number of items on the menu. ");
		}

	}

	public static void createOriginalDrinks() {
		MenuItem m = new MenuItem("Coffee", 3.31);
		Drinks[3] = m;

		MenuItem n = new MenuItem("Latte", 3.15);
		Drinks[1] = n;

		MenuItem o = new MenuItem("Iced Coffee", 3.15);
		Drinks[2] = o;
	}

	// This is my coffee menu function of original drinks. I used classes and
	// strings to create theses
	// menu items.
	// overload method
	public static void createOriginalDrinks(Boolean expensive) {

		if (expensive) {
			MenuItem m = new MenuItem("Coffee", 20.31);
			Drinks[3] = m;

			MenuItem n = new MenuItem("Latte", 30.15);
			Drinks[1] = n;

			MenuItem o = new MenuItem("Iced Coffee", 30.15);
			Drinks[2] = o;
		} else {
			createOriginalDrinks();
		}
	}

	// This is my main menu that will print first in the program.
	// \n is an escape sequence that inserts a newline. \t is an escape sequence
	// that inserts a tab.

	public static void printMainMenu() {
		System.out.println("\n\nWelcome to the Coffee Creator!\n\n");
		System.out.println("\t1.Order a drink.");
		System.out.println("\t2.Create a drink.");
		System.out.println("\t3.View Orders.");
		System.out.println("\t4.View Cheapest Drink.");
		System.out.println("\t5.Close.\n\n");

	}

	// I used a switch case to create user input to select an option from the main
	// menu.
	// a switch statement is a multi-branch statement that provides an easy way to
	// execute different
	// parts of code.
	public static void mainMenuInput(int i) {

		switch (i) {
		case 1:
			orderADrink();
			break;
		case 2:
			createMenuItemUserInput();
			break;
		case 3:
			displayAllOrders();
			break;
		case 4:
			MenuItem m = getCheapestDrink();
			println("Cheapest drink: " + m.getDrinkName() + " $" + m.getPrice() + "");
			break;
		case 5:
			quit();
			break;
		default:
			println("Please enter a valid input.");
			break;
		}

	}

	public static void print(String s) {
		System.out.print(s);
	}

	public static void println(String s) {
		System.out.println(s);
	}

	// this function tells the CreateOrder function to expect a String and an int
	// from the user.
	public static void orderADrink() {
		String name = askName();
		int drink = askDrink();
		createOrder(drink, name);
	}

	// This function tells the CreateMenuItem function to expect a String and a
	// double from the user.
	public static void createMenuItemUserInput() {
		String DrinkName = drinkName();
		double price = customOrderPrice();
		createMenuItem(DrinkName, price);

	}

	// this function expects a double from the user.
	public static double customOrderPrice() {
		System.out.println("Please provide a price.\n");
		double i = getDouble();
		return i;
	}

	// This string prints out "please provide the name of drink" and expects user to
	// return a string

	public static String drinkName() {
		System.out.println("Please provide the name of drink.");
		return scanner.nextLine();
	}

	// This string prints out "please provide the name of the drink" and expects
	// user to return a
	// string

	public static String askName() {

		try {
			System.out.println("Please provide a name for the order.");
			return scanner.nextLine();

		} catch (java.util.InputMismatchException e) {
			println("Hey you entered something bad. Please try again");
			scanner.nextLine();
			return askName();
		} catch (Exception e) {
			println(e.getMessage());
			scanner.nextLine();
			return askName();
		}
	}

	// this function prints out "please select a drink" and expects an int from the
	// user. Also prints
	// out drink menu.
	public static int askDrink() {
		System.out.println("Please select a drink, or enter 0 for random.\n");
		printDrinkMenu();
		int i = getNumber();
		return i;
	}

	public static void createOrder(int i, String name) {
		Order o = new Order();
		MenuItem m = getDrink(i);
		o.Name = name;
		o.Drink = m.getDrinkName();
		o.Price = m.getPrice();
		createOrder(o);
		displayAllOrders();
	}

	// this function expects an int and a string from the user, name and menu item.
	// It then creates
	// the order with the info given from the user.
	public static void createMenuItem(String DrinkName, Double price) {
		MenuItem m = new MenuItem();
		m.setDrinkName(DrinkName);
		m.setPrice(price);
		if (checkForDuplicate(DrinkName)) {
			System.out.println("You can't! That drink name is already taken! \n");
		} else {
			createMenuItem(m);
		}
	}
	// this function checks drink name for a duplicate name.
	// then is prints out that the drink name is already taken if it is

	public static Boolean checkForDuplicate(String s) {

		for (int i = 1; i < Drinks.length - 1; i++) {
			if (Drinks[i] != null
					&& (Drinks[i].getDrinkName().compareTo(s) == 0 || Drinks[i].getDrinkName().equals(s))) {
				// == compares object references, it checks to see if the two operands point to
				// the same object
				return true;
			}
		}
		return false;

	}

	// casting forces Java to treat a variable as a different data type...
	// MenuItem m is a string and a double and can be cast to a string and a
	// double.. to create menu
	// item.
	// this function expects a string and a double from the user, the drink name and
	// price, then
	// creates them into the coffee menu.
	public static int getNumber() {

		try {
			int i = scanner.nextInt();
			scanner.nextLine();
			return i;

		} catch (java.util.InputMismatchException e) {
			println("Hey you entered something bad. Please try again");
			scanner.nextLine();
			return getNumber();
		} catch (Exception e) {
			println(e.getMessage());
			scanner.nextLine();
			return getNumber();
		}
	}

	// exception handling if user something other than a double
	public static double getDouble() {

		try {
			double i = scanner.nextDouble();
			scanner.nextLine();
			return i;

		} catch (java.util.InputMismatchException e) {
			println("Hey you entered something bad. Please try again");
			scanner.nextLine();
			return getDouble();
		} catch (Exception e) {
			println(e.getMessage());
			scanner.nextLine();
			return getDouble();
		}

	}

	// will give a random drink from drink menu when user enters 0
	public static MenuItem getDrink(int x) {
		int count = getMenuItemCount();
		return x == 0 ? Drinks[1 + (int) (Math.random() * ((count - 1) + 1))] : Drinks[x];
	}

	public static int getMenuItemCount() {
		int count = 0;
		for (int i = 1; i < Drinks.length - 1; i++) {
			if (Drinks[i] != null) {
				count += 2;
				count--;
			}
		}
		return count;
	}
	// this will print the drink menu

	public static void printDrinkMenu() {
		System.out.println("\n");
		for (int i = 1; i < Drinks.length - 1; i++) {
			if (Drinks[i] != null) {
				System.out.println(i + ". " + Drinks[i].getDrinkName() + "\t$" + Drinks[i].getPrice());
			}
		}
		System.out.println("\n");
	}

	// this displays all orders and prints the name, price, and drink of the order.
	public static void displayAllOrders() {
		int i = 1;

		for (Order o : Orders) {

			System.out.println(i + ". Name: " + o.Name + "\t Price: $" + o.Price + "\tDrink: " + o.Drink);
			i++;
		}
	}

	// Orders is an array. Adds order to empty spot and then stops looping and saves
	// the new order.
	// for(int i=1; i<Orders.length-1; i++) is a for loop.
	public static void createOrder(Order o) {
		Orders.add(o);
	}

	// Drinks is an array. Adds drink to empty spot and then stops looping and saves
	// the new drink to
	// the menu. For loop is looping through the array.
	public static void createMenuItem(MenuItem m) {
		for (int i = 1; i < Drinks.length - 1; i++) {
			if (Drinks[i] == null) {
				Drinks[i] = m;
				break;
			}
		}
	}

	// This loops through the array and finds the lowest value in the array
	public static MenuItem getCheapestDrink() {
		MenuItem cheapo = null;
		for (int i = 1; i < Drinks.length - 1; i++) {
			if ((Drinks[i] != null && cheapo == null)
					|| (cheapo != null && Drinks[i] != null && Drinks[i].getPrice() < cheapo.getPrice())) {
				cheapo = Drinks[i];
			}
		}
		return cheapo;
	}

	// this calculates the total value of all menu items
	public static double getTotalPriceOfAllMenuItems() {
		double accumulator = 0;

		for (MenuItem drink : Drinks) {
			if (drink != null) {
				accumulator += drink.getPrice();
			}
		}
		return accumulator;
	}

	// boolean that ends program.
	// also will check how many items are in the menu before it quits the program
	public static void quit() {
		Run[0][0] = false;

		if (getMenuItemCount() % 2 == 1) {
			do {
				println("Theres an odd number of items on the menu. ");
			} while (Run[0][0]);

		} else {
			println("Theres an even number of items on the menu. ");
		}

		System.out.println("Also, the total cost of the items on the drink menu is: $" + getTotalPriceOfAllMenuItems());

	}
		
	}
	// boolean that ends program. 	
}
//Precedence rules can be overridden by explicit parentheses. 
/* List of Data Types in Java
1. Byte- is an 8-bit complement integer. With the minimum value of -128, and the maximum value of of 127. Byte is used to save space in large arrays.
2. Short- is a 116-bit complement integer. With a minimum value of -32768, and a maximum value of 32767. Short can be used to save memory.
3. Int- is an unsigned 32-bit integer. With a minimum value of 0, and a maximum value of 2^32-1. 
4. Long- is a 64-bit complement integer. With a minimum value of -2^32 and a maximum value of 2^63-1. Use when in need of a larger range than an int.
5. Float- is a single-precision 32-bit IEEE 754 floating point. Use a float (instead of double) if you need to save memory in large arrays of floating point numbers. This data type should never be used for precise values, such as currency.
6. Double-is a double-precision 64-bit IEEE 754 floating point. For decimal values, this data type is generally the default choice. This data type should never be used for precise values, such as currency.
7. Boolean- has only two possible values: true and false. Use this data type for simple flags that track true/false conditions. 
8. Char- is a single 16-bit Unicode character. It has a minimum value of '\u0000' (or 0) and a maximum value of '\uffff' (or 65,535). */

/*
 * List of Data Types in Java 1. Byte- is an 8-bit complement integer. With the
 * minimum value of -128, and the maximum value of of 127. Byte is used to save
 * space in large arrays. 2. Short- is a 116-bit complement integer. With a
 * minimum value of -32768, and a maximum value of 32767. Short can be used to
 * save memory. 3. Int- is an unsigned 32-bit integer. With a minimum value of
 * 0, and a maximum value of 2^32-1. 4. Long- is a 64-bit complement integer.
 * With a minimum value of -2^32 and a maximum value of 2^63-1. Use when in need
 * of a larger range than an int. 5. Float- is a single-precision 32-bit IEEE
 * 754 floating point. Use a float (instead of double) if you need to save
 * memory in large arrays of floating point numbers. This data type should never
 * be used for precise values, such as currency. 6. Double-is a double-precision
 * 64-bit IEEE 754 floating point. For decimal values, this data type is
 * generally the default choice. This data type should never be used for precise
 * values, such as currency. 7. Boolean- has only two possible values: true and
 * false. Use this data type for simple flags that track true/false conditions.
 * 8. Char- is a single 16-bit Unicode character. It has a minimum value of
 * '\u0000' (or 0) and a maximum value of '\uffff' (or 65,535).
 */

// Variable is a piece of memory that has a data type. 
// Scope is where a variable is only available where the method is prepared.
// control+shift+f to format
//inheritance is is where one object acquires properties of a parent object. 
