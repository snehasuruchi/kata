package com.suruchi.pos;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.Test;

import com.suruchi.pos.rules.SpecialRules;

public class ProcessItems {
	
	String item;
    double price;
    double weight;
    double  quantity ;
    double numsOfUnit;
    boolean ScanContinue = true;
    public double checkoutTotal =0;
    
  // Items with its Unit of Measurement HashMap
  HashMap<String,String> itemUOM = new HashMap<String,String>();
  
  //Items with its Price HashMap
  HashMap<String,Double> itemPriceList = new HashMap<String,Double>();
  
  //Items with its  Marked down price HashMap
  HashMap<String,Double> itemMarkedDownPriceList = new HashMap<String,Double>();
  
  //weekly special items
  HashMap<String,String> weeklySpecials = new HashMap<String,String>();
  
  
  Scanner scanner = new Scanner(System.in);
  
  
  public ProcessItems(){
	  
	  
	// Put default items to the itemUOM map
  itemUOM.put("pepsi", "ea");
  itemUOM.put("soup", "ea");
  itemUOM.put("oranges", "lb");
  itemUOM.put("onions", "lb");
  itemUOM.put("avocado", "ea");
  itemUOM.put("pineapple","ea");
  itemUOM.put("cauliflower","ea");
  itemUOM.put("celery","ea");
  itemUOM.put("Eggs","ea");

  
//Put default items to the itemPriceList map
  itemPriceList.put("pepsi", 1.5);
  itemPriceList.put("soup",1.20);
  itemPriceList.put("oranges",1.40);
  itemPriceList.put("onions",1.5);
  itemPriceList.put("avocado",1.10);
  itemPriceList.put("pineapple",1.29);
  itemPriceList.put("cauliflower",2.50);
  itemPriceList.put("celery",1.50);
  itemPriceList.put("Eggs",1.50);
  
//Put items to the itemMarkedDownPriceList map
  
  itemMarkedDownPriceList.put("pineapple", 1.09);
  itemMarkedDownPriceList.put("soup", 1.0);
  itemMarkedDownPriceList.put("avocado", .50);
  itemMarkedDownPriceList.put("pepsi", 1.20);
  itemMarkedDownPriceList.put("cauliflower", 1.20);
  //itemMarkedDownPriceList.put("celery", 0.99);
  
  
  //Put items in the weeklySpecial map
  weeklySpecials.put("Eggs", "BNGMPX-1:1:100:5");
  weeklySpecials.put("celery", "BNGMPX-2:1:50:U");
  
  
  }
  
  //Method to get the scanned item
  public void processInputItem(){
	  System.out.println("Press R when ready for checkout");
	  System.out.println("Press X for item cancellation");
	  System.out.println("Enter the item: ");
	  while(ScanContinue)
	  {
	  
	  item= scanner.next();
	   if (item.equalsIgnoreCase("R"))
	  {
		  ScanContinue = false;
		  System.out.println("Thanks For Shopping with Us");
		  continue;
	  }
	   
	   else if (item.equalsIgnoreCase("X"))
		  {
			  //ScanContinue = false;
		   	   numsOfUnit=cancelItem();
			  
		  }
	   
	  
	  else if (getItemUOM(item).equals("ea")){
	  System.out.println("Enter the quantity: ");
	  quantity = scanner.nextDouble();
	  numsOfUnit=quantity;
	  
	  }
	  else if (getItemUOM(item).equals("lb"))
	  {
		  System.out.println("Enter the weight in LBS: ");
		  weight = scanner.nextDouble();
		  numsOfUnit=weight;
	  }

		printOutput(item,numsOfUnit); 
		 
	  System.out.println("Enter the item: ");
   }
	  
	  
  }
  
   
  //Method to get the items corresponding Unit of Measurement
  public String getItemUOM(String item){ 
	  
  String uOM = itemUOM.get(item);  
  
  return uOM;
  
 }
  
