package diet;

public class RawMaterial implements NutritionalElement {

	
	private String rawMaterialName;
	private double rawMaterialCalories;
	private double rawMaterialProteins;
	private double rawMaterialCarbs;
	private double rawMaterialFat;
	
	
	public RawMaterial(String name,double calories, double proteins, double carbs, double fat) {
		
		rawMaterialName=name;
		rawMaterialCalories=calories;
		rawMaterialProteins=proteins;
		rawMaterialCarbs=carbs;
		rawMaterialFat=fat;
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return rawMaterialName;
	}


	@Override
	public double getCalories() {
		// TODO Auto-generated method stub
		return rawMaterialCalories;
	}


	@Override
	public double getProteins() {
		// TODO Auto-generated method stub
		return rawMaterialProteins;
	}


	@Override
	public double getCarbs() {
		// TODO Auto-generated method stub
		return rawMaterialCarbs;
	}


	@Override
	public double getFat() {
		// TODO Auto-generated method stub
		return rawMaterialFat;
	}


	@Override
	public boolean per100g() {
		// TODO Auto-generated method stub
		
		return true;
	}
	
	
}
