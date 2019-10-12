package diet;


/**
 * Represents a recipe of the diet.
 * 
 * A recipe consists of a a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 * 
 *
 */
public class Recipe implements NutritionalElement {
    

	/**
	 * Adds a given quantity of an ingredient to the recipe.
	 * The ingredient is a raw material.
	 * 
	 * @param material the name of the raw material to be used as ingredient
	 * @param quantity the amount in grams of the raw material to be used
	 * @return the same Recipe object, it allows method chaining.
	 */
	
	private String recipeName;
	private RawMaterial materia;
	private double quantita=0;
	//private Map<String,NutritionalElement> materiePrime;
	private Food food;
	
	
	private double calories=0;
	private double proteins=0;
	private double carbs=0;
	private double fat=0;	
	
	/*
	public Recipe(String name, Map<String,NutritionalElement> materiePrime) {
		recipeName=name;
		this.materiePrime=materiePrime;
	}
	*/
	
	public Recipe(String name, Food food) {
		
		recipeName=name;
		this.food=food;
	}
	
	
	
	public Recipe addIngredient(String material, double quantity) {
		quantita=quantity+quantita;
		materia= (RawMaterial) food.getRawMaterial(material);
		
		calories=(materia.getCalories()*quantity/100)+calories;
		proteins=(materia.getProteins()*quantity/100)+proteins;
		carbs=(materia.getCarbs()*quantity/100)+carbs;
		fat=(materia.getFat()*quantity/100)+fat;
		
		return this;
	}

	@Override
	public String getName() {
		return recipeName;
	}

	@Override
	public double getCalories() {
		return calories*100/quantita;
	}

	@Override
	public double getProteins() {
		return proteins*100/quantita;
	}

	@Override
	public double getCarbs() {
		return carbs*100/quantita;
	}

	@Override
	public double getFat() {
		return fat*100/quantita;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Recipe} class it must always return {@code true}:
	 * a recipe expressed nutritional values per 100g
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
	    // a recipe expressed nutritional values per 100g
		return true;
	}

}
