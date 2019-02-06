package com.suruchi.pos.rules;

import com.suruchi.pos.ProcessItems;

public class SpecialRules {
	
	public double getPriceBNGMPX(String item,double noOfItems,double noOfSpecials,double percentageDiscount){
		double SpecialPrice = 0;
		ProcessItems pt1 = new ProcessItems();

		
        SpecialPrice = pt1.getItemPrice(item,noOfSpecials) -  (pt1.getItemPrice(item,noOfSpecials)* percentageDiscount/100);
		
		SpecialPrice = pt1.getItemPrice(item,noOfItems) + SpecialPrice;
		
		return SpecialPrice;
		
	}
	

}