  //Method to calculate the price of the item
  public double getItemPrice(String item,double noOfUnits){
	  //getting the item's price from the marked down list
	 
	  
	  if(itemPriceList.get(item) == null){
		  price = 0;
	  }
	  else{
		  price = itemPriceList.get(item)*noOfUnits;
	  }
	  return price;
  }
   
  
  //Method to remove an item 
  public double cancelItem(){
	  double cancelledNumsOfUnit=0;
	  System.out.println("Enter the item you want to cancel: ");
	  item= scanner.next();
	   if (getItemUOM(item).equals("ea")){
		  System.out.println("Enter the quantity of items to cancel: ");
		  quantity = scanner.nextDouble();
		  cancelledNumsOfUnit=quantity*-1;
		  
		  }
		  else if (getItemUOM(item).equals("lb"))
		  {
			  System.out.println("Enter the weight of the item you want to cancel: ");
			  weight = scanner.nextDouble();
			  cancelledNumsOfUnit=weight*-1;
		  }
	   return cancelledNumsOfUnit; 
  }
  
  public void printOutput(String printItem,double printNumsOfUnit){
	  
	  double specialsPrice = 0;
	  double standardPrice = 0;
	  
	  standardPrice = getItemPrice(printItem,printNumsOfUnit);
	  specialsPrice = getItemMarkedDownPrice(printItem,printNumsOfUnit);
	  //If MarkDown is not maintained then check the weekly special rules
	      if(specialsPrice==0 & !(weeklySpecials.get(item) == null))
	      {
	    	  String ruleName = weeklySpecials.get(item);
	    	    if (ruleName.startsWith("BNGMPX"))
	    			   {		   
	    	           SpecialRules sr = new SpecialRules();
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
	    	           if(ItemLimit > printNumsOfUnit || splittedValues[3].equals("U"))
	    	           {
	    	        	 // Calculate Quantities based on Rules and Input
	    	        	  // If the rule says Buy 1 Get 1 free and user bought 100 items so only 50 items will be charged
	    	        	   double WeightageRemainder = printNumsOfUnit % (noOfItemsRule+noOfSpecialItemsRule);
	    	        	   // Make sure to have the quantity which can be distributed as per rule
	    	        	   printNumsOfUnit = printNumsOfUnit - WeightageRemainder;
	    	        	   double noOfItems = printNumsOfUnit * (noOfItemsRule/(noOfItemsRule+noOfSpecialItemsRule));
	    	        	   double noOfSpecialItems = printNumsOfUnit * (noOfSpecialItemsRule/(noOfItemsRule+noOfSpecialItemsRule));
	    	        	   
	    	        	   specialsPrice = sr.getPriceBNGMPX(printItem, noOfItems, noOfSpecialItems, discountRate);
	    	            // Add the Standard Price of Remainder
	    	        	   specialsPrice = specialsPrice + getItemPrice(printItem,WeightageRemainder);
	    			   }
	    	  
	      
	      }
	  }
	  
	  
			  if(specialsPrice>0){
				 
				  System.out.println("Your Special price is : "+specialsPrice);
				  double differencePrice = standardPrice - specialsPrice;
				  checkoutTotal = checkoutTotal +specialsPrice; 
				  System.out.println("Total is : "+checkoutTotal);
				  System.out.println("You save: " + differencePrice + " on "+printItem);
			  }else
			  {
				  checkoutTotal = checkoutTotal +  standardPrice;
				  System.out.println("Total is : "+checkoutTotal);
			  }
			  
	  
  }
  
  //Method to calculate the marked down price of item
  public double getItemMarkedDownPrice(String item,double noOfUnits){
	  Double markedDownItemPrice = itemMarkedDownPriceList.get(item);
	  
	  if(markedDownItemPrice == null){
		  price = 0;
	  }
	  else{
		  price = markedDownItemPrice*noOfUnits;
	  }
	  return price;
	  
  }
}
