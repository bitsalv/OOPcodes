package diet;


/**
 * Represents a complete menu.
 * 
 * It can be made up of both packaged products and servings of given recipes.
 *
 */
public class Menu implements NutritionalElement {
	
	/**
	 * Adds a given serving size of a recipe.
	 * The recipe is a name of a recipe defined in the {@code food}
	 * argument of the constructor.
	 * 
	 * @param recipe the name of the recipe to be used as ingredient
	 * @param quantity the amount in grams of the recipe to be used
	 * @return the same Menu to allow method chaining
	 */
	
	private String nameMenu;
	private Product prodotto;
	private Recipe ricetta;
	//private Map<String,NutritionalElement> ricette;
	//private Map<String,NutritionalElement> prodotti;
	private Food food;
	
	private double caloriesRecipe=0;
	private double proteinsRecipe=0;
	private double carbsRecipe=0;
	private double fatRecipe=0;	
	
	private double caloriesProduct=0;
	private double proteinsProduct=0;
	private double carbsProduct=0;
	private double fatProduct=0;	
	
	
	public Menu(String name, Food food) {
		nameMenu=name;
		this.food=food;
	}
	
	
	//errore qui
	public Menu addRecipe(String recipe, double quantity) {
		
		ricetta=(Recipe) food.getRecipe(recipe);
		
		caloriesRecipe=(ricetta.getCalories()/100)*quantity+caloriesRecipe;
		proteinsRecipe=(ricetta.getProteins()/100)*quantity+proteinsRecipe;
		carbsRecipe=(ricetta.getCarbs()/100)*quantity+carbsRecipe;
		fatRecipe=(ricetta.getFat()/100)*quantity+fatRecipe;
		
		return this;
	}

	/**
	 * Adds a unit of a packaged product.
	 * The product is a name of a product defined in the {@code food}
	 * argument of the constructor.
	 * 
	 * @param product the name of the product to be used as ingredient
	 * @return the same Menu to allow method chaining
	 */
	public Menu addProduct(String product) {
		
		prodotto=(Product) food.getProduct(product);
		
		caloriesProduct=(prodotto.getCalories())+caloriesProduct;
		proteinsProduct=(prodotto.getProteins())+proteinsProduct;
		carbsProduct=(prodotto.getCarbs())+carbsProduct;
		fatProduct=(prodotto.getFat())+fatProduct;
		
		return this;
	}

	@Override
	public String getName() {
		return nameMenu;
	}

	/**
	 * Total KCal in the menu
	 */
	@Override
	public double getCalories() {
		return caloriesProduct+caloriesRecipe;
	}

	/**
	 * Total proteins in the menu
	 */
	@Override
	public double getProteins() {
		return proteinsProduct+proteinsRecipe;
	}

	/**
	 * Total carbs in the menu
	 */
	@Override
	public double getCarbs() {
		return carbsProduct+carbsRecipe;
	}

	/**
	 * Total fats in the menu
	 */
	@Override
	public double getFat() {
		return fatProduct+fatRecipe;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Menu} class it must always return {@code false}:
	 * nutritional values are provided for the whole menu.
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		// nutritional values are provided for the whole menu.
		return false;
	}
}
