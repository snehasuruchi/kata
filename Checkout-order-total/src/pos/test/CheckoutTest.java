package pos.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.suruchi.pos.ProcessItems;
import com.suruchi.pos.rules.SpecialRules;

public class CheckoutTest {
	
	double price;
	ProcessItems pitems;
	
	@Before
	public void setUp(){
		pitems = new ProcessItems();
	}
	
	
   //To test the standard price of pepsi for its corresponding unit of measurement
	@Test
	public void testTheItemPriceOfPepsi(){
		price = pitems.getItemPrice("pepsi", 1);
		String priceStr = Double.toString(price);
		assertEquals("1.5",priceStr);
		
	}
	
	//To test the standard price of onion for its corresponding unit of measurement
	@Test
	public void testTheItemPriceOfOnion(){
		price = pitems.getItemPrice("onions", 2);
		String priceStr = Double.toString(price);
		assertEquals("3.0",priceStr);
		}
	
	
	//To test the standard price for pepsi and onions 
	@Test
	public void checkTotalPriceFor1PepsiAnd1Onion(){
		pitems.processInputItem();
		double totalPrice  = pitems.checkoutTotal;
		String priceStr = Double.toString(totalPrice);
		assertEquals("3.0",priceStr);
		
	}
	
	//To test the cancellation functionality
	@Test
	public void TestItemCancellationForPepsi2Units(){
		
		double cancelItems  = pitems.cancelItem();
		String cancelStrItems = Double.toString(cancelItems);
		assertEquals("-2.0",cancelStrItems );
		
	}
	
	//To test the cancellation functionality 
	@Test
	public void checkTotalPriceFor4PepsiAnd2CancelledPepsi(){
		pitems.processInputItem();
		double totalPrice  = pitems.checkoutTotal;
		String priceStr = Double.toString(totalPrice);
		assertEquals("3.0",priceStr);
		
	}

	//To test for marked down 
	@Test
	public void testMarkdownPriceFor2Pineapple(){
		price = pitems.getItemMarkedDownPrice("pineapple", 2);
		String priceStr = Double.toString(price);
		assertEquals("2.18",priceStr);
		
		}
	
	//To test weekly specials
	@Test
	public void testGetPriceBNGMPXFor3Celery(){
		SpecialRules sr = new SpecialRules();
	    price =	sr.getPriceBNGMPX("celery", 2, 1, 50);
	    String priceStr = Double.toString(price);
	    assertEquals("3.75",priceStr);
		
	}
	
	//To test the weekly special and marked down
	@Test
	public void checkTotalPriceFor1PepsiAnd2Eggs(){
		pitems.processInputItem();
		double totalPrice  = pitems.checkoutTotal;
		String priceStr = Double.toString(totalPrice);
		assertEquals("2.7",priceStr);
		
	}
}
