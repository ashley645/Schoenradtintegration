public class MenuItem extends ParentMenuItem {

	public MenuItem() {

	}

	public MenuItem(String DrinkName, Double Price) {
		this._DrinkName = DrinkName;
		super.setPrice(Price);
	}
	// constructor

	// This is polymorphism
	public String getDrinkName() {
		return _DrinkName;
	}
	// get

	public void setDrinkName(String drinkName) {
		this._DrinkName = drinkName;
	}
	// set

	private String _DrinkName;

}