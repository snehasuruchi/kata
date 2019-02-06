package com.suruchi.pos;
import java.util.HashMap;
import java.util.Scanner;
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
  public HashMap<String,Double> itemMarkedDownPriceList = new HashMap<String,Double>();
  
  //weekly special items
  HashMap<String,String> weeklySpecials = new HashMap<String,String>();
  
  
  Scanner scanner = new Scanner(System.in);
  
  
  public ProcessItems(){
	  
	  
	// Put default items to the itemUOM map
  itemUOM.put("Pepsi", "ea");
  itemUOM.put("Soup", "ea");
  itemUOM.put("Oranges", "lb");
  itemUOM.put("Onions", "lb");
  itemUOM.put("Avocado", "ea");
  itemUOM.put("Pineapple","ea");
  itemUOM.put("Cauliflower","ea");
  itemUOM.put("Celery","ea");
  itemUOM.put("Eggs","ea");
  itemUOM.put("Cookies","ea");

  
//Put default items to the itemPriceList map
  itemPriceList.put("Pepsi", 1.5);
  itemPriceList.put("Soup",1.20);
  itemPriceList.put("Oranges",1.40);
  itemPriceList.put("Onions",1.5);
  itemPriceList.put("Avocado",1.10);
  itemPriceList.put("Pineapple",1.29);
  itemPriceList.put("Cauliflower",2.50);
  itemPriceList.put("Celery",1.50);
  itemPriceList.put("Eggs",1.50);
  itemPriceList.put("Cookies",2.0);
  
//Put items to the itemMarkedDownPriceList map
  
  itemMarkedDownPriceList.put("Pineapple", 1.09);
  itemMarkedDownPriceList.put("Soup", 1.0);
  itemMarkedDownPriceList.put("Avocado", .50);
  itemMarkedDownPriceList.put("Pepsi", 1.20);
  itemMarkedDownPriceList.put("Cauliflower", 1.20);
  //itemMarkedDownPriceList.put("Celery", 0.99);
  
  
  //Put items in the weeklySpecial map
  //Pricing Rule Code - BNFX is for Buy N For X (e.g Buy 3 for 5)
  //BNFX Rule format is "BNFX-<Number Of Items>:<Price>:<ItemLimit>
  //ItemLimit U is equivalent to Unlimited
  
  //Pricing Rule Code - BNGMPXF is for Buy N For X (e.g Buy 2 and get 50% off for additional 1 item)
  //BNGMPXF Rule format is "BNGMPXF-<Number Of Items>:<Number Of Special Items>:<DiscountPercent>:<ItemLimit>
  //ItemLimit U is equivalent to Unlimited
  
  weeklySpecials.put("Eggs", "BNGMPX-1:1:100:5");
  weeklySpecials.put("Celery", "BNGMPX-2:1:50:U");
  weeklySpecials.put("Cookies", "BNFX-3:5:9");
  
  }
  
  //Method to get the scanned item
  public void processInputItem(){
	  System.out.println("Press R when ready for checkout");
	  System.out.println("Press X for item cancellation");
	  System.out.println("Enter the item: ");
	  while(ScanContinue)
	  {
	  
	  //item= scanner.next().toUpperCase();
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
	  String uOM = "";
	  if(itemUOM.get(item)!=null)  
	{
   uOM = itemUOM.get(item);  
	}else{
	System.out.println("UOM Not Maintained, Enter UOM lb or ea");
	uOM = scanner.next();
	}
		
	  return uOM;
  
 }
  
  //Method to calculate the price of the item
  public double getItemPrice(String item,double noOfUnits){
	 
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
	  item= scanner.next().toUpperCase();
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
	  //Check the markdown price
	  specialsPrice = getItemMarkedDownPrice(printItem,printNumsOfUnit);
	  //If MarkDown is not maintained then check the weekly special rules
	      if(specialsPrice==0 & !(weeklySpecials.get(item) == null))
	    	  
	      {
	    	  String ruleName = weeklySpecials.get(item);
	    	  SpecialRules sr = new SpecialRules();
	           //Check if item is marked for pricing rule BNGMPX
	    	    if (ruleName.startsWith("BNGMPX"))
	    			   {		   
	    	      
	    	    	specialsPrice = sr.calculatePriceBNGMPX(printItem,printNumsOfUnit,ruleName);
	    	    	//Check if item is marked for pricing rule BNFX
	      }   else if (ruleName.startsWith("BNFX"))
	    			   {		   
	    	      
	    	    	specialsPrice = sr.calculatePriceBNFX(printItem,printNumsOfUnit,ruleName);
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
