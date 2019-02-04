package com.suruchi.pos;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.Test;

public class ProcessItems {
	
	String item;
    double price;
    double weight;
    double  quantity ;
    double numsOfUnit;
    boolean ScanContinue = true;
    public double checkoutTotal =0;
  // Create a hash map
  HashMap<String,String> itemUOM = new HashMap<String,String>();
  
  HashMap<String,Double> itemPriceList = new HashMap<String,Double>();
  
  Scanner scanner = new Scanner(System.in);
  
  
  public ProcessItems(){
	// Put default items to the itemUOM map
  itemUOM.put("pepsi", "ea");
  itemUOM.put("soup", "ea");
  itemUOM.put("oranges", "lb");
  itemUOM.put("onions", "lb");
  itemUOM.put("avocado", "ea");
  
//Put default items to the itemPriceList map
  itemPriceList.put("pepsi", 1.5);
  itemPriceList.put("soup",1.20);
  itemPriceList.put("oranges",1.40);
  itemPriceList.put("onions",1.5);
  itemPriceList.put("avocado",1.10);
  
  }
  
  //Method to get the scanned item
  public void processInputItem(){
	  System.out.println("Press R when ready for checkout");
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
	  
	  else if (getItemUOM(item).equals("ea")){
	  System.out.println("Enter the quantity: ");
	  quantity = scanner.nextDouble();
	  numsOfUnit=quantity;
	  
	  }
	  else if (getItemUOM(item).equals("lb"))
	  {
		  System.out.println("Enter the weight: ");
		  weight = scanner.nextDouble();
		  numsOfUnit=weight;
	  }

		printOutput(item,numsOfUnit); 
		 System.out.println("Total is : "+checkoutTotal);
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
	  price = itemPriceList.get(item)*noOfUnits;
	 return price;
	  }
  
  
  public void printOutput(String printItem,double printNumsOfUnit){
	  
	  checkoutTotal = checkoutTotal + getItemPrice(printItem,printNumsOfUnit);
	 
  }
}
