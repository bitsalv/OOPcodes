
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

import diet.Food;
import diet.NutritionalElement;
import diet.Menu;
import diet.Recipe;


public class TestR1_RawMaterials {
	private Food dieta;
	
	
	@Test
	public void testDefinition(){

		dieta = new Food();
		int initialSize = dieta.rawMaterials().size();
		dieta.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
		int finalSize = dieta.rawMaterials().size();

		assertEquals(0,initialSize);
		assertEquals(1,finalSize);
	}

	@Test
	public void testRawMaterialsCollection(){
		dieta = new Food();
		dieta.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
		
		Collection<NutritionalElement> c = dieta.rawMaterials();

		NutritionalElement en = (NutritionalElement)c.iterator().next();

		assertEquals("Pasta",en.getName());
		assertEquals(350,en.getCalories(),0.001); 
		assertEquals(12,en.getProteins(),0.001); 
		assertEquals(72.2,en.getCarbs(),0.001); 
		assertEquals(1.5,en.getFat(),0.001); 
		assertTrue(en.per100g());
	}

	@Test
	public void testRawMaterials(){
		dieta = new Food();
		dieta.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
		NutritionalElement en = dieta.getRawMaterial("Pasta");

		assertEquals("Pasta",en.getName());
		assertEquals(350,en.getCalories(),0.001); 
		assertEquals(12,en.getProteins(),0.001); 
		assertEquals(72.2,en.getCarbs(),0.001); 
		assertEquals(1.5,en.getFat(),0.001); 
		assertTrue(en.per100g());
	}

	@Test
	public void testRawMaterialsCollectionsSorted(){
		dieta = new Food();
		dieta.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
		
		dieta.defineRawMaterial("Zucchero", 400, 0, 100, 0);
		dieta.defineRawMaterial("Mais", 70, 2.7, 12, 1.3);
		
		Collection<NutritionalElement> c = dieta.rawMaterials();
		Iterator<NutritionalElement> it = c.iterator();
		NutritionalElement prev = it.next();
		while(it.hasNext()) {
			NutritionalElement current=it.next();
			assertTrue(current.getName().compareTo(prev.getName())>=0);
			// prev = current;
		}
	}

}
