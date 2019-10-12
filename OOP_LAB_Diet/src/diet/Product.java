package diet;

public class Product implements NutritionalElement {
	
	private String productName;
	private double productCalories;
	private double productProteins;
	private double productCarbs;
	private double productFat;
	
	
	public Product(String name, double calories, double proteins, double carbs, double fat) {
		
		productName=name;
		productCalories=calories;
		productProteins=proteins;
		productCarbs=carbs;
		productFat=fat;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return productName;
	}

	@Override
	public double getCalories() {
		// TODO Auto-generated method stub
		return productCalories;
	}

	@Override
	public double getProteins() {
		// TODO Auto-generated method stub
		return productProteins;
	}

	@Override
	public double getCarbs() {
		// TODO Auto-generated method stub
		return productCarbs;
	}

	@Override
	public double getFat() {
		// TODO Auto-generated method stub
		return productFat;
	}

	@Override
	public boolean per100g() {
		// TODO Auto-generated method stub
		return false;
	}

}
