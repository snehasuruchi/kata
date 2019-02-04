package pos.test;
//import static org.junit.framework.TestCase.assertEquals;

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
	public void testTheItemPrice(){
		price = pitems.getItemPrice("pepsi", 1);
		String priceStr = Double.toString(price);
		assertEquals("1.5",priceStr);
		
	}
	
	@Test
	public void checkTotalPriceFor1PepsiAnd1Onion(){
		pitems.processInputItem();
		double totalPrice  = pitems.checkoutTotal;
		String priceStr = Double.toString(totalPrice);
		assertEquals("3.0",priceStr);
		
	}

}
