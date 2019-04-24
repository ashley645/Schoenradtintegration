import java.util.ArrayList;
import java.util.Scanner;

// Ashley Schoenradt
// Intergration Project
// Coffee ordering machine that takes orders, saves orders, and creates new drink menu options.

public class Main {

	public static ArrayList<Order> orders = new ArrayList<Order>();
	public static MenuItem[] drinks = new MenuItem[100];
	public static Scanner scanner;
	public static boolean run[][] = new boolean[1][1];
	
	
	//This is the main function, all it does it initiate the scanner and start the program
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		runProgram();
	}
	
	//first it creates some drinks to go in the menu, then it starts a while loop that will keep running until run is set to false.
	public static void runProgram() {
		createOriginalDrinks();
		run[0][0] = true;

		while (run[0][0]) {
			printMainMenu();
			try {
				int i = scanner.nextInt();
				scanner.nextLine();
				mainMenuInput(i);
			}catch(java.util.InputMismatchException e) {
				println("Hey you entered something bad. Please try again");
				scanner.nextLine();
			}catch(Exception e) {
				println(e.getMessage());
				scanner.nextLine();
			}
		}
		System.out.println("Goodbye!");
		System.exit(0);
	}

	//This creates 3 drinks to go on the menu
	public static void createOriginalDrinks() {
		MenuItem m = new MenuItem("Coffee", 3.31);
		drinks[3] = m;

		MenuItem n = new MenuItem("Latte", 3.15);
		drinks[1] = n;
		
		MenuItem o = new MenuItem("Iced Coffee", 3.25);
		drinks[2] = o;
	}
	
	//overloaded the createOriginal drinks to make more expensive drinks if you pass in true
	public static void createOriginalDrinks(Boolean expensive) {
		
		if(expensive) {
			MenuItem m = new MenuItem("Coffee", 20.31);
			drinks[3] = m;

			MenuItem n = new MenuItem("Latte", 30.15);
			drinks[1] = n;

			MenuItem o = new MenuItem("Iced Coffee", 30.15);
			drinks[2] = o;
		}else {
			createOriginalDrinks();
		}
	}
	
	

	//This function prints out the menu options
	public static void printMainMenu() {
		System.out.println("\n\nWelcome to the Coffee Creator!\n\n");
		System.out.println("\t1.Order a drink.");
		System.out.println("\t2.Create a drink.");
		System.out.println("\t3.View Orders.");
		System.out.println("\t4.View Cheapest Drink.");
		System.out.println("\t5.Close.\n\n");

	}

	//this function is called after the user gives some answer for the menu, it uses a switch case to decide which option the user picked
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
			println("Cheapest drink: "+m.getDrinkName()+" $"+m.getPrice()+"");
			break;
		case 5:
			quit();
			break;
		default:
			println("Please enter a valid input.");
			break;
		}

	}

	//This is just a shortcut function for System.out.print
	public static void print(String s) {
		System.out.print(s);
	}
	//This is a shortcut function for System.out.println
	public static void println(String s) {
		System.out.println(s);
	}

	//This function creates a new drink order. First it asks for a name, then lets you pick a drink, then it saves the drink in the array
	public static void orderADrink() {
		String name = askName();
		int drink = askDrink();
		createOrder(drink, name);
	}

	//This lets the user create a new item to go on the menu. First it asks for a drink name, then a price, then it saves the menu item in the List
	public static void createMenuItemUserInput() {
		String DrinkName = drinkName();
		double price = customOrderPrice();
		createMenuItem(DrinkName, price);

	}

	//This asks for a price, and returns the price they entered
	public static double customOrderPrice() {
		System.out.println("Please provide a price.\n");
		double i = getDouble();
		return i;
	}

	//This asks for a name, and then returns the name the user entered
	public static String drinkName() {
		System.out.println("Please provide the name of drink.");
		return scanner.nextLine();
	}

	//This asks for a name, and returns the name the user entered. It also can catch an error if the user entered something bad.  
	public static String askName() {
			
		try {
			System.out.println("Please provide a name for the order.");
			return scanner.nextLine();

		}catch(java.util.InputMismatchException e) {
			println("Hey you entered something bad. Please try again");
			scanner.nextLine();
			return askName();
		}catch(Exception e) {
			println(e.getMessage());
			scanner.nextLine();
			return askName();
		}	
	}

	//this function asks the user to select a drink from the menu, and returns whatever number they enter. If they enter 0 it just returns 0 which we will turn into a random drink later. 
	public static int askDrink() {
		System.out.println("Please select a drink, or enter 0 for random.\n");
		printDrinkMenu();
		int i = getNumber();
		return i;
	}

	//This function takes the index of a drink, and the name the user entered, and creates a drink order. Then it displays the orders. 
	public static void createOrder(int i, String name) {
		Order o = new Order();
		MenuItem m = getDrink(i);
		o.Name = name;
		o.Drink = m.getDrinkName();
		o.Price = m.getPrice();
		createOrder(o);
		displayAllOrders();
	}

	//This takes a drink name and price to create a new menu item. It can also check for existing drink names! 
	public static void createMenuItem(String DrinkName, Double price) {
		MenuItem m = new MenuItem();
		m.setDrinkName (DrinkName);
		m.setPrice (price);
		if (checkForDuplicate(DrinkName)) {
			System.out.println("You can't! That drink name is already taken! \n");
		} else {
			createMenuItem(m);
		}
	}
	
	
	//checks for existing drink names by comparing a string to every existing menu item and returns true or false 
	public static Boolean checkForDuplicate(String s) {

		for (int i = 1; i < drinks.length - 1; i++) {
			if (drinks[i] != null && (drinks[i].getDrinkName().compareTo(s) == 0 || drinks[i].getDrinkName().equals(s))) {
				// == compares object references, it checks to see if the two operands point to
				// the same object
				return true;
			}
		}
		return false;

	}

	//This function just accepts an int from the user and can handle errors! If it runs into an error it will call itself again to try again until it can return a valid int!
	public static int getNumber() {

		try {
			int i = scanner.nextInt();
			scanner.nextLine();
			return i;

		}catch(java.util.InputMismatchException e) {
			println("Hey you entered something bad. Please try again");
			scanner.nextLine();
			return getNumber();
		}catch(Exception e) {
			println(e.getMessage());
			scanner.nextLine();
			return getNumber();
		}	
	}

	//This is just like the above function but asks for and returns a double
	public static double getDouble() {
		
		
		try {
			double i = scanner.nextDouble();
			scanner.nextLine();
			return i;

		}catch(java.util.InputMismatchException e) {
			println("Hey you entered something bad. Please try again");
			scanner.nextLine();
			return getDouble();
		}catch(Exception e) {
			println(e.getMessage());
			scanner.nextLine();
			return getDouble();
		}
		

	}

	//this function returns a drink at an index you select, but if you give it a 0 it will give you a random drink instead. 
	public static MenuItem getDrink(int x) {
		int count = getMenuItemCount();
		return x == 0 ? drinks[1 + (int) (Math.random() * ((count - 1) + 1))] : drinks[x];
	}
	
	//this uses a for loop to get the number of drinks on the menu
	public static int getMenuItemCount() {
		int count = 0;
		for (int i = 1; i < drinks.length - 1; i++) {
			if (drinks[i] != null) {
				count += 2;
				count--;
			}
		}
		return count;
	}

	//This just displays the drink menu
	public static void printDrinkMenu() {
		System.out.println("\n");
		for (int i = 1; i < drinks.length - 1; i++) {
			if (drinks[i] != null) {
				System.out.println(i + ". " + drinks[i].getDrinkName() + "\t$" + drinks[i].getPrice());

			}
		}
		System.out.println("\n");
	}

	// this will display all orders using a for each loop
	public static void displayAllOrders() {
		int i = 1;
		
		for(Order o : orders) {
			
			System.out.println(i + ". Name: " + o.Name + "\t Price: $" + o.Price + "\tDrink: "
					+ o.Drink);
			i++;
		}
	}

	//creates an order when you pass in an Order object 
	public static void createOrder(Order o) {
		orders.add(o);
	}

	//creates a menu item when you pass in a menu item object 
	public static void createMenuItem(MenuItem m) {
		for (int i = 1; i < drinks.length - 1; i++) {
			if (drinks[i] == null) {
				drinks[i] = m;
				break;
			}
		}
	}

	//loops through the drinks and returns the cheapest one
	public static MenuItem getCheapestDrink() {
		MenuItem cheapo = null;
		for (int i = 1; i < drinks.length - 1; i++) {
			if ((drinks[i] != null && cheapo == null) || (cheapo != null && drinks[i] != null && drinks[i].getPrice() < cheapo.getPrice())) {
				cheapo = drinks[i];
			}
		}
		return cheapo;
	}
	
	
	//uses an accumulator to add up all the price of the menu items
	public static double getTotalPriceOfAllMenuItems() {
		double accumulator = 0;
		
		for (MenuItem drink : drinks) {
			if (drink != null) {
				accumulator += drink.getPrice();
			}
		}
		return accumulator;
	}
	
	
	
	
	//this will set run to false, and it uses a % to calculate even or odd. It will tell you if there is an even or odd number of items on the menu, and the total price of all items. 
	public static void quit() {
		run[0][0] = false;

		if (getMenuItemCount() % 2 == 1) {
			do {
				println("Theres an odd number of items on the menu. ");
			} while (run[0][0]);

		} else {
			println("Theres an even number of items on the menu. ");
		}
		
		System.out.println("Also, the total cost of the items on the drink menu is: $"+getTotalPriceOfAllMenuItems());

	}
}

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

// Variable is a
// Scope is where a variable is only available where the method is prepared.
// control+shift+f to format
