package pos.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.suruchi.pos.ProcessItems;

public class CheckoutTest {
	
	double price;
	ProcessItems pitems;
	
	@Before
	public void setUp(){
		pitems = new ProcessItems();
	}
	
	

	@Test
	public void testTheItemPriceOfPepsi(){
		price = pitems.getItemPrice("pepsi", 1);
		String priceStr = Double.toString(price);
		assertEquals("1.5",priceStr);
		
	}
	
	
	@Test
	public void testTheItemPriceOfOnion(){
		price = pitems.getItemPrice("onions", 2);
		String priceStr = Double.toString(price);
		assertEquals("3.0",priceStr);
		}
	
	@Test
	public void checkTotalPriceFor1PepsiAnd1Onion(){
		pitems.processInputItem();
		double totalPrice  = pitems.checkoutTotal;
		String priceStr = Double.toString(totalPrice);
		assertEquals("3.0",priceStr);
		
	}
	
	@Test
	public void TestItemCancellationForPepsi2Units(){
		
		double cancelItems  = pitems.cancelItem();
		String cancelStrItems = Double.toString(cancelItems);
		assertEquals("-2.0",cancelStrItems );
		
	}
	
	@Test
	public void checkTotalPriceFor4PepsiAnd2CancelledPepsi(){
		pitems.processInputItem();
		double totalPrice  = pitems.checkoutTotal;
		String priceStr = Double.toString(totalPrice);
		assertEquals("3.0",priceStr);
		
	}

	@Test
	public void testMarkdownPriceFor2Pineapple(){
		price = pitems.getItemPrice("pineapple", 2);
		String priceStr = Double.toString(price);
		assertEquals("2.18",priceStr);
		
		
		
		
	}
}
