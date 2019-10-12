import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import diet.Food;
import diet.NutritionalElement;
import diet.Menu;
import diet.Recipe;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


public class TestR2_Products {
	
	private Food food;

	
	@Test
	public void testDefinition(){
		food = new Food();


		int initialSize = food.products().size();
		food.defineProduct("Cracker", 111, 2.6, 17.2, 3.5);
		
		int finalSize = food.products().size();

		assertEquals(0,initialSize);
		assertEquals(1,finalSize);
	}

	@Test
	public void testProductColleciton(){
		food = new Food();
		food.defineProduct("Cracker", 111, 2.6, 17.2, 3.5);

		Collection<NutritionalElement> c = food.products();

		NutritionalElement en = (NutritionalElement)c.iterator().next();

		assertEquals("Cracker",en.getName());
		assertEquals(111,en.getCalories(),0.001); 
		assertEquals(2.6,en.getProteins(),0.001); 
		assertEquals(17.2,en.getCarbs(),0.001); 
		assertEquals(3.5,en.getFat(),0.001); 
		assertFalse(en.per100g());
	}

	@Test
	public void testProduct(){
		food = new Food();
		food.defineProduct("Cracker", 111, 2.6, 17.2, 3.5);
		NutritionalElement en = food.getProduct("Cracker");

		assertEquals("Cracker",en.getName());
		assertEquals(111,en.getCalories(),0.001); 
		assertEquals(2.6,en.getProteins(),0.001); 
		assertEquals(17.2,en.getCarbs(),0.001); 
		assertEquals(3.5,en.getFat(),0.001); 
		assertFalse(en.per100g());
	}

	@Test
	public void testProductCollectionSorted(){
		food = new Food();
		food.defineProduct("Cracker", 111, 2.6, 17.2, 3.5);
		food.defineProduct("Cornetto Cioccolato", 230, 3, 27, 11);
		food.defineProduct("Barretta Bueno", 122, 2, 10.6, 8);
		food.defineProduct("Mozzarella Light", 206, 25, 2, 11.25);

		Collection<NutritionalElement> c = food.products();
		Iterator<NutritionalElement> it = c.iterator();
		NutritionalElement prev = it.next();
		while(it.hasNext()) {
			NutritionalElement current=it.next();
			assertTrue(current.getName().compareTo(prev.getName())>=0);
		}
	}

}
