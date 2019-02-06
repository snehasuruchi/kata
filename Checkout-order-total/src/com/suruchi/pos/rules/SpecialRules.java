package com.suruchi.pos.rules;

import com.suruchi.pos.ProcessItems;

public class SpecialRules {
	

	
	public double calculatePriceBNGMPX(String printItem,double printNumsOfUnit,String ruleName){
		double SpecialPrice = 0;
        ProcessItems pt1 = new ProcessItems();
        //Read the rule name and Rule parameters
    	 ruleName = ruleName.replaceAll("BNGMPX-", "");
       String[] splittedValues = ruleName.split(":");
   
       
       double noOfItemsRule = Double.valueOf(splittedValues[0]);
       double noOfSpecialItemsRule = Double.valueOf(splittedValues[1]);
       double discountRate = Double.valueOf(splittedValues[2]);
       double ItemLimit = 0;
       if(!splittedValues[3].equals("U"))
    		   {
    	         ItemLimit = Double.valueOf(splittedValues[3]);
    		   }
       
       //Check Allowed Limits. Process only if allowed number of units	    	        			 
       if(ItemLimit >= printNumsOfUnit || splittedValues[3].equals("U"))
       {
    	 // Calculate Quantities based on Rules and Input
    	  // If the rule says Buy 1 Get 1 free and user bought 100 items so only 50 items will be charged
    	   double WeightageRemainder = printNumsOfUnit % (noOfItemsRule+noOfSpecialItemsRule);
    	   // Make sure to have the quantity which can be distributed as per rule
    	   printNumsOfUnit = printNumsOfUnit - WeightageRemainder;
    	   double noOfItems = printNumsOfUnit * (noOfItemsRule/(noOfItemsRule+noOfSpecialItemsRule));
    	   double noOfSpecialItems = printNumsOfUnit * (noOfSpecialItemsRule/(noOfItemsRule+noOfSpecialItemsRule));
    	   
    	   SpecialPrice = getPriceBNGMPX(printItem, noOfItems, noOfSpecialItems, discountRate);
        // Add the Standard Price of Remainder
    	   SpecialPrice = SpecialPrice + pt1.getItemPrice(printItem,WeightageRemainder);
       }    
        
		return SpecialPrice;
		
	}
	
	
	public double getPriceBNGMPX(String item,double noOfItems,double noOfSpecials,double percentageDiscount){
		double SpecialPrice = 0;
		ProcessItems pt1 = new ProcessItems();
		
		//Calculate the price of items which are qualified for specials
        SpecialPrice = pt1.getItemPrice(item,noOfSpecials) -  (pt1.getItemPrice(item,noOfSpecials)* percentageDiscount/100);
		
        //Add the price of items which are not qualified for specials
		SpecialPrice = pt1.getItemPrice(item,noOfItems) + SpecialPrice;
		
		return SpecialPrice;
		
	}
	
	//-3:5:9
	
	public double calculatePriceBNFX(String printItem,double printNumsOfUnit,String ruleName){
		double SpecialPrice = 0;
		ProcessItems pt1 = new ProcessItems();
   	    ruleName = ruleName.replaceAll("BNFX-", "");
        String[] splittedValues = ruleName.split(":");
      
       double noOfItemsRule = Double.valueOf(splittedValues[0]);
       double SpecialPriceRule = Double.valueOf(splittedValues[1]);
      //double discountRate = Double.valueOf(splittedValues[2]);
       double ItemLimit = 0;
       if(!splittedValues[2].equals("U"))
   		   {
   	         ItemLimit = Double.valueOf(splittedValues[2]);
   		   }
      
      //Check Allowed Limits. Process only if allowed number of units	    	        			 
      if(ItemLimit >= printNumsOfUnit || splittedValues[2].equals("U"))
      {
   	 // Calculate Price based on Rules and Input
   	  // Identify the quantity on which special price can be calculated
   	   double WeightageRemainder = printNumsOfUnit % noOfItemsRule;
   	   // Make sure to have the quantity which can be distributed as per rule
   	   printNumsOfUnit = printNumsOfUnit - WeightageRemainder;
   	    SpecialPrice = SpecialPriceRule*(printNumsOfUnit/noOfItemsRule) ;
   	      
   	   
       // Add the Standard Price of Remainder
   	   SpecialPrice = SpecialPrice + pt1.getItemPrice(printItem,WeightageRemainder);
      }    
       
		return SpecialPrice;
		
		
	}
	
}
